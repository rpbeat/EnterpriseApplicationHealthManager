/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
//@Table(name= "ProfissionalSaude")
@NamedQuery(name = "GetAllProfissionalSaude", query = "SELECT P FROM ProfissionalSaude P ORDER BY P.nome")
public class ProfissionalSaude extends User implements Serializable{

    public ProfissionalSaude() {
    }
    
    public ProfissionalSaude(String username, String password, String nome, String email,int contacto,String morada) {
        super(username, password, nome, email, contacto, morada,GROUP.ProfissionalSaude);
    }

    @Override
    public String toString() {
        return "ProfissionalSaude"+super.toString(); 
    }
    
}
