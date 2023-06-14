/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence.dao.impl;

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
import java.util.Date;
import persistence.dao.PriceListItemDao;

/**
 *
 * @author Somika
 */
public class PriceListItemDaoImpl implements PriceListItemDao {

    @Override
    public List<PriceListItem> getPriceListItems(TypeOfVehicle tov, Date dateFrom) throws Exception {
        List<PriceListItem> priceListItems = new ArrayList<>();
        
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        
        String maxPriceListQuery = "SELECT * "
                + "FROM pricelist "
                + "WHERE dateFrom <= ? "
                + "ORDER BY dateFrom DESC "
                + "LIMIT 1";
        
        PreparedStatement maxPriceListStatement = connection.prepareStatement(maxPriceListQuery);
        maxPriceListStatement.setDate(1, new java.sql.Date(dateFrom.getTime()));
        ResultSet maxPriceListResult = maxPriceListStatement.executeQuery();
        Long maxPriceListId = null;
        if (maxPriceListResult.next()) {
            maxPriceListId = maxPriceListResult.getLong("id");
        }
        maxPriceListResult.close();
        maxPriceListStatement.close();

        String sql = "SELECT * "
                + "FROM pricelistitem pli "
                + "INNER JOIN pricelist pl ON pli.priceList_id = pl.id "
                + "INNER JOIN pdv pdv ON pli.pdv_fk = pdv.id "
                + "WHERE pli.typeOfVehicle_fk = ? "
                + "AND pli.priceList_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tov.getId());
        preparedStatement.setLong(2, maxPriceListId);

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
