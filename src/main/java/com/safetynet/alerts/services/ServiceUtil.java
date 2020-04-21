package com.safetynet.alerts.services;

import java.text.DateFormat;
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

}
