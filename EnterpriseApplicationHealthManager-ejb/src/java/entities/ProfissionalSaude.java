/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
//@Table(name= "ProfissionalSaude")
@NamedQuery(name = "GetAllProfissionalSaude", query = "SELECT P FROM ProfissionalSaude P ORDER BY P.nome")
public class ProfissionalSaude extends User implements Serializable{
    
    List<Questionario> questionarios;

    public ProfissionalSaude() {
        questionarios = new LinkedList<>();
    }
    
    public ProfissionalSaude(String username, String password, String nome, String email,int contacto,String morada) {
        super(username, password, nome, email, contacto, morada,GROUP.ProfissionalSaude);
        questionarios = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "ProfissionalSaude"+super.toString(); 
    }

    public List<Questionario> getQuestionarios() {
        return questionarios;
    }

    public void setQuestionarios(List<Questionario> questionarios) {
        this.questionarios = questionarios;
    }
    
    public void addQuestionario(Questionario questionario){
        this.questionarios.add(questionario);
    }
    
    public void removeQuestionario(Questionario questionario){
        this.questionarios.remove(questionario);
    }
    
}
