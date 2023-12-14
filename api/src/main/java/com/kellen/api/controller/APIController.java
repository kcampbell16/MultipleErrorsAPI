package com.kellen.api.controller;

import com.kellen.api.dto.ErrorResponse;
import com.kellen.api.dto.RequestDTO;
import com.kellen.api.dto.Response;
import com.kellen.api.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class APIController {

    @Autowired private Validator validator;
    @Autowired private MapperService mapperService;

    @RequestMapping(
            value = "/receive",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> receive(@RequestBody RequestDTO requestDTO, BindingResult bindingResult) {
        validator.validate(requestDTO, bindingResult);
        mapperService.validate(requestDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());

            Response errorResponse = Response.builder().result(errorMessages).build();
            return ResponseEntity.badRequest().body(errorResponse);
        }

        Response response = Response.builder()
                .result(Collections.singletonList("Successful!"))
                .build();
        return ResponseEntity.ok().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleSchemaViolationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorResponse res = new ErrorResponse(errorMessages);
        return ResponseEntity.badRequest().body(res);
    }
}
