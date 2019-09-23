package com.mycompany.crud.controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import com.mycompany.crud.modelo.Animal;
import com.mycompany.crud.modelo.Especie;
import com.mycompany.crud.modelo.Zoologico;
import com.mycompany.crud.repositorio.AnimalRepositorio;
import com.mycompany.crud.repositorio.EspecieRepositorio;
import com.mycompany.crud.repositorio.ZoologicoRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author professor
 */
@Named
@RequestScoped
public class AnimalControle {

    @Inject
    private Animal animal;

    @Inject
    private AnimalRepositorio repositorio;

    @Inject
    private ZoologicoRepositorio zooRepositorio;
    
    @Inject
    private Zoologico zoologico;
    
    @Inject
    private Especie especie;
    @Inject
    private AnimalRepositorio animalRepositorio;
    @Inject
    private EspecieRepositorio especieRepositorio;
    List<Animal> animais = new ArrayList<>();
    
    @PostConstruct
    public void inicializar() {
        System.out.println("POST CONSTRUCT");
        buscarAnimais();
    }
    
    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
    private List<Animal> animaisSelecionados = new ArrayList<>();
    
    public Animal getAnimal() {
        return animal;
    }

    public List<Especie> getEspecies() {
        return especieRepositorio.findAll();
    }
   
   public List<Zoologico> getZoologicos() {
        return zooRepositorio.findAll();
    }

    public Especie getEspecie() {
        return especie;
    }

    public Zoologico getZoologico() {
        return zoologico;
    }

    public void setZoologico(Zoologico zoologico) {
        this.zoologico = zoologico;
    }
    
    public List<Animal> getAnimaisSelecionados() {
        return animaisSelecionados;
    }
    
    public void addAnimal() {
        try {
            repositorio.create(animal);
            addMensagem("Cadastro realizado com sucesso!"+animal.getNome()+" "+animal.getEspecie());


            animal = new Animal();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
            addMensagem("Erro ao cadastrar!");
        }
    }
    
    public String deletarAnimal() {
        try {
            Long id = new Long(FacesContext.getCurrentInstance()
                    .getExternalContext().getRequestParameterMap().get("animal"));
            System.out.println("ANIMAL ID: " + id);
            repositorio.deletar(id);
            addMensagem("Deleção realizada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao deletar animal: " + e);
            addMensagem("Erro ao deletar animal!");
        }
        return "";
    }
    
    public void buscarAnimalPorZoologico() {
        if (zoologico == null) {
            animaisSelecionados = repositorio.buscarTodos();
        } else {
            animaisSelecionados = repositorio.buscarAnimalPorZoologico(zoologico);
        }
    }
    
    public void buscarAnimaisMaisPesados() {
        animaisSelecionados = repositorio.buscarAnimaisMaisPesados();
    }
    public List<Animal> buscarAnimais(){
    
        return repositorio.buscarTodos();
    }
    public List<Animal> getAnimals() {
         try {
            this.animais = animalRepositorio.buscarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao buscar animais!");
        }
        return animais;
    }
    
    public Animal buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public void addMensagem(String msg) {
        FacesContext.getCurrentInstance().addMessage(
                msg, new FacesMessage(msg));
    }



}
