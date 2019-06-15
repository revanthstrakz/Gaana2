package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;

@zzark
public final class zzait {
    private final Context mContext;
    private final Object mLock;
    private final zzbbi zzbob;
    private final String zzdiv;
    private zzazn<zzaii> zzdiw;
    private zzazn<zzaii> zzdix;
    @Nullable
    private zzajm zzdiy;
    private int zzdiz;

    public zzait(Context context, zzbbi zzbbi, String str) {
        this.mLock = new Object();
        this.zzdiz = 1;
        this.zzdiv = str;
        this.mContext = context.getApplicationContext();
        this.zzbob = zzbbi;
        this.zzdiw = new zzajh();
        this.zzdix = new zzajh();
    }

    public zzait(Context context, zzbbi zzbbi, String str, zzazn<zzaii> zzazn, zzazn<zzaii> zzazn2) {
        this(context, zzbbi, str);
        this.zzdiw = zzazn;
        this.zzdix = zzazn2;
    }

    /* Access modifiers changed, original: protected|final */
    public final zzajm zza(@Nullable zzcu zzcu) {
        zzajm zzajm = new zzajm(this.zzdix);
        zzbcg.zzepo.execute(new zzaiu(this, zzcu, zzajm));
        zzajm.zza(new zzaje(this, zzajm), new zzajf(this, zzajm));
        return zzajm;
    }

    public final zzaji zzb(@Nullable zzcu zzcu) {
        synchronized (this.mLock) {
            zzaji zzud;
            synchronized (this.mLock) {
                if (this.zzdiy != null && this.zzdiz == 0) {
                    if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcob)).booleanValue()) {
                        this.zzdiy.zza(new zzaiv(this), zzaiw.zzdjd);
                    }
                }
            }
            if (this.zzdiy != null) {
                if (this.zzdiy.getStatus() != -1) {
                    if (this.zzdiz == 0) {
                        zzud = this.zzdiy.zzud();
                        return zzud;
                    } else if (this.zzdiz == 1) {
                        this.zzdiz = 2;
                        zza(null);
                        zzud = this.zzdiy.zzud();
                        return zzud;
                    } else if (this.zzdiz == 2) {
                        zzud = this.zzdiy.zzud();
                        return zzud;
                    } else {
                        zzud = this.zzdiy.zzud();
                        return zzud;
                    }
                }
            }
            this.zzdiz = 2;
            this.zzdiy = zza(null);
            zzud = this.zzdiy.zzud();
            return zzud;
        }
    }
}
