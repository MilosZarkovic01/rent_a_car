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
    private LocalDate validFrom;
    private LocalDate validTo;
    private List<PriceListItem> priceListItems;

    public PriceList() {
        this.priceListItems = new ArrayList<>();
    }

    public PriceList(Long id, LocalDate validFrom, LocalDate validTo) {
        this.id = id;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.priceListItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return validFrom;
    }

    public void setDateFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getDateTo() {
        return validTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.validTo = dateTo;
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
        return "PriceList{" + "id=" + id + ", validFrom=" + validFrom + ", validTo=" + validTo + ", priceListItems=" + priceListItems + '}';
    }

}
