package com.kota.pm.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.zaposleni.Employee;
import com.kota.pm.domain.zaposleni.Poslovi;
import com.kota.pm.domain.zaposleni.Raspored;
import com.kota.pm.domain.zaposleni.RasporedRadnika;
import com.kota.pm.domain.zaposleni.proizvodnja.Karnet;
import com.kota.pm.repository.EmployeeRepository;
import com.kota.pm.repository.KarnetRepository;
import com.kota.pm.repository.RasporedRadnikaRepository;
import com.kota.pm.repository.RasporedRepository;

@RestController
@RequestMapping("/controller/raspored")
public class RasporedRest {

	@Autowired
	private RasporedRepository rasporedRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired 
	private RasporedRadnikaRepository rasporedRadnikaRepo;
	
	@Autowired
	private KarnetRepository karnetRepo;
	
	
	@RequestMapping("/createRaspored")
	public Raspored createRaspored(@RequestBody Date datum, Pogon pogon) {
		Raspored r = new Raspored();
		r.setDatum(datum);
		r.setPogon(pogon);
		r = rasporedRepo.save(r);
		return r;
	}
	
	@RequestMapping("/updateRaspored")
	public Raspored updateRaspored(@RequestBody Raspored raspored) {
		return rasporedRepo.save(raspored);
	}
	
	@GetMapping("/getRaspored/{id}")
	public Raspored getRaspored(@PathVariable long id) {
		Optional<Raspored> r = rasporedRepo.findById(id);
		return r.isPresent() ? r.get() : null;
	}
	
	@GetMapping("/getRasporedByDate")
	public List<Raspored> getRasporedByDate(@RequestBody Date datum) {
		List<Raspored> r = rasporedRepo.getRasporedByDatum(datum);
		return r.isEmpty() ? null : r;
	}
	
	@GetMapping("/createRaspored/{id}")
	public Raspored copyRasporedForDate(@RequestBody Date datum, @PathVariable("id") long id) {
		return createNewRasporedForDiferentDate(id, datum);
	}
	
	@GetMapping("/dodajRadnikeNaRaspored/{id}")
	public Raspored dodajRadnikeNaRaspored(@PathVariable("id") long id, List<RasporedRadnika> rasporedRadnika) {
		Raspored r = getById(id);
		if(r != null)
			r.getRadniciNaRasporedu().addAll(rasporedRadnika);
		return rasporedRepo.save(r);
	}
	
	/**
	 * Kreira raspored radnika od radnika koji se nalaze na karnetu da su radili za određeni datum
	 * @param zaDatum	-	datum za koji treba napraviti novi raspored	
	 * @param id		-	id rasporeda
	 * @return 			- vraća novonapravljeni raspored
	 */
	@GetMapping("/napraviRasporedOdKarneta/{idRasporeda}")
	public Raspored napraviRasporedOdKarneta(@RequestBody Date zaDatum, @PathVariable("id") long id) {
		Raspored noviRaspored = createNewRasporedForDiferentDateWithoutRadnici(id, zaDatum);
		
		if(null == noviRaspored)
			return null;
		
		ArrayList<RasporedRadnika> rr = new ArrayList<>();
		rasporedRepo.findById(id).get().getRadniciNaKarnetu().stream()
			.forEach(
					karnet -> {
						if(karnet.getRadnihSati() == 0 )
							return;
						Employee e = karnet.getZaposleni();
						if(e.getTrenutniStatus().getTrenutniRadniStatusZaspolenog().getId() != 0 || e.getTrenutniStatus().getKrajStatusa().after(zaDatum))
							return;
						Poslovi p = karnet.getObavljeniPoslovi().get(0).getObavljeniPosao();
						RasporedRadnika rasRad = new RasporedRadnika();
						rasRad.setRadnik(e);
						rasRad.setRaspored(noviRaspored);
						rasRad.setPoslovi(p);
						rr.add(rasRad);
					}
					);
		noviRaspored.setRadniciNaRasporedu(rr);
		return rasporedRepo.save(noviRaspored);
	}
	
	
	////////////////////////////////////////////////////////////////////
	///////////////HELPER METHODS///////////////////////////////////////
	////////////////////////////////////////////////////////////////////
	
	private Raspored createNewRasporedForDiferentDate(long id, Date zaDatum) {
		Raspored stari = getById(id);
		if(stari == null)
			return null;
		Raspored novi = new Raspored();
		novi.setPogon(stari.getPogon());
		novi.setPoslovodja(stari.getPoslovodja());
		novi.setRadniciNaRasporedu(stari.getRadniciNaRasporedu());
		novi.setDatum(zaDatum);
		novi.setSmena(stari.getSmena());
		novi = rasporedRepo.save(novi);
		return novi;
	}
	
	private Raspored createNewRasporedForDiferentDateWithoutRadnici(long id, Date zaDatum) {
		Raspored stari = getById(id);
		if(stari == null)
			return null;
		Raspored novi = new Raspored();
		novi.setPogon(stari.getPogon());
		novi.setPoslovodja(stari.getPoslovodja());
		novi.setDatum(zaDatum);
		novi.setSmena(stari.getSmena());
		novi = rasporedRepo.save(novi);
		return novi;
	}
	
	private Raspored getById(long id) {
		Optional<Raspored> o = rasporedRepo.findById(id);
		if(!o.isPresent())
			return null;
		return o.get();
	}
	

}
