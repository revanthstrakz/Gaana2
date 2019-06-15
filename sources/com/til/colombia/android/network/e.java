package com.til.colombia.android.network;

import android.text.TextUtils;
import com.android.volley.toolbox.f;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.h;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;

final class e extends f {
    e() {
    }

    /* Access modifiers changed, original: protected|final */
    public final HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection createConnection = super.createConnection(url);
        String str = com.til.colombia.android.internal.e.c;
        h.i();
        createConnection.setRequestProperty(str, h.j());
        if (HttpCookie.domainMatches(h.h, createConnection.getURL().getHost()) && a.b().getCookies().size() >= 0) {
            createConnection.setRequestProperty("Cookie", TextUtils.join(";", a.b().getCookies()));
        }
        return createConnection;
    }
}
