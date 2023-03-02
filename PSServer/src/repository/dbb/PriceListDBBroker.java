/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.dbb;

import domain.PriceList;

/**
 *
 * @author Somika
 */
public interface PriceListDBBroker {
    void add(PriceList pl) throws Exception;
}
