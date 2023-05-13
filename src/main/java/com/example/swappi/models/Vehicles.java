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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {
    @Id
    @GeneratedValue
    UUID id;

    private String name;
    private String model;
    private String manufacturer;
    private long cost_in_credits;
    private double length;
    private int max_atmosphering_speed;
    private int crew;
    private String passengers;
    private long cargo_capacity;
    private String consumables;
    private String vehicle_class;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehiclesPeople", joinColumns = @JoinColumn(name = "vehicleId"),
            inverseJoinColumns = @JoinColumn(name = "peopleId"))
    private Set<People> vehiclesPeople = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Films> vehiclesFilms = new HashSet<>();




}
