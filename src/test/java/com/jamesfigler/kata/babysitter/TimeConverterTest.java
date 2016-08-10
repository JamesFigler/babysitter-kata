package com.jamesfigler.kata.babysitter;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TimeConverterTest {

    private TimeConverter underTest;

    @Before
    public void setUp() {
        underTest = new TimeConverter();
    }

    @Test
    public void shouldConvertFromStringToMilliseconds() throws Exception {
        int result = underTest.toMilliseconds("8:00 PM");
        assertThat(result, is(20 * 60 * 60 * 1000));
    }

    @Test
    public void shouldConvertAnotherStringToMilliseconds() throws Exception {
        int result = underTest.toMilliseconds("10:00 PM");
        assertThat(result, is(22 * 60 * 60 * 1000));
    }

    @Test
    public void shouldRollIntoNextDayIfTimeIsMorning() throws Exception {
        int result = underTest.toMilliseconds("2:00 AM");
        assertThat(result, is((2 + 24) * 60 * 60 * 1000));
    }
}