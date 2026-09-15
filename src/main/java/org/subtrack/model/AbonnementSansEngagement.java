package org.subtrack.model;

import java.time.LocalDate;

public class AbonnementSansEngagement extends Abonnement {

    public AbonnementSansEngagement() {
        super();
    }

    public AbonnementSansEngagement(String nomService, double montantMensuel, LocalDate dateDebut,
                                    LocalDate dateFin, Statut statut) {
        super(nomService, montantMensuel, dateDebut, dateFin, statut);
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
