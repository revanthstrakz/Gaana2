package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import org.json.JSONObject;

@zzark
public final class zzbfq implements zzu<zzbdz> {
    private static Integer zze(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt((String) map.get(str)));
        } catch (NumberFormatException unused) {
            String str2 = (String) map.get(str);
            StringBuilder stringBuilder = new StringBuilder((39 + String.valueOf(str).length()) + String.valueOf(str2).length());
            stringBuilder.append("Precache invalid numeric parameter '");
            stringBuilder.append(str);
            stringBuilder.append("': ");
            stringBuilder.append(str2);
            zzbbd.zzeo(stringBuilder.toString());
            return null;
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        String valueOf;
        zzbdz zzbdz = (zzbdz) obj;
        if (zzbbd.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            valueOf = String.valueOf(jSONObject);
            StringBuilder stringBuilder = new StringBuilder(15 + String.valueOf(valueOf).length());
            stringBuilder.append("Precache GMSG: ");
            stringBuilder.append(valueOf);
            zzbbd.zzdn(stringBuilder.toString());
        }
        zzbv.zzmd();
        if (map.containsKey("abort")) {
            if (!zzbfj.zzc(zzbdz)) {
                zzbbd.zzeo("Precache abort but no precache task running.");
            }
            return;
        }
        zzbfk zzbfk;
        valueOf = (String) map.get("src");
        if (valueOf == null) {
            zzbfh zzd = zzbfj.zzd(zzbdz);
            if (zzd != null) {
                zzbfk = zzd.zzewk;
            } else {
                zzbbd.zzeo("Precache must specify a source.");
                return;
            }
        } else if (zzbfj.zzd(zzbdz) != null) {
            zzbbd.zzeo("Precache task is already running.");
            return;
        } else if (zzbdz.zzid() == null) {
            zzbbd.zzeo("Precache requires a dependency provider.");
            return;
        } else {
            zzbdy zzbdy = new zzbdy((String) map.get("flags"));
            Integer zze = zze(map, "player");
            if (zze == null) {
                zze = Integer.valueOf(0);
            }
            zzbfk = zzbdz.zzid().zzbms.zza(zzbdz, zze.intValue(), null, zzbdy);
            new zzbfh(zzbdz, zzbfk, valueOf).zzwa();
        }
        Integer zze2 = zze(map, "minBufferMs");
        if (zze2 != null) {
            zzbfk.zzcz(zze2.intValue());
        }
        zze2 = zze(map, "maxBufferMs");
        if (zze2 != null) {
            zzbfk.zzda(zze2.intValue());
        }
        zze2 = zze(map, "bufferForPlaybackMs");
        if (zze2 != null) {
            zzbfk.zzdb(zze2.intValue());
        }
        zze2 = zze(map, "bufferForPlaybackAfterRebufferMs");
        if (zze2 != null) {
            zzbfk.zzdc(zze2.intValue());
        }
    }
}
