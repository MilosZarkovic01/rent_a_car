/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.dbb.impl;

import domain.PDV;
import java.util.ArrayList;
import java.util.List;
import repository.dbb.PdvDBBroker;
import java.sql.*;
import repository.conn.DBConnectionFactory;

/**
 *
 * @author Somika
 */
public class RepositoryDBPdv implements PdvDBBroker {

    @Override
    public List<PDV> getAll() throws Exception {
        try {
            String sql = "SELECT * FROM pdv";

            List<PDV> pdvs = new ArrayList<>();
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                PDV pdv = new PDV();
                pdv.setId(rs.getLong("id"));
                pdv.setPercent(rs.getDouble("percent"));
                pdvs.add(pdv);
            }
            rs.close();
            statement.close();
            connection.close();
            return pdvs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
