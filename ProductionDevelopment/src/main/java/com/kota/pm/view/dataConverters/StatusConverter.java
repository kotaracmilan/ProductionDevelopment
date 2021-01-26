package com.kota.pm.view.dataConverters;

import java.util.Optional;
import java.util.stream.Stream;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import com.kota.pm.domain.datatype.Status;

@Component("statusConverter")
public class StatusConverter implements Converter<Status> {

	@Override
	public Status getAsObject(FacesContext context, UIComponent component, String value) {
		Optional<Status> s = Stream.of(Status.values()).filter(v -> v.toString().equals(value))
		.findAny();
		if(s.isPresent())
			return s.get();
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Status value) {	
		return value.toString();
	}

}
