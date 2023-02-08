/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package databasebroker;

import domain.Renting;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface RentingDBBroker {
    List<Renting> getAll() throws Exception;
}
