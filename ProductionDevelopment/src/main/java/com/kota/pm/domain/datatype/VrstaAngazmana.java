package com.kota.pm.domain.datatype;

public enum VrstaAngazmana {
	UGOVOR(0, "ugovor"), LIZING(1, "lizing"), ANEKS(2, "aneks");
	
	private int i;
	private String name;
	
	VrstaAngazmana(int i, String naziv) {
		this.i=i;
		this.name = naziv;
	}
	
	public int getOrder() {
		return i;
	}
	public String getNaziv() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
