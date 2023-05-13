package com.example.swappi.web.dto.species;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpeciesDto {
    private String classification;
    private String designation;
    private String average_height;
    private String skin_colors;
    private String hair_colors;
    private String eye_colors;
    private String average_lifespan;
    private String homeworld;
    private String language;
}
