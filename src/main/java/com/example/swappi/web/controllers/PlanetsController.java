package com.example.swappi.web.controllers;

import com.example.swappi.error.InvalidObjectException;
import com.example.swappi.mapper.PlanetsMapper;
import com.example.swappi.models.Planets;
import com.example.swappi.repository.paginationRepos.PlanetsPagingRepository;
import com.example.swappi.service.PlanetsService;
import com.example.swappi.validation.ObjectValidator;
import com.example.swappi.web.dto.pagination.SwappiPage;
import com.example.swappi.web.dto.planets.PlanetsCreateRequest;
import com.example.swappi.web.dto.planets.PlanetsResponse;
import com.example.swappi.web.dto.planets.PlanetsUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("planets")
public class PlanetsController {

    @Autowired
    private ObjectValidator validator;

    @Autowired
    private PlanetsService planetsService;

    @Autowired
    private PlanetsMapper planetsMapper;

    private final Integer PAGE_SIZE = 10;

    @GetMapping(name = "", produces = "application/json")
    public SwappiPage<PlanetsResponse>  getAllPlanets(@RequestParam(required = false, defaultValue = "0")Integer currPage){
        Page<PlanetsResponse> planetsPage = planetsService.fetchAll(currPage, PAGE_SIZE).map(planetsMapper::toPlanetsResponse);

        return new SwappiPage<>(planetsPage);
    }

    @DeleteMapping("{planetId}")
    public void deletePlanetById(@PathVariable String planetId){
        planetsService.deleteById(planetId);
    }

    @GetMapping("{planetId")
    public ResponseEntity<PlanetsResponse> getPlanetById(@PathVariable String planetId){
        Planets planets = planetsService.findById(planetId);
        return ResponseEntity.ok(planetsMapper.toPlanetsResponse(planets));
    }

    @PostMapping("")
    public ResponseEntity<PlanetsResponse> createPlanet(PlanetsCreateRequest planetsCreateRequest){
        Map<String,String> validationErrors = validator.validate(planetsCreateRequest);
        if(validationErrors.size()!=0){
            throw new InvalidObjectException("Invalid Planet Create", validationErrors);
        }
        Planets planet = planetsMapper.toPlanets(planetsCreateRequest);
        Planets savedPlanet = planetsService.save(planet);
        PlanetsResponse planetsResponse = planetsMapper.toPlanetsResponse(savedPlanet);
        return ResponseEntity.status(200).body(planetsResponse);
    }

    @PatchMapping("{planetId}")
    public ResponseEntity<PlanetsResponse> updatePlanet(@PathVariable String planetId,
                                                        @RequestBody PlanetsUpdateRequest planetsUpdateRequest){
        Map<String, String> validationErrors = validator.validate(planetsUpdateRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Planet Create", validationErrors);
        }
        Planets planet = planetsService.findById(planetId);
        planetsMapper.updateModelFromDto(planetsUpdateRequest,planet);
        Planets updatedPlanet = planetsService.save(planet);
        PlanetsResponse planetsResponse = planetsMapper.toPlanetsResponse(updatedPlanet);
        return ResponseEntity.status(200).body(planetsResponse);


    }
}
