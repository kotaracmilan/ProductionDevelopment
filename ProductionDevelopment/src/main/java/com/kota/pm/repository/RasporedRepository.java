package com.kota.pm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.zaposleni.Raspored;

public interface RasporedRepository extends CrudRepository<Raspored, Long> {

	public List<Raspored> getRasporedByDatum(Date datum);
	
	public Raspored findByDatumAndPogonAndSmena(Date datum, Pogon pogon, int smena);
}
