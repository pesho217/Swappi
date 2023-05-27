package com.example.swappi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Planets {
    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    private UUID id;

    private Long index;
    private String name;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private String url;
    public String getUrl(){
        return "http://localhost:8080/planets/" + index + "/";
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "planets_people", joinColumns = @JoinColumn(name = "planets_id"),
    inverseJoinColumns = @JoinColumn(name = "people_id"))
    private Set<People> planetsPeople = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "planets_films", joinColumns = @JoinColumn(name = "planets_id"),
    inverseJoinColumns = @JoinColumn(name = "people_id"))
    private Set<Films> planetsFilms = new HashSet<>();



}
