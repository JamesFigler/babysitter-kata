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
    public void shouldConvertFromStringToMilliseconds() {
        int result = underTest.toMilliseconds("8:00 PM");
        assertThat(result, is(72000000)); // 20 hours since epoch
    }
}