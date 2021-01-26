package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Pogon;

public interface PogonRepository extends CrudRepository<Pogon, Integer>{

	public List<Pogon> findAll();
}
