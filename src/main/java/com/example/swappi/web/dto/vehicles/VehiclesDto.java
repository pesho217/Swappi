package com.example.swappi.web.dto.vehicles;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehiclesDto {

    private String name;
    private String model;
    private String manufacturer;
    private long cost_in_credits;
    private double length;
    private int max_atmosphering_speed;
    private int crew;
    private String passengers;
    private long cargo_capacity;
    private String consumables;
    private String vehicle_class;
}
