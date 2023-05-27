package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.*;
import com.example.swappi.repository.modelsRepos.FilmsRepository;
import com.example.swappi.repository.modelsRepos.PeopleRepository;
import com.example.swappi.repository.modelsRepos.PlanetRepository;
import com.example.swappi.repository.paginationRepos.PlanetsPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PlanetsService {

    @Autowired
    private PlanetRepository planetRepo;
    @Autowired
    private PeopleRepository peopleRepo;
    @Autowired
    private FilmsRepository filmsRepo;

    @Autowired
    private PlanetsPagingRepository pagingRepo;

    public Planets save(Planets planets) {

        return planetRepo.save(planets);
    }

   public Page<Planets> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage,pageSize));
   }

    public void deleteById(String planetId) {

        planetRepo.deleteById(UUID.fromString(planetId));
    }
    public Planets findById(String planetsId){
        return planetRepo.findById(UUID.fromString(planetsId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Planet Not Found", Planets.class.getName(), planetsId);
        });
    }

    public long getTotalPlanets(){
        return planetRepo.count();
    }

    public void connectPlanetsWithFilms(Planets planets, List<String> filmIds) {
        if (filmIds != null && !filmIds.isEmpty()) {
            Set<Films> films = new HashSet<>();
            for (String filmId : filmIds) {
                Films film = filmsRepo.findById(UUID.fromString(filmId))
                        .orElseThrow(() -> new NotFoundObjectException("Film not found with id", Films.class.getName(), filmId));
                films.add(film);
            }
            planets.setPlanetsFilms(films);
        }
    }
    public void connectPlanetsWithPeople(Planets planets, List<String> peopleIds) {
        if (peopleIds != null && !peopleIds.isEmpty()) {
            Set<People> people = new HashSet<>();
            for (String peopleId : peopleIds) {
                People person = peopleRepo.findById(UUID.fromString(peopleId))
                        .orElseThrow(() -> new NotFoundObjectException("Person not found with id", Films.class.getName(), peopleId));
                people.add(person);
            }
            planets.setPlanetsPeople(people);
        }
    }

}
