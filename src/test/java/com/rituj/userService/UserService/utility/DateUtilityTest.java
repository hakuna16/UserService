package com.rituj.userService.UserService.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.Test;

public class DateUtilityTest {

	@Test
    public void testGetDateObj() {
        LocalDate date = DateUtility.getDateObj(LocalDate.now().toString());
        assertEquals(date, LocalDate.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDateObjWhenDateIsNull() {
        DateUtility.getDateObj(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDateObjWhenDateIsEmpty() {
        DateUtility.getDateObj("");
    }

    @Test(expected = DateTimeParseException.class)
    public void testGetDateObjWhenDateIsNotProper() {
        DateUtility.getDateObj("2018-05");
    }

    @Test
    public void shouldFormatDateInGivenFormat() {
        String date = DateUtility.dateInString();
        assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{2,4}\\+\\d{2}:\\d{2}"));
    }
}
