/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author rpbeat
 */
@Entity
@Table(name= "ManagerApp")
public class ManagerApp {
    @Id 
    private int id=1;
    private int countLogin;
    private List<Date> listaTotal;
    private HashMap<String,List<Date>> listaDeAcessos;

    public ManagerApp() {
        this.listaTotal = new LinkedList<>();
        this.listaDeAcessos = new HashMap<>();
        this.countLogin=0;
        this.id=1;
    }

    public List<Date> getListaTotal() {
        return listaTotal;
    }

    public void setListaTotal(List<Date> listaTotal) {
        this.listaTotal = listaTotal;
    }

    public HashMap<String, List<Date>> getListaDeAcessos() {
        return listaDeAcessos;
    }

    public void setListaDeAcessos(HashMap<String, List<Date>> listaDeAcessos) {
        this.listaDeAcessos = listaDeAcessos;
    }
    
    public int getCountLogin() {
        return countLogin;
    }

    public void setCountLogin(int countLogin) {
        this.countLogin = countLogin;
    }
    
    public void addAcessoCuidador(String userNameCuidador, Date data){
        
        
        if(listaDeAcessos.isEmpty()){
            List<Date> list = new LinkedList<>();
            listaDeAcessos = new HashMap<>();
            list.add(new Date());
            listaDeAcessos.put(userNameCuidador, list);
            listaTotal.add(data);
            countLogin+=1;
            
        }else{
            List<Date> list = new LinkedList<>();
             list = listaDeAcessos.get(userNameCuidador);
             if(list==null){
                list = new LinkedList<>();
             }
             list.add(new Date());
             listaDeAcessos.put(userNameCuidador, list);
             listaTotal.add(data);
             countLogin+=1;
             
        }
    }
    
    public List<Date> getListaDeAcessosUser(String userNameCuidador){
        System.err.println("LISTA DE ACESSOS SIZE: "+listaDeAcessos.size());
        return listaDeAcessos.get(userNameCuidador);
    }
   
}
