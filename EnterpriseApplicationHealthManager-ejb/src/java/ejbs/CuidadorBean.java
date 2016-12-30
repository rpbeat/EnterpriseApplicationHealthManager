/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CuidadorDTO;
import dtos.MaterialCapacitacaoDTO;
import dtos.ProfissionalSaudeDTO;
import dtos.UtenteDTO;
import entities.Cuidador;
import entities.MaterialCapacitacao;
import entities.ProfissionalSaude;
import entities.Utente;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
public class CuidadorBean {

    @PersistenceContext
    private EntityManager em;
    private UtenteBean utenteBean;
    private MaterialCapacitacaoBean materialCapacitacaoBean;

    public void create(String nome, String email, int contacto, String morada, String username, String password) {
        try {
            if (em.find(Cuidador.class, username) != null) {
                return;
            }
            em.persist(new Cuidador(username, password, nome, email, contacto, morada));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<UtenteDTO> getAllenrroledUtentes(String userName) {
        try {
            Cuidador cuidador = em.find(Cuidador.class, userName);

            if (cuidador == null) {
                throw new EJBException();
            }
            System.err.println("GETERROLEDDD" + cuidador.getUsername() + "List: " + cuidador.getUtentes().size());
            return utentesToDTOs(cuidador.getUtentes());

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<UtenteDTO> utentesToDTOs(List<Utente> utentes) {
        if (utentes == null) {
            return null;
        }
        List<UtenteDTO> dtos = new ArrayList<>();
        for (Utente s : utentes) {
            dtos.add(utenteToDTO(s));
        }
        return dtos;
    }

    private UtenteDTO utenteToDTO(Utente utente) {
        return new UtenteDTO(utente.getId(),
                utente.getNome(),
                utente.getEmail(),
                utente.getContacto(),
                utente.getMorada());
    }

    public void enrollUtente(String usernameCuidador, long idUtente)
            throws EntityAlreadyExistsException, EntityDoesNotExistsException {
        try {
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            Utente utente = em.find(Utente.class, idUtente);

            if (cuidador == null || utente == null) {
                throw new EntityDoesNotExistsException("Cuidador ou utente não existentes");
            }

            List<Utente> list = cuidador.getUtentes();
            if (list.contains(utente)) {
                throw new EntityAlreadyExistsException("O utente já existe neste cuidador");
            }
            if (checkUtenteHasCuidador(utente)) {
                throw new EntityAlreadyExistsException("Utente já tem um cuidador associado");
            }

            cuidador.addUtente(utente);
        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public boolean checkUtenteHasCuidador(Utente utente) {
        List<Cuidador> cuidadores = getAll();
        for (Cuidador cuidador : cuidadores) {
            if (cuidador.getUtentes().contains(utente)) {
                return true;
            }
        }
        return false;
    }

    public void removeEnrroledUtente(String usernameCuidador, long idUtente) {
        try {
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            Utente utente = em.find(Utente.class, idUtente);

            if (cuidador == null || utente == null) {
                throw new EJBException();
            }
            cuidador.removeUtente(utente);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void enrollMaterial(String idMaterial, String usernameCuidador)
            throws EntityAlreadyExistsException, EntityDoesNotExistsException {
        try {
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            MaterialCapacitacao material = em.find(MaterialCapacitacao.class, Long.parseLong(idMaterial));

            if (cuidador == null || material == null) {
                throw new EntityDoesNotExistsException("Cuidador ou utente não existentes");
            }

            List<MaterialCapacitacao> list = cuidador.getMateriais();
            if (list != null) {
                if (list.contains(material)) {
                    throw new EntityAlreadyExistsException("Material já existente neste cuidador!");
                }
                cuidador.addMaterial(material);
            }

        } catch (EntityAlreadyExistsException | EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<MaterialCapacitacaoDTO> getAllenrroledMaterial(String userName) {
        try {
            Cuidador cuidador = em.find(Cuidador.class, userName);

            if (cuidador == null) {
                throw new EJBException();
            }
            return materialToDTOs(cuidador.getMateriais());

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    List<MaterialCapacitacaoDTO> materialToDTOs(List<MaterialCapacitacao> material) {
        if (material == null) {
            return null;
        }
        List<MaterialCapacitacaoDTO> dtos = new ArrayList<>();
        for (MaterialCapacitacao s : material) {
            dtos.add(materialToDTO(s));
        }
        return dtos;
    }

    MaterialCapacitacaoDTO materialToDTO(MaterialCapacitacao material) {
        return new MaterialCapacitacaoDTO(material.getId(), material.getTipo(), material.getLink(), material.getDescricao());
    }

    public void removeEnrroledMaterial(String idMaterial, String usernameCuidador) {
        try {
            Cuidador cuidador = em.find(Cuidador.class, usernameCuidador);
            MaterialCapacitacao material = em.find(MaterialCapacitacao.class, Long.parseLong(idMaterial));

            if (cuidador == null || material == null) {
                throw new EJBException();
            }
            cuidador.removeMaterial(material);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<Cuidador> getAll() {
        List<Cuidador> cuidadores = em.createNamedQuery("GetAllCuidadores").getResultList();
        return cuidadores;
    }

    //@GET
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    //@Path("all")
    public List<CuidadorDTO> getAllDTO() {
        //try {
        List<Cuidador> cuidadores = (List<Cuidador>) em.createNamedQuery("GetAllCuidadores").getResultList();
        return cuidadoresToDTOs(cuidadores);
        // } catch (Exception e) {
        //   throw new EJBException(e.getMessage());
        //}
    }

    List<CuidadorDTO> cuidadoresToDTOs(List<Cuidador> cuidadores) {
        if (cuidadores == null) {
            return null;
        }
        List<CuidadorDTO> dtos = new ArrayList<>();
        for (Cuidador s : cuidadores) {
            dtos.add(cuidadorToDTO(s));
        }
        return dtos;
    }

    CuidadorDTO cuidadorToDTO(Cuidador cuidador) {
        return new CuidadorDTO(cuidador.getNome(),
                cuidador.getEmail(),
                cuidador.getContacto(),
                cuidador.getMorada(),
                cuidador.getUsername(),
                cuidador.getPassword());

    }

    public void remove(String username) throws EntityDoesNotExistsException {
        try {
            Cuidador cuidador = em.find(Cuidador.class, username);
            if (cuidador == null) {
                throw new EntityDoesNotExistsException("There is no cuidador with that username.");
            }

            em.remove(cuidador);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(String nome, String email, int contacto, String morada, String username, String password)
            throws EntityDoesNotExistsException, MyConstraintViolationException {
        try {
            System.out.println("USERNAME: " + username);
            Cuidador cuidador = em.find(Cuidador.class, username);
            if (cuidador == null) {
                throw new EntityDoesNotExistsException("There is no cuidador with that username.");
            }

            cuidador.setPassword(password);
            cuidador.setNome(nome);
            cuidador.setEmail(email);
            cuidador.setContacto(contacto);
            cuidador.setMorada(morada);
            //cuidador.setUsername(username);
            em.merge(cuidador);

        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(Utils.getConstraintViolationMessages(e));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

}
