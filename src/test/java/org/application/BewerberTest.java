package org.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.model.Bewerber;

import static org.junit.jupiter.api.Assertions.*;

class BewerberTest {
    @Test
    @DisplayName("testBewerberInstantiation")
    void testBewerberInstantiation() {
        Bewerber bewerber = new Bewerber(true, true, false, false);
        assertTrue(bewerber.isSearchJob());
        assertTrue(bewerber.isJava());
        assertFalse(bewerber.isAndroid());
        assertFalse(bewerber.isAngular());
    }


    @Test
    @DisplayName("testArbeitenReturns8")
    void testArbeitenReturns8() {
        Bewerber bewerber = new Bewerber();
        double arbeitsResultat = bewerber.arbeiten(2.00, 2.00, 3.00);
        assertEquals(8.00, arbeitsResultat);
    }


    @Test
    @DisplayName("testArbeitenReturns34")
    void testArbeitenReturns34() {
        Bewerber bewerber = new Bewerber();
        double arbeitsResultat = bewerber.arbeiten(2.00, 4.00, 8.00);
        assertEquals(34.00, arbeitsResultat);
    }

    @Test
    @DisplayName("testAddArbeitsvertragIsTrue")
    void testAddArbeitsvertragIsTrue() {
        Bewerber bewerber = new Bewerber();
        boolean arbeitsvertrag = bewerber.addArbeitsvertrag("$$");
        assertTrue(arbeitsvertrag);
    }

    @Test
    @DisplayName("testAddArbeitsvertragIsFalse")
    void testAddArbeitsvertragIsFalse() {
        Bewerber bewerber = new Bewerber();
        boolean arbeitsvertrag = bewerber.addArbeitsvertrag("Nein");
        assertFalse(arbeitsvertrag);
    }
}