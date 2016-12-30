package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dtos.AdministradorDTO;
import dtos.CuidadorDTO;
import dtos.MaterialCapacitacaoDTO;
import dtos.ProcedimentoCuidadoDTO;
import dtos.ProfissionalSaudeDTO;
import dtos.UtenteDTO;
import ejbs.AdministradorBean;
import ejbs.CuidadorBean;
import ejbs.MaterialCapacitacaoBean;
import ejbs.ProcedimentoCuidadoBean;
import ejbs.ProfissionalSaudeBean;
import ejbs.UtenteBean;
import entities.Cuidador;
import entities.MaterialCapacitacao;
import entities.ProcedimentoCuidado;
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
    @EJB
    private MaterialCapacitacaoBean materialCapacitacaoBean;
    @EJB
    private ProcedimentoCuidadoBean procedimentoCuidadoBean;

    private UtenteDTO newUtente;
    private UtenteDTO currentUtente; 
    
    private ProfissionalSaudeDTO newProfissional;
    private ProfissionalSaudeDTO currentProfissional;
    
    private CuidadorDTO newCuidador;
    private CuidadorDTO currentCuidador;
    
    private AdministradorDTO newAdministrador;
    private AdministradorDTO currentAdministrador;
    
    private MaterialCapacitacaoDTO newMaterialCapacitacao;
    private MaterialCapacitacaoDTO currentMaterialCapacitacao;
    
    private ProcedimentoCuidadoDTO newProcedimento;
    private ProcedimentoCuidadoDTO currentProcedimento;

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
        newMaterialCapacitacao = new MaterialCapacitacaoDTO();
        currentMaterialCapacitacao = new MaterialCapacitacaoDTO();
        currentProcedimento = new ProcedimentoCuidadoDTO();
        newProcedimento = new ProcedimentoCuidadoDTO();
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
            //String nome, String email,int contacto,String morada
            utenteBean.create(newUtente.getNome(),newUtente.getEmail(),newUtente.getContacto(),newUtente.getMorada());
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

    public UtenteDTO getCurrentUtente() {
        return currentUtente;
    }

    public void setCurrentUtente(UtenteDTO currentUtente) {
        this.currentUtente = currentUtente;
    }
    
    public void removeUtente(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("utenteUsername");
            String id = param.getValue().toString();
            utenteBean.remove(Long.parseLong(id));
        } catch (EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public String updateUtente() {
        try {
            // long idUtente, String password, String nome, String email,int contacto,String morada
            utenteBean.update(currentUtente.getId(),
                    currentUtente.getNome(), 
                    currentUtente.getEmail(), 
                    currentUtente.getContacto(),
                    currentUtente.getMorada());
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
            //String nome, String email, int contacto, String morada, String username, String password
            profissionalSaudeBean.create(newProfissional.getNome(),
                    newProfissional.getEmail(),
                    newProfissional.getContacto(),
                    newProfissional.getMorada(),
                    newProfissional.getUsername(),
                    newProfissional.getPassword());
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
            profissionalSaudeBean.update(currentProfissional.getNome(),
                    currentProfissional.getEmail(),
                    currentProfissional.getContacto(),
                    currentProfissional.getMorada(),
                    currentProfissional.getUsername(),
                    currentProfissional.getPassword());
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
            cuidadorBean.create(newCuidador.getNome(),
                    newCuidador.getEmail(),
                    newCuidador.getContacto(),
                    newCuidador.getMorada(),
                    newCuidador.getUsername(),
                    newCuidador.getPassword());
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
                    currentCuidador.getNome(),
                    currentCuidador.getEmail(),
                    currentCuidador.getContacto(),
                    currentCuidador.getMorada(),
                    currentCuidador.getUsername(),
                    currentCuidador.getPassword());
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
            administradorBean.create(newAdministrador.getNome(),
                    newAdministrador.getEmail(),
                    newAdministrador.getContacto(),
                    newAdministrador.getMorada(),
                    newAdministrador.getUsername(),
                    newAdministrador.getPassword());
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
            administradorBean.update(newAdministrador.getNome(),
                    newAdministrador.getEmail(),
                    newAdministrador.getContacto(),
                    newAdministrador.getMorada(),
                    newAdministrador.getUsername(),
                    newAdministrador.getPassword());
            return "admin_list_all?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_profissional_update";
    }
    
    public List<UtenteDTO> getCurrentCuidadorUtentes() {
        try {
            System.out.println("GET: currentCuidador.getUsername : "+currentCuidador.getUsername());
            return cuidadorBean.getAllenrroledUtentes(currentCuidador.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
    public String enrrolUtenteToCuidador(ActionEvent event){
         try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("utenteUsername");
            String id = param.getValue().toString();
            cuidadorBean.enrollUtente(currentCuidador.getUsername(), Long.parseLong(id));
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
         return "admin_enrrol_utente?faces-redirect=true";
    }
    
    public void removeEnrroledUtente(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("utenteUsername");
            String id = param.getValue().toString();
            cuidadorBean.removeEnrroledUtente(currentCuidador.getUsername(), Long.parseLong(id));
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    ///////////////MATERIALCAPACITAÇAO

    public MaterialCapacitacaoDTO getNewMaterialCapacitacao() {
        return newMaterialCapacitacao;
    }

    public void setNewMaterialCapacitacao(MaterialCapacitacaoDTO newMaterialCapacitacao) {
        this.newMaterialCapacitacao = newMaterialCapacitacao;
    }

    public MaterialCapacitacaoDTO getCurrentMaterialCapacitacao() {
        return currentMaterialCapacitacao;
    }

    public void setCurrentMaterialCapacitacao(MaterialCapacitacaoDTO currentMaterialCapacitacao) {
        this.currentMaterialCapacitacao = currentMaterialCapacitacao;
    }
    
    ///////////////////// MATERIAL
    public String createMaterial() {
        try {
            materialCapacitacaoBean.create(newMaterialCapacitacao.getTipo(),newMaterialCapacitacao.getDescricao(),newMaterialCapacitacao.getLink());
            newMaterialCapacitacao.reset();
            return "admin_create_material?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }
    
    public List<MaterialCapacitacaoDTO> getAllMaterialDTO(){
        try {
            return materialCapacitacaoBean.getAllDTO();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    public void removeMaterial(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("materialId");
            String id = param.getValue().toString();
            materialCapacitacaoBean.remove(Long.parseLong(id));
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public List<MaterialCapacitacaoDTO> getCurrentCuidadorMateriais() {
        try {
            return cuidadorBean.getAllenrroledMaterial(currentCuidador.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
    public void enrrolMaterialToCuidador(ActionEvent event){
         try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("materialId");
            String id = param.getValue().toString();
            cuidadorBean.enrollMaterial(id, currentCuidador.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    public void removeEnrroledMaterial(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("materialId");
            String id = param.getValue().toString();
            cuidadorBean.removeEnrroledMaterial(id,currentCuidador.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
        ////////////////////////PROCEDIMENTOS
    public ProcedimentoCuidadoDTO getNewProcedimento() {    
        return newProcedimento;
    }

    public void setNewProcedimento(ProcedimentoCuidadoDTO newProcedimento) {
        this.newProcedimento = newProcedimento;
    }

    public ProcedimentoCuidadoDTO getCurrentProcedimento() {
        return currentProcedimento;
    }

    public void setCurrentProcedimento(ProcedimentoCuidadoDTO currentProcedimento) {    
        this.currentProcedimento = currentProcedimento;
    }

    public String createProcedimento() {
        try {
            procedimentoCuidadoBean.create(newProcedimento.getId(),currentCuidador.getUsername(),newProcedimento.getDescricao());
            utenteBean.enrrolProcedimento(newProcedimento.getId(), currentUtente.getId());
            newProcedimento.reset();
            
            
            return "admin_create_procedimento?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }
    
    public List<ProcedimentoCuidadoDTO> getAllProcedimentoDTO(){
        try {
            return procedimentoCuidadoBean.getAllDTO();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    public List<ProcedimentoCuidadoDTO> getAllEnrroledProcedimentos(){
        try {
            return utenteBean.getAllProcedimentos(currentUtente.getId());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
    public void removeEnrroledProcedimento(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("procedimentoId");
            String id = param.getValue().toString();
            utenteBean.removeEnrroledProdecimento(id,currentUtente.getId());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }
    
    

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    
}
