/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author rpbeat
 */
public class ProcedimentoCuidadoDTO {
    private String id;
    private String userNameCuidador;
    private String descricao;
    private MaterialCapacitacaoDTO materialCapacitacaoDTO;

    public ProcedimentoCuidadoDTO(String id, String userNameCuidador, String descricao, MaterialCapacitacaoDTO materialToDTO) {
        this.id = id;
        this.userNameCuidador = userNameCuidador;
        this.descricao = descricao;
        this.materialCapacitacaoDTO = materialToDTO;
    }

    public ProcedimentoCuidadoDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNameCuidador() {
        return userNameCuidador;
    }

    public void setUserNameCuidador(String userNameCuidador) {
        this.userNameCuidador = userNameCuidador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void reset(){
        setDescricao(null);
        setId(null);
        setUserNameCuidador(null);
    }

    public MaterialCapacitacaoDTO getMaterialCapacitacaoDTO() {
        return materialCapacitacaoDTO;
    }

    public void setMaterialCapacitacaoDTO(MaterialCapacitacaoDTO materialCapacitacaoDTO) {
        this.materialCapacitacaoDTO = materialCapacitacaoDTO;
    }
    
    
}
