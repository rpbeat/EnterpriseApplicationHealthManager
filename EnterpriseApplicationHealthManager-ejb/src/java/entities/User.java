/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "USERS")
public class User  implements Serializable{
    @Id
    protected String username;
    @NotNull
    protected String password;
    @NotNull(message = "Name must not be empty")
    protected String nome;
    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid email format")
    protected String email;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    protected UserGroup group;

    
    protected User() {
    }
    
    protected User(String username, String password, String nome, String email,  GROUP group) {
        this.username = username;
        this.password =   hashPassword(password);
        this.nome = nome;
        this.email = email;
        this.group = new UserGroup(group,this);
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

    @Override
    public String toString() {
        return "{" + "username=" + username + ", password=" + password + ", name=" + nome + ", email=" + email + '}';
    }

    private String hashPassword(String password) {
       char[] encoded = null;
        try {
        ByteBuffer passwdBuffer =
        Charset.defaultCharset().encode(CharBuffer.wrap(password));
        byte[] passwdBytes = passwdBuffer.array();
        MessageDigest mdEnc = MessageDigest.getInstance("SHA-256");
        mdEnc.update(passwdBytes, 0, password.toCharArray().length);
        encoded = new BigInteger(1, mdEnc.digest()).toString(16).toCharArray();
        } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(encoded);

    }
    
    
}
