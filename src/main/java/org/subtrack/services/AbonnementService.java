package org.subtrack.services;

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
          Abonnement abonnementEnregistre =  abonnementDao.save(abonnement);
          
            System.out.println("Abonnement enregistré");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    // get abonnements

    public void updateAbonnement(Abonnement abonnement ){
        
    }
      public void deleteAbonnement(Abonnement abonnement ){
        
    }

      public void realiseAbonnement(Abonnement abonnement ){
        
    }
    public void getAll() {
        AbonnementDaoImpl abonnementDao = new AbonnementDaoImpl();
        try {
            List<Abonnement> abonnements = abonnementDao.getAll();
            System.out.println(abonnements);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

}