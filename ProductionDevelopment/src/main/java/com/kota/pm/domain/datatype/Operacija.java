package com.kota.pm.domain.datatype;

import com.kota.pm.domain.transakcije.Transakcija;

/**
 * Operacije nad dokumentima
 * @author kotarac
 *
 */
public enum Operacija {
	SABIRANJE(0, "sabiranje"), ODUZIMANJE(1, "oduzimanje");
	
	String naziv;
	int order;
	
	Operacija(int i, String s){
		this.naziv = s;
		this.order = i;
	}
	
	public String getNaziv() {
		return this.naziv;
	}
	public int getOrder() {
		return this.order;
	}
	
	public float doTransact(float a, float b) {
		float resultat = 0.0f;
		switch(this.order) {
		case 0:
			resultat = a+b;
			break;
		case 1:
			resultat = a-b;
			break;
		}
		return resultat;
	}
}
