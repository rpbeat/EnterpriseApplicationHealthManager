/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.PerguntaDTO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rpbeat
 */
@Entity
@Table(name= "Questionario")
@NamedQuery(name = "GetAllQuestionarios", query = "SELECT U FROM Questionario U ORDER BY U.id")
public class Questionario implements Serializable{
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    long id;
    private List<Pergunta> perguntas;
    private String UserNameCuidador;
    private String UserNameProfissional;
    private Date data;

    public Questionario() {
    }

    public Questionario(List<Pergunta> perguntas, String userNameCuidador, String userNameProfissional) {
        this.perguntas=perguntas;
        this.UserNameCuidador =userNameCuidador;
        this.UserNameProfissional=userNameProfissional;
        this.perguntas = new LinkedList<>();
        this.data = new Date();
    }
    
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(this.data);
    }
    
    public String getUserNameCuidador() {
        return UserNameCuidador;
    }

    public void setUserNameCuidador(String UserNameCuidador) {
        this.UserNameCuidador = UserNameCuidador;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public String getUserNameProfissional() {
        return UserNameProfissional;
    }

    public void setUserNameProfissional(String UserNameProfissional) {
        this.UserNameProfissional = UserNameProfissional;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
