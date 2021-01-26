package com.kota.pm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.zaposleni.Employee;
import com.kota.pm.repository.EmployeeRepository;
import com.kota.pm.repository.PogonRepository;

@RestController
@RequestMapping("/controller/employee")
public class EmployeeRest {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PogonRepository pogonRepo;
	
	/**
	 * Snima prosledjenog zaposlenog
	 * @param employee
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void dadajZaposlenog(@RequestBody Employee employee) {
		employeeRepository.save(employee);
	}
	
	/**
	 * Vraća sve zaposlene
	 * @return
	 */
	@GetMapping("/listall")
	public List<Employee>listajZaposlene() {
		return employeeRepository.findAll();
	}
	
	/**
	 * Zaspoleni u definisanom broju
	 * @param početni broj
	 * @param broj zaposlenih u listi koje vraća
	 * @return
	 */
	@GetMapping("/list/{count}/{start}")
	public List<Employee> traziZaposleneOdDo(@PathVariable("start") Integer start, @PathVariable("count") Integer count) {
		Pageable pageable = new PageRequest(start, count);
		return employeeRepository.findAll(pageable).getContent();
	}
	
	
	/**
	 * Vraća listu od 5 zaposlenih čije ime id prezime sadrže zadati tekst
	 * @param tekst koji se traži
	 * @return 5 zaposlenih koji sadrže tekst
	 */
	@GetMapping("/listForAutocomplete/{tekst}")
	public List<Employee> autocompleteZaposlenog(@PathVariable("tekst") String s) {
		Pageable p = new PageRequest(0,5);
		 return employeeRepository.findForAutocomplete(s, p);
	}
	
	@GetMapping("/pogoni")
	public List<Pogon> getPogon(){
		return pogonRepo.findAll();
	}
}
