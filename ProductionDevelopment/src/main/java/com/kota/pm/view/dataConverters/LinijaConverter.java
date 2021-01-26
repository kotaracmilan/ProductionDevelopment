package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.proizvodnja.Linija;

@Component("linijaConverter")
public class LinijaConverter implements Converter<Linija> {
	
	@Autowired
	private KontrolerPodatakaRadnika kpr;

	@Override
	public Linija getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		try {
		Integer id = Integer.valueOf(value);
		Linija l = kpr.getLinijaFromId(id);
		return l;
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Linija value) {
		return String.valueOf(value.getId());
	}

}
