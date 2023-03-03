/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enumeration.Currency;
import enumeration.TypeOfPriceListItem;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Somika
 */
public class PriceListItem implements Serializable {

    private Long id;
    private PriceList priceList;
    private BigDecimal price;
    private PDV pdv;
    private TypeOfPriceListItem typeOfPriceListItem;
    private Currency currency;
    private TypeOfVehicle typeOfVehicle;

    public PriceListItem() {
    }

    public PriceListItem(Long id, PriceList priceList, BigDecimal price, PDV pdv, TypeOfPriceListItem typeOfPriceListItem, Currency currency, TypeOfVehicle typeOfVehicle) {
        this.id = id;
        this.priceList = priceList;
        this.price = price;
        this.pdv = pdv;
        this.typeOfPriceListItem = typeOfPriceListItem;
        this.currency = currency;
        this.typeOfVehicle = typeOfVehicle;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PDV getPdv() {
        return pdv;
    }

    public void setPdv(PDV pdv) {
        this.pdv = pdv;
    }

    public TypeOfPriceListItem getTypeOfPriceListItem() {
        return typeOfPriceListItem;
    }

    public void setTypeOfPriceListItem(TypeOfPriceListItem typeOfPriceListItem) {
        this.typeOfPriceListItem = typeOfPriceListItem;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(TypeOfVehicle typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PriceListItem other = (PriceListItem) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return price + " " + currency + " " + typeOfPriceListItem;
    }

}
