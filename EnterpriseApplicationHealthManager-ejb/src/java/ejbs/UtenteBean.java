/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.UtenteDTO;
import entities.ProcedimentoCuidado;
import entities.Utente;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import exceptions.Utils;

/**
 *
 * @author rpbeat
 */
@Stateless
public class UtenteBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public void create(String nome, String email,int contacto,String morada) {
        try {
            em.persist(new Utente(nome, email, contacto, morada));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Utente> getall(){
         List<Utente> utentes = em.createNamedQuery("GetAllUtentes").getResultList();
         return utentes;
    }
    
    //@GET
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    //@Path("all")
    public List<UtenteDTO> getAllDTO() {
        try {
            List<Utente> utentes = (List<Utente>) em.createNamedQuery("GetAllUtentes").getResultList();
            System.err.println(""+utentes.toString());
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
        return new UtenteDTO(utente.getId(),utente.getNome(), utente.getEmail(), utente.getContacto(), utente.getMorada());
    }
    
    public void remove(long idUtente) throws EntityDoesNotExistsException {
        try {
            Utente utente = em.find(Utente.class, idUtente);
            if (utente == null) {
                throw new EntityDoesNotExistsException("There is no utente with that username.");
            }
            
            em.remove(utente);
        
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
     public void update(long idUtente, String nome, String email,int contacto,String morada) 
        throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            Utente utente = em.find(Utente.class, idUtente);
            if (utente == null) {
                throw new EntityDoesNotExistsException("There is no utente with that username.");
            }
            
            utente.setContacto(contacto);
            utente.setMorada(morada);
            utente.setNome(nome);
            utente.setEmail(email);
            em.merge(utente);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
     
     public void enrrolProcedimento(String idProcedimento, long idUtente){
         try{
            Utente utente = em.find(Utente.class, idUtente);
            ProcedimentoCuidado procedimento = em.find(ProcedimentoCuidado.class, idProcedimento);
            if(utente == null | procedimento == null){
                throw new EntityDoesNotExistsException();
            }
            
            List<ProcedimentoCuidado> list = utente.getProcedimentos();
            if(list.contains(procedimento)){
                throw new EntityAlreadyExistsException();
            }
            
            utente.addProcedimento(procedimento);
        }catch(EJBException | EntityAlreadyExistsException | EntityDoesNotExistsException e){
            e.getMessage();
        }
     }
     
     public void removeEnrroledProdecimento(String idProcedimento, long idUtente){
        try{
            Utente utente = em.find(Utente.class, idUtente);
            ProcedimentoCuidado procedimento = em.find(ProcedimentoCuidado.class, idProcedimento);
            
            if(utente == null | procedimento == null){
                throw new EntityDoesNotExistsException();
            }
            utente.removeProcedimento(procedimento);
        }catch(Exception e){
            e.getMessage();
        }
    }
     
     public List<ProcedimentoCuidado> getAllProcedimentos(long idUtente){
         try{
            Utente utente = em.find(Utente.class, idUtente);          
            if(utente == null ){
                throw new EntityDoesNotExistsException();
            }
            return utente.getProcedimentos();
        }catch(Exception e){
            e.getMessage();
            return null;
        }
     }
}
