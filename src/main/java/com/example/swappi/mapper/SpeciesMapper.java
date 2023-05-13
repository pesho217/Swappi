package com.example.swappi.mapper;

import com.example.swappi.models.Species;
import com.example.swappi.web.dto.species.SpeciesCreateRequest;
import com.example.swappi.web.dto.species.SpeciesResponse;
import com.example.swappi.web.dto.species.SpeciesUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface SpeciesMapper {

    Species toSpecies(SpeciesCreateRequest speciesCreateRequest);

    SpeciesResponse toSpeciesResponse(Species species);
    void updateModelFromDto (SpeciesUpdateRequest speciesUpdateRequest, @MappingTarget Species species);
}
