package com.safetynet.alerts.web.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFound extends RuntimeException {

        public PersonNotFound(String s) {
            super("Personne not found" + s);
        }
}
