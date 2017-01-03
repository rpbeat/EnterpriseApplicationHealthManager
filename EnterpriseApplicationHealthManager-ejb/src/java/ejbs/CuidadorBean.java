package ejbs;

import common.BasicAuth;
import dtos.CuidadorDTO;
import dtos.MaterialCapacitacaoDTO;
import dtos.ProcedimentoCuidadoDTO;
import dtos.UtenteDTO;
import entities.Cuidador;
import entities.MaterialCapacitacao;
import entities.ProcedimentoCuidado;
import entities.User;
import entities.Utente;
import exceptions.EntityAlreadyExistsException;
import exceptions.EntityDoesNotExistsException;
import exceptions.MyConstraintViolationException;
import exceptions.Utils;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.component.UIParameter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Param;

@Stateless
@Path("/cuidador")
public class CuidadorBean {

    @EJB
    private UtenteBean utenteBean;
    @EJB
    private ProcedimentoCuidadoBean procedimentoCuidadoBean;

    @PersistenceContext
    private EntityManager em;
//    private UtenteBean utenteBean;
//    private MaterialCapacitacaoBean materialCapacitacaoBean;

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

    @GET
    @RolesAllowed({"Administrador", "ProfissionalSaude", "Cuidador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("procedimentos/{id}")
    public List<ProcedimentoCuidadoDTO> getAllenrroledProcedimentosUtente(@Context HttpHeaders headers, @PathParam("id") long idUtente) {
        List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String username = BasicAuth.decodeUsername(authHeaders.get(0).toString());
        List<UtenteDTO> listaUtentes = getAllenrroledUtentes(username);
        Utente utente = em.find(Utente.class, idUtente);

        for (UtenteDTO utentedto : listaUtentes) {
            if (utentedto.getId() == utente.getId()) {
                return procedimentosToDTOs(utente.getProcedimentos());
            }
        }
        return null;
    }

    List<ProcedimentoCuidadoDTO> procedimentosToDTOs(List<ProcedimentoCuidado> procedimentos) {
        List<ProcedimentoCuidadoDTO> dtos = new ArrayList<>();
        for (ProcedimentoCuidado s : procedimentos) {
            dtos.add(procedimentoToDTO(s));
        }
        return dtos;
    }

    ProcedimentoCuidadoDTO procedimentoToDTO(ProcedimentoCuidado procedimento) {
        return new ProcedimentoCuidadoDTO(procedimento.getId(), procedimento.getUserNameCuidador(), procedimento.getDescricao(), materialToDTO(procedimento.getMaterialCapacitacao()));
    }

    @GET
    @RolesAllowed({"Administrador", "ProfissionalSaude", "Cuidador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("utentes")
    public List<UtenteDTO> getAllenrroledUtentes(@Context HttpHeaders headers) {
        List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String username = BasicAuth.decodeUsername(authHeaders.get(0).toString());
        return getAllenrroledUtentes(username);
    }

    public List<UtenteDTO> getAllenrroledUtentes(String username) {
        try {
            Cuidador cuidador = em.find(Cuidador.class, username);

            if (cuidador == null) {
                throw new EJBException();
            }
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
                throw new EntityDoesNotExistsException("Cuidador ou material não existentes");
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

    @GET
    @RolesAllowed({"Administrador", "ProfissionalSaude", "Cuidador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("material")
    public List<MaterialCapacitacaoDTO> getAllenrroledMaterial(@Context HttpHeaders headers) {
        List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String username = BasicAuth.decodeUsername(authHeaders.get(0).toString());
        return getAllenrroledMaterial(username);
    }

    public List<MaterialCapacitacaoDTO> getAllenrroledMaterial(String username) {
        try {
            Cuidador cuidador = em.find(Cuidador.class, username);

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

    @GET
    @RolesAllowed({"Administrador", "ProfissionalSaude"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("all")
    public List<CuidadorDTO> getAllDTO() {
        try {
            List<Cuidador> cuidadores = em.createNamedQuery("GetAllCuidadores").getResultList();
            return cuidadoresToDTOs(cuidadores);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
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

    @POST
    @RolesAllowed({"Administrador", "ProfissionalSaude", "Cuidador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("procedimentos/create")
    public String PostcreateProcedimento(@Context HttpHeaders headers,
            @FormParam("identificador") String identificador,
            @FormParam("descricao") String descricao,
            @FormParam("material") String idMaterial,
            @FormParam("idUtente") String idUtente) throws EntityDoesNotExistsException {
        try {

            List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
            String username = BasicAuth.decodeUsername(authHeaders.get(0).toString());

            if (descricao == "" || idMaterial == "" || identificador == "") {
                return null;
            }

            MaterialCapacitacao material = em.find(MaterialCapacitacao.class, Long.parseLong(idMaterial));
            if (material == null) {
                throw new EntityDoesNotExistsException("material não existente");
            }

            procedimentoCuidadoBean.create(identificador, username, descricao);
            procedimentoCuidadoBean.enrrolMaterialToProcedimento(Long.parseLong(idMaterial), identificador);
            utenteBean.enrrolProcedimento(identificador, Long.parseLong(idUtente));
        } catch (EntityDoesNotExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return "OK";
    }

    @DELETE
    @RolesAllowed({"Administrador", "ProfissionalSaude", "Cuidador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("procedimentos/delete/{id}/{idUtente}")
    public String removeProcedimento(@Context HttpHeaders headers, @PathParam("id") String id, @PathParam("idUtente") long idUtente) {
        try {
            List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
            String username = BasicAuth.decodeUsername(authHeaders.get(0).toString());

            List<UtenteDTO> listaUtentes = getAllenrroledUtentes(username);
            Utente utente = em.find(Utente.class, idUtente);

            for (UtenteDTO utentedto : listaUtentes) {
                if (utentedto.getId() == utente.getId()) {
                    utenteBean.removeEnrroledProdecimento(id, idUtente);
                    return "OK";
                }
            }
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return "OK";
    }

    @GET
    @RolesAllowed({"Administrador", "ProfissionalSaude", "Cuidador"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("login")
    public CuidadorDTO login(@Context HttpHeaders headers) {
        List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String username = BasicAuth.decodeUsername(authHeaders.get(0).toString());
        String password = BasicAuth.decodePassword(authHeaders.get(0).toString());
        
        

        Cuidador cuidador = em.find(Cuidador.class, username);
        
        System.out.println(authHeaders.get(0)+"username: "+username + " password: "+hashPassword(password) + "   "+cuidador.getPassword());

        if (cuidador == null) {
            return null;
        }
        if (cuidador.getPassword().equals(hashPassword(password))) {
            return cuidadorToDTO(cuidador);
        }
        return null;
    }
    
    private String hashPassword(String password) {
       char[] encoded = null;
        try {
        ByteBuffer passwdBuffer =
        Charset.defaultCharset().encode(CharBuffer.wrap(password));
        byte[] passwdBytes = passwdBuffer.array();
        MessageDigest mdEnc = MessageDigest.getInstance("SHA-256");
        mdEnc.update(passwdBytes, 0, password.toCharArray().length);
        encoded = new BigInteger(1, mdEnc.digest()).toString(16).toCharArray();
        } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(encoded);

    }

}
