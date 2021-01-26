package com.kota.pm.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPermanentnihPodataka;
import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.proizvodnja.Linija;
import com.kota.pm.domain.zaposleni.Employee;
import com.kota.pm.domain.zaposleni.Poslovi;
import com.kota.pm.domain.zaposleni.Raspored;
import com.kota.pm.domain.zaposleni.RasporedRadnika;


@Component("kontrolerRasporeda")
@Scope("view") //prebaciti na view
public class KontrolerRasporeda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	transient private KontrolerPodatakaRadnika kpr;
	
	@Autowired
	transient private KontrolerPermanentnihPodataka kpp;
	
	private List<Employee> raspoloziviRadnici;
	private List<Poslovi> listaPoslova;
	private Date datumRasporeda;
	private Pogon pogon;
	private int smena;
	private Raspored raspored;
	private Poslovi poslovi;
	private Linija linija;
	private List<Employee> rasporedjeni;
	
	@PostConstruct
	private void init() {
		raspored = new Raspored();
		//raspored.setRadniciNaRasporedu(new ArrayList<RasporedRadnika>());
		raspoloziviRadnici = kpr.listaZaposlenih();
		rasporedjeni = new ArrayList<Employee>();
		
	}

	/////////----------------methods--------------------
	
	public void ucitajRasporedNaDan() {
		if(pogon == null || smena != -1 || datumRasporeda == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("msg", new FacesMessage("Morate izabrati pogon, datum id smenu!"));
		}
		raspored = kpr.findRasporedOnDate(datumRasporeda, smena, pogon);
	}

	public void onRadnikSelected(DragDropEvent dde) {
		/*if(pogon == null || datumRasporeda==null || smena == -1) {
			sendMessage("Pogon, smena id datum moraju biti unešeni!");
			return;
		}*/
		Employee e = (Employee)dde.getData();
		//System.out.println(e);
		RasporedRadnika rr = new RasporedRadnika();
		rr.setRadnik(e);
		//rr.setPoslovi(poslovi);
		rr.setRaspored(raspored);
		//rr.setLinija(linija);
		//dodati id poslovođu
		if(raspored.getRadniciNaRasporedu() == null)
			raspored.setRadniciNaRasporedu(new ArrayList<>());
		
		raspored.getRadniciNaRasporedu().add(rr);
		raspoloziviRadnici.remove(e);
		System.out.println(raspoloziviRadnici.size());
	}
	public void testClick(DragDropEvent dde) {
		Employee e = (Employee)dde.getData();
		raspoloziviRadnici.remove(e);
		rasporedjeni.add(e);
	}
	public void onPogonChanged() {
		raspoloziviRadnici=kpr.getRaspoloziviRadnici(pogon);
		//listaPoslova = kpr.getListaPoslova(pogon);
	}

	public Pogon getPogon() {
		return pogon;
	}

	public void setPogon(Pogon pogon) {
		if(pogon != null && !pogon.equals(this.pogon))
			raspoloziviRadnici = kpr.getRaspoloziviRadnici(pogon);
		this.pogon = pogon;
	}

	public List<Employee> getRaspoloziviRadnici(){
		return raspoloziviRadnici;
	}
	
	public void setRaspoloziviRadnici(List<Employee> radnici) {
		this.raspoloziviRadnici = radnici;
	}

	public List<RasporedRadnika> getRadniciNaRasporedu() {
		return raspored.getRadniciNaRasporedu();
	}

	public void setRadniciNaRasporedu(List<RasporedRadnika> radniciNaRasporedu) {
		this.raspored.setRadniciNaRasporedu(radniciNaRasporedu);
	}

	public Date getDatumRasporeda() {
		return datumRasporeda;
	}

	public void setDatumRasporeda(Date datumRasporeda) {
		this.datumRasporeda = datumRasporeda;
	}
	
	public List<Pogon> getPogoni(){
		return kpp.getPogoni();
	}
	public int getSmena() {
		return smena;
	}
	public void setSmena(int smena) {
		this.smena = smena;
	}
	public Raspored getRaspored() {
		return raspored;
	}
	public void setRaspored(Raspored raspored) {
		this.raspored = raspored;
	}

	public Poslovi getPoslovi() {
		return poslovi;
	}

	public void setPoslovi(Poslovi poslovi) {
		this.poslovi = poslovi;
	}
	
	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
	}

	public List<Poslovi> getListaPoslova() {
		if(pogon == null)
			return null;
		if(listaPoslova == null)
			listaPoslova = kpr.getListaPoslova(pogon);
		return listaPoslova; 
	}
	
	public List<Linija> getLinije(){
		if(pogon == null)
			return null;
		return kpp.getLinije(pogon);
	}

	public List<Employee> getRasporedjeni() {
		return rasporedjeni;
	}

	public void setRasporedjeni(List<Employee> rasporedjeni) {
		this.rasporedjeni = rasporedjeni;
	}

	/////////////////////////////////////////////////////
	/////////////////helper methods/////////////////////
	private void sendMessage(String message) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("msg_raspored", new FacesMessage("message"));
	}
}
