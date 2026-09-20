package org.subtrack.daos;

import java.sql.SQLException;
import java.util.List;

import org.subtrack.models.Paiement;

public interface PaiementDAO extends DAO<Paiement> {
    List<Paiement> findByAbonnement(String idAbonnement) throws SQLException;

    List<Paiement> findUnpaidByAbonnement(String idAbonnement) throws SQLException;

    List<Paiement> findLastPayments(int limit) throws SQLException;
}
