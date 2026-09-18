package org.subtrack;

import java.time.LocalDate;

import org.subtrack.enums.Statut;
import org.subtrack.models.AbonnementAvecEngagement;
import org.subtrack.services.AbonnementService;

public class Main {
    public static void main(String[] args) {

        AbonnementAvecEngagement abonnement = new AbonnementAvecEngagement(1, "Netflix", 10.0, LocalDate.now(),
                LocalDate.now().plusMonths(1), Statut.ACTIF, 3);

        AbonnementService abonnementService = new AbonnementService();
        abonnementService.saveAbonnement(abonnement);
        abonnementService.getAll();
    }
}