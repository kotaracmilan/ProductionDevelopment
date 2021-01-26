package com.kota.pm.repository;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.transakcije.Transakcija;

public interface TransakcijaRepository extends CrudRepository<Transakcija, Long> {

}
