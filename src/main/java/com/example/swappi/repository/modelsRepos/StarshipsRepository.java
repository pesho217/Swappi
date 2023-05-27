package com.example.swappi.repository.modelsRepos;

import com.example.swappi.models.Starships;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StarshipsRepository extends CrudRepository<Starships, UUID> {
    long count();

}
