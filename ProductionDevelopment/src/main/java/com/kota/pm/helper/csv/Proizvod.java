package com.kota.pm.helper.csv;

import com.kota.pm.domain.datatype.JedinicaMere;
import com.kota.pm.domain.datatype.VrstaArtikla;

public class Proizvod implements CsvCompatible{


	private long id;

	private String naziv;

	private JedinicaMere jedinicaMere;

	private VrstaArtikla vrstaArtikla;

	private Float jedinicnaCena;

	private Float konverzioniFaktor;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public JedinicaMere getJedinicaMere() {
		return jedinicaMere;
	}
	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	public VrstaArtikla getVrstaArtikla() {
		return vrstaArtikla;
	}
	public void setVrstaArtikla(VrstaArtikla vrstaArtikla) {
		this.vrstaArtikla = vrstaArtikla;
	}
	public Float getJedinicnaCena() {
		return jedinicnaCena;
	}
	public void setJedinicnaCena(Float jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}
	public Float getKonverzioniFaktor() {
		return konverzioniFaktor;
	}
	public void setKonverzioniFaktor(Float konverzioniFaktor) {
		this.konverzioniFaktor = konverzioniFaktor;
	}
	
}
