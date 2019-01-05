package com.bootcamp.demo.application.controller;

import com.bootcamp.demo.domain.HeroesService;
import com.bootcamp.demo.domain.model.Hero;
import com.github.rawls238.scientist4j.Experiment;
import io.dropwizard.metrics5.MetricRegistry;
import io.dropwizard.metrics5.Slf4jReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class HereosController {

    private HeroesService legacyHeroesService;
    private HeroesService refactoHeroesService;

    public HereosController(@Qualifier("legacy") HeroesService legacyHeroesService, @Qualifier("refacto") HeroesService refactoHeroesService) {
        this.legacyHeroesService = legacyHeroesService;
        this.refactoHeroesService = refactoHeroesService;
    }

    @GetMapping("/hero/{id}")
    public Hero getHero(@PathVariable(value = "id") int id) {

        return legacyHeroesService.getHero(id);
    }

    @GetMapping("/heroes/")
    public List<Hero> getHeroes() throws Exception {


        Experiment<List<Hero>> experiment = new Experiment<>("getHeroes");
        List<Hero> heroes = experiment.runAsync(() -> legacyHeroesService.getHeroes(), () -> refactoHeroesService.getHeroes());

        MetricRegistry metrics = experiment.getMetrics(null);

        Slf4jReporter reporter = getSlf4jReporter(metrics);
        reporter.report();

        return heroes;
    }

    @GetMapping("/heroes/good")
    public List<Hero> getGoodHeroes() throws Exception {

        Experiment<List<Hero>> experiment = new Experiment<>("goodHeoes", false);

        List<Hero> heroes = experiment.runAsync(() -> legacyHeroesService.getGoodHeroes(), () -> refactoHeroesService.getGoodHeroes());

        MetricRegistry metrics = experiment.getMetrics(null);
        Slf4jReporter reporter = getSlf4jReporter(metrics);
        reporter.report();

        return heroes;
    }

    private Slf4jReporter getSlf4jReporter(MetricRegistry metrics) {
        return Slf4jReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
    }

}
