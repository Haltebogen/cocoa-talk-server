package com.haltebogen.gittalk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationResponseDto<T> {
    private int totalPage;
    private Boolean hasNext;
    private T data;
}
