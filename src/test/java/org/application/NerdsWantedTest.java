package org.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.model.Bewerber;
import org.service.NerdsWanted;

import static org.junit.jupiter.api.Assertions.*;

class NerdsWantedTest {

    @Test
    @DisplayName("softWareEntwicklerIsTrue")
    void softWareEntwicklerIsTrue() {
        Bewerber bewerber = new Bewerber(true, true, false, false);
        NerdsWanted nerd = new NerdsWanted();
        boolean isNerd = nerd.softWareEntwickler(bewerber);
        assertTrue(isNerd);
    }

    @Test
    @DisplayName("softWareEntwicklerIsFalse")
    void softWareEntwicklerIsFalse() {
        Bewerber bewerber = new Bewerber(false, true, false, false);
        NerdsWanted nerd = new NerdsWanted();
        boolean isNerd = nerd.softWareEntwickler(bewerber);
        assertFalse(isNerd);
    }
}