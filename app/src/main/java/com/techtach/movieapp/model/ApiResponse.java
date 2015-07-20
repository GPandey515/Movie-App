package com.techtach.movieapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noones on 7/12/15.
 */
public class ApiResponse {
    private ArrayList<Movies> results;
    private String page;
    private String total_pages;
    private String total_results;

    public List<Movies> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movies> results) {
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }
}

