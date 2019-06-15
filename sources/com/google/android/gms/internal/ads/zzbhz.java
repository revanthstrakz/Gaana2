package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;

@zzark
public final class zzbhz {
    public static zzbgg zza(Context context, zzbht zzbht, String str, boolean z, boolean z2, @Nullable zzcu zzcu, zzbbi zzbbi, zzaba zzaba, zzbo zzbo, zzv zzv, zzum zzum) throws zzbgq {
        try {
            return (zzbgg) zzbak.zzb(new zzbia(context, zzbht, str, z, z2, zzcu, zzbbi, zzaba, zzbo, zzv));
        } catch (Throwable th) {
            Throwable th2 = th;
            zzbv.zzlj().zza(th2, "AdWebViewFactory.newAdWebView2");
            zzbgq zzbgq = new zzbgq("Webview initialization failed.", th2);
        }
    }
}
