package com.starwars.controllers;
import com.starwars.apis.StarWarsApi;
import com.starwars.entities.FilmResponse;
import com.starwars.managers.StarWarsManager;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.annotation.Nullable;

/**
 * Class that expose the methods that was accesibles to request
 * @author: Omaira Dorta
 * @version: 25/05/2001
 */
@Controller("/StarWars")
public class StarWarsController {
    private final StarWarsApi api;
    private final StarWarsManager manager;

    public StarWarsController(StarWarsApi api, StarWarsManager manager) {
        this.api = api;
        this.manager = manager;
    }

    /**
     * @param command Is the action that we need to call to the API
     * @param query Is the filter to search. For example; searchquery=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns a json with the data that we need exactly depends on command and goToPage
     */
    @Get(produces = "application/json")
    public String GetStarWarsInformation(String command, @Nullable String query, @Nullable String goToPage ) {
        String response = manager.GetStarWarsInfoFilter(command, query, goToPage);
        return response;
    }
}
