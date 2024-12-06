package edu.redwoods.cis18.assemble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

/*
Role: Entity

Purpose: Represents an Event object that will be stored in and retrieved from the database.
___

Details:

Annotated with @Entity, indicating it's a JPA entity.

Contains fields that map to columns in a database table (e.g., id, name, date, location).

Includes getter and setter methods to access and modify the entity's properties.

Implements equals and hashCode methods to compare entity instances based on the id field.
*/

// Event.java focuses on the structure and representation of event data as an entity.

@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String date;
    private String location;
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //image URL in the database

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // This method is overridden so that the DATABASE record id is used for comparing object equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id.equals(event.id);
    }

    // This method is overridden so that the object id will be tied to the DATABASE record id.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
