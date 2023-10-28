package com.example.Using.an.external.API.from.Spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name ;
    private String description;
    private double price;
    private boolean isSummer;


    public Meal(){

    }

    public Meal(Long id, String name, String description, double price, boolean isSummer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isSummer = isSummer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSummer() {
        return isSummer;
    }

    public void setSummer(boolean summer) {
        isSummer = summer;
    }
}
