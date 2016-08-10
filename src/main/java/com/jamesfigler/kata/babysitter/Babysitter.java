package com.jamesfigler.kata.babysitter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Babysitter {

    private TimeConverter timeConverter;

    public Babysitter() {
        timeConverter = new TimeConverter();
    }

    public int calculate(String startTime, String endTime, String bedTime) throws Exception {

        long start = timeConverter.toMilliseconds(startTime);
        long end = timeConverter.toMilliseconds(endTime);
        long bed = timeConverter.toMilliseconds(bedTime);
        long midnight = timeConverter.toMilliseconds("12:00 AM");

        if(bed > end) {
            return (int) (12 * MILLISECONDS.toHours(end - start));
        }

        else if((start > bed) && (start < midnight)) {
            return (int) (8 * MILLISECONDS.toHours(end - start));
        }

        else if(start >= midnight) {
            return (int) (16 * MILLISECONDS.toHours(end - start));
        }

        long hoursBeforeBedtime = MILLISECONDS.toHours(bed - start);
        long hoursAfterBedtime = MILLISECONDS.toHours(end - bed);

        int totalPay = 0;
        totalPay += 12 * hoursBeforeBedtime;
        totalPay += 8 * hoursAfterBedtime;

        return totalPay;
    }
}
