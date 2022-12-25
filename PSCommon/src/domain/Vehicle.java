/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Somika
 */
public class Vehicle implements Serializable{

    private Long id;
    private String brand;
    private String model;
    private int mileage;
    private boolean availability;
    private TypeOfVehicle typeOfVehicle;

    public Vehicle() {
    }

    public Vehicle(Long id, String brand, String model, int mileage, boolean availability, TypeOfVehicle typeOfVehicle) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.availability = availability;
        this.typeOfVehicle = typeOfVehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(TypeOfVehicle typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
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
        final Vehicle other = (Vehicle) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", brand=" + brand + ", model=" + model + ", mileage=" + mileage + ", availability=" + availability + ", typeOfVehicle=" + typeOfVehicle + '}';
    }

}
