/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.dbb.impl;

import domain.PriceList;
import domain.PriceListItem;
import repository.dbb.PriceListDBBroker;
import java.sql.*;
import repository.conn.DBConnectionFactory;
/**
 *
 * @author Somika
 */
public class RepositoryDBPriceList implements PriceListDBBroker{

    @Override
    public void add(PriceList pl) throws Exception {
         try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "INSERT INTO pricelist(dateFrom,dateTo) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(pl.getDateFrom()));
            statement.setDate(2, Date.valueOf(pl.getDateTo()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong(1);
                pl.setId(id);
                query = "INSERT INTO pricelistitem(price, typeOfPriceListItem, currency, pdv_fk, typeOfVehicle_fk, priceList_id) VALUES (?,?,?,?,?,?)";
                statement = connection.prepareStatement(query);
                for (PriceListItem item : pl.getPriceListItems()) {
                    statement.setDouble(1, item.getPrice().doubleValue());
                    statement.setString(2, item.getTypeOfPriceListItem().toString());
                    statement.setString(3, item.getCurrency().toString());
                    statement.setLong(4, item.getPdv().getId());
                    statement.setLong(5, item.getTypeOfVehicle().getId());
                    statement.setLong(6, pl.getId());
                    statement.executeUpdate();
                }
            } else {
                throw new Exception("Invoice id is not generated!");
            }
            rs.close();
            statement.close();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
}
