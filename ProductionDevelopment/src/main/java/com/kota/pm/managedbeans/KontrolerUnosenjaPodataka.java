package com.kota.pm.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPermanentnihPodataka;
import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.datatype.Poslodavac;
import com.kota.pm.domain.datatype.Status;
import com.kota.pm.domain.datatype.TrenutniRadniStatusZaposlenog;
import com.kota.pm.domain.datatype.VrstaAngazmana;
import com.kota.pm.domain.zaposleni.Angazman;
import com.kota.pm.domain.zaposleni.Employee;
import com.kota.pm.domain.zaposleni.RadnoMesto;
import com.kota.pm.domain.zaposleni.Raspolozivost;

@Component("kontrolerUnosenjaPodataka")
@Scope("request")
public class KontrolerUnosenjaPodataka implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	transient private KontrolerPodatakaRadnika kpr;
	
	@Autowired
	private KontrolerPermanentnihPodataka kpp;
	
	private Employee zaposleni;
	private Employee noviZaposleni;
	private List<Status> statusi;
	private List<VrstaAngazmana> vrsteAngazmana;
	private String ime;
	private List<Poslodavac> poslodavci;
	private List<RadnoMesto> radnaMesta;
	private List<Employee> listaZaposlenih;
	private RadnoMesto radnoMesto;
	private Poslodavac poslodavac;
	private Angazman angazman;
	private String tt;
	private Raspolozivost raspolozivost;

	@PostConstruct
	private void init(){
		noviZaposleni = new Employee();
		angazman = new Angazman();//noviZaposleni.setAngazman(new Angazman());
		statusi = Arrays.asList(Status.values());
		vrsteAngazmana = Arrays.asList(VrstaAngazmana.values());
		System.out.println("stausi->"+statusi + "KPR__>>"+kpr.getText());
		poslodavci = kpr.getAllPoslodavac();//new ArrayList<>();//poslodavacRepo.findAll();
		radnaMesta = kpr.getAllRadnoMesto();//new ArrayList<>();// radnoMestoRepo.findAll();
		radnoMesto = new RadnoMesto();
		poslodavac = new Poslodavac();
		raspolozivost = new Raspolozivost();
		//listaZaposlenih = 
	}
/////////////////////HELPER//////////////////////////
	public List<Poslodavac> getPoslodavci(){
		return poslodavci;
	}
	public List<RadnoMesto> getRadnaMesta() {
		return radnaMesta;
	}
	
	public void snimiNovogZaposlenog() {
		//noviZaposleni.getAngazman().setZaposleni(noviZaposleni);
		System.out.println("poslodavac"+angazman.getPoslodavac());
//		System.out.println("poslodavac:: "+angazman.getPoslodavac().getNazivPoslodavca());
//		System.out.println("datum:: "+angazman);
//		System.out.println("datum:: "+angazman.getDatumPocetka());
		//System.out.println("radnomesto"+angazman.getRadnoMesto());
		//kpr.saveAngazman(angazman);
		noviZaposleni.setAngazman(new ArrayList<Angazman>());
		angazman.setEmployee(noviZaposleni);
		noviZaposleni.getAngazman().add(angazman);
		kpr.saveAngazman(angazman);
		kpr.saveEmployee(noviZaposleni); 
		System.out.println("poslodavac - ali posle"+angazman);
/*		//FacesContext fc = FacesContext.getCurrentInstance();
		//fc.addMessage("msg", new FacesMessage("Radnik je snimljen"));
		 */
		noviZaposleni = new Employee();
		angazman = new Angazman();
	//	angazman = new Angazman(); */
		
	}
	
	public List<Employee> autocompleteEmployee(String query) {
		List<Employee> e = kpr.listaZaposlenih(query);
		return e;
	}

	public void snimiRadnoMesto() {
		//kpr.sinimiRadnoMesto(radnoMesto);
		//radnoMesto = new RadnoMesto();
		if(kpr != null)
			kpr.sinimiRadnoMesto(radnoMesto);
		else
			System.out.println("NULLA JE");
	}
	public void snimiPoslodavca() {
		//kpr.sinimiRadnoMesto(radnoMesto);
		//radnoMesto = new RadnoMesto();
		if(kpr != null)
			kpr.savePoslodavac(poslodavac);
		else
			System.out.println("NULLA JE");
	}
	
	public List<Pogon> getPogoni(){
		return kpp.getPogoni();
	}
	
	public List<TrenutniRadniStatusZaposlenog> getStatusZaposlenog() {
		return kpp.getTrenutniRadniStatusZaposlenog();
	}
	
	public void snimiRaspolozivost() {
		kpr.saveRaspolozivost(raspolozivost);
		raspolozivost = new Raspolozivost();
	}
////////////////////GETTER AND SETTER////////////////
	public Employee getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Employee zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Employee getNoviZaposleni() {
		return noviZaposleni;
	}

	public void setNoviZaposleni(Employee noviZaposleni) {
		this.noviZaposleni = noviZaposleni;
	}
	public List<Status> getStatusi() {
		return statusi;
	}
	public List<VrstaAngazmana> getVrsteAngazmana() {
		return vrsteAngazmana;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public RadnoMesto getRadnoMesto() {
		return radnoMesto;
	}
	public void setRadnoMesto(RadnoMesto radnoMesto) {
		this.radnoMesto = radnoMesto;
	}
	public Poslodavac getPoslodavac() {
		return poslodavac;
	}
	public void setPoslodavac(Poslodavac poslodavac) {
		this.poslodavac = poslodavac;
	}
	public Angazman getAngazman() {
		return angazman;
	}
	public void setAngazman(Angazman angazman) {
		this.angazman = angazman;
	}
	public String getTt() {
		return tt;
	}
	public void setTt(String tt) {
		this.tt = tt;
	}
	
	public void test() {
		System.out.println("OVO JE TEST!");
	}
	public Raspolozivost getRaspolozivost() {
		return raspolozivost;
	}
	public void setRaspolozivost(Raspolozivost raspolozivost) {
		this.raspolozivost = raspolozivost;
	}

	
	
}
