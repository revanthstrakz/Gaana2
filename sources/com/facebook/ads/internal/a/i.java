package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.l.a.a;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.c.g;
import java.util.Map;

class i extends h {
    private static final String e = "i";
    private final Uri f;
    private final Map<String, String> g;

    i(Context context, c cVar, String str, Uri uri, Map<String, String> map, l lVar) {
        super(context, cVar, str, lVar);
        this.f = uri;
        this.g = map;
    }

    public a a() {
        return a.OPEN_LINK;
    }

    @Nullable
    public a c() {
        try {
            g.a(new g(), this.a, Uri.parse(this.f.getQueryParameter("link")), this.c);
            return null;
        } catch (Exception e) {
            String str = e;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to open link url: ");
            stringBuilder.append(this.f.toString());
            Log.d(str, stringBuilder.toString(), e);
            return a.CANNOT_OPEN;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        a(this.g, c());
    }
}
