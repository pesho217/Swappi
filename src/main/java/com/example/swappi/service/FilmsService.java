package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.Films;
import com.example.swappi.repository.modelsRepos.FilmsRepository;
import com.example.swappi.repository.paginationRepos.FilmsPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class FilmsService {

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
}
