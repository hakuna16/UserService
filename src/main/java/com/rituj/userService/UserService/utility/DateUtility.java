package com.rituj.userService.UserService.utility;

import static com.rituj.userService.UserService.common.UserConstants.OUTPUT_DATE_FORMAT;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.rituj.userService.UserService.common.UserConstants;

@Component
public class DateUtility {

	/**
	 * Converts the given string date representation to {@link LocalDate}. Uses
	 * {@link UserConstants#OUTPUT_DATE_FORMAT} as the date format while parsing
	 * the date.
	 *
	 * @param date
	 *            string representation of date to be parsed.
	 * @return {@link LocalDate} object of the given string date.
	 * @throws IllegalArgumentException
	 *             if date is empty or null.
	 */
	public static LocalDate getDateObj(final String date) {
		Assert.hasText(date, "Date is not provided");

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
		return LocalDate.parse(date, dateTimeFormatter);
	}

	/**
	 * Get system date as string in the format
	 * {@link DateTimeFormatter#ISO_OFFSET_DATE_TIME}. Gets the current date &
	 * time from {@link ZonedDateTime#now} and formats in
	 * {@link DateTimeFormatter#ISO_OFFSET_DATE_TIME}.
	 * 
	 * @return String representation of the system Date.
	 */
	public static String dateInString() {
		return ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
}
