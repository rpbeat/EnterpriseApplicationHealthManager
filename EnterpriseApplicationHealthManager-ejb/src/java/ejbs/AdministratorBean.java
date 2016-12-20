/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Administrator;
<<<<<<< HEAD
import java.util.List;
import javax.ejb.EJBException;

/**
 *
 * @author rpbeat
 */
@Stateless
public class AdministratorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String username, String password, String name, String email) {
        try {
            if(em.find(Administrator.class, username) != null){
                return;
            }
            em.persist(new Administrator(username, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Administrator> getall(){
         List<Administrator> admin = em.createNamedQuery("GETALLADMINISTRATORS").getResultList();
         return admin;
    }
}
=======
import javax.ejb.EJBException;

/**
 *
 * @author rpbeat
 */
@Stateless
public class AdministratorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String username, String password, String name, String email) {
        try {
            if(em.find(Administrator.class, username) != null){
                return;
            }
            em.persist(new Administrator(username, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
>>>>>>> origin/master
