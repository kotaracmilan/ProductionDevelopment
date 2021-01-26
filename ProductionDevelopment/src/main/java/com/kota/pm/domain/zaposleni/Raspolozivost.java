package com.kota.pm.domain.zaposleni;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.datatype.TrenutniRadniStatusZaposlenog;

import lombok.Data;

@Entity
@Data
public class Raspolozivost implements Serializable{

	private static final long serialVersionUID = 7728261595770835383L;

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Employee employee;
	@Enumerated(EnumType.STRING)
	private TrenutniRadniStatusZaposlenog trenutniRadniStatusZaspolenog;
	private Date pocetakStatusa;
	private Date krajStatusa;
	@OneToOne
	private Pogon pogon;
		
}
