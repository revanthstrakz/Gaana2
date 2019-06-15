package com.google.android.gms.internal.ads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class zzap {
    public static zzc zzb(zzp zzp) {
        int i;
        long j;
        long j2;
        int i2;
        zzp zzp2 = zzp;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = zzp2.zzab;
        String str = (String) map.get("Date");
        long zzf = str != null ? zzf(str) : 0;
        str = (String) map.get("Cache-Control");
        int i3 = 0;
        if (str != null) {
            String[] split = str.split(",", 0);
            i = 0;
            j = 0;
            j2 = 0;
            while (i3 < split.length) {
                String trim = split[i3].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i = 1;
                }
                i3++;
            }
            i2 = 1;
        } else {
            i = 0;
            i2 = i;
            j = 0;
            j2 = 0;
        }
        str = (String) map.get("Expires");
        long zzf2 = str != null ? zzf(str) : 0;
        str = (String) map.get("Last-Modified");
        long zzf3 = str != null ? zzf(str) : 0;
        str = (String) map.get("ETag");
        if (i2 != 0) {
            long j3 = currentTimeMillis + (j * 1000);
            currentTimeMillis = i != 0 ? j3 : j3 + (j2 * 1000);
            zzf2 = j3;
        } else if (zzf <= 0 || zzf2 < zzf) {
            currentTimeMillis = 0;
            zzf2 = currentTimeMillis;
        } else {
            zzf2 = currentTimeMillis + (zzf2 - zzf);
            currentTimeMillis = zzf2;
        }
        zzc zzc = new zzc();
        zzc.data = zzp2.data;
        zzc.zza = str;
        zzc.zze = zzf2;
        zzc.zzd = currentTimeMillis;
        zzc.zzb = zzf;
        zzc.zzc = zzf3;
        zzc.zzf = map;
        zzc.zzg = zzp2.allHeaders;
        return zzc;
    }

    private static long zzf(String str) {
        try {
            return zzq().parse(str).getTime();
        } catch (ParseException e) {
            zzaf.zza(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    static String zzb(long j) {
        return zzq().format(new Date(j));
    }

    private static SimpleDateFormat zzq() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
