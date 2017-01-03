/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.EstadoProcedimento;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rpbeat
 */
@XmlRootElement(name = "Procedimento")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedimentoCuidadoDTO {
    private String id;
    private String userNameCuidador;
    private String descricao;
    private EstadoProcedimento estado;
    private MaterialCapacitacaoDTO materialCapacitacaoDTO;
    private String date;

    public ProcedimentoCuidadoDTO(String id, String userNameCuidador, String descricao, MaterialCapacitacaoDTO materialToDTO, EstadoProcedimento estado, String date) {
        this.id = id;
        this.userNameCuidador = userNameCuidador;
        this.descricao = descricao;
        this.materialCapacitacaoDTO = materialToDTO;
        this.estado = estado;
        this.date = date;
    }

    public ProcedimentoCuidadoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNameCuidador() {
        return userNameCuidador;
    }

    public void setUserNameCuidador(String userNameCuidador) {
        this.userNameCuidador = userNameCuidador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void reset(){
        setDescricao(null);
        setId(null);
        setUserNameCuidador(null);
    }

    public MaterialCapacitacaoDTO getMaterialCapacitacaoDTO() {
        return materialCapacitacaoDTO;
    }

    public void setMaterialCapacitacaoDTO(MaterialCapacitacaoDTO materialCapacitacaoDTO) {
        this.materialCapacitacaoDTO = materialCapacitacaoDTO;
    }

    public EstadoProcedimento getEstado() {
        return estado;
    }
    
    public String getEstadoString() {
        return estado.toString();
    }
    

    public void setEstado(EstadoProcedimento estado) {
        this.estado = estado;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    } 
}
