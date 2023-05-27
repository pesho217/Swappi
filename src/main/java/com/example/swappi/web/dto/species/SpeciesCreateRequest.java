package com.example.swappi.web.dto.species;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SpeciesCreateRequest {
    private String name;
    private String classification;
    private String designation;
    private String average_height;
    private String skin_colors;
    private String hair_colors;
    private String eye_colors;
    private String average_lifespan;
    private String language;
    private String homeworld;
    private List<String> charactersIds;
    private List<String> filmsIds;

}
