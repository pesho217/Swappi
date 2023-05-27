package com.example.swappi.web.dto.films;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FilmsResponse {

    private UUID id;
    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private LocalDate releaseDate;
    private List<String> characters;
    private List<String> planets;
    private List<String> vehicles;
    private List<String> starships;
    private List<String> species;
    private String created;
    private String edited;
    private String url;
}
