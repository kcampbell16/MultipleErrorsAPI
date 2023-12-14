package com.kellen.api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Data
@Builder
@ResponseBody
public class Response {
    private List<String> result;
}
