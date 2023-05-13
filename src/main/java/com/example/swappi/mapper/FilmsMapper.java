package com.example.swappi.mapper;

import com.example.swappi.models.Films;
import com.example.swappi.models.People;
import com.example.swappi.web.dto.films.FilmsCreateRequest;
import com.example.swappi.web.dto.films.FilmsResponse;
import com.example.swappi.web.dto.films.FilmsUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmsMapper {

    FilmsMapper INSTANCE = Mappers.getMapper(FilmsMapper.class);


    Films toFilm(FilmsCreateRequest filmCreateRequest);

    FilmsResponse toFilmResponse(Films film);

    void updateModelFromDto (FilmsUpdateRequest filmsUpdateRequest,@MappingTarget Films film);


}
