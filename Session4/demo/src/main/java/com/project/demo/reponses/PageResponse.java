package com.project.demo.reponses;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> items;
    private Integer page;
    private Integer size;
    private Integer totalItems;
    private Integer totalPages;
    private Boolean isLast;
}
