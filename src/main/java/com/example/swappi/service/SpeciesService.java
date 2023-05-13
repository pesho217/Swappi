package com.example.swappi.service;

import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.Species;
import com.example.swappi.repository.modelsRepos.SpeciesRepository;
import com.example.swappi.repository.paginationRepos.SpeciesPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpeciesService {
    @Autowired
    private SpeciesRepository speciesRepo;

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
}
