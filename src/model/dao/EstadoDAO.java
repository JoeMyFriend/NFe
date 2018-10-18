/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.bean.Estado;
import static nfe.NFe.emBs;

/**
 *
 * @author Joe
 */
public class EstadoDAO {

    public List<Estado> findAll() {
        List<Estado> estados = null;

        try {
            estados = emBs.createQuery("from Estado e").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }

        return estados;
    }

}
