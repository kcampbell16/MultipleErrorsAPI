package com.kellen.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Jacksonized
public class RequestDTO {

    @Valid @NotNull private Person person;

    @Valid @NotNull private Car car;
}
