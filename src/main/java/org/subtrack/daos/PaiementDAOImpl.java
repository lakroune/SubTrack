package org.subtrack.daos;

import org.subtrack.config.DatabaseConnection;
import org.subtrack.enums.Statut;
import org.subtrack.models.Paiement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Paiement get(String idPaiement) throws SQLException {
        String selectSql = "SELECT * FROM paiements WHERE idPaiement = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            preparedStatement.setString(1, idPaiement);

            try (ResultSet resultat = preparedStatement.executeQuery()) {
                if (resultat.next()) {
                    Paiement p = new Paiement();
                    p.setIdPaiement(resultat.getString("idPaiement"));
                    p.setIdAbonnement(resultat.getString("idAbonnement"));

                    Date dateEcheance = resultat.getDate("dateEcheance");
                    if (dateEcheance != null) {
                        p.setDateEcheance(dateEcheance.toLocalDate());
                    }

                    Date datePaiement = resultat.getDate("datePaiement");
                    if (datePaiement != null) {
                        p.setDatePaiement(datePaiement.toLocalDate());
                    }

                    String typePaiement = resultat.getString("typePaiement");
                    if (typePaiement != null) {
                        p.setTypePaiement(typePaiement);
                    }

                    String statut = resultat.getString("statut");
                    if (statut != null) {
                        p.setStatut(Statut.valueOf(statut));
                    }

                    return p;
                }
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Paiement> getAll() throws SQLException {
        List<Paiement> list = new ArrayList<>();
        String selectSql = "SELECT * FROM paiements";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Paiement p = new Paiement();
                p.setIdPaiement(rs.getString("idPaiement"));
                p.setIdAbonnement(rs.getString("idAbonnement"));

                Date dateEcheance = rs.getDate("dateEcheance");
                if (dateEcheance != null) {
                    p.setDateEcheance(dateEcheance.toLocalDate());
                }

                Date datePaiement = rs.getDate("datePaiement");
                if (datePaiement != null) {
                    p.setDatePaiement(datePaiement.toLocalDate());
                }

                String typePaiement = rs.getString("typePaiement");
                if (typePaiement != null) {
                    p.setTypePaiement(typePaiement);
                }

                String statut = rs.getString("statut");
                if (statut != null) {
                    p.setStatut(Statut.valueOf(statut));
                }

                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return list;
    }

    
    
    public List<Paiement> findByAbonnement(String idAbonnement) {
        return new ArrayList<Paiement>();

    }

    public List<Paiement> findUnpaidByAbonnement(String idAbonnement) {
        return new ArrayList<Paiement>();

    }

    public List<Paiement> findLastPayments(int limit) {
        return new ArrayList<Paiement>();
    }

    public double getSommePayeeParAbonnement(String idAbonnement) {
        return 0;
    }

    public List<Paiement> getDerniersPaiements(int limit) {
        return new ArrayList<Paiement>();

    }
}