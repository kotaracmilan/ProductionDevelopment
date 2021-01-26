package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.zaposleni.RadnoMesto;

public interface RadnoMestoRepository extends CrudRepository<RadnoMesto, Integer> {

	public List<RadnoMesto> findAll();
}
