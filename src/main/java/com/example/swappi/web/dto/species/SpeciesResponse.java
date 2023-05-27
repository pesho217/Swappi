package com.example.swappi.web.dto.species;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SpeciesResponse {
    private UUID id;
    private String name;
    private String classification;
    private String designation;
    private String average_height;
    private String skin_colors;
    private String hair_colors;
    private String eye_colors;
    private String average_lifespan;
    private String homeworld;
    private String language;
    private List<String> characters;
    private List<String> films;
    private String created;
    private String edited;
    private String url;


}
