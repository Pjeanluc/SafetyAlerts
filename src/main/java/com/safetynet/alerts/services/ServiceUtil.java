package com.safetynet.alerts.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class ServiceUtil {
    
    public int calculateAge(Date date) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(date));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        return (d2 - d1) / 10000;
    }
    
    public boolean isMinorPeopleFromAge(Integer age) throws ParseException {
        if (age < 18) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMinorPeopleFromDate(Date date) throws ParseException {
        int age = calculateAge(date);
        boolean isMinor = false;
        if (isMinorPeopleFromAge(age)) isMinor = true;
        else isMinor = false;
        return isMinor;
    }

}
