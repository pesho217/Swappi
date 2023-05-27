package com.example.swappi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Films {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private Long index;
    private String title;
    private String episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String url;
    public String getUrl(){
        return "http://localhost:8080/films/" + index + "/";
    }

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_characters", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<People> filmCharacters = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_planets", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "planet_id"))
    private Set<Planets> filmPlanets = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_starships", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "starship_id"))
    private Set<Starships> filmStarships = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_vehicles", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicles> filmVehicles = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_species", joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "specie_id"))
    private Set<Species> filmSpecies = new HashSet<>();


}



