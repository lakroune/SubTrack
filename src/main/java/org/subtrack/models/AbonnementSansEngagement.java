package org.subtrack.models;

import java.time.LocalDate;
import org.subtrack.enums.Statut;

public class AbonnementSansEngagement extends Abonnement {
    /**
     * 
     */
    public AbonnementSansEngagement() {
        super();
    }

    /**
     * 
     * @param idUser
     * @param nomService
     * @param montantMensuel
     * @param dateDebut
     * @param dateFin
     * @param statut
     */
    public AbonnementSansEngagement(String idUser, String nomService, double montantMensuel, LocalDate dateDebut,
            LocalDate dateFin, Statut statut) {
        super(idUser, nomService, montantMensuel, dateDebut, dateFin, statut);
    }

    /**
     * 
     * @param id
     * @param idUser
     * @param nomService
     * @param montantMensuel
     * @param dateDebut
     * @param dateFin
     * @param statut
     */
    public AbonnementSansEngagement(String id, String idUser, String nomService, double montantMensuel,
            LocalDate dateDebut,
            LocalDate dateFin, Statut statut) {
        super(id, idUser, nomService, montantMensuel, dateDebut, dateFin, statut);
    }

    @Override
    public double calculerCoutTotal() {
        return getMontantMensuel();
    }

    @Override
    public String toString() {
        return "AbonnementSansEngagement{" +
                "id='" + getId() + '\'' +
                ", nomService='" + getNomService() + '\'' +
                ", montantMensuel=" + getMontantMensuel() +
                ", dateDebut=" + getDateDebut() +
                ", dateFin=" + getDateFin() +
                ", statut=" + getStatut() +
                '}';
    }
}
