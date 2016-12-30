/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author rpbeat
 */
@Entity
@Table(name= "ProcedimentoCuidado")
@NamedQuery(name = "GetAllProcedimentoCuidado", query = "SELECT U FROM ProcedimentoCuidado U ORDER BY U.id")
public class ProcedimentoCuidado implements Serializable {

    @Id
    private String id;
    private String userNameCuidador;
    private String descricao;

    @OneToOne
    private MaterialCapacitacao materialCapacitacao;

    public ProcedimentoCuidado(String id,String userNameCuidador, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.userNameCuidador = userNameCuidador;
    }

    public ProcedimentoCuidado() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public MaterialCapacitacao getMaterialCapacitacao() {
        return materialCapacitacao;
    }

    public void setMaterialCapacitacao(MaterialCapacitacao materialCapacitacao) {
        this.materialCapacitacao = materialCapacitacao;
    }
    
}
