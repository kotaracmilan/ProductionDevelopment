package com.kota.pm.domain.zaposleni;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.kota.pm.domain.datatype.Poslodavac;
import com.kota.pm.domain.datatype.Status;
import com.kota.pm.domain.datatype.VrstaAngazmana;

import lombok.Data;

/**
 * Način na koji je radnik u radnom odnosu sa poslodavcem
 * @author kotarac
 *
 */

@Entity
@Data
public class Angazman implements Serializable{

	private static final long serialVersionUID = 8378119925695853651L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	private Date datumPocetka;
	private Date datumZavrsetka;
	@OneToOne
	private Poslodavac poslodavac;
	@OneToOne
	private RadnoMesto radnoMesto;
	@ManyToOne(cascade=CascadeType.ALL)
	private Employee employee;
	@Enumerated(EnumType.ORDINAL)
	private VrstaAngazmana vrstaAngazmana;
	
	

}
