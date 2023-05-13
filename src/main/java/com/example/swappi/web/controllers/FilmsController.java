package com.example.swappi.web.controllers;

import com.example.swappi.error.InvalidObjectException;
import com.example.swappi.mapper.FilmsMapper;
import com.example.swappi.models.Films;
import com.example.swappi.service.FilmsService;
import com.example.swappi.validation.ObjectValidator;
import com.example.swappi.web.dto.films.FilmsCreateRequest;
import com.example.swappi.web.dto.films.FilmsResponse;
import com.example.swappi.web.dto.films.FilmsUpdateRequest;
import com.example.swappi.web.dto.pagination.SwappiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmsController {

    @Autowired
    private ObjectValidator validator;
    @Autowired
    private FilmsMapper filmsMapper;

    @Autowired
    private FilmsService filmsService;

    private final Integer PAGE_SIZE = 10;

    @GetMapping(name = "", produces = "application/json")
    public SwappiPage<FilmsResponse> getAllFilms(@RequestParam(required = false, defaultValue = "0")
                                              Integer currPage){

        Page<FilmsResponse> filmsPage = filmsService.fetchAll(currPage, PAGE_SIZE).map(filmsMapper::toFilmResponse);

        return new SwappiPage<>(filmsPage);

    }

    @DeleteMapping("{filmId}")
    public void deleteFilmById(@PathVariable String filmId){

        filmsService.deleteById(filmId);
    }

    @GetMapping("{filmId}")
    public ResponseEntity<FilmsResponse> getFilmById(@PathVariable String filmId){
        Films film = filmsService.findById(filmId);
        return ResponseEntity.ok(filmsMapper.toFilmResponse(film));
    }


    @PostMapping("")
    public ResponseEntity<FilmsResponse> createFilm(@RequestBody FilmsCreateRequest filmsCreateRequest) {
        Map<String, String> validationErrors = validator.validate(filmsCreateRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Films Create", validationErrors);
        }
        Films film = filmsMapper.toFilm(filmsCreateRequest);
        Films savedFilm = filmsService.save(film);
        FilmsResponse filmsResponse = filmsMapper.toFilmResponse(savedFilm);
        return ResponseEntity.status(201).body(filmsResponse);
    }
    @PatchMapping("{personId}")
    public ResponseEntity<FilmsResponse> updatePerson(@PathVariable String filmId ,
                                                       @RequestBody FilmsUpdateRequest filmsUpdateRequest){
        Map<String, String> validationErrors = validator.validate(filmsUpdateRequest);
        if(validationErrors.size()!=0){
            throw new InvalidObjectException("Invalid Film Create", validationErrors);
        }
        Films film = filmsService.findById(filmId);
        filmsMapper.updateModelFromDto(filmsUpdateRequest, film);
        Films updatedFilm = filmsService.save(film);
        FilmsResponse filmsResponse = filmsMapper.toFilmResponse(updatedFilm);
        return ResponseEntity.status(200).body(filmsResponse);
    }
}



