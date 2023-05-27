package com.example.swappi.web.dto.people;

import com.example.swappi.validation.ValidGender;
import lombok.Builder;
import lombok.Data;

import java.util.List;



@Data
@Builder
public class PeopleCreateRequest {

    private String name;
    @ValidGender(message = "The genders are only 2 - male and female!")
    private String gender;
    private String birth_year;
    private String height;
    private String mass;
    private String hair_color;
    private String eye_color;
    private String skin_color;
    private String homeworld;
    private List<String> filmIds;
    private List<String> speciesIds;
    private List<String> starshipsIds;
    private List<String> vehiclesIds;

}
