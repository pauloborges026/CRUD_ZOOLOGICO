package com.mycompany.crud.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Arnaldo Junior
 */
@FacesConverter(value = "localDate")
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext contexto, UIComponent componente, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        System.out.println("STRING: "+ string);
        LocalDate data = LocalDate.parse(string);
        return data;
    }

    @Override
    public String getAsString(FacesContext contexto, UIComponent componente, Object objeto) {
        if (objeto == null) {
            return null;
        }
        System.out.println("OBJETO: "+ objeto);
        if (objeto instanceof LocalDate) {
            LocalDate data = (LocalDate) objeto;
            return data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } else {
            throw new IllegalArgumentException("Tipo não suportado ao converter objeto para String");
        }
    }    
}