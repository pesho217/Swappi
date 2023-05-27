package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.*;
import com.example.swappi.repository.modelsRepos.FilmsRepository;
import com.example.swappi.repository.modelsRepos.PeopleRepository;
import com.example.swappi.repository.modelsRepos.VehicleRepository;
import com.example.swappi.repository.paginationRepos.FilmsPagingRepository;
import com.example.swappi.repository.paginationRepos.VehiclesPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class VehiclesService {
    @Autowired
    private VehicleRepository vehicleRepo;
    @Autowired
    private FilmsRepository filmsRepo;
    @Autowired
    private PeopleRepository peopleRepo;

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
    public long getTotalVehicles(){
        return vehicleRepo.count();
    }

    public void connectVehiclesWithFilms(Vehicles vehicles, List<String> filmIds) {
        if (filmIds != null && !filmIds.isEmpty()) {
            Set<Films> films = new HashSet<>();
            for (String filmId : filmIds) {
                Films film = filmsRepo.findById(UUID.fromString(filmId))
                        .orElseThrow(() -> new NotFoundObjectException("Film not found with id", Films.class.getName(), filmId));
                films.add(film);
            }
            vehicles.setVehiclesFilms(films);
        }
    }
    public void connectVehiclesWithPeople(Vehicles vehicles, List<String> peopleIds) {
        if (peopleIds != null && !peopleIds.isEmpty()) {
            Set<People> people = new HashSet<>();
            for (String peopleId : peopleIds) {
                People person = peopleRepo.findById(UUID.fromString(peopleId))
                        .orElseThrow(() -> new NotFoundObjectException("Person not found with id", Films.class.getName(), peopleId));
                people.add(person);
            }
            vehicles.setVehiclesPeople(people);
        }
    }
}

