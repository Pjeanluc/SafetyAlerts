package com.safetynet.alerts.web.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalRecordNotFound extends RuntimeException {

    public MedicalRecordNotFound(String s) {
        super("Medical record" + s);
    }

}
