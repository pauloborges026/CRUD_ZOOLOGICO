package com.mycompany.crud.controle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.mycompany.crud.modelo.Especie;
import com.mycompany.crud.repositorio.EspecieRepositorio;

/**
 * @author jeremias
 */
@Named
@RequestScoped
public class EspecieControle {

    @Inject
    private Especie especie;

    @Inject
    private EspecieRepositorio especieRepositorio;

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    
    public void addEspecie() {
        try {
            especieRepositorio.create(especie);
            addMensagem("Cadastro realizado com sucesso! " + especie.getNome());
            especie = new Especie();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
            addMensagem("Erro ao cadastrar!");
        }
    }

    public void addMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(
                msg, new FacesMessage(msg));
    }

    public Especie getEspecie(Long id) {
        return especieRepositorio.findById(id);
    }

}
