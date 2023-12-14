package com.kellen.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class Car {

    @NotNull
    private int year;

    @NotNull
    @NotBlank
    private String make;

    @NotNull
    @NotBlank
    private String model;
}
