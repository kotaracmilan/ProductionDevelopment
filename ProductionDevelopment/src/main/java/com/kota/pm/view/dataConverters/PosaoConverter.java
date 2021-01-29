package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.zaposleni.Poslovi;

@Component("posaoConverter")
public class PosaoConverter implements Converter {
	
	@Autowired
	KontrolerPodatakaRadnika kpr;

	@Override
	public Poslovi getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		try {
		Integer i = Integer.valueOf(value);
		return kpr.findPosloviById(i);
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		return String.valueOf(((Poslovi)value).getId());
	}

}
