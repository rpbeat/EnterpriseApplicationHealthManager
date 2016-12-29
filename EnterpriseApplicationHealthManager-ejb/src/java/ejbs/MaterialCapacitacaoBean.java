/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.MaterialCapacitacaoDTO;
import entities.MaterialCapacitacao;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author rpbeat
 */
@Stateless
public class MaterialCapacitacaoBean {
    
   @PersistenceContext
    private EntityManager em; 
   
   public void create(String id, String tipo,String descricao, String link) 
            throws EntityAlreadyExistsException, MyConstraintViolationException{
        try {
            if (em.find(MaterialCapacitacao.class, id) != null) {
                throw new EntityAlreadyExistsException("A Training Material with that id already exists.");
            }
            
            em.persist(new MaterialCapacitacao(id, tipo, descricao, link));
        } catch (EntityAlreadyExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
   
    public void update(String id, String tipo,String descricao, String link) 
        throws EntityDoesNotExistsException, MyConstraintViolationException{
        try {
            MaterialCapacitacao materialCapacitacao = em.find(MaterialCapacitacao.class, id);
            if (materialCapacitacao == null) {
                throw new EntityDoesNotExistsException("There is no Training Material with that id.");
            }
            
            materialCapacitacao.setId(id);
            materialCapacitacao.setTipo(tipo);
            materialCapacitacao.setDescricao(descricao);
            materialCapacitacao.setLink(link);
            em.merge(materialCapacitacao);
            
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void remove(String id) throws EntityDoesNotExistsException {
        try {
            MaterialCapacitacao materialCapacitacao = em.find(MaterialCapacitacao.class, id);
            if (materialCapacitacao == null) {
                throw new EntityDoesNotExistsException("There is no Training Material with that id.");
            }
            
            em.remove(materialCapacitacao);
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
     public List<MaterialCapacitacao> getAll(){
        List<MaterialCapacitacao> materialCapacitacao = em.createNamedQuery("GetAllMaterialCapacitacao").getResultList();
         return materialCapacitacao;
    }
     
   public List<MaterialCapacitacaoDTO> getAllDTO() {
        try {
            List<MaterialCapacitacao> materialCapacitacao = (List<MaterialCapacitacao>) em.createNamedQuery("GetAllMaterialCapacitacao").getResultList();
            return materialToDTOs(materialCapacitacao);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    List<MaterialCapacitacaoDTO> materialToDTOs(List<MaterialCapacitacao> material) {
        List<MaterialCapacitacaoDTO> dtos = new ArrayList<>();
        for (MaterialCapacitacao s : material) {
            dtos.add(materialToDTO(s));
        }
        return dtos;
    }
    
    MaterialCapacitacaoDTO materialToDTO(MaterialCapacitacao material) {
        return new MaterialCapacitacaoDTO(material.getId(),material.getTipo(),material.getLink(),material.getDescricao());
    }
}
