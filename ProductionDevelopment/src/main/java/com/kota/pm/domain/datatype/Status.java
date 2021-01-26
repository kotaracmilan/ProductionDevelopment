package com.kota.pm.domain.datatype;

public enum Status {
ZAPOSLEN_NEODREDJENO(0, "zaposlen neodredjeno"), ZAPOSLEN_ODREDJENO(1,"zaposlen odredjeno"), AGENCIJA(2,"agencija"), PRIVREMENO_POVREMENI_POSLOVI(3,"privremenopovremeni poslovi");

	private String name;
	private int i;
	
	Status(int i, String naziv) {
		this.name= naziv;
		this.i = i;
	}
	
	public int getOrder() {
		return i;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
