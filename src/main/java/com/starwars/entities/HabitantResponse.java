package com.starwars.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Nullable;

import java.util.List;

public class HabitantResponse {
    @JsonProperty("count")
    private int count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("results")
    private List<Habitant> results;

    public HabitantResponse()
    {
        super();
    }

    public HabitantResponse(int count, @Nullable String next, @Nullable String previous, List<Habitant> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Habitant> getResults() {
        return results;
    }

    public void setResults(List<Habitant> results) {
        this.results = results;
    }
}
