/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name= "Utentes")
@NamedQuery(name = "GetAllUtentes", query = "SELECT U FROM Utente U ORDER BY U.nome")
public class Utente extends User implements Serializable{
    private List<Cuidador> cuidadores;

    public Utente() {
    }
    
    public Utente(String username, String password, String nome, String email) {
        super(username, password, nome, email,GROUP.Utente);
    }

    public List<Cuidador> getCuidadores() {
        return cuidadores;
    }

    public void setCuidadores(List<Cuidador> cuidadores) {
        this.cuidadores = cuidadores;
    }
    
    
    public void addCuidador(Cuidador cuidador){
        this.cuidadores.add(cuidador);
    }
    
    public void removeCuidador(Cuidador cuidador){
        this.cuidadores.remove(cuidador);
    }

    @Override
    public String toString() {
        return "Utente"+super.toString(); 
    }
}
