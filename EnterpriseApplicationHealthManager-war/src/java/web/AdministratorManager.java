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
import entities.ProfissionalSaude;
import entities.Utente;
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

    private UIComponent component;
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");

    public AdministratorManager() {
        newUtente = new UtenteDTO();
        newProfissional = new ProfissionalSaudeDTO();
        currentProfissional = new ProfissionalSaudeDTO();
        newCuidador = new CuidadorDTO();
        currentCuidador = new CuidadorDTO();
        newAdministrador = new AdministradorDTO();
        currentAdministrador = new AdministradorDTO();
        newMaterialCapacitacao = new MaterialCapacitacaoDTO();
        currentMaterialCapacitacao = new MaterialCapacitacaoDTO();

    }

    //////////////// UTENTE
    public List<UtenteDTO> getAllUtentesDTO() {
        try {
            return utenteBean.getAllDTO();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter1!", logger);
        }
        return null;
    }

    public String createUtente() {
        try {
            //String nome, String email,int contacto,String morada
            utenteBean.create(newUtente.getNome(), newUtente.getEmail(), newUtente.getContacto(), newUtente.getMorada());
            newUtente.reset();
            return "admin_view?faces-redirect=true";
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
            return "admin_view?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_utente_update";
    }

    //////////PROFISSIONALSAUDE
    public List<ProfissionalSaudeDTO> getAllProfissionaisDTO() {
        try {
            return profissionalSaudeBean.getAllDTO();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }

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
            return "admin_view?faces-redirect=true";
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
            return "admin_view?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_profissional_update";
    }

    ///////CUIDADOR
    public List<CuidadorDTO> getAllCuidadoresDTO() {
        try {
            return cuidadorBean.getAllDTO();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }

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
            return "admin_view?faces-redirect=true";
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
            return "admin_view?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_profissional_update";
    }

    ////////ADMINISTRADOR
    public List<AdministradorDTO> getAllAdministradoresDTO() {
        try {
            return administradorBean.getAllDTO();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return null;
    }
    
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
            return "admin_view?faces-redirect=true";
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
            administradorBean.update(currentAdministrador.getNome(),
                    currentAdministrador.getEmail(),
                    currentAdministrador.getContacto(),
                    currentAdministrador.getMorada(),
                    currentAdministrador.getUsername(),
                    currentAdministrador.getPassword());
            return "admin_view?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_profissional_update";
    }

    public List<UtenteDTO> getCurrentCuidadorUtentes() {
        try {
            return cuidadorBean.getAllenrroledUtentes(currentCuidador.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
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

    public String createMaterial() {
        try {
            materialCapacitacaoBean.create(newMaterialCapacitacao.getTipo(), newMaterialCapacitacao.getDescricao(), newMaterialCapacitacao.getLink());
            newMaterialCapacitacao.reset();
            return "admin_create_material?faces-redirect=true";
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", component, logger);
        }
        return null;
    }

    public List<MaterialCapacitacaoDTO> getAllMaterialDTO() {
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

    public void enrrolMaterialToCuidador(ActionEvent event) {
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

    public void removeEnrroledMaterial(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("materialId");
            String id = param.getValue().toString();
            cuidadorBean.removeEnrroledMaterial(id, currentCuidador.getUsername());
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
    }

    public String updateMaterial() {
        try {
            // long id, String tipo, String descricao, String link
            materialCapacitacaoBean.update(currentMaterialCapacitacao.getId(), currentMaterialCapacitacao.getTipo(), currentMaterialCapacitacao.getDescricao(), currentMaterialCapacitacao.getLink());
            return "admin_create_material?faces-redirect=true";

        } catch (EntityDoesNotExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), logger);
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
        }
        return "admin_material_update";
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
}
