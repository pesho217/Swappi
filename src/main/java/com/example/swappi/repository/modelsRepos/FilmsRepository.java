package com.example.swappi.repository.modelsRepos;

import com.example.swappi.models.Films;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FilmsRepository extends CrudRepository<Films, UUID> {
}
