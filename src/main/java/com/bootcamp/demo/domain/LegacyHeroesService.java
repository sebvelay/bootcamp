package com.bootcamp.demo.domain;

import com.bootcamp.demo.domain.model.Hero;
import com.bootcamp.demo.infrastructure.services.HeroesAPI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Qualifier("legacy")
public class LegacyHeroesService implements HeroesService {

    private HeroesAPI heroesAPI;

    public LegacyHeroesService(HeroesAPI heroesAPI) {
        this.heroesAPI = heroesAPI;
    }

    @Override
    public Hero getHero(int id) {
        return heroesAPI.getHero(id);
    }

    @Override
    public List<Hero> getHeroes() {

        return Stream.iterate(89, x -> x + 1)
                .limit(10)
                .map(this::getHero)
                .collect(Collectors.toList());
    }

    @Override
    public List<Hero> getGoodHeroes() {

        return getHeroes().stream()
                .filter(h -> h.getBiography().getAlignment().equals("good"))
                .collect(Collectors.toList());
    }

}
