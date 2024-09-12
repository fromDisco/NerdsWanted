package org.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.model.Bewerber;
import org.io.FileReader;
import org.mail.MailUtil;

/**
 * This class is the Service class, that manages the Application
 */
public class NerdsWanted {
    private double konto = 0;
    private int iteration = 0;

    /**
     * Am i a SoftwareEntwickler?
     * Yes I am. But do LYTH and me fit together?
     * @param ich Instance of (lucky) Bewerber
     * @return true if application is successful
     */
    public boolean softWareEntwickler(Bewerber ich) {
        // prevent spam
        if (iteration > 2) {
            return false;
        }

        // check basic requirements
       if (ich.isSearchJob() && ich.isJava() || ich.isAndroid() || ich.isAngular()) {
           // Read contents of email from json file
           JsonNode jsonNode = new FileReader().jsonParser("files/mail.json");

           boolean response = MailUtil.sendMailTo(jsonNode);
           ich.setPasse(response);

           // if lucky, execute following expressions
           if (ich.isPasse() && kennenlernen(ich) && probearbeit()) {
               ich.addArbeitsvertrag("$$");
               this.konto += ich.arbeiten(ich.verantwortung, ich.spass, ich.geileProjekte);
               return ich.isArbeitsVertrag();
           }
       }

       // if unlucky repeat until lucky
       ich.setPasse(ich.addArbeitsvertrag("--"));
       iteration++;
       weitersuchen(ich);
       return ich.isPasse();
    }


    /**
     * If not successful, try again
     * @param ich
     */
    private void weitersuchen(Bewerber ich) {
        softWareEntwickler(ich);
    }


    /**
     * @return true if trial work has been agreed
     */
    private boolean probearbeit() {
        return true;
    }


    /**
     * @param ich
     * @return true if job interview has been made
     */
    private boolean kennenlernen(Bewerber ich) {
        return true;
    }
}
