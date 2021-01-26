package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPermanentnihPodataka;
import com.kota.pm.domain.datatype.Dokument;

@Component("dokumentConverter")
public class DokumentConverter implements Converter<Dokument> {

	@Autowired
	private KontrolerPermanentnihPodataka kpp;
	
	@Override
	public Dokument getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.equals(""))
			return null;
		Integer in = Integer.valueOf(value);
		return kpp.findDokumentById(in);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Dokument value) {
		if(value == null)
			return null;
		return String.valueOf(value.getId());
	}

}
