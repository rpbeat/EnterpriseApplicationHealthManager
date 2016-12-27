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
    //@EJB
    //private MaterialCapacitacaoBean materialCapacitacaoBean;
    
    @PostConstruct
    public void populateBD(){
        try{
            administratorBean.create("rp", "ola", "ruben", "asdasd@asdas.com");
            cuidadorBean.create("rb", "olex", "rubex", "asdasasd@assdas.com");
            profissionalSaudeBean.create("rf", "olfa", "rubffefn", "afffsdasd@asdas.com");
            utenteBean.create("rfss", "olfa", "rubffefn", "afffsdasd@asdas.com");
            administratorBean.create("1111111", "Manuel", "Manuel", "dae.ei.ipleiria@gmail.com");
            administratorBean.create("2222222", "Antonio", "António", "dae.ei.ipleiria@gmail.com");
            cuidadorBean.create("3333333", "Ana", "Ana", "dae.ei.ipleiria@gmail.com");
            cuidadorBean.create("4444444", "Jose", "José", "dae.ei.ipleiria@gmail.com");
            cuidadorBean.create("5555555", "Maria", "Maria", "dae.ei.ipleiria@gmail.com");
            utenteBean.create("6666666", "Joaquim", "Joaquim", "dae.ei.ipleiria@gmail.com");
            utenteBean.create("7777777", "Alzira", "Alzira", "dae.ei.ipleiria@gmail.com");
            profissionalSaudeBean.create("8888888", "Pedro", "Pedro", "dae.ei.ipleiria@gmail.com");
            
            cuidadorBean.enrollUtente("7777777", "5555555");
            
        }catch(Exception e){
            e.getMessage();
        }
    }
    
}
