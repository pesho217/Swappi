package com.example.swappi.web.controllers;

import com.example.swappi.error.InvalidObjectException;
import com.example.swappi.mapper.PeopleMapper;
import com.example.swappi.models.People;
import com.example.swappi.repository.paginationRepos.PeoplePagingRepository;
import com.example.swappi.service.PeopleService;
import com.example.swappi.validation.ObjectValidator;
import com.example.swappi.web.dto.pagination.SwappiPage;
import com.example.swappi.web.dto.people.PeopleCreateRequest;
import com.example.swappi.web.dto.people.PeopleResponse;
import com.example.swappi.web.dto.people.PeopleUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private ObjectValidator validator;
    @Autowired
    private PeopleMapper peopleMapper;

    @Autowired
    private PeopleService peopleService;

    private final Integer PAGE_SIZE = 10;

    @GetMapping()
    public SwappiPage<PeopleResponse> getAllPeople(@RequestParam(required = false, defaultValue = "0")Integer currPage){
        Page<PeopleResponse> peoplePage = peopleService.fetchAll(currPage, PAGE_SIZE).map(peopleMapper::toPeopleResponse);
        return new SwappiPage<>(peoplePage);

    }

    @DeleteMapping("{peopleId}")
    public void deletePersonById(@PathVariable String personId){
        peopleService.deleteById(personId);
    }

    @GetMapping("{peopleId}")
    public ResponseEntity<PeopleResponse> getPersonById(@PathVariable String peopleId){
        People person = peopleService.findById(peopleId);
        return ResponseEntity.ok(peopleMapper.toPeopleResponse(person));
    }

    @PostMapping("")
    public ResponseEntity<PeopleResponse> createPerson(@RequestBody PeopleCreateRequest peopleCreateRequest) {
        Map<String, String> validationErrors = validator.validate(peopleCreateRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Person Create", validationErrors);
        }
        People mappedPerson = peopleMapper.toPeople(peopleCreateRequest);
        People savedPerson = peopleService.save(mappedPerson);
        PeopleResponse peopleResponse = peopleMapper.toPeopleResponse(savedPerson);
        return ResponseEntity.status(201).body(peopleResponse);
    }
    @PatchMapping("{personId}")
    public ResponseEntity<PeopleResponse> updatePerson(@PathVariable String personId ,
                                               @RequestBody PeopleUpdateRequest peopleUpdateRequest){
        Map<String, String> validationErrors = validator.validate(peopleUpdateRequest);
        if(validationErrors.size()!=0){
            throw new InvalidObjectException("Invalid Person Create", validationErrors);
        }
        People currentPerson = peopleService.findById(personId);
        peopleMapper.updateModelFromDto(peopleUpdateRequest, currentPerson);
        People updatedPerson = peopleService.save(currentPerson);
        PeopleResponse peopleResponse = peopleMapper.toPeopleResponse(updatedPerson);
        return ResponseEntity.status(200).body(peopleResponse);

    }
}

























//    @Autowired
//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PersonResponse> getPersonById(@PathVariable("id") Long id) {
//        PersonResponse person = personService.getPersonById(id);
//        if (person == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(person);
//    }
//
//    @PostMapping
//    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonCreateRequest request) {
//        PersonResponse person = personService.createPerson(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(person);
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<PersonResponse> updatePerson(
//            @PathVariable("id") Long id, @RequestBody PersonUpdateRequest request) {
//        PersonResponse person = personService.updatePerson(id, request);
//        if (person == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(person);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePersonById(@PathVariable("id") Long id) {
//        boolean deleted = personService.deletePersonById(id);
//        if (!deleted) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.noContent().build();
//    }
//
//}
