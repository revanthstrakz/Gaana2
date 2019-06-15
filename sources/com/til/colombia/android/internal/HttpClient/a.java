package com.til.colombia.android.internal.HttpClient;

import android.text.TextUtils;
import android.util.Log;
import com.til.colombia.android.internal.a.c;
import com.til.colombia.android.internal.a.d;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.h;
import com.til.colombia.android.internal.i;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public final class a {
    public static HttpURLConnection a(String str) {
        try {
            HttpURLConnection c = c(str);
            c.connect();
            if (HttpCookie.domainMatches(h.h, c.getURL().getHost())) {
                b(c);
                c.a();
            }
            if (c.getResponseCode() / 10 == 30) {
                c = a(c.getHeaderField(e.e));
            }
            return c;
        } catch (Exception e) {
            Log.e(i.f, "", e);
            return null;
        }
    }

    private static HttpURLConnection b(String str) {
        try {
            HttpURLConnection c = c(str);
            c.setInstanceFollowRedirects(false);
            c.connect();
            if (HttpCookie.domainMatches(h.h, c.getURL().getHost())) {
                b(c);
                c.a();
            }
            return c;
        } catch (Exception e) {
            com.til.colombia.android.internal.Log.a(i.f, "", e);
            return null;
        }
    }

    private static HttpURLConnection c(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(d.a(str)).openConnection();
        String str2 = e.c;
        h.i();
        httpURLConnection.setRequestProperty(str2, h.j());
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setUseCaches(false);
        if (HttpCookie.domainMatches(h.h, httpURLConnection.getURL().getHost()) && com.til.colombia.android.internal.a.b().getCookies().size() >= 0) {
            httpURLConnection.setRequestProperty("Cookie", TextUtils.join(";", com.til.colombia.android.internal.a.b().getCookies()));
        }
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private static void a(HttpURLConnection httpURLConnection) {
        if (HttpCookie.domainMatches(h.h, httpURLConnection.getURL().getHost()) && com.til.colombia.android.internal.a.b().getCookies().size() >= 0) {
            httpURLConnection.setRequestProperty("Cookie", TextUtils.join(";", com.til.colombia.android.internal.a.b().getCookies()));
        }
    }

    private static void b(HttpURLConnection httpURLConnection) {
        List<String> list = (List) httpURLConnection.getHeaderFields().get(h.i);
        if (list != null) {
            for (String parse : list) {
                HttpCookie httpCookie = (HttpCookie) HttpCookie.parse(parse).get(0);
                if (!(httpCookie == null || httpCookie.getDomain() == null)) {
                    com.til.colombia.android.internal.a.b().add(null, httpCookie);
                }
            }
        }
    }
}
