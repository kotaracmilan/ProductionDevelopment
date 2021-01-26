package com.kota.pm.domain.datatype;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * Klasa u kojoj se navodi ko je poslodavac zaposlenom
 * @author kotarac
 *
 */

@Entity
@Data
public class Poslodavac implements Serializable {
	

	private static final long serialVersionUID = 1632328756978369955L;

	@Id
	@GeneratedValue
	private int id;
	private String nazivPoslodavca;
	


}
