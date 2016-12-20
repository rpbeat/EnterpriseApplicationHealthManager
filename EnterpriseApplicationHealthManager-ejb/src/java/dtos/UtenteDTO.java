/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.User;
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
        super(username, password, nome, email);
    }
}
