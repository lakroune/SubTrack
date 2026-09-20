package org.subtrack.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.subtrack.daos.AbonnementDaoImpl;
import org.subtrack.daos.PaiementDAOImpl;
import org.subtrack.enums.Statut;
import org.subtrack.models.Abonnement;
import org.subtrack.models.Paiement;

public class PaiementService {

    private final PaiementDAOImpl paiementDAO = new PaiementDAOImpl();

    public Paiement savePaiement(Paiement p) {
        if (p == null) {
            System.out.println("Erreur ");
            return null;
        }

        if (p.getIdAbonnement() == null || p.getIdAbonnement().trim().isEmpty()) {
            System.out.println("Erreur : L'ID de l'abonnement  n'existe pas.");
            return null;
        }

        try {
            Paiement nouveauPaiement = paiementDAO.save(p);
            return nouveauPaiement;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement du paiement : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Paiement getPaiement(String idPaiement) {
        try {
            return paiementDAO.get(idPaiement);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du paiement : " + e.getMessage());
            return null;
        }
    }

    /**
     * 
     * @param p
     */
    public void updatePaiement(Paiement p) {
        try {
            paiementDAO.update(p);
            System.out.println("Paiement mis à jour avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du paiement : " + e.getMessage());
        }
    }

    /**
     * 
     * @param idPaiement
     */
    public void deletePaiement(String idPaiement) {
        try {
            Paiement p = paiementDAO.get(idPaiement);
            if (p != null) {
                paiementDAO.delete(p);
                System.out.println("Paiement supprimé avec succès.");
            } else {
                System.out.println("Impossible de supprimer : paiement introuvable.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du paiement : " + e.getMessage());
        }
    }

    public List<Paiement> getPaiementsByAbonnement(String idAbonnement) {
        try {
            List<Paiement> paiementList = paiementDAO.getAll();

            return paiementList.stream()
                    .filter(p -> p.getIdAbonnement() != null && p.getIdAbonnement().equals(idAbonnement))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("Erreur  : " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public double getSommePayeeParAbonnement(String idAbonnement) {
        try {
            AbonnementDaoImpl abonnementDAO = new AbonnementDaoImpl();
            Abonnement abonnement = abonnementDAO.get(idAbonnement);

            if (abonnement == null) {
                System.out.println("Abonnement introuvable pour l'ID : " + idAbonnement);
                return 0.0;
            }

            double montantMensuel = abonnement.getMontantMensuel();
            List<Paiement> paiementList = paiementDAO.getAll();

            return paiementList.stream()
                    .filter(p -> p.getIdAbonnement() != null && p.getIdAbonnement().equals(idAbonnement))
                    .filter(p -> p.getStatut() == Statut.PAYE)
                    .mapToDouble(p -> montantMensuel)
                    .sum();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }

    public List<Paiement> getDerniersPaiements(int limit) {
        try {
            List<Paiement> paiementList = paiementDAO.getAll();

            return paiementList.stream()
                    .filter(p -> p.getDatePaiement() != null)
                    .sorted(Comparator.comparing(Paiement::getDatePaiement).reversed())
                    .limit(limit)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void afficherPaiementsManquesAvecTotalImpaye() {
        try {
            List<Paiement> paiementList = paiementDAO.getAll();
            AbonnementDaoImpl abonnementDAO = new AbonnementDaoImpl();

            List<Paiement> paiementsManques = paiementList.stream()
                    .filter(p -> p.getStatut() == Statut.EN_RETARD)
                    .collect(Collectors.toList());

            if (paiementsManques.isEmpty()) {
                System.out.println("Aucun paiement manqué ou en retard trouvé.");
                return;
            }

            System.out.println("=== Liste des paiements manqués ===");
            double totalImpaye = 0.0;

            for (Paiement p : paiementsManques) {
                Abonnement abonnement = abonnementDAO.get(p.getIdAbonnement());
                double montant = (abonnement != null) ? abonnement.getMontantMensuel() : 0.0;

                totalImpaye += montant;

                System.out.println("- Paiement ID: " + p.getIdAbonnement()
                        + " | Abonnement: " + (abonnement != null ? abonnement.getNomService() : p.getIdAbonnement())
                        + " | Date echéance: " + p.getDateEcheance()
                        + " | Montant: " + montant + " DH");
            }

            System.out.println("-----------------------------------");
            System.out.println("TOTAL DES IMPAYÉS : " + totalImpaye + " DH");
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des impayés : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void genererRapportMensuel(int mois, int annee) {

    }

    public void genererRapportAnnuel(int annee) {

    }

    public void genererRapportImpayes() {

    }
}