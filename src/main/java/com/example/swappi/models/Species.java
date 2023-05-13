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
public class Species {
    @Id
    @GeneratedValue
    private UUID id;

            private String classification;
            private String designation;
            private String average_height;
            private String skin_colors;
            private String hair_colors;
            private String eye_colors;
            private String average_lifespan;
            private String homeworld;
            private String language;

            @JsonIgnore
            @ManyToOne(fetch = FetchType.EAGER)
            @JoinColumn(name = "planetId")
            private Planets speciesPlanets;

            @JsonIgnore
            @ManyToMany(fetch = FetchType.EAGER)
            private Set<Films> speciesFilms = new HashSet<>();

            @JsonIgnore
            @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(name = "species_people", joinColumns = @JoinColumn(name = "speciesId"),
                    inverseJoinColumns = @JoinColumn(name = "peopleId"))
            private Set<People> speciesPeople = new HashSet<>();


}
