package com.safetynet.alerts.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonBadParameter extends RuntimeException {
    public PersonBadParameter (String s){
        super(s);
    }

}
