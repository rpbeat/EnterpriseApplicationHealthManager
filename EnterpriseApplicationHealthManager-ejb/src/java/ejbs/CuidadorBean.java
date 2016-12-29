/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CuidadorDTO;
import dtos.ProfissionalSaudeDTO;
import dtos.UtenteDTO;
import entities.Cuidador;
import entities.MaterialCapacitacao;
import entities.ProfissionalSaude;
import entities.Utente;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
public class CuidadorBean {
    @PersistenceContext
    private EntityManager em;
    private UtenteBean ub;
    
    public void create(String username, String password, String name, String email) {
        try {
            if(em.find(Cuidador.class, username) != null){
                return;
            }
            em.persist(new Cuidador(username, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Utente> getAllenrroledUtentes(String userName){
        try{
            Cuidador cuidador = em.find(Cuidador.class, userName);
            
            if(cuidador == null ){
                throw new EJBException();
            }
            return cuidador.getUtentes();
            
        }catch(Exception e){
            e.getMessage();
        }
        return null;
    }
    
    public void enrollUtente(String usernameCuidador, String usernameUtente){
        try{
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            Utente utente = em.find(Utente.class, usernameUtente);
            
            if(cuidador == null || utente == null){
                throw new EntityDoesNotExistsException();
            }
            
            List<Utente> list = cuidador.getUtentes();
            if(list.contains(utente)){
                throw new EntityAlreadyExistsException();
            }
            
            cuidador.addUtente(utente);
        }catch(EJBException | EntityAlreadyExistsException | EntityDoesNotExistsException e){
            e.getMessage();
        }
    }
    
    
    public void removeEnrroledUtente(String usernameCuidador, String usernameUtente){
        try{
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            Utente utente = em.find(Utente.class, usernameUtente);
            
            if(cuidador == null || utente == null){
                throw new EJBException();
            }
            cuidador.removeUtente(utente);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enrollMaterial(String idMaterial, String usernameCuidador){
        try{
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            MaterialCapacitacao material = em.find(MaterialCapacitacao.class, idMaterial);
            
            if(cuidador == null || material == null){
                throw new EntityDoesNotExistsException();
            }
            
            List<MaterialCapacitacao> list = cuidador.getMateriais();
            if(list!=null){
                if(list.contains(material)){
                    throw new EntityAlreadyExistsException();
                }
                cuidador.addMaterial(material);
            }
            
        }catch(EJBException | EntityAlreadyExistsException | EntityDoesNotExistsException e){
            e.getMessage();
        }
    }
    
    public List<MaterialCapacitacao> getAllenrroledMaterial(String userName){
        try{
            Cuidador cuidador = em.find(Cuidador.class, userName);
            
            if(cuidador == null ){
                throw new EJBException();
            }
            return cuidador.getMateriais();
            
        }catch(Exception e){
            e.getMessage();
        }
        return null;
    }
    
    public void removeEnrroledMaterial(String idMaterial, String usernameCuidador){
        try{
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            MaterialCapacitacao material = em.find(MaterialCapacitacao.class, idMaterial);
            
            if(cuidador == null || material == null){
                throw new EJBException();
            }
            cuidador.removeMaterial(material);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    
    
    public List<Cuidador> getAll(){
        List<Cuidador> Cuidadores = em.createNamedQuery("GetAllCuidadores").getResultList();
         return Cuidadores;
    }
    
    //@GET
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    //@Path("all")
    public List<CuidadorDTO> getAllDTO() {
        try {
            List<Cuidador> cuidadores = (List<Cuidador>) em.createNamedQuery("GetAllCuidadores").getResultList();
            return cuidadoresToDTOs(cuidadores);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    List<CuidadorDTO> cuidadoresToDTOs(List<Cuidador> cuidadores) {
        List<CuidadorDTO> dtos = new ArrayList<>();
        for (Cuidador s : cuidadores) {
            dtos.add(cuidadorToDTO(s));
        }
        return dtos;
    }
    
    CuidadorDTO cuidadorToDTO(Cuidador cuidador) {
        return new CuidadorDTO(cuidador.getUsername(), null, cuidador.getNome(), cuidador.getEmail());
    }
    
     public void remove(String username) throws EntityDoesNotExistsException {
        try {
            Cuidador cuidador = em.find(Cuidador.class, username);
            if (cuidador == null) {
                throw new EntityDoesNotExistsException("There is no cuidador with that username.");
            }
            
            em.remove(cuidador);
        
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
     
    public void update(String username, String password, String nome, String email) 
        throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            Cuidador cuidador = em.find(Cuidador.class, username);
            if (cuidador == null) {
                throw new EntityDoesNotExistsException("There is no cuidador with that username.");
            }
            
            cuidador.setPassword(password);
            cuidador.setNome(nome);
            cuidador.setEmail(email);
            em.merge(cuidador);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
}
