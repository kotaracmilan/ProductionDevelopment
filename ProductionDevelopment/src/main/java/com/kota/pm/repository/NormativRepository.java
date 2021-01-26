package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.product.Normativ;
import com.kota.pm.domain.product.Proizvod;

public interface NormativRepository extends CrudRepository<Normativ, Long> {

	public List<Normativ> findAllByProizvodAndOznakaNormativa(Proizvod proizvod, int oznakaNormativa);
	
	public List<Normativ> findAllByProizvod(Proizvod proizvod);
}
