package com.kota.pm.domain.zaposleni;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;

import lombok.Data;

/**
 * Podaci o zaposlenima
 * @author kotarac
 *
 */
@Entity
@Data
public class Employee implements Serializable{
	
	private static final long serialVersionUID = -7236012829237632675L;
	
	@Id
	@GeneratedValue
	private int id;
	private String ime;
	private String prezime;
	private String srednjeIme;
	private long jmbg;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Angazman> angazman;
	@OneToOne
	@Null
	private Raspolozivost trenutniStatus;
	

	
}
