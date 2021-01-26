package com.kota.pm.view.dataConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kota.pm.controller.KontrolerPodatakaRadnika;
import com.kota.pm.domain.zaposleni.Employee;

@Component("employeeConverterFromName")
public class EmployeeConverterFromName implements Converter<Employee> {
	
	@Autowired
	private KontrolerPodatakaRadnika kpr;

	@Override
	public Employee getAsObject(FacesContext context, UIComponent component, String value) {
		if(value.isEmpty() || value.equals(""))
			return null;
		String[] name = value.split(" ");
		return kpr.getZaposleniByNameAndSurname(name[0], name[1]);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Employee value) {
		if(value==null)
			return null;
		return value.getIme() + " " + value.getPrezime();
	}

}
