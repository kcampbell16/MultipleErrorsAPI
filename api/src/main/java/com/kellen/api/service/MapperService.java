package com.kellen.api.service;

import com.kellen.api.dto.Car;
import com.kellen.api.dto.Person;
import com.kellen.api.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Validator;

@Service
public class MapperService {

    @Autowired private Validator validator;

    public void validate(RequestDTO requestDTO, BindingResult bindingResult) {
        Person person = requestDTO.getPerson();
        Car car = requestDTO.getCar();

        if (person.getFirstName().equals("Kellen")) {
            // add to javax errors
            bindingResult.addError(new FieldError(
                    "person",
                    "firstName",
                    "'Kellen' is not allowed as a first name")
            );
        }
    }
}
