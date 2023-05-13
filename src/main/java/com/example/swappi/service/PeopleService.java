package com.example.swappi.service;


import com.example.swappi.error.NotFoundObjectException;
import com.example.swappi.models.People;
import com.example.swappi.repository.modelsRepos.PeopleRepository;
import com.example.swappi.repository.paginationRepos.PeoplePagingRepository;
import com.example.swappi.web.dto.people.PeopleCreateRequest;
import com.example.swappi.web.dto.people.PeopleUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepo;

    @Autowired
    private PeoplePagingRepository pagingRepo;

    public People save(People people) {

        return peopleRepo.save(people);
    }

    public Page<People> fetchAll(int currentPage, int pageSize){
        return pagingRepo.findAll(PageRequest.of(currentPage,pageSize));
    }

    public void deleteById(String peopleId) {

        peopleRepo.deleteById(UUID.fromString(peopleId));
    }

    public People findById(String peopleId){
       return peopleRepo.findById(UUID.fromString(peopleId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", People.class.getName(), peopleId);
        });
    }
}
