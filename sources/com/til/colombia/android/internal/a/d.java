package com.til.colombia.android.internal.a;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

public final class d {
    private static final String a = "Col:aos:4.0.0RequestUtil";

    public static String a(String str) {
        try {
            return b(str);
        } catch (Exception unused) {
            return str;
        }
    }

    public static String b(String str) throws Exception {
        if (str == null) {
            return null;
        }
        if (c(str)) {
            StringBuilder stringBuilder = new StringBuilder("URL is improperly encoded: ");
            stringBuilder.append(str);
            throw new UnsupportedEncodingException(stringBuilder.toString());
        }
        URI e;
        if (d(str)) {
            e = e(str);
        } else {
            e = new URI(str);
        }
        return e.toURL().toString();
    }

    private static boolean c(String str) {
        try {
            URLDecoder.decode(str, "UTF-8");
            return false;
        } catch (UnsupportedEncodingException unused) {
            String str2 = i.f;
            StringBuilder stringBuilder = new StringBuilder("Url is improperly encoded: ");
            stringBuilder.append(str);
            Log.b(str2, stringBuilder.toString());
            return true;
        }
    }

    private static boolean d(String str) {
        try {
            URI uri = new URI(str);
            return false;
        } catch (URISyntaxException unused) {
            return true;
        }
    }

    private static URI e(String str) throws Exception {
        try {
            URL url = new URL(str);
            return new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } catch (Exception e) {
            String str2 = i.f;
            StringBuilder stringBuilder = new StringBuilder("Failed to encode url: ");
            stringBuilder.append(str);
            Log.b(str2, stringBuilder.toString());
            throw e;
        }
    }
}
