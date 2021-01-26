package com.kota.pm.domain.transakcije;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Stanje implements Serializable {


	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StanjeId id;
	private float stanjePoTransakcijama = 0.0f;
	private float stanjePoPopisu;
	
	
}
