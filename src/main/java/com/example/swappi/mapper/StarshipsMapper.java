package com.example.swappi.mapper;

import com.example.swappi.models.Films;
import com.example.swappi.models.People;
import com.example.swappi.models.Starships;
import com.example.swappi.web.dto.starships.StarshipsCreateRequest;
import com.example.swappi.web.dto.starships.StarshipsResponse;
import com.example.swappi.web.dto.starships.StarshipsUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper
public interface StarshipsMapper {

    @Mapping(target = "id", ignore = true)
    Starships toStarships(StarshipsCreateRequest starshipsCreateRequest);

    @Mapping(target = "characters", source = "starshipsPeople")
    @Mapping(target = "films", source = "starshipsFilms")
    StarshipsResponse toStarshipsResponse(Starships starships);
    void updateModelFromDto (StarshipsUpdateRequest starshipsUpdateRequest, @MappingTarget Starships starships);

    default List<String> mapStarshipsPeople(Set<People> starshipsPeople) {
        if(starshipsPeople != null) {
            return starshipsPeople.stream()
                    .map(People::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
    default List<String> mapStarshipsFilms(Set<Films> starshipsFilms) {
        if(starshipsFilms != null) {
            return starshipsFilms.stream()
                    .map(Films::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }

}
