/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Administrador;
import entities.ManagerApp;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

/**
 *
 * @author rpbeat
 */
@Stateless
@Path("/managerAppBean")
public class ManagerAppBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public void create() {
        try {
            em.persist(new ManagerApp());
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void addAcessoCuidador(String userNameCuidador, Date data) {
      //  try {         
            ManagerApp manager = em.find(ManagerApp.class, 1);
            if(manager == null){
                em.persist(new ManagerApp());
                ManagerApp manager2 = em.find(ManagerApp.class, 1);
                manager2.addAcessoCuidador(userNameCuidador, data);
            }else{
                manager.addAcessoCuidador(userNameCuidador, data);
            }
            
    //    } catch (Exception e) {
  //          throw new EJBException(e.getMessage());
//        }
    }
    
    public List<Date> getListTotalAcessos(){
        try {         
            ManagerApp manager = em.find(ManagerApp.class, 1);
            if(manager != null){
                return manager.getListaTotal();
            }
            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return null;
    }
    
    public List<Date> getListAcessosByUser(String UserName){
        try {         
            ManagerApp manager = em.find(ManagerApp.class, 1);
            if(manager != null){
                return manager.getListaDeAcessosUser(UserName);
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return null;
    }
    
    public int getTotalLogin(){
        try {         
            ManagerApp manager = em.find(ManagerApp.class, 1);
            if(manager != null){
                return manager.getCountLogin();
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return 0;
    }
}
