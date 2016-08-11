package com.jamesfigler.kata.babysitter;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BabysitterTest {

    private final String bedTime = "8:00 PM";
    private final String midnight = "12:00 AM";

    private Babysitter underTest;

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
        assertThat(result, is(12 * 2));
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

    @Test
    public void shouldGetPaidDifferentWagesBeforeAndAfterBedtime() throws Exception {
        int result = underTest.calculate("7:00 PM", "10:00 PM", bedTime);
        assertThat(result, is(12 + (8 * 2)));
    }

    @Test
    public void shouldGetPaidSixteenDollarsPerHourAfterMidnight() throws Exception {
        int result = underTest.calculate(midnight, "1:00 AM", bedTime);
        assertThat(result, is(16));
    }

    @Test
    public void shouldGetPaidForWorkingAllHoursBeforeBedtime() throws Exception {
        int result = underTest.calculate("5:00 PM", bedTime, bedTime);
        assertThat(result, is(12 * 3));
    }

    @Test
    public void shouldGetPaidForWorkingAllHoursBetweenBedtimeAndMidnight() throws Exception {
        int result = underTest.calculate(bedTime, midnight, bedTime);
        assertThat(result, is(8 * 4));
    }

    @Test
    public void shouldGetPaidForWorkingAllHoursAfterMidnight() throws Exception {
        int result = underTest.calculate(midnight, "4:00 AM", bedTime);
        assertThat(result, is(16 * 4));
    }

    @Test
    public void shouldGetPaidForWorkingAllHoursOfTheNight() throws Exception {
        int result = underTest.calculate("5:00 PM", "4:00 AM", bedTime);
        assertThat(result, is((12 * 3) + (8 * 4) + (16 * 4)));
    }

    @Test(expected = TimeNotAllowedException.class)
    public void shouldNotAllowWorkingBeforeFivePM() throws Exception {
        underTest.calculate("4:00 PM", "6:00 PM", bedTime);
    }

    @Test(expected = TimeNotAllowedException.class)
    public void shouldNotAllowWorkingAfterFourAM() throws Exception {
        underTest.calculate("11:00 PM", "5:00 AM", bedTime);
    }

    @Test(expected = TimeNotAllowedException.class)
    public void shouldOnlyAllowWorkingBetweenFivePmAndFourAm() throws Exception {
        underTest.calculate("3:00 AM", "6:00 PM", bedTime);
    }
}
