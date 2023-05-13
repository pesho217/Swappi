package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.Films;
import com.example.swappi.models.Starships;
import com.example.swappi.repository.modelsRepos.FilmsRepository;
import com.example.swappi.repository.modelsRepos.StarshipsRepository;
import com.example.swappi.repository.paginationRepos.FilmsPagingRepository;
import com.example.swappi.repository.paginationRepos.StarshipsPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StarshipsService {
    @Autowired
    private StarshipsRepository starshipsRepo;

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
}
