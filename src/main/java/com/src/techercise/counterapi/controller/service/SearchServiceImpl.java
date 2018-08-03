package com.src.techercise.counterapi.controller.service;

import com.src.techercise.counterapi.model.SearchResult;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    private static final String SEARCH_SPACE = "";


    @Override
    public SearchResult findByMatchingWords(String[] words) {
        return null;
    }

    @Override
    public SearchResult topNWords(int n) {
        return null;
    }
}
