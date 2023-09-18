package com.andriiiiiko.database.entity;

import javax.persistence.*;

/**
 * Represents an Apartment entity with a unique number, area, and associated building.
 */
@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "area")
    private double area;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    private Building buildingId;

    public int getNumber() {
        return number;
    }

    public double getArea() {
        return area;
    }

    public Building getBuildingId() {
        return buildingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
