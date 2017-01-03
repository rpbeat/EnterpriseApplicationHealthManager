/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rpbeat
 */
public class Pergunta implements Serializable{
    private String descricao;
    private String resposta; //0- absolutamente nada/nunca ,1 - um pouco/raramente,2 - moderadamente/algumas vezes ,3 - bastantes vezes/muito,4  - quase sempre/muitissimo
    private List<String> tipoResposta;  

    public Pergunta(String descricao) {
        tipoResposta = new LinkedList<>();
        this.descricao = descricao;

            tipoResposta.add("Absolutamente nada");
            tipoResposta.add("Um pouco");
            tipoResposta.add("Moderadamente");
            tipoResposta.add("Bastantes vezes");
            tipoResposta.add("Quase sempre");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public List<String> getTipoResposta() {
        return tipoResposta;
    }

    public void setTipoResposta(List<String> tipoResposta) {
        this.tipoResposta = tipoResposta;
    }
    
    
}
