package com.kota.pm.view.dataConverters;

import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPermanentnihPodataka;
import com.kota.pm.domain.datatype.Operacija;

@Component("operacijaKonverter")
public class OperacijaConverter implements Converter {

	@Autowired
	private KontrolerPermanentnihPodataka kpp;
	
	@Override
	public Operacija getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.equals(""))
			return null;
		Optional<Operacija> res = kpp.getListaOperacija().stream().filter(
				o -> o.getNaziv().equals(value)
				).findFirst();
		if(res.isPresent())
			return res.get();
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null)
			return null;
		return ((Operacija)value).getNaziv();
	}

}
