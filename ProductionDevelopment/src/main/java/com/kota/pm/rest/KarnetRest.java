package com.kota.pm.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kota.pm.domain.datatype.TrenutniRadniStatusZaposlenog;
import com.kota.pm.domain.zaposleni.Raspored;
import com.kota.pm.domain.zaposleni.proizvodnja.Karnet;
import com.kota.pm.repository.KarnetRepository;
import com.kota.pm.repository.RasporedRepository;

@RestController
@RequestMapping("/controller/karnet")
public class KarnetRest {

	@Autowired
	private KarnetRepository karnetRepo;
	@Autowired
	private RasporedRepository rasporedRepo;
	
	/**
	 * Snima pojedinačan karent (unos radnika)
	 * @param k
	 * @return
	 */
	@PostMapping("/snimikarnet")
	@ResponseStatus(HttpStatus.OK)
	public Karnet createKarnet(@RequestBody Karnet k) {
		return karnetRepo.save(k);
	}
	
	/**
	 * Proverava da li su podaci prosledjeni tačni id ukoliko jesu snima karnete koji se nalaze u listi
	 * Svi koji nisu ispravni vraćaju se na proveru kao posebna lista
	 * @param k
	 */
	@PostMapping("/snimikarnete")
	@ResponseStatus(HttpStatus.OK)
	public List<Karnet> snimiKarnete(@RequestBody List<Karnet> k) {
		ArrayList<Karnet> neispravni = new ArrayList<>();
		k.stream().forEach(
				r -> {
					if(daLiJeIspravanKarnet(r))
						karnetRepo.save(r);
					else 
						neispravni.add(r);
				}
			);
		return neispravni;
	}
	
	/**
	 * Pravi karnet listu za sve zaposlene sa rasporeda, id vraća na potvrdu id korekcije 
	 * @param id
	 * @return
	 */
	@PostMapping("/pretvoriRasporedUKarnet/{id}")
	public List<Karnet> pretvoriRasporedUKarnet(@PathVariable("id") long id) {
		Optional<Raspored> raspored = rasporedRepo.findById(id);
		ArrayList<Karnet> karnet = new ArrayList<>();
		if(raspored.isPresent()) {
			Raspored r = raspored.get();
			 raspored.get().getRadniciNaRasporedu()
			 	.forEach(
			 			rr -> {
			 				Karnet k = new Karnet();
			 				k.setDatum(r.getDatum());
			 				//k.setOdgovornoLice(); Preuzeti odgovorno lice iz Security-a
			 				k.setPogon(r.getPogon());
			 				k.setRadnihSati(8);
			 				k.setSmena(r);
			 				k.setZaposleni(rr.getRadnik());
			 				k.setObavljeniPoslovi(new ArrayList());
			 				
			 				karnet.add(k);
			 			}
			 			);
		}
		return karnet;
	}
	
	///////////////////////////////////////////////////////////////////////
	///////////////////HELPER METODE///////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	
	/**
	 * Proverava da li su svi podaci u karnetu ispravni
	 * @param k
	 * @return
	 */
	private boolean daLiJeIspravanKarnet(Karnet k) {
		if(k.getRadnihSati() >= 11 || k.getRadnihSati() <= -11)
			return false;
		if(!k.getDatum().equals(k.getSmena().getDatum()))
			return false;
		if(k.getZaposleni().getTrenutniStatus().getTrenutniRadniStatusZaspolenog() != TrenutniRadniStatusZaposlenog.RASPOLOZIV)
			return false;
		
		return true;
	}
	
}
