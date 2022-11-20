package com.nttdata.formacao.mainproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("M","Male"),
    FEMALE("F","Female");

    private String shortDescription;

    private String description;
}
