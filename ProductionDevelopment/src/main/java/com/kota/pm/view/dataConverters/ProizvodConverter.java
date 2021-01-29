package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerProizvodnihPodataka;
import com.kota.pm.domain.product.Proizvod;
import com.kota.pm.domain.zaposleni.Poslovi;

@Component("proizvodConverter")
public class ProizvodConverter implements Converter {

	@Autowired
	private KontrolerProizvodnihPodataka kpp;
	@Override
	public Proizvod getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.equals(""))
			return null;
		Long id = Long.valueOf(value);
		return kpp.getProizvodById(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value==null)
			return null;
		return String.valueOf(((Proizvod)value).getId());
	}

}
