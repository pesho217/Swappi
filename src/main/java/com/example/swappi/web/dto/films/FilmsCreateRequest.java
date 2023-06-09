package com.example.swappi.web.dto.films;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class FilmsCreateRequest {
    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private LocalDate releaseDate;
    private List<String> charactersIds;
    private List<String> planetsIds;
    private List<String> vehiclesIds;
    private List<String> starshipIds;
    private List<String> speciesIds;

}
