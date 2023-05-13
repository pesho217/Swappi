package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.Films;
import com.example.swappi.models.Vehicles;
import com.example.swappi.repository.modelsRepos.FilmsRepository;
import com.example.swappi.repository.modelsRepos.VehicleRepository;
import com.example.swappi.repository.paginationRepos.FilmsPagingRepository;
import com.example.swappi.repository.paginationRepos.VehiclesPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehiclesService {
    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private VehiclesPagingRepository pagingRepo;


    public Vehicles save(Vehicles vehicles) {

        return vehicleRepo.save(vehicles);
    }

    public Page<Vehicles> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage,pageSize));
    }

    public void deleteById(String vehicleId) {

        vehicleRepo.deleteById(UUID.fromString(vehicleId));
    }

    public Vehicles findById(String vehicleId){
        return vehicleRepo.findById(UUID.fromString(vehicleId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Vehicle Not Found", Vehicles.class.getName(), vehicleId);
        });
    }
}

