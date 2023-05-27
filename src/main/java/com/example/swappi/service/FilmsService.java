package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.*;
import com.example.swappi.repository.modelsRepos.*;
import com.example.swappi.repository.paginationRepos.FilmsPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class FilmsService {

    @Autowired
    private PeopleRepository peopleRepo;
    @Autowired
    private PlanetRepository planetRepo;
    @Autowired
    private SpeciesRepository speciesRepo;
    @Autowired
    private StarshipsRepository starshipsRepo;
    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private FilmsRepository filmsRepo;

    @Autowired
    private FilmsPagingRepository pagingRepo;


    public Films save(Films films) {

        return filmsRepo.save(films);
    }

    public Page<Films> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage,pageSize));
    }

    public void deleteById(String filmsId) {

        filmsRepo.deleteById(UUID.fromString(filmsId));
    }

    public Films findById(String filmsId){
        return filmsRepo.findById(UUID.fromString(filmsId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Film Not Found", Films.class.getName(), filmsId);
        });
    }
    public long getTotalFilms(){
        return filmsRepo.count();
    }

    public void connectFilmsWithPeople(Films films, List<String> peopleIds) {
        if (peopleIds != null && !peopleIds.isEmpty()) {
            Set<People> people = new HashSet<>();
            for (String peopleId : peopleIds) {
                People person = peopleRepo.findById(UUID.fromString(peopleId))
                        .orElseThrow(() -> new NotFoundObjectException("Person not found with id", Films.class.getName(), peopleId));
                people.add(person);
            }
            films.setFilmCharacters(people);
        }
    }
    public void connectFilmsWithPlanets(Films films, List<String> planetIds) {
        if (planetIds != null && !planetIds.isEmpty()) {
            Set<Planets> planets = new HashSet<>();
            for (String planetId : planetIds) {
                Planets planet = planetRepo.findById(UUID.fromString(planetId))
                        .orElseThrow(() -> new NotFoundObjectException("Planet not found with id", Planets.class.getName(), planetId));
                planets.add(planet);
            }
            films.setFilmPlanets(planets);
        }
    }

    public void connectFilmsWithSpecies(Films films, List<String> speciesIds) {
        if (speciesIds != null && !speciesIds.isEmpty()) {
            Set<Species> species = new HashSet<>();
            for (String speciesId : speciesIds) {
                Species specie = speciesRepo.findById(UUID.fromString(speciesId)).orElseThrow(() -> new NotFoundObjectException("Specie not found with id", Species.class.getName(), speciesId));
                species.add(specie);
            }
            films.setFilmSpecies(species);
        }

    }
    public void connectFilmsWithVehicles(Films films, List<String> vehiclesIds) {
        if (vehiclesIds != null && !vehiclesIds.isEmpty()) {
            Set<Vehicles> vehicles  = new HashSet<>();
            for (String vehicleId : vehiclesIds) {
                Vehicles vehicle = vehicleRepo.findById(UUID.fromString(vehicleId)).orElseThrow(() ->
                        new NotFoundObjectException("Vehicle not found with id", Vehicles.class.getName(), vehicleId));
                vehicles.add(vehicle);
            }
            films.setFilmVehicles(vehicles);
        }
    }
    public void connectFilmsWithStarships(Films films, List<String> starshipsIds) {
        if (starshipsIds != null && !starshipsIds.isEmpty()) {
            Set<Starships> starships = new HashSet<>();
            for (String starshipId : starshipsIds) {
                Starships starship = starshipsRepo.findById(UUID.fromString(starshipId)).orElseThrow(() ->
                        new NotFoundObjectException("Starship not found with id", Starships.class.getName(), starshipId));
                starships.add(starship);
            }
            films.setFilmStarships(starships);
        }
    }
}
