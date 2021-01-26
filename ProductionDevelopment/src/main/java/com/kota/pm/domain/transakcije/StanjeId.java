package com.kota.pm.domain.transakcije;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.product.Proizvod;

import lombok.Data;

@Embeddable
@Data
public class StanjeId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message="Obavezno uneti proizvod") @ManyToOne
	private Proizvod proizvod;
	@NotNull(message="Obavezno uneti datum")
	private Date datum;
	@NotNull(message="Obavezno izabrati pogon") @ManyToOne
	private Pogon pogon;
	
	
}
