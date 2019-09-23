package com.mycompany.crud.converter;

import com.mycompany.crud.controle.ZoologicoControle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.mycompany.crud.modelo.Zoologico;

/**
 *implements Converter
 * @author Arnaldo Junior
 */
@FacesConverter(forClass = Zoologico.class)
public class ZoologicoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext contexto, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Long id = new Long(string);
        // Busca um objeto através de Linguagem de Expressão.
        ZoologicoControle controle = (ZoologicoControle) contexto.getApplication()
                .getELResolver().getValue(contexto.getELContext(), null, "zoologicoControle");
        return controle.findById(id);
    }

    @Override
    public String getAsString(FacesContext contexto, UIComponent componente, Object objeto) {
        if (objeto == null) {
            return null;
        }
        if (objeto instanceof Zoologico) {
            Zoologico zoo = (Zoologico) objeto;
            // Se a condição for verdadeira, retorna uma string vazia, senão retorna o id.
            return zoo.getId() == null ? "" : zoo.getId().toString();
        } else {
            throw new IllegalArgumentException("Objeto "+ objeto +" é do tipo "+ objeto.getClass().getName() +"; tipo esperado: Zoologico");
        }
    }
  
}

/*
    O método getAsString retorna o ID não para exibir para o usuário, mas apenas
    para relacionar o que ele espera receber o processo inverso, através do
    método getAsObject.
*/