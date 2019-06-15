package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzbb;
import com.google.android.gms.common.util.PlatformVersion;

@zzark
public final class zzapl {
    public static zzazb zza(Context context, zza zza, zzaxg zzaxg, zzcu zzcu, @Nullable zzbgg zzbgg, zzalg zzalg, zzapm zzapm, zzaba zzaba) {
        Object zzapo;
        zzasm zzasm = zzaxg.zzehy;
        if (zzasm.zzdyd) {
            zzapr zzapr = new zzapr(context, zzaxg, zzalg, zzapm, zzaba, zzbgg);
        } else if (zzasm.zzckn || (zza instanceof zzbb)) {
            if (zzasm.zzckn && (zza instanceof zzbb)) {
                zzapt zzapt = new zzapt(context, (zzbb) zza, zzaxg, zzcu, zzapm, zzaba);
            } else {
                zzapo = new zzapo(zzaxg, zzapm);
            }
        } else if (!PlatformVersion.isAtLeastKitKat() || PlatformVersion.isAtLeastLollipop() || zzbgg == null || !zzbgg.zzadj().zzafb()) {
            zzapo = new zzapn(context, zzaxg, zzbgg, zzapm);
        } else {
            zzapo = new zzapq(context, zzaxg, zzbgg, zzapm);
        }
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(zzapo.getClass().getName());
        zzbbd.zzdn(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzapo.zzwa();
        return zzapo;
    }
}
