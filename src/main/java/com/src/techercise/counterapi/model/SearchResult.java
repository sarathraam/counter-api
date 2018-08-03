package com.src.techercise.counterapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class SearchResult {

    // Design choice: Could have used a custom class - Item { word, times}
    // But that produces output like : "counts": [ {word: "abc", times: 2} ]
    // instead of "counts": [ {"abc" : 2} ] - Using Map.Entry enables this.

    @JsonProperty("counts")
    private Map.Entry[] items;


    public SearchResult(Map.Entry[] items) {
        this.items = items;
    }


    public Map.Entry[] getItems() {
        return items;
    }
}
