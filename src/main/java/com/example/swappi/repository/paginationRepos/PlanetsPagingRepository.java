package com.example.swappi.repository.paginationRepos;

import com.example.swappi.models.Planets;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PlanetsPagingRepository extends PagingAndSortingRepository<Planets, UUID> {
}
