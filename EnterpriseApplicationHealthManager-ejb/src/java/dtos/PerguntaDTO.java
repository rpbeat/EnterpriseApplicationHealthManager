/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.List;

/**
 *
 * @author rpbeat
 */
public class PerguntaDTO {
    private String descricao;
    private String resposta = "Absolutamente nada"; //0- absolutamente nada/nunca ,1 - um pouco/raramente,2 - moderadamente/algumas vezes ,3 - bastantes vezes/muito,4  - quase sempre/muitissimo
    private List<String> tipoResposta;  

    public PerguntaDTO(String descricao) {
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
