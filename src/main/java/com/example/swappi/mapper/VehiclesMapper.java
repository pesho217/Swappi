package com.example.swappi.mapper;

import com.example.swappi.models.Vehicles;
import com.example.swappi.web.dto.vehicles.VehiclesCreateRequest;
import com.example.swappi.web.dto.vehicles.VehiclesResponse;
import com.example.swappi.web.dto.vehicles.VehiclesUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface VehiclesMapper {

    Vehicles toVehicles(VehiclesCreateRequest vehiclesCreateRequest);

    VehiclesResponse toVehiclesResponse(Vehicles vehicles);
    void updateModelFromDto (VehiclesUpdateRequest vehiclesUpdateRequest, @MappingTarget Vehicles vehicles);
}
