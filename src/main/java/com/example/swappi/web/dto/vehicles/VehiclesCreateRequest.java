package com.example.swappi.web.dto.vehicles;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VehiclesCreateRequest {
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
    private List<String> filmsIds;
    private List<String> charactersIds;
}
