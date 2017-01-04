/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Pergunta;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rpbeat
 */
public class QuestionarioDTO {
    
    private List<Pergunta> perguntas;
    private String UserNameCuidador;
    private String UserNameProfissional;
    long id;
    private String data;

    public QuestionarioDTO(List<Pergunta> perguntas, String UserNameCuidador, String UserNameProfissional, long id, String data) {
        this.perguntas = perguntas;
        this.UserNameCuidador = UserNameCuidador;
        this.UserNameProfissional = UserNameProfissional;
        this.id = id;
        this.data = data;
    }
    
    public QuestionarioDTO() {
        this.perguntas = new LinkedList<>();
        this.perguntas.add(new Pergunta("Acha que o seu utente pede mais ajuda do que ele(ela) realmente precisa?"));
        this.perguntas.add(new Pergunta("Acha que não tem tempo sufuciente para si próprio(a), devido ao tempo que tem de dedicar ao seu utente?"));
        this.perguntas.add(new Pergunta("Sente-se em 'stress' por ter de se dividir entre o cuidar do seu utente e as suas outras responsabilidades?"));
        this.perguntas.add(new Pergunta("Sente-se envergonhado(a) com o comportamento do seu utente?"));
        this.perguntas.add(new Pergunta("Sente-se irritado(a) ou zangado(a) quando está com o seu utente?"));
        this.perguntas.add(new Pergunta("Acha que o seu cuidador está presentemente a afectar, de forma negativa, a sua relação com outros membros da família ou com os seus amigos?"));
        this.perguntas.add(new Pergunta("Tem medo do que o futuro pode reservar ao seu utente?"));
        this.perguntas.add(new Pergunta("Acha que o seu utente está dependente de si?"));
        this.perguntas.add(new Pergunta("Sente-se em tensão quando está com o seu utente?"));
        this.perguntas.add(new Pergunta("Acha que não tem tanta privacidade quanto desejaria, por causa do seu utente?"));
        this.perguntas.add(new Pergunta("Acha que a sua vida social se tem ressentido por estar responsavel pelo seu utente?"));
        this.perguntas.add(new Pergunta("Sente-se desconfortável quando recebe visitas dos seus amigos, por causa do seu utente?"));
        this.perguntas.add(new Pergunta("Acha que o seu utente espera que cuide dele como se fosse a única pessoa com quem ele pode contar?"));
        this.perguntas.add(new Pergunta("Acha que não tem dinheiro suficiente para cuidar do seu utente, tendo em conta todas as suas outras despesas?"));
        this.perguntas.add(new Pergunta("Acha que já não será capaz de continuar a cuidar do seu utente por muito mais tempo?"));
        this.perguntas.add(new Pergunta("Sente que perdeu o controlo sobre a sua vida desde que a doença do seu utente apareceu?"));
        this.perguntas.add(new Pergunta("Deseja que pudesse ser uma outra pessoa a cuidar do seu utente?"));
        this.perguntas.add(new Pergunta("Sente-se indeciso(a) quanto ao que fazer com o seu utente?"));
        this.perguntas.add(new Pergunta("Acha que devia estar a fazer mais pelo seu utente?"));
        this.perguntas.add(new Pergunta("Acha que podia cuidar melhor do seu familiar?"));
        this.perguntas.add(new Pergunta("De uma maneira geral, de que forma se sente sobrecarregado(a) por estar a cuidar do seu familiar?"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
    
    public void addPergunta(String descricao){
        perguntas.add(new Pergunta(descricao));
    }
    
    public void remove(String descricao){
        perguntas.remove(new Pergunta(descricao));
    }
    
    public void reset(){
        setUserNameCuidador(null);
        setUserNameProfissional(null);
        
    }
}
