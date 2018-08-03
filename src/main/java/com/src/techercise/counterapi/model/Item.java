package com.src.techercise.counterapi.model;

public class Item {

    private String word;
    private Long times;

    public Item(String word, Long times) {
        this.word = word;
        this.times = times;
    }


    public String getWord() {
        return word;
    }

    public Long getTimes() {
        return times;
    }
}
