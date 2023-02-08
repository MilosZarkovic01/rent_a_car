/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import databasebroker.RentingDBBroker;
import domain.PDV;
import domain.PriceList;
import domain.PriceListItem;
import domain.Renting;
import enumeration.Currency;
import enumeration.TypeOfPriceListItem;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import repository.db.DBConnectionFactory;

/**
 *
 * @author Somika
 */
public class RepositoryDBRenting implements RentingDBBroker {
    
    @Override
    public List<Renting> getAll() throws Exception {
        List<Renting> rentings = new ArrayList<>();
        String sql = "SELECT * FROM renting r\n"
                + "INNER JOIN pricelistitem pli ON r.priceListItem_fk = pli.id\n"
                + "INNER JOIN pdv p ON pli.pdv_fk = p.id\n"
                + "INNER JOIN pricelist pl ON pli.priceList_id = pl.id";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Renting renting = new Renting();
            renting.setId(rs.getLong("id"));
            renting.setDateFrom(rs.getDate("dateFrom").toLocalDate());
            renting.setDateTo(rs.getDate("dateTo").toLocalDate());
            renting.setTotalAmount(BigDecimal.valueOf(rs.getDouble("totalAmount")));
            renting.setCurrency(Currency.valueOf(rs.getString("currency")));
            renting.setVehicle(RepositoryDBVehicle.getById(rs.getLong("vehicle_fk")));
            renting.setClient(RepositoryDBClient.getById(rs.getLong("client_fk")));
            
            PriceListItem pli = new PriceListItem();
            pli.setPrice(BigDecimal.valueOf(rs.getDouble("pli.price")));
            pli.setTypeOfPriceListItem(TypeOfPriceListItem.valueOf(rs.getString("pli.typeOfPriceListItem")));
            pli.setCurrency(Currency.valueOf(rs.getString("pli.currency")));
            
            PDV pdv = new PDV();
            pdv.setId(rs.getLong("pli.pdv_fk"));
            pdv.setPercent(rs.getDouble("p.percent"));
            
            pli.setPdv(pdv);
            pli.setTypeOfVehicle(RepositoryDBTypeOfVehicle.getById(rs.getLong("pli.typeOfVehicle_fk")));
            PriceList pl = new PriceList();
            pl.setDateFrom(rs.getDate("pl.dateFrom").toLocalDate());
            pl.setDateTo(rs.getDate("pl.dateTo").toLocalDate());
            pl.setId(rs.getLong("pli.priceList_id"));
            pli.setPriceList(pl);
            renting.setPriceListItem(pli);
            
            rentings.add(renting);
        }
        
        connection.commit();
        statement.close();
        connection.close();
        
        return rentings;
    }
    
}
