package com.example.swappi.web.dto.vehicles;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class VehiclesResponse {
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
    private List<String> filmsUrls;
    private List<String> characterUrls;
    private String created;
    private String edited;
    private String url;
}
