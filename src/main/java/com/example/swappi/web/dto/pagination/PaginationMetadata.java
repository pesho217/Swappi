package com.example.swappi.web.dto.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationMetadata {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
}
