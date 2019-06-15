package com.til.colombia.android.internal;

import android.telephony.TelephonyManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.til.colombia.android.commons.a.a;
import com.til.colombia.android.internal.HttpClient.b;

final class c implements Runnable {
    c() {
    }

    public final void run() {
        try {
            a.j = new b(a.a);
            try {
                h.i().Q = a.c();
                TelephonyManager telephonyManager = (TelephonyManager) a.a.getSystemService("phone");
                h.i().P = telephonyManager.getNetworkOperatorName();
            } catch (Exception e) {
                Log.a(i.f, "", e);
            } catch (Throwable th) {
                Log.a(i.f, "", th);
            }
            h.i().V = a.e();
            CookieSyncManager.createInstance(a.a);
            CookieManager.getInstance().setAcceptCookie(true);
            com.til.colombia.android.internal.a.c.a();
            com.til.colombia.android.utils.c.a(a.a(), com.til.colombia.android.utils.c.a);
            a.a(a.a);
        } catch (Throwable th2) {
            Log.a(i.f, "", th2);
        }
    }
}
