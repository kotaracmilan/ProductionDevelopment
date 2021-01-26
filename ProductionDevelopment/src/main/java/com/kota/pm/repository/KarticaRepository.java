package com.kota.pm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.product.Proizvod;
import com.kota.pm.domain.transakcije.Kartica;

public interface KarticaRepository extends CrudRepository<Kartica, Long> {

	public Optional<Kartica> findByProizvod(Proizvod p);
	
	public Optional<Kartica> findByProizvodAndPogon(Proizvod proizvod, Pogon pogon);
}
