package com.Kotech.Customer.Entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="city")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;



    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}