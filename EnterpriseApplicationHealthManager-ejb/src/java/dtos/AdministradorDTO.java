/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author rpbeat
 */
public class AdministradorDTO extends UserDTO implements Serializable{
    
    public AdministradorDTO() {
    }

    public AdministradorDTO(String username, String password, String nome, String email) {
        super(username, password, nome, email);
    }
}
