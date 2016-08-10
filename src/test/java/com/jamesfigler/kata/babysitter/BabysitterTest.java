package com.jamesfigler.kata.babysitter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BabysitterTest {

    @Test
    public void shouldGetPaidTwelveDollarsPerHourFromStartToBedtime() {
        Babysitter underTest = new Babysitter();
        int result = underTest.calculate("6:00 PM", "7:00 PM");
        assertThat(result, is(12));
    }
}
