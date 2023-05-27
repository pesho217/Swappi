package com.example.swappi.repository.modelsRepos;

import com.example.swappi.models.People;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PeopleRepository extends CrudRepository<People, UUID> {
    long count();

}
