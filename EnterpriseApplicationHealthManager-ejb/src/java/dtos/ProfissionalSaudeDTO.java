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
@XmlRootElement(name = "Profissional")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfissionalSaudeDTO extends UserDTO implements Serializable{

    public ProfissionalSaudeDTO() {
    }

    public ProfissionalSaudeDTO(String username, String password, String nome, String email) {
        super(username, password, nome, email);
    }
    
}
