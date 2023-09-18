package com.andriiiiiko.database.entity;

import javax.persistence.*;

/**
 * Represents a Building entity with a section number and street information.
 */
@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "section_number")
    private String sectionNumber;

    @Column(name = "street")
    private String street;

    public String getSectionNumber() {
        return sectionNumber;
    }

    public String getStreet() {
        return street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
