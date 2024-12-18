package edu.redwoods.cis18.assemble.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated primary key

    @Column(nullable = false, unique = true)
    private String name; // Unique name for the business

    @NotBlank(message = "Location is needed!")
    @Column(nullable = false)
    private String location; // Address or general location

    private String category; // e.g., game store, cafe, etc.

    @Column(nullable = true, length = 1000)
    private String description; // Description of the business

    @Column(nullable = true, length = 1000)
    private String imageUrl; // Business image URL

    @Column(nullable = true, length = 1000)
    private String logoUrl; // Business logo image URL

    private Double latitude; // For geolocation
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner; // The user who owns the business

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OperatingHours> operatingHours = new ArrayList<>(); // Business hours

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Event> events; // One business can host multiple events

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "business_games",
            joinColumns = @JoinColumn(name = "business_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games = new ArrayList<>(); // Games associated with the business

    // New operatingHoursSummary method to return more readable format
    @Transient
    public String getOperatingHoursSummary() {
        if (operatingHours == null || operatingHours.isEmpty()) {
            return "Hours not available";
        }

        // Sort and group by day of the week
        List<OperatingHours> sortedHours = operatingHours.stream()
                .sorted(Comparator.comparing(OperatingHours::getDayOfWeek))
                .collect(Collectors.toList());

        StringBuilder summary = new StringBuilder();
        String openTime = formatTime(sortedHours.get(0).getOpenTime().toString());
        String closeTime = formatTime(sortedHours.get(0).getCloseTime().toString());
        String currentRange = getAbbreviatedDay(sortedHours.get(0).getDayOfWeek());

        for (int i = 1; i < sortedHours.size(); i++) {
            OperatingHours current = sortedHours.get(i);
            OperatingHours previous = sortedHours.get(i - 1);

            if (current.getOpenTime().equals(previous.getOpenTime()) &&
                    current.getCloseTime().equals(previous.getCloseTime())) {
                // Extend the current range to include the current day
                if (!currentRange.contains(" - ")) {
                    currentRange += " - " + getAbbreviatedDay(current.getDayOfWeek());
                } else {
                    currentRange = currentRange.split(" - ")[0] + " - " + getAbbreviatedDay(current.getDayOfWeek());
                }
            } else {
                // Finalize the current range and hours
                summary.append(currentRange).append(" ")
                        .append(openTime).append(" to ").append(closeTime).append(", ");

                // Start a new range
                currentRange = getAbbreviatedDay(current.getDayOfWeek());
                openTime = formatTime(current.getOpenTime().toString());
                closeTime = formatTime(current.getCloseTime().toString());
            }
        }

// Finalize the last range and hours
        summary.append(currentRange).append(" ")
                .append(openTime).append(" to ").append(closeTime);


        return summary.toString();
    }

    private String getAbbreviatedDay(DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    private String formatTime(String time) {
        try {
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm");
            SimpleDateFormat sdf12 = new SimpleDateFormat("h:mma");
            return sdf12.format(sdf24.parse(time)).toLowerCase();
        } catch (ParseException e) {
            return time;
        }
    }


    // Constructors, getters, and setters
    public Business() {
    }

    public Business(String name, String location, String category, String description, String imageUrl, String logoUrl, Double latitude, Double longitude, User owner) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.logoUrl = logoUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.owner = owner;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<OperatingHours> getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(List<OperatingHours> operatingHours) {
        this.operatingHours = operatingHours;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
