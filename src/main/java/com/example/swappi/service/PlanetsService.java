package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.People;
import com.example.swappi.models.Planets;
import com.example.swappi.repository.modelsRepos.PlanetRepository;
import com.example.swappi.repository.paginationRepos.PlanetsPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlanetsService {

    @Autowired
    private PlanetRepository planetRepo;

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

    public Planets findById(String planetId){
        return planetRepo.findById(UUID.fromString(planetId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", People.class.getName(), planetId);
        });
    }
}
