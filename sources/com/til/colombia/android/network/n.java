package com.til.colombia.android.network;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.a.c;
import com.til.colombia.android.internal.h;
import com.til.colombia.android.internal.i;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.util.Collection;

public final class n {
    public static void a(Context context, Uri uri) throws Exception {
        if (uri != null) {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!(context instanceof Activity)) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            intent.setPackage(CommonUtil.b(context));
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
            } catch (Exception e) {
                Log.a(i.f, "", e);
                throw new Exception(e);
            }
        }
    }

    public static void a(Collection<String> collection, int i, String str) {
        if (collection != null && collection.size() != 0) {
            for (String a : collection) {
                l.a(a, i, new o(str));
            }
        }
    }

    public static void a(String str) {
        if (str != null) {
            HttpCookie httpCookie = (HttpCookie) HttpCookie.parse(str).get(0);
            if (!(httpCookie == null || httpCookie.getDomain() == null)) {
                a.b().add(null, httpCookie);
            }
            c.a();
        }
    }

    public static void a(HttpURLConnection httpURLConnection) {
        if (HttpCookie.domainMatches(h.h, httpURLConnection.getURL().getHost()) && a.b().getCookies().size() >= 0) {
            httpURLConnection.setRequestProperty("Cookie", TextUtils.join(";", a.b().getCookies()));
        }
    }
}
