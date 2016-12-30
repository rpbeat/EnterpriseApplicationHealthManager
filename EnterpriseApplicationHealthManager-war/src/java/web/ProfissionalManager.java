/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import static com.sun.codemodel.JExpr.component;
import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import dtos.CuidadorDTO;
import dtos.MaterialCapacitacaoDTO;
import dtos.ProcedimentoCuidadoDTO;
import dtos.UtenteDTO;
import ejbs.CuidadorBean;
import ejbs.MaterialCapacitacaoBean;
import ejbs.ProcedimentoCuidadoBean;
import ejbs.UtenteBean;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

/**
 *
 * @author rpbeat
 */
@ManagedBean
@SessionScoped
public class ProfissionalManager {
    
    @EJB
    private UtenteBean utenteBean;
    @EJB
    private CuidadorBean cuidadorBean;
    @EJB
    private MaterialCapacitacaoBean materialCapacitacaoBean;
    @EJB
    private ProcedimentoCuidadoBean procedimentoCuidadoBean;
    
    private UtenteDTO newUtente;
    private UtenteDTO currentUtente;
    
    private CuidadorDTO newCuidador;
    private CuidadorDTO currentCuidador;
    
    private MaterialCapacitacaoDTO newMaterialCapacitacao;
    private MaterialCapacitacaoDTO currentMaterialCapacitacao;
    
    private ProcedimentoCuidadoDTO newProcedimento;
    private ProcedimentoCuidadoDTO currentProcedimento;
    
    private String selectedMaterial;
    
    private UIComponent component;
    private static final Logger logger = Logger.getLogger("web.ProfissionalManager");

    public ProfissionalManager() {        
        newMaterialCapacitacao = new MaterialCapacitacaoDTO();
        currentMaterialCapacitacao = new MaterialCapacitacaoDTO();
        currentProcedimento = new ProcedimentoCuidadoDTO();
        newProcedimento = new ProcedimentoCuidadoDTO();
        newCuidador = new CuidadorDTO();
        currentCuidador = new CuidadorDTO();
        newUtente = new UtenteDTO();
        currentUtente = new UtenteDTO();
        
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
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

    public CuidadorDTO getNewCuidador() {
        return newCuidador;
    }

    public void setNewCuidador(CuidadorDTO newCuidador) {
        this.newCuidador = newCuidador;
    }

    public CuidadorDTO getCurrentCuidador() {
        return currentCuidador;
    }

    public void setCurrentCuidador(CuidadorDTO currentCuidador) {
        this.currentCuidador = currentCuidador;
    }

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

    public String getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(String selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }
    
    ///////////////////// CUIDADORES
    
    public  List<UtenteDTO> getAllUtentesDTO(){
        try{
            System.out.println(""+utenteBean.getAllDTO().toString());
            return utenteBean.getAllDTO();
        }catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter1!", logger);
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
    
    ///////CUIDADOR

    public String createCuidador() {
        try {
            cuidadorBean.create(newCuidador.getNome(),
                    newCuidador.getEmail(),
                    newCuidador.getContacto(),
                    newCuidador.getMorada(),
                    newCuidador.getUsername(),
                    newCuidador.getPassword());
            newCuidador.reset();
            return "profissional_view?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
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
            return "profissional_view?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
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
       
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "profissional_enrrol_utente?faces-redirect=true";
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
    
    
  //////////////// UTENTE
    public String createUtente() {
        try {
            //String nome, String email,int contacto,String morada
            utenteBean.create(newUtente.getNome(),newUtente.getEmail(),newUtente.getContacto(),newUtente.getMorada());
            newUtente.reset();
            return "profissional_view?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
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
            return "profissional_view?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_utente_update";
    }
    

    ///////////////////// MATERIAL
    public String createMaterial() {
        try {
            materialCapacitacaoBean.create(newMaterialCapacitacao.getTipo(),newMaterialCapacitacao.getDescricao(),newMaterialCapacitacao.getLink());
            newMaterialCapacitacao.reset();
            return "profissional_create_material?faces-redirect=true";
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
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
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

    public String createProcedimento() {
        try {
            procedimentoCuidadoBean.create(newProcedimento.getId(),currentCuidador.getUsername(),newProcedimento.getDescricao());
            procedimentoCuidadoBean.enrrolMaterialToProcedimento(Long.parseLong(getSelectedMaterial()), newProcedimento.getId());
            utenteBean.enrrolProcedimento(newProcedimento.getId(), currentUtente.getId());
            newProcedimento.reset();

            
            return "profissional_create_procedimento?faces-redirect=true";
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
}