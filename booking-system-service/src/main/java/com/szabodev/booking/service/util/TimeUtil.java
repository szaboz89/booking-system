package com.szabodev.booking.service.util;

public class TimeUtil {

    private static String minutesToString(int minutes) {
        return String.format("%02d:%02d", minutes / 60, minutes % 60);
    }

    private static int timeToMinutes(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3));
        return hour * 60 + minutes;
    }

}
