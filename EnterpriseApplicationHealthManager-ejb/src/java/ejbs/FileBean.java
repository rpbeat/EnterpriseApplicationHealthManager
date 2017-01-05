/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Cuidador;
import entities.FileUpload;
import entities.ManagerApp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class FileBean {

    @PersistenceContext
    private EntityManager em;
    
    public void create(String nomeFicheiro, String caminhoFicheiro) {
        try {
            em.persist(new FileUpload(nomeFicheiro, caminhoFicheiro));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<FileUpload> getAllFiles(){
        return em.createNamedQuery("GetAllFiles").getResultList();
    }
}
