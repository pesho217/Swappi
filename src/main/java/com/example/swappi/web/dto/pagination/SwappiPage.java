package com.example.swappi.web.dto.pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@EqualsAndHashCode
public class SwappiPage<T> {
    private List<T> content;
    private PaginationMetadata pagination;
    public SwappiPage(Page<T> springPage) {
        this.content = springPage.getContent();
        this.pagination = PaginationMetadata.builder()
                .currentPage(springPage.getPageable().getPageNumber())
                .pageSize(springPage.getPageable().getPageSize())
                .totalElements(springPage.getTotalElements())
                .totalPages(springPage.getTotalPages())
                .build();
    }


}


