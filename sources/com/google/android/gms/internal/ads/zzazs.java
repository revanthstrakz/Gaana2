package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.api.client.http.HttpMethods;
import java.io.File;
import java.util.Map;

@zzark
public final class zzazs {
    private static zzv zzemw;
    private static final Object zzemx = new Object();
    @Deprecated
    private static final zzazy<Void> zzemy = new zzazt();

    public zzazs(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        zzbe(context);
    }

    @Deprecated
    public final <T> zzbcb<T> zza(String str, zzazy<T> zzazy) {
        zzbcb zzbcl = new zzbcl();
        zzemw.zze(new zzbaa(str, zzbcl));
        return zzbbq.zza(zzbbq.zza(zzbcl, new zzazv(this, zzazy), zzayf.zzeky), Throwable.class, new zzazu(this, zzazy), zzbcg.zzepp);
    }

    public final zzbcb<String> zza(int i, String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        String str2 = str;
        zzazz zzazz = new zzazz();
        zzazw zzazw = new zzazw(this, str2, zzazz);
        zzbax zzbax = new zzbax(null);
        zzazx zzazx = new zzazx(this, i, str2, zzazz, zzazw, bArr, map, zzbax);
        if (zzbax.isEnabled()) {
            try {
                zzbax.zza(str2, HttpMethods.GET, zzazx.getHeaders(), zzazx.zzh());
            } catch (zza e) {
                zzbbd.zzeo(e.getMessage());
            }
        }
        zzemw.zze(zzazx);
        return zzazz;
    }

    public final zzbcb<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }

    @VisibleForTesting
    private static zzv zzbe(Context context) {
        zzv zzbd;
        synchronized (zzemx) {
            if (zzemw == null) {
                zzaan.initialize(context);
                if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcvy)).booleanValue()) {
                    zzbd = zzazm.zzbd(context);
                } else {
                    zzbd = new zzv(new zzam(new File(context.getCacheDir(), "volley")), new zzaj(new zzas()));
                    zzbd.start();
                }
                zzemw = zzbd;
            }
            zzbd = zzemw;
        }
        return zzbd;
    }
}
