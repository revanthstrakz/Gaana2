package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.l.a.a;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.c.g;

public class k extends b {
    private static final String d = "k";
    private final Uri e;

    public k(Context context, c cVar, String str, Uri uri) {
        super(context, cVar, str);
        this.e = uri;
    }

    public a a() {
        return a.OPEN_LINK;
    }

    public void b() {
        try {
            Log.w("REDIRECTACTION: ", this.e.toString());
            g.a(new g(), this.a, this.e, this.c);
        } catch (Exception e) {
            String str = d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to open link url: ");
            stringBuilder.append(this.e.toString());
            Log.d(str, stringBuilder.toString(), e);
        }
    }
}
