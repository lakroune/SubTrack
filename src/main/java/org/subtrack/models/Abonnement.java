package org.subtrack.models;

import java.time.LocalDate;
import java.util.UUID;
import org.subtrack.enums.Statut;

public abstract class Abonnement {
    protected String id;
    protected String idUser;
    protected String nomService;
    protected double montantMensuel;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected Statut statut;

    public Abonnement() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Constructeur
     * 
     * @param idUser
     * @param nomService
     * @param montantMensuel
     * @param dateDebut
     * @param dateFin
     * @param statut
     */

    public Abonnement(String idUser, String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin,
            Statut statut) {
        this.id = UUID.randomUUID().toString();
        this.idUser = idUser;
        this.nomService = nomService;
        this.montantMensuel = montantMensuel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNomService() {
        return nomService;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(double montantMensuel) {
        this.montantMensuel = montantMensuel;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public abstract double calculerCoutTotal();

    @Override
    public String toString() {
        return "Abonnement{" +
                "id='" + id + '\'' +
                ", nomService='" + nomService + '\'' +
                ", montantMensuel=" + montantMensuel +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", statut=" + statut +
                '}';
    }
}
