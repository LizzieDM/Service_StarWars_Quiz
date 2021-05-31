package com.starwars.apis;
import com.starwars.entities.*;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.reactivex.Flowable;


import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;


@Singleton
@Client
@ExecuteOn(TaskExecutors.IO)
/**
 * Client class to connect to StarWars API and retrieve the Data
 * @author: Omaira Dorta
 * @version: 25/05/2001
 */
public class StarWarsApi {
    private final @Client("https://swapi.dev/api/") @Inject
    RxHttpClient httpClient;

    public StarWarsApi(RxHttpClient httpClient) {

        this.httpClient = httpClient;
    }

    /**
     * Get the info of Planets
     * @param command Is the action that we need to call to the API
     * @param filter Is the filter to search. For example; filter=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns PlanetResponse with the data that we need exactly depends on command and goToPage
     */
    public PlanetResponse getPlanets(String command, @Nullable String filter, @Nullable String goToPage) {
        PlanetResponse planetRes = new PlanetResponse();
        try {
            String url = getUrl(command, filter, goToPage);
            HttpRequest<?> req = HttpRequest.GET(url);
            if(goToPage != null){
                HttpResponse<Planet> rsp = httpClient.toBlocking().exchange(req, Argument.of(Planet.class));
                if (rsp.getStatus().equals(HttpStatus.OK)) {
                    Planet planet = rsp.body();
                    ArrayList<Planet> planetList = new ArrayList<Planet>();
                    planetList.add(planet);
                    planetRes.setResults(planetList);
                }
            }else {
                HttpResponse<PlanetResponse> rsp = httpClient.toBlocking().exchange(req, Argument.of(PlanetResponse.class));
                if (rsp.getStatus().equals(HttpStatus.OK)) {
                    planetRes = rsp.body();
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return planetRes;
    }
    /**
     * Get the info of characters of the films
     * @param command Is the action that we need to call to the API
     * @param filter Is the filter to search. For example; filter=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns HabitantResponse with the data that we need exactly depends on command and goToPage
     */
    public HabitantResponse getHabitants(String command, @Nullable String filter, @Nullable String goToPage) {
        HabitantResponse habitantRes = new HabitantResponse();
        try {
            String url = getUrl(command, filter, goToPage);
            HttpRequest<?> req = HttpRequest.GET(url);
            if(goToPage != null){
                HttpResponse<Habitant> rsp = httpClient.toBlocking().exchange(req, Argument.of(Habitant.class));
                if (rsp.getStatus().equals(HttpStatus.OK)) {
                    Habitant habitant = rsp.body();
                    ArrayList<Habitant> habitantList = new ArrayList<Habitant>();
                    habitantList.add(habitant);
                    habitantRes.setResults(habitantList);
                }
            }else{
                HttpResponse<HabitantResponse> rsp = httpClient.toBlocking().exchange(req, Argument.of(HabitantResponse.class));
                if (rsp.getStatus().equals(HttpStatus.OK)) {
                    habitantRes = rsp.body();
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return habitantRes;
    }

    /**
     * Get the info of Starships of the films
     * @param command Is the action that we need to call to the API
     * @param filter Is the filter to search. For example; filter=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns StarshipResponse with the data that we need exactly depends on command and goToPage
     */
    public StarshipResponse getStarships(String command, @Nullable String filter, @Nullable String goToPage) {
        StarshipResponse startshipRes = new StarshipResponse();
        try {
            String url = getUrl(command, filter, goToPage);
            HttpRequest<?> req = HttpRequest.GET(url);
            if(goToPage != null){
                HttpResponse<Starship> rsp = httpClient.toBlocking().exchange(req, Argument.of(Starship.class));
                if (rsp.getStatus().equals(HttpStatus.OK)) {
                    Starship starShip = rsp.body();
                    ArrayList<Starship> starShipList = new ArrayList<Starship>();
                    starShipList.add(starShip);
                    startshipRes.setResults(starShipList);
                }
            }else{
            HttpResponse<StarshipResponse> rsp = httpClient.toBlocking().exchange(req, Argument.of(StarshipResponse.class));
            if (rsp.getStatus().equals(HttpStatus.OK)) {
                startshipRes = rsp.body();
            }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return startshipRes;
    }

    /**
     * Get the info of films
     * @param command Is the action that we need to call to the API
     * @param filter Is the filter to search. For example; filter=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns that build a json with the data that we need exactly depends on command and goToPage
     */
    public FilmResponse getFilms(String command, @Nullable String filter, @Nullable String goToPage) {
        try {
            String url = getUrl(command, filter, goToPage);
            HttpRequest<?> req = HttpRequest.GET(url);
            HttpResponse<FilmResponse> rsp = httpClient.toBlocking().exchange(req,Argument.of(FilmResponse.class) );

            if (rsp.getStatus().equals(HttpStatus.OK)) {
                FilmResponse releases = rsp.body();
                return releases;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }
    /**
     * Get the json of the result depends on command, filter or goToPage
     * @param command Is the action that we need to call to the API
     * @param filter Is the filter to search. For example; filter=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns Object with the Json Data
     */
    public Object GetStarWarsApiInfo(String command, @Nullable String filter, @Nullable String goToPage) {
        try {
            String url = getUrl(command, filter, goToPage);

            HttpRequest<?> req = HttpRequest.GET(url);
            HttpResponse<Object> rsp = httpClient.toBlocking().exchange(req,Argument.of(Object.class));

            if (rsp.getStatus().equals(HttpStatus.OK)) {
                Object releases = rsp.body();
                return releases;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    /**
     * Format the url to call the api depending on params
     * @param command Is the action that we need to call to the API
     * @param filter Is the filter to search. For example; filter=Luke
     * @param goToPage If is needed go to a specific page from the API
     * @return Returns a String
     */
    private String getUrl(String command,  @Nullable String filter,  @Nullable String goToPage) {
        String url = "https://swapi.dev/api/" + command + "/";
        if (filter != null) {
            if (!filter.isEmpty() & !filter.isBlank())
                url = url + "?search=" + filter;
        }
        if (goToPage != null) {
            if (!goToPage.isEmpty() & !goToPage.isBlank())
                url = goToPage;
        }
        return url;
    }
}
