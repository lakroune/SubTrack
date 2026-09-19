package org.subtrack.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.subtrack.config.DatabaseConnection;
import org.subtrack.enums.Statut;
import org.subtrack.models.Abonnement;
import org.subtrack.models.AbonnementAvecEngagement;
import org.subtrack.models.AbonnementSansEngagement;

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
            int rows = preparedStatement.executeUpdate();
            if (rows == 1)
                return a;
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Abonnement delete(Abonnement a) throws SQLException {
        String deleteSql = "DELETE FROM abonnements WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {

            preparedStatement.setString(1, a.getId());

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0)
                return a;

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Abonnement update(Abonnement a) throws SQLException {
        String updateSql = "UPDATE abonnements SET nomService = ?, montantMensuel = ?, dateDebut = ?, "
                + "dateFin = ?, statut = ?, typeAbonnement = ?, dureeEngagementMois = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setString(1, a.getNomService());
            preparedStatement.setDouble(2, a.getMontantMensuel());
            preparedStatement.setDate(3, Date.valueOf(a.getDateDebut()));

            preparedStatement.setDate(4, Date.valueOf(a.getDateFin()));
            preparedStatement.setString(5, a.getStatut().name());

            if (a instanceof AbonnementAvecEngagement) {
                AbonnementAvecEngagement avecEng = (AbonnementAvecEngagement) a;
                preparedStatement.setString(6, "AVEC_ENGAGEMENT");
                preparedStatement.setInt(7, avecEng.getDureeEngagementMois());
            } else {
                preparedStatement.setString(6, "SANS_ENGAGEMENT");
                preparedStatement.setNull(7, Types.INTEGER);
            }

            preparedStatement.setString(8, a.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                return a;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Abonnement get(String id) throws SQLException {
        String selectSql = "SELECT * FROM abonnements WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String idUser = resultSet.getString("idUser");
                String nomService = resultSet.getString("nomService");
                double montantMensuel = resultSet.getDouble("montantMensuel");

                LocalDate dateDebut = resultSet.getDate("dateDebut").toLocalDate();

                Date sqlDateFin = resultSet.getDate("dateFin");
                LocalDate dateFin = (sqlDateFin != null) ? sqlDateFin.toLocalDate() : null;

                Statut statut = Statut.valueOf(resultSet.getString("statut"));
                String typeAbonnement = resultSet.getString("typeAbonnement");

                if ("AVEC_ENGAGEMENT".equalsIgnoreCase(typeAbonnement)) {
                    int dureeEngagement = resultSet.getInt("dureeEngagementMois");
                    return new AbonnementAvecEngagement(idUser, nomService, montantMensuel, dateDebut, dateFin,
                            statut, dureeEngagement);
                } else {
                    return new AbonnementSansEngagement(idUser, nomService, montantMensuel, dateDebut, dateFin,
                            statut);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return null;
    }

    @Override
    public List<Abonnement> getAll() throws SQLException {
        List<Abonnement> abonnements = new ArrayList<>();
        String selectAllSql = "SELECT * FROM abonnements";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectAllSql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String idUser = resultSet.getString("idUser");
                String nomService = resultSet.getString("nomService");
                double montantMensuel = resultSet.getDouble("montantMensuel");

                LocalDate dateDebut = resultSet.getDate("dateDebut").toLocalDate();

                java.sql.Date sqlDateFin = resultSet.getDate("dateFin");
                LocalDate dateFin = (sqlDateFin != null) ? sqlDateFin.toLocalDate() : null;

                Statut statut = Statut.valueOf(resultSet.getString("statut"));
                String typeAbonnement = resultSet.getString("typeAbonnement");

                Abonnement abonnement;
                if ("AVEC_ENGAGEMENT".equalsIgnoreCase(typeAbonnement)) {
                    int dureeEngagement = resultSet.getInt("dureeEngagementMois");
                    abonnement = new AbonnementAvecEngagement(id, idUser, nomService, montantMensuel, dateDebut,
                            dateFin, statut, dureeEngagement);
                } else {
                    abonnement = new AbonnementSansEngagement(id, idUser, nomService, montantMensuel, dateDebut,
                            dateFin, statut);
                }

                abonnements.add(abonnement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return abonnements;
    }

}
