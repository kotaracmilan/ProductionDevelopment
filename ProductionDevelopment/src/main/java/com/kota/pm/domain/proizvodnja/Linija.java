package com.kota.pm.domain.proizvodnja;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kota.pm.domain.datatype.Pogon;

import lombok.Data;

@Entity
@Data
public class Linija implements Serializable{

	private static final long serialVersionUID = 5005022052734289832L;

	@Id
	@GeneratedValue
	private int id;
	private String naziv;
	private Pogon pogon;

}
