package com.kota.pm.domain.zaposleni;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.kota.pm.domain.datatype.Pogon;
import com.kota.pm.domain.proizvodnja.Linija;

import lombok.Data;

/**
 * Evidencija poslova koji se obavljaju u određenom pogonu
 * @author kotarac
 *
 */

@Entity
@Data
public class Poslovi implements Serializable {

	private static final long serialVersionUID = -3683842679968266519L;

	@Id
	@GeneratedValue
	private int id;
	private String posao;
	private Pogon pogon;
	private Linija linija;
	
	
	
	
}
