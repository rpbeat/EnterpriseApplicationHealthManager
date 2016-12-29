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
@Table(name= "MaterialCapacitacao")
@NamedQuery(name = "GetAllMaterialCapacitacao", query = "SELECT U FROM MaterialCapacitacao U ORDER BY U.id")
public class MaterialCapacitacao implements Serializable{
    @Id
    private String id;
    private String tipo;
    private String link;
    private String descricao;

    public MaterialCapacitacao(String id, String tipo, String descricao, String link) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.link = link;
    }

    public MaterialCapacitacao() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    
    
    
}
