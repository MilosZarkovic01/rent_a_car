/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Somika
 */
public class PriceList implements Serializable{

    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<PriceListItem> priceListItems;

    public PriceList() {
        this.priceListItems = new ArrayList<>();
    }

    public PriceList(Long id, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.priceListItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public List<PriceListItem> getPriceListItems() {
        return priceListItems;
    }

    public void setPriceListItems(List<PriceListItem> priceListItems) {
        this.priceListItems = priceListItems;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final PriceList other = (PriceList) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "PriceList{" + "id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", priceListItems=" + priceListItems + '}';
    }

}
