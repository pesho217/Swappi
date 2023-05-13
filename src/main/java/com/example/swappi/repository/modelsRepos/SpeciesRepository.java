package com.example.swappi.repository.modelsRepos;

import com.example.swappi.models.Species;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpeciesRepository extends CrudRepository<Species, UUID> {
}
