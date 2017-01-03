/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;


import dtos.PerguntaDTO;
import dtos.QuestionarioDTO;
import entities.Pergunta;
import entities.ProfissionalSaude;
import entities.Questionario;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Path;

/**
 *
 * @author rpbeat
 */
@Stateless
public class QuestionarioBean {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;
    
   public void create(List<Pergunta> perguntas, String userNameCuidador, String userNameProfissional) throws MyConstraintViolationException {
        try {
            Questionario questionario;
            questionario = new Questionario(perguntas,userNameCuidador,userNameProfissional);
            
            //ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, userNameProfissional);
            //profissionalSaude.addQuestionario(questionario);
            System.err.println("Cuidador "+userNameCuidador+"Profissional "+userNameProfissional);
            em.persist(new Questionario(perguntas,userNameCuidador,userNameProfissional));
            
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
   public List<Questionario> getAll(){
        List<Questionario> questionarios = em.createNamedQuery("GetAllQuestionarios").getResultList();
         return questionarios;
    }
   

    public void remove(long id) throws EntityDoesNotExistsException {
        try {
            Questionario questionario = em.find(Questionario.class, id);
            if (questionario == null) {
                throw new EntityDoesNotExistsException("There is no questionario with that id.");
            }

            em.remove(questionario);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void update(long id, Questionario questionario)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Questionario questionario1 = em.find(Questionario.class, id);
            if (questionario1 == null) {
                throw new EntityDoesNotExistsException("There is no cuidador with that username.");
            }

            questionario1.setPerguntas(questionario.getPerguntas());
            questionario1.setUserNameCuidador(questionario.getUserNameCuidador());
            questionario1.setUserNameProfissional(questionario.getUserNameProfissional());
            em.merge(questionario1);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public QuestionarioDTO getQuestionario(long id){
        try {
            Questionario questionario = em.find(Questionario.class, id);
            if (questionario == null) {
                    throw new EJBException();
                }
            return questionarioToDTO(questionario);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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
