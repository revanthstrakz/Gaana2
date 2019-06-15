package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzayh;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzwu;

@zzark
public final class zza {
    public static boolean zza(Context context, zzc zzc, zzt zzt) {
        if (zzc == null) {
            zzbbd.zzeo("No intent data for launcher overlay.");
            return false;
        }
        zzaan.initialize(context);
        if (zzc.intent != null) {
            return zza(context, zzc.intent, zzt);
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(zzc.url)) {
            zzbbd.zzeo("Open GMSG did not contain a URL.");
            return false;
        }
        String valueOf;
        if (TextUtils.isEmpty(zzc.mimeType)) {
            intent.setData(Uri.parse(zzc.url));
        } else {
            intent.setDataAndType(Uri.parse(zzc.url), zzc.mimeType);
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(zzc.packageName)) {
            intent.setPackage(zzc.packageName);
        }
        if (!TextUtils.isEmpty(zzc.zzdqq)) {
            String[] split = zzc.zzdqq.split("/", 2);
            if (split.length < 2) {
                String str = "Could not parse component name from open GMSG: ";
                valueOf = String.valueOf(zzc.zzdqq);
                zzbbd.zzeo(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        valueOf = zzc.zzdqr;
        if (!TextUtils.isEmpty(valueOf)) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(valueOf);
            } catch (NumberFormatException unused) {
                zzbbd.zzeo("Could not parse intent flags.");
                parseInt = 0;
            }
            intent.addFlags(parseInt);
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwc)).booleanValue()) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
        } else {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwb)).booleanValue()) {
                zzbv.zzlf();
                zzayh.zzb(context, intent);
            }
        }
        return zza(context, intent, zzt);
    }

    private static boolean zza(Context context, Intent intent, zzt zzt) {
        try {
            String str = "Launching an intent: ";
            String valueOf = String.valueOf(intent.toURI());
            zzaxz.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            zzbv.zzlf();
            zzayh.zza(context, intent);
            if (zzt != null) {
                zzt.zzig();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            zzbbd.zzeo(e.getMessage());
            return false;
        }
    }
}
