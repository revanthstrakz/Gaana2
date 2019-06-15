package com.til.colombia.android.network;

import com.android.volley.Request;
import com.android.volley.c;
import com.android.volley.g;
import com.android.volley.i;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.android.volley.toolbox.d;
import com.google.android.exoplayer2.DefaultLoadControl;
import java.util.Map;

public final class h extends Request<byte[]> {
    private final b<byte[]> a;

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void deliverResponse(Object obj) {
        byte[] bArr = (byte[]) obj;
        if (this.a != null) {
            this.a.onResponse(bArr);
        }
    }

    public h(int i, String str, b<byte[]> bVar, a aVar) {
        super(0, str, aVar);
        setRetryPolicy(new c(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, 0, 1.0f));
        this.a = bVar;
    }

    public final String getCacheKey() {
        return q.a(getUrl());
    }

    /* Access modifiers changed, original: protected|final */
    public final i<byte[]> parseNetworkResponse(g gVar) {
        byte[] bArr;
        if (com.til.colombia.android.internal.a.H()) {
            bArr = gVar.b;
            n.a((String) gVar.c.get(com.til.colombia.android.internal.h.i));
            return i.a(bArr, d.a(gVar));
        }
        bArr = gVar.b;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = gVar.c;
        long j = 0;
        String str = (String) map.get("Date");
        if (str != null) {
            j = d.a(str);
        }
        str = (String) map.get(com.til.colombia.android.internal.h.i);
        if (!com.til.colombia.android.internal.a.h.a(str)) {
            n.a(str);
        }
        str = (String) map.get("ETag");
        long j2 = currentTimeMillis + 240000;
        long j3 = currentTimeMillis + 14400000;
        com.android.volley.a.a aVar = new com.android.volley.a.a();
        aVar.a = gVar.b;
        aVar.b = str;
        aVar.f = j2;
        aVar.e = j3;
        aVar.c = j;
        aVar.g = map;
        return i.a(bArr, aVar);
    }

    private void a(byte[] bArr) {
        if (this.a != null) {
            this.a.onResponse(bArr);
        }
    }

    private static com.android.volley.a.a a(g gVar) {
        n.a((String) gVar.c.get(com.til.colombia.android.internal.h.i));
        return d.a(gVar);
    }

    private static com.android.volley.a.a b(g gVar) {
        long currentTimeMillis = System.currentTimeMillis();
        Map map = gVar.c;
        String str = (String) map.get("Date");
        long a = str != null ? d.a(str) : 0;
        String str2 = (String) map.get(com.til.colombia.android.internal.h.i);
        if (!com.til.colombia.android.internal.a.h.a(str2)) {
            n.a(str2);
        }
        str2 = (String) map.get("ETag");
        long j = currentTimeMillis + 240000;
        long j2 = currentTimeMillis + 14400000;
        com.android.volley.a.a aVar = new com.android.volley.a.a();
        aVar.a = gVar.b;
        aVar.b = str2;
        aVar.f = j;
        aVar.e = j2;
        aVar.c = a;
        aVar.g = map;
        return aVar;
    }
}
