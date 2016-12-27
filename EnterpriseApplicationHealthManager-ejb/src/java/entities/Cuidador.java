/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "Cuidadores")
@NamedQuery(name = "GetAllCuidadores", query = "SELECT U FROM Cuidador U ORDER BY U.nome")
public class Cuidador extends User implements Serializable{
    @ManyToMany(mappedBy = "Cuidadores")
    private List<Utente> utentes;
    
    public Cuidador() {
        utentes = new LinkedList<>();
    }
    
    public Cuidador(String username, String password, String nome, String email) {
        super(username, password, nome, email, GROUP.Cuidador);
        utentes = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Cuidador"+super.toString(); 
    }

    public List<Utente> getUtentes() {
        return utentes;
    }

    public void setUtentes(List<Utente> utentes) {
        this.utentes = utentes;
    }
    
    public void addUtente(Utente utente){
        this.utentes.add(utente);
    }
    
    public void removeUtente(Utente utente){
        this.utentes.remove(utente);
    }
}