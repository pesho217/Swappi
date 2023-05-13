package com.example.swappi.repository.paginationRepos;

import com.example.swappi.models.Starships;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface StarshipsPagingRepository extends PagingAndSortingRepository<Starships, UUID> {
}
