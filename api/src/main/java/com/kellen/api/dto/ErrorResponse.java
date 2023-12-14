package com.kellen.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    @NotEmpty private List<String> messages;
}
