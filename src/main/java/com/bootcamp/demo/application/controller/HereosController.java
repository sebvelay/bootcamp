package com.bootcamp.demo.application.controller;

import com.bootcamp.demo.domain.HeroesService;
import com.bootcamp.demo.domain.model.Hero;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HereosController {

    private HeroesService heroesService;

    public HereosController(@Qualifier("legacy") HeroesService hereosService) {
        this.heroesService = hereosService;
    }

    @GetMapping("/hero/{id}")
    public Hero getHero(@PathVariable(value = "id") int id) {

        return heroesService.getHero(id);
    }

    @GetMapping("/heroes/")
    public List<Hero> getHeroes() {
        return heroesService.getHeroes();
    }

    @GetMapping("/heroes/good")
    public List<Hero> getGoodHeroes() {
        return heroesService.getGoodHeroes();
    }

}
