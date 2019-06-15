package com.til.colombia.android.internal.a;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.til.colombia.android.internal.HttpClient.b;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.h;
import java.net.HttpCookie;

public final class c {
    public static void a() {
        for (HttpCookie httpCookie : a.b().getCookies()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(httpCookie.getName());
            stringBuilder.append("=");
            stringBuilder.append(httpCookie.getValue());
            stringBuilder.append("; domain=");
            stringBuilder.append(httpCookie.getDomain());
            String stringBuilder2 = stringBuilder.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append(", version :");
            stringBuilder3.append(httpCookie.getVersion());
            Log.b("SyncCookies", stringBuilder3.toString());
            CookieManager.getInstance().setCookie(h.h, stringBuilder2);
        }
        CookieSyncManager.getInstance().sync();
    }

    private static void b() {
        b b = a.b();
        for (HttpCookie httpCookie : b.getCookies()) {
            if (httpCookie.getName().equals("_col_fcap")) {
                b.remove(null, httpCookie);
                break;
            }
        }
        a();
    }
}
