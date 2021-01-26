package com.kota.pm.domain.datatype;

public enum TrenutniRadniStatusZaposlenog {
	RASPOLOZIV(0, "RASPOLOŽIV"), BOLOVANJE(1, "BOLOVANJE"), GODIŠNJI_ODMOR(2, "GODIŠNJI ODMOR"), SLOBODNI_DANI(3, "SLOBODNI DANI"), SANITARNA_ZABRANA(4, "SANITARNA ZABRANA"); 

	int id;
	String naziv;
	
	TrenutniRadniStatusZaposlenog(int i, String s) {
		this.id= i;
		this.naziv = s;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNaziv(){
		return naziv;
	}
}
