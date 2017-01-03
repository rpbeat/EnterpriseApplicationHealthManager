/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CuidadorDTO;
import dtos.PerguntaDTO;
import dtos.ProfissionalSaudeDTO;
import dtos.QuestionarioDTO;
import entities.Cuidador;
import entities.Pergunta;
import entities.ProfissionalSaude;
import entities.Questionario;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("/profissionalsaude")
public class ProfissionalSaudeBean {
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private QuestionarioBean questionarioBean;
    
    public ProfissionalSaudeDTO getProfissional(String usernameProfissional){
         try {
            ProfissionalSaude profissional = em.find(ProfissionalSaude.class, usernameProfissional);
            if (profissional == null) {
                    throw new EJBException();
                }
            return profissionalToDTO(profissional);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    
    public List<QuestionarioDTO> getEnrroledQuestionarios(String usernameProfissional){
       List<Questionario> questionarios = questionarioBean.getAll();
       List<QuestionarioDTO> questionarioDTOs = new LinkedList<>();
       for(Questionario s : questionarios){
           if(s.getUserNameProfissional().equals(usernameProfissional)){
               questionarioDTOs.add(questionarioToDTO(s));
           }
       }
       return questionarioDTOs;
    }
    
    public void create(String nome, String email, int contacto, String morada, String username, String password) {
        try {
            if(em.find(ProfissionalSaude.class, username) != null){
                return;
            }
            
            em.persist(new ProfissionalSaude(username, password, nome, email, contacto, morada));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<QuestionarioDTO> getAllQuestionarios(String userName) throws EntityDoesNotExistsException{
        try {
            ProfissionalSaude profissional = em.find(ProfissionalSaude.class, userName);
            if (profissional == null) {
                throw new EntityDoesNotExistsException("There is no profissional with that username.");
            }
            return questionariosToDTO(profissional.getQuestionarios());
        
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<ProfissionalSaude> getAll(){
        List<ProfissionalSaude> ProfissionaisSaude = em.createNamedQuery("GetAllProfissionalSaude").getResultList();
         return ProfissionaisSaude;
    }
    
    @GET
    @RolesAllowed({"Administrador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<ProfissionalSaudeDTO> getAllDTO() {
        try {
            List<ProfissionalSaude> profissionais = em.createNamedQuery("GetAllProfissionalSaude").getResultList();
            return profissionaisToDTOs(profissionais);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    List<ProfissionalSaudeDTO> profissionaisToDTOs(List<ProfissionalSaude> profissionais) {
        List<ProfissionalSaudeDTO> dtos = new ArrayList<>();
        for (ProfissionalSaude s : profissionais) {
            dtos.add(profissionalToDTO(s));
        }
        return dtos;
    }
    
    ProfissionalSaudeDTO profissionalToDTO(ProfissionalSaude profissional) {
        return new ProfissionalSaudeDTO(profissional.getNome(), profissional.getEmail(), profissional.getContacto(), profissional.getMorada(), profissional.getUsername(), profissional.getPassword());
    }

    public void remove(String username) throws EntityDoesNotExistsException {
        try {
            ProfissionalSaude profissional = em.find(ProfissionalSaude.class, username);
            if (profissional == null) {
                throw new EntityDoesNotExistsException("There is no profissional with that username.");
            }
            
            em.remove(profissional);
        
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
     public void update(String nome, String email, int contacto, String morada, String username, String password) 
        throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            ProfissionalSaude profissional = em.find(ProfissionalSaude.class, username);
            if (profissional == null) {
                throw new EntityDoesNotExistsException("There is no utente with that username.");
            }
            
            profissional.setPassword(password);
            profissional.setNome(nome);
            profissional.setEmail(email);
            profissional.setContacto(contacto);
            profissional.setMorada(morada);
            profissional.setUsername(username);
            em.merge(profissional);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
      List<QuestionarioDTO> questionariosToDTO(List<Questionario> questionarios) {
        if (questionarios == null) {
            return null;
        }
        List<QuestionarioDTO> dtos = new ArrayList<>();
        for (Questionario s : questionarios) {
            dtos.add(questionarioToDTO(s));
        }
        return dtos;
    }
     
     QuestionarioDTO questionarioToDTO(Questionario questionario) {
        return new QuestionarioDTO(questionario.getPerguntas(),questionario.getUserNameCuidador(),questionario.getUserNameProfissional(),questionario.getId(),questionario.getDate());
    }
    
    List<PerguntaDTO> perguntasToDTO(List<Pergunta> perguntas) {
        if (perguntas == null) {
            return null;
        }
        List<PerguntaDTO> dtos = new ArrayList<>();
        for (Pergunta s : perguntas) {
            dtos.add(perguntaToDTO(s));
        }
        return dtos;
    }
    PerguntaDTO perguntaToDTO(Pergunta pergunta) {
        return new PerguntaDTO(pergunta.getDescricao());
    }
}
