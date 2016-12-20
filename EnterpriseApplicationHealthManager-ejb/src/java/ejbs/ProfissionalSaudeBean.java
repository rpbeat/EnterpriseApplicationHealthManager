/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.ProfissionalSaudeDTO;
import entities.ProfissionalSaude;
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
public class ProfissionalSaudeBean {
     @PersistenceContext
    private EntityManager em;
    
    public void create(String username, String password, String name, String email) {
        try {
            if(em.find(ProfissionalSaude.class, username) != null){
                return;
            }
            em.persist(new ProfissionalSaude(username, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<ProfissionalSaude> getAll(){
        List<ProfissionalSaude> ProfissionaisSaude = em.createNamedQuery("GetAllProfissionalSaude").getResultList();
         return ProfissionaisSaude;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<ProfissionalSaudeDTO> getAllDTO() {
        try {
            List<ProfissionalSaude> profissionais = (List<ProfissionalSaude>) em.createNamedQuery("GetAllProfissionalSaude").getResultList();
            return profissionaisToDTOs(profissionais);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    List<ProfissionalSaudeDTO> profissionaisToDTOs(List<ProfissionalSaude> profissionais) {
        List<ProfissionalSaudeDTO> dtos = new ArrayList<>();
        for (ProfissionalSaude s : profissionais) {
            dtos.add(profissionalToDTO(s));
        }
        return dtos;
    }
    
    ProfissionalSaudeDTO profissionalToDTO(ProfissionalSaude profissional) {
        return new ProfissionalSaudeDTO(profissional.getUsername(), null, profissional.getNome(), profissional.getEmail());
    }
}
