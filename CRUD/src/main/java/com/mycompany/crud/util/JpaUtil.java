package com.mycompany.crud.util;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author paulo.borges
 */
public class JpaUtil {

  /*   @PersistenceUnit(unitName = "CadastroZooJPAPU")*/
     static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("CadastroZooJPAPU1");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void close() {
        factory.close();
    }
    
}
