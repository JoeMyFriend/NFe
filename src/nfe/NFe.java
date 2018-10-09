/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfe;

import connection.ConnectionFactory;
import javax.persistence.EntityManager;
import view.MainView;

/**
 *
 * @author User
 */
public class NFe {
    public static EntityManager em;
    public static EntityManager emBs;
    public final static MainView MAIN_VIEW = new MainView();
    
    public static void main(String[] args){
        em = new ConnectionFactory().getConnection();
        emBs = new ConnectionFactory().getConnectionBootstrap();
        
        MAIN_VIEW.setVisible(true);
    }
    
    
}
