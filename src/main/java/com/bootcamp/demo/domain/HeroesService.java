package com.bootcamp.demo.domain;

import com.bootcamp.demo.domain.model.Hero;

import java.util.List;

public interface HeroesService {
    Hero getHero(int id);

    List<Hero> getHeroes();

    List<Hero> getGoodHeroes();
}
