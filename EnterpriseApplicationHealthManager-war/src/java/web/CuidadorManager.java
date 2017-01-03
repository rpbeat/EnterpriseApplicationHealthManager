/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import dtos.CuidadorDTO;
import dtos.MaterialCapacitacaoDTO;
import dtos.ProcedimentoCuidadoDTO;
import dtos.UtenteDTO;
import ejbs.CuidadorBean;
import ejbs.ProcedimentoCuidadoBean;
import ejbs.UtenteBean;
import entities.EstadoProcedimento;
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
public class CuidadorManager {
    @EJB
    CuidadorBean cuidadorBean;
    @EJB
    ProcedimentoCuidadoBean procedimentoCuidadoBean;
    @EJB
    UtenteBean utenteBean;
    
    String currentCuidadorString;
    CuidadorDTO currentCuidadorDTO;
    
    UtenteDTO currentUtente;
    ProcedimentoCuidadoDTO newProcedimento;
    ProcedimentoCuidadoDTO currentProcedimento;
    
   private String selectedMaterial;
   private String selectedEstado;
   
    private UIComponent component;
    private static final Logger logger = Logger.getLogger("web.CuidadorManager");

    public CuidadorManager() {
        
        currentCuidadorDTO = new CuidadorDTO();
        currentUtente = new UtenteDTO();
        newProcedimento = new ProcedimentoCuidadoDTO();
        currentProcedimento = new ProcedimentoCuidadoDTO();

    }

    public ProcedimentoCuidadoDTO getCurrentProcedimento() {
        return currentProcedimento;
    }

    public void setCurrentProcedimento(ProcedimentoCuidadoDTO currentProcedimento) {
        this.currentProcedimento = currentProcedimento;
    }

    public String getSelectedEstado() {
        return selectedEstado;
    }

    public void setSelectedEstado(String selectedEstado) {
        this.selectedEstado = selectedEstado;
    }
    

    public void setCurrentCuidadorString(String currentCuidadorString) {
        this.currentCuidadorString = currentCuidadorString;
        currentCuidadorDTO = cuidadorBean.getCuidador(currentCuidadorString);
    }

    public CuidadorDTO getCurrentCuidadorDTO() {
        return currentCuidadorDTO;
    }

    public void setCurrentCuidadorDTO(CuidadorDTO currentCuidadorDTO) {
        this.currentCuidadorDTO = currentCuidadorDTO;
    }
    
    public List<UtenteDTO> getCurrentCuidadorUtentes() {
        try {
            System.out.println("GET: currentCuidador.getUsername : "+currentCuidadorDTO.getUsername());
            return cuidadorBean.getAllenrroledUtentes(currentCuidadorDTO.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }

    public UtenteDTO getCurrentUtente() {
        return currentUtente;
    }

    public void setCurrentUtente(UtenteDTO currentUtente) {
        this.currentUtente = currentUtente;
    }

    public ProcedimentoCuidadoDTO getNewProcedimento() {
        return newProcedimento;
    }

    public void setNewProcedimento(ProcedimentoCuidadoDTO procedimentoCuidadoDTO) {
        this.newProcedimento = procedimentoCuidadoDTO;
    }

    public String getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(String selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }
    
    public List<MaterialCapacitacaoDTO> getCurrentCuidadorMateriais() {
        try {
            return cuidadorBean.getAllenrroledMaterial(currentCuidadorDTO.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
    
    public EstadoProcedimento[] getEstados(){
        EstadoProcedimento[] estados = {EstadoProcedimento.A_iniciar, EstadoProcedimento.Cancelado, EstadoProcedimento.Concluido,EstadoProcedimento.Em_Curso};
        return estados;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
    
    
    
    ////////////////////////PROCEDIMENTOS

    public String createProcedimento() {
        try {
            System.err.println("CREATEEEEE:"+getSelectedEstado());
            procedimentoCuidadoBean.create(newProcedimento.getId(),currentCuidadorDTO.getUsername(),newProcedimento.getDescricao(),EstadoProcedimento.valueOf(getSelectedEstado()));
            procedimentoCuidadoBean.enrrolMaterialToProcedimento(Long.parseLong(getSelectedMaterial()), newProcedimento.getId());
            utenteBean.enrrolProcedimento(newProcedimento.getId(), currentUtente.getId());
            newProcedimento.reset();

            return "cuidador_create_procedimento?faces-redirect=true";
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
    
    public String updateProcedimento(){
        try {
            // long id, String tipo, String descricao, String link
            procedimentoCuidadoBean.update(currentProcedimento.getId(),currentProcedimento.getUserNameCuidador(),currentProcedimento.getDescricao(),EstadoProcedimento.valueOf(getSelectedEstado()));
            return "cuidador_create_procedimento?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter! Material may be used for somebody!", logger);
        }
        return "cuidador_procedimento_update";
    
    }
      
}
