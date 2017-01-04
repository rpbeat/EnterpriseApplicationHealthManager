/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejbs.ManagerAppBean;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rpbeat
 */
@ManagedBean
@SessionScoped
public class ManagerApp {
    
    @EJB
    ManagerAppBean managerAppBean;
    
    public void addAcessoCuidador(String userNameCuidador, Date data){
        managerAppBean.addAcessoCuidador(userNameCuidador, data);
    }
    
    public int getTotalLogin(){
        return managerAppBean.getTotalLogin();
    }
    
    public List<Date> getListaAcessosByUser(String username){
        return managerAppBean.getListAcessosByUser(username);
    }
    
    public List<Date> getListaTotal(){
        return managerAppBean.getListTotalAcessos();
    }
}
