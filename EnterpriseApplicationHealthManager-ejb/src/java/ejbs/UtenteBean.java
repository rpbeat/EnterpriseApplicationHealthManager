/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.UtenteDTO;
import entities.Utente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rpbeat
 */
@Stateless
public class UtenteBean {
    @PersistenceContext
    private EntityManager em;
    
    public void create(String username, String password, String name, String email) {
        try {
            if(em.find(Utente.class, username) != null){
                return;
            }
            em.persist(new Utente(username, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Utente> getall(){
         List<Utente> utentes = em.createNamedQuery("GetAllUtentes").getResultList();
         return utentes;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<UtenteDTO> getAllDTO() {
        try {
            List<Utente> utentes = (List<Utente>) em.createNamedQuery("GetAllUtentes").getResultList();
            return utentesToDTOs(utentes);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    List<UtenteDTO> utentesToDTOs(List<Utente> utentes) {
        List<UtenteDTO> dtos = new ArrayList<>();
        for (Utente s : utentes) {
            dtos.add(utenteToDTO(s));
        }
        return dtos;
    }
    
    UtenteDTO utenteToDTO(Utente utente) {
        return new UtenteDTO(utente.getUsername(), null, utente.getNome(), utente.getEmail());
    }
}
