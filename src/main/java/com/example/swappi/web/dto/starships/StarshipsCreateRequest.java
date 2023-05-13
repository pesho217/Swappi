package com.example.swappi.web.dto.starships;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StarshipsCreateRequest {
    private String name;
    private String model;
    private String manufacturer;
    private long cost_in_credits;
    private int length;
    private int max_atmosphering_speed;
    private int crew;
    private String passengers;
    private long cargo_capacity;
    private String consumables;
    private double hyperdrive_rating;
    private int MGLT;
    private String starship_class;
}
