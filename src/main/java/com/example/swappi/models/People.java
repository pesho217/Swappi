package com.example.swappi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
    private Long index;
    private String name;
    private String birth_year;
    private String height;
    private String mass;
    private String gender;
    private String hair_color;
    private String eye_color;
    private String skin_color;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private String url;
    public String getUrl(){
       return "http://localhost:8080/people/" + index + "/";
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planetId")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Planets peoplePlanets;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Films> peopleFilms = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "people_species", joinColumns = @JoinColumn(name = "people_id"),
    inverseJoinColumns = @JoinColumn(name = "species_id"))
    private Set<Species> peopleSpecies = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "people_vehicles", joinColumns = @JoinColumn(name = "people_id"),
    inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicles> peopleVehicles = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "people_starships", joinColumns = @JoinColumn(name = "people_id"),
    inverseJoinColumns = @JoinColumn(name = "starship_id"))
    private Set<Starships> peopleStarships = new HashSet<>();

}
