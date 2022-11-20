package com.nttdata.formacao.mainproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassResult {

    APPROVED("Approved"),
    REFUSED("Refused");

    private String description;
}
