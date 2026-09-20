package org.subtrack.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.subtrack.daos.AbonnementDaoImpl;
import org.subtrack.enums.Statut;
import org.subtrack.models.Abonnement;
import org.subtrack.models.AbonnementAvecEngagement;
import org.subtrack.models.AbonnementSansEngagement;
import org.subtrack.models.Paiement;

/**
 * AbonnementService
 */
public class AbonnementService {
    private final AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();

    public void saveAbonnement(Abonnement abonnement) {

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

    public void deleteAbonnement(String id) {
        try {
            Abonnement abonnementDelete = abonnementDao.delete(abonnementDao.get(id));

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

    public boolean resilierAbonnement(String idAbonnement) {
        try {
            Abonnement abonnement = abonnementDao.get(idAbonnement);

            if (abonnement == null) {
                System.out.println("Abonnement introuvable avec l'ID : " + idAbonnement);
                return false;
            }

            abonnement.setStatut(Statut.RESILIE);
            abonnement.setDateFin(LocalDate.now());

            abonnementDao.update(abonnement);

            System.out.println("Abonnement résilié avec succès.");
            return true;

        } catch (Exception e) {
            System.out.println("Erreur lors de la résiliation de l'abonnement : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Abonnement> getAllAbonnements() {
        AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();
        try {
            return abonnementDao.getAll();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des abonnements : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Abonnement getAbonnement(String id) {

        try {
            AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();
            Abonnement abonnement = abonnementDao.get(id);

            if (abonnement == null) {
                System.out.println("Aucun abonnement trouvé avec l'ID : " + id);
            }
            return abonnement;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'abonnement : " + e.getMessage());
            return null;
        }

    }

    public List<Abonnement> findByType(String typeAbonnement) {
        try {
            List<Abonnement> abonnements = abonnementDao.getAll();

            return abonnements.stream()
                    .filter(a -> ("AVEC_ENGAGEMENT".equalsIgnoreCase(typeAbonnement)
                            && a instanceof AbonnementAvecEngagement)
                            || ("SANS_ENGAGEMENT".equalsIgnoreCase(typeAbonnement)
                                    && a instanceof AbonnementSansEngagement))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("Erreur de type : " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public List<Abonnement> findActiveSubscriptions() {
        try {
            List<Abonnement> abonnements = abonnementDao.getAll();

            return abonnements.stream()
                    .filter(a -> a.getStatut() == Statut.ACTIF)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche des abonnements actifs : " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}