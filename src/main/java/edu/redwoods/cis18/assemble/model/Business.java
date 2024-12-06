package edu.redwoods.cis18.assemble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/*
Role: Entity

Purpose: Represents a Game object that will be stored in and retrieved from the database.
___

Details:

Annotated with @Entity, indicating it's a JPA entity.

Contains fields that map to columns in a database table (e.g., id, name, type).

Includes getter and setter methods to access and modify the entity's properties.

Implements equals and hashCode methods to compare entity instances based on the id field.
*/

// Game.java focuses on the structure and representation of game data as an entity.

@Entity
public class Business implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String time;
    private String location;
    private String category;

    public Business(Long id, String name, String location, String category) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.category = category;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Find store by location
    public static List<Business> findStoreByLocation(List<Business> businesses, String location) {
        return businesses.stream()
                .filter(business -> business.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    // Find store by name
    public static Business findStoreByName(List<Business> businesses, String name) {
        return businesses.stream()
                .filter(business -> business.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Find store by category
    public static List<Business> findStoreByCategory(List<Business> businesses, String category) {
        return businesses.stream()
                .filter(business -> business.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Get all stores
    public static List<Business> getAllStores(List<Business> businesses) {
        return businesses;
    }


    // This method is overridden so that the DATABASE record id is used for comparing object equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return id.equals(business.id);
    }

    // This method is overridden so that the object id will be tied to the DATABASE record id.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
