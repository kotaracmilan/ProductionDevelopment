package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.proizvodnja.Linija;

public interface LinijaRepository extends CrudRepository<Linija, Integer> {

	public List<Linija> findAll();
	public List<Linija> findAllByPogon(Pogon pogon);
}
