/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.AdministradorDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Administrador;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
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
@Path("/administrador")
public class AdministradorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String nome, String email, int contacto, String morada, String username, String password) {
        try {
            if (em.find(Administrador.class, username) != null) {
                return;
            }
            em.persist(new Administrador(username, password, nome, email, contacto, morada));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    @GET
    @RolesAllowed({"Administrador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<AdministradorDTO> getAllDTO() {
        try {
            List<Administrador> aministradores = (List<Administrador>) em.createNamedQuery("GetAllAdministradores").getResultList();
            return administradoresToDTOs(aministradores);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    List<AdministradorDTO> administradoresToDTOs(List<Administrador> administradores) {
        List<AdministradorDTO> dtos = new ArrayList<>();
        for (Administrador s : administradores) {
            dtos.add(cuidadorToDTO(s));
        }
        return dtos;
    }

    AdministradorDTO cuidadorToDTO(Administrador cuidador) {
        return new AdministradorDTO(cuidador.getNome(), cuidador.getEmail(), cuidador.getContacto(), cuidador.getMorada(), cuidador.getUsername(), cuidador.getPassword());
    }

    public void remove(String username) throws EntityDoesNotExistsException {
        try {
            Administrador administrador = em.find(Administrador.class, username);
            if (administrador == null) {
                throw new EntityDoesNotExistsException("There is no administrador with that username.");
            }

            em.remove(administrador);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(String nome, String email, int contacto, String morada, String username, String password)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            Administrador admin = em.find(Administrador.class, username);
            if (admin == null) {
                throw new EntityDoesNotExistsException("There is no administrador with that username.");
            }
            admin.setPassword(password);
            admin.setNome(nome);
            admin.setEmail(email);
            admin.setContacto(contacto);
            admin.setMorada(morada);
            admin.setUsername(username);
            em.merge(admin);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
