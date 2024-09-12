package org.application;


import org.model.Bewerber;
import org.service.NerdsWanted;

public class Main {
    public static void main(String[] args) {
        Bewerber bewerber = new Bewerber(true, true, false, false);
        NerdsWanted nerdsWanted = new NerdsWanted();
        nerdsWanted.softWareEntwickler(bewerber);
    }

}