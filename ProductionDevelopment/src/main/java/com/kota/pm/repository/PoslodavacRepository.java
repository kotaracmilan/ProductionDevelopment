package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Poslodavac;

public interface PoslodavacRepository extends CrudRepository<Poslodavac, Integer> {

	public List<Poslodavac> findAll();
}
