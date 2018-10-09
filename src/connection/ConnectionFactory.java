/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joe
 */
public class ConnectionFactory {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("NFePU");
    private static EntityManagerFactory emfBootstrap = Persistence.createEntityManagerFactory("bootstrapPU");
    
    public EntityManager getConnection(){
        return emf.createEntityManager();
    }
    public EntityManager getConnectionBootstrap(){
        return emfBootstrap.createEntityManager();
    }
}
