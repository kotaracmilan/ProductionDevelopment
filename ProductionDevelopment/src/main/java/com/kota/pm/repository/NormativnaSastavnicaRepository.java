package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.product.Normativ;
import com.kota.pm.domain.product.NormativnaSastavnica;

public interface NormativnaSastavnicaRepository extends CrudRepository<NormativnaSastavnica, Long>{

	public List<NormativnaSastavnica> findAllByNormativ(Normativ normativ);
}
