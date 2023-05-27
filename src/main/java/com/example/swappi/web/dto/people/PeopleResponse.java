package com.example.swappi.web.dto.people;

import com.example.swappi.validation.ValidGender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.UUID;
@Data
@Builder
public class PeopleResponse {

    private UUID id;
    private String name;
    private String birth_year;
    private String height;
    private String mass;
    private String gender;
    private String hair_color;
    private String eye_color;
    private String skin_color;
    private  String homeworld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;
    private String created;
    private String edited;
    private String url;

}
