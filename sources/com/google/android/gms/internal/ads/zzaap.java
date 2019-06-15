package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.zzbv;
import com.til.colombia.android.internal.e;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@zzark
public final class zzaap {
    private Context mContext = null;
    private String zzbuk = null;
    private String zzcyt;
    private Map<String, String> zzcyu;

    public zzaap(Context context, String str) {
        Object packageName;
        this.mContext = context;
        this.zzbuk = str;
        this.zzcyt = (String) zzwu.zzpz().zzd(zzaan.zzcpx);
        this.zzcyu = new LinkedHashMap();
        this.zzcyu.put("s", "gmob_sdk");
        this.zzcyu.put("v", "3");
        this.zzcyu.put(e.C, VERSION.RELEASE);
        this.zzcyu.put("sdk", VERSION.SDK);
        zzbv.zzlf();
        this.zzcyu.put("device", zzayh.zzzt());
        Map map = this.zzcyu;
        String str2 = "app";
        if (context.getApplicationContext() != null) {
            packageName = context.getApplicationContext().getPackageName();
        } else {
            packageName = context.getPackageName();
        }
        map.put(str2, packageName);
        map = this.zzcyu;
        str2 = "is_lite_sdk";
        zzbv.zzlf();
        map.put(str2, zzayh.zzav(context) ? "1" : "0");
        Future zzt = zzbv.zzlq().zzt(this.mContext);
        try {
            zzt.get();
            this.zzcyu.put("network_coarse", Integer.toString(((zzatz) zzt.get()).zzedd));
            this.zzcyu.put("network_fine", Integer.toString(((zzatz) zzt.get()).zzede));
        } catch (Exception e) {
            zzbv.zzlj().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    /* Access modifiers changed, original: final */
    public final String zzra() {
        return this.zzcyt;
    }

    /* Access modifiers changed, original: final */
    public final Context getContext() {
        return this.mContext;
    }

    /* Access modifiers changed, original: final */
    public final String zzmr() {
        return this.zzbuk;
    }

    /* Access modifiers changed, original: final */
    public final Map<String, String> zzrb() {
        return this.zzcyu;
    }
}
