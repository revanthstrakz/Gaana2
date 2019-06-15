package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

public class a {
    private static final SimpleDateFormat[] a = new SimpleDateFormat[]{new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US), new SimpleDateFormat("yyyy-MM", Locale.US), new SimpleDateFormat("yyyyMMddHHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMddHHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US), new SimpleDateFormat("yyyyMM", Locale.US), new SimpleDateFormat("yyyy", Locale.US)};
    private static String b = "a";

    @TargetApi(14)
    public static int a(Context context) {
        r3 = new String[2];
        int i = 0;
        r3[0] = BaseColumns._ID;
        r3[1] = "title";
        Cursor query = context.getContentResolver().query(Events.CONTENT_URI, r3, null, null, null);
        if (query != null && query.moveToLast()) {
            int columnIndex = query.getColumnIndex("title");
            int columnIndex2 = query.getColumnIndex(BaseColumns._ID);
            String string = query.getString(columnIndex);
            String string2 = query.getString(columnIndex2);
            if (string != null) {
                i = Integer.parseInt(string2);
            }
            query.close();
        }
        return i;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String a(String str) {
        String str2 = null;
        if (!(str == null || "".equals(str))) {
            Date parse;
            SimpleDateFormat[] simpleDateFormatArr = a;
            int i = 0;
            int length = simpleDateFormatArr.length;
            int i2 = 0;
            while (i2 < length) {
                try {
                    parse = simpleDateFormatArr[i2].parse(str);
                    break;
                } catch (ParseException unused) {
                    i2++;
                }
            }
            parse = null;
            if (parse != null) {
                DateFormat[] dateFormatArr = new DateFormat[]{new SimpleDateFormat("yyyyMMdd'T'HHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US)};
                while (i < 3) {
                    try {
                        str2 = dateFormatArr[i].format(Long.valueOf(parse.getTime()));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        i++;
                    }
                }
                return str2;
            }
        }
        return null;
    }

    public static GregorianCalendar b(String str) {
        SimpleDateFormat[] simpleDateFormatArr = a;
        int i = 0;
        int length = simpleDateFormatArr.length;
        while (i < length) {
            SimpleDateFormat simpleDateFormat = simpleDateFormatArr[i];
            try {
                Date parse = simpleDateFormat.parse(str);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(parse);
                new StringBuilder("Date format: ").append(simpleDateFormat.toPattern());
                return gregorianCalendar;
            } catch (ParseException unused) {
                new StringBuilder("Skipping format: ").append(simpleDateFormat.toPattern());
                i++;
            }
        }
        return null;
    }

    public static String a(JSONArray jSONArray, int i, int i2) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i3 = 0;
        while (i3 < jSONArray.length()) {
            try {
                int i4 = jSONArray.getInt(i3);
                if (i4 >= i && i4 <= i2) {
                    if (i4 != 0) {
                        stringBuilder.append(i4);
                        stringBuilder.append(",");
                    }
                }
                i3++;
            } catch (JSONException e) {
                new StringBuilder("Could not parse day ").append(e.getMessage());
                return null;
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        i = stringBuilder2.length();
        if (i == 0) {
            return null;
        }
        i--;
        if (stringBuilder2.charAt(i) == ',') {
            stringBuilder2 = stringBuilder2.substring(0, i);
        }
        return stringBuilder2;
    }

    public static String a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                stringBuilder.append(jSONArray.get(i));
                stringBuilder.append(",");
                i++;
            } catch (JSONException e) {
                new StringBuilder("Could not parse day object ").append(e.toString());
                return null;
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        int length = stringBuilder2.length();
        if (length == 0) {
            return null;
        }
        length--;
        if (stringBuilder2.charAt(length) == ',') {
            stringBuilder2 = stringBuilder2.substring(0, length);
        }
        return stringBuilder2;
    }
}
