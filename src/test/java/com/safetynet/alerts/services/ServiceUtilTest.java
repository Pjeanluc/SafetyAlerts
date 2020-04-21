package com.safetynet.alerts.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class ServiceUtilTest {
    
    @Autowired
    ServiceUtil serviceUtil;
    
    @Test
    void calculAgeJustMajorTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = addYears(d, -18);

        assertThat(serviceUtil.calculateAge(birthDay)).isEqualTo(18);
    }
    
    @Test
    void calculAgeMinorTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = addYears(d, -17);

        assertThat(serviceUtil.calculateAge(birthDay)).isEqualTo(17);
    }
    
    @Test
    void calculAgeJustBornTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = d;

        assertThat(serviceUtil.calculateAge(birthDay)).isEqualTo(0);
    }
    
    public static Date addYears(Date date, int nb) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);        
        cal.add(Calendar.YEAR,nb);
        return cal.getTime();
    }

}
