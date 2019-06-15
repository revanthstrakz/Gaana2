package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public final class zzeg {
    private static final String[] zzaav = new String[]{"Z", "+hh", "+hhmm", "+hh:mm"};
    private static final String zzaaw;
    private static final zzdw zzbf = new zzdw("MetadataUtils");

    public static void zza(List<WebImage> list, JSONArray jSONArray) {
        try {
            list.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    list.add(new WebImage(jSONArray.getJSONObject(i)));
                } catch (IllegalArgumentException unused) {
                }
            }
        } catch (JSONException unused2) {
        }
    }

    public static JSONArray zze(List<WebImage> list) {
        if (list == null && list.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (WebImage toJson : list) {
            jSONArray.put(toJson.toJson());
        }
        return jSONArray;
    }

    public static String zza(Calendar calendar) {
        if (calendar == null) {
            zzbf.d("Calendar object cannot be null", new Object[0]);
            return null;
        }
        String str = zzaaw;
        if (calendar.get(11) == 0 && calendar.get(12) == 0 && calendar.get(13) == 0) {
            str = "yyyyMMdd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String format = simpleDateFormat.format(calendar.getTime());
        if (format.endsWith("+0000")) {
            format = format.replace("+0000", zzaav[0]);
        }
        return format;
    }

    public static Calendar zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbf.d("Input string is empty or null", new Object[0]);
            return null;
        }
        String zzw = zzw(str);
        if (TextUtils.isEmpty(zzw)) {
            zzbf.d("Invalid date format", new Object[0]);
            return null;
        }
        str = zzx(str);
        String str2 = "yyyyMMdd";
        if (!TextUtils.isEmpty(str)) {
            zzw = String.valueOf(zzw);
            StringBuilder stringBuilder = new StringBuilder((String.valueOf(zzw).length() + 1) + String.valueOf(str).length());
            stringBuilder.append(zzw);
            stringBuilder.append("T");
            stringBuilder.append(str);
            zzw = stringBuilder.toString();
            if (str.length() == 6) {
                str2 = "yyyyMMdd'T'HHmmss";
            } else {
                str2 = zzaaw;
            }
        }
        Calendar instance = GregorianCalendar.getInstance();
        try {
            instance.setTime(new SimpleDateFormat(str2).parse(zzw));
            return instance;
        } catch (ParseException e) {
            zzbf.d("Error parsing string: %s", e.getMessage());
            return null;
        }
    }

    private static String zzw(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbf.d("Input string is empty or null", new Object[0]);
            return null;
        }
        try {
            return str.substring(0, 8);
        } catch (IndexOutOfBoundsException e) {
            zzbf.i("Error extracting the date: %s", e.getMessage());
            return null;
        }
    }

    private static String zzx(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbf.d("string is empty or null", new Object[0]);
            return null;
        }
        int indexOf = str.indexOf(84);
        int i = indexOf + 1;
        if (indexOf != 8) {
            zzbf.d("T delimeter is not found", new Object[0]);
            return null;
        }
        indexOf = 1;
        try {
            str = str.substring(i);
            if (str.length() == 6) {
                return str;
            }
            char charAt = str.charAt(6);
            if (charAt == '+' || charAt == '-') {
                i = str.length();
                if (!(i == zzaav[1].length() + 6 || i == zzaav[2].length() + 6 || i == 6 + zzaav[3].length())) {
                    indexOf = 0;
                }
                if (indexOf != 0) {
                    return str.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
                }
            } else if (charAt != 'Z' || str.length() != 6 + zzaav[0].length()) {
                return null;
            } else {
                str = String.valueOf(str.substring(0, str.length() - 1));
                String valueOf = String.valueOf("+0000");
                return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            zzbf.d("Error extracting the time substring: %s", e.getMessage());
            return null;
        }
    }

    static {
        String valueOf = String.valueOf("yyyyMMdd'T'HHmmss");
        String valueOf2 = String.valueOf(zzaav[0]);
        zzaaw = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }
}
