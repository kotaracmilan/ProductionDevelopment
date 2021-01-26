package com.kota.pm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Dokument;

public interface DokumentRepository extends CrudRepository<Dokument, Integer> {

	public List<Dokument> findAll();
}
