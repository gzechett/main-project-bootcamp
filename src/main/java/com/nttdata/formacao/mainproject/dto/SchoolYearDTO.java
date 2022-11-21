package com.nttdata.formacao.mainproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolYearDTO {

    private String schoolYear;

    private int age;

    private String schoolStage;
}
