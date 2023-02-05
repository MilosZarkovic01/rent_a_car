/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package databasebroker;

import domain.Client;
import domain.Renting;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface ClientDBBroker {
    List<Client> getAllClients();
    void update(Client client) throws Exception;
    void add(Client client) throws Exception;
    List<Renting> getClientRentings(Client client) throws Exception;
}
