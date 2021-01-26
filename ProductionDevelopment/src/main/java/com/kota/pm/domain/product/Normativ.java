package com.kota.pm.domain.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.kota.pm.domain.datatype.JedinicaMere;
import com.kota.pm.domain.proizvodnja.Linija;

import lombok.Data;

@Entity
@Data
public class Normativ implements Serializable{

	private static final long serialVersionUID = 9097495389097865971L;
	
	
	@Id
	@GeneratedValue
	private long id;
	private String naziv;
	@NotNull(message="Obavezna oznaka normativa") @Min(0) @Max(30)
	private int oznakaNormativa;
	@NotNull(message="Obavezno uneti proizvod")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<NormativnaSastavnica> normativnaSastavnica;
	@Enumerated(EnumType.STRING)
	private JedinicaMere jedinicaMere; //jedinica mere za količinu po normativu;
	private Float jedinicnaKolicina; //kolicina na koju se odnosi normativ
	@OneToOne
	private Linija linija;
	private Float kapacitet;
	private Integer brojRadnika;
	private Boolean isActive;
	private Date activeFrom;
	private Date activeUntil;
	@ManyToOne
	private Proizvod proizvod;
	
	
}
