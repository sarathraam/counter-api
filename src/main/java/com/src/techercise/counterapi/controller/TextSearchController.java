package com.src.techercise.counterapi.controller;

import com.src.techercise.counterapi.service.SearchService;
import com.src.techercise.counterapi.model.SearchCriteria;
import com.src.techercise.counterapi.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.src.techercise.counterapi.ApplicationConstants.PLAIN_TEXT_CSV;

@RestController
public class TextSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextSearchController.class);

    @Autowired
    private SearchService searchService;


    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })

    public ResponseEntity<SearchResult> search(@RequestBody SearchCriteria searchCriteria) {

        SearchResult searchResult = searchService.findByMatchingWords(searchCriteria.getSearchTexts());
        return ResponseEntity.ok(searchResult);

    }


    @RequestMapping(path = "/top/{size}", method = RequestMethod.GET, produces = { PLAIN_TEXT_CSV })
    public ResponseEntity<?> top(HttpServletResponse response, @PathVariable("size") int size) {

        SearchResult searchResult = searchService.topNWords(size);

        try {

            writeAsCSV(response, searchResult);

        } catch (IOException e) {

            LOGGER.error("Error while writing CSV", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private void writeAsCSV(HttpServletResponse response, SearchResult searchResult) throws IOException {

        response.setContentType(PLAIN_TEXT_CSV);

        String output = Arrays.stream(searchResult.getItems())
                .map(this::pipeSeparatedValues)
                .collect(Collectors.joining(", "));

        byte[] bytes = output.getBytes("UTF-8");
        response.getOutputStream().write(bytes);

        response.flushBuffer();

    }

    private String pipeSeparatedValues(Map.Entry item) {

        return item.getKey() + "|" + item.getValue();

    }
}
