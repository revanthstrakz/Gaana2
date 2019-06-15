package com.facebook.ads.internal.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.l.a.a;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.c.g;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends h {
    private static final String e = "f";
    private final Uri f;
    private final Map<String, String> g;
    private final boolean h;

    public f(Context context, c cVar, String str, Uri uri, Map<String, String> map, l lVar, boolean z) {
        super(context, cVar, str, lVar);
        this.f = uri;
        this.g = map;
        this.h = z;
    }

    private Intent a(g gVar) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        if (!(TextUtils.isEmpty(gVar.a()) || TextUtils.isEmpty(gVar.b()))) {
            intent.setComponent(new ComponentName(gVar.a(), gVar.b()));
        }
        if (!TextUtils.isEmpty(gVar.c())) {
            intent.setData(Uri.parse(gVar.c()));
        }
        return intent;
    }

    private Intent b(g gVar) {
        if (TextUtils.isEmpty(gVar.a()) || !e.a(this.a, gVar.a())) {
            return null;
        }
        String c = gVar.c();
        if (!TextUtils.isEmpty(c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (TextUtils.isEmpty(gVar.b()) && TextUtils.isEmpty(c)) {
            return packageManager.getLaunchIntentForPackage(gVar.a());
        }
        Intent a = a(gVar);
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(a, 65536);
        if (a.getComponent() == null) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(gVar.a())) {
                    a.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    break;
                }
            }
        }
        return (queryIntentActivities.isEmpty() || a.getComponent() == null) ? null : a;
    }

    private List<g> g() {
        String queryParameter = this.f.getQueryParameter("appsite_data");
        if (TextUtils.isEmpty(queryParameter) || "[]".equals(queryParameter)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray("android");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    g a = g.a(optJSONArray.optJSONObject(i));
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
        } catch (JSONException e) {
            Log.w(e, "Error parsing appsite_data", e);
        }
        return arrayList;
    }

    private boolean h() {
        List<Intent> e = e();
        if (e == null) {
            return false;
        }
        for (Intent startActivity : e) {
            try {
                this.a.startActivity(startActivity);
                return true;
            } catch (Exception e2) {
                Log.d(e, "Failed to open app intent, falling back", e2);
            }
        }
        return false;
    }

    private boolean i() {
        g gVar = new g();
        try {
            g.a(gVar, this.a, d(), this.c);
            return true;
        } catch (Exception e) {
            String str = e;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to open market url: ");
            stringBuilder.append(this.f.toString());
            Log.d(str, stringBuilder.toString(), e);
            String queryParameter = this.f.getQueryParameter("store_url_web_fallback");
            if (queryParameter != null && queryParameter.length() > 0) {
                g.a(gVar, this.a, Uri.parse(queryParameter), this.c);
            }
            return false;
        }
    }

    public a a() {
        return a.OPEN_STORE;
    }

    @Nullable
    public a c() {
        Object obj = "opened_deeplink";
        a aVar = null;
        if (!h()) {
            try {
                obj = i() ? "opened_store_url" : "opened_store_fallback_url";
            } catch (Exception unused) {
                Log.d(e, "Failed to open all options including fallback url, can't open anything");
                aVar = a.CANNOT_OPEN;
            }
        }
        this.g.put(obj, String.valueOf(true));
        return aVar;
    }

    /* Access modifiers changed, original: protected */
    public Uri d() {
        String queryParameter = this.f.getQueryParameter("store_url");
        if (!TextUtils.isEmpty(queryParameter)) {
            return Uri.parse(queryParameter);
        }
        queryParameter = this.f.getQueryParameter("store_id");
        return Uri.parse(String.format(Locale.US, "market://details?id=%s", new Object[]{queryParameter}));
    }

    /* Access modifiers changed, original: protected */
    public List<Intent> e() {
        List<g> g = g();
        ArrayList arrayList = new ArrayList();
        if (g != null) {
            for (g b : g) {
                Intent b2 = b(b);
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        a aVar;
        if (this.h) {
            this.g.put("opened_store_url", String.valueOf(true));
            aVar = null;
        } else {
            aVar = c();
        }
        a(this.g, aVar);
    }
}
