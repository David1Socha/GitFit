package com.raik383h_group_6.healthtracmobile.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class FormatUtils {

    private FormatUtils() {
        //unused
    }

    public static double parseDouble(String doubleStr) {
        double d = Double.parseDouble(doubleStr);
        return d;
    }

    public static Date parseDate(String dateStr) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        formatter.setLenient(false);
        Date d = null;
        try {
            d = formatter.parse(dateStr);
        } catch (ParseException ignored) {
        }
        return d;
    }

    public static String format(Date d) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        if (d == null) {
            return null;
        }
        return formatter.format(d);
    }

    public static String format(Double d) {
        return String.valueOf(d);
    }

}
