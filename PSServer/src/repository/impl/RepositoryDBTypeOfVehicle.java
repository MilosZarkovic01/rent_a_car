/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import databasebroker.TypeOfVehicleDBBroker;
import domain.TypeOfVehicle;
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
public class RepositoryDBTypeOfVehicle implements TypeOfVehicleDBBroker {

    @Override
    public List<TypeOfVehicle> getAll() {
        try {
            String sql = "SELECT * FROM typeofvehicle";
            List<TypeOfVehicle> types = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                TypeOfVehicle type = new TypeOfVehicle();
                type.setId(rs.getLong("id"));
                type.setName(rs.getString("name"));
                types.add(type);
            }
            rs.close();
            statement.close();
            return types;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static TypeOfVehicle getById(Long id) throws Exception {
        TypeOfVehicle tov = new TypeOfVehicle();
        String sql = "SELECT name FROM typeofvehicle WHERE id = ?;";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            tov.setId(id);
            tov.setName(rs.getString("name"));
        }

        connection.commit();
        return tov;
    }

}
