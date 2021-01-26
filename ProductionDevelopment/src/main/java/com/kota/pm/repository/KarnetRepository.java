package com.kota.pm.repository;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.zaposleni.proizvodnja.Karnet;

public interface KarnetRepository extends CrudRepository<Karnet, Long>{

}
