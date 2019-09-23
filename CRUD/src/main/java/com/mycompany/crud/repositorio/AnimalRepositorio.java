package com.mycompany.crud.repositorio;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import com.mycompany.crud.modelo.Animal;
import com.mycompany.crud.modelo.Especie;
import com.mycompany.crud.modelo.Zoologico;
import javax.enterprise.context.Dependent;

/**
 *
 * @author professor
 */
@Dependent
public class AnimalRepositorio {
    public void AnimalRepositorio(){

    }

    @PersistenceUnit(unitName = "CadastroZooJPAPU")
    EntityManagerFactory emf;
    
    @Resource
    UserTransaction transaction;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Adiciona um objeto Animal na base de dados.
     * @param animal
     * @throws Exception 
     */
    public void create(Animal animal) throws Exception {
        EntityManager em = null;
        try {
            transaction.begin();
            em = this.getEntityManager();
            em.persist(animal); 
            transaction.commit();
        
        } catch(Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (Exception ex) {
                throw new Exception("Erro ao efetuar rollback: "+ ex);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void deletar(Long id) throws Exception {
         EntityManager em = null;
        try {
            transaction.begin();
            em = getEntityManager();
            Animal animal;
            try {
                animal = em.getReference(Animal.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new Exception("Animal com id " + id + " não existe.", enfe);
            }
            em.remove(animal);
            transaction.commit();
        
        } catch(Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (Exception ex) {
                throw new Exception("Erro ao efetuar rollback: "+ ex);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Busca um objeto do tipo Animal pelo ID.
     * @param id
     * @return 
     */
    public Animal buscarPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Animal.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca todos os objetos do tipo Animal.
     * @return 
     */
    public List<Animal> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Animal> q = em.createQuery("select a from Animal a", Animal.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Animal> buscarAnimalPorZoologico(Zoologico zoo) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Animal a WHERE a.zoologico = ?1").setParameter(1, zoo).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Animal> buscarAnimaisMaisPesados() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Animal> q = em.createQuery("SELECT a FROM Animal a ORDER BY a.peso DESC", Animal.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}