package com.kota.pm.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPermanentnihPodataka;
import com.kota.pm.controller.KontrolerProizvodnihPodataka;
import com.kota.pm.domain.datatype.JedinicaMere;
import com.kota.pm.domain.datatype.VrstaArtikla;
import com.kota.pm.domain.product.Normativ;
import com.kota.pm.domain.product.NormativnaSastavnica;
import com.kota.pm.domain.product.Proizvod;

@Component("proizvodiMB")
public class ProizvodiMB implements Serializable {
	

	@Autowired
	private KontrolerProizvodnihPodataka kpp;
	@Autowired
	private KontrolerPermanentnihPodataka kPerPod;
	

	private Proizvod proizvod;
	private Proizvod proizvodNormativ;
	private Normativ normativ;
	private List<JedinicaMere> jediniceMere;
	private Date danasnjiDatum;
	private NormativnaSastavnica normativnaSastavnica;
	//polja vezana za selekciju proizvoda i normativa
	private NormativnaSastavnica selektovanaKomponenta;
	private Proizvod izabraniProizvod;
	private List<Normativ> listaNormativaZaSelektovaniProizvod;
	private Normativ selektovaniNormativ;
	private boolean ekspandirajSastavnicu;
	private List<Proizvod> ucitaniProizvodi;
	
	 @PostConstruct
	 public void init() {
		 danasnjiDatum = Calendar.getInstance().getTime();
		 proizvod = new Proizvod();
		 jediniceMere = kPerPod.getJediniceMere();
		 normativ = new Normativ();
		 normativ.setActiveFrom(danasnjiDatum);
		 normativ.setNormativnaSastavnica(new ArrayList<NormativnaSastavnica>());
		 normativnaSastavnica = new NormativnaSastavnica();
		 normativ.setIsActive(true);
		 ekspandirajSastavnicu = true;
	 }

	 public void snimiProizvod() {
		 kpp.saveProizvod(proizvod);
		 proizvod = new Proizvod();
	}
	
	public void snimiNormativ() {
		kpp.saveNormativ(normativ);

		normativ = new Normativ();
		normativ.setActiveFrom(danasnjiDatum);
		normativ.setIsActive(true);

	}
	
	public String test() {
		System.out.println("TEST");
		return "test";
	}
	
	/**
	 * Dodaje komponentu u listu sastavnica.
	 * Ukoliko je opcija ekspandiraj ukljucena, rastavlja
	 * komponentu na sastavne delove, po prvom normativu
	 */
	public void dodajSastavnicu() {
		if(normativ == null)
			return;
		if(normativ.getNormativnaSastavnica() == null)
			normativ.setNormativnaSastavnica(new ArrayList<NormativnaSastavnica>());
		if(ekspandirajSastavnicu) {
			List<Normativ> normativiZaKomponentu = kpp.findNormativByProizvod(normativnaSastavnica.getKomponenta());
			if(normativnaSastavnica.getKomponenta().getVrstaArtikla() == VrstaArtikla.POLUPROIZVOD && normativiZaKomponentu.size() > 0) {
				Normativ maliNormativ = normativiZaKomponentu.get(0);
				for(NormativnaSastavnica ns : kpp.findNormativnaSastavnicaByNormativ(maliNormativ)) {
					float jedinicnaKolicina = ns.getUdeo()/maliNormativ.getJedinicnaKolicina();
					NormativnaSastavnica novaSastavnica = new NormativnaSastavnica();
					novaSastavnica.setUdeo(jedinicnaKolicina*normativnaSastavnica.getUdeo());
					novaSastavnica.setKomponenta(ns.getKomponenta());
					novaSastavnica.setJedinicaMere(ns.getJedinicaMere());
					novaSastavnica.setNormativ(normativ);
					normativ.getNormativnaSastavnica().add(novaSastavnica);
				}
			}
			else {
				normativ.getNormativnaSastavnica().add(normativnaSastavnica);
				normativnaSastavnica.setNormativ(normativ);
			}
		}
		else {
			normativ.getNormativnaSastavnica().add(normativnaSastavnica);
			normativnaSastavnica.setNormativ(normativ);
		}
		normativnaSastavnica = new NormativnaSastavnica();
	}
	
	public void testProizvodExists(AjaxBehaviorEvent event) {
		Optional<Proizvod> pr = kpp.findProizvodById(proizvod.getId());
		if(pr.isPresent())
			proizvod = pr.get();
	}
	
	public void popuniNormativ() {
		if(normativ.getProizvod() == null)
			return;
		Normativ n = kpp.findNormativByProizvodAndOznakaNormativa(normativ.getProizvod(), normativ.getOznakaNormativa());
		if(n != null)
			normativ = n;
	}
	
	public void obrisiKomponentu() {
		if(selektovanaKomponenta != null)
			normativ.getNormativnaSastavnica().remove(selektovanaKomponenta);
		selektovanaKomponenta = null;
	}
	
	public void selektovanProizvodUTabeli() {
		listaNormativaZaSelektovaniProizvod = kpp.findNormativByProizvod(izabraniProizvod);
	}
	
	public void obrisiNormativ() {
		kpp.obrisiNormativ(selektovaniNormativ);
	}
	
	public List<Proizvod> autocompleteProizvod(String q) {
		return kpp.findProizvodById(q);
	}
	
	public List<VrstaArtikla> getVrsteArtikla(){
		return kPerPod.getVrstaArtikla();
	}
	
	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public List<JedinicaMere> getJediniceMere() {
		return jediniceMere;
	}

	public void setJediniceMere(List<JedinicaMere> jediniceMere) {
		this.jediniceMere = jediniceMere;
	}

	public Normativ getNormativ() {
		return normativ;
	}

	public void setNormativ(Normativ normativ) {
		this.normativ = normativ;
	}

	public NormativnaSastavnica getNormativnaSastavnica() {
		return normativnaSastavnica;
	}

	public NormativnaSastavnica getSelektovanaKomponenta() {
		return selektovanaKomponenta;
	}

	public void setSelektovanaKomponenta(NormativnaSastavnica selektovanaKomponenta) {
		this.selektovanaKomponenta = selektovanaKomponenta;
	}

	public Proizvod getIzabraniProizvod() {
		return izabraniProizvod;
	}

	public void setIzabraniProizvod(Proizvod izabraniProizvod) {
		this.izabraniProizvod = izabraniProizvod;
	}

	public List<Normativ> getListaNormativaZaSelektovaniProizvod() {
		return listaNormativaZaSelektovaniProizvod;
	}

	public void setListaNormativaZaSelektovaniProizvod(List<Normativ> listaNormativaZaSelektovaniProizvod) {
		this.listaNormativaZaSelektovaniProizvod = listaNormativaZaSelektovaniProizvod;
	}

	public Normativ getSelektovaniNormativ() {
		return selektovaniNormativ;
	}

	public void setSelektovaniNormativ(Normativ selektovaniNormativ) {
		this.selektovaniNormativ = selektovaniNormativ;
	}

	public boolean isEkspandirajSastavnicu() {
		return ekspandirajSastavnicu;
	}

	public void setEkspandirajSastavnicu(boolean ekspandirajSastavnicu) {
		this.ekspandirajSastavnicu = ekspandirajSastavnicu;
	}

	public List<Proizvod> getUcitaniProizvodi() {
		return ucitaniProizvodi;
	}

	public void setUcitaniProizvodi(List<Proizvod> ucitaniProizvodi) {
		this.ucitaniProizvodi = ucitaniProizvodi;
	}
	
}
