/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Cuidador;
import entities.GROUP;
import entities.ProcedimentoCuidado;
import entities.User;
import entities.UserGroup;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rpbeat
 */
@XmlRootElement(name = "Utente")
@XmlAccessorType(XmlAccessType.FIELD)
public class UtenteDTO extends PessoaDTO implements Serializable{
    long id;
    
    
    public UtenteDTO(long id,String nome, String email, int contacto, String morada) {
        super(nome,email,contacto,morada);
        this.id=id;

    }

    public UtenteDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
 
    
}
