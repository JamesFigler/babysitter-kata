package com.jamesfigler.kata.babysitter;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Babysitter {

    public int calculate(String startTime, String endTime) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        long difference = formatter.parse(endTime).getTime() - formatter.parse(startTime).getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(difference);
        return (int) (hours * 12);
    }
}
