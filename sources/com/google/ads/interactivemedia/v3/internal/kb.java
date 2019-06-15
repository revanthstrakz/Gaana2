package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;

public class kb {
    private static final String[] e = new String[]{"/aclk", "/pcs/click"};
    private String a = "googleads.g.doubleclick.net";
    private String b = "/pagead/ads";
    private String c = "ad.doubleclick.net";
    private String[] d = new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private jx f;

    public kb(jx jxVar) {
        this.f = jxVar;
    }

    public boolean a(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.c);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public boolean b(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String endsWith : this.d) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public jx a() {
        return this.f;
    }

    public Uri a(Uri uri, Context context) throws kc {
        return a(uri, context, null, false);
    }

    private Uri a(Uri uri, String str, String str2) throws UnsupportedOperationException {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = uri2.indexOf("?adurl");
        }
        if (indexOf == -1) {
            return uri.buildUpon().appendQueryParameter(str, str2).build();
        }
        indexOf++;
        StringBuilder stringBuilder = new StringBuilder(uri2.substring(0, indexOf));
        stringBuilder.append(str);
        stringBuilder.append("=");
        stringBuilder.append(str2);
        stringBuilder.append("&");
        stringBuilder.append(uri2.substring(indexOf));
        return Uri.parse(stringBuilder.toString());
    }

    private Uri b(Uri uri, String str, String str2) {
        String uri2 = uri.toString();
        int indexOf = uri2.indexOf(";adurl");
        if (indexOf != -1) {
            indexOf++;
            StringBuilder stringBuilder = new StringBuilder(uri2.substring(0, indexOf));
            stringBuilder.append(str);
            stringBuilder.append("=");
            stringBuilder.append(str2);
            stringBuilder.append(";");
            stringBuilder.append(uri2.substring(indexOf));
            return Uri.parse(stringBuilder.toString());
        }
        String encodedPath = uri.getEncodedPath();
        indexOf = uri2.indexOf(encodedPath);
        StringBuilder stringBuilder2 = new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf));
        stringBuilder2.append(";");
        stringBuilder2.append(str);
        stringBuilder2.append("=");
        stringBuilder2.append(str2);
        stringBuilder2.append(";");
        stringBuilder2.append(uri2.substring(indexOf + encodedPath.length()));
        return Uri.parse(stringBuilder2.toString());
    }

    private Uri a(Uri uri, Context context, String str, boolean z) throws kc {
        try {
            String a;
            boolean a2 = a(uri);
            if (a2) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new kc("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new kc("Query parameter already exists: ms");
            }
            if (z) {
                a = this.f.a(context, str);
            } else {
                a = this.f.a(context);
            }
            if (a2) {
                return b(uri, "dc_ms", a);
            }
            return a(uri, "ms", a);
        } catch (UnsupportedOperationException unused) {
            throw new kc("Provided Uri is not in a valid state");
        }
    }
}
