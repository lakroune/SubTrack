package org.subtrack.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

import org.subtrack.config.DatabaseConnection;
import org.subtrack.models.Abonnement;
import org.subtrack.models.AbonnementAvecEngagement;

public class AbonnementDaoImpl implements DAO<Abonnement> {
    public AbonnementDaoImpl() {
    }

    @Override
    public Abonnement save(Abonnement a) throws SQLException {
        String insertSql = "INSERT INTO abonnements (id, idUser, nomService, montantMensuel, dateDebut, dateFin, statut, typeAbonnement, dureeEngagementMois) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

            preparedStatement.setString(1, a.getId());
            preparedStatement.setString(2, a.getIdUser());
            preparedStatement.setString(3, a.getNomService());
            preparedStatement.setDouble(4, a.getMontantMensuel());
            preparedStatement.setDate(5, Date.valueOf(a.getDateDebut()));
            preparedStatement.setDate(6, a.getDateFin() != null ? Date.valueOf(a.getDateFin()) : null);
            preparedStatement.setString(7, a.getStatut().name());

            if (a instanceof AbonnementAvecEngagement) {
                AbonnementAvecEngagement avecEng = (AbonnementAvecEngagement) a;
                preparedStatement.setString(8, "AVEC_ENGAGEMENT");
                preparedStatement.setInt(9, avecEng.getDureeEngagementMois());
            } else {
                preparedStatement.setString(8, "SANS_ENGAGEMENT");
                preparedStatement.setNull(9, Types.INTEGER);
            }

            preparedStatement.executeUpdate();
            return a;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Abonnement delete(Abonnement a) throws SQLException {

        try {

            String DELETE_SQL = "DELETE FROM  abonnements WHERE id = ?";
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);

            preparedStatement.setString(1, a.getId());

            preparedStatement.executeUpdate();
            return a;

        } catch (Exception e) {

        }
        return null;
    }

    //
    public Abonnement update(Abonnement a) throws SQLException {
        try {

            String UPDATE_SQL = "UPDATE Abonnement SET nomService = ?, montantMensuel = ?, dateDebut = ?, dateFin = ?, statut = ? WHERE id = ?";
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);

            preparedStatement.setString(1, a.getNomService());
            preparedStatement.setDouble(2, a.getMontantMensuel());
            preparedStatement.setDate(3, Date.valueOf(a.getDateDebut()));
            preparedStatement.setDate(4, Date.valueOf(a.getDateFin()));
            preparedStatement.setString(5, a.getStatut().toString());
            preparedStatement.setString(6, a.getId());

            preparedStatement.executeUpdate();
            return a;

        } catch (Exception e) {

        }
        return null;
    }

    public Abonnement get(String id) throws SQLException {

        try {
            String SELECT_SQL = "SELECT * FROM  abonnements WHERE id = ?";
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
            preparedStatement.setString(1, id);
            preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Abonnement> getAll() throws SQLException {

        try {
            // String SELECT_SQL = "SELECT * FROM abonnements";
            // Connection connection = DatabaseConnection.getConnection();
            // PreparedStatement preparedStatement =
            // connection.prepareStatement(SELECT_SQL);
            // ResultSet resultSet = preparedStatement.executeQuery();
            // while (resultSet.next()) {
            // String id = resultSet.getString("id");
            // String nomService = resultSet.getString("nomService");
            // double montantMensuel = resultSet.getDouble("montantMensuel");
            // Date dateDebut = resultSet.getDate("dateDebut");
            // Date dateFin = resultSet.getDate("dateFin");
            // String statut = resultSet.getString("statut");
            // Abonnement abonnement = new Abonnement(id, nomService, montantMensuel,
            // dateDebut.toLocalDate(), dateFin.toLocalDate(), Statut.valueOf(statut));
            // System.out.println(abonnement);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
