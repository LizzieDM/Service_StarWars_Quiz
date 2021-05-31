package com.starwars;

import com.starwars.managers.StarWarsManager;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@MicronautTest
class MicronautStarWarsTest {

    @Inject
    EmbeddedApplication<?> application;
    @Inject private StarWarsManager service;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    public void testGetPlanetsHasResult() {
        var planets =service.GetStarWarsInfoFilter("planets", null, null);
        Assertions.assertTrue(planets.contains("\"count\":60"));
    }
    @Test
    public void testGetCharactersHasResult() {
        var people =service.GetStarWarsInfoFilter("people", null, null);
        Assertions.assertTrue(people.contains("\"count\":82"));
    }
    @Test
    public void testGetStarShipsHasResult() {
        var starShips =service.GetStarWarsInfoFilter("starships", null, null);
        Assertions.assertTrue(starShips.contains("\"count\":36"));
    }

    @Test
    public void testGetLukeSkywalkerHasResult() {
        var peopleLuke =service.GetStarWarsInfoFilter("people", "Luke", null);
        Assertions.assertTrue(peopleLuke.contains("\"name\":\"Luke"));
    }

    @Test
    public void testGetCoruscantHasResult() {
        var planetCoruscant =service.GetStarWarsInfoFilter("planets", "Coruscant", null);
        Assertions.assertTrue(planetCoruscant.contains("\"name\":\"Coruscant"));
    }

    @Test
    public void testGetCR90HasResult() {
        var starShipCR90 =service.GetStarWarsInfoFilter("starships", "CR90", null);
        Assertions.assertTrue(starShipCR90.contains("\"name\":\"CR90"));
    }
    



}
