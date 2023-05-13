package com.example.swappi.web.controllers;

import com.example.swappi.error.InvalidObjectException;
import com.example.swappi.mapper.VehiclesMapper;
import com.example.swappi.models.Vehicles;
import com.example.swappi.service.VehiclesService;
import com.example.swappi.validation.ObjectValidator;
import com.example.swappi.web.dto.pagination.SwappiPage;
import com.example.swappi.web.dto.vehicles.VehiclesCreateRequest;
import com.example.swappi.web.dto.vehicles.VehiclesResponse;
import com.example.swappi.web.dto.vehicles.VehiclesUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("vehicles")
public class VehiclesController {
    @Autowired
    private ObjectValidator validator;

    @Autowired
    private VehiclesMapper vehiclesMapper;
    @Autowired
    private VehiclesService vehiclesService;

    private final Integer PAGE_SIZE = 10;

    @GetMapping(name = "", produces = "application/json")
    public SwappiPage<VehiclesResponse> getAllVehicles(@RequestParam(required = false, defaultValue = "0")
                                                     Integer currPage){

        Page<VehiclesResponse> vehiclesPage = vehiclesService.fetchAll(currPage, PAGE_SIZE).map(vehiclesMapper::toVehiclesResponse);

        return new SwappiPage<>(vehiclesPage);

    }

    @DeleteMapping("{vehicleId}")
    public void deleteVehicleById(@PathVariable String vehicleId){

        vehiclesService.deleteById(vehicleId);
    }

    @GetMapping("{vehicleId}")
    public ResponseEntity<VehiclesResponse> getVehicleById(@PathVariable String vehicleId){
        Vehicles vehicle = vehiclesService.findById(vehicleId);
        return ResponseEntity.ok(vehiclesMapper.toVehiclesResponse(vehicle));
    }


    @PostMapping("")
    public ResponseEntity<VehiclesResponse> createVehicle(@RequestBody VehiclesCreateRequest vehiclesCreateRequest) {
        Map<String, String> validationErrors = validator.validate(vehiclesCreateRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Vehicle Create", validationErrors);
        }
        Vehicles vehicle = vehiclesMapper.toVehicles(vehiclesCreateRequest);
        Vehicles savedVehicle = vehiclesService.save(vehicle);
        VehiclesResponse vehiclesResponse = vehiclesMapper.toVehiclesResponse(savedVehicle);

        return ResponseEntity.status(201).body(vehiclesResponse);
    }
    @PatchMapping("{vehicleId}")
    public ResponseEntity<VehiclesResponse> updateStarship(@PathVariable String vehicleId ,
                                                           @RequestBody VehiclesUpdateRequest vehiclesUpdateRequest){
        Map<String, String> validationErrors = validator.validate(vehiclesUpdateRequest);
        if(validationErrors.size()!=0){
            throw new InvalidObjectException("Invalid Vehicle Create", validationErrors);
        }
        Vehicles vehicle = vehiclesService.findById(vehicleId);
        Vehicles savedVehicle = vehiclesService.save(vehicle);
        vehiclesMapper.updateModelFromDto(vehiclesUpdateRequest,savedVehicle);
        VehiclesResponse vehiclesResponse = vehiclesMapper.toVehiclesResponse(savedVehicle);
        return ResponseEntity.status(200).body(vehiclesResponse);
    }
}
