package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerProizvodnihPodataka;
import com.kota.pm.domain.product.Normativ;

@Component("normativConverter")
public class NormativConverter implements Converter<Normativ> {

	@Autowired
	public KontrolerProizvodnihPodataka kpp;
	@Override
	public Normativ getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		Long l = Long.valueOf(value);
		return kpp.findNormativById(l);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Normativ value) {
		if(value == null)
			return null;
		return String.valueOf(value.getId());
	}

}
