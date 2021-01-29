package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.zaposleni.Employee;

@Component("employeeConverter")
public class EmployeeConverter implements Converter {
	
	@Autowired
	private KontrolerPodatakaRadnika kpr;

	@Override
	public Employee getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty())
			return null;
		try {
		Integer id = Integer.valueOf(value);
		Employee e = kpr.getRadnikFromId(id);
		return e;
		}
		catch(Exception e) {
			
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return String.valueOf(((Employee)value).getId());
	}

}
