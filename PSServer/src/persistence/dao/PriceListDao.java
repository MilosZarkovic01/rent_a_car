/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence.dao;

import domain.PriceList;

/**
 *
 * @author Somika
 */
public interface PriceListDao {
    Long save(PriceList pl) throws Exception;
}
