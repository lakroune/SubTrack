package org.subtrack.models;

import java.time.LocalDate;
import org.subtrack.enums.Statut;

public class AbonnementAvecEngagement extends Abonnement {
    private int dureeEngagementMois;

    /**
     * Constructeur par deefaut
     * 
     */
    public AbonnementAvecEngagement() {
        super();
    }

    /**
     * construct pour instance un object
     * 
     * @param idUser
     * @param nomService
     * @param montantMensuel
     * @param dateDebut
     * @param dateFin
     * @param statut
     * @param dureeEngagementMois
     */
    public AbonnementAvecEngagement(String idUser, String nomService, double montantMensuel, LocalDate dateDebut,
            LocalDate dateFin, Statut statut, int dureeEngagementMois) {
        super(idUser, nomService, montantMensuel, dateDebut, dateFin, statut);
        this.dureeEngagementMois = dureeEngagementMois;
    }

    /**
     * construct pour requeper un object
     * 
     * @param id
     * @param idUser
     * @param nomService
     * @param montantMensuel
     * @param dateDebut
     * @param dateFin
     * @param statut
     * @param dureeEngagementMois
     */

    public AbonnementAvecEngagement(String id,
            String idUser,
            String nomService,
            double montantMensuel,
            LocalDate dateDebut,
            LocalDate dateFin,
            Statut statut,
            int dureeEngagementMois) {
        super(id, idUser, nomService, montantMensuel, dateDebut, dateFin, statut);
        this.dureeEngagementMois = dureeEngagementMois;
    }

    /**
     * 
     * @return
     */

    public int getDureeEngagementMois() {
        return dureeEngagementMois;
    }

    public void setDureeEngagementMois(int dureeEngagementMois) {
        this.dureeEngagementMois = dureeEngagementMois;
    }

    @Override
    public double calculerCoutTotal() {
        return getMontantMensuel() * dureeEngagementMois;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "AbonnementAvecEngagement{" +
                "dureeEngagementMois=" + dureeEngagementMois +
                ", id='" + getId() + '\'' +
                ", nomService='" + getNomService() + '\'' +
                ", montantMensuel=" + getMontantMensuel() +
                ", dateDebut=" + getDateDebut() +
                ", dateFin=" + getDateFin() +
                ", statut=" + getStatut() +
                '}';
    }
}
