package edu.redwoods.cis18.assemble.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

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
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        Game game = (Game) o;
        return id.equals(game.id);
    }

    // This method is overridden so that the object id will be tied to the DATABASE record id.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
