/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.dbb;

import domain.PriceListItem;
import domain.TypeOfVehicle;
import java.util.List;

/**
 *
 * @author Somika
 */
public interface PriceListItemDBBroker {
    List<PriceListItem> getPriceListItems(TypeOfVehicle tov) throws Exception;
}
