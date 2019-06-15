package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;

@zzark
public final class zzbff implements zzu<zzbdz> {
    private boolean zzewj;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                zzwu.zzpv();
                return zzbat.zza(context, Integer.parseInt(str2));
            } catch (NumberFormatException unused) {
                StringBuilder stringBuilder = new StringBuilder((34 + String.valueOf(str).length()) + String.valueOf(str2).length());
                stringBuilder.append("Could not parse ");
                stringBuilder.append(str);
                stringBuilder.append(" in a video GMSG: ");
                stringBuilder.append(str2);
                zzbbd.zzeo(stringBuilder.toString());
            }
        }
        return i;
    }

    private static void zza(zzbdk zzbdk, Map<String, String> map) {
        String str = (String) map.get("minBufferMs");
        String str2 = (String) map.get("maxBufferMs");
        String str3 = (String) map.get("bufferForPlaybackMs");
        String str4 = (String) map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                zzbdk.zzcz(Integer.parseInt(str));
            } catch (NumberFormatException unused) {
                zzbbd.zzeo(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            zzbdk.zzda(Integer.parseInt(str2));
        }
        if (str3 != null) {
            zzbdk.zzdb(Integer.parseInt(str3));
        }
        if (str4 != null) {
            zzbdk.zzdc(Integer.parseInt(str4));
        }
    }
}
