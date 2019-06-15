package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaan;
import com.google.android.gms.internal.ads.zzajv;
import com.google.android.gms.internal.ads.zzaka;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxj;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbbi;
import com.google.android.gms.internal.ads.zzbbo;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzbcb;
import com.google.android.gms.internal.ads.zzbcg;
import com.google.android.gms.internal.ads.zzwu;
import org.json.JSONObject;

@zzark
public final class zzad {
    private Context mContext;
    private final Object mLock = new Object();
    private long zzbnh = 0;

    public final void zza(Context context, zzbbi zzbbi, String str, @Nullable Runnable runnable) {
        zza(context, zzbbi, true, null, str, null, runnable);
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zza(Context context, zzbbi zzbbi, boolean z, @Nullable zzaxj zzaxj, String str, @Nullable String str2, @Nullable Runnable runnable) {
        if (zzbv.zzlm().elapsedRealtime() - this.zzbnh < DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
            zzbbd.zzeo("Not retrying to fetch app settings");
            return;
        }
        this.zzbnh = zzbv.zzlm().elapsedRealtime();
        Object obj = 1;
        if (zzaxj != null) {
            if ((zzbv.zzlm().currentTimeMillis() - zzaxj.zzyc() > ((Long) zzwu.zzpz().zzd(zzaan.zzcvb)).longValue() ? 1 : null) == null && zzaxj.zzyd()) {
                obj = null;
            }
        }
        if (obj != null) {
            if (context == null) {
                zzbbd.zzeo("Context not provided to fetch application settings");
            } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                zzbbd.zzeo("App settings could not be fetched. Required parameters missing");
            } else {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = context;
                }
                this.mContext = applicationContext;
                zzajv zza = zzbv.zzlu().zzb(this.mContext, zzbbi).zza("google.afma.config.fetchAppSettings", zzaka.zzdkb, zzaka.zzdkb);
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str)) {
                        jSONObject.put("app_id", str);
                    } else if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("ad_unit_id", str2);
                    }
                    jSONObject.put("is_init", z);
                    jSONObject.put("pn", context.getPackageName());
                    zzbcb zzj = zza.zzj(jSONObject);
                    zzbcb zza2 = zzbbq.zza(zzj, zzae.zzbni, zzbcg.zzepp);
                    if (runnable != null) {
                        zzj.zza(runnable, zzbcg.zzepp);
                    }
                    zzbbo.zza(zza2, "ConfigLoader.maybeFetchNewAppSettings");
                } catch (Exception e) {
                    zzbbd.zzb("Error requesting application settings", e);
                }
            }
        }
    }
}
