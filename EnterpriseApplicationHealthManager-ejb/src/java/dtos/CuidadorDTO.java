/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.sun.faces.application.resource.LibraryInfo;
import entities.MaterialCapacitacao;
import entities.Utente;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rpbeat
 */
public class CuidadorDTO extends UserDTO implements Serializable{
      
    public CuidadorDTO(String nome, String email, int contacto, String morada, String username, String password) {
        super(nome, email, contacto, morada, username, password);

    }

    public CuidadorDTO() {
    }
    
}
