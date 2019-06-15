package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzr<T> implements Comparable<zzr<T>> {
    private final Object mLock;
    private final zza zzae;
    private final int zzaf;
    private final String zzag;
    private final int zzah;
    @Nullable
    @GuardedBy("mLock")
    private zzy zzai;
    private Integer zzaj;
    private zzv zzak;
    private boolean zzal;
    @GuardedBy("mLock")
    private boolean zzam;
    @GuardedBy("mLock")
    private boolean zzan;
    private boolean zzao;
    private zzab zzap;
    private zzc zzaq;
    @GuardedBy("mLock")
    private zzt zzar;

    public zzr(int i, String str, @Nullable zzy zzy) {
        this.zzae = zza.zzbk ? new zza() : null;
        this.mLock = new Object();
        this.zzal = true;
        int i2 = 0;
        this.zzam = false;
        this.zzan = false;
        this.zzao = false;
        this.zzaq = null;
        this.zzaf = i;
        this.zzag = str;
        this.zzai = zzy;
        this.zzap = new zzh();
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    i2 = host.hashCode();
                }
            }
        }
        this.zzah = i2;
    }

    public abstract zzx<T> zza(zzp zzp);

    public abstract void zza(T t);

    public byte[] zzh() throws zza {
        return null;
    }

    public final int getMethod() {
        return this.zzaf;
    }

    public final int zze() {
        return this.zzah;
    }

    public final void zzb(String str) {
        if (zza.zzbk) {
            this.zzae.zza(str, Thread.currentThread().getId());
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzc(String str) {
        if (this.zzak != null) {
            this.zzak.zzf(this);
        }
        if (zza.zzbk) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzs(this, str, id));
            } else {
                this.zzae.zza(str, id);
                this.zzae.zzc(toString());
            }
        }
    }

    public final zzr<?> zza(zzv zzv) {
        this.zzak = zzv;
        return this;
    }

    public final zzr<?> zza(int i) {
        this.zzaj = Integer.valueOf(i);
        return this;
    }

    public final String getUrl() {
        return this.zzag;
    }

    public final String zzf() {
        String str = this.zzag;
        int i = this.zzaf;
        if (i == 0 || i == -1) {
            return str;
        }
        String num = Integer.toString(i);
        StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(num).length()) + String.valueOf(str).length());
        stringBuilder.append(num);
        stringBuilder.append('-');
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public final zzr<?> zza(zzc zzc) {
        this.zzaq = zzc;
        return this;
    }

    public final zzc zzg() {
        return this.zzaq;
    }

    public final boolean isCanceled() {
        synchronized (this.mLock) {
        }
        return false;
    }

    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }

    public final boolean zzi() {
        return this.zzal;
    }

    public final int zzj() {
        return this.zzap.zzc();
    }

    public final zzab zzk() {
        return this.zzap;
    }

    public final void zzl() {
        synchronized (this.mLock) {
            this.zzan = true;
        }
    }

    public final boolean zzm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzan;
        }
        return z;
    }

    public final void zzb(zzae zzae) {
        zzy zzy;
        synchronized (this.mLock) {
            zzy = this.zzai;
        }
        if (zzy != null) {
            zzy.zzd(zzae);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzt zzt) {
        synchronized (this.mLock) {
            this.zzar = zzt;
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzx<?> zzx) {
        zzt zzt;
        synchronized (this.mLock) {
            zzt = this.zzar;
        }
        if (zzt != null) {
            zzt.zza(this, zzx);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzn() {
        zzt zzt;
        synchronized (this.mLock) {
            zzt = this.zzar;
        }
        if (zzt != null) {
            zzt.zza(this);
        }
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(this.zzah));
        Object concat = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        isCanceled();
        valueOf = "[ ] ";
        String str2 = this.zzag;
        String valueOf2 = String.valueOf(zzu.NORMAL);
        String valueOf3 = String.valueOf(this.zzaj);
        StringBuilder stringBuilder = new StringBuilder(((((3 + String.valueOf(valueOf).length()) + String.valueOf(str2).length()) + String.valueOf(concat).length()) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length());
        stringBuilder.append(valueOf);
        stringBuilder.append(str2);
        stringBuilder.append(" ");
        stringBuilder.append(concat);
        stringBuilder.append(" ");
        stringBuilder.append(valueOf2);
        stringBuilder.append(" ");
        stringBuilder.append(valueOf3);
        return stringBuilder.toString();
    }

    public /* synthetic */ int compareTo(Object obj) {
        zzr zzr = (zzr) obj;
        zzu zzu = zzu.NORMAL;
        zzu zzu2 = zzu.NORMAL;
        return zzu == zzu2 ? this.zzaj.intValue() - zzr.zzaj.intValue() : zzu2.ordinal() - zzu.ordinal();
    }
}
