package edu.redwoods.cis18.assemble.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Game name is required")
    @Size(max = 100, message = "Game name cannot exceed 100 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 50, message = "Game type cannot exceed 50 characters")
    private String type;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = true)
    private String logoUrl;

    @Column(length = 500) // Set a reasonable length for the description
    private String description; // Brief description of the game

    @ManyToMany(mappedBy = "games", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Business> businesses;

    // Constructors
    public Game() {
    }

    public Game(String name, String type, String imageUrl, String logoUrl, String description) {
        this.name = name;
        this.type = type;
        this.imageUrl = imageUrl;
        this.logoUrl = logoUrl;
        this.description = description;
    }

    // Getters and Setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
