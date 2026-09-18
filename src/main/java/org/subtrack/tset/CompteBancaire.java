package org.subtrack.tset;

public class CompteBancaire {

    private double solde;

    public CompteBancaire(double solde) {
        this.solde = solde;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void retirer(double montant) throws SoldeInsuffisantException {

        if (montant > solde) {
            throw new SoldeInsuffisantException("Solde insuffisant");
        }
        solde -= montant;
        System.out.println("Solde après retrait : " + solde);
    }

    public static void main(String[] args) {
        CompteBancaire compte = new CompteBancaire(1000.0);
        try {
            compte.retirer(991.0);
        } catch (SoldeInsuffisantException e) {
            System.out.println("Exception : " + e.getMessage());
        }

    }
}
