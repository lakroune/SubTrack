package org.subtrack.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Abonnement {
    private String id;
    private String nomService;
    private double montantMensuel;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Statut statut;

    public enum Statut {
        Active,
        Suspendu,
        Résilié
    }

    public Abonnement() {
        this.id = UUID.randomUUID().toString();
    }

    public Abonnement(String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, Statut statut) {
        this.id = UUID.randomUUID().toString();
        this.nomService = nomService;
        this.montantMensuel = montantMensuel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }

    public String getId() {
        return id;
    }

    public String getNomService() {
        return nomService;
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
