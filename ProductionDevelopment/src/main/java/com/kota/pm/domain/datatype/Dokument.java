package com.kota.pm.domain.datatype;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

import lombok.Data;

/**
 * Definisanje vrste dokumenta
 * @author kotarac
 *
 */
@Data
@Entity
public class Dokument implements Serializable {

	private static final long serialVersionUID = -4418200977041682149L;

	@Id
	@GeneratedValue
	private int id;
	private String naziv;
	@Enumerated(EnumType.STRING)
	private Operacija operacija;
	
}
