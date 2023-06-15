/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistence.dao;

import domain.PriceListItem;
import domain.TypeOfVehicle;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface PriceListItemDao {

    List<PriceListItem> getPriceListItems(TypeOfVehicle tov, Date dateFrom) throws Exception;
    void savePriceListItem(PriceListItem item) throws Exception;
}
