package com.kota.pm.domain.zaposleni.proizvodnja;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.datatype.User;
import com.kota.pm.domain.zaposleni.Employee;
import com.kota.pm.domain.zaposleni.Raspored;

import lombok.Data;

/**
 * Karnet evidencije radnog vremena
 * @author kotarac
 *
 */

@Entity
@Data
public class Karnet implements Serializable {

	private static final long serialVersionUID = -8126812957989187364L;

	
	@Id
	@GeneratedValue
	private long id;
	@Max(value=11)
	private int radnihSati;
	private Employee zaposleni;
	private Date datum;
	private Pogon pogon;
	private Raspored smena;
	private User odgovornoLice;
	@OneToMany
	private List<ObavljeniPoslovi> obavljeniPoslovi;
		
}
