package com.example.swappi.mapper;

import com.example.swappi.models.Starships;
import com.example.swappi.web.dto.starships.StarshipsCreateRequest;
import com.example.swappi.web.dto.starships.StarshipsResponse;
import com.example.swappi.web.dto.starships.StarshipsUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper
public interface StarshipsMapper {

    Starships toStarships(StarshipsCreateRequest starshipsCreateRequest);

    StarshipsResponse toStarshipsResponse(Starships starships);
    void updateModelFromDto (StarshipsUpdateRequest starshipsUpdateRequest, @MappingTarget Starships starships);


}
