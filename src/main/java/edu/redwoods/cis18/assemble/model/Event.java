package edu.redwoods.cis18.assemble.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name is required")
    @Size(max = 100, message = "Event name cannot exceed 100 characters")
    @Column(nullable = false)
    private String name; // Name of the event

    @Size(max = 500, message = "Event description cannot exceed 500 characters")
    private String description; // Detailed description of the event

    @Column(nullable = true)
    private String imageUrl; // Event image URL

    @FutureOrPresent(message = "Event date must be today or in the future")
    @Column(nullable = false)
    private LocalDate date; // Date of the event

    @Column(nullable = false)
    private LocalTime time; // Time of the event

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @JsonIgnore
    private Business business; // The business hosting the event

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private User host; // The user hosting the event

    // Constructors
    public Event() {
    }

    public Event(String name, String description, String imageUrl, LocalDate date, LocalTime time, Business business, User host) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.time = time;
        this.business = business;
        this.host = host;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", business=" + (business != null ? business.getName() : "null") +
                ", host=" + (host != null ? host.getName() : "null") +
                '}';
    }
}
