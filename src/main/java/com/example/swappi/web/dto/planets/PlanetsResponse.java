package com.example.swappi.web.dto.planets;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PlanetsResponse {
    private UUID id;
    private String name;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private List<String> characters;
    private List<String> films;
    private String created;
    private String edited;
    private String url;

}
