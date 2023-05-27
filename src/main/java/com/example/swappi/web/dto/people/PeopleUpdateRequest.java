package com.example.swappi.web.dto.people;

import com.example.swappi.validation.ValidGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class PeopleUpdateRequest {
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    @ValidGender(message = "The genders are only 2 - male and female!")
    private String gender;
    private Set<String> species;
    private Set<String> vehicles;
    private Set<String> starships;

}
