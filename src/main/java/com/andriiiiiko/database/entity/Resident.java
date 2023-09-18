package com.andriiiiiko.database.entity;

import javax.persistence.*;

/**
 * Represents a Resident entity with a person and apartment association.
 */
@Entity
@Table(name = "resident")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "car_entry_permission")
    private boolean carEntryPermission;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartment apartmentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return "\nFirst Name: " + personId.getFirstName() + "\n" +
                "Last Name: " + personId.getLastName() + "\n" +
                "Middle Name: " + personId.getMiddleName() + "\n" +
                "Email Address: " + personId.getEmail() + "\n" +
                "Phone Number: " + personId.getPhoneNumber() + "\n" +
                "Apartment Number: №" + apartmentId.getNumber() + "\n" +
                "Apartment Area: " + apartmentId.getArea() + " square meters" + "\n" +
                "Section: " + apartmentId.getBuildingId().getSectionNumber() + "\n" +
                "Street: " + apartmentId.getBuildingId().getStreet() + "\n";
    }
}
