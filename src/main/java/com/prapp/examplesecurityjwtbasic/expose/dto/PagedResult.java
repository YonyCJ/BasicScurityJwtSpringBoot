package com.prapp.examplesecurityjwtbasic.expose.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PagedResult<T>  {
    private List<T> content;
    private long totalItems;
    private int page;
    private int pageSize;
}
