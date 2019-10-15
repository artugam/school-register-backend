package com.artur.engineer.payload;


import com.artur.engineer.engine.views.PagedView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class PagedResponse<T> {


    @JsonView({PagedView.class})
    private List<T> content;

    @JsonView({PagedView.class})
    private int page;

    @JsonView({PagedView.class})
    private int size;

    @JsonView({PagedView.class})
    private long totalElements;

    @JsonView({PagedView.class})
    private int totalPages;

    @JsonView({PagedView.class})
    private boolean last;

    public PagedResponse() {

    }

    public PagedResponse(List<T> content, int page, int size, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.page = page + 1;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public PagedResponse(Page<T> query) {
        this.content = query.getContent();
        this.page = query.getNumber() + 1;
        this.size = query.getSize();
        this.totalElements = query.getTotalElements();
        this.totalPages = query.getTotalPages();
        this.last = query.isLast();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page + 1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}