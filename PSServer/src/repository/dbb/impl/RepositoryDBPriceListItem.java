/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.dbb.impl;

import repository.dbb.PriceListItemDBBroker;
import domain.PDV;
import domain.PriceList;
import domain.PriceListItem;
import domain.TypeOfVehicle;
import enumeration.Currency;
import enumeration.TypeOfPriceListItem;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import repository.conn.DBConnectionFactory;

/**
 *
 * @author Somika
 */
public class RepositoryDBPriceListItem implements PriceListItemDBBroker {
    
    @Override
    public List<PriceListItem> getPriceListItems(TypeOfVehicle tov) throws Exception {
        List<PriceListItem> priceListItems = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM pricelistitem pli\n"
                + "INNER JOIN pricelist pl ON pli.priceList_id = pl.id\n"
                + "INNER JOIN pdv pdv ON pli.pdv_fk = pdv.id\n"
                + "WHERE pl.dateFrom <= CURRENT_DATE AND pl.dateTo >= CURRENT_DATE\n"
                + "AND pli.typeOfVehicle_fk = ?";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tov.getId());
        
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            PriceListItem pli = new PriceListItem();            
            pli.setId(rs.getLong("pli.id"));
            
            PriceList pl = new PriceList();
            pl.setId(rs.getLong("pl.id"));
            pl.setDateFrom(rs.getDate("pl.dateFrom").toLocalDate());
            pl.setDateTo(rs.getDate("pl.dateTo").toLocalDate());
            pli.setPriceList(pl);
            
            pli.setPrice(BigDecimal.valueOf(rs.getDouble("pli.price")));
            pli.setTypeOfPriceListItem(TypeOfPriceListItem.valueOf(rs.getString("pli.typeOfPriceListItem")));
            pli.setCurrency(Currency.valueOf(rs.getString("pli.currency")));
            
            PDV pdv = new PDV();
            pdv.setId(rs.getLong("pdv.id"));
            pdv.setPercent(rs.getDouble("pdv.percent"));
            pli.setPdv(pdv);
            
            pli.setTypeOfVehicle(tov);
            
            priceListItems.add(pli);
        }
        
        connection.commit();
        preparedStatement.close();
        connection.close();
        return priceListItems;
    }
    
}
