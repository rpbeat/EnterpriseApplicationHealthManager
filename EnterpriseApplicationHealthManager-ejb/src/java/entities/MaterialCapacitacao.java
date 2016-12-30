/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy=GenerationType.AUTO) 
    long id;
    private String tipo;
    private String link;
    private String descricao;
    
    @ManyToMany
    @JoinTable(name = "USERS_MATERIALCAPACITACAO",
            joinColumns
            = @JoinColumn(name = "MATERIAIS_ID", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "CUIDADORES_USERNAME", referencedColumnName = "USERNAME"))
    private List<Cuidador> cuidadores;
    
    
    
    public MaterialCapacitacao(String tipo, String descricao, String link) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.link = link;
        this.cuidadores = new LinkedList<>();
    }

    public MaterialCapacitacao() {
        this.cuidadores = new LinkedList<>();
    }

    public long getId() {
        return id;
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

    public List<Cuidador> getCuidadores() {
        return cuidadores;
    }

    public void setCuidadores(List<Cuidador> cuidadores) {
        this.cuidadores = cuidadores;
    }
    
    
    
    
    
    
}
