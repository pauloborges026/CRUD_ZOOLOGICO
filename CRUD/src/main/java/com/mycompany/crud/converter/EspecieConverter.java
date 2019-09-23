/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crud.converter;

import com.mycompany.crud.controle.EspecieControle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.mycompany.crud.modelo.Especie;

/**
 *
 * @author jeremias.neres
 */

@FacesConverter(value = "especieconverter")
public class EspecieConverter implements Converter {
  
  public String getAsString(FacesContext context, UIComponent component,
      Object value) {
    if (value == null) {
      return null;
    }
  
    Especie especie = (Especie) value;
  
    return especie.getId().toString();
  }
  
  public Object getAsObject(FacesContext context, UIComponent component,
      String value) {
      if (value == null){
          return null;
      }
      
      EspecieControle controle = (EspecieControle) context.getApplication().
              getELResolver().getValue(context.getELContext(), null, "especieControle");
      
      Especie objeto = controle.getEspecie(new Long(value));
      return objeto;
  }
}
  


