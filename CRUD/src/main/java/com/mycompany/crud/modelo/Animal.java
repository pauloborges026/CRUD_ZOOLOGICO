
package com.mycompany.crud.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author professor
 */
@Entity
@Dependent
@Table(name = "animal")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
                    generator = "animal_seq")
    @SequenceGenerator(name = "animal_seq",
                       sequenceName = "animal_seq",
                       initialValue = 1000)
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    private float peso;
    
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    @Transient
    private int idade;
    
    @ManyToOne
    @JoinColumn(name = "zoo_fk", referencedColumnName = "zoo_id")
    private Zoologico zoologico;
    
    @ManyToOne
    @JoinColumn(name = "especie_fk", referencedColumnName = "especie_id")
    private Especie especie;
    
    public Animal(){
    
    }
    public Animal(String nome, float peso, int idade, Zoologico zoologico, Especie especie ){
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
        this.zoologico = zoologico;
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }    

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public Zoologico getZoologico() {
        return zoologico;
    }

    public void setZoologico(Zoologico zoologico) {
        this.zoologico = zoologico;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", peso=" + peso + ", dataNascimento=" + dataNascimento + ", idade=" + idade + ", zoologico=" + zoologico + ", especie=" + especie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
   
}



