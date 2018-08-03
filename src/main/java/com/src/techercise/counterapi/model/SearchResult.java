package com.src.techercise.counterapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class SearchResult {

    @JsonProperty("counts")
    private Map.Entry[] items;


    public SearchResult(Map.Entry[] items) {
        this.items = items;
    }


    public Map.Entry[] getItems() {
        return items;
    }
}
