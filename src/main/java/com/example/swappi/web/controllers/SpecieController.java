package com.example.swappi.web.controllers;

import com.example.swappi.error.InvalidObjectException;
import com.example.swappi.mapper.SpeciesMapper;
import com.example.swappi.models.Species;
import com.example.swappi.service.SpeciesService;
import com.example.swappi.validation.ObjectValidator;
import com.example.swappi.web.dto.pagination.SwappiPage;
import com.example.swappi.web.dto.species.SpeciesCreateRequest;
import com.example.swappi.web.dto.species.SpeciesResponse;
import com.example.swappi.web.dto.species.SpeciesUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("species")
public class SpecieController {
    @Autowired
    private ObjectValidator validator;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private SpeciesMapper speciesMapper;

    private final Integer PAGE_SIZE = 10;

    @GetMapping(name = "", produces = "application/json")
    public SwappiPage<SpeciesResponse> getAllSpecies(@RequestParam(required = false, defaultValue = "0") Integer currPage) {
        Page<SpeciesResponse> speciesPage = speciesService.fetchAll(currPage, PAGE_SIZE).map(speciesMapper::toSpeciesResponse);

        return new SwappiPage<>(speciesPage);
    }

    @DeleteMapping("{planetId}")
    public void deletePlanetById(@PathVariable String specieId) {
        speciesService.deleteById(specieId);
    }

    @GetMapping("{specieId}")
    public ResponseEntity<SpeciesResponse> getSpecieById(@PathVariable String specieId) {
        Species species = speciesService.findById(specieId);
        return ResponseEntity.ok(speciesMapper.toSpeciesResponse(species));
    }

    @PostMapping("")
    public ResponseEntity<SpeciesResponse> createSpecie(@RequestBody SpeciesCreateRequest speciesCreateRequest) {
        Map<String, String> validationErrors = validator.validate(speciesCreateRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Specie Create", validationErrors);
        }
        Species specie = speciesMapper.toSpecies(speciesCreateRequest);

        speciesService.connectSpeciesWithPlanets(specie,speciesCreateRequest.getHomeworld());
        speciesService.connectSpeciesWithFilms(specie, speciesCreateRequest.getFilmsIds());
        speciesService.connectSpeciesWithPeople(specie, speciesCreateRequest.getCharactersIds());

        Long totalPeople = speciesService.getTotalSpecies();
        Long index = totalPeople + 1;
        specie.setIndex(index);
        Species savedSpecie = speciesService.save(specie);
        SpeciesResponse speciesResponse = speciesMapper.toSpeciesResponse(savedSpecie);
        return ResponseEntity.status(201).body(speciesResponse);
    }

    @PatchMapping("{specieId}")
    public ResponseEntity<SpeciesResponse> updateSpecie(@PathVariable String specieId,
                                                        @RequestBody SpeciesUpdateRequest speciesUpdateRequest) {
        Map<String, String> validationErrors = validator.validate(speciesUpdateRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Specie Create", validationErrors);
        }
        Species specie = speciesService.findById(specieId);
        speciesMapper.updateModelFromDto(speciesUpdateRequest, specie);
        Species updatedSpecie = speciesService.save(specie);
        SpeciesResponse speciesResponse = speciesMapper.toSpeciesResponse(updatedSpecie);
        return ResponseEntity.status(200).body(speciesResponse);
    }
}
