package com.example.swappi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Starships {

    @Id
    @GeneratedValue
    UUID id;

    private String name;
    private String model;
    private String manufacturer;
    private long cost_in_credits;
    private int length;
    private int max_atmosphering_speed;
    private int crew;
    private String passengers;
    private long cargo_capacity;
    private String consumables;
    private double hyperdrive_rating;
    private int MGLT;
    private String starship_class;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "starshipsPeople", joinColumns = @JoinColumn(name = "starshipId"),
    inverseJoinColumns = @JoinColumn(name = "peopleId"))
    private Set<People> starshipsPeople = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Films> starshipsFilms = new HashSet<>();

}


