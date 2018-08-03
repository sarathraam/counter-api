package com.src.techercise.counterapi.service;

import com.src.techercise.counterapi.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

import static com.src.techercise.counterapi.ApplicationConstants.DATA_PATH;
import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.*;

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    private static Map<String, Long> wordTokens = Collections.emptyMap();


    @PostConstruct
    public void initialize() {

        Resource resource = new ClassPathResource(DATA_PATH);
        InputStream is;

        try {
            is = resource.getInputStream();
        } catch (IOException e) {

            LOGGER.error("Missing data file?!", e);
            return;

        }

        StringBuilder builder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

        } catch (IOException e) {

            LOGGER.error("Error while reading data file", e);

        }

        String[] allWords = builder.toString().split("\\s+");
        wordTokens = mapSortedByValue(
                Arrays.stream(allWords)
                        .collect(groupingBy(Function.identity(), counting()))
        );

    }

    private Map<String, Long> mapSortedByValue(Map<String, Long> map) {

        Map<String, Long> sorted = new LinkedHashMap<>();

        List<Map.Entry<String, Long>> entries = new ArrayList<>(map.entrySet());
        entries.stream()
                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> sorted.put(entry.getKey(), entry.getValue()));

        return sorted;
    }


    @Override
    public SearchResult findByMatchingWords(String[] words) {

        if (words == null || words.length == 0) {
            return new SearchResult(new Map.Entry[0]);
        }

        Map.Entry[] items = new Map.Entry[words.length];

        for (int i = 0; i < words.length; i++) {
            Long count = wordTokens.getOrDefault(words[i], 0L);
            items[i] = new AbstractMap.SimpleEntry(words[i], count);
        }

        return new SearchResult(items);
    }

    @Override
    public SearchResult topNWords(int n) {

        int capped = Math.max(0, n);

        List<Map.Entry<String, Long>> topWords = wordTokens.entrySet().stream()
                .limit(capped)
                .collect(toList());

        Map.Entry[] items = new Map.Entry[topWords.size()];
        for (int i = 0; i < topWords.size(); i++) {
            items[i] = topWords.get(i);
        }

        return new SearchResult(items);

    }
}
