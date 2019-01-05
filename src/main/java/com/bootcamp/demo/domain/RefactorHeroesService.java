package com.bootcamp.demo.domain;

import com.bootcamp.demo.domain.model.Hero;
import com.bootcamp.demo.infrastructure.services.HeroesAPI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Qualifier("refacto")
public class RefactorHeroesService implements HeroesService {
    private HeroesAPI heroesAPI;

    public RefactorHeroesService(HeroesAPI heroesAPI) {
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
                .parallel()
                .map(this::getHero)
                .collect(Collectors.toList());
    }

    @Override
    public List<Hero> getGoodHeroes() {

        return getHeroes().stream()
                .filter(this::isGood)
                .collect(Collectors.toList());
    }

    private boolean isGood(Hero hero) {
        return !hero.getBiography().getAlignment().equals("bad");
    }
}
