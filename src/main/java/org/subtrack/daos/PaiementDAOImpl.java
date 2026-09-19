package org.subtrack.daos;

import org.subtrack.config.DatabaseConnection;
import org.subtrack.models.Paiement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PaiementDAOImpl implements PaiementDAO {


    @Override
    public Paiement save(Paiement p) throws SQLException {
        String insertSql = "INSERT INTO paiements (idPaiement, idAbonnement, dateEcheance, datePaiement, typePaiement, statut) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setString(1, p.getIdPaiement());
            preparedStatement.setString(2, p.getIdAbonnement());
            preparedStatement.setDate(3, Date.valueOf(p.getDateEcheance()));

            if (p.getDatePaiement() != null) {
                preparedStatement.setDate(4, Date.valueOf(p.getDatePaiement()));
            } else {
                preparedStatement.setNull(4, Types.DATE);
            }

            preparedStatement.setString(5, p.getTypePaiement() != null ? p.getTypePaiement().toString() : null);
            preparedStatement.setString(6, p.getStatut().name());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return p;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Paiement delete(Paiement p) throws SQLException {
        String deleteSql = "DELETE FROM paiements WHERE idPaiement = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setString(1, p.getIdPaiement());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return p;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public Paiement update(Paiement p) throws SQLException {
        String updateSql = "UPDATE paiements SET idAbonnement = ?, dateEcheance = ?, datePaiement = ?, typePaiement = ?, statut = ? "
                + "WHERE idPaiement = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setString(1, p.getIdAbonnement());
            preparedStatement.setDate(2, Date.valueOf(p.getDateEcheance()));

            if (p.getDatePaiement() != null) {
                preparedStatement.setDate(3, Date.valueOf(p.getDatePaiement()));
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }

            preparedStatement.setString(4, p.getTypePaiement() != null ? p.getTypePaiement().toString() : null);
            preparedStatement.setString(5, p.getStatut().name());
            preparedStatement.setString(6, p.getIdPaiement());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return p;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}