package com.kota.pm.domain.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.kota.pm.domain.datatype.JedinicaMere;

import lombok.Data;

@Entity
@Data
public class NormativnaSastavnica implements Serializable {

	private static final long serialVersionUID = 627873419572459310L;

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne @NotNull(message="Normativ mora bit odabran")
	private Normativ normativ;
	@OneToOne
	private Proizvod komponenta;
	@Enumerated(EnumType.STRING)
	private JedinicaMere jedinicaMere;
	@NotNull(message="Udeo mora biti definisan")
	private float udeo;
	
}
