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
public class PessoaDTO {

    protected String nome;
    protected String email;    
    protected int contacto;   
    protected  String morada;

    public PessoaDTO(String nome, String email, int contacto, String morada) {
        this.nome = nome;
        this.email = email;
        this.contacto = contacto;
        this.morada = morada;
    }

    public PessoaDTO() {
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void reset() {
        setContacto(0);
        setEmail(null);
        setMorada(null);
        setNome(null);
    }
    
    
}
