package com.example.swappi.mapper;
import com.example.swappi.models.Planets;
import com.example.swappi.web.dto.planets.PlanetsCreateRequest;
import com.example.swappi.web.dto.planets.PlanetsResponse;
import com.example.swappi.web.dto.planets.PlanetsUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper
public interface PlanetsMapper {


    Planets toPlanets(PlanetsCreateRequest planetsCreateRequest);

    PlanetsResponse toPlanetsResponse(Planets planets);
    void updateModelFromDto (PlanetsUpdateRequest planetsUpdateRequest, @MappingTarget Planets planets);

}
