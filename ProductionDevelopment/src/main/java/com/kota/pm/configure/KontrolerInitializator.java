package com.kota.pm.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.view.dataConverters.PoslodavacConverter;
import com.kota.pm.view.dataConverters.RadnoMestoConverter;
import com.kota.pm.view.datamodels.EmployeeLazyLoadDataModel;

@Configuration
public class KontrolerInitializator {

	/*@Bean
	public KontrolerPodatakaRadnika getKontrolerUnosaRadnika() {
		return new KontrolerPodatakaRadnika();
	}*/
	/*
	@Bean
	public EmployeeLazyLoadDataModel getEmployeeLazyDataModel() {
		return new EmployeeLazyLoadDataModel();
	}
	@Bean
	public RadnoMestoConverter getRadnoMestoConverter() {
		return new RadnoMestoConverter();
	}
	@Bean PoslodavacConverter getPoslodavacConverter() {
		return new PoslodavacConverter();
	}*/
}
