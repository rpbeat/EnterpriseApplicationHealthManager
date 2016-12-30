/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.ProcedimentoCuidadoDTO;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.ProcedimentoCuidado;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author rpbeat
 */
@Stateless
@Path("/procedimento")
public class ProcedimentoCuidadoBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public void create(String id ,String userNameCuidador, String descricao) {
        try {
            if(em.find(ProcedimentoCuidado.class, id) != null){
                return;
            }
            em.persist(new ProcedimentoCuidado(id,userNameCuidador,descricao));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    @GET
    //@RolesAllowed({"Administrador", "ProfissionalSaude", "Cuidador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<ProcedimentoCuidadoDTO> getAllDTO() {
        try {
            List<ProcedimentoCuidado> procedimentos = (List<ProcedimentoCuidado>) em.createNamedQuery("GetAllProcedimentoCuidado").getResultList();
            return procedimentosToDTOs(procedimentos);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    List<ProcedimentoCuidadoDTO> procedimentosToDTOs(List<ProcedimentoCuidado> procedimentos) {
        List<ProcedimentoCuidadoDTO> dtos = new ArrayList<>();
        for (ProcedimentoCuidado s : procedimentos) {
            dtos.add(procedimentoToDTO(s));
        }
        return dtos;
    }
    
    ProcedimentoCuidadoDTO procedimentoToDTO(ProcedimentoCuidado procedimento) {
        return new ProcedimentoCuidadoDTO(procedimento.getId(),procedimento.getUserNameCuidador(),procedimento.getDescricao());
    }
    
    public void remove(String id) throws EntityDoesNotExistsException {
        try {
            ProcedimentoCuidado procedimento = em.find(ProcedimentoCuidado.class, id);
            if (procedimento == null) {
                throw new EntityDoesNotExistsException("There is no profissional with that username.");
            }
            
            em.remove(procedimento);
        
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
     public void update(String id ,String userNameCuidador, String descricao) 
        throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            ProcedimentoCuidado procedimento = em.find(ProcedimentoCuidado.class, id);
            if (procedimento == null) {
                throw new EntityDoesNotExistsException("There is no utente with that username.");
            }
            
            procedimento.setId(id);
            procedimento.setUserNameCuidador(userNameCuidador);
            procedimento.setDescricao(descricao);
            em.merge(procedimento);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
     
     
}
