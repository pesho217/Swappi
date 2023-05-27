package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.*;
import com.example.swappi.repository.modelsRepos.FilmsRepository;
import com.example.swappi.repository.modelsRepos.PeopleRepository;
import com.example.swappi.repository.modelsRepos.StarshipsRepository;
import com.example.swappi.repository.paginationRepos.FilmsPagingRepository;
import com.example.swappi.repository.paginationRepos.StarshipsPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StarshipsService {
    @Autowired
    private StarshipsRepository starshipsRepo;
    @Autowired
    private FilmsRepository filmsRepo;
    @Autowired
    private PeopleRepository peopleRepo;

    @Autowired
    private StarshipsPagingRepository pagingRepo;


    public Starships save(Starships starships) {

        return starshipsRepo.save(starships);
    }

    public Page<Starships> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage,pageSize));
    }

    public void deleteById(String starshipsId) {

        starshipsRepo.deleteById(UUID.fromString(starshipsId));
    }

    public Starships findById(String starshipsId){
        return starshipsRepo.findById(UUID.fromString(starshipsId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Starship Not Found", Starships.class.getName(), starshipsId);
        });
    }
    public long getTotalStarships(){
        return starshipsRepo.count();
    }

    public void connectStarshipsWithFilms(Starships starships, List<String> filmIds) {
        if (filmIds != null && !filmIds.isEmpty()) {
            Set<Films> films = new HashSet<>();
            for (String filmId : filmIds) {
                Films film = filmsRepo.findById(UUID.fromString(filmId))
                        .orElseThrow(() -> new NotFoundObjectException("Film not found with id", Films.class.getName(), filmId));
                films.add(film);
            }
            starships.setStarshipsFilms(films);
        }
    }
    public void connectStarshipsWithPeople(Starships starships, List<String> peopleIds) {
        if (peopleIds != null && !peopleIds.isEmpty()) {
            Set<People> people = new HashSet<>();
            for (String peopleId : peopleIds) {
                People person = peopleRepo.findById(UUID.fromString(peopleId))
                        .orElseThrow(() -> new NotFoundObjectException("Person not found with id", Films.class.getName(), peopleId));
                people.add(person);
            }
            starships.setStarshipsPeople(people);
        }
    }
}
