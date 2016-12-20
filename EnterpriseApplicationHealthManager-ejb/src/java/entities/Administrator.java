/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name= "Administrators")
@NamedQuery(name = "GETALLADMINISTRATORS", query = "SELECT A FROM Administrator A ORDER BY A.nome")
public class Administrator extends User implements Serializable{

    public Administrator() {
    }
    
    public Administrator(String username, String password, String nome, String email) {
        super(username, password, nome, email);
    }

    @Override
    public String toString() {
        return "Administrator"+super.toString(); 
    }
    
}
