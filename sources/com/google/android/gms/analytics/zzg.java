package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import com.comscore.utils.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@VisibleForTesting
public final class zzg {
    private final zzj zzry;
    private final Clock zzrz;
    private boolean zzsa;
    private long zzsb;
    private long zzsc;
    private long zzsd;
    private long zzse;
    private long zzsf;
    private boolean zzsg;
    private final Map<Class<? extends zzi>, zzi> zzsh;
    private final List<zzo> zzsi;

    @VisibleForTesting
    public final zzg zzs() {
        return new zzg(this);
    }

    @VisibleForTesting
    public final void zza(zzi zzi) {
        Preconditions.checkNotNull(zzi);
        Class cls = zzi.getClass();
        if (cls.getSuperclass() != zzi.class) {
            throw new IllegalArgumentException();
        }
        zzi.zzb(zzb(cls));
    }

    @VisibleForTesting
    public final <T extends zzi> T zza(Class<T> cls) {
        return (zzi) this.zzsh.get(cls);
    }

    @VisibleForTesting
    public final <T extends zzi> T zzb(Class<T> cls) {
        zzi zzi = (zzi) this.zzsh.get(cls);
        if (zzi != null) {
            return zzi;
        }
        zzi = zzc(cls);
        this.zzsh.put(cls, zzi);
        return zzi;
    }

    @VisibleForTesting
    public final Collection<zzi> zzt() {
        return this.zzsh.values();
    }

    public final List<zzo> zzu() {
        return this.zzsi;
    }

    @VisibleForTesting
    public final long zzv() {
        return this.zzsb;
    }

    @VisibleForTesting
    public final void zza(long j) {
        this.zzsc = j;
    }

    @VisibleForTesting
    public final void zzw() {
        this.zzry.zzac().zze(this);
    }

    @VisibleForTesting
    zzg(zzj zzj, Clock clock) {
        Preconditions.checkNotNull(zzj);
        Preconditions.checkNotNull(clock);
        this.zzry = zzj;
        this.zzrz = clock;
        this.zzse = Constants.SESSION_INACTIVE_PERIOD;
        this.zzsf = 3024000000L;
        this.zzsh = new HashMap();
        this.zzsi = new ArrayList();
    }

    private zzg(zzg zzg) {
        this.zzry = zzg.zzry;
        this.zzrz = zzg.zzrz;
        this.zzsb = zzg.zzsb;
        this.zzsc = zzg.zzsc;
        this.zzsd = zzg.zzsd;
        this.zzse = zzg.zzse;
        this.zzsf = zzg.zzsf;
        this.zzsi = new ArrayList(zzg.zzsi);
        this.zzsh = new HashMap(zzg.zzsh.size());
        for (Entry entry : zzg.zzsh.entrySet()) {
            zzi zzc = zzc((Class) entry.getKey());
            ((zzi) entry.getValue()).zzb(zzc);
            this.zzsh.put((Class) entry.getKey(), zzc);
        }
    }

    @TargetApi(19)
    private static <T extends zzi> T zzc(Class<T> cls) {
        try {
            return (zzi) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            if (e instanceof InstantiationException) {
                throw new IllegalArgumentException("dataType doesn't have default constructor", e);
            } else if (e instanceof IllegalAccessException) {
                throw new IllegalArgumentException("dataType default constructor is not accessible", e);
            } else if (VERSION.SDK_INT < 19 || !(e instanceof ReflectiveOperationException)) {
                throw new RuntimeException(e);
            } else {
                throw new IllegalArgumentException("Linkage exception", e);
            }
        }
    }

    @VisibleForTesting
    public final boolean zzx() {
        return this.zzsa;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzy() {
        this.zzsd = this.zzrz.elapsedRealtime();
        if (this.zzsc != 0) {
            this.zzsb = this.zzsc;
        } else {
            this.zzsb = this.zzrz.currentTimeMillis();
        }
        this.zzsa = true;
    }

    /* Access modifiers changed, original: final */
    public final zzj zzz() {
        return this.zzry;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final boolean zzaa() {
        return this.zzsg;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzab() {
        this.zzsg = true;
    }
}
