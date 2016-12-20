/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.util.logging.Logger;
import entities.Administrator;
import entities.Utente;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ruben
*/
@ManagedBean
@SessionScoped
public class LoginManager {
  private String username;
  private String password;
  AdministratorManager adminManager;
  private static final Logger logger = Logger.getLogger("web.LoginManager");
  
    public LoginManager() {
    }


  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String login () {
    FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getRequest();
    try {
      //request.login(this.username, this.password);
      //FUCK THIS SHIT!!!!!
            System.out.println("try login ");
        Administrator admin = new Administrator(this.username, this.password, "ruben", "asdasd@asdas.com");
        List<Administrator> list = adminManager.getAllAdministrators();
        if(list.contains(admin)){
            System.out.println("web.LoginManager.login()");
        }else{
            
            System.out.println("login incorrect ");
        }
    } catch (Exception e) {
        FacesExceptionHandler.handleException(e, "Login failed!", logger);
        return null;
    }
    return "admin/index";
  }

  public void logout() {
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) 
        context.getExternalContext().getRequest();
    try {
      request.logout();
    } catch (ServletException e) {
      context.addMessage(null, new FacesMessage("Logout failed."));
    }
  }
}
