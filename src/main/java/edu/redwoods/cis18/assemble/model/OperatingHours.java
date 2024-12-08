package edu.redwoods.cis18.assemble.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class OperatingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @JsonIgnore
    private Business business; // Associated business

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek; // Enum for days of the week

    @Column(nullable = false)
    private LocalTime openTime; // Open time as LocalTime

    @Column(nullable = false)
    private LocalTime closeTime; // Close time as LocalTime

    @Column(nullable = false)
    private Boolean isSpecial = false; // Marking special hours, default to false

    // Constructors, getters, and setters
    public OperatingHours() {
    }

    public OperatingHours(Business business, DayOfWeek dayOfWeek, LocalTime openTime, LocalTime closeTime, Boolean isSpecial) {
        this.business = business;
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.isSpecial = isSpecial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    @Override
    public String toString() {
        return "OperatingHours{" +
                "id=" + id +
                ", business=" + (business != null ? business.getName() : "null") +
                ", dayOfWeek=" + dayOfWeek +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", isSpecial=" + isSpecial +
                '}';
    }
}
