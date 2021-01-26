package com.kota.pm.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.datatype.TrenutniRadniStatusZaposlenog;
import com.kota.pm.domain.zaposleni.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	public List<Employee> findAll();
	
	public Page<Employee> findAll(Pageable p);
	
	@Query("SELECT s FROM Employee s WHERE s.ime LIKE %:tekst% OR s.prezime LIKE %:tekst%")
	public List<Employee> findForAutocomplete(@Param("tekst") String tekst, Pageable p);
	
	@Query("SELECT COUNT(u) FROM Employee u")
	public Long getEmployeeNumber();	
	
	/**
	 * Izmeniti tako da se napravi INNER JOIN LEFT
	 * @param p
	 * @return
	 */
	//@Query("SELECT e FROM Employee e WHERE INNER JOIN ")
	public List<Employee> findByTrenutniStatusTrenutniRadniStatusZaspolenogAndTrenutniStatusPogon(TrenutniRadniStatusZaposlenog trenutniRadniStatusZaspolenog, Pogon pogon);
	
	public List<Employee> findByAngazmanRadnoMestoNazivRadnogMesta(String nazivRadnogMesta);
	
	public Optional<Employee> findByImeAndPrezime(String ime, String prezime);
}
