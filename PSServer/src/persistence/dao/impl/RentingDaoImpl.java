/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence.dao.impl;

import domain.Client;
import domain.PDV;
import domain.PriceList;
import domain.PriceListItem;
import domain.Renting;
import domain.TypeOfVehicle;
import domain.Vehicle;
import enumeration.Currency;
import enumeration.TypeOfPriceListItem;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import repository.conn.DBConnectionFactory;
import persistence.dao.RentingDao;

/**
 *
 * @author Somika
 */
public class RentingDaoImpl implements RentingDao {

    @Override
    public List<Renting> getAll() throws Exception {
        List<Renting> rentings = new ArrayList<>();
        String sql = "SELECT * FROM renting r\n"
                + "INNER JOIN client c ON r.client_fk = c.id\n"
                + "INNER JOIN vehicle v ON r.vehicle_fk = v.id\n"
                + "INNER JOIN typeofvehicle tov ON v.typeOfVehicle_fk = tov.id\n"
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

            Vehicle vehicle = new Vehicle();
            vehicle.setId(rs.getLong("v.id"));
            vehicle.setBrand(rs.getString("v.brand"));
            vehicle.setModel(rs.getString("v.model"));
            vehicle.setMileage(rs.getInt("v.mileage"));
            vehicle.setAvailability(rs.getBoolean("v.availability"));

            TypeOfVehicle tov = new TypeOfVehicle();
            tov.setId(rs.getLong("tov.id"));
            tov.setName(rs.getString("tov.name"));
            vehicle.setTypeOfVehicle(tov);
            renting.setVehicle(vehicle);

            Client client = new Client();
            client.setId(rs.getLong("c.id"));
            client.setFirstName(rs.getString("c.firstname"));
            client.setLastName(rs.getString("c.lastname"));
            client.setTelNumber(rs.getString("c.telNumber"));
            renting.setClient(client);

            PriceListItem pli = new PriceListItem();
            pli.setId(rs.getLong("pli.id"));
            pli.setPrice(BigDecimal.valueOf(rs.getDouble("pli.price")));
            pli.setTypeOfPriceListItem(TypeOfPriceListItem.valueOf(rs.getString("pli.typeOfPriceListItem")));
            pli.setCurrency(Currency.valueOf(rs.getString("pli.currency")));

            PDV pdv = new PDV();
            pdv.setId(rs.getLong("pli.pdv_fk"));
            pdv.setPercent(rs.getDouble("p.percent"));

            pli.setPdv(pdv);
            pli.setTypeOfVehicle(tov);
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

    @Override
    public void add(Renting renting) throws Exception {
        String sql = "INSERT INTO renting(dateFrom, dateTo, totalAmount, currency, vehicle_fk, client_fk, priceListItem_fk) VALUES(?,?,?,?,?,?,?);";
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, Date.valueOf(renting.getDateFrom()));
        preparedStatement.setDate(2, Date.valueOf(renting.getDateTo()));
        preparedStatement.setBigDecimal(3, renting.getTotalAmount());
        preparedStatement.setString(4, renting.getCurrency().toString());
        preparedStatement.setLong(5, renting.getVehicle().getId());
        preparedStatement.setLong(6, renting.getClient().getId());
        preparedStatement.setLong(7, renting.getPriceListItem().getId());
        preparedStatement.executeUpdate();

        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Renting renting) throws Exception {
        String sql = "DELETE FROM renting WHERE id = ?";

        Connection connection = DBConnectionFactory.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, renting.getId());
        preparedStatement.execute();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

}
