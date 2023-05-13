package com.example.swappi.repository.paginationRepos;

import com.example.swappi.models.People;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PeoplePagingRepository extends PagingAndSortingRepository<People, UUID> {
}
