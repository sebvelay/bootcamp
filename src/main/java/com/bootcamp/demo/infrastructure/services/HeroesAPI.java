package com.bootcamp.demo.infrastructure.services;

import com.bootcamp.demo.domain.model.Hero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HeroesAPI {

    @Value("${apikey}")
    private String API_KEY;
    private String BASE_URL = "https://superheroapi.com/api/";

    public Hero getHero(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Hero hero = restTemplate.getForObject(BASE_URL + API_KEY + "/" + id, Hero.class);

        log.info("Hero : " + hero);

        return hero;
    }
}
