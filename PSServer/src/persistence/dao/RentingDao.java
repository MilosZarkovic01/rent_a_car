/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence.dao;

import domain.Renting;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface RentingDao {
    List<Renting> getAll() throws Exception;
    void add (Renting renting) throws Exception;
    void delete(Renting renting) throws Exception;
}
