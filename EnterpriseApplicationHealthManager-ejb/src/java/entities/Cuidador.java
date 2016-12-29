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
    

    
    private List<MaterialCapacitacao> materiais;
    
    public Cuidador() {
        utentes = new LinkedList<>();
    }
    
    public Cuidador(String username, String password, String nome, String email) {
        super(username, password, nome, email, GROUP.Cuidador);
        utentes = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Cuidador"+super.toString()+"utentes: "+utentes.toString(); 
    }

    public List<Utente> getUtentes() {
        return this.utentes;
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

    public List<MaterialCapacitacao> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialCapacitacao> materiais) {
        this.materiais = materiais;
    }
    
    public void addMaterial(MaterialCapacitacao m){
        this.materiais.add(m);
    }
    
    public void removeMaterial(MaterialCapacitacao m){
        this.materiais.remove(m);
        
    }
}