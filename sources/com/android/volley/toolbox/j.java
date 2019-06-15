package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.g;
import com.android.volley.i;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.android.volley.l;
import com.facebook.ads.AudienceNetworkActivity;
import java.io.UnsupportedEncodingException;

public abstract class j<T> extends Request<T> {
    private static final String a = String.format("application/json; charset=%s", new Object[]{AudienceNetworkActivity.WEBVIEW_ENCODING});
    private final b<T> b;
    private final String c;

    public abstract i<T> parseNetworkResponse(g gVar);

    public j(int i, String str, String str2, b<T> bVar, a aVar) {
        super(i, str, aVar);
        this.b = bVar;
        this.c = str2;
    }

    /* Access modifiers changed, original: protected */
    public void deliverResponse(T t, boolean z) {
        this.b.onResponse(t);
    }

    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    public byte[] getPostBody() {
        return getBody();
    }

    public String getBodyContentType() {
        return a;
    }

    public byte[] getBody() {
        byte[] bArr = null;
        try {
            if (this.c != null) {
                bArr = this.c.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING);
            }
            return bArr;
        } catch (UnsupportedEncodingException unused) {
            l.d("Unsupported Encoding while trying to get the bytes of %s using %s", this.c, AudienceNetworkActivity.WEBVIEW_ENCODING);
            return null;
        }
    }
}
