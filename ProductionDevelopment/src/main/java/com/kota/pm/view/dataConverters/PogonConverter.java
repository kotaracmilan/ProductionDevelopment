package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.datatype.Pogon;

@Controller("pogonConverter")
public class PogonConverter implements Converter<Pogon> {
	
	@Autowired
	transient public KontrolerPodatakaRadnika kpr;

	@Override
	public Pogon getAsObject(FacesContext context, UIComponent component, String value) {
		if(value== null || value.isEmpty())
			return null;
		try {
		Integer i = Integer.valueOf(value);
		return kpr.findPogonById(i);
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Pogon value) {
		return String.valueOf(value.getId());
	}

}
