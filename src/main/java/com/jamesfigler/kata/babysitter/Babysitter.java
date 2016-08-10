package com.jamesfigler.kata.babysitter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Babysitter {

    private TimeConverter timeConverter;
    private int start, end, bed, midnight;

    public Babysitter() {
        timeConverter = new TimeConverter();
    }

    public int calculate(String startTime, String endTime, String bedTime) throws Exception {
        start = timeConverter.toMilliseconds(startTime);
        end = timeConverter.toMilliseconds(endTime);
        bed = timeConverter.toMilliseconds(bedTime);
        midnight = timeConverter.toMilliseconds("12:00 AM");

        return (int) (
                12 * hoursBeforeBedtime() +
                8 * hoursBeforeMidnight() +
                16 * hoursAfterMidnight()
        );
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

        if((start > bed) && (end > midnight))
            return MILLISECONDS.toHours(midnight - start);

        if((start < bed) && (end > bed) && (end < midnight))
            return MILLISECONDS.toHours(end - bed);

        if((start > bed) && (end < midnight))
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
