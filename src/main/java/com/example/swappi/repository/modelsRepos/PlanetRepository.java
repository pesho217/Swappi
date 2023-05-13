package com.example.swappi.repository.modelsRepos;

import com.example.swappi.models.Planets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlanetRepository extends CrudRepository<Planets, UUID> {

}
