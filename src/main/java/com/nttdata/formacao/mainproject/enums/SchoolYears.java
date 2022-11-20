package com.nttdata.formacao.mainproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SchoolYears {

    FIRST ("1st"),
    SECOND ("2nd"),
    THIRD("3rd"),
    FOURTH("4th"),
    FIFTH("5th"),
    SIXTH("6th"),
    SEVENTH("7th"),
    EIGHT("8th"),
    NINTH("9th"),
    TENTH("10th"),
    ELEVENTH("11th"),
    TWELFTH("12th");

    private String description;
}
