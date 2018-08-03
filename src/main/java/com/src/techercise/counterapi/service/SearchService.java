package com.src.techercise.counterapi.service;

import com.src.techercise.counterapi.model.SearchResult;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchService {

    SearchResult findByMatchingWords(String[] words);

    SearchResult topNWords(int n);

}
