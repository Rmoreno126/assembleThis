package edu.redwoods.cis18.assemble.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Location is needed!")
    @Column(nullable = true)
    private String location; // Address or general location

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

    //Changed business to be nullable because users need to be able to host their own events!
    @ManyToOne
    @JoinColumn(name = "business_id", nullable = true)
    @JsonIgnore
    private Business business; // The business hosting the event

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private User host; // The user hosting the event

    @ManyToMany
    @JoinTable(
            name = "event_games",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games = new ArrayList<>(); // Games associated with the event

    // Constructors
    public Event() {
    }

    public Event(String name, String location, String description, String imageUrl, LocalDate date, LocalTime time, Business business, User host) {
        this.name = name;
        this.location = location;
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
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", business=" + (business != null ? business.getName() : "null") +
                ", host=" + (host != null ? host.getName() : "null") +
                '}';
    }

    public @NotBlank(message = "Location is needed!") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Location is needed!") String location) {
        this.location = location;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
