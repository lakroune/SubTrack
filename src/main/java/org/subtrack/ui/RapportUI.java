package org.subtrack.ui;

import org.subtrack.services.PaiementService;

import java.util.Scanner;

public class RapportUI {

    private final PaiementService paiementService;
    private final Scanner scanner;

    public RapportUI(PaiementService paiementService, Scanner scanner) {
        this.paiementService = paiementService;
        this.scanner = scanner;
    }

    public void consulterPaiementsManquesEngagements() {
        System.out.println("\n--- Paiements manques (Abonnements avec engagement) ---");
        paiementService.afficherPaiementsManquesAvecTotalImpaye();
    }

    public void genererRapportsFinanciers() {
        System.out.println("\n--- Rapports Financiers ---");
        System.out.println("1. Rapport Mensuel");
        System.out.println("2. Rapport Annuel");
        System.out.println("3. Rapport des Impayés");
        System.out.print("Choix : ");
        String choix = scanner.nextLine();

        switch (choix) {
            case "1":
                System.out.print("Mois (1-12) : ");
                int mois = Integer.parseInt(scanner.nextLine());
                System.out.print("Année : ");
                int anneeMois = Integer.parseInt(scanner.nextLine());
                paiementService.genererRapportMensuel(mois, anneeMois);
                break;
            case "2":
                System.out.print("Année : ");
                int annee = Integer.parseInt(scanner.nextLine());
                paiementService.genererRapportAnnuel(annee);
                break;
            case "3":
                paiementService.genererRapportImpayes();
                break;
            default:
                System.out.println("Option invalide.");
        }
    }
}