/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import databasebroker.ClientDBBroker;
import domain.Client;
import java.sql.Connection;
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
        try {
            String sql = "UPDATE client SET "
                    + "firstname='" + client.getFirstName() + "', "
                    + "lastname='" + client.getLastName() + "', "
                    + "telNumber='" + client.getTelNumber() + "' "
                    + "WHERE id=" + client.getId();
            System.out.println(sql);
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update client DB error: \n" + ex.getMessage());
        }
    }

}
