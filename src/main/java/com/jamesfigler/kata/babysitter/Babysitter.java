package com.jamesfigler.kata.babysitter;

import java.text.SimpleDateFormat;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Babysitter {

    public int calculate(String startTime, String endTime, String bedTime) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");

        int totalPay = 0;
        int dayInMilliseconds = 24 * 60 * 60 * 1000;

        long start = formatter.parse(startTime).getTime();
        long end = formatter.parse(endTime).getTime();
        long bed = formatter.parse(bedTime).getTime();
        long midnight = formatter.parse("12:00 AM").getTime() + dayInMilliseconds;

        if(startTime.endsWith("AM")) {
            // advance to next day if AM
            start += dayInMilliseconds;
        }
        if(endTime.endsWith("AM")) {
            // advance to next day if AM
            end += dayInMilliseconds;
        }

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

        totalPay += 12 * hoursBeforeBedtime;
        totalPay += 8 * hoursAfterBedtime;

        return totalPay;
    }
}
