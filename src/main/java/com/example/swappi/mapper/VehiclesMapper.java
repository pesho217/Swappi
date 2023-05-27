package com.example.swappi.mapper;

import com.example.swappi.models.Films;
import com.example.swappi.models.People;
import com.example.swappi.models.Vehicles;
import com.example.swappi.web.dto.vehicles.VehiclesCreateRequest;
import com.example.swappi.web.dto.vehicles.VehiclesResponse;
import com.example.swappi.web.dto.vehicles.VehiclesUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface VehiclesMapper {

    @Mapping(target = "id", ignore = true)
    Vehicles toVehicles(VehiclesCreateRequest vehiclesCreateRequest);

    @Mapping(target = "characters", source = "vehiclesPeople")
    @Mapping(target = "films", source = "vehiclesFilms")
    VehiclesResponse toVehiclesResponse(Vehicles vehicles);
    void updateModelFromDto (VehiclesUpdateRequest vehiclesUpdateRequest, @MappingTarget Vehicles vehicles);

    default List<String> mapVehiclesPeople(Set<People> vehiclesPeople) {
        if(vehiclesPeople != null) {
            return vehiclesPeople.stream()
                    .map(People::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
    default List<String> mapVehiclesFilms(Set<Films> vehiclesFilms) {
        if(vehiclesFilms != null) {
            return vehiclesFilms.stream()
                    .map(Films::getUrl)
                    .collect(Collectors.toList());
        }else return null;
    }
}
