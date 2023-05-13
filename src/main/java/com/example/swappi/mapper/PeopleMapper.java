package com.example.swappi.mapper;

import com.example.swappi.models.People;
import com.example.swappi.web.dto.people.PeopleCreateRequest;
import com.example.swappi.web.dto.people.PeopleResponse;
import com.example.swappi.web.dto.people.PeopleUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PeopleMapper {

    PeopleMapper INSTANCE = Mappers.getMapper( PeopleMapper.class );

    PeopleResponse toPeopleResponse(People people);

    List<PeopleResponse> toPeopleResponses(List<People> peopleList);

    People toPeople(PeopleCreateRequest createRequest);

    void updateModelFromDto(PeopleUpdateRequest peopleUpdateRequest, @MappingTarget People people);


}
