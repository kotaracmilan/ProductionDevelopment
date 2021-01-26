package com.kota.pm.domain.transakcije;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.kota.pm.domain.datatype.Dokument;
import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.product.Proizvod;

import lombok.Data;

@Entity
@Data
public class Transakcija implements Serializable {


	private static final long serialVersionUID = -7020302593371288268L;

	@Id
	@GeneratedValue
	private long id;
	@OneToOne @NotNull(message="Obavezno odabrati proizvod")
	private Proizvod proizvod;
	@NotNull(message="Obavezno uneti količinu")
	private float kolicina;
	@NotNull(message="Obavezno uneti datum")
	private Date datum;
	private int brojNaloga;
	@OneToOne @NotNull(message="Obavezno uneti vrstu dokumenta")
	private Dokument dokument;
	@OneToOne @NotNull(message="Obavezno odabrati pogon")
	private Pogon pogon;
	@ManyToOne
	private Kartica kartica;
		
}
