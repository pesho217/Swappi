package com.example.swappi.mapper;

import com.example.swappi.models.*;
import com.example.swappi.web.dto.films.FilmsCreateRequest;
import com.example.swappi.web.dto.films.FilmsResponse;
import com.example.swappi.web.dto.films.FilmsUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface FilmsMapper {

    FilmsMapper INSTANCE = Mappers.getMapper(FilmsMapper.class);

    @Mapping(target = "id", ignore = true)
    Films toFilm(FilmsCreateRequest filmCreateRequest);

    @Mapping(target = "characters", source = "filmCharacters")
    @Mapping(target = "planets", source = "filmPlanets")
    @Mapping(target = "species", source = "filmSpecies")
    @Mapping(target = "starships", source = "filmStarships")
    @Mapping(target = "vehicles", source = "filmVehicles")
    FilmsResponse toFilmResponse(Films film);

    void updateModelFromDto (FilmsUpdateRequest filmsUpdateRequest,@MappingTarget Films film);

    default List<String> mapFilmsPeople(Set<People> FilmsPeople) {
        if(FilmsPeople != null) {
            return FilmsPeople.stream()
                    .map(People::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
    default List<String> mapFilmsPlanets(Set<Planets> filmsPlanets){
        if(filmsPlanets != null) {
            return filmsPlanets.stream()
                    .map(Planets::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
    default List<String> mapFilmsSpecies(Set<Species> filmsSpecies){
        if(filmsSpecies!=null) {
            return filmsSpecies.stream()
                    .map(Species::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }

    default List<String> mapFilmsVehicles(Set<Vehicles> filmsVehicles) {
        if (filmsVehicles != null) {
            return filmsVehicles.stream()
                    .map(Vehicles::getUrl)
                    .collect(Collectors.toList());
        } else return null;
    }
    default List<String> mapFilmsStarships(Set<Starships> filmsStarships) {
        if (filmsStarships != null) {
            return filmsStarships.stream()
                    .map(Starships::getUrl)
                    .collect(Collectors.toList());
        } else return null;
    }

}
