package org.subtrack.tset;

public class Livre {
    private String titre;
    private double prix;
    private String auteur;

    public Livre(String titre, double prix, String auteur) {
        this.titre = titre;
        this.prix = prix;
        this.auteur = auteur;
    }

    public String getTitre() { return titre; }
    public double getPrix() { return prix; }
    public String getAuteur() { return auteur; }

    @Override
    public String toString() {
        return "Livre{titre='" + titre + "', prix=" + prix + " €, auteur='" + auteur + "'}";
    }
}
