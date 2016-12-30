/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rpbeat
 */
@XmlRootElement(name = "Cuidador")
@XmlAccessorType(XmlAccessType.FIELD)
public class CuidadorDTO extends UserDTO implements Serializable{
      
    public CuidadorDTO(String nome, String email, int contacto, String morada, String username, String password) {
        super(nome, email, contacto, morada, username, password);

    }

    public CuidadorDTO() {
    }
    
}
