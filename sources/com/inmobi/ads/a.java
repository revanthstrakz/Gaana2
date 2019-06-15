package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.c.j;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.a.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static final String l = "a";
    final int a;
    final String b;
    final String c;
    final long d;
    long e;
    long f;
    public final String g;
    String h;
    String i;
    boolean j;
    public final float k;
    private final String m;
    private String n;
    private MonetizationContext o;
    @Nullable
    private final String p;

    static final class a {
        @Nullable
        static a a(JSONObject jSONObject, long j, String str, String str2, String str3, MonetizationContext monetizationContext, r rVar) {
            Exception exception;
            Exception e;
            a aVar;
            JSONObject jSONObject2 = jSONObject;
            r rVar2 = rVar;
            String str4 = null;
            try {
                long j2;
                String string = jSONObject2.getString("markupType");
                long optLong = jSONObject2.optLong("expiry", -1);
                if (-1 == optLong || optLong <= 0) {
                    j2 = -1;
                } else {
                    try {
                        j2 = TimeUnit.SECONDS.toMillis(optLong);
                    } catch (Exception e2) {
                        exception = e2;
                        aVar = str4;
                    }
                }
                String string2 = jSONObject2.getString("impressionId");
                float parseFloat = Float.parseFloat(d.a(b.a(Base64.decode(jSONObject2.getString("bid"), 0), rVar2.b, rVar2.a)));
                String a = rVar2.a(jSONObject2.optString("bidInfoEncrypted", null));
                int i = -1;
                int hashCode = string.hashCode();
                if (hashCode != -1084172778) {
                    if (hashCode == 3213227) {
                        if (string.equals("html")) {
                            i = 1;
                        }
                    }
                } else if (string.equals("inmobiJson")) {
                    i = 2;
                }
                String str5;
                switch (i) {
                    case 1:
                        str5 = null;
                        return new a(jSONObject2, j, str, str2, string2, str3, monetizationContext, j2, parseFloat, a, (byte) 0);
                    case 2:
                        JSONObject jSONObject3 = new JSONObject(jSONObject2.getString("pubContent"));
                        a.l;
                        jSONObject3.toString();
                        HashMap hashMap;
                        if (!jSONObject3.isNull("rootContainer")) {
                            String b;
                            JSONObject jSONObject4 = jSONObject3.getJSONObject("rootContainer");
                            JSONArray jSONArray = new JSONArray();
                            for (String b2 : c(jSONObject4)) {
                                a.a(jSONArray, b2, 2);
                            }
                            for (String b22 : d(jSONObject4)) {
                                a.a(jSONArray, b22, 1);
                            }
                            String a2 = a(jSONObject4);
                            boolean b3 = b(jSONObject4);
                            if (a2.trim().length() != 0) {
                                try {
                                    com.inmobi.commons.core.configs.a cVar = new c();
                                    str4 = null;
                                    com.inmobi.commons.core.configs.b.a().a(cVar, null);
                                    bx a3 = new bu(cVar.m).a(a2);
                                    if (a3.f != 0) {
                                        a.l;
                                        hashMap = new HashMap();
                                        hashMap.put("errorCode", String.valueOf(a3.f));
                                        hashMap.put("reason", "Processing VAST XML to build a video descriptor failed");
                                        hashMap.put("latency", "0");
                                        com.inmobi.commons.core.e.b.a();
                                        com.inmobi.commons.core.e.b.a("ads", "VastProcessingError", hashMap);
                                        return null;
                                    }
                                    HashMap hashMap2 = new HashMap();
                                    hashMap2.put("message", "VAST PROCESSING SUCCESS");
                                    com.inmobi.commons.core.e.b.a();
                                    com.inmobi.commons.core.e.b.a("ads", "VastProcessingSuccess", hashMap2);
                                    List<NativeTracker> list = a3.d;
                                    JSONArray jSONArray2 = new JSONArray();
                                    for (NativeTracker nativeTracker : list) {
                                        jSONArray2.put(nativeTracker.toString());
                                    }
                                    List<bt> list2 = a3.e;
                                    JSONArray jSONArray3 = new JSONArray();
                                    for (bt btVar : list2) {
                                        jSONArray3.put(btVar.toString());
                                    }
                                    b22 = a3.b();
                                    if (b22 != null) {
                                        if (!b22.isEmpty()) {
                                            a.a(jSONArray, b22, 0);
                                            List<String> a4 = a(jSONObject3, cVar.m);
                                            a.l;
                                            new StringBuilder("Media size for pages").append(a4.size());
                                            for (String b222 : a4) {
                                                a.a(jSONArray, b222, 0);
                                            }
                                            for (String a5 : a(jSONObject3, "pages")) {
                                                a.a(jSONArray, a5, 2);
                                            }
                                            for (String string3 : b(jSONObject3, "pages")) {
                                                a.a(jSONArray, string3, 1);
                                            }
                                            string3 = jSONArray.toString();
                                            b222 = a3.b();
                                            String str6 = a3.b;
                                            String str7 = a3.c;
                                            str5 = jSONArray2.toString();
                                            bc bcVar = bcVar;
                                            String str8 = str6;
                                            str6 = str5;
                                            aVar = null;
                                            try {
                                                return new bc(jSONObject2, string3, j, str, str2, string2, str3, b222, str8, str7, str6, jSONArray3.toString(), monetizationContext, b3, j2, parseFloat, a);
                                            } catch (Exception e3) {
                                                e2 = e3;
                                                break;
                                            }
                                        }
                                    }
                                    aVar = null;
                                    hashMap = new HashMap();
                                    hashMap.put("errorCode", "ZERO LENGTH ASSET");
                                    hashMap.put("reason", "Asset length is 0");
                                    com.inmobi.commons.core.e.b.a();
                                    com.inmobi.commons.core.e.b.a("ads", "ServerError", hashMap);
                                    a.l;
                                    return aVar;
                                } catch (Exception e4) {
                                    e2 = e4;
                                    aVar = null;
                                    break;
                                }
                            }
                            a.l;
                            a aVar2 = aVar2;
                            try {
                                return new a(jSONObject2, jSONArray.toString(), j, str, str2, string2, str3, monetizationContext, b3, j2, parseFloat, a);
                            } catch (Exception e22) {
                                exception = e22;
                                aVar = null;
                                break;
                            }
                        }
                        a.l;
                        try {
                            hashMap = new HashMap();
                            hashMap.put("errorCode", "MISSING rootContainer");
                            hashMap.put("reason", "Missing rootContainer ad markup");
                            com.inmobi.commons.core.e.b.a();
                            com.inmobi.commons.core.e.b.a("ads", "ServerError", hashMap);
                        } catch (Exception e222) {
                            exception = e222;
                            a.l;
                            StringBuilder stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                            stringBuilder.append(exception.getMessage());
                            stringBuilder.append(")");
                        }
                        return null;
                    default:
                        return null;
                }
            } catch (Exception e5) {
                e222 = e5;
                aVar = str4;
            }
            exception = e222;
            a.l;
            new StringBuilder("Error parsing ad markup; ").append(exception.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(exception));
            return aVar;
        }

        private static List<String> a(JSONObject jSONObject, j jVar) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("pages");
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        String a = a(jSONArray.getJSONObject(i).getJSONObject("rootContainer"));
                        if (a.trim().length() == 0) {
                            a.l;
                        } else {
                            bx a2 = new bu(jVar).a(a);
                            if (a2 != null) {
                                if (a2.f == 0) {
                                    a = a2.b();
                                    if (!(a == null || a.isEmpty())) {
                                        arrayList.add(a);
                                    }
                                }
                            }
                            a.l;
                        }
                    }
                }
            } catch (JSONException unused) {
                a.l;
            }
            return arrayList;
        }

        private static List<String> a(JSONObject jSONObject, String str) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        arrayList.addAll(c(jSONArray.getJSONObject(i).getJSONObject("rootContainer")));
                    }
                }
            } catch (JSONException unused) {
                a.l;
            }
            return arrayList;
        }

        private static List<String> b(JSONObject jSONObject, String str) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        arrayList.addAll(d(jSONArray.getJSONObject(i).getJSONObject("rootContainer")));
                    }
                }
            } catch (JSONException unused) {
                a.l;
            }
            return arrayList;
        }

        static a a(ContentValues contentValues) {
            if (!contentValues.containsKey("video_url") || contentValues.getAsString("video_url") == null || contentValues.getAsString("video_url").isEmpty()) {
                return new a(contentValues);
            }
            return new bc(contentValues);
        }

        @NonNull
        private static String a(@NonNull JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return "";
                }
                String string = jSONObject.getString("assetType");
                int i = 0;
                if (string.equalsIgnoreCase("video")) {
                    return jSONArray.getString(0);
                }
                if (!string.equalsIgnoreCase("container")) {
                    return "";
                }
                string = "";
                while (i < jSONArray.length()) {
                    string = a(jSONArray.getJSONObject(i));
                    if (string.trim().length() != 0) {
                        break;
                    }
                    i++;
                }
                return string;
            } catch (JSONException e) {
                a.l;
                StringBuilder stringBuilder = new StringBuilder("Error getting VAST video XML (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return "";
            }
        }

        private static boolean b(@NonNull JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return false;
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("webview")) {
                    if (jSONObject.isNull("preload") || !jSONObject.getBoolean("preload")) {
                        return false;
                    }
                    return true;
                } else if (!string.equalsIgnoreCase("container")) {
                    return false;
                } else {
                    int i = 0;
                    boolean z = i;
                    while (i < jSONArray.length()) {
                        z = b(jSONArray.getJSONObject(i));
                        if (z) {
                            break;
                        }
                        i++;
                    }
                    return z;
                }
            } catch (JSONException e) {
                a.l;
                StringBuilder stringBuilder = new StringBuilder("Error getting preload webview flag (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return false;
            }
        }

        @NonNull
        private static List<String> c(@NonNull JSONObject jSONObject) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                String string = jSONObject.getString("assetType");
                int i = 0;
                if (string.equalsIgnoreCase(TtmlNode.TAG_IMAGE)) {
                    if (!jSONObject.isNull("preload") && jSONObject.getBoolean("preload")) {
                        arrayList.add(jSONArray.getString(0));
                    }
                    return arrayList;
                } else if (!string.equalsIgnoreCase("container")) {
                    return arrayList;
                } else {
                    while (i < jSONArray.length()) {
                        arrayList.addAll(c(jSONArray.getJSONObject(i)));
                        i++;
                    }
                    return arrayList;
                }
            } catch (JSONException e) {
                a.l;
                StringBuilder stringBuilder = new StringBuilder("Error getting getImageAssetUrls (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
                return arrayList;
            }
        }

        @NonNull
        private static List<String> d(@NonNull JSONObject jSONObject) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                String string = jSONObject.getString("assetType");
                int i = 0;
                if (string.equalsIgnoreCase("gif")) {
                    arrayList.add(jSONArray.getString(0));
                    return arrayList;
                } else if (!string.equalsIgnoreCase("container")) {
                    return arrayList;
                } else {
                    while (i < jSONArray.length()) {
                        arrayList.addAll(d(jSONArray.getJSONObject(i)));
                        i++;
                    }
                    return arrayList;
                }
            } catch (JSONException e) {
                a.l;
                StringBuilder stringBuilder = new StringBuilder("Error getting getGifAssetUrls (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
                return arrayList;
            }
        }
    }

    /* synthetic */ a(JSONObject jSONObject, long j, String str, String str2, String str3, String str4, MonetizationContext monetizationContext, long j2, float f, String str5, byte b) {
        this(jSONObject, j, str, str2, str3, str4, monetizationContext, j2, f, str5);
    }

    private a(JSONObject jSONObject, long j, String str, String str2, String str3, String str4, MonetizationContext monetizationContext, long j2, float f, @Nullable String str5) {
        this(jSONObject, null, j, str, str2, str3, str4, monetizationContext, false, j2, f, str5);
    }

    a(JSONObject jSONObject, String str, long j, String str2, String str3, String str4, String str5, MonetizationContext monetizationContext, boolean z, long j2, float f, @Nullable String str6) {
        this.a = -1;
        this.c = jSONObject.toString();
        this.n = str;
        this.d = j;
        this.b = str2;
        this.m = str3;
        this.e = System.currentTimeMillis();
        this.g = str4;
        this.h = str5;
        this.o = monetizationContext;
        this.i = "";
        this.j = z;
        this.f = j2;
        this.k = f;
        this.p = str6;
    }

    a(ContentValues contentValues) {
        this.a = contentValues.getAsInteger("id").intValue();
        this.b = contentValues.getAsString("ad_type");
        this.m = contentValues.getAsString("ad_size");
        this.n = contentValues.getAsString("asset_urls");
        this.c = contentValues.getAsString("ad_content");
        this.d = contentValues.getAsLong("placement_id").longValue();
        this.e = contentValues.getAsLong("insertion_ts").longValue();
        this.f = contentValues.getAsLong("expiry_duration").longValue();
        this.g = contentValues.getAsString("imp_id");
        this.h = contentValues.getAsString("client_request_id");
        this.o = MonetizationContext.a(contentValues.getAsString("m10_context"));
        if (this.o == null) {
            this.o = MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        }
        this.i = contentValues.getAsString("web_vast");
        this.j = contentValues.getAsInteger("preload_webView").intValue() != 0;
        this.k = contentValues.getAsFloat("bid").floatValue();
        this.p = contentValues.getAsString("bidInfo");
    }

    public ContentValues a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad_type", this.b);
        contentValues.put("ad_size", this.m);
        contentValues.put("asset_urls", this.n);
        contentValues.put("ad_content", this.c);
        contentValues.put("placement_id", Long.valueOf(this.d));
        contentValues.put("insertion_ts", Long.valueOf(this.e));
        contentValues.put("expiry_duration", Long.valueOf(this.f));
        contentValues.put("imp_id", this.g);
        contentValues.put("client_request_id", this.h);
        contentValues.put("m10_context", this.o.a);
        if (this.i != null) {
            contentValues.put("web_vast", this.i);
        }
        contentValues.put("preload_webView", Integer.valueOf(this.j));
        contentValues.put("bid", Float.valueOf(this.k));
        contentValues.put("bidInfo", this.p);
        return contentValues;
    }

    @NonNull
    public final JSONObject b() {
        try {
            return this.p == null ? new JSONObject() : new JSONObject(this.p);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    /* Access modifiers changed, original: final */
    public final long c() {
        if (this.f == -1) {
            return -1;
        }
        return this.e + this.f;
    }

    @NonNull
    public final Set<bm> d() {
        HashSet hashSet = new HashSet();
        if (this.n == null || this.n.length() == 0) {
            return hashSet;
        }
        try {
            JSONArray jSONArray = new JSONArray(this.n);
            if (jSONArray.length() == 0) {
                return hashSet;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = new JSONObject(jSONArray.getString(i));
                int i2 = jSONObject.getInt("type");
                String string = jSONObject.getString("url");
                if (string != null) {
                    hashSet.add(new bm(i2, string));
                }
            }
            return hashSet;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return hashSet;
        }
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final String e() {
        try {
            JSONObject jSONObject = new JSONObject(this.c);
            if (jSONObject.isNull("markupType")) {
                return "";
            }
            return jSONObject.getString("markupType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    /* Access modifiers changed, original: final */
    public final boolean a(@NonNull a aVar) {
        return d().equals(aVar.d());
    }

    @Nullable
    public final JSONObject f() {
        try {
            JSONObject jSONObject = new JSONObject(this.c);
            if (jSONObject.has("cachedAdData")) {
                return jSONObject.getJSONObject("cachedAdData");
            }
            return null;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }

    static /* synthetic */ void a(JSONArray jSONArray, String str, int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", i);
        jSONObject.put("url", str);
        jSONArray.put(jSONObject);
    }
}
