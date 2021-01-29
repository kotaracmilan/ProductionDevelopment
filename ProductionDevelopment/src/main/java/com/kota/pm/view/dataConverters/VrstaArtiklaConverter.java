package com.kota.pm.view.dataConverters;

import java.util.Optional;
import java.util.stream.Stream;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import com.kota.pm.domain.datatype.VrstaArtikla;

@Component("vrstaArtiklaConverter")
public class VrstaArtiklaConverter implements Converter {

	@Override
	public VrstaArtikla getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.equals(""))
			return null;
		Optional<VrstaArtikla> va = Stream.of(VrstaArtikla.values()).filter(v -> v.toString().equals(value))
				.findAny();
		if(va.isPresent())
			return va.get();
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null)
			return null;
		return value.toString();
	}

}
