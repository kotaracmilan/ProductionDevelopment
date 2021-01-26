package com.kota.pm.domain.datatype;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.kota.pm.domain.transakcije.Stanje;
import com.kota.pm.domain.zaposleni.Employee;

import lombok.Data;

@Entity
@Data
public class Pogon implements Serializable {

	private static final long serialVersionUID = 9168358412837243952L;

	@Id
	@GeneratedValue
	private int id;
	@NotNull(message="Naziv pogona mora biti definisan")
	private String pogon;
	@OneToOne
	private Employee rukovodilac;
	@OneToMany
	private List<Stanje> stanje;
	

}
