package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dtos.AdministradorDTO;
import dtos.CuidadorDTO;
import dtos.ProfissionalSaudeDTO;
import dtos.UtenteDTO;
import ejbs.AdministradorBean;
import ejbs.CuidadorBean;
import ejbs.ProfissionalSaudeBean;
import ejbs.UtenteBean;
import entities.Cuidador;
import entities.ProfissionalSaude;
import entities.Utente;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

@ManagedBean
@SessionScoped
public class AdministratorManager {
   
    @EJB
    private UtenteBean utenteBean;
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;
    @EJB
    private CuidadorBean cuidadorBean;
    @EJB
    private AdministradorBean administradorBean;   
    public UtenteDTO newUtente;
    private UIComponent component;
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    
    
    
    public AdministratorManager() {
        newUtente = new UtenteDTO();
    }
    
    public List<Utente> getAllUtentes() {
        try {
            return utenteBean.getall();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    public List<ProfissionalSaude> getAllProfissionais(){
        try{
            return profissionalSaudeBean.getAll();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
         return null;
    }
    
    public List<Cuidador> getAllCuidadores(){
        try{
            return cuidadorBean.getAll();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
         return null;
    }
    
    public  List<UtenteDTO> getAllUtentesDTO(){
        try{
            return utenteBean.getAllDTO();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    public List<ProfissionalSaudeDTO> getAllProfissionaisDTO(){
        try{
            return profissionalSaudeBean.getAllDTO();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    public List<CuidadorDTO> getAllCuidadoresDTO(){
        try{
            return cuidadorBean.getAllDTO();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    public List<AdministradorDTO> getAllAdministradoresDTO(){
        try{
            return administradorBean.getAllDTO();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    //////////////// UTENTE
    public String createUtente() {
        try {
            utenteBean.create(newUtente.getUsername(),newUtente.getPassword(),newUtente.getNome(),newUtente.getEmail());
            newUtente.reset();
            return "admin_listAll?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }

    public UtenteDTO getNewUtente() {
        return newUtente;
    }

    public void setNewUtente(UtenteDTO newUtente) {
        this.newUtente = newUtente;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
}
