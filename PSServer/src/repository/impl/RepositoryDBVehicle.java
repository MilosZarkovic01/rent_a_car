/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domain.TypeOfVehicle;
import domain.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBConnectionFactory;
import databasebroker.VehicleDBBroker;

/**
 *
 * @author Somika
 */
public class RepositoryDBVehicle implements VehicleDBBroker {

    @Override
    public void add(Vehicle vehicle) throws Exception {
        System.out.println(vehicle.toString());
        try {
            String sql = "INSERT INTO vehicle(brand, model, mileage, availability, typeOfVehicle_fk) VALUES(?,?,?,?,?);";

            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getMileage());
            preparedStatement.setBoolean(4, vehicle.isAvailability());
            preparedStatement.setLong(5, vehicle.getTypeOfVehicle().getId());

            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Vehicle vehicle) throws Exception {
        try {
            String sql = "UPDATE vehicle SET "
                    + "brand='" + vehicle.getBrand() + "', "
                    + "model='" + vehicle.getModel() + "', "
                    + "mileage=" + vehicle.getMileage() + ","
                    + "availability=" + vehicle.isAvailability() + ","
                    + "typeOfVehicle_fk=" + vehicle.getTypeOfVehicle().getId() + " "
                    + "WHERE id=" + vehicle.getId();
            //System.out.println(sql);
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Update vehicle DB error: \n" + ex.getMessage());
        }
    }

    @Override
    public void delete(Vehicle vehicle) throws Exception {
        try {
            String sql = "DELETE FROM vehicle WHERE id = ?";

            Connection connection = DBConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, vehicle.getId());
            preparedStatement.execute();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Vehicle> getAll() {
        try {
            String sql = "SELECT v.id, brand, model, mileage, availability, typeOfVehicle_fk, tov.name FROM vehicle v INNER JOIN typeofvehicle tov ON v.typeOfVehicle_fk = tov.id";

            List<Vehicle> vehicles = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getLong("v.id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMileage(rs.getInt("mileage"));
                vehicle.setAvailability(rs.getBoolean("availability"));

                TypeOfVehicle tov = new TypeOfVehicle(rs.getLong("typeOfVehicle_fk"), rs.getString("tov.name"));
                vehicle.setTypeOfVehicle(tov);
                vehicles.add(vehicle);
            }
            rs.close();
            statement.close();
            connection.close();
            return vehicles;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
