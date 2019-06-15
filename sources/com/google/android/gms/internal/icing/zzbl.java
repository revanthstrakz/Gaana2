package com.google.android.gms.internal.icing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzbl<T> {
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzdc = null;
    private static final Object zzdf = new Object();
    private static boolean zzdg = false;
    private static final AtomicInteger zzdj = new AtomicInteger();
    private final String name;
    private final zzbo zzdh;
    private final T zzdi;
    private volatile int zzdk;
    private volatile T zzdl;

    public static void zzd(Context context) {
        synchronized (zzdf) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzdc != context) {
                synchronized (zzbb.class) {
                    zzbb.zzct.clear();
                }
                synchronized (zzbp.class) {
                    zzbp.zzdt.clear();
                }
                synchronized (zzbi.class) {
                    zzbi.zzdb = null;
                }
                zzdj.incrementAndGet();
                zzdc = context;
            }
        }
    }

    public abstract T zza(Object obj);

    static void zzx() {
        zzdj.incrementAndGet();
    }

    private zzbl(zzbo zzbo, String str, T t) {
        this.zzdk = -1;
        if (zzbo.zzdn == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzdh = zzbo;
        this.name = str;
        this.zzdi = t;
    }

    private final String zzq(String str) {
        if (str != null && str.isEmpty()) {
            return this.name;
        }
        str = String.valueOf(str);
        String valueOf = String.valueOf(this.name);
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public final String zzy() {
        return zzq(this.zzdh.zzdp);
    }

    public final T get() {
        int i = zzdj.get();
        if (this.zzdk < i) {
            synchronized (this) {
                if (this.zzdk < i) {
                    if (zzdc == null) {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                    zzbo zzbo = this.zzdh;
                    Object zzz = zzz();
                    if (zzz == null) {
                        zzz = zzaa();
                        if (zzz == null) {
                            zzz = this.zzdi;
                        }
                    }
                    this.zzdl = zzz;
                    this.zzdk = i;
                }
            }
        }
        return this.zzdl;
    }

    private final T zzz() {
        zzbo zzbo = this.zzdh;
        String str = (String) zzbi.zzc(zzdc).zzn("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
        Object obj = (str == null || !zzay.zzcg.matcher(str).matches()) ? null : 1;
        if (obj == null) {
            zzbf zza;
            if (this.zzdh.zzdn != null) {
                zza = zzbb.zza(zzdc.getContentResolver(), this.zzdh.zzdn);
            } else {
                Context context = zzdc;
                zzbo zzbo2 = this.zzdh;
                zza = zzbp.zza(context, null);
            }
            if (zza != null) {
                obj = zza.zzn(zzy());
                if (obj != null) {
                    return zza(obj);
                }
            }
        }
        str = "PhenotypeFlag";
        String str2 = "Bypass reading Phenotype values for flag: ";
        String valueOf = String.valueOf(zzy());
        Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        return null;
    }

    private final T zzaa() {
        zzbo zzbo = this.zzdh;
        zzbi zzc = zzbi.zzc(zzdc);
        zzbo zzbo2 = this.zzdh;
        Object zzn = zzc.zzn(zzq(this.zzdh.zzdo));
        return zzn != null ? zza(zzn) : null;
    }

    private static zzbl<Boolean> zza(zzbo zzbo, String str, boolean z) {
        return new zzbn(zzbo, str, Boolean.valueOf(z));
    }

    /* synthetic */ zzbl(zzbo zzbo, String str, Object obj, zzbm zzbm) {
        this(zzbo, str, obj);
    }
}
