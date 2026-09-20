package org.subtrack.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.subtrack.enums.Statut;
import org.subtrack.models.Abonnement;
import org.subtrack.models.AbonnementAvecEngagement;
import org.subtrack.models.AbonnementSansEngagement;
import org.subtrack.services.AbonnementService;

public class AbonnementUI {

    private final AbonnementService abonnementService;
    private final Scanner scanner;

    public AbonnementUI(AbonnementService abonnementService, Scanner scanner) {
        this.abonnementService = abonnementService;
        this.scanner = scanner;
    }

    public void creerAbonnement() {
        System.out.println("\n--- Créer un Abonnement ---");
        System.out.print("ID Utilisateur : ");
        String idUser = scanner.nextLine();
        System.out.print("Nom du service (ex: Netflix, Spotify) : ");
        String nomService = scanner.nextLine();
        System.out.print("Montant mensuel : ");
        double montant = Double.parseDouble(scanner.nextLine());

        System.out.println("Type d'abonnement : 1. Avec Engagement | 2. Sans Engagement");
        String type = scanner.nextLine();

        Abonnement abonnement;

        if ("1".equals(type)) {
            System.out.print("Durée d'engagement (mois) : ");
            int duree = Integer.parseInt(scanner.nextLine());
            abonnement = new AbonnementAvecEngagement(idUser, nomService, montant, LocalDate.now(), LocalDate.now(),
                    Statut.ACTIF, duree);
        } else {
            abonnement = new AbonnementSansEngagement(idUser, nomService, montant, LocalDate.now(), LocalDate.now(),
                    Statut.ACTIF);
        }

        abonnementService.saveAbonnement(abonnement);
        System.out.println("Abonnement créé avec succès (ID: " + abonnement.getId() + ")");
    }

    public void modifierAbonnement() {
        System.out.print("\nID de l'abonnement à modifier : ");
        String id = scanner.nextLine();
        Abonnement abonnement = abonnementService.getAbonnement(id);
        if (abonnement == null) {
            System.out.println("Abonnement introuvable.");
            return;
        }

        System.out.print("Nouveau nom du service (" + abonnement.getNomService() + ") : ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty())
            abonnement.setNomService(nom);

        System.out.print("Nouveau montant (" + abonnement.getMontantMensuel() + ") : ");
        String montantStr = scanner.nextLine();
        if (!montantStr.isEmpty())
            abonnement.setMontantMensuel(Double.parseDouble(montantStr));

        abonnementService.updateAbonnement(abonnement);
    }

    public void supprimerAbonnement() {
        System.out.print("\nID de l'abonnement à supprimer : ");
        String id = scanner.nextLine();
        abonnementService.deleteAbonnement(id);
    }

    public void consulterAbonnements() {
        System.out.println("\n--- Liste des Abonnements ---");
        List<Abonnement> list = abonnementService.getAllAbonnements();
        if (list.isEmpty()) {
            System.out.println("Aucun abonnement trouvé.");
        } else {
            list.forEach(a -> System.out.println("ID: " + a.getId() + " | Service: " + a.getNomService()
                    + " | Montant: " + a.getMontantMensuel() + " DH | Statut: " + a.getStatut()));
        }
    }
}