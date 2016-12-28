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
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

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

    private UtenteDTO newUtente;
    private Utente currentUtente; //PROBLEMA COM O DTO A VERIFICAR
    
    private ProfissionalSaudeDTO newProfissional;
    private ProfissionalSaudeDTO currentProfissional;
    
    private CuidadorDTO newCuidador;
    private CuidadorDTO currentCuidador;
    
    private AdministradorDTO newAdministrador;
    private AdministradorDTO currentAdministrador;

    private UIComponent component;
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    
    
    
    
    public AdministratorManager() {
        newUtente = new UtenteDTO();
        newProfissional = new ProfissionalSaudeDTO();
        currentProfissional = new ProfissionalSaudeDTO();
        newCuidador = new CuidadorDTO();
        newAdministrador = new AdministradorDTO();
        currentCuidador = new CuidadorDTO();
        currentAdministrador = new AdministradorDTO();
    }
    
    public List<Utente> getAllUtentes() {
        try {
            return utenteBean.getall();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter4!", logger);
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
            System.out.println(""+utenteBean.getAllDTO().toString());
            return utenteBean.getAllDTO();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter1!", logger);
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
            return "admin_list_all?faces-redirect=true";
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

    public Utente getCurrentUtente() {
        return currentUtente;
    }

    public void setCurrentUtente(Utente currentUtente) {
        this.currentUtente = currentUtente;
    }
    
    public void removeUtente(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("utenteUsername");
            String id = param.getValue().toString();
            utenteBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public String updateUtente() {
        try {
            utenteBean.update(
                    currentUtente.getUsername(),
                    currentUtente.getPassword(),
                    currentUtente.getNome(),
                    currentUtente.getEmail());
            return "admin_list_all?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_utente_update";
    }
    
    
    
    //////////PROFISSIONALSAUDE

    public ProfissionalSaudeDTO getNewProfissional() {
        return newProfissional;
    }

    public void setNewProfissional(ProfissionalSaudeDTO newProfissional) {
        this.newProfissional = newProfissional;
    }
    
    public String createProfissionalSaude() {
        try {
            profissionalSaudeBean.create(newProfissional.getUsername(),newProfissional.getPassword(),newProfissional.getNome(),newProfissional.getEmail());
            newProfissional.reset();
            return "admin_list_all?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }

    public ProfissionalSaudeDTO getCurrentProfissional() {
        return currentProfissional;
    }

    public void setCurrentProfissional(ProfissionalSaudeDTO currentProfissional) {
        this.currentProfissional = currentProfissional;
    }
    
    public void removeProfissional(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("profissionalUsername");
            String id = param.getValue().toString();
            profissionalSaudeBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
     public String updateProfissional() {
        try {
            profissionalSaudeBean.update(
                    currentProfissional.getUsername(),
                    currentProfissional.getPassword(),
                    currentProfissional.getNome(),
                    currentProfissional.getEmail());
            return "admin_list_all?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_profissional_update";
    }
    
    ///////CUIDADOR

    public CuidadorDTO getNewCuidador() {
        return newCuidador;
    }

    public void setNewCuidador(CuidadorDTO newCuidador) {
        this.newCuidador = newCuidador;
    }
    
    public String createCuidador() {
        try {
            cuidadorBean.create(newCuidador.getUsername(),newCuidador.getPassword(),newCuidador.getNome(),newCuidador.getEmail());
            newCuidador.reset();
            return "admin_list_all?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }

    public CuidadorDTO getCurrentCuidador() {
        return currentCuidador;
    }

    public void setCurrentCuidador(CuidadorDTO currentCuidador) {
        this.currentCuidador = currentCuidador;
    }
    
    public void removeCuidador(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("cuidadorUsername");
            String id = param.getValue().toString();
            cuidadorBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public String updateCuidador() {
        try {
            cuidadorBean.update(
                    currentCuidador.getUsername(),
                    currentCuidador.getPassword(),
                    currentCuidador.getNome(),
                    currentCuidador.getEmail());
            return "admin_list_all?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_profissional_update";
    }
    
    
    
    ////////ADMINISTRADOR

    public AdministradorDTO getNewAdministrador() {
        return newAdministrador;
    }

    public void setNewAdministrador(AdministradorDTO newAdministrador) {
        this.newAdministrador = newAdministrador;
    }
    
        public String createAdministrador() {
        try {
            administradorBean.create(newAdministrador.getUsername(),newAdministrador.getPassword(),newAdministrador.getNome(),newAdministrador.getEmail());
            newAdministrador.reset();
            return "admin_list_all?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }

    public AdministradorDTO getCurrentAdministrador() {
        return currentAdministrador;
    }

    public void setCurrentAdministrador(AdministradorDTO currentAdministrador) {
        this.currentAdministrador = currentAdministrador;
    }
        
    public void removeAdministrador(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("administradorUsername");
            String id = param.getValue().toString();
            administradorBean.remove(id);
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public String updateAdministrador() {
        try {
            administradorBean.update(
                    currentAdministrador.getUsername(),
                    currentAdministrador.getPassword(),
                    currentAdministrador.getNome(),
                    currentAdministrador.getEmail());
            return "admin_list_all?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_profissional_update";
    }
    
    

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    
}
