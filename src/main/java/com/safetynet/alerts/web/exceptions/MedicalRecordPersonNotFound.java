package com.safetynet.alerts.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalRecordPersonNotFound extends RuntimeException{
    public MedicalRecordPersonNotFound(String s) {
        super("Person not found : " + s + " MedicalRecord not added");
    }

}
