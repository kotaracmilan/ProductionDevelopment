package com.kota.pm.view.dataConverters;

import java.util.Optional;
import java.util.stream.Stream;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import com.kota.pm.domain.datatype.JedinicaMere;

@Component("jedinicaMereConverter")
public class JedinicaMereConverter implements Converter {

	@Override
	public JedinicaMere getAsObject(FacesContext context, UIComponent component, String value) {
		Optional<JedinicaMere> va = Stream.of(JedinicaMere.values()).filter(v -> v.toString().equals(value))
				.findAny();
		if(va.isPresent())
			return va.get();
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null)
			return null;
		return ((JedinicaMere)value).name();
	}

}
