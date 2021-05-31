package com.starwars.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.apis.StarWarsApi;
import com.starwars.entities.*;

import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
/**
 * Manager Class to return the json with the data it depends to the parameter
 * command go to the correct method in the client API
 *
 * @author: Omaira Dorta
 * @version: 25/05/2001
 */
public class StarWarsManager {
    private final StarWarsApi api;
    public StarWarsManager(StarWarsApi api) {
        this.api = api;
    }

    /**
     * Get the info depending of the parameters
     * @param command Is the action that we need to call to the API
     * @param searchquery Is the filter to search. For example; searchquery=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns that build a json with the data that we need exactly depends on command and goToPage
     */
    public String  GetStarWarsInfoFilter(String command, @Nullable String searchquery, @Nullable String goToPage )  {
        try {
            String result = "";
            switch (command) {
                case "films":
                    FilmResponse response = api.getFilms(command,searchquery, goToPage);
                    result = new ObjectMapper().writeValueAsString(response.getResults());

                case "people" :
                    HabitantResponse habitantRes = new HabitantResponse();
                    habitantRes = api.getHabitants(command,searchquery, goToPage);
                    if(goToPage != null)
                        result = new ObjectMapper().writeValueAsString(habitantRes.getResults().get(0));
                    else
                        result = new ObjectMapper().writeValueAsString(habitantRes);
                    break;
                case "planets":
                    PlanetResponse planetRes = new PlanetResponse();
                    planetRes = api.getPlanets(command,searchquery, goToPage);
                    result = new ObjectMapper().writeValueAsString(planetRes);
                    break;
                case "starships":
                    StarshipResponse starShipRes = new StarshipResponse();
                    starShipRes = api.getStarships(command, searchquery, goToPage);
                    if(goToPage != null)
                        result = new ObjectMapper().writeValueAsString(starShipRes.getResults().get(0));
                    else
                        result = new ObjectMapper().writeValueAsString(starShipRes);
                    break;
                case "habitantsPlanet":
                    PlanetResponse planetsLookUpRes = new PlanetResponse();
                    planetsLookUpRes = api.getPlanets("planets",searchquery, goToPage);
                    result = new ObjectMapper().writeValueAsString(planetsLookUpRes.getResults().get(0).getResidents());
                    break;
                case "starShipByPerson":
                    HabitantResponse personLookUpRes = new HabitantResponse();
                    personLookUpRes = api.getHabitants("people",searchquery, goToPage);
                    result = new ObjectMapper().writeValueAsString(personLookUpRes.getResults().get(0).getStarships());
                    break;
                default:
                    Object res = api.GetStarWarsApiInfo(command, searchquery, goToPage);
                    String result1 = new ObjectMapper().writeValueAsString(res);
                    return result1;
            }
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
