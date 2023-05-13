package com.example.swappi.web.dto.people;

import com.example.swappi.validation.ValidGender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class PeopleCreateRequest {

    private String name;
    @ValidGender(message = "The genders are only 2 - male and female!")
    private String gender;
    private String birth_Year;
    private int height;
    private double mass;
    private String hair_color;
    private String eye_color;
    private String skin_color;
    private String homeworld;
    private Set<String> films;
    private Set<String> species;
    private Set<String> vehicles;
    private Set<String> starships;
    private String created;
    private String edited;
    private String url;



}
