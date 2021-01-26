package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kota.pm.domain.product.Proizvod;

public interface ProizvodRepository extends CrudRepository<Proizvod, Long> {

	@Query("SELECT p FROM Proizvod p WHERE id = :id")
	public List<Proizvod> findAllByPartId(@Param("id") Long id, Pageable p);
	
	public Page<Proizvod> findAll(Pageable p);
}
