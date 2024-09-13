package org.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * "Bewerberprofil" that applies for a job at LYTH
 */
@Getter
@Setter
@NoArgsConstructor
public class Bewerber {
    private boolean searchJob;
    private boolean java;
    private boolean angular;
    private boolean android;
    private boolean passe;
    private boolean arbeitsVertrag;
    public double verantwortung;
    public double spass;
    public double geileProjekte;

    /**
     * Creates instance of Bewerber
     * @param searchJob boolean - better be true
     * @param java booelan - does Applicant have Java Skills
     * @param angular booelan - does Applicant have Angular Skills
     * @param android booelan - does Applicant have Android Skills
     */
    public Bewerber (boolean searchJob, boolean java, boolean angular, boolean android) {
        this.searchJob = searchJob;
        this.java = java;
        this.angular = angular;
        this.android = android;
    }

    /**
     * If shortCode is "$$" arbeitsvertrag is true. In all other cases arbeitsvertrag is false.
     * @param shortCode
     */
    public boolean addArbeitsvertrag(String shortCode) {
        if (shortCode.equals("$$")) {
            this.arbeitsVertrag = true;
        } else {
            this.arbeitsVertrag = false;
        }

        return this.arbeitsVertrag;
    }

    /**
     * Calculates the amount to be added to the konto
     * @param verantwortung
     * @param spass
     * @param geileProjekte
     * @return amount to be added to Konto
     */
    public double arbeiten(double verantwortung, double spass, double geileProjekte) {
        return verantwortung + spass * geileProjekte;
    }
}
