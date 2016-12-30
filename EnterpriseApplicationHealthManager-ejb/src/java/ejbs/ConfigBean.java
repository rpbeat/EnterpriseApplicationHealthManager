/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.ejb.Singleton;

/**
 *
 * @author rpbeat
 */
@Singleton
@Startup
public class ConfigBean {
    
    @EJB
    private AdministradorBean administratorBean;
    @EJB
    private CuidadorBean cuidadorBean;
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;
    @EJB
    private UtenteBean utenteBean;
    @EJB
    private MaterialCapacitacaoBean materialCapacitacaoBean;
    @EJB
    private ProcedimentoCuidadoBean procedimentoCuidadoBean;
    
    @PostConstruct
    public void populateBD(){
        try{
            administratorBean.create("Emaluel","Emanuel@gmail.com",91555555,"Rua da Maria","emanuel","ola");
            administratorBean.create("Ruben","Ruben@gmail.com",91555555,"Rua do Ruben","Ruben","ola");
            administratorBean.create("Cena","Cena@gmail.com",91555555,"Rua do Cena","Cena","ola");
  
            cuidadorBean.create("ZeMaria","Emanuel@gmail.com",91555555,"Rua da Maria","ZeMaria","ola");
            cuidadorBean.create("Camila","Camila@gmail.com",91555555,"Rua da Camila","Camila","ola");
            cuidadorBean.create("Isabel","Isabel@gmail.com",91555555,"Rua da Isabel","Isabel","ola");
            cuidadorBean.create("William","William@gmail.com",91555555,"Rua da William","William","ola");

            profissionalSaudeBean.create("Edgard","Edgard@gmail.com",91555555,"Rua da Edgard","Edgard","ola");
            profissionalSaudeBean.create("Walace","Walace@gmail.com",91555555,"Rua da Walace","Walace","ola");
   
            
            utenteBean.create("Guilherme", "Guilherme", 95555, "dae.ei.ipleiria@gmail.com");
            utenteBean.create("Victor", "Victor", 95555, "dae.ei.ipleiria@gmail.com");
            utenteBean.create("Afonso", "Afonso", 95555, "dae.ei.ipleiria@gmail.com");
           
            
            //cuidadorBean.enrollUtente("Camila", "Afonso");
            //cuidadorBean.enrollUtente("William", "Victor");
            //cuidadorBean.enrollUtente("Camila", "Guilherme");
            
            materialCapacitacaoBean.create("sonda", "equipamento de sonda", "não tem");
            materialCapacitacaoBean.create("bistu", "equipamento de bistu", "não tem");
            materialCapacitacaoBean.create("oscut", "equipamento de oscut", "não tem");
            materialCapacitacaoBean.create("cadeira", "equipamento de cadeira", "não tem");
            
            //cuidadorBean.enrollMaterial("1", "William");
            //cuidadorBean.enrollMaterial("2", "William");
            //cuidadorBean.enrollMaterial("3", "Isabel");
            //cuidadorBean.enrollMaterial("4", "Isabel");
           
            procedimentoCuidadoBean.create("2", "sad", "sadas");
            procedimentoCuidadoBean.create("3", "sad", "sadas");
            
        }catch(Exception e){
            e.getMessage();
        }
    }
    
}
