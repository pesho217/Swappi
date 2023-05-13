package com.example.swappi.models;

import com.example.swappi.validation.ValidGender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People {
    @GeneratedValue
    @Id
    @Setter(value = AccessLevel.NONE)
    private UUID id;

    private String name;
    private String birth_Year;
    private int height;
    private double mass;
    private String gender;
    private String hair_color;
    private String eye_color;
    private String skin_color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planetId")
    private Planets peoplePlanets;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Films> peopleFilms = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "people_species", joinColumns = @JoinColumn(name = "people_id"),
    inverseJoinColumns = @JoinColumn(name = "species_id"))
    private Set<Species> peopleSpecies = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "people_vehicles", joinColumns = @JoinColumn(name = "people_id"),
    inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicles> peopleVehicles = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "people_spaceships", joinColumns = @JoinColumn(name = "people_id"),
    inverseJoinColumns = @JoinColumn(name = "spaceship_id"))
    private Set<Starships> peopleSpaceships = new HashSet<>();









}
