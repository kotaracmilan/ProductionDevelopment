package com.kota.pm.view.dataConverters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.zaposleni.Poslovi;
import com.kota.pm.domain.zaposleni.RadnoMesto;

@Component("radnoMestoConverter")
public class RadnoMestoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	transient private KontrolerPodatakaRadnika kpr;
	
	@Override
	public RadnoMesto getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		Integer i = Integer.parseInt(value);
		try {
			return kpr.findRadnoMestoById(i);
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return String.valueOf(((RadnoMesto)value).getId());
	}

}
