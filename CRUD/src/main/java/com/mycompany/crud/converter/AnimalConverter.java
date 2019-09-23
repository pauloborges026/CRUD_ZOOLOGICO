package com.mycompany.crud.converter;

import com.mycompany.crud.controle.AnimalControle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.mycompany.crud.modelo.Animal;

/**
 *
 * @author Arnaldo Junior
 */
@FacesConverter(forClass = Animal.class)
public class AnimalConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext contexto, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Long id = new Long(string);
        // Busca um objeto através de Linguagem de Expressão.
        AnimalControle controle = (AnimalControle) contexto.getApplication()
                .getELResolver().getValue(contexto.getELContext(), null, "animalControle");
        return controle.buscarPorId(id);
    }

    @Override
    public String getAsString(FacesContext contexto, UIComponent componente, Object objeto) {
        if (objeto == null) {
            return null;
        }
        if (objeto instanceof Animal) {
            Animal animal = (Animal) objeto;
            // Se a condição for verdadeira, retorna uma string vazia, senão retorna o id.
            return animal.getId() == null ? "" : animal.getId().toString();
        } else {
            throw new IllegalArgumentException("Objeto "+ objeto +" é do tipo "+ objeto.getClass().getName() +"; tipo esperado: Animal");
        }
    }
    
}

/*
    O método getAsString retorna o ID não para exibir para o usuário, mas apenas
    para relacionar o que ele espera receber o processo inverso, através do
    método getAsObject.
*/