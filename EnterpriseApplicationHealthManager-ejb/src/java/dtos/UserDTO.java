package dtos;

import java.io.Serializable;

public class UserDTO extends PessoaDTO implements Serializable{

    protected String username;
    protected String password;    
    
    public UserDTO(String nome, String email, int contacto, String morada, String username, String password) {
        super(nome,email,contacto,morada);
        this.username = username;
        this.password = password;

    }

    public UserDTO() {
    }
    
    
    public void reset() {
        super.reset();
        setUsername(null);
        setPassword(null);
        setNome(null);
        setEmail(null);
    }        

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
