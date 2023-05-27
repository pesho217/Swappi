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
public class Vehicles {
    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    UUID id;
    private Long index;
    private String name;
    private String model;
    private String manufacturer;
    private String cost_in_credits;
    private String length;
    private String max_atmosphering_speed;
    private String crew;
    private String passengers;
    private String cargo_capacity;
    private String consumables;
    private String vehicle_class;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String url;
    public String getUrl(){
        return "http://localhost:8080/vehicles/" + index + "/";
    }

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vehiclesPeople", joinColumns = @JoinColumn(name = "vehicleId"),
            inverseJoinColumns = @JoinColumn(name = "peopleId"))
    private Set<People> vehiclesPeople = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Films> vehiclesFilms = new HashSet<>();




}
