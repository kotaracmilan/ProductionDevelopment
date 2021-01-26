package com.kota.pm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kota.pm.domain.product.Normativ;
import com.kota.pm.domain.product.NormativnaSastavnica;
import com.kota.pm.domain.product.Proizvod;
import com.kota.pm.repository.NormativRepository;
import com.kota.pm.repository.NormativnaSastavnicaRepository;
import com.kota.pm.repository.ProizvodRepository;

@Service
public class KontrolerProizvodnihPodataka {

	@Autowired
	private ProizvodRepository proizvodRepo;
	@Autowired
	private NormativRepository normativRepo;
	@Autowired
	private NormativnaSastavnicaRepository normativnaSastavnicaRepo;
	@Autowired
	private CsvLoader csvLoader;
	
	public Proizvod getProizvodById(long id) {
		Optional<Proizvod> pr = proizvodRepo.findById(id);
		if(pr.isPresent())
			return pr.get();
		return null;
	}
	
	public List<Proizvod> findProizvodById(String query) {
		Long id = Long.valueOf(query);
		Pageable p = new PageRequest(0,10);
		return proizvodRepo.findAllByPartId(id, p);
		
	}
	
	public Proizvod saveProizvod(Proizvod  p) {
		return proizvodRepo.save(p);
	}
	
	public Optional<Proizvod> findProizvodById(long id){
		return proizvodRepo.findById(id);
	}
	public Normativ findNormativByProizvodAndOznakaNormativa(Proizvod p, int o) {
		List<Normativ> n = normativRepo.findAllByProizvodAndOznakaNormativa(p, o);
		if(n.isEmpty())
			return null;
		return n.get(0);
	}
	
	public Normativ findNormativById(long id) {
		Optional<Normativ> n = normativRepo.findById(id);
		if(n.isPresent())
			return n.get();
		return null;
	}
	
	public List<Proizvod> ucitajProizvodIzFajla(File f) throws ClassNotFoundException, FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		return csvLoader.loadFromCsv(f, Proizvod.class);
	}
	
	public void saveNormativ(Normativ n) {
		normativRepo.save(n);
	}
	
	public List<Proizvod> findAllProizvod(Pageable p) {
		return proizvodRepo.findAll(p).getContent();
	}
	
	public long countProizvod() {
		return proizvodRepo.count();
	}
	
	public List<Normativ> findNormativByProizvod(Proizvod p) {
		return normativRepo.findAllByProizvod(p);
	}
	public void obrisiNormativ(Normativ n) {
		normativRepo.delete(n);
	}
	
	public List<NormativnaSastavnica> findNormativnaSastavnicaByNormativ(Normativ n) {
		return normativnaSastavnicaRepo.findAllByNormativ(n);
	}
}

