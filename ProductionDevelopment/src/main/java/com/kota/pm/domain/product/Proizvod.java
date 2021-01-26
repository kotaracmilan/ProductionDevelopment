package com.kota.pm.domain.product;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.kota.pm.domain.datatype.JedinicaMere;
import com.kota.pm.domain.datatype.VrstaArtikla;
import com.kota.pm.domain.transakcije.Kartica;
import com.kota.pm.domain.transakcije.Stanje;

import lombok.Data;

@Entity
@Data
public class Proizvod implements Serializable{
	

	private static final long serialVersionUID = 5906170255159531588L;

	@Id
	@NotNull(message="Šifra je obavezna") @Min(1) @Max(999999)
	private long id;
	@NotEmpty(message="Naziv proizvoda je obavezan")
	private String naziv;
	@Enumerated(EnumType.STRING) @NotNull(message="Obavezno odabrati jedinicu mere")
	private JedinicaMere jedinicaMere;
	@Enumerated(EnumType.STRING) @NotNull(message="Obavezno odabrati vrstu artikla")
	private VrstaArtikla vrstaArtikla;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Normativ> normativi;
	private Float jedinicnaCena;
	private Float konverzioniFaktor;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Kartica> kartice;
	@OneToMany
	private List<Stanje> stanje;
	
	private double cogs;
	private double prodajnaCena; //bez pdv-a
	private int pdvSopa;
	
	
	

}
