package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;

@ShowFirstParty
public final class zzag extends zzi<zzag> {
    private String zzuo;
    private String zzup;
    private String zzuq;
    private String zzur;
    private boolean zzus;
    private String zzut;
    private boolean zzuu;
    private double zzuv;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("hitType", this.zzuo);
        hashMap.put("clientId", this.zzup);
        hashMap.put("userId", this.zzuq);
        hashMap.put("androidAdId", this.zzur);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzus));
        hashMap.put("sessionControl", this.zzut);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzuu));
        hashMap.put("sampleRate", Double.valueOf(this.zzuv));
        return zzi.zza((Object) hashMap);
    }

    public final String zzbc() {
        return this.zzuo;
    }

    public final void zzl(String str) {
        this.zzuo = str;
    }

    public final String zzbd() {
        return this.zzup;
    }

    public final void setClientId(String str) {
        this.zzup = str;
    }

    public final String zzbe() {
        return this.zzuq;
    }

    public final void setUserId(String str) {
        this.zzuq = str;
    }

    public final String zzbf() {
        return this.zzur;
    }

    public final void zzm(String str) {
        this.zzur = str;
    }

    public final boolean zzbg() {
        return this.zzus;
    }

    public final void zza(boolean z) {
        this.zzus = z;
    }

    public final String zzbh() {
        return this.zzut;
    }

    public final boolean zzbi() {
        return this.zzuu;
    }

    public final void zzb(boolean z) {
        this.zzuu = true;
    }

    public final double zzbj() {
        return this.zzuv;
    }
}
