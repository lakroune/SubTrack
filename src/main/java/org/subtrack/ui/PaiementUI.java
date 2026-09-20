package org.subtrack.ui;

import org.subtrack.enums.Statut;
import org.subtrack.models.Paiement;
import org.subtrack.services.PaiementService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaiementUI {

    private final PaiementService paiementService;
    private final Scanner scanner;

    public PaiementUI(PaiementService paiementService, Scanner scanner) {
        this.paiementService = paiementService;
        this.scanner = scanner;
    }

    public void enregistrerPaiement() {
        System.out.println("\n--- Enregistrer un Paiement ---");
        System.out.print("ID Abonnement : ");
        String idAb = scanner.nextLine();

        System.out.print("Type de paiement (ex: CARTE, VIREMENT) : ");
        String typePaiement = scanner.nextLine();

        String idPaiement = "PAY-" + UUID.randomUUID().toString().substring(0, 6);
        Paiement p = new Paiement();
        p.setIdPaiement(idPaiement);
        p.setIdAbonnement(idAb);
        p.setDateEcheance(LocalDate.now());
        p.setDatePaiement(LocalDate.now());
        p.setTypePaiement(typePaiement);
        p.setStatut(Statut.PAYE);

        if (paiementService.savePaiement(p) != null)
            System.out.println("Paiement enregistré avec succès (ID: " + idPaiement + ")");
    }

    public void modifierPaiement() {
        System.out.print("\nID du paiement à modifier : ");
        String idP = scanner.nextLine();
        Paiement p = paiementService.getPaiement(idP);
        if (p == null) {
            System.out.println("Paiement introuvable.");
            return;
        }

        System.out.print("Nouveau type de paiement (" + p.getTypePaiement() + ") : ");
        String type = scanner.nextLine();
        if (!type.isEmpty())
            p.setTypePaiement(type);

        paiementService.updatePaiement(p);
    }

    public void supprimerPaiement() {
        System.out.print("\nID du paiement à supprimer : ");
        String idP = scanner.nextLine();
        paiementService.deletePaiement(idP);
    }

    public void afficherPaiementsAbonnement() {
        System.out.print("\nID de l'abonnement : ");
        String idAb = scanner.nextLine();
        List<Paiement> list = paiementService.getPaiementsByAbonnement(idAb);
        if (list.isEmpty()) {
            System.out.println("Aucun paiement pour cet abonnement.");
        } else {
            list.forEach(p -> System.out.println("Paiement ID: " + p.getIdPaiement() + " | Date Échéance: "
                    + p.getDateEcheance() + " | Statut: " + p.getStatut()));
        }
    }

    public void afficherSommePayeeAbonnement() {
        System.out.print("\nID de l'abonnement : ");
        String idAb = scanner.nextLine();
        double somme = paiementService.getSommePayeeParAbonnement(idAb);
        System.out.println("Somme totale payée pour l'abonnement " + idAb + " : " + somme + " DH");
    }

    public void afficherCinqDerniersPaiements() {
        System.out.println("\n--- Les 5 derniers paiements ---");
        List<Paiement> list = paiementService.getDerniersPaiements(5);
        if (list.isEmpty()) {
            System.out.println("Aucun paiement trouvé.");
        } else {
            list.forEach(p -> System.out.println(
                    "ID: " + p.getIdPaiement() + " | Date: " + p.getDateEcheance() + " | Statut: " + p.getStatut()));
        }
    }
}