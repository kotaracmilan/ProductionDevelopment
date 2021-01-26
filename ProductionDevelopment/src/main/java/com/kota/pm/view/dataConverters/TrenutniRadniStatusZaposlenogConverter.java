package com.kota.pm.view.dataConverters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import com.kota.pm.domain.datatype.TrenutniRadniStatusZaposlenog;

@Component("trenutniStatusConverter")
public class TrenutniRadniStatusZaposlenogConverter implements Converter<TrenutniRadniStatusZaposlenog>, Serializable {

	@Override
	public TrenutniRadniStatusZaposlenog getAsObject(FacesContext context, UIComponent component, String value) {
		if(!value.isEmpty() || !value.equals(""))
			return TrenutniRadniStatusZaposlenog.values()[Integer.parseInt(value)];
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TrenutniRadniStatusZaposlenog value) {
		return String.valueOf(value.getId());
	}

}
