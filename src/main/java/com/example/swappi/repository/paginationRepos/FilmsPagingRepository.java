package com.example.swappi.repository.paginationRepos;

import com.example.swappi.models.Films;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface FilmsPagingRepository extends PagingAndSortingRepository<Films, UUID> {
}
