/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author rpbeat
 */
@ManagedBean
@SessionScoped
public class UserManager implements Serializable {

    private String username;
    private String password;
    private boolean loginFlag = false;
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");

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

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public String login() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request
                = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {
            logger.log(Level.WARNING, e.getMessage());
            System.out.println("" + e.getMessage());
            loginFlag = false;
            return "error?faces-redirect=true";
        }
        loginFlag = true;
        if (isUserInRole("Administrador")) {
            return "/faces/admin/admin_view?faces-redirect=true";
        }
        if (isUserInRole("Cuidador")) {
            return "/faces/cuidador/cuidador_view?faces-redirect=true";
        }
        if (isUserInRole("ProfissionalSaude")) {
            return "/faces/profissional/profissional_view?faces-redirect=true";
        }
        if (isUserInRole("Utente")) {
            return "error?faces-redirect=true";
        }
        return "error?faces-redirect=true";
    }

    public boolean isUserInRole(String role) {
        return (isSomeUserAuthenticated()
                && FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role));
    }

    public boolean isSomeUserAuthenticated() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }

    public String logout() {
        loginFlag = false;
        FacesContext context = FacesContext.getCurrentInstance();

        for (String bean : context.getExternalContext().getSessionMap().keySet()) {
            context.getExternalContext().getSessionMap().remove(bean);
        }
        HttpSession session
                = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "/index_login.xhtml?faces-redirect=true";
    }

    public String clearLogin() {
        if (isSomeUserAuthenticated()) {
            logout();
        }
        return "index_login.xhtml?faces-redirect=true";
    }
}
