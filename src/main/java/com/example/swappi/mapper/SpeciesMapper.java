package com.example.swappi.mapper;

import com.example.swappi.models.Films;
import com.example.swappi.models.People;
import com.example.swappi.models.Planets;
import com.example.swappi.models.Species;
import com.example.swappi.web.dto.species.SpeciesCreateRequest;
import com.example.swappi.web.dto.species.SpeciesResponse;
import com.example.swappi.web.dto.species.SpeciesUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface SpeciesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "classification",source = "classification")
    @Mapping(target = "designation",source = "designation")
    @Mapping(target = "average_height",source = "average_height")
    @Mapping(target = "skin_colors",source = "skin_colors")
    @Mapping(target = "hair_colors",source = "hair_colors")
    @Mapping(target = "eye_colors", source = "eye_colors")
    @Mapping(target = "average_lifespan", source = "average_lifespan")
    Species toSpecies(SpeciesCreateRequest speciesCreateRequest);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "classification",source = "classification")
    @Mapping(target = "designation",source = "designation")
    @Mapping(target = "average_height",source = "average_height")
    @Mapping(target = "skin_colors",source = "skin_colors")
    @Mapping(target = "hair_colors",source = "hair_colors")
    @Mapping(target = "eye_colors", source = "eye_colors")
    @Mapping(target = "average_lifespan", source = "average_lifespan")
    @Mapping(target = "characters", source = "speciesPeople")
    @Mapping(target = "films", source = "speciesFilms")
    @Mapping(target = "homeworld", source = "speciesPlanets")
    SpeciesResponse toSpeciesResponse(Species species);
    void updateModelFromDto (SpeciesUpdateRequest speciesUpdateRequest, @MappingTarget Species species);
    default List<String> mapSpeciesFilms(Set<Films> speciesFilms) {
        if(speciesFilms != null) {
            return speciesFilms.stream()
                    .map(Films::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
    default String mapSpeciesPlanets(Planets speciesPlanets){
        if(speciesPlanets != null) {
            return speciesPlanets.getUrl();
        }else return null;
    }
    default List<String> mapSpeciesPeople(Set<People> speciesPeople) {
        if(speciesPeople != null) {
            return speciesPeople.stream()
                    .map(People::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
}
