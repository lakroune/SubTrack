package org.subtrack.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.subtrack.config.DatabaseConnection;
import org.subtrack.models.Abonnement;

public class AbonnementDaoImpl implements DAO<Abonnement> {

    @Override
    public Abonnement insert(Abonnement abonnement) {
        String INSERT_SQL = "INSERT INTO Abonnement (id, nomService, montantMensuel, dateDebut, dateFin, statut) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

        preparedStatement.setString(1, abonnement.getId());
        preparedStatement.setString(2, abonnement.getIdUser());
        preparedStatement.setString(3, abonnement.getNomService());
        preparedStatement.setDouble(4, abonnement.getMontantMensuel());
        preparedStatement.setDate(5, Date.valueOf(abonnement.getDateDebut()));
        preparedStatement.setDate(6, Date.valueOf(abonnement.getDateFin()));
        preparedStatement.setString(7, abonnement.getStatut().toString());

        return null;

    }

    @Override
    public Abonnement save(Abonnement abonnement) {
        return null;
    }

    @Override
    public Abonnement delete(Abonnement abonnement) {
        return null;
    }

    @Override
    public Abonnement update(Abonnement abonnement) {
        return null;
    }

    @Override
    public Abonnement get(String id) {
        return null;
    }

    @Override
    public List<Abonnement> getAll() {
        return null;
    }
}
