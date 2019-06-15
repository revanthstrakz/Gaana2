package com.google.api.client.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DateTime implements Serializable {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final Pattern RFC3339_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");
    private static final long serialVersionUID = 1;
    private final boolean dateOnly;
    private final int tzShift;
    private final long value;

    public DateTime(Date date, TimeZone timeZone) {
        this(false, date.getTime(), timeZone == null ? null : Integer.valueOf(timeZone.getOffset(date.getTime()) / 60000));
    }

    public DateTime(long j) {
        this(false, j, null);
    }

    public DateTime(Date date) {
        this(date.getTime());
    }

    public DateTime(long j, int i) {
        this(false, j, Integer.valueOf(i));
    }

    public DateTime(boolean z, long j, Integer num) {
        this.dateOnly = z;
        this.value = j;
        int offset = z ? 0 : num == null ? TimeZone.getDefault().getOffset(j) / 60000 : num.intValue();
        this.tzShift = offset;
    }

    public DateTime(String str) {
        DateTime parseRfc3339 = parseRfc3339(str);
        this.dateOnly = parseRfc3339.dateOnly;
        this.value = parseRfc3339.value;
        this.tzShift = parseRfc3339.tzShift;
    }

    public long getValue() {
        return this.value;
    }

    public boolean isDateOnly() {
        return this.dateOnly;
    }

    public int getTimeZoneShift() {
        return this.tzShift;
    }

    public String toStringRfc3339() {
        StringBuilder stringBuilder = new StringBuilder();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.setTimeInMillis(this.value + (((long) this.tzShift) * 60000));
        appendInt(stringBuilder, gregorianCalendar.get(1), 4);
        stringBuilder.append('-');
        appendInt(stringBuilder, gregorianCalendar.get(2) + 1, 2);
        stringBuilder.append('-');
        appendInt(stringBuilder, gregorianCalendar.get(5), 2);
        if (!this.dateOnly) {
            stringBuilder.append('T');
            appendInt(stringBuilder, gregorianCalendar.get(11), 2);
            stringBuilder.append(':');
            appendInt(stringBuilder, gregorianCalendar.get(12), 2);
            stringBuilder.append(':');
            appendInt(stringBuilder, gregorianCalendar.get(13), 2);
            if (gregorianCalendar.isSet(14)) {
                stringBuilder.append('.');
                appendInt(stringBuilder, gregorianCalendar.get(14), 3);
            }
            if (this.tzShift == 0) {
                stringBuilder.append('Z');
            } else {
                int i = this.tzShift;
                if (this.tzShift > 0) {
                    stringBuilder.append('+');
                } else {
                    stringBuilder.append('-');
                    i = -i;
                }
                int i2 = i / 60;
                i %= 60;
                appendInt(stringBuilder, i2, 2);
                stringBuilder.append(':');
                appendInt(stringBuilder, i, 2);
            }
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return toStringRfc3339();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DateTime)) {
            return false;
        }
        DateTime dateTime = (DateTime) obj;
        if (!(this.dateOnly == dateTime.dateOnly && this.value == dateTime.value && this.tzShift == dateTime.tzShift)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long[] jArr = new long[3];
        jArr[0] = this.value;
        jArr[1] = this.dateOnly ? 1 : 0;
        jArr[2] = (long) this.tzShift;
        return Arrays.hashCode(jArr);
    }

    public static DateTime parseRfc3339(String str) throws NumberFormatException {
        Matcher matcher = RFC3339_PATTERN.matcher(str);
        String valueOf;
        String valueOf2;
        if (matcher.matches()) {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2)) - 1;
            int parseInt3 = Integer.parseInt(matcher.group(3));
            int i = matcher.group(4) != null ? 1 : 0;
            String group = matcher.group(9);
            int i2 = group != null ? 1 : 0;
            Integer num = null;
            if (i2 == 0 || i != 0) {
                int parseInt4;
                int i3;
                int parseInt5;
                int i4;
                int i5;
                if (i != 0) {
                    int parseInt6 = Integer.parseInt(matcher.group(5));
                    int parseInt7 = Integer.parseInt(matcher.group(6));
                    parseInt4 = Integer.parseInt(matcher.group(7));
                    if (matcher.group(8) != null) {
                        i3 = i;
                        parseInt5 = (int) (((double) ((float) Integer.parseInt(matcher.group(8).substring(1)))) / Math.pow(10.0d, (double) (matcher.group(8).substring(1).length() - 3)));
                        i4 = parseInt7;
                        i5 = parseInt4;
                    } else {
                        i3 = i;
                        i4 = parseInt7;
                        i5 = parseInt4;
                        parseInt5 = 0;
                    }
                    parseInt4 = parseInt6;
                } else {
                    i3 = i;
                    parseInt4 = 0;
                    i4 = 0;
                    i5 = 0;
                    parseInt5 = 0;
                }
                Calendar gregorianCalendar = new GregorianCalendar(GMT);
                gregorianCalendar.set(parseInt, parseInt2, parseInt3, parseInt4, i4, i5);
                gregorianCalendar.set(14, parseInt5);
                long timeInMillis = gregorianCalendar.getTimeInMillis();
                if (!(i3 == 0 || i2 == 0)) {
                    if (Character.toUpperCase(group.charAt(0)) == 'Z') {
                        parseInt5 = 0;
                    } else {
                        int parseInt8 = (Integer.parseInt(matcher.group(11)) * 60) + Integer.parseInt(matcher.group(12));
                        parseInt5 = matcher.group(10).charAt(0) == '-' ? -parseInt8 : parseInt8;
                        timeInMillis -= ((long) parseInt5) * 60000;
                    }
                    num = Integer.valueOf(parseInt5);
                }
                return new DateTime(i3 ^ 1, timeInMillis, num);
            }
            valueOf = String.valueOf("Invalid date/time format, cannot specify time zone shift without specifying time: ");
            valueOf2 = String.valueOf(str);
            throw new NumberFormatException(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        }
        valueOf = "Invalid date/time format: ";
        valueOf2 = String.valueOf(str);
        throw new NumberFormatException(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    private static void appendInt(StringBuilder stringBuilder, int i, int i2) {
        if (i < 0) {
            stringBuilder.append('-');
            i = -i;
        }
        int i3 = i2;
        i2 = i;
        while (i2 > 0) {
            i2 /= 10;
            i3--;
        }
        for (i2 = 0; i2 < i3; i2++) {
            stringBuilder.append('0');
        }
        if (i != 0) {
            stringBuilder.append(i);
        }
    }
}
