package com.kota.pm.view.dataConverters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.datatype.Poslodavac;

//@Service
@Component(value="poslodavacConverter")
public class PoslodavacConverter implements Converter<Poslodavac>, Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	transient private KontrolerPodatakaRadnika kontrolerPodatakaRadnika;
	
	
	@Override
	public Poslodavac getAsObject(FacesContext context, UIComponent component, String value) {
		if(value.isEmpty())
			return null;
		Integer i = Integer.parseInt(value);
		try {
			return kontrolerPodatakaRadnika.findPoslodavacById(i);
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Poslodavac value) {
		return String.valueOf(value.getId());
	}

}
