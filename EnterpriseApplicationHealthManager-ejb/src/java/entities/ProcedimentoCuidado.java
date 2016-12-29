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
import javax.persistence.Table;

/**
 *
 * @author rpbeat
 */
@Entity
@Table(name= "ProcedimentoCuidado")
@NamedQuery(name = "GetAllProcedimentoCuidado", query = "SELECT U FROM ProcedimentoCuidado U ORDER BY U.id")
class ProcedimentoCuidado implements Serializable {

    @Id
    private String id;
    private String descricao;

    public ProcedimentoCuidado(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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
}
