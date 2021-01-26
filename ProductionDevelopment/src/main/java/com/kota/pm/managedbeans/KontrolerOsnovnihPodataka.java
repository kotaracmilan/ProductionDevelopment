package com.kota.pm.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPermanentnihPodataka;
import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.datatype.Dokument;
import com.kota.pm.domain.datatype.Operacija;
import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.zaposleni.Employee;

@Component("kop")
public class KontrolerOsnovnihPodataka {

	
	@Autowired
	private KontrolerPermanentnihPodataka kpp;
	
	@Autowired
	private KontrolerPodatakaRadnika kpr;
	
	
	private String naziv;
	private Employee rukovodilac;
	private Dokument dokument;
	
	
	@PostConstruct
	public void init() {
		dokument = new Dokument();
	}
	/////methods
	
	public void snimiPogon() {
		Pogon pogon = new Pogon();
		pogon.setPogon(naziv);
		pogon.setRukovodilac(rukovodilac);
		kpp.savePogon(pogon);
	}

	public List<Dokument> getVrsteDokumenata() {
		return kpp.getVrsteDokumenata();
	}

	public void saveDokument() {
		kpp.saveDokument(dokument);
	}
	//////////////getter setter
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Employee getRukovodilac() {
		return rukovodilac;
	}

	public void setRukovodilac(Employee rukovodilac) {
		this.rukovodilac = rukovodilac;
	}
	
	public List<Employee> getZaposleniNaRadnomMestuRukovodioca() {
		return kpr.getZaposleniByRadnoMesto("rukovodilac");
	}
	public List<Operacija> getListaOperacija() {
		return kpp.getListaOperacija();
	}

	public Dokument getDokument() {
		return dokument;
	}

	public void setDokument(Dokument dokument) {
		this.dokument = dokument;
	}
	
	
	
}
