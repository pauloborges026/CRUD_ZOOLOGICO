package com.mycompany.crud.repositorio;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import com.mycompany.crud.modelo.Zoologico;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

/**
 *
 * @author professor
 */
public class ZoologicoRepositorio {
    public void ZoologicoRepositorio(){}
    @PersistenceUnit(unitName = "CadastroZooJPAPU")
    EntityManagerFactory emf;
    
    //EntityManager manager;
    
    @Resource
    UserTransaction transaction;
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
   
    /**
     * Adiciona um objeto Zoologico na base de dados.
     * @param zoo
     * @param zoologico
     * @throws javax.transaction.RollbackException
     * @throws Exception 
     */
    
        public void create(Zoologico zoo) throws javax.transaction.RollbackException {
        EntityManager em = null;
        try {
            System.out.println(zoo.getNome());
            transaction.begin();
            em = this.getEntityManager();
            em.persist(zoo);
            transaction.commit();
            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(ZoologicoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    

     
    public List<Zoologico> findAll() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            //Query q = em.createQuery("select o from Zoologico o");
            TypedQuery<Zoologico> q = em.createQuery("select o from Zoologico o", Zoologico.class);
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
     /**
     * Busca um objeto do tipo Zoologico pelo ID.
     * @param id
     * @return 
     * 
     */
    public Zoologico findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zoologico.class, id);
        } finally {
            em.close();
        }
       }

}