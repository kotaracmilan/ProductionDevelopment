package com.kota.pm.domain.zaposleni;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kota.pm.domain.proizvodnja.Linija;

import lombok.Data;

/**
 * Radnici koji su na rasporedu za određenu smenu.
 * Medjutabela koja povezuje Radika id Raposred
 * @author kotarac
 *
 */
@Entity
@Data
public class RasporedRadnika implements Serializable {
	private static final long serialVersionUID = -4352876801163385834L;
	
	@Id
	@GeneratedValue
	private long id;
	private Employee radnik;
	private Poslovi poslovi;
	private Raspored raspored;
	private Linija linija;
	
}
