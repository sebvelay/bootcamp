package com.bootcamp.demo.domain.model;

import lombok.Data;

@Data
public class Hero {

    private String id;
    private String name;
    private Biography biography;
}
