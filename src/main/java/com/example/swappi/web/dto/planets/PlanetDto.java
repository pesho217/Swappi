package com.example.swappi.web.dto.planets;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanetDto {
    private String name;
    private String climate;
    private String terrain;
    private String surfaceWater;
    private String population;
    private List<String> residentUrls;
    private String filmUrls;
}
