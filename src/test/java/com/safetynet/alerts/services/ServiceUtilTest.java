package com.safetynet.alerts.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
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
    void isMajorByAgeJustMajorTest() throws ParseException {

        assertThat(serviceUtil.isMinorPeopleFromAge(18)).isFalse();

    }

    @Test
    void isMajorByAgeTest() throws ParseException {

        assertThat(serviceUtil.isMinorPeopleFromAge(19)).isFalse();

    }

    @Test
    void isMinorByAgeTest() throws ParseException {

        assertThat(serviceUtil.isMinorPeopleFromAge(17)).isTrue();

    }

    @Test
    void isMinorForNewBornByAgeTest() throws ParseException {

        assertThat(serviceUtil.isMinorPeopleFromAge(0)).isTrue();

    }

    @Test
    void isMajorByDateJustMajorTest() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = addYears(d, -18);

        assertThat(serviceUtil.isMinorPeopleFromDate(birthDay)).isFalse();

    }

    @Test
    void isMajorByDateTest() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = addYears(d, -20);

        assertThat(serviceUtil.isMinorPeopleFromDate(birthDay)).isFalse();

    }

    @Test
    void isMinorByDateTest() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Date birthDay = addYears(d, -17);

        assertThat(serviceUtil.isMinorPeopleFromDate(birthDay)).isTrue();

    }

    @Test
    void isMinorForNewBornByDateTest() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date birthDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        assertThat(serviceUtil.isMinorPeopleFromDate(birthDay)).isTrue();

    }

    public static Date addYears(Date date, int nb) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);        
        cal.add(Calendar.YEAR,nb);
        return cal.getTime();
    }

}
