package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.g;
import com.android.volley.i;
import com.android.volley.i.a;
import com.android.volley.i.b;
import java.io.UnsupportedEncodingException;

public class n extends Request<String> {
    private final b<String> a;

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void deliverResponse(String str) {
    }

    public n(int i, String str, b<String> bVar, a aVar) {
        super(i, str, aVar);
        this.a = bVar;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void deliverResponse(String str, boolean z) {
        this.a.onResponse(str);
    }

    /* Access modifiers changed, original: protected */
    public i<String> parseNetworkResponse(g gVar) {
        Object str;
        try {
            str = new String(gVar.b, d.a(gVar.c));
        } catch (UnsupportedEncodingException unused) {
            str = new String(gVar.b);
        }
        return i.a(str, d.a(gVar));
    }
}
