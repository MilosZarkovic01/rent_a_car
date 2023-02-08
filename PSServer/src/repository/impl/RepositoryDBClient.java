/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import databasebroker.ClientDBBroker;
import domain.Client;
import domain.Renting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBConnectionFactory;

/**
 *
 * @author Somika
 */
public class RepositoryDBClient implements ClientDBBroker {

    @Override
    public List<Client> getAllClients() {
        try {
            String sql = "SELECT id, firstname, lastname, telNumber FROM client";

            List<Client> clients = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setFirstName(rs.getString("firstname"));
                client.setLastName(rs.getString("lastname"));
                client.setTelNumber(rs.getString("telNumber"));
                clients.add(client);
            }
            rs.close();
            statement.close();
            connection.close();
            return clients;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(Client client) throws Exception {
        String sql = "UPDATE client SET "
                + "firstname='" + client.getFirstName() + "', "
                + "lastname='" + client.getLastName() + "', "
                + "telNumber='" + client.getTelNumber() + "' "
                + "WHERE id=" + client.getId();
        //System.out.println(sql);
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        connection.commit();
        statement.close();
        connection.close();
    }

    @Override
    public void add(Client client) throws Exception {
        String sql = "INSERT INTO client(firstname, lastname, telNumber) VALUES(?,?,?);";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setString(3, client.getTelNumber());
        preparedStatement.executeUpdate();

        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public List<Renting> getClientRentings(Client client) throws Exception {
        List<Renting> clientRentings = new ArrayList<>();
        String sql = "SELECT vehicle_fk FROM renting WHERE client_fk = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, client.getId());

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Renting renting = new Renting();
            renting.setVehicle(RepositoryDBVehicle.getById(rs.getLong("vehicle_fk")));
            clientRentings.add(renting);
        }
        connection.commit();
        preparedStatement.close();
        connection.close();
        return clientRentings;
    }

    public static Client getById(Long id) throws Exception {
        Client client = new Client();
        String sql = "SELECT firstname, lastname, telNumber FROM client WHERE id = ?;";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            client.setId(id);
            client.setFirstName(rs.getString("firstname"));
            client.setLastName(rs.getString("lastname"));
            client.setTelNumber(rs.getString("telNumber"));
        }
        connection.commit();

        return client;
    }

}
