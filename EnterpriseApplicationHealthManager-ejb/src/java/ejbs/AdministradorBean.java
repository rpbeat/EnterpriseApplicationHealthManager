/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.AdministradorDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Administrador;
import exceptions.EntityDoesNotExistsException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;

/**
 *
 * @author rpbeat
 */
@Stateless
public class AdministradorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String username, String password, String name, String email) {
        try {
            if(em.find(Administrador.class, username) != null){
                return;
            }
            em.persist(new Administrador(username, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<AdministradorDTO> getAllDTO() {
        try {
            List<Administrador> aministradores = (List<Administrador>) em.createNamedQuery("GetAllAdministradores").getResultList();
            return administradoresToDTOs(aministradores);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    List<AdministradorDTO> administradoresToDTOs(List<Administrador> administradores) {
        List<AdministradorDTO> dtos = new ArrayList<>();
        for (Administrador s : administradores) {
            dtos.add(cuidadorToDTO(s));
        }
        return dtos;
    }
    
    AdministradorDTO cuidadorToDTO(Administrador cuidador) {
        return new AdministradorDTO(cuidador.getUsername(), null, cuidador.getNome(), cuidador.getEmail());
    }
    
     public void remove(String username) throws EntityDoesNotExistsException {
        try {
            Administrador administrador = em.find(Administrador.class, username);
            if (administrador == null) {
                throw new EntityDoesNotExistsException("There is no administrador with that username.");
            }
            
            em.remove(administrador);
        
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}