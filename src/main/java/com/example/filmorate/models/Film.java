package com.example.filmorate.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class Film {
    private int id;
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    private LocalDate dateOfRealise;
    @Positive
    private int duration;

    public Film(int id, int duration, LocalDate dateOfRealise, String description, String name) {
        this.id = id;
        this.duration = duration;
        this.dateOfRealise = dateOfRealise;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDateOfRealise() {
        return dateOfRealise;
    }

    public void setDateOfRealise(LocalDate dateOfRealise) {
        this.dateOfRealise = dateOfRealise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
