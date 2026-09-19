package org.subtrack;

import java.time.LocalDate;

import org.subtrack.enums.Statut;
import org.subtrack.models.AbonnementSansEngagement;
import org.subtrack.services.AbonnementService;

public class Main {
    public static void main(String[] args) {

        AbonnementSansEngagement abonnement = new AbonnementSansEngagement("4c49fd04-d90b-4fe7-811b-2e168f89f190",
                "s2", 10.0, LocalDate.now(),
                LocalDate.now().plusMonths(3), Statut.ACTIF);

        AbonnementService abonnementService = new AbonnementService();
        abonnementService.saveAbonnement(abonnement);
        // abonnementService.deleteAbonnement(abonnement);
        abonnementService.getAll();
    }
}   