package com.example.swappi.web.controllers;

import com.example.swappi.error.InvalidObjectException;
import com.example.swappi.mapper.StarshipsMapper;
import com.example.swappi.models.Starships;
import com.example.swappi.service.StarshipsService;
import com.example.swappi.validation.ObjectValidator;
import com.example.swappi.web.dto.pagination.SwappiPage;
import com.example.swappi.web.dto.starships.StarshipsCreateRequest;
import com.example.swappi.web.dto.starships.StarshipsResponse;
import com.example.swappi.web.dto.starships.StarshipsUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("starships")
public class StarshipsController {
    @Autowired
    private ObjectValidator validator;

    @Autowired
    private StarshipsMapper starshipsMapper;
    @Autowired
    private StarshipsService starshipsService;

    private final Integer PAGE_SIZE = 10;

    @GetMapping(name = "", produces = "application/json")
    public SwappiPage<StarshipsResponse> getAllFilms(@RequestParam(required = false, defaultValue = "0")
                                                 Integer currPage){

        Page<StarshipsResponse> starshipsPage = starshipsService.fetchAll(currPage, PAGE_SIZE).map(starshipsMapper::toStarshipsResponse);

        return new SwappiPage<>(starshipsPage);

    }

    @DeleteMapping("{starshipId}")
    public void deleteFilmById(@PathVariable String starshipId){

        starshipsService.deleteById(starshipId);
    }

    @GetMapping("{starshipId}")
    public ResponseEntity<StarshipsResponse> getStarshipById(@PathVariable String starshipId){
        Starships starship = starshipsService.findById(starshipId);
        return ResponseEntity.ok(starshipsMapper.toStarshipsResponse(starship));
    }


    @PostMapping("")
    public ResponseEntity<StarshipsResponse> createStarship(@RequestBody StarshipsCreateRequest starshipsCreateRequest) {
        Map<String, String> validationErrors = validator.validate(starshipsCreateRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Starship Create", validationErrors);
        }
        Starships starship = starshipsMapper.toStarships(starshipsCreateRequest);
        starshipsService.connectStarshipsWithFilms(starship,starshipsCreateRequest.getFilmsIds());
        starshipsService.connectStarshipsWithPeople(starship, starshipsCreateRequest.getCharactersIds());

        Long totalPeople = starshipsService.getTotalStarships();
        Long index = totalPeople + 1;
        starship.setIndex(index);
        Starships savedStarship = starshipsService.save(starship);
        StarshipsResponse starshipsResponse = starshipsMapper.toStarshipsResponse(savedStarship);

        return ResponseEntity.status(201).body(starshipsResponse);
    }
    @PatchMapping("{starshipId}")
    public ResponseEntity<StarshipsResponse> updateStarship(@PathVariable String starshipId ,
                                                            @RequestBody StarshipsUpdateRequest starshipsUpdateRequest){
        Map<String, String> validationErrors = validator.validate(starshipsUpdateRequest);
        if(validationErrors.size()!=0){
            throw new InvalidObjectException("Invalid Starship Create", validationErrors);
        }
        Starships starship = starshipsService.findById(starshipId);
        Starships savedStarship = starshipsService.save(starship);
        starshipsMapper.updateModelFromDto(starshipsUpdateRequest,savedStarship);
        StarshipsResponse starshipsResponse = starshipsMapper.toStarshipsResponse(savedStarship);
        return ResponseEntity.status(200).body(starshipsResponse);
    }
}
