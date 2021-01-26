package com.kota.pm.domain.datatype;

public enum VrstaArtikla {
SIROVINA(0, 0), AMBALAZA(0,1), POLUPROIZVOD(1,2), PROIZVOD(2,3);
	
	private int dubina;
	private int redniBroj;
	
	VrstaArtikla(int i, int x) {
		dubina = i;
		redniBroj = x;
	}

	public int getDubina() {
		return dubina;
	}

	public int getRedniBroj() {
		return redniBroj;
	}
	
}
