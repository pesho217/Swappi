package com.example.swappi.service;


import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.*;
import com.example.swappi.repository.modelsRepos.*;
import com.example.swappi.repository.paginationRepos.PeoplePagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepo;

    @Autowired
    private FilmsRepository filmsRepo;
    @Autowired
    private PlanetRepository planetRepo;
    @Autowired
    private SpeciesRepository speciesRepo;
    @Autowired
    private VehicleRepository vehicleRepo;
    @Autowired
    private StarshipsRepository starshipsRepo;

    @Autowired
    private PeoplePagingRepository pagingRepo;

    public People save(People people) {

        return peopleRepo.save(people);
    }

    public Page<People> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage,pageSize));
    }
    public void deleteAll(){
        peopleRepo.deleteAll();
    }

    public void deleteById(String peopleId) {

        peopleRepo.deleteById(UUID.fromString(peopleId));
    }

    public long getTotalPeople(){
        return peopleRepo.count();
    }

    public People findById(String peopleId){
       return peopleRepo.findById(UUID.fromString(peopleId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", People.class.getName(), peopleId);
        });
    }
    public void connectPeopleWithFilms(People people, List<String> filmIds) {
        if (filmIds != null && !filmIds.isEmpty()) {
            Set<Films> films = new HashSet<>();
            for (String filmId : filmIds) {
                Films film = filmsRepo.findById(UUID.fromString(filmId)).orElseThrow(()
                        -> new NotFoundObjectException("Film not found with id", Films.class.getName(), filmId));
                film.getFilmCharacters().add(people);
                films.add(film);
            }
            people.setPeopleFilms(films);
        }
    }
    public void connectPeopleWithPlanets(People people, String planetId) {
        Planets planet = new Planets();
        if (planetId != null && !planetId.isEmpty()) {
                planet = planetRepo.findById(UUID.fromString(planetId)).orElseThrow(()
                        -> new NotFoundObjectException("Planet not found with id", Planets.class.getName(), planetId));
                planet.getPlanetsPeople().add(people);
                people.setPeoplePlanets(planet);
            }

        }

    public void connectPeopleWithSpecies(People people, List<String> speciesIds) {
        if (speciesIds != null && !speciesIds.isEmpty()) {
            Set<Species> species = new HashSet<>();
            for (String speciesId : speciesIds) {
                Species specie = speciesRepo.findById(UUID.fromString(speciesId)).orElseThrow(()
                        -> new NotFoundObjectException("Specie not found with id", Species.class.getName(), speciesId));
                specie.getSpeciesPeople().add(people);
                species.add(specie);
            }
            people.setPeopleSpecies(species);
        }

    }
    public void connectPeopleWithVehicles(People people, List<String> vehiclesIds) {
        if (vehiclesIds != null && !vehiclesIds.isEmpty()) {
            Set<Vehicles> vehicles  = new HashSet<>();
            for (String vehicleId : vehiclesIds) {
                Vehicles vehicle = vehicleRepo.findById(UUID.fromString(vehicleId)).orElseThrow(() ->
                        new NotFoundObjectException("Vehicle not found with id", Vehicles.class.getName(), vehicleId));
                vehicle.getVehiclesPeople().add(people);
                vehicles.add(vehicle);
            }
            people.setPeopleVehicles(vehicles);
        }
    }
    public void connectPeopleWithStarships(People people, List<String> starshipsIds) {
        if (starshipsIds != null && !starshipsIds.isEmpty()) {
            Set<Starships> starships = new HashSet<>();
            for (String starshipId : starshipsIds) {
                Starships starship = starshipsRepo.findById(UUID.fromString(starshipId)).orElseThrow(() ->
                        new NotFoundObjectException("Starship not found with id", Starships.class.getName(), starshipId));
                starships.add(starship);
                starship.getStarshipsPeople().add(people);
            }
            people.setPeopleStarships(starships);
        }
    }
}

