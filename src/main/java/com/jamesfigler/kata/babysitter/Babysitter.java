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

        if(bed > end) {
            return (int) (12 * MILLISECONDS.toHours(end - start));
        }

        else if((start > bed) && (start < midnight)) {
            return (int) (8 * MILLISECONDS.toHours(end - start));
        }

        else if(start >= midnight) {
            return (int) (16 * MILLISECONDS.toHours(end - start));
        }

        int totalPay = 0;
        totalPay += 12 * hoursBeforeBedtime();
        totalPay += 8 * hoursBeforeMidnight();

        return totalPay;
    }

    private long hoursBeforeBedtime() {
        return MILLISECONDS.toHours(bed - start);
    }

    private long hoursBeforeMidnight() {
        return MILLISECONDS.toHours(end - bed);
    }

    private long hoursAfterMidnight() {
        throw new UnsupportedOperationException();
    }
}
