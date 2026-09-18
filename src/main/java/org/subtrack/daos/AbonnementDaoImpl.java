package org.subtrack.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.subtrack.config.DatabaseConnection;
import org.subtrack.models.Abonnement;

public class AbonnementDaoImpl implements DAO<Abonnement> {
    public AbonnementDaoImpl() {
    }

    @Override
    public Abonnement save(Abonnement a) throws SQLException {

        try {

            String INSERT_SQL = "INSERT INTO abonnements (id, nomService, montantMensuel, dateDebut, dateFin, statut) VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

            preparedStatement.setString(1, a.getId());
            preparedStatement.setString(2, a.getNomService());
            preparedStatement.setDouble(3, a.getMontantMensuel());
            preparedStatement.setDate(4, Date.valueOf(a.getDateDebut()));
            preparedStatement.setDate(5, Date.valueOf(a.getDateFin()));
            preparedStatement.setString(6, a.getStatut().toString());

            preparedStatement.executeUpdate();
            return a;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
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
            // String SELECT_SQL = "SELECT * FROM  abonnements";
            // Connection connection = DatabaseConnection.getConnection();
            // PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
            // ResultSet resultSet = preparedStatement.executeQuery();
            // while (resultSet.next()) {
            //     String id = resultSet.getString("id");
            //     String nomService = resultSet.getString("nomService");
            //     double montantMensuel = resultSet.getDouble("montantMensuel");
            //     Date dateDebut = resultSet.getDate("dateDebut");
            //     Date dateFin = resultSet.getDate("dateFin");
            //     String statut = resultSet.getString("statut");
            //     Abonnement abonnement = new Abonnement(id, nomService, montantMensuel, dateDebut.toLocalDate(), dateFin.toLocalDate(), Statut.valueOf(statut));
            //     System.out.println(abonnement);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
