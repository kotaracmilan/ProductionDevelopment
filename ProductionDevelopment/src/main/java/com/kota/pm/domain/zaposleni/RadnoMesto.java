package com.kota.pm.domain.zaposleni;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Radna mesta prema sistematizaciji
 * 
 */
@Entity
@Data
public class RadnoMesto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String nazivRadnogMesta;
	
	
}
