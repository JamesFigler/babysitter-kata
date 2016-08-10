package com.jamesfigler.kata.babysitter;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BabysitterTest {

    private Babysitter underTest;
    private String bedTime = "8:00 PM";

    @Before
    public void setUp() {
        underTest = new Babysitter();
    }

    @Test
    public void shouldGetPaidTwelveDollarsPerHourFromStartToBedtime() throws Exception {
        int result = underTest.calculate("6:00 PM", "7:00 PM", bedTime);
        assertThat(result, is(12));
    }

    @Test
    public void shouldGetPaidTwentyFourDollarsForTwoHoursBeforeBedtime() throws Exception {
        int result = underTest.calculate("5:00 PM", "7:00 PM", bedTime);
        assertThat(result, is(24));
    }

    @Test
    public void shouldOnlyGetPaidForFullHoursWorked() throws Exception {
        int result = underTest.calculate("5:30 PM", "6:45 PM", bedTime);
        assertThat(result, is(12));
    }

    @Test
    public void shouldGetPaidEightDollarsPerHourAfterBedtimeUntilMidnight() throws Exception {
        int result = underTest.calculate("9:00 PM", "10:00 PM", bedTime);
        assertThat(result, is(8));
    }
}
