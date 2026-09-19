package org.subtrack.services;

import java.sql.SQLException;
import java.util.List;

import org.subtrack.daos.AbonnementDaoImpl;
import org.subtrack.models.Abonnement;

/**
 * AbonnementService
 */
public class AbonnementService {

    public void saveAbonnement(Abonnement abonnement) {
        AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();
        try {
            Abonnement abonnementEnregistre = abonnementDao.save(abonnement);

            if (abonnementEnregistre != null)
                System.out.println("Abonnement etes ajouter ");
            else
                System.out.println("Abonnement non ajouter ");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void deleteAbonnement(Abonnement abonnement) {
        AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();
        try {
            Abonnement abonnementDelete = abonnementDao.delete(abonnement);

            if (abonnementDelete != null) {
                System.out.println("Abonnement supprimé .");
            } else {
                System.out.println("Abonnement non supprimé ");
            }

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void updateAbonnement(Abonnement abonnement) {
        AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();
        try {
            Abonnement abonnementUpdated = abonnementDao.update(abonnement);

            if (abonnementUpdated != null) {
                System.out.println("Abonnement mis à jour .");
            } else {
                System.out.println("Abonnement non mis à jour ");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    public void realiseAbonnement(Abonnement abonnement) {

    }

    public void getAll() {
        AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();
        try {
            List<Abonnement> abonnements = abonnementDao.getAll();

            if (abonnements.isEmpty()) {
                System.out.println("Aucun abonnement trouvé.");
            } else {
                System.out.println("=== Liste des Abonnements ===");
                for (Abonnement a : abonnements) {
                    System.out.println(a);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

}