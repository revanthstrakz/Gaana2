package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzark
public final class zzaba {
    private final Object mLock = new Object();
    @VisibleForTesting
    private boolean zzczj;
    private final List<zzaay> zzczk = new LinkedList();
    private final Map<String, String> zzczl = new LinkedHashMap();
    private String zzczm;
    @Nullable
    private zzaba zzczn;

    public zzaba(boolean z, String str, String str2) {
        this.zzczj = z;
        this.zzczl.put(NativeProtocol.WEB_DIALOG_ACTION, str);
        this.zzczl.put("ad_format", str2);
    }

    public final void zzc(@Nullable zzaba zzaba) {
        synchronized (this.mLock) {
            this.zzczn = zzaba;
        }
    }

    public final zzaay zzrg() {
        return zzao(zzbv.zzlm().elapsedRealtime());
    }

    @Nullable
    public final zzaay zzao(long j) {
        if (this.zzczj) {
            return new zzaay(j, null, null);
        }
        return null;
    }

    public final boolean zza(@Nullable zzaay zzaay, String... strArr) {
        return (!this.zzczj || zzaay == null) ? false : zza(zzaay, zzbv.zzlm().elapsedRealtime(), strArr);
    }

    public final boolean zza(zzaay zzaay, long j, String... strArr) {
        synchronized (this.mLock) {
            for (String zzaay2 : strArr) {
                this.zzczk.add(new zzaay(j, zzaay2, zzaay));
            }
        }
        return true;
    }

    public final String zzrh() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        synchronized (this.mLock) {
            for (zzaay zzaay : this.zzczk) {
                long time = zzaay.getTime();
                String zzrd = zzaay.zzrd();
                zzaay zzaay2 = zzaay2.zzre();
                if (zzaay2 != null && time > 0) {
                    long time2 = time - zzaay2.getTime();
                    stringBuilder2.append(zzrd);
                    stringBuilder2.append('.');
                    stringBuilder2.append(time2);
                    stringBuilder2.append(',');
                }
            }
            this.zzczk.clear();
            if (!TextUtils.isEmpty(this.zzczm)) {
                stringBuilder2.append(this.zzczm);
            } else if (stringBuilder2.length() > 0) {
                stringBuilder2.setLength(stringBuilder2.length() - 1);
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    public final void zzbp(String str) {
        if (this.zzczj) {
            synchronized (this.mLock) {
                this.zzczm = str;
            }
        }
    }

    public final void zzg(String str, String str2) {
        if (this.zzczj && !TextUtils.isEmpty(str2)) {
            zzaaq zzyh = zzbv.zzlj().zzyh();
            if (zzyh != null) {
                synchronized (this.mLock) {
                    zzaau zzbn = zzyh.zzbn(str);
                    Map map = this.zzczl;
                    map.put(str, zzbn.zzf((String) map.get(str), str2));
                }
            }
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final Map<String, String> zzri() {
        synchronized (this.mLock) {
            Map zza;
            zzaaq zzyh = zzbv.zzlj().zzyh();
            if (zzyh != null) {
                if (this.zzczn != null) {
                    zza = zzyh.zza(this.zzczl, this.zzczn.zzri());
                    return zza;
                }
            }
            zza = this.zzczl;
            return zza;
        }
    }

    public final zzaay zzrj() {
        synchronized (this.mLock) {
        }
        return null;
    }
}
