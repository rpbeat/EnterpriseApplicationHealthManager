/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.security.acl.Group;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name= "Administrador")
@NamedQuery(name = "GetAllAdministradores", query = "SELECT A FROM Administrador A ORDER BY A.nome")
public class Administrador extends User implements Serializable{

    public Administrador() {
    }
    
    public Administrador(String username, String password, String nome, String email) {
        super(username, password, nome, email, GROUP.Administrador);
    }

    @Override
    public String toString() {
        return "Administrator"+super.toString(); 
    }
    
}
