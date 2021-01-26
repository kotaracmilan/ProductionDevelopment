package com.kota.pm.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.product.Proizvod;
import com.kota.pm.domain.transakcije.Stanje;
import com.kota.pm.domain.transakcije.StanjeId;

public interface StanjeRepository extends CrudRepository<Stanje, StanjeId> {

	public Optional<Stanje> findByIdProizvodAndIdPogonOrderByIdDatumDesc(Proizvod proizvod, Pogon pogon);
	
	public Optional<Stanje> findByIdProizvodAndIdDatumAndIdPogon(Proizvod proizvod, Date datum, Pogon pogon);
	
	public Optional<Stanje> findFirstByIdProizvodAndIdPogonAndIdDatumBeforeOrderByIdDatumDesc(Proizvod proizvod, Pogon pogon, Date datum);
	
	public List<Stanje> findByIdProizvodAndIdPogonAndIdDatumAfterOrderByIdDatumDesc(Proizvod proizvod, Pogon pogon, Date datum);
}
