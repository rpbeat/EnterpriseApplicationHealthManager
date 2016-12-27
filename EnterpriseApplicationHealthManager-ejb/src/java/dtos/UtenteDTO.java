/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.GROUP;
import entities.User;
import entities.UserGroup;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rpbeat
 */
@XmlRootElement(name = "Utente")
@XmlAccessorType(XmlAccessType.FIELD)
public class UtenteDTO extends User implements Serializable{

    public UtenteDTO() {
    }

    public UtenteDTO(String username, String password, String nome, String email) {
        super(username, password, nome, email, GROUP.Utente);
    }

    public void reset() {
       this.email=null;
       this.nome=null;
       this.password=null;
       this.username=null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }
    
    

}
