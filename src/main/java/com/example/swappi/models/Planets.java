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
public class Planets {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int rotation_period;
    private int orbital_period;
    private long diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private long population;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "planets_people", joinColumns = @JoinColumn(name = "planets_id"),
    inverseJoinColumns = @JoinColumn(name = "people_id"))
    private Set<People> planetsPeople = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "planets_films", joinColumns = @JoinColumn(name = "planets_id"),
    inverseJoinColumns = @JoinColumn(name = "people_id"))
    private Set<Films> planetsFilms = new HashSet<>();



}
