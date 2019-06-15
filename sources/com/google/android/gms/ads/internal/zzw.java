package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaso;
import com.google.android.gms.internal.ads.zzawr;
import com.google.android.gms.internal.ads.zzayh;

@zzark
public final class zzw {
    private final Context mContext;
    private boolean zzbmw;
    private zzawr zzbmx;
    private zzaso zzbmy;

    public zzw(Context context, zzawr zzawr, zzaso zzaso) {
        this.mContext = context;
        this.zzbmx = zzawr;
        this.zzbmy = zzaso;
        if (this.zzbmy == null) {
            this.zzbmy = new zzaso();
        }
    }

    private final boolean zzjt() {
        return (this.zzbmx != null && this.zzbmx.zzxp().zzegm) || this.zzbmy.zzdzg;
    }

    public final void recordClick() {
        this.zzbmw = true;
    }

    public final boolean zzju() {
        return !zzjt() || this.zzbmw;
    }

    public final void zzas(@Nullable String str) {
        if (zzjt()) {
            if (str == null) {
                str = "";
            }
            if (this.zzbmx != null) {
                this.zzbmx.zza(str, null, 3);
                return;
            }
            if (this.zzbmy.zzdzg && this.zzbmy.zzdzh != null) {
                for (String str2 : this.zzbmy.zzdzh) {
                    String str22;
                    if (!TextUtils.isEmpty(str22)) {
                        str22 = str22.replace("{NAVIGATION_URL}", Uri.encode(str));
                        zzbv.zzlf();
                        zzayh.zzc(this.mContext, "", str22);
                    }
                }
            }
        }
    }
}
