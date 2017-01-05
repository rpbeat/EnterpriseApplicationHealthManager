/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Emanuel Lopes
 */
@Entity
@Table(name = "Files")
@NamedQuery(name = "GetAllFiles", query = "SELECT U FROM FileUpload U ORDER BY U.id")
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String nomeFicheiro;
    private String caminhoFicheiro;

    public FileUpload() {
    }

    public FileUpload(String nomeFicheiro, String caminhoFicheiro) {
        this.nomeFicheiro = nomeFicheiro;
        this.caminhoFicheiro = caminhoFicheiro;
    }

    public String getNomeFicheiro() {
        return nomeFicheiro;
    }

    public void setNomeFicheiro(String nomeFicheiro) {
        this.nomeFicheiro = nomeFicheiro;
    }

    public String getCaminhoFicheiro() {
        return caminhoFicheiro;
    }

    public void setCaminhoFicheiro(String caminhoFicheiro) {
        this.caminhoFicheiro = caminhoFicheiro;
    }
}
