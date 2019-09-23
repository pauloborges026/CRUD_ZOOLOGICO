package com.mycompany.crud.controle;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.mycompany.crud.modelo.Zoologico;
import com.mycompany.crud.repositorio.ZoologicoRepositorio;

/**
 *
 * @author professor
 */
@Named
@RequestScoped
public class ZoologicoControle {

    @Inject
    private Zoologico zoologico;
    
    private List<Zoologico> zoologicos = new ArrayList<>();

    @Inject
    private ZoologicoRepositorio repositorio;

    public Zoologico getZoologico() {
        return zoologico;
    }

    public List<Zoologico> getZoologicos() {
        return zoologicos;
    }
    
    public void addZoologico() {
        try {
            repositorio.create(zoologico);
            //System.out.println(zoologico.getNome());
            addMensagem("Cadastro realizado com sucesso!");
            zoologico = new Zoologico();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
            addMensagem("Erro ao cadastrar!");
        }
    }
    
    public Zoologico findById(Long id) {
        return repositorio.findById(id);
    }
    
    public void buscarTodosZoologicos() {
        zoologicos = repositorio.findAll();
    }

    /**
     * Adiciona uma mensagem ao FacesContext;
     * @param msg 
     */
    public void addMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(msg, new FacesMessage(msg));
    }
}
