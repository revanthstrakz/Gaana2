package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebView;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
final class zzbii {
    @VisibleForTesting
    private static Boolean zzfbi;

    private zzbii() {
    }

    @TargetApi(19)
    private static boolean zzb(WebView webView) {
        boolean booleanValue;
        synchronized (zzbii.class) {
            if (zzfbi == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", null);
                    zzfbi = Boolean.valueOf(true);
                } catch (IllegalStateException unused) {
                    zzfbi = Boolean.valueOf(false);
                }
            }
            booleanValue = zzfbi.booleanValue();
        }
        return booleanValue;
    }

    @TargetApi(19)
    static void zza(WebView webView, String str) {
        if (PlatformVersion.isAtLeastKitKat() && zzb(webView)) {
            webView.evaluateJavascript(str, null);
            return;
        }
        String str2 = "javascript:";
        str = String.valueOf(str);
        webView.loadUrl(str.length() != 0 ? str2.concat(str) : new String(str2));
    }
}
