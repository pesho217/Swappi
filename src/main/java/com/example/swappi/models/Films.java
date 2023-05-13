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

    private String title;
    private String episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<People> filmCharacters = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Planets> filmPlanets = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Starships> filmStarships = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Vehicles> filmVehicles = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Species> filmSpecies = new HashSet<>();


}



