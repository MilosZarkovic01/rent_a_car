/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.table.model;

import domain.Renting;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Somika
 */
public class RentingTableModel extends AbstractTableModel {

    private final List<Renting> rentings;
    private final String[] columnNames = {"Id", "Date from", "Date to", "Total amount", "Currency", "Vehicle", "Client", "Price"};

    public RentingTableModel(List<Renting> rentings) {
        this.rentings = rentings;
    }

    @Override
    public int getRowCount() {
        return rentings.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Renting renting = rentings.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return renting.getId();
            case 1:
                return renting.getDateFrom();
            case 2:
                return renting.getDateTo();
            case 3:
                return renting.getTotalAmount();
            case 4:
                return renting.getCurrency();
            case 5:
                return renting.getVehicle().getBrand() + " " + renting.getVehicle().getModel();
            case 6:
                return renting.getClient().getFirstName() + " " + renting.getClient().getLastName();
            case 7:
                return renting.getPriceListItem().getTypeOfPriceListItem().toString() + " " + renting.getPriceListItem().getPrice() + " " + renting.getPriceListItem().getCurrency().toString();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<Renting> getRentings() {
        return rentings;
    }

    public void update() {
        fireTableDataChanged();
    }
}
