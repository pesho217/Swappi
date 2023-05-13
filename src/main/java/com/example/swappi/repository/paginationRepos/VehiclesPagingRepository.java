package com.example.swappi.repository.paginationRepos;

import com.example.swappi.models.Vehicles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface VehiclesPagingRepository extends PagingAndSortingRepository<Vehicles, UUID> {
}
