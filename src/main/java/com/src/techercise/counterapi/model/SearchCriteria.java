package com.src.techercise.counterapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchCriteria {

    @JsonProperty("searchText")
    private String[] searchTexts;


    public String[] getSearchTexts() {
        return searchTexts;
    }
}
