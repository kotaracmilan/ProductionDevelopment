package com.kota.pm.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kota.pm.domain.datatype.Operacija;
import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.datatype.VrstaArtikla;
import com.kota.pm.domain.product.Normativ;
import com.kota.pm.domain.product.NormativnaSastavnica;
import com.kota.pm.domain.product.Proizvod;
import com.kota.pm.domain.transakcije.Kartica;
import com.kota.pm.domain.transakcije.Stanje;
import com.kota.pm.domain.transakcije.StanjeId;
import com.kota.pm.domain.transakcije.Transakcija;
import com.kota.pm.repository.KarticaRepository;
import com.kota.pm.repository.StanjeRepository;
import com.kota.pm.repository.TransakcijaRepository;

@Service
public class KontrolerProizvodnihTransakcionihPodataka {

	@Autowired
	private KarticaRepository karticeRepo;
	
	@Autowired
	private TransakcijaRepository transakcijaRepo;
	
	@Autowired
	private StanjeRepository stanjeRepo;
	
	/*
	 * Snima transakciju i azurira stanje na datum, i sve naredne datume
	 */
	@Transactional
	public void snimiTransakciju(Transakcija t, Normativ n) {
		Kartica k;
		Optional<Kartica> o = karticeRepo.findByProizvodAndPogon(t.getProizvod(), t.getPogon());
		if(!o.isPresent()) {
			k = new Kartica(); 
			k.setProizvod(t.getProizvod());
			k.setPogon(t.getPogon());
			k.setTransakcije(new ArrayList<Transakcija>());
			k = karticeRepo.save(k);
		}
		else {
			k = o.get();
		}
		transakcijaRepo.save(t);
		k.getTransakcije().add(t);
		updateStanje(t, n);
		karticeRepo.save(k);
	}
	
	/*
	 * Snima popis
	 */
	@Transactional
	public void unesiPopis(Hashtable<StanjeId, Float> listaPopisa) {
		ArrayList<Stanje> stanja = new ArrayList<>();
		
		listaPopisa.forEach( 
				(x,y) -> {
					System.out.println(x + " -> " + y);
			Stanje st = findStanjeById(x);
			st.setStanjePoPopisu(y);
			stanja.add(st);
				}
		);
		
		snimiListu(stanja);
		listaPopisa = new Hashtable<>();
	}
	
	
	///helper methods
	private void updateStanje(Transakcija t, Normativ n) {
		Stanje st = findStanjeById(t.getDatum(), t.getProizvod(), t.getPogon());
		List<Stanje> listaZaPersistence = new ArrayList<Stanje>();
		
		st = promeniStanje(st, t.getDokument().getOperacija(), t.getKolicina());		

		if((t.getProizvod().getVrstaArtikla().equals(VrstaArtikla.POLUPROIZVOD) || t.getProizvod().getVrstaArtikla().equals(VrstaArtikla.PROIZVOD)) && n != null) {
			listaZaPersistence.addAll(izvrsiPromenuPoNormativu(t, n));
		}
		
		listaZaPersistence.add(st);
		listaZaPersistence.addAll(updateRekruzivnoStanje(st, t.getDokument().getOperacija(), t.getKolicina()));
		
		snimiListu(listaZaPersistence);
	}
	
	private Stanje promeniStanje(Stanje s, Operacija operacija, float kolicina) {
		s.setStanjePoTransakcijama(operacija.doTransact(s.getStanjePoTransakcijama(), kolicina));
		return s;
	}
	
	private List<Stanje> izvrsiPromenuPoNormativu(Transakcija t, Normativ normativ) {
		List<Stanje> stanje = new ArrayList<>();
		
		for (NormativnaSastavnica ns: normativ.getNormativnaSastavnica()) {
					
						float odnos = ns.getUdeo() / normativ.getJedinicnaKolicina();
						
						Stanje s = findStanjeById(t.getDatum(),t.getProizvod(), t.getPogon());
						float utrosenaKolicina = t.getKolicina() * odnos;
						s = promeniStanje(s, Operacija.ODUZIMANJE, utrosenaKolicina);
						stanje.add(s);
		}
						
		return stanje;
	}
	
	private void snimiListu(List<Stanje> l) {
		l.stream().forEach(
				s -> stanjeRepo.save(s)
				);
	}
	
	private Stanje findStanjeById(Date datum, Proizvod proizvod, Pogon pogon) {
		Stanje st;
		StanjeId id = new StanjeId();
		
		id.setDatum(datum);
		id.setProizvod(proizvod);
		id.setPogon(pogon);
		
		//testirati
		Optional<Stanje> opSt = stanjeRepo.findById(id); //stanjeRepo.findByIdProizvodAndIdDatumAndIdPogon(proizvod, datum, pogon);
		
		if(opSt.isPresent()) 
		{
			st = opSt.get();
		}
		else 
		{
			st = new Stanje();
			st.setId(id);
				///nacu poslednju promenu za dati datum
			Optional<Stanje> os = stanjeRepo.findFirstByIdProizvodAndIdPogonAndIdDatumBeforeOrderByIdDatumDesc(proizvod, pogon, datum);
			
			if(os.isPresent()) 
			{
				Stanje stTemp = os.get();
				st.setStanjePoTransakcijama(stTemp.getStanjePoTransakcijama());
				st.setStanjePoPopisu(stTemp.getStanjePoPopisu());
			}
		}

		return st;
	}
	
	private Stanje findStanjeById(StanjeId stanjeId) {
		return findStanjeById(stanjeId.getDatum(), stanjeId.getProizvod(), stanjeId.getPogon());
	}
	
	private List<Stanje> updateRekruzivnoStanje(Stanje s, Operacija operacija, float kolicina ) {
		List<Stanje> stanjaZaUpdate = stanjeRepo.findByIdProizvodAndIdPogonAndIdDatumAfterOrderByIdDatumDesc(s.getId().getProizvod(), s.getId().getPogon(), s.getId().getDatum());
		List<Stanje> listaZaSnimanje = new ArrayList<Stanje>();
		stanjaZaUpdate.forEach(
				x -> listaZaSnimanje.add(promeniStanje(x, operacija, kolicina))
				);
		return listaZaSnimanje;
	}
}
