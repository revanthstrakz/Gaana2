package com.google.ads.interactivemedia.v3.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class cg {
    private static final Pattern c = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public final int a;
    public final int b;

    public static cg a(String str, String str2) {
        cg cgVar = null;
        if (!"iTunSMPB".equals(str)) {
            return null;
        }
        Matcher matcher = c.matcher(str2);
        if (matcher.find()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1), 16);
                int parseInt2 = Integer.parseInt(matcher.group(2), 16);
                if (parseInt != 0 || parseInt2 != 0) {
                    cgVar = new cg(parseInt, parseInt2);
                }
                return cgVar;
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static cg a(int i) {
        int i2 = i >> 12;
        i &= 4095;
        return (i2 == 0 && i == 0) ? null : new cg(i2, i);
    }

    private cg(int i, int i2) {
        this.a = i;
        this.b = i2;
    }
}
