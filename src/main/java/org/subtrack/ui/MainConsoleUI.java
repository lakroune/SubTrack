package org.subtrack.ui;

import org.subtrack.services.AbonnementService;
import org.subtrack.services.PaiementService;

import java.util.Scanner;

public class MainConsoleUI {

    private Scanner scanner = new Scanner(System.in);
    private  AbonnementUI abonnementUI;
    private  PaiementUI paiementUI;
    private  RapportUI rapportUI;

    public MainConsoleUI() {
        AbonnementService abonnementService = new AbonnementService();
        PaiementService paiementService = new PaiementService();

        this.abonnementUI = new AbonnementUI(abonnementService, scanner);
        this.paiementUI = new PaiementUI(paiementService, scanner);
        this.rapportUI = new RapportUI(paiementService, scanner);
    }

    public void start() {
        boolean quitter = false;

        while (!quitter) {
            afficherMenu();
            System.out.print("Choisissez une option: ");
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":  abonnementUI.creerAbonnement(); break;
                case "2":  abonnementUI.modifierAbonnement(); break;
                case "3":  abonnementUI.supprimerAbonnement(); break;
                case "4":  abonnementUI.consulterAbonnements(); break;
                case "5":  paiementUI.afficherPaiementsAbonnement(); break;
                case "6":  paiementUI.enregistrerPaiement(); break;
                case "7":  paiementUI.modifierPaiement(); break;
                case "8":  paiementUI.supprimerPaiement(); break;
                case "9":  rapportUI.consulterPaiementsManquesEngagements(); break;
                case "10": paiementUI.afficherSommePayeeAbonnement(); break;
                case "11": paiementUI.afficherCinqDerniersPaiements(); break;
                case "12": rapportUI.genererRapportsFinanciers(); break;
                case "0":
                    quitter = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
            System.out.println("\n--------------------------------------------------\n");
        }
    }

    private void afficherMenu() {
        System.out.println("=== MENU PRINCIPAL - SUBTRACK ===");
        System.out.println("1.  Créer un abonnement (avec/sans engagement)");
        System.out.println("2.  Modifier un abonnement");
        System.out.println("3.  Supprimer un abonnement");
        System.out.println("4.  Consulter la liste des abonnements");
        System.out.println("5.  Afficher les paiements d'un abonnement");
        System.out.println("6.  Enregistrer un paiement");
        System.out.println("7.  Modifier un paiement");
        System.out.println("8.  Supprimer un paiement");
        System.out.println("9.  Consulter les paiements manqués + total impayé (Avec engagement)");
        System.out.println("10. Afficher la somme payée d'un abonnement");
        System.out.println("11. Afficher les 5 derniers paiements");
        System.out.println("12. Générer des rapports financiers (mensuels, annuels, impayés)");
        System.out.println("0.  Quitter");
    }

    
}