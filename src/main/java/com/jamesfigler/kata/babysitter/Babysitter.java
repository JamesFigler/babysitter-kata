package com.jamesfigler.kata.babysitter;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Babysitter {

    public int calculate(String startTime, String endTime, String bedTime) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");

        long start = formatter.parse(startTime).getTime();
        long end = formatter.parse(endTime).getTime();
        long bed = formatter.parse(bedTime).getTime();

        int wage = 12;

        if(start > bed) {
            wage = 8;
        }

        long difference = end - start;
        long hours = TimeUnit.MILLISECONDS.toHours(difference);
        return (int) (hours * wage);
    }
}
