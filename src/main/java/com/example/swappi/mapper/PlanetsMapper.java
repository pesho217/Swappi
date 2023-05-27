package com.example.swappi.mapper;
import com.example.swappi.models.Films;
import com.example.swappi.models.People;
import com.example.swappi.models.Planets;
import com.example.swappi.models.Species;
import com.example.swappi.web.dto.planets.PlanetsCreateRequest;
import com.example.swappi.web.dto.planets.PlanetsResponse;
import com.example.swappi.web.dto.planets.PlanetsUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper
public interface PlanetsMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name",source = "name")
    @Mapping(target = "rotation_period",source = "rotation_period")
    @Mapping(target = "orbital_period",source = "orbital_period")
    @Mapping(target = "diameter",source = "diameter")
    @Mapping(target = "climate",source = "climate")
    @Mapping(target = "gravity",source = "gravity")
    @Mapping(target = "terrain",source = "terrain")
    @Mapping(target = "surface_water",source = "surface_water")
    @Mapping(target = "population",source = "population")
    Planets toPlanets(PlanetsCreateRequest planetsCreateRequest);

    @Mapping(target = "name",source = "name")
    @Mapping(target = "rotation_period",source = "rotation_period")
    @Mapping(target = "orbital_period",source = "orbital_period")
    @Mapping(target = "diameter",source = "diameter")
    @Mapping(target = "climate",source = "climate")
    @Mapping(target = "gravity",source = "gravity")
    @Mapping(target = "terrain",source = "terrain")
    @Mapping(target = "surface_water",source = "surface_water")
    @Mapping(target = "population",source = "population")
    @Mapping(target = "characters", source = "planetsPeople")
    @Mapping(target = "films", source = "planetsFilms")
    PlanetsResponse toPlanetsResponse(Planets planets);
    void updateModelFromDto (PlanetsUpdateRequest planetsUpdateRequest, @MappingTarget Planets planets);

    default List<String> mapPlanetFilms(Set<Films> planetsFilms) {
        if(planetsFilms != null) {
            return planetsFilms.stream()
                    .map(Films::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
    default List<String> mapPlanetsPeople(Set<People> planetsPeople){
        if(planetsPeople!=null) {
            return planetsPeople.stream()
                    .map(People::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
}
