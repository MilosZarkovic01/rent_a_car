/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.dbb;

import domain.Administrator;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface AdministratorDBBroker {
    List<Administrator> getAll();
}
