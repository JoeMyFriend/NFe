/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import model.bean.Emitente;

/**
 *
 * @author Joe
 */
public class EmitenteDAO {
    
    EntityManager em = new ConnectionFactory().getConnection();
    
    public Emitente save(Emitente emitente){
        try {
            em.getTransaction().begin();
            if(emitente.getId() == null){
                em.persist(emitente);
            }
            else{
                em.merge(emitente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return emitente;
    }
}
