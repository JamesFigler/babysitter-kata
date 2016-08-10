package com.jamesfigler.kata.babysitter;

import java.text.SimpleDateFormat;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Babysitter {

    public int calculate(String startTime, String endTime, String bedTime) throws Exception {

        long start = convertTimeToMilliseconds(startTime);
        long end = convertTimeToMilliseconds(endTime);
        long bed = convertTimeToMilliseconds(bedTime);
        long midnight = convertTimeToMilliseconds("12:00 AM");

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

    private long convertTimeToMilliseconds(String time) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        long output = formatter.parse(time).getTime();

        // advance to next day if time is AM
        if(time.endsWith("AM")) {
            output += 24 * 60 * 60 * 1000;
        }

        return output;
    }
}
