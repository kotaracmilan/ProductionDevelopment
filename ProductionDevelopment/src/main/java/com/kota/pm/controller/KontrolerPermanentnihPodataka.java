package com.kota.pm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kota.pm.domain.datatype.Dokument;
import com.kota.pm.domain.datatype.JedinicaMere;
import com.kota.pm.domain.datatype.Operacija;
import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.datatype.TrenutniRadniStatusZaposlenog;
import com.kota.pm.domain.datatype.VrstaArtikla;
import com.kota.pm.domain.proizvodnja.Linija;
import com.kota.pm.repository.DokumentRepository;
import com.kota.pm.repository.LinijaRepository;
import com.kota.pm.repository.PogonRepository;

@Component
@Scope("application")
public class KontrolerPermanentnihPodataka{

	@Autowired
	private LinijaRepository linijaRepo;
	
	@Autowired
	private PogonRepository pogonRepo;
	
	@Autowired
	private DokumentRepository dokumentRepo;
	
	//razmisliti da li treba uopste
	private List<Linija> linije;
	private List<Pogon> pogoni;
	private List<JedinicaMere> jediniceMere;
	private List<TrenutniRadniStatusZaposlenog> trenutniRadniStatusZaposlenog;
	private List<Operacija> listaOperacija;
	private List<Dokument> vrsteDokumenata;
	private List<VrstaArtikla> vrstaArtikla;
	
	@PostConstruct
	public void init() {
		linije = linijaRepo.findAll();
		pogoni = pogonRepo.findAll();
		trenutniRadniStatusZaposlenog = Arrays.asList(TrenutniRadniStatusZaposlenog.values());
		listaOperacija = Arrays.asList(Operacija.values());
		vrsteDokumenata = dokumentRepo.findAll();
		jediniceMere = Arrays.asList(JedinicaMere.values());
		vrstaArtikla = Arrays.asList(VrstaArtikla.values());
	}
	
	@Transactional
	public void savePogon(Pogon p) {
		p = pogonRepo.save(p);
		pogoni.add(p);
	}
	
	public void saveDokument(Dokument d) {
		d = dokumentRepo.save(d);
		vrsteDokumenata.add(d);
	}
	
	public Pogon findPogonById(int id) {
		Optional<Pogon> op = pogoni.stream().filter(p -> p.getId()==id).findFirst();
		if(op.isPresent())
			return op.get();
		return null;
	}
	
	public Dokument findDokumentById(Integer id) {
		Optional<Dokument> op = vrsteDokumenata.stream().filter(
				r -> r.getId() == id
				).findFirst();
		if(op.isPresent())
			return op.get();
		return null;
	}

	/////geter seter
	public List<Linija> getLinije() {
		return linije;
	}

	public void setLinije(List<Linija> linije) {
		this.linije = linije;
	}
	
	public List<Pogon> getPogoni() {
		return pogoni;
	}

	public void setPogoni(List<Pogon> pogoni) {
		this.pogoni = pogoni;
	}

	public List<Linija> getLinije(Pogon pogon) {
		return linijaRepo.findAllByPogon(pogon);
	}

	public List<TrenutniRadniStatusZaposlenog> getTrenutniRadniStatusZaposlenog() {
		return trenutniRadniStatusZaposlenog;
	}

	public void setTrenutniRadniStatusZaposlenog(List<TrenutniRadniStatusZaposlenog> trenutniRadniStatusZaposlenog) {
		this.trenutniRadniStatusZaposlenog = trenutniRadniStatusZaposlenog;
	}

	public List<Operacija> getListaOperacija() {
		return listaOperacija;
	}

	public List<Dokument> getVrsteDokumenata() {
		return vrsteDokumenata;
	}

	public void setVrsteDokumenata(List<Dokument> vrsteDokumenata) {
		this.vrsteDokumenata = vrsteDokumenata;
	}

	public List<JedinicaMere> getJediniceMere() {
		return jediniceMere;
	}

	public void setJediniceMere(List<JedinicaMere> jediniceMere) {
		this.jediniceMere = jediniceMere;
	}

	public List<VrstaArtikla> getVrstaArtikla() {
		return vrstaArtikla;
	}
	
	
}
