package com.kota.pm.domain.zaposleni;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.zaposleni.proizvodnja.Karnet;

import lombok.Data;

@Entity
@Data
public class Raspored implements Serializable{
	

	private static final long serialVersionUID = -1078079347279549774L;

	@Id
	@GeneratedValue
	private long id;
	private Pogon pogon;
	private Employee poslovodja;
	private Date datum;
	private int smena;
	@OneToMany(fetch = FetchType.LAZY)
	private List<RasporedRadnika> radniciNaRasporedu;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Karnet> radniciNaKarnetu;
	
}
