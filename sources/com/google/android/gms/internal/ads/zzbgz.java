package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzark
@TargetApi(17)
public final class zzbgz<WebViewT extends zzbhc & zzbhk & zzbhm> {
    private final zzbhb zzfaq;
    private final WebViewT zzfar;

    public static zzbgz<zzbgg> zzk(zzbgg zzbgg) {
        return new zzbgz(zzbgg, new zzbha(zzbgg));
    }

    private zzbgz(WebViewT webViewT, zzbhb zzbhb) {
        this.zzfaq = zzbhb;
        this.zzfar = webViewT;
    }
}
