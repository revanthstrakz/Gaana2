package com.helpshift.common.util;

import com.helpshift.common.platform.p;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class a {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static {
        a.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static Date a(p pVar) {
        float a = pVar.q().a();
        long currentTimeMillis = System.currentTimeMillis();
        if (a != 0.0f) {
            currentTimeMillis = (long) (((float) currentTimeMillis) + (a * 1000.0f));
        }
        return new Date(currentTimeMillis);
    }

    public static String b(p pVar) {
        return a.format(a(pVar));
    }

    public static String a(SimpleDateFormat simpleDateFormat, String str, int i) {
        try {
            Date parse = simpleDateFormat.parse(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            return simpleDateFormat.format(new Date(instance.getTimeInMillis() + ((long) i)));
        } catch (ParseException unused) {
            return str;
        }
    }

    public static float a(String str) {
        return (float) (((double) (new Date((long) Double.valueOf(Double.parseDouble(str) * 1000.0d).doubleValue()).getTime() / 1000)) - Double.parseDouble(new DecimalFormat("0.000", new DecimalFormatSymbols(Locale.US)).format(((double) System.currentTimeMillis()) / 1000.0d)));
    }
}
