package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.l.a.a;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.o.e;
import java.util.Map;

class j extends b {
    private static final String d = "j";
    private final Uri e;
    private final Map<String, String> f;

    j(Context context, c cVar, String str, Uri uri, Map<String, String> map) {
        super(context, cVar, str);
        this.e = uri;
        this.f = map;
    }

    public a a() {
        return null;
    }

    public void b() {
        e eVar = e.IMMEDIATE;
        String queryParameter = this.e.getQueryParameter("priority");
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                eVar = e.values()[Integer.valueOf(queryParameter).intValue()];
            } catch (Exception unused) {
            }
        }
        this.b.a(this.c, this.f, this.e.getQueryParameter("type"), eVar);
    }
}
