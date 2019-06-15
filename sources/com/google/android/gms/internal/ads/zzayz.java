package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.zzbv;
import java.io.InputStream;
import java.util.Map;

@TargetApi(21)
public final class zzayz extends zzayy {
    public final int zzaab() {
        return 16974374;
    }

    public final CookieManager zzba(Context context) {
        if (zzayp.zzaaa()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzbbd.zzb("Failed to obtain CookieManager.", th);
            zzbv.zzlj().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public final zzbgh zza(zzbgg zzbgg, zzum zzum, boolean z) {
        return new zzbhg(zzbgg, zzum, z);
    }

    public final WebResourceResponse zza(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, i, str3, map, inputStream);
    }
}
