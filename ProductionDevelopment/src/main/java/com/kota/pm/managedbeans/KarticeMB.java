package com.kota.pm.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPermanentnihPodataka;
import com.kota.pm.controller.KontrolerProizvodnihPodataka;
import com.kota.pm.controller.KontrolerProizvodnihTransakcionihPodataka;
import com.kota.pm.domain.datatype.Dokument;
import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.product.Normativ;
import com.kota.pm.domain.product.Proizvod;
import com.kota.pm.domain.transakcije.Kartica;
import com.kota.pm.domain.transakcije.Stanje;
import com.kota.pm.domain.transakcije.StanjeId;
import com.kota.pm.domain.transakcije.Transakcija;

/**
 * Spring component - jsf managed bean
 * for kartice.xhtml page
 * 
 * @author kotarac
 *
 */
@Component("kontrolerKartica")
public class KarticeMB  implements Serializable{


	private static final long serialVersionUID = 1L;

	@Autowired
	private KontrolerProizvodnihPodataka kpp;
	
	@Autowired
	private KontrolerPermanentnihPodataka kPerPod;
	
	@Autowired
	private KontrolerProizvodnihTransakcionihPodataka kptp;
	
	private Transakcija transakcija;
	private Kartica kartica;
	private Date datum;
	private Proizvod proizvod;
	private Dokument dokument;
	private Pogon pogon;
	private Hashtable<StanjeId, Float> popis;
	private Normativ normativ;
	private Stanje stanje;
	private List<Transakcija> listaTransakcija;
	private float kolicinaNaPopisu;

	
	@PostConstruct
	public void init() {
		transakcija = new Transakcija();
		datum = Calendar.getInstance().getTime();
		transakcija.setDatum(datum);
		normativ = null;
		popis = new Hashtable<>();
		stanje = new Stanje();
		listaTransakcija = new ArrayList<Transakcija>();
	}
	
	/**
	 * Snima transakciju i prateću promenu
	 */
	public void snimiTransakciju(){
		dokument = transakcija.getDokument();
		pogon = transakcija.getPogon();
		
		kptp.snimiTransakciju(transakcija, normativ);
		listaTransakcija.add(transakcija);
		
		transakcija = new Transakcija();
		transakcija.setDatum(datum);
		transakcija.setPogon(pogon);
		transakcija.setDokument(dokument);
		normativ= null;
	}
	/*
	 *Snima stanje po popisu proizvoda na zadati datum u hashtable 
	 */
	public void snimiStanje() {
		StanjeId sId = new StanjeId();
		sId.setDatum(datum);
		sId.setPogon(pogon);
		sId.setProizvod(proizvod);
		
		popis.put(sId, kolicinaNaPopisu);
		kolicinaNaPopisu = 0.0f;
	}
	
	/*
	 * Snima has tabelu u bazu podataka i azurira datume
	 */
	public void snimiPopis() {
		kptp.unesiPopis(popis);
		//popis = new Hashtable<>();
		
	}
	
	public boolean proveriNormativ() {
		if(proizvod == null || proizvod.getNormativi() == null || proizvod.getNormativi().size()<1)
			return false;
		return true;
	}
	
	public List<Proizvod> traziProizvode(String query) {
		return kpp.findProizvodById(query);
	}
	
	public List<Dokument> getListaDokumenata(){
		return kPerPod.getVrsteDokumenata();
	}
	public List<Pogon> getListaPogona() {
		return kPerPod.getPogoni();
	}
	/////getters and setters
	
	public Transakcija getTransakcija() {
		return transakcija;
	}

	public void setTransakcija(Transakcija transakcija) {
		this.transakcija = transakcija;
	}

	public Kartica getKartica() {
		return kartica;
	}

	public void setKartica(Kartica kartica) {
		this.kartica = kartica;
	}

	public Pogon getPogon() {
		return pogon;
	}

	public void setPogon(Pogon pogon) {
		this.pogon = pogon;
	}

	public Hashtable<StanjeId, Float> getPopis() {
		return popis;
	}

	public void setPopis(Hashtable<StanjeId, Float> popis) {
		this.popis = popis;
	}

	public Normativ getNormativ() {
		return normativ;
	}

	public void setNormativ(Normativ normativ) {
		this.normativ = normativ;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public Dokument getDokument() {
		return dokument;
	}

	public void setDokument(Dokument dokument) {
		this.dokument = dokument;
	}

	public List<Transakcija> getListaTransakcija() {
		return listaTransakcija;
	}

	public void setListaTransakcija(List<Transakcija> listaTransakcija) {
		this.listaTransakcija = listaTransakcija;
	}

	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	public Float getKolicinaNaPopisu() {
		return kolicinaNaPopisu;
	}

	public void setKolicinaNaPopisu(Float kolicinaNaPopisu) {
		this.kolicinaNaPopisu = kolicinaNaPopisu;
	}

	
}
