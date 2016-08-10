package com.jamesfigler.kata.babysitter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeConverter {
    public int toMilliseconds(String time) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        long output = formatter.parse(time).getTime();

        if(time.endsWith("AM")) {
            output += (24 * 60 * 60 * 1000);
        }

        return (int) output;
    }
}
