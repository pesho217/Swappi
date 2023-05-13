package com.example.swappi.web.dto.planets;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanetsCreateRequest {

    private String name;
    private int rotation_period;
    private int orbital_period;
    private long diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private long population;
    private List<String> characterUrls;
    private List<String> filmsUrls;

}
