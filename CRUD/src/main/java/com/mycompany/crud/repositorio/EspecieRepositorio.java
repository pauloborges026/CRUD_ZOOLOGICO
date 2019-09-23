package com.mycompany.crud.repositorio;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import com.mycompany.crud.modelo.Especie;
import javax.enterprise.context.Dependent;

/**
 * @author jeremias
 */
@Dependent
public class EspecieRepositorio {
    
    public void EspecieRepositorio(){

    }
  
    @PersistenceUnit(unitName = "CadastroZooJPAPU")
    EntityManagerFactory emf;
    
    @Resource
    UserTransaction transaction;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Adiciona um objeto Especie na base de dados.
     * @param especie
     * @throws Exception 
     */
    public void create(Especie especie) throws Exception {
        
        EntityManager em = null;
        try {
            transaction.begin();
            em = getEntityManager();
            em.persist(especie);
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
    
    public List<Especie> findAll() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q = em.createQuery("select object(o) from Especie as o");
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Especie findById(Long id){
        EntityManager em = getEntityManager();
        try {
            return em.find(Especie.class, id);
        } finally {
            em.close();
        }
    }  
}