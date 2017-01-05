/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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
    @EJB
    private ManagerAppBean managerAppBean;

    @PostConstruct
    public void populateBD() {
        try {
            administratorBean.create("Emanuel", "Emanuel@gmail.com", 821894351, "Rua da Maria", "emanuel", "ola");
            administratorBean.create("Ruben Pereira", "RubenP@gmail.com", 687474658, "Rua do Ruben", "rubenp", "ola");
            administratorBean.create("Ruben Domingues", "RubenD@gmail.com", 706648931, "Rua do Cena", "rubend", "ola");

            cuidadorBean.create("ZeMaria", "Emanuel@gmail.com", 786659177, "Rua da Maria", "ZeMaria", "ola");
            cuidadorBean.create("Camila", "Camila@gmail.com", 894221969, "Rua da Camila", "Camila", "ola");
            cuidadorBean.create("Isabel", "Isabel@gmail.com", 760440227, "Rua da Isabel", "Isabel", "ola");
            cuidadorBean.create("William", "William@gmail.com", 720667714, "Rua da William", "William", "ola");

            profissionalSaudeBean.create("Edgard", "Edgard@gmail.com", 690391646, "Rua da Edgard", "Edgard", "ola");
            profissionalSaudeBean.create("Walace", "Walace@gmail.com", 610445082, "Rua da Walace", "Walace", "ola");

            utenteBean.create("Guilherme", "Guilherme@gmail.com", 775758684, "Rua do Guilherme");
            utenteBean.create("Victor", "Victor@gmail.com", 894221969, "Rua do Vitor");
            utenteBean.create("Afonso", "Afonso@gmail.com", 821894351, "Rua do Afonso");
            utenteBean.create("Rodrigo", "Rodrigo@gmail.com", 894221969, "Rua do Rodrigo");
            utenteBean.create("Diogo", "Diogo@gmail.com", 821894351, "Rua do Diogo");
            utenteBean.create("Tiago", "Tiago@gmail.com", 894221969, "Rua do Tiago");
            utenteBean.create("Vitoria", "Vitoria@gmail.com", 821894351, "Rua do Vitoria");

            cuidadorBean.enrollUtente("Camila", 1);
            cuidadorBean.enrollUtente("ZeMaria", 2);
            cuidadorBean.enrollUtente("Isabel", 3);
            cuidadorBean.enrollUtente("William", 4);
           
            //cuidadorBean.enrollUtente("William", "Victor");
            //cuidadorBean.enrollUtente("Camila", "Guilherme");
            materialCapacitacaoBean.create("Estetoscópio", "Saúde do Adulto e do Idoso", "ND");
            materialCapacitacaoBean.create("Esfigmomanômetro", "Saúde do Adulto e do Idoso", "ND");
            materialCapacitacaoBean.create("Termômetro", "Semiologia e Semiotécnica de Enfermagem", "ND");
            materialCapacitacaoBean.create("Tesoura pequena ponta romba", "Semiologia e Semiotécnica de Enfermagem", "ND");
            materialCapacitacaoBean.create("Penso rapido", "Video demostrativo penso rapido", "https://www.youtube.com/watch?v=NLuETJOY7Hg");
            materialCapacitacaoBean.create("Máscara descartável", "Saúde da Mulher, Criança e Adolescente", "ND");
            materialCapacitacaoBean.create("Gorro descartável", "Saúde da Mulher, Criança e Adolescente", "ND");
            
            

            cuidadorBean.enrollMaterial("9", "Camila");
            cuidadorBean.enrollMaterial("8", "Camila");
            cuidadorBean.enrollMaterial("10", "Isabel");
            cuidadorBean.enrollMaterial("12", "Isabel");
            cuidadorBean.enrollMaterial("13", "William");
            cuidadorBean.enrollMaterial("14", "William");
            cuidadorBean.enrollMaterial("10", "ZeMaria");
            cuidadorBean.enrollMaterial("9", "ZeMaria");
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            String dateInString1 = "28-Dez-2017";
            String dateInString2 = "29-Dez-2017";
            String dateInString3 = "30-Dez-2017";
            String dateInString4 = "31-Dez-2017";
            String dateInString5 = "3-Jan-2017";
            String dateInString6 = "1-Jan-2017";
            String dateInString7 = "2-Jan-2017";
            String dateInString8 = "2-Dez-2017";
            String dateInString9 = "2-Jan-2017";
            


            managerAppBean.addAcessoCuidador("Camila", formatter.parse(dateInString1));
            managerAppBean.addAcessoCuidador("Camila", formatter.parse(dateInString2));
            managerAppBean.addAcessoCuidador("Camila", formatter.parse(dateInString3));
            managerAppBean.addAcessoCuidador("Isabel", formatter.parse(dateInString4));
            managerAppBean.addAcessoCuidador("Isabel", formatter.parse(dateInString5));
            managerAppBean.addAcessoCuidador("Isabel", formatter.parse(dateInString6));
            managerAppBean.addAcessoCuidador("Camila", formatter.parse(dateInString7));
            managerAppBean.addAcessoCuidador("Isabel", formatter.parse(dateInString4));
            managerAppBean.addAcessoCuidador("William", formatter.parse(dateInString8));
            managerAppBean.addAcessoCuidador("William", formatter.parse(dateInString9));
            managerAppBean.addAcessoCuidador("ZeMaria", formatter.parse(dateInString4));
            managerAppBean.addAcessoCuidador("ZeMaria", formatter.parse(dateInString8));
            managerAppBean.addAcessoCuidador("William", formatter.parse(dateInString7));

            //procedimentoCuidadoBean.create("1", "Camila", "Cena de obtstrução",EstadoProcedimento.A_iniciar);
           
            //procedimentoCuidadoBean.enrrolMaterialToProcedimento(4, "1");
            //utenteBean.enrrolProcedimento("1", 1);
            
            
            //procedimentoCuidadoBean.create("1", "sad", "sadas");

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
