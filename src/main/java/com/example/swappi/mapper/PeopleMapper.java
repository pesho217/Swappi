package com.example.swappi.mapper;

import com.example.swappi.models.*;
import com.example.swappi.web.controllers.SpecieController;
import com.example.swappi.web.dto.people.PeopleCreateRequest;
import com.example.swappi.web.dto.people.PeopleResponse;
import com.example.swappi.web.dto.people.PeopleUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper
public interface PeopleMapper {

    PeopleMapper INSTANCE = Mappers.getMapper( PeopleMapper.class );


    @Mapping(target = "homeworld", source = "peoplePlanets")
    @Mapping(target = "species", source = "peopleSpecies")
    @Mapping(target = "starships", source = "peopleStarships")
    @Mapping(target = "vehicles", source = "peopleVehicles")
    @Mapping(target = "films", source = "peopleFilms")
    @Mapping(target = "birth_year", source = "birth_year")
    @Mapping(target = "hair_color", source = "hair_color")
    @Mapping(target = "skin_color", source = "skin_color")
    @Mapping(target = "eye_color", source = "eye_color")
    PeopleResponse toPeopleResponse(People people);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birth_year", source = "birth_year")
    @Mapping(target = "hair_color", source = "hair_color")
    @Mapping(target = "skin_color", source = "skin_color")
    @Mapping(target = "eye_color", source = "eye_color")
    People toPeople(PeopleCreateRequest createRequest);

    void updateModelFromDto(PeopleUpdateRequest peopleUpdateRequest, @MappingTarget People people);

    default List<String> convertPeopleFilms(Set<Films> peopleFilms) {
        if(peopleFilms != null) {
            return peopleFilms.stream()
                    .map(Films::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
    default String mapPeoplePlanets(Planets peoplePlanets){
        if(peoplePlanets != null) {
            return peoplePlanets.getUrl();
        }else return null;
    }
    default List<String> mapPeopleSpecies(Set<Species> peopleSpecies){
        if(peopleSpecies!=null) {
            return peopleSpecies.stream()
                    .map(Species::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }

    default List<String> mapPeopleVehicles(Set<Vehicles> peopleVehicles) {
        if (peopleVehicles != null) {
            return peopleVehicles.stream()
                    .map(Vehicles::getUrl)
                    .collect(Collectors.toList());
        } else return null;
    }
    default List<String> mapPeopleStarships(Set<Starships> peopleStarships) {
        if (peopleStarships != null) {
            return peopleStarships.stream()
                    .map(Starships::getUrl)
                    .collect(Collectors.toList());
        } else return null;
    }
}

