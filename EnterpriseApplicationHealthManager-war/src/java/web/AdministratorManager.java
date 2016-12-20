package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dtos.CuidadorDTO;
import dtos.ProfissionalSaudeDTO;
import dtos.UtenteDTO;
<<<<<<< HEAD
<<<<<<< HEAD
import ejbs.AdministratorBean;
import ejbs.CuidadorBean;
import ejbs.ProfissionalSaudeBean;
import ejbs.UtenteBean;
import entities.Administrator;
import entities.Cuidador;
import entities.ProfissionalSaude;
import entities.Utente;
import java.io.Console;
import java.util.List;
import javax.ejb.EJB;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

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
    private AdministratorBean adminBean;
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    
    
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
    
    public List<Administrator> getAllAdministrators() {
        try {
            return adminBean.getall();
        } catch (Exception e) {
=======
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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AdministratorManager {
   
    @EJB
    private UtenteBean utenteBean;
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;
    @EJB
    private CuidadorBean cuidadorBean;
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    
    
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
>>>>>>> origin/master
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }

    public AdministratorManager() {
    }
    

    public AdministratorManager(UtenteBean utenteBean) {
        this.utenteBean = utenteBean;
    }
    
}
