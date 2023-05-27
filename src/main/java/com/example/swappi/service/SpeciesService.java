package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.*;
import com.example.swappi.repository.modelsRepos.FilmsRepository;
import com.example.swappi.repository.modelsRepos.PeopleRepository;
import com.example.swappi.repository.modelsRepos.PlanetRepository;
import com.example.swappi.repository.modelsRepos.SpeciesRepository;
import com.example.swappi.repository.paginationRepos.SpeciesPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class SpeciesService {
    @Autowired
    private SpeciesRepository speciesRepo;
    @Autowired
    private FilmsRepository filmsRepo;
    @Autowired
    private PeopleRepository peopleRepo;
    @Autowired
    private PlanetRepository planetRepo;

    @Autowired
    private SpeciesPagingRepository pagingRepo;

    public Species save(Species species) {

        return speciesRepo.save(species);
    }

    public Page<Species> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage,pageSize));
    }

    public void deleteById(String specieId) {

        speciesRepo.deleteById(UUID.fromString(specieId));
    }

    public Species findById(String specieId){
        return speciesRepo.findById(UUID.fromString(specieId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Specie Not Found", Species.class.getName(), specieId);
        });
    }
    public long getTotalSpecies(){
        return speciesRepo.count();
    }

    public void connectSpeciesWithFilms(Species species, List<String> filmIds) {
        if (filmIds != null && !filmIds.isEmpty()) {
            Set<Films> films = new HashSet<>();
            for (String filmId : filmIds) {
                Films film = filmsRepo.findById(UUID.fromString(filmId))
                        .orElseThrow(() -> new NotFoundObjectException("Film not found with id", Films.class.getName(), filmId));
                films.add(film);
            }
            species.setSpeciesFilms(films);
        }
    }
    public void connectSpeciesWithPeople(Species species, List<String> peopleIds) {
        if (peopleIds != null && !peopleIds.isEmpty()) {
            Set<People> people = new HashSet<>();
            for (String peopleId : peopleIds) {
                People person = peopleRepo.findById(UUID.fromString(peopleId))
                        .orElseThrow(() -> new NotFoundObjectException("Person not found with id", Films.class.getName(), peopleId));
                people.add(person);
            }
            species.setSpeciesPeople(people);
        }
    }
    public void connectSpeciesWithPlanets(Species species, String planetId) {
        Planets planet = new Planets();
        if (planetId != null && !planetId.isEmpty()) {
            planet = planetRepo.findById(UUID.fromString(planetId))
                    .orElseThrow(() -> new NotFoundObjectException("Planet not found with id", Planets.class.getName(), planetId));
        }else{
            planet = null;
        }
        species.setSpeciesPlanets(planet);
    }


}
