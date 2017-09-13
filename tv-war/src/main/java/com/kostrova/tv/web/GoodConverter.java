package com.kostrova.tv.web;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.kostrova.tv.dto.Good;

@FacesConverter("goodConverter")
public class GoodConverter implements Converter {


	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object object) {
	        return ((Good) object).toString();
	    }

	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
	   //     List<Good> goods = (List<Good>) context.getApplication().evaluateExpressionGet(context.getELContext(), "#{cart.good}", List.class);
//
//	        for (Good good : goods) {
//	            if (good.getId().toString().equals(submittedValue)) {
//	                return good;
//	            }
//	        }

	        return null;
	    }
}
