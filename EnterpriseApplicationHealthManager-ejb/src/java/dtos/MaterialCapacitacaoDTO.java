/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rpbeat
 */
@XmlRootElement(name = "Material")
@XmlAccessorType(XmlAccessType.FIELD)
public class MaterialCapacitacaoDTO {
    private long id;
    private String tipo;
    private String link;
    private String descricao;

    public MaterialCapacitacaoDTO(long id, String tipo, String link, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.link = link;
        this.descricao = descricao;
    }

    public MaterialCapacitacaoDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void reset(){
        setDescricao(null);
        setId(-10);
        setLink(null);
        setTipo(null);
    }
    
    
}
