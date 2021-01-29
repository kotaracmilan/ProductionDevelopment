package com.kota.pm.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.datatype.Poslodavac;
import com.kota.pm.domain.datatype.TrenutniRadniStatusZaposlenog;
import com.kota.pm.domain.proizvodnja.Linija;
import com.kota.pm.domain.zaposleni.Angazman;
import com.kota.pm.domain.zaposleni.Employee;
import com.kota.pm.domain.zaposleni.Poslovi;
import com.kota.pm.domain.zaposleni.RadnoMesto;
import com.kota.pm.domain.zaposleni.Raspolozivost;
import com.kota.pm.domain.zaposleni.Raspored;
import com.kota.pm.repository.AngazmanRepository;
import com.kota.pm.repository.EmployeeRepository;
import com.kota.pm.repository.LinijaRepository;
import com.kota.pm.repository.PogonRepository;
import com.kota.pm.repository.PoslodavacRepository;
import com.kota.pm.repository.PosloviRepository;
import com.kota.pm.repository.RadnoMestoRepository;
import com.kota.pm.repository.RaspolozivostRepository;
import com.kota.pm.repository.RasporedRepository;

@Service
public class KontrolerPodatakaRadnika {
	

	@Autowired
	private KontrolerPermanentnihPodataka kpp;

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private PoslodavacRepository poslodavacRepo;
	@Autowired
	private RadnoMestoRepository radnoMestoRepo;
	@Autowired
	private AngazmanRepository angazmanRepo;
	@Autowired
	private PogonRepository pogonRepo;
	@Autowired 
	private PosloviRepository posloviRepo;
	@Autowired 
	private RasporedRepository rasporedRepo;
	@Autowired
	private LinijaRepository linijaRepo;
	@Autowired
	private RaspolozivostRepository raspolozivostRepo;

	@PostConstruct
	public void init() {

	}
	
	///------------------------------------------------------------------
	public Raspored findRasporedOnDate(Date datum, int smena, Pogon pogon) {
		return rasporedRepo.findByDatumAndPogonAndSmena(datum, pogon, smena);
	}
	
	public List<Poslodavac> getAllPoslodavac(){
		return poslodavacRepo.findAll();
	}
	public List<RadnoMesto> getAllRadnoMesto(){
		return radnoMestoRepo.findAll();
	}
	
	public Employee saveEmployee(Employee e) {
		return employeeRepo.save(e);
	}
	public Poslodavac savePoslodavac(Poslodavac p) {
		return poslodavacRepo.save(p);
	}
	
	public String getText() {
		return"radi";
	}
	
	public List<Employee> listaZaposlenih() {
		return employeeRepo.findAll();
	}
	public List<Employee> listaZaposlenih(Pageable p) {
		return employeeRepo.findAll(p).getContent();
	}
	public List<Employee> listaZaposlenih(String q) {
		Pageable p = PageRequest.of(0, 10);
		return employeeRepo.findForAutocomplete(q, p);
	}
	
	public void saveRaspolozivost(Raspolozivost r) {
		raspolozivostRepo.save(r);
	}
	
	public long getEmployeeCount() {
		return employeeRepo.count();
	}
	
	public void sinimiRadnoMesto(RadnoMesto rm) {
		radnoMestoRepo.save(rm);
	}
	
	public void test() {
		System.out.println(radnoMestoRepo);
	}
	
	public void saveAngazman(Angazman a) {
		angazmanRepo.save(a);
	}
	public Poslodavac findPoslodavacById(int i) {  
		Poslodavac p = poslodavacRepo.findById(i).get();
		return p;
	}
	public RadnoMesto findRadnoMestoById(int i) {  
		RadnoMesto p = radnoMestoRepo.findById(i).get();
		return p;
	}
	
	public List<Employee> getRaspoloziviRadnici(Pogon p) {
		return employeeRepo.findByTrenutniStatusTrenutniRadniStatusZaspolenogAndTrenutniStatusPogon(TrenutniRadniStatusZaposlenog.RASPOLOZIV, p);
	}
	
	public Pogon findPogonById(int id) {
		return kpp.findPogonById(id);
	}
	
	public Poslovi findPosloviById(Integer id) {
		Optional<Poslovi> po = posloviRepo.findById(id);
		if(po.isPresent())
			return po.get();
		return null;
	}

	
	public List<Poslovi> getListaPoslova(Pogon p) {
		return posloviRepo.findPosloviByPogon(p);
	}
	
	public Linija getLinijaFromId(int id) {
		Optional<Linija> op = linijaRepo.findById(id);
		if(op.isPresent())
			return op.get();
		return null;
	}
	
	public Employee getRadnikFromId(Integer id) {
		Optional<Employee> e = employeeRepo.findById(id);
		if(e.isPresent())
			return e.get();
		return null;
	}
	
	public List<Employee> getZaposleniByRadnoMesto(String naziv) {
		List<Employee> e = employeeRepo.findByAngazmanRadnoMestoNazivRadnogMesta(naziv);
		if(!e.isEmpty())
			return e;
		return null;
	}
	
	public Employee getZaposleniByNameAndSurname(String name, String surname) {
		Optional<Employee> e = employeeRepo.findByImeAndPrezime(name, surname);
		if(e.isPresent())
			return e.get();
		return null;
	}
}
