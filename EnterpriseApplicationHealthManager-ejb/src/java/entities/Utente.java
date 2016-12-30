/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name= "Utentes")
@NamedQuery(name = "GetAllUtentes", query = "SELECT U FROM Utente U ORDER BY U.nome")
public class Utente extends Pessoa implements Serializable{
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    long id;
    
    @ManyToOne
    private Cuidador cuidadores;
    
    private List<ProcedimentoCuidado> procedimentos;
    
    public Utente() {
    }
    
    public Utente(String nome, String email, int contacto,String morada) {
        super(nome, email, contacto, morada);
    }

    public Cuidador getCuidador() {
        return cuidadores;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidadores = cuidador;
    }
    
    
    public void removeCuidador(){
        this.cuidadores = null;
    }

    public List<ProcedimentoCuidado> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<ProcedimentoCuidado> procedimentos) {
        this.procedimentos = procedimentos;
    }
    
    public void addProcedimento(ProcedimentoCuidado p){
        this.procedimentos.add(p);
    }
    
    public void removeProcedimento(ProcedimentoCuidado p){
        this.procedimentos.remove(p);
    }

    public long getId() {
        return id;
    }

    public void setId(long username) {
        this.id = username;
    }

    @Override
    public String toString() {
        return "Utente"+super.toString(); 
    }
}
