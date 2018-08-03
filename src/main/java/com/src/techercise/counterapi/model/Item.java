package com.src.techercise.counterapi.model;

public class Item {

    private String word;
    private int times;

    public Item(String word, int times) {
        this.word = word;
        this.times = times;
    }


    public String getWord() {
        return word;
    }

    public int getTimes() {
        return times;
    }
}
