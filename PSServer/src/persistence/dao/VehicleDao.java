/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence.dao;

import domain.Vehicle;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface VehicleDao {
    void add(Vehicle vehicle) throws Exception;
    void update(Vehicle vehicle) throws Exception;
    void delete(Vehicle vehicle)throws Exception;
    List<Vehicle> getAll();
    List<Vehicle> getAvailable();
}
