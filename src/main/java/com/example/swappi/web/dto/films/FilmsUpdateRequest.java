package com.example.swappi.web.dto.films;

import java.time.LocalDate;
import java.util.List;

public class FilmsUpdateRequest {
    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private LocalDate releaseDate;
    private List<String> characterUrls;
    private List<String> planetsUrls;
    private List<String> vehiclesUrls;
    private List<String> starshipsUrls;
    private List<String> speciesUrls;
}
