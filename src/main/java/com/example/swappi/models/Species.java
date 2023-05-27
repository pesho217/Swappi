package com.example.swappi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @Setter(value = AccessLevel.NONE)
    private UUID id;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String url;
    private Long index;
    private String name;
            private String classification;
            private String designation;
            private String average_height;
            private String skin_colors;
            private String hair_colors;
            private String eye_colors;
            private String average_lifespan;
            private String language;
        public String getUrl(){
                return "http://localhost:8080/species/" + index + "/";
        }

            @JsonIgnore
            @EqualsAndHashCode.Exclude
            @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "planetId")
            private Planets speciesPlanets;

            @JsonIgnore
            @EqualsAndHashCode.Exclude
            @ManyToMany(fetch = FetchType.LAZY)
            private Set<Films> speciesFilms = new HashSet<>();

            @JsonIgnore
            @EqualsAndHashCode.Exclude
            @ManyToMany(fetch = FetchType.LAZY)
            @JoinTable(name = "species_people", joinColumns = @JoinColumn(name = "speciesId"),
                    inverseJoinColumns = @JoinColumn(name = "peopleId"))
            private Set<People> speciesPeople = new HashSet<>();


}
