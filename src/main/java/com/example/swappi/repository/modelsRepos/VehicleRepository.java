package com.example.swappi.repository.modelsRepos;

import com.example.swappi.models.Vehicles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface VehicleRepository extends CrudRepository<Vehicles, UUID> {

}
