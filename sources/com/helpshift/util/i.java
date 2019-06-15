package com.helpshift.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class i {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final DecimalFormat c = new DecimalFormat("0.000", new DecimalFormatSymbols(Locale.US));
    public static final SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS ");

    static {
        b.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
