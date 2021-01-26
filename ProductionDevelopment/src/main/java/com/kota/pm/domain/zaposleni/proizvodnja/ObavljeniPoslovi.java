package com.kota.pm.domain.zaposleni.proizvodnja;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kota.pm.domain.zaposleni.Employee;
import com.kota.pm.domain.zaposleni.Poslovi;
import com.kota.pm.domain.zaposleni.Raspored;

import lombok.Data;

/**
 * Evidencija obavljenih poslova koji čine ukupno radno vreme, odnosno ukupne sate u karnetu za taj Raspored (dan)
 * @author radni
 *
 */

@Entity
@Data
public class ObavljeniPoslovi implements Serializable{
	private static final long serialVersionUID = -1656921407025734552L;

	@Id
	@GeneratedValue
	private long id;
	private Raspored smena;
	private Employee zaposleni;
	private Poslovi obavljeniPosao;
	private float vreme;
	private Karnet karnet;
	

}
