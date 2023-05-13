package com.example.swappi.repository.paginationRepos;

import com.example.swappi.models.Species;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface SpeciesPagingRepository extends PagingAndSortingRepository<Species, UUID> {
}
