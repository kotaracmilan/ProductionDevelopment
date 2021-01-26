package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.zaposleni.Poslovi;

public interface PosloviRepository extends CrudRepository<Poslovi, Integer>{

	public List<Poslovi> findPosloviByPogon(Pogon pogon);
}
