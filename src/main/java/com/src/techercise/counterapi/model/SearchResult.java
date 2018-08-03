package com.src.techercise.counterapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {

    @JsonProperty("counts")
    private Item[] items;


    public SearchResult(Item[] items) {
        this.items = items;
    }


    public Item[] getItems() {
        return items;
    }



}
