package com.jamesfigler.kata.babysitter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Babysitter {

    private int start, end, bed, midnight;
    private final int startLimit = 17 * 60 * 60 * 1000;
    private final int endLimit = (24 + 4) * 60 * 60 * 1000;

    public int calculate(String startTime, String endTime, String bedTime) throws Exception {
        TimeConverter timeConverter = new TimeConverter();

        start = timeConverter.toMilliseconds(startTime);
        end = timeConverter.toMilliseconds(endTime);
        bed = timeConverter.toMilliseconds(bedTime);
        midnight = timeConverter.toMilliseconds("12:00 AM");

        checkAllowedTimes();

        return (int) (
                12 * hoursBeforeBedtime() +
                8 * hoursBeforeMidnight() +
                16 * hoursAfterMidnight()
        );
    }

    private void checkAllowedTimes() throws TimeNotAllowedException {
        if(start < startLimit)
            throw new TimeNotAllowedException("Starting time cannot be earlier than 5:00 PM");

        if(end > endLimit)
            throw new TimeNotAllowedException("Ending time cannot be after 4:00 AM");

        if(start > end)
            throw new TimeNotAllowedException("Valid working hours are between 5:00 PM and 4:00 AM");
    }

    private long hoursBeforeBedtime() {
        if((end > bed) && (start < bed))
            return MILLISECONDS.toHours(bed - start);

        if(end <= bed) {
            return MILLISECONDS.toHours(end - start);
        }

        return 0;
    }

    private long hoursBeforeMidnight() {
        if((start < bed) && (end > midnight))
            return MILLISECONDS.toHours(midnight - bed);

        if((start >= bed) && (end > midnight))
            return MILLISECONDS.toHours(midnight - start);

        if((start < bed) && (end > bed) && (end <= midnight))
            return MILLISECONDS.toHours(end - bed);

        if((start >= bed) && (end <= midnight))
            return MILLISECONDS.toHours(end - start);

        return 0;
    }

    private long hoursAfterMidnight() {
        if(start >= midnight)
            return MILLISECONDS.toHours(end - start);

        if(start < midnight && end > midnight)
            return MILLISECONDS.toHours(end - midnight);

        return 0;
    }
}
