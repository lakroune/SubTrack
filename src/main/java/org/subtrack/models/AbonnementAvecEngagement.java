package org.subtrack.models;

import java.time.LocalDate;
import org.subtrack.enums.Statut;
public class AbonnementAvecEngagement extends Abonnement {
    private int dureeEngagementMois;

    public AbonnementAvecEngagement() {
        super();
    }

    public AbonnementAvecEngagement(String nomService, double montantMensuel, LocalDate dateDebut,
                                    LocalDate dateFin, Statut statut, int dureeEngagementMois) {
        super(nomService, montantMensuel, dateDebut, dateFin, statut);
        this.dureeEngagementMois = dureeEngagementMois;
    }

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
