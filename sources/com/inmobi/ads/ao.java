package com.inmobi.ads;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.URLUtil;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.google.android.exoplayer2.util.MimeTypes;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.utilities.b.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ao {
    private static final String l = "ao";
    int a;
    public boolean b;
    public boolean c;
    public am d;
    JSONArray e;
    final ao f;
    @Nullable
    Map<String, String> g;
    Map<String, List<ak>> h;
    a i;
    boolean j;
    bf k;
    private JSONObject m;
    private JSONObject n;
    private Map<String, ak> o;
    private Map<String, String> p;
    @Nullable
    private bx q;
    private c r;
    private PlacementType s;
    private boolean t;

    class a {
        JSONObject a;
        @NonNull
        a b = new a();
        ak c;

        class a {
            public String a;
            public String b;
            public String c;
            public String d;
            public float e;
            public String f;
            public boolean g;

            a() {
            }
        }

        a() {
        }
    }

    ao() {
        this.f = null;
    }

    public ao(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable c cVar, @Nullable bx bxVar) {
        this(placementType, jSONObject, null, false, cVar, bxVar);
    }

    public ao(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable ao aoVar, boolean z, @Nullable c cVar, @Nullable bx bxVar) {
        this(placementType, jSONObject, aoVar, z, cVar, bxVar, (byte) 0);
    }

    private ao(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable ao aoVar, boolean z, @Nullable c cVar, @Nullable bx bxVar, byte b) {
        this.s = placementType;
        this.f = aoVar;
        if (cVar == null) {
            cVar = new c();
        }
        this.r = cVar;
        this.m = jSONObject;
        this.a = 0;
        this.b = false;
        this.q = bxVar;
        this.o = new HashMap();
        this.p = new HashMap();
        this.h = new HashMap();
        this.i = new a();
        this.t = z;
        f();
    }

    /* Access modifiers changed, original: final */
    public final JSONObject a() {
        try {
            return this.e.getJSONObject(0);
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }

    /* Access modifiers changed, original: final */
    public final int b() {
        if (this.d == null) {
            return 0;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ak akVar = (ak) it.next();
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                return ((am) akVar).C;
            }
        }
        return 0;
    }

    /* Access modifiers changed, original: final */
    public final am a(int i) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ak akVar = (ak) it.next();
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                am amVar = (am) akVar;
                if (i >= amVar.C) {
                    return null;
                }
                return (am) amVar.a(i);
            }
        }
        return null;
    }

    static am a(@NonNull ak akVar) {
        if (akVar instanceof am) {
            am amVar = (am) akVar;
            if (a(amVar)) {
                return amVar;
            }
        }
        for (am amVar2 = (am) akVar.t; amVar2 != null; amVar2 = (am) amVar2.t) {
            if (a(amVar2)) {
                return amVar2;
            }
        }
        return null;
    }

    private void d() {
        for (ak akVar : c(ShareConstants.IMAGE_URL)) {
            if (!URLUtil.isValidUrl((String) akVar.e)) {
                ak a = a(this, akVar);
                if (a == null) {
                    StringBuilder stringBuilder = new StringBuilder("Could not find referenced asset for asset (");
                    stringBuilder.append(akVar.d);
                    stringBuilder.append(")");
                } else if (a.b.equals(akVar.b)) {
                    akVar.e = a.e;
                } else if (ShareConstants.VIDEO_URL.equals(a.b) && 1 != a.m && 2 == a.m) {
                    be beVar = (be) a;
                    by b = beVar.b();
                    bt a2 = bs.a(beVar, akVar);
                    List list;
                    if (a2 == null) {
                        list = null;
                    } else {
                        list = a2.a(1);
                    }
                    if (list != null) {
                        for (a aVar : list) {
                            if (URLUtil.isValidUrl(aVar.b)) {
                                break;
                            }
                        }
                    }
                    a aVar2 = null;
                    if (a2 == null || aVar2 == null) {
                        a(beVar);
                        a(a2 == null ? "NoBestFitCompanion" : "NoValidResource", "Static", "URL", null, null);
                    } else {
                        b.a(a2);
                        new StringBuilder("Setting image asset value: ").append(aVar2.b);
                        akVar.e = aVar2.b;
                        akVar.a(a2.a(TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW));
                        akVar.a(beVar.u, TrackerEventType.TRACKER_EVENT_TYPE_ERROR);
                    }
                }
            }
        }
    }

    private static void a(String str, String str2, String str3, String str4, String str5) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", str);
            hashMap.put("type", str2);
            hashMap.put("dataType", str3);
            hashMap.put("clientRequestId", null);
            hashMap.put("impId", null);
            b.a();
            b.a("ads", "EndCardCompanionFailure", hashMap);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("Error in sendTelemetryEventForCompanionFailure : (");
            stringBuilder.append(e.getMessage());
            stringBuilder.append(")");
        }
    }

    private void e() {
        for (ak akVar : c("WEBVIEW")) {
            bf bfVar = (bf) akVar;
            if (!("URL".equals(bfVar.z) || "HTML".equals(bfVar.z))) {
                ak a = a(this, akVar);
                if (a == null) {
                    StringBuilder stringBuilder = new StringBuilder("Could not find referenced asset for asset (");
                    stringBuilder.append(akVar.d);
                    stringBuilder.append(")");
                } else if (a.b.equals(akVar.b)) {
                    akVar.e = a.e;
                } else if (ShareConstants.VIDEO_URL.equals(a.b) && 2 == a.m) {
                    String str;
                    be beVar = (be) a;
                    by b = beVar.b();
                    bt a2 = bs.a(beVar, akVar);
                    if (a2 == null) {
                        str = null;
                    } else {
                        str = a(a2, bfVar);
                    }
                    boolean equals = "REF_IFRAME".equals(bfVar.z);
                    boolean equals2 = "REF_HTML".equals(bfVar.z);
                    if (a2 == null || ((equals && !URLUtil.isValidUrl(str)) || (equals2 && str == null))) {
                        a(beVar);
                        a(a2 == null ? "NoBestFitCompanion" : "NoValidResource", "Rich", bfVar.z, null, null);
                        bfVar.z = CARD.UNKNOWN;
                    } else {
                        b.a(a2);
                        akVar.e = str;
                        akVar.a(a2.a(TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW));
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x015e A:{Catch:{ JSONException -> 0x018b }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x0156 */
    /* JADX WARNING: Can't wrap try/catch for region: R(10:36|37|38|(1:40)(1:41)|42|(1:44)|45|46|47|(1:49)) */
    private void f() {
        /*
        r11 = this;
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r1 = "styleRefs";
        r0 = r0.optJSONObject(r1);	 Catch:{ JSONException -> 0x02a1 }
        r11.n = r0;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r1 = "orientation";
        r0 = r0.isNull(r1);	 Catch:{ JSONException -> 0x02a1 }
        r1 = 3;
        r2 = 2;
        r3 = 0;
        r4 = 1;
        if (r0 == 0) goto L_0x001a;
    L_0x0018:
        r0 = r3;
        goto L_0x0065;
    L_0x001a:
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "orientation";
        r0 = r0.getString(r5);	 Catch:{ JSONException -> 0x02a1 }
        r5 = java.util.Locale.US;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r0.toLowerCase(r5);	 Catch:{ JSONException -> 0x02a1 }
        r0 = r0.trim();	 Catch:{ JSONException -> 0x02a1 }
        r5 = -1;
        r6 = r0.hashCode();	 Catch:{ JSONException -> 0x02a1 }
        r7 = -1626174665; // 0xffffffff9f128b37 float:-3.103186E-20 double:NaN;
        if (r6 == r7) goto L_0x0055;
    L_0x0036:
        r7 = 729267099; // 0x2b77bb9b float:8.8012383E-13 double:3.603058203E-315;
        if (r6 == r7) goto L_0x004b;
    L_0x003b:
        r7 = 1430647483; // 0x5545f2bb float:1.36028944E13 double:7.068337727E-315;
        if (r6 == r7) goto L_0x0041;
    L_0x0040:
        goto L_0x005e;
    L_0x0041:
        r6 = "landscape";
        r0 = r0.equals(r6);	 Catch:{ JSONException -> 0x02a1 }
        if (r0 == 0) goto L_0x005e;
    L_0x0049:
        r5 = r1;
        goto L_0x005e;
    L_0x004b:
        r6 = "portrait";
        r0 = r0.equals(r6);	 Catch:{ JSONException -> 0x02a1 }
        if (r0 == 0) goto L_0x005e;
    L_0x0053:
        r5 = r2;
        goto L_0x005e;
    L_0x0055:
        r6 = "unspecified";
        r0 = r0.equals(r6);	 Catch:{ JSONException -> 0x02a1 }
        if (r0 == 0) goto L_0x005e;
    L_0x005d:
        r5 = r4;
    L_0x005e:
        switch(r5) {
            case 2: goto L_0x0064;
            case 3: goto L_0x0062;
            default: goto L_0x0061;
        };	 Catch:{ JSONException -> 0x02a1 }
    L_0x0061:
        goto L_0x0018;
    L_0x0062:
        r0 = r2;
        goto L_0x0065;
    L_0x0064:
        r0 = r4;
    L_0x0065:
        r11.a = r0;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "shouldAutoOpenLandingPage";
        r0 = r0.optBoolean(r5, r4);	 Catch:{ JSONException -> 0x02a1 }
        r11.j = r0;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "disableBackButton";
        r0 = r0.optBoolean(r5, r3);	 Catch:{ JSONException -> 0x02a1 }
        r11.b = r0;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "rootContainer";
        r0 = r0.getJSONObject(r5);	 Catch:{ JSONException -> 0x02a1 }
        r5 = "CONTAINER";
        r6 = "/rootContainer";
        r7 = 0;
        r0 = r11.a(r0, r5, r6, r7);	 Catch:{ JSONException -> 0x02a1 }
        r0 = (com.inmobi.ads.am) r0;	 Catch:{ JSONException -> 0x02a1 }
        r11.d = r0;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r5 = "passThroughJson";
        r0 = r0.isNull(r5);	 Catch:{ JSONException -> 0x018b }
        if (r0 != 0) goto L_0x00a6;
    L_0x009a:
        r0 = r11.i;	 Catch:{ JSONException -> 0x018b }
        r5 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r6 = "passThroughJson";
        r5 = r5.getJSONObject(r6);	 Catch:{ JSONException -> 0x018b }
        r0.a = r5;	 Catch:{ JSONException -> 0x018b }
    L_0x00a6:
        r0 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r5 = "adContent";
        r0 = r0.isNull(r5);	 Catch:{ JSONException -> 0x018b }
        if (r0 != 0) goto L_0x0103;
    L_0x00b0:
        r0 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r5 = "adContent";
        r0 = r0.getJSONObject(r5);	 Catch:{ JSONException -> 0x018b }
        if (r0 == 0) goto L_0x0103;
    L_0x00ba:
        r5 = new com.inmobi.ads.ao$a$a;	 Catch:{ JSONException -> 0x018b }
        r6 = r11.i;	 Catch:{ JSONException -> 0x018b }
        r6.getClass();	 Catch:{ JSONException -> 0x018b }
        r5.<init>();	 Catch:{ JSONException -> 0x018b }
        r6 = "title";
        r6 = r0.optString(r6, r7);	 Catch:{ JSONException -> 0x018b }
        r5.a = r6;	 Catch:{ JSONException -> 0x018b }
        r6 = "description";
        r6 = r0.optString(r6, r7);	 Catch:{ JSONException -> 0x018b }
        r5.b = r6;	 Catch:{ JSONException -> 0x018b }
        r6 = "ctaText";
        r6 = r0.optString(r6, r7);	 Catch:{ JSONException -> 0x018b }
        r5.d = r6;	 Catch:{ JSONException -> 0x018b }
        r6 = "iconUrl";
        r6 = r0.optString(r6, r7);	 Catch:{ JSONException -> 0x018b }
        r5.c = r6;	 Catch:{ JSONException -> 0x018b }
        r6 = "rating";
        r8 = 0;
        r8 = r0.optLong(r6, r8);	 Catch:{ JSONException -> 0x018b }
        r6 = (float) r8;	 Catch:{ JSONException -> 0x018b }
        r5.e = r6;	 Catch:{ JSONException -> 0x018b }
        r6 = "landingPageUrl";
        r6 = r0.optString(r6, r7);	 Catch:{ JSONException -> 0x018b }
        r5.f = r6;	 Catch:{ JSONException -> 0x018b }
        r6 = "isApp";
        r0 = r0.optBoolean(r6);	 Catch:{ JSONException -> 0x018b }
        r5.g = r0;	 Catch:{ JSONException -> 0x018b }
        r0 = r11.i;	 Catch:{ JSONException -> 0x018b }
        r0.b = r5;	 Catch:{ JSONException -> 0x018b }
    L_0x0103:
        r0 = new com.inmobi.ads.ak;	 Catch:{ JSONException -> 0x018b }
        r0.<init>();	 Catch:{ JSONException -> 0x018b }
        r5 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r6 = "onClick";
        r5 = r5.isNull(r6);	 Catch:{ JSONException -> 0x018b }
        if (r5 != 0) goto L_0x0173;
    L_0x0112:
        r5 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r6 = "onClick";
        r5 = r5.getJSONObject(r6);	 Catch:{ JSONException -> 0x018b }
        r6 = "";
        r7 = "";
        r8 = "itemUrl";
        r8 = r5.isNull(r8);	 Catch:{ JSONException -> 0x0156 }
        if (r8 != 0) goto L_0x012e;
    L_0x0126:
        r6 = "itemUrl";
        r6 = r5.getString(r6);	 Catch:{ JSONException -> 0x0156 }
        r8 = r4;
        goto L_0x012f;
    L_0x012e:
        r8 = r3;
    L_0x012f:
        r9 = "action";
        r9 = r5.isNull(r9);	 Catch:{ JSONException -> 0x0156 }
        if (r9 != 0) goto L_0x013e;
    L_0x0137:
        r7 = "action";
        r7 = r5.getString(r7);	 Catch:{ JSONException -> 0x0156 }
        r8 = r4;
    L_0x013e:
        r0.a(r6);	 Catch:{ JSONException -> 0x0156 }
        r6 = "fallbackUrl";
        r6 = r5.optString(r6);	 Catch:{ JSONException -> 0x0156 }
        r0.b(r6);	 Catch:{ JSONException -> 0x0156 }
        r0.j = r7;	 Catch:{ JSONException -> 0x0156 }
        r0.h = r8;	 Catch:{ JSONException -> 0x0156 }
        r6 = "appBundleId";
        r6 = r5.optString(r6);	 Catch:{ JSONException -> 0x0156 }
        r0.w = r6;	 Catch:{ JSONException -> 0x0156 }
    L_0x0156:
        r6 = "openMode";
        r6 = r5.isNull(r6);	 Catch:{ JSONException -> 0x018b }
        if (r6 != 0) goto L_0x0173;
    L_0x015e:
        r6 = "openMode";
        r6 = r5.getString(r6);	 Catch:{ JSONException -> 0x018b }
        r6 = d(r6);	 Catch:{ JSONException -> 0x018b }
        r0.i = r6;	 Catch:{ JSONException -> 0x018b }
        r6 = "fallbackUrl";
        r5 = r5.optString(r6);	 Catch:{ JSONException -> 0x018b }
        r0.b(r5);	 Catch:{ JSONException -> 0x018b }
    L_0x0173:
        r5 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r6 = "trackers";
        r5 = r5.isNull(r6);	 Catch:{ JSONException -> 0x018b }
        if (r5 != 0) goto L_0x0186;
    L_0x017d:
        r5 = r11.m;	 Catch:{ JSONException -> 0x018b }
        r5 = b(r5);	 Catch:{ JSONException -> 0x018b }
        r0.a(r5);	 Catch:{ JSONException -> 0x018b }
    L_0x0186:
        r5 = r11.i;	 Catch:{ JSONException -> 0x018b }
        r5.c = r0;	 Catch:{ JSONException -> 0x018b }
        goto L_0x0198;
    L_0x018b:
        r0 = move-exception;
        r5 = com.inmobi.commons.core.a.a.a();	 Catch:{ JSONException -> 0x02a1 }
        r6 = new com.inmobi.commons.core.e.a;	 Catch:{ JSONException -> 0x02a1 }
        r6.<init>(r0);	 Catch:{ JSONException -> 0x02a1 }
        r5.a(r6);	 Catch:{ JSONException -> 0x02a1 }
    L_0x0198:
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "prefetchNextPage";
        r0 = r0.optBoolean(r5);	 Catch:{ JSONException -> 0x02a1 }
        r11.c = r0;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "rewards";
        r0 = r0.has(r5);	 Catch:{ JSONException -> 0x02a1 }
        if (r0 == 0) goto L_0x01b3;
    L_0x01ac:
        r0 = new java.util.HashMap;	 Catch:{ JSONException -> 0x02a1 }
        r0.<init>();	 Catch:{ JSONException -> 0x02a1 }
        r11.g = r0;	 Catch:{ JSONException -> 0x02a1 }
    L_0x01b3:
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "rewards";
        r0 = r0.isNull(r5);	 Catch:{ JSONException -> 0x02a1 }
        if (r0 != 0) goto L_0x01e1;
    L_0x01bd:
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r5 = "rewards";
        r0 = r0.getJSONObject(r5);	 Catch:{ JSONException -> 0x02a1 }
        if (r0 == 0) goto L_0x01e1;
    L_0x01c7:
        r5 = r0.keys();	 Catch:{ JSONException -> 0x02a1 }
    L_0x01cb:
        r6 = r5.hasNext();	 Catch:{ JSONException -> 0x02a1 }
        if (r6 == 0) goto L_0x01e1;
    L_0x01d1:
        r6 = r5.next();	 Catch:{ JSONException -> 0x02a1 }
        r6 = (java.lang.String) r6;	 Catch:{ JSONException -> 0x02a1 }
        r7 = r0.getString(r6);	 Catch:{ JSONException -> 0x02a1 }
        r8 = r11.g;	 Catch:{ JSONException -> 0x02a1 }
        r8.put(r6, r7);	 Catch:{ JSONException -> 0x02a1 }
        goto L_0x01cb;
    L_0x01e1:
        r11.d();	 Catch:{ JSONException -> 0x02a1 }
        r11.e();	 Catch:{ JSONException -> 0x02a1 }
        r0 = r11.p;	 Catch:{ JSONException -> 0x02a1 }
        r0 = r0.entrySet();	 Catch:{ JSONException -> 0x02a1 }
        r0 = r0.iterator();	 Catch:{ JSONException -> 0x02a1 }
    L_0x01f1:
        r5 = r0.hasNext();	 Catch:{ JSONException -> 0x02a1 }
        if (r5 == 0) goto L_0x0284;
    L_0x01f7:
        r5 = r0.next();	 Catch:{ JSONException -> 0x02a1 }
        r5 = (java.util.Map.Entry) r5;	 Catch:{ JSONException -> 0x02a1 }
        r6 = r5.getValue();	 Catch:{ JSONException -> 0x02a1 }
        r6 = (java.lang.String) r6;	 Catch:{ JSONException -> 0x02a1 }
        r7 = r11.o;	 Catch:{ JSONException -> 0x02a1 }
        r5 = r5.getKey();	 Catch:{ JSONException -> 0x02a1 }
        r5 = r7.get(r5);	 Catch:{ JSONException -> 0x02a1 }
        r5 = (com.inmobi.ads.ak) r5;	 Catch:{ JSONException -> 0x02a1 }
        r7 = r5.n;	 Catch:{ JSONException -> 0x02a1 }
        r8 = 4;
        if (r8 != r7) goto L_0x01f1;
    L_0x0214:
        r7 = r11.o;	 Catch:{ JSONException -> 0x02a1 }
        r6 = r7.get(r6);	 Catch:{ JSONException -> 0x02a1 }
        r6 = (com.inmobi.ads.ak) r6;	 Catch:{ JSONException -> 0x02a1 }
        r7 = "VIDEO";
        r9 = r6.b;	 Catch:{ JSONException -> 0x02a1 }
        r7 = r7.equals(r9);	 Catch:{ JSONException -> 0x02a1 }
        if (r7 == 0) goto L_0x01f1;
    L_0x0226:
        r7 = r6;
        r7 = (com.inmobi.ads.be) r7;	 Catch:{ JSONException -> 0x02a1 }
        r7 = r7.b();	 Catch:{ JSONException -> 0x02a1 }
        r7 = (com.inmobi.ads.bx) r7;	 Catch:{ JSONException -> 0x02a1 }
        r7 = r7.b;	 Catch:{ JSONException -> 0x02a1 }
        r9 = ":";
        r7 = r7.split(r9);	 Catch:{ JSONException -> 0x02a1 }
        r9 = r7[r4];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0247 }
        r9 = java.lang.Integer.parseInt(r9);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0247 }
        r9 = r9 * 60;
        r7 = r7[r2];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0247 }
        r7 = java.lang.Integer.parseInt(r7);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0247 }
        r7 = r7 + r9;
        goto L_0x0255;
    L_0x0247:
        r7 = move-exception;
        r9 = com.inmobi.commons.core.a.a.a();	 Catch:{ JSONException -> 0x02a1 }
        r10 = new com.inmobi.commons.core.e.a;	 Catch:{ JSONException -> 0x02a1 }
        r10.<init>(r7);	 Catch:{ JSONException -> 0x02a1 }
        r9.a(r10);	 Catch:{ JSONException -> 0x02a1 }
        r7 = r3;
    L_0x0255:
        if (r7 != 0) goto L_0x025c;
    L_0x0257:
        r7 = r7 / 4;
        r5.o = r7;	 Catch:{ JSONException -> 0x02a1 }
        goto L_0x027b;
    L_0x025c:
        r9 = r5.o;	 Catch:{ JSONException -> 0x02a1 }
        r10 = 50;
        if (r9 == r10) goto L_0x0277;
    L_0x0262:
        r10 = 75;
        if (r9 == r10) goto L_0x0272;
    L_0x0266:
        r8 = 100;
        if (r9 == r8) goto L_0x026f;
    L_0x026a:
        r7 = r7 / 4;
        r5.o = r7;	 Catch:{ JSONException -> 0x02a1 }
        goto L_0x027b;
    L_0x026f:
        r5.o = r7;	 Catch:{ JSONException -> 0x02a1 }
        goto L_0x027b;
    L_0x0272:
        r7 = r7 * r1;
        r7 = r7 / r8;
        r5.o = r7;	 Catch:{ JSONException -> 0x02a1 }
        goto L_0x027b;
    L_0x0277:
        r7 = r7 / 2;
        r5.o = r7;	 Catch:{ JSONException -> 0x02a1 }
    L_0x027b:
        r6 = (com.inmobi.ads.be) r6;	 Catch:{ JSONException -> 0x02a1 }
        r6 = r6.z;	 Catch:{ JSONException -> 0x02a1 }
        r6.add(r5);	 Catch:{ JSONException -> 0x02a1 }
        goto L_0x01f1;
    L_0x0284:
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r1 = "pages";
        r0 = r0.isNull(r1);	 Catch:{ JSONException -> 0x02a1 }
        if (r0 == 0) goto L_0x0296;
    L_0x028e:
        r0 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x02a1 }
        r0.<init>();	 Catch:{ JSONException -> 0x02a1 }
        r11.e = r0;	 Catch:{ JSONException -> 0x02a1 }
        return;
    L_0x0296:
        r0 = r11.m;	 Catch:{ JSONException -> 0x02a1 }
        r1 = "pages";
        r0 = r0.getJSONArray(r1);	 Catch:{ JSONException -> 0x02a1 }
        r11.e = r0;	 Catch:{ JSONException -> 0x02a1 }
        return;
    L_0x02a1:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.a.a.a();
        r2 = new com.inmobi.commons.core.e.a;
        r2.<init>(r0);
        r1.a(r2);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.f():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041 A:{RETURN} */
    static int a(java.lang.String r4) {
        /*
        r0 = java.util.Locale.US;
        r4 = r4.toLowerCase(r0);
        r4 = r4.trim();
        r0 = r4.hashCode();
        r1 = -1412832500; // 0xffffffffabc9e30c float:-1.4344927E-12 double:NaN;
        r2 = 1;
        r3 = 2;
        if (r0 == r1) goto L_0x0031;
    L_0x0015:
        if (r0 == 0) goto L_0x0027;
    L_0x0017:
        r1 = 112202875; // 0x6b0147b float:6.6233935E-35 double:5.5435586E-316;
        if (r0 == r1) goto L_0x001d;
    L_0x001c:
        goto L_0x003b;
    L_0x001d:
        r0 = "video";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x003b;
    L_0x0025:
        r4 = r3;
        goto L_0x003c;
    L_0x0027:
        r0 = "";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x003b;
    L_0x002f:
        r4 = r2;
        goto L_0x003c;
    L_0x0031:
        r0 = "companion";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x003b;
    L_0x0039:
        r4 = 3;
        goto L_0x003c;
    L_0x003b:
        r4 = -1;
    L_0x003c:
        switch(r4) {
            case 1: goto L_0x0042;
            case 2: goto L_0x0042;
            case 3: goto L_0x0041;
            default: goto L_0x003f;
        };
    L_0x003f:
        r4 = 0;
        return r4;
    L_0x0041:
        return r3;
    L_0x0042:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.a(java.lang.String):int");
    }

    /* Access modifiers changed, original: final */
    public final boolean c() {
        if (this.d == null) {
            return false;
        }
        am amVar;
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ak akVar = (ak) it.next();
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                amVar = (am) akVar;
                break;
            }
        }
        amVar = null;
        if (amVar == null) {
            return g();
        }
        if (b() <= 0) {
            return false;
        }
        return g();
    }

    private boolean g() {
        List<ak> c = c(ShareConstants.VIDEO_URL);
        if (c == null || c.size() <= 0) {
            return true;
        }
        for (ak akVar : c) {
            akVar.a.length();
            be beVar = (be) akVar;
            if (beVar.b() == null) {
                return false;
            }
            List c2 = beVar.b().c();
            if (c2 == null || c2.size() == 0) {
                return false;
            }
            String b = beVar.b().b();
            if (b != null) {
                if (b.length() == 0) {
                }
            }
            HashMap hashMap = new HashMap();
            hashMap.put("[ERRORCODE]", "403");
            beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, (Map) hashMap);
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x015e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0153 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0148 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x013d A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0133 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0129 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0113 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0107 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fb A:{SYNTHETIC, Splitter:B:53:0x00fb} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x015e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0153 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0148 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x013d A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0133 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0129 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0113 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0107 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fb A:{SYNTHETIC, Splitter:B:53:0x00fb} */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x015e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0153 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0148 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x013d A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0133 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0129 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0113 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0107 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fb A:{SYNTHETIC, Splitter:B:53:0x00fb} */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x015e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0153 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0148 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x013d A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0133 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0129 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0113 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0107 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fb A:{SYNTHETIC, Splitter:B:53:0x00fb} */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x015e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0153 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0148 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x013d A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0133 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0129 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011e A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0113 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0107 A:{Catch:{ JSONException -> 0x0168 }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fb A:{SYNTHETIC, Splitter:B:53:0x00fb} */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0874  */
    /* JADX WARNING: Missing block: B:88:0x0178, code skipped:
            switch(r6) {
                case 0: goto L_0x0662;
                case 1: goto L_0x063f;
                case 2: goto L_0x061f;
                case 3: goto L_0x0564;
                case 4: goto L_0x0495;
                case 5: goto L_0x0495;
                case 6: goto L_0x042b;
                case 7: goto L_0x024d;
                case 8: goto L_0x023f;
                case 9: goto L_0x0187;
                default: goto L_0x017b;
            };
     */
    /* JADX WARNING: Missing block: B:89:0x017b, code skipped:
            r56 = r12;
            r33 = r23;
            r55 = r25;
            r32 = r26;
            r34 = r27;
     */
    /* JADX WARNING: Missing block: B:92:0x018b, code skipped:
            if (p(r59) != false) goto L_0x018e;
     */
    /* JADX WARNING: Missing block: B:93:0x018d, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:94:0x018e, code skipped:
            r8 = r25;
            r32 = r26;
     */
    /* JADX WARNING: Missing block: B:96:?, code skipped:
            r1 = c(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Missing block: B:97:0x01a4, code skipped:
            if (r15.getJSONObject("assetOnclick").isNull("openMode") != false) goto L_0x01c2;
     */
    /* JADX WARNING: Missing block: B:100:0x01b6, code skipped:
            r28 = d(r15.getJSONObject("assetOnclick").getString("openMode"));
     */
    /* JADX WARNING: Missing block: B:101:0x01b9, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:102:0x01ba, code skipped:
            r1 = r0;
            r55 = r8;
            r56 = r12;
            r33 = r23;
     */
    /* JADX WARNING: Missing block: B:103:0x01c2, code skipped:
            r28 = 2;
     */
    /* JADX WARNING: Missing block: B:105:?, code skipped:
            r2 = r15.getJSONObject("assetOnclick").optString("fallbackUrl");
     */
    /* JADX WARNING: Missing block: B:106:0x01d0, code skipped:
            if (r11 == null) goto L_0x01f3;
     */
    /* JADX WARNING: Missing block: B:108:0x01d6, code skipped:
            if (r11.size() != 0) goto L_0x01d9;
     */
    /* JADX WARNING: Missing block: B:111:0x01db, code skipped:
            r6 = r8;
            r8 = r8;
            r9 = r12;
            r4 = r11;
            r11 = r1;
            r5 = r12;
            r12 = r13;
            r33 = r23;
            r34 = r27;
            r1 = r61;
            r13 = r4;
            r4 = r14;
     */
    /* JADX WARNING: Missing block: B:113:?, code skipped:
            r8 = new com.inmobi.ads.an(r9, r10, r11, r12, r13, r28, r59);
            r15 = r1;
     */
    /* JADX WARNING: Missing block: B:114:0x01f3, code skipped:
            r6 = r8;
            r5 = r12;
            r4 = r14;
            r33 = r23;
            r34 = r27;
            r15 = r61;
            r8 = new com.inmobi.ads.an(r5, r10, r1, r13, r28, r59);
     */
    /* JADX WARNING: Missing block: B:115:0x0209, code skipped:
            r3.g = r15;
            a(r3, r59);
     */
    /* JADX WARNING: Missing block: B:116:0x0210, code skipped:
            if (r2 == null) goto L_0x0215;
     */
    /* JADX WARNING: Missing block: B:117:0x0212, code skipped:
            r3.b(r2);
     */
    /* JADX WARNING: Missing block: B:118:0x0215, code skipped:
            r21 = r3;
            r56 = r5;
            r55 = r6;
     */
    /* JADX WARNING: Missing block: B:119:0x021d, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:120:0x021e, code skipped:
            r1 = r0;
            r56 = r5;
            r55 = r6;
     */
    /* JADX WARNING: Missing block: B:121:0x0225, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:122:0x0226, code skipped:
            r33 = r23;
            r34 = r27;
            r1 = r0;
            r55 = r8;
            r56 = r12;
     */
    /* JADX WARNING: Missing block: B:123:0x0231, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:124:0x0232, code skipped:
            r33 = r23;
            r32 = r26;
            r34 = r27;
            r1 = r0;
            r56 = r12;
            r55 = r25;
     */
    /* JADX WARNING: Missing block: B:125:0x023f, code skipped:
            r33 = r23;
            r32 = r26;
            r34 = r27;
            r56 = r12;
            r2 = null;
            r55 = r25;
     */
    /* JADX WARNING: Missing block: B:126:0x024d, code skipped:
            r8 = r11;
            r11 = r12;
            r12 = r14;
            r14 = r15;
            r33 = r23;
            r35 = r25;
            r32 = r26;
            r34 = r27;
            r15 = r61;
     */
    /* JADX WARNING: Missing block: B:128:?, code skipped:
            r36 = r8;
            r7.h.get(com.facebook.share.internal.ShareConstants.VIDEO_URL);
            r37 = new com.inmobi.ads.be.a(r2.x, r2.y, r3.x, r3.y, r4.x, r4.y, r5.x, r5.y, s(r9));
            r8 = r62;
     */
    /* JADX WARNING: Missing block: B:129:0x0291, code skipped:
            if (r8 == null) goto L_0x02a8;
     */
    /* JADX WARNING: Missing block: B:131:0x0297, code skipped:
            if (a(r8, r37) != false) goto L_0x02a8;
     */
    /* JADX WARNING: Missing block: B:132:0x0299, code skipped:
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "InvalidVideoGeometry", new java.util.HashMap());
     */
    /* JADX WARNING: Missing block: B:134:0x02aa, code skipped:
            if (r7.q != null) goto L_0x02b1;
     */
    /* JADX WARNING: Missing block: B:135:0x02ac, code skipped:
            r2 = a(r14, r13, r1);
     */
    /* JADX WARNING: Missing block: B:136:0x02b1, code skipped:
            r2 = r7.q;
     */
    /* JADX WARNING: Missing block: B:137:0x02b3, code skipped:
            r12 = r2;
     */
    /* JADX WARNING: Missing block: B:138:0x02ba, code skipped:
            if (com.inmobi.ads.AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE != r7.s) goto L_0x0366;
     */
    /* JADX WARNING: Missing block: B:139:0x02bc, code skipped:
            if (r1 == null) goto L_0x0321;
     */
    /* JADX WARNING: Missing block: B:141:0x02cc, code skipped:
            if (((java.lang.Boolean) r1.v.get("didRequestFullScreen")).booleanValue() != false) goto L_0x02e4;
     */
    /* JADX WARNING: Missing block: B:143:0x02d0, code skipped:
            if (r7.t == false) goto L_0x02d3;
     */
    /* JADX WARNING: Missing block: B:145:0x02d3, code skipped:
            r2 = Integer.MAX_VALUE;
            r3 = 0;
            r5 = true;
            r13 = true;
            r16 = true;
            r17 = true;
            r24 = false;
     */
    /* JADX WARNING: Missing block: B:146:0x02e4, code skipped:
            r2 = r14.optBoolean("loop", false);
            r3 = r14.optBoolean("showProgress", true);
            r9 = r14.optBoolean("soundOn", true);
            r13 = r14.optBoolean("showMute", true);
            r16 = r3;
            r17 = r14.optBoolean("autoPlay", true);
            r5 = r13;
            r24 = r2;
            r3 = (int) r14.optDouble("pauseAfter", 0.0d);
            r13 = r9;
            r2 = ((com.inmobi.ads.be) r1).E;
     */
    /* JADX WARNING: Missing block: B:147:0x0321, code skipped:
            r2 = r14.optBoolean("loop", true);
            r3 = r14.optBoolean("showProgress", false);
            r9 = r14.optBoolean("soundOn", false);
            r8 = r14.optBoolean("showMute", false);
            r48 = r2;
            r49 = r3;
            r3 = (int) r14.optDouble("pauseAfter", 0.0d);
            r5 = r8;
            r17 = r14.optBoolean("autoPlay", true);
            r2 = r14.optInt("completeAfter", Integer.MAX_VALUE);
            r24 = r48;
            r16 = r49;
            r13 = r9;
     */
    /* JADX WARNING: Missing block: B:148:0x0366, code skipped:
            r2 = r14.optBoolean("loop", false);
            r3 = r14.optBoolean("showProgress", true);
            r4 = r14.optBoolean("soundOn", true);
            r5 = r14.optBoolean("showMute", true);
            r50 = r2;
            r51 = r3;
            r3 = (int) r14.optDouble("pauseAfter", 0.0d);
            r13 = r4;
            r17 = r14.optBoolean("autoPlay", true);
            r2 = r14.optInt("completeAfter", Integer.MAX_VALUE);
            r24 = r50;
            r16 = r51;
     */
    /* JADX WARNING: Missing block: B:149:0x03a5, code skipped:
            r4 = new java.util.HashMap();
     */
    /* JADX WARNING: Missing block: B:150:0x03b0, code skipped:
            if (r14.isNull("videoViewabilityConfig") != false) goto L_0x03d9;
     */
    /* JADX WARNING: Missing block: B:151:0x03b2, code skipped:
            r8 = r14.getJSONObject("videoViewabilityConfig");
            r9 = r8.keys();
     */
    /* JADX WARNING: Missing block: B:153:0x03c0, code skipped:
            if (r9.hasNext() == false) goto L_0x03d9;
     */
    /* JADX WARNING: Missing block: B:154:0x03c2, code skipped:
            r15 = (java.lang.String) r9.next();
            r52 = r9;
            r4.put(r15, r8.get(r15));
            r9 = r52;
     */
    /* JADX WARNING: Missing block: B:156:0x03e1, code skipped:
            r8 = r8;
            r54 = r11;
            r6 = r60;
            r14 = r5;
            r6 = r8;
            r5 = r61;
     */
    /* JADX WARNING: Missing block: B:158:?, code skipped:
            r8 = new com.inmobi.ads.be(r11, r10, r37, r12, r13, r14, r24, r16, r17, r36, r59, r7.r.i.m);
            ((com.inmobi.ads.be) r6).G = new java.util.HashMap(r4);
            r15 = (com.inmobi.ads.be) r6;
     */
    /* JADX WARNING: Missing block: B:159:0x0409, code skipped:
            if (r2 > 0) goto L_0x040d;
     */
    /* JADX WARNING: Missing block: B:160:0x040b, code skipped:
            r2 = Integer.MAX_VALUE;
     */
    /* JADX WARNING: Missing block: B:161:0x040d, code skipped:
            r15.E = r2;
            r6.g = r5;
            r6.y = r1;
     */
    /* JADX WARNING: Missing block: B:162:0x0413, code skipped:
            if (r1 == null) goto L_0x0417;
     */
    /* JADX WARNING: Missing block: B:163:0x0415, code skipped:
            r1.y = r6;
     */
    /* JADX WARNING: Missing block: B:164:0x0417, code skipped:
            if (r3 == 0) goto L_0x041e;
     */
    /* JADX WARNING: Missing block: B:165:0x0419, code skipped:
            ((com.inmobi.ads.be) r6).F = r3;
     */
    /* JADX WARNING: Missing block: B:166:0x041e, code skipped:
            r2 = r6;
            r55 = r35;
     */
    /* JADX WARNING: Missing block: B:167:0x0423, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:168:0x0424, code skipped:
            r1 = r0;
            r56 = r11;
            r55 = r35;
     */
    /* JADX WARNING: Missing block: B:169:0x042b, code skipped:
            r54 = r12;
            r33 = r23;
            r35 = r25;
            r32 = r26;
            r34 = r27;
            r14 = r61;
     */
    /* JADX WARNING: Missing block: B:170:0x0439, code skipped:
            if (r13 != null) goto L_0x043c;
     */
    /* JADX WARNING: Missing block: B:171:0x043b, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:173:?, code skipped:
            r11 = com.inmobi.ads.bf.c(h(r59));
     */
    /* JADX WARNING: Missing block: B:174:0x044a, code skipped:
            if ("URL".equals(r11) == false) goto L_0x0459;
     */
    /* JADX WARNING: Missing block: B:177:0x0450, code skipped:
            if (android.webkit.URLUtil.isValidUrl(r13) != false) goto L_0x0459;
     */
    /* JADX WARNING: Missing block: B:178:0x0452, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:179:0x0453, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:180:0x0454, code skipped:
            r1 = r0;
            r55 = r35;
     */
    /* JADX WARNING: Missing block: B:181:0x0459, code skipped:
            r12 = false;
            r55 = r35;
            r8 = r60;
     */
    /* JADX WARNING: Missing block: B:183:?, code skipped:
            r4 = a(r2, r3, r4, r5, r9);
            r6 = r59;
            r5 = r13;
            r13 = r6;
            r1 = new com.inmobi.ads.bf(r54, r10, r4, r5, r6.optBoolean("isScrollable"));
            r1.z = r11;
            r1.g = r14;
     */
    /* JADX WARNING: Missing block: B:184:0x0483, code skipped:
            if (r13.optBoolean("preload", r12) == false) goto L_0x048c;
     */
    /* JADX WARNING: Missing block: B:185:0x0485, code skipped:
            r1.A = true;
            r7.k = r1;
            r2 = r1;
     */
    /* JADX WARNING: Missing block: B:186:0x048c, code skipped:
            r21 = r1;
     */
    /* JADX WARNING: Missing block: B:187:0x0490, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:188:0x0491, code skipped:
            r55 = r35;
     */
    /* JADX WARNING: Missing block: B:189:0x0495, code skipped:
            r53 = r11;
            r54 = r12;
            r8 = r14;
            r13 = r15;
            r33 = r23;
            r55 = r25;
            r32 = r26;
            r34 = r27;
            r11 = 2;
            r14 = r61;
            r1 = a(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Missing block: B:190:0x04b1, code skipped:
            if (p(r59) == false) goto L_0x04e1;
     */
    /* JADX WARNING: Missing block: B:192:0x04bf, code skipped:
            if (r13.getJSONObject("assetOnclick").isNull("openMode") != false) goto L_0x04d2;
     */
    /* JADX WARNING: Missing block: B:193:0x04c1, code skipped:
            r11 = d(r13.getJSONObject("assetOnclick").getString("openMode"));
     */
    /* JADX WARNING: Missing block: B:194:0x04d2, code skipped:
            r3 = r13.getJSONObject("assetOnclick").optString("fallbackUrl");
            r2 = r11;
     */
    /* JADX WARNING: Missing block: B:195:0x04e1, code skipped:
            r2 = 0;
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:196:0x04e4, code skipped:
            r6 = r53;
     */
    /* JADX WARNING: Missing block: B:197:0x04e6, code skipped:
            if (r6 == null) goto L_0x0525;
     */
    /* JADX WARNING: Missing block: B:199:0x04ec, code skipped:
            if (r6.size() != 0) goto L_0x04ef;
     */
    /* JADX WARNING: Missing block: B:202:0x04f5, code skipped:
            if (com.facebook.share.internal.ShareConstants.IMAGE_URL.equals(r8) == false) goto L_0x050d;
     */
    /* JADX WARNING: Missing block: B:203:0x04f7, code skipped:
            r5 = r8;
            r11 = r1;
            r1 = r13;
            r13 = r6;
            r6 = r14;
            r15 = new com.inmobi.ads.as(r54, r10, r11, c(r59), r13, r2, r1);
            r4 = r1;
     */
    /* JADX WARNING: Missing block: B:204:0x050d, code skipped:
            r5 = r8;
            r4 = r13;
            r6 = r14;
            r15 = new com.inmobi.ads.aq(r54, r10, r1, c(r59), r6, r2, r4);
     */
    /* JADX WARNING: Missing block: B:205:0x0525, code skipped:
            r4 = r13;
            r6 = r14;
     */
    /* JADX WARNING: Missing block: B:206:0x052e, code skipped:
            if (com.facebook.share.internal.ShareConstants.IMAGE_URL.equals(r8) == false) goto L_0x0540;
     */
    /* JADX WARNING: Missing block: B:207:0x0530, code skipped:
            r8 = new com.inmobi.ads.as(r54, r10, r1, c(r59), r2, r4);
     */
    /* JADX WARNING: Missing block: B:208:0x0540, code skipped:
            r8 = new com.inmobi.ads.aq(r54, r10, r1, c(r59), r2, r4);
     */
    /* JADX WARNING: Missing block: B:209:0x054f, code skipped:
            r15.g = r6;
            a(r15, r4);
     */
    /* JADX WARNING: Missing block: B:210:0x0554, code skipped:
            if (r3 == null) goto L_0x055e;
     */
    /* JADX WARNING: Missing block: B:211:0x0556, code skipped:
            r15.b(r3);
            r2 = r15;
     */
    /* JADX WARNING: Missing block: B:212:0x055a, code skipped:
            r56 = r54;
     */
    /* JADX WARNING: Missing block: B:213:0x055e, code skipped:
            r21 = r15;
     */
    /* JADX WARNING: Missing block: B:214:0x0560, code skipped:
            r56 = r54;
     */
    /* JADX WARNING: Missing block: B:215:0x0564, code skipped:
            r54 = r12;
            r8 = r14;
            r14 = r15;
            r33 = r23;
            r55 = r25;
            r32 = r26;
            r34 = r27;
            r12 = 0;
            r13 = r61;
            r1 = a(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Missing block: B:216:0x0582, code skipped:
            if (r14.has("startOffset") == false) goto L_0x058f;
     */
    /* JADX WARNING: Missing block: B:217:0x0584, code skipped:
            r2 = q(r14.getJSONObject("startOffset"));
     */
    /* JADX WARNING: Missing block: B:218:0x058f, code skipped:
            r2 = null;
     */
    /* JADX WARNING: Missing block: B:220:0x0597, code skipped:
            if (r14.has("timerDuration") == false) goto L_0x05a4;
     */
    /* JADX WARNING: Missing block: B:221:0x0599, code skipped:
            r3 = q(r14.getJSONObject("timerDuration"));
     */
    /* JADX WARNING: Missing block: B:222:0x05a4, code skipped:
            r3 = null;
     */
    /* JADX WARNING: Missing block: B:223:0x05a6, code skipped:
            r4 = r14.optBoolean("displayTimer", true);
     */
    /* JADX WARNING: Missing block: B:224:0x05b3, code skipped:
            r6 = r54;
     */
    /* JADX WARNING: Missing block: B:226:?, code skipped:
            r2 = new com.inmobi.ads.bb(r6, r10, r1, new com.inmobi.ads.ba(r2, r3));
            r2.z = r4;
     */
    /* JADX WARNING: Missing block: B:227:0x05c0, code skipped:
            if (r14.has("assetOnFinish") == false) goto L_0x060d;
     */
    /* JADX WARNING: Missing block: B:228:0x05c2, code skipped:
            r1 = (org.json.JSONObject) r14.get("assetOnFinish");
     */
    /* JADX WARNING: Missing block: B:229:0x05d0, code skipped:
            if (r1.has(com.facebook.internal.NativeProtocol.WEB_DIALOG_ACTION) == false) goto L_0x060d;
     */
    /* JADX WARNING: Missing block: B:230:0x05d2, code skipped:
            r1 = r1.getString(com.facebook.internal.NativeProtocol.WEB_DIALOG_ACTION).toUpperCase(java.util.Locale.US).trim();
            r3 = r1.hashCode();
     */
    /* JADX WARNING: Missing block: B:231:0x05e9, code skipped:
            if (r3 == 2142494) goto L_0x05fb;
     */
    /* JADX WARNING: Missing block: B:233:0x05ee, code skipped:
            if (r3 == 2402104) goto L_0x05f1;
     */
    /* JADX WARNING: Missing block: B:236:0x05f7, code skipped:
            if (r1.equals("NONE") == false) goto L_0x0605;
     */
    /* JADX WARNING: Missing block: B:237:0x05f9, code skipped:
            r1 = true;
     */
    /* JADX WARNING: Missing block: B:239:0x0601, code skipped:
            if (r1.equals("EXIT") == false) goto L_0x0605;
     */
    /* JADX WARNING: Missing block: B:240:0x0603, code skipped:
            r1 = true;
     */
    /* JADX WARNING: Missing block: B:241:0x0605, code skipped:
            r1 = true;
     */
    /* JADX WARNING: Missing block: B:242:0x0607, code skipped:
            if (r1 == true) goto L_0x060a;
     */
    /* JADX WARNING: Missing block: B:244:0x060a, code skipped:
            r12 = 1;
     */
    /* JADX WARNING: Missing block: B:245:0x060b, code skipped:
            r2.k = r12;
     */
    /* JADX WARNING: Missing block: B:246:0x060d, code skipped:
            r2.g = r13;
     */
    /* JADX WARNING: Missing block: B:247:0x060f, code skipped:
            r56 = r6;
     */
    /* JADX WARNING: Missing block: B:248:0x0613, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:249:0x0614, code skipped:
            r1 = r0;
            r56 = r6;
     */
    /* JADX WARNING: Missing block: B:250:0x0619, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:251:0x061a, code skipped:
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:252:0x061b, code skipped:
            r56 = r54;
     */
    /* JADX WARNING: Missing block: B:253:0x061f, code skipped:
            r8 = r14;
            r14 = r15;
            r33 = r23;
            r55 = r25;
            r32 = r26;
            r34 = r27;
            r13 = r61;
            r11 = r12;
     */
    /* JADX WARNING: Missing block: B:255:?, code skipped:
            r2 = new com.inmobi.ads.ar(r11, r10, a(r2, r3, r4, r5, r9), c(r59));
            r2.g = r13;
     */
    /* JADX WARNING: Missing block: B:256:0x063f, code skipped:
            r11 = r12;
            r8 = r14;
            r33 = r23;
            r55 = r25;
            r32 = r26;
            r34 = r27;
            r12 = r61;
            r2 = new com.inmobi.ads.az(r11, r10, b(r2, r3, r4, r5, r9), r13);
            r2.g = r12;
     */
    /* JADX WARNING: Missing block: B:257:0x0658, code skipped:
            r56 = r11;
     */
    /* JADX WARNING: Missing block: B:258:0x065c, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:259:0x065d, code skipped:
            r1 = r0;
            r56 = r11;
     */
    /* JADX WARNING: Missing block: B:260:0x0662, code skipped:
            r56 = r12;
            r13 = r14;
            r14 = r15;
            r33 = r23;
            r55 = r25;
            r32 = r26;
            r34 = r27;
            r8 = r62;
            r12 = r11;
     */
    /* JADX WARNING: Missing block: B:262:?, code skipped:
            r1 = a(r2, r3, r4, r5, r9);
     */
    /* JADX WARNING: Missing block: B:263:0x067b, code skipped:
            if (r8 == null) goto L_0x0692;
     */
    /* JADX WARNING: Missing block: B:265:0x0681, code skipped:
            if (a(r8, r1) != false) goto L_0x0692;
     */
    /* JADX WARNING: Missing block: B:266:0x0683, code skipped:
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "InvalidContainerGeometry", new java.util.HashMap());
     */
    /* JADX WARNING: Missing block: B:268:0x0696, code skipped:
            if (p(r59) == false) goto L_0x06c7;
     */
    /* JADX WARNING: Missing block: B:270:0x06a4, code skipped:
            if (r14.getJSONObject("assetOnclick").isNull("openMode") != false) goto L_0x06b7;
     */
    /* JADX WARNING: Missing block: B:271:0x06a6, code skipped:
            r8 = d(r14.getJSONObject("assetOnclick").getString("openMode"));
     */
    /* JADX WARNING: Missing block: B:272:0x06b7, code skipped:
            r8 = 2;
     */
    /* JADX WARNING: Missing block: B:273:0x06b8, code skipped:
            r3 = r14.getJSONObject("assetOnclick").optString("fallbackUrl");
            r2 = r8;
     */
    /* JADX WARNING: Missing block: B:274:0x06c7, code skipped:
            r3 = null;
            r2 = 0;
     */
    /* JADX WARNING: Missing block: B:276:0x06d0, code skipped:
            if (r9.has("transitionEffect") == false) goto L_0x0706;
     */
    /* JADX WARNING: Missing block: B:277:0x06d2, code skipped:
            r4 = r9.getString("transitionEffect").trim();
            r5 = r4.hashCode();
     */
    /* JADX WARNING: Missing block: B:278:0x06e3, code skipped:
            if (r5 == 3151468) goto L_0x06f5;
     */
    /* JADX WARNING: Missing block: B:280:0x06e8, code skipped:
            if (r5 == 106426293) goto L_0x06eb;
     */
    /* JADX WARNING: Missing block: B:283:0x06f1, code skipped:
            if (r4.equals("paged") == false) goto L_0x06ff;
     */
    /* JADX WARNING: Missing block: B:284:0x06f3, code skipped:
            r4 = 1;
     */
    /* JADX WARNING: Missing block: B:286:0x06fb, code skipped:
            if (r4.equals("free") == false) goto L_0x06ff;
     */
    /* JADX WARNING: Missing block: B:287:0x06fd, code skipped:
            r4 = 2;
     */
    /* JADX WARNING: Missing block: B:288:0x06ff, code skipped:
            r4 = -1;
     */
    /* JADX WARNING: Missing block: B:289:0x0701, code skipped:
            if (r4 == 2) goto L_0x0704;
     */
    /* JADX WARNING: Missing block: B:291:0x0704, code skipped:
            r4 = 1;
     */
    /* JADX WARNING: Missing block: B:292:0x0706, code skipped:
            r4 = 0;
     */
    /* JADX WARNING: Missing block: B:293:0x0707, code skipped:
            if (r12 == null) goto L_0x0722;
     */
    /* JADX WARNING: Missing block: B:295:0x070d, code skipped:
            if (r12.size() != 0) goto L_0x0710;
     */
    /* JADX WARNING: Missing block: B:297:0x0710, code skipped:
            r6 = 2;
            r13 = r2;
            r2 = r14;
            r22 = 1;
            r15 = new com.inmobi.ads.am(r56, r10, r1, r12, r13, r14, r4);
            r5 = r2;
     */
    /* JADX WARNING: Missing block: B:298:0x0722, code skipped:
            r6 = 2;
            r5 = r14;
            r22 = 1;
            r8 = new com.inmobi.ads.am(r56, r10, r1, r2, r5, r4);
     */
    /* JADX WARNING: Missing block: B:299:0x0732, code skipped:
            r2 = r61;
            r15.g = r2;
     */
    /* JADX WARNING: Missing block: B:300:0x0736, code skipped:
            if (r3 == null) goto L_0x073b;
     */
    /* JADX WARNING: Missing block: B:301:0x0738, code skipped:
            r15.b(r3);
     */
    /* JADX WARNING: Missing block: B:302:0x073b, code skipped:
            a(r15, r5);
            r3 = r5.getJSONArray("assetValue");
            r4 = 0;
     */
    /* JADX WARNING: Missing block: B:304:0x0749, code skipped:
            if (r4 >= r3.length()) goto L_0x0854;
     */
    /* JADX WARNING: Missing block: B:305:0x074b, code skipped:
            r5 = new java.lang.StringBuilder();
            r5.append(r2);
            r5.append(".assetValue[");
            r5.append(r4);
            r5.append("]");
            r5 = r5.toString();
            r8 = r3.getJSONObject(r4);
            r9 = f(r8).toLowerCase(java.util.Locale.US).trim();
     */
    /* JADX WARNING: Missing block: B:306:0x077a, code skipped:
            switch(r9.hashCode()) {
                case -938102371: goto L_0x07e2;
                case -410956671: goto L_0x07d7;
                case 98832: goto L_0x07cc;
                case 102340: goto L_0x07c1;
                case 3226745: goto L_0x07b7;
                case 3556653: goto L_0x07ac;
                case 100313435: goto L_0x07a1;
                case 110364485: goto L_0x0796;
                case 112202875: goto L_0x078b;
                case 1224424441: goto L_0x077f;
                default: goto L_0x077d;
            };
     */
    /* JADX WARNING: Missing block: B:309:0x0785, code skipped:
            if (r9.equals("webview") == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:310:0x0787, code skipped:
            r9 = 9;
     */
    /* JADX WARNING: Missing block: B:312:0x0791, code skipped:
            if (r9.equals("video") == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:313:0x0793, code skipped:
            r9 = 4;
     */
    /* JADX WARNING: Missing block: B:315:0x079c, code skipped:
            if (r9.equals("timer") == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:316:0x079e, code skipped:
            r9 = 8;
     */
    /* JADX WARNING: Missing block: B:318:0x07a7, code skipped:
            if (r9.equals(com.google.android.exoplayer2.text.ttml.TtmlNode.TAG_IMAGE) == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:319:0x07a9, code skipped:
            r9 = 3;
     */
    /* JADX WARNING: Missing block: B:321:0x07b2, code skipped:
            if (r9.equals(com.google.android.exoplayer2.util.MimeTypes.BASE_TYPE_TEXT) == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:322:0x07b4, code skipped:
            r9 = 5;
     */
    /* JADX WARNING: Missing block: B:324:0x07bd, code skipped:
            if (r9.equals(com.google.ads.mediation.inmobi.InMobiNetworkValues.ICON) == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:325:0x07bf, code skipped:
            r9 = r6;
     */
    /* JADX WARNING: Missing block: B:327:0x07c7, code skipped:
            if (r9.equals("gif") == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:328:0x07c9, code skipped:
            r9 = 10;
     */
    /* JADX WARNING: Missing block: B:330:0x07d2, code skipped:
            if (r9.equals(com.google.ads.mediation.inmobi.InMobiNetworkValues.CTA) == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:331:0x07d4, code skipped:
            r9 = 6;
     */
    /* JADX WARNING: Missing block: B:333:0x07dd, code skipped:
            if (r9.equals("container") == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:334:0x07df, code skipped:
            r9 = r22;
     */
    /* JADX WARNING: Missing block: B:336:0x07e8, code skipped:
            if (r9.equals("rating") == false) goto L_0x07ed;
     */
    /* JADX WARNING: Missing block: B:337:0x07ea, code skipped:
            r9 = 7;
     */
    /* JADX WARNING: Missing block: B:338:0x07ed, code skipped:
            r9 = -1;
     */
    /* JADX WARNING: Missing block: B:339:0x07ef, code skipped:
            switch(r9) {
                case 2: goto L_0x080d;
                case 3: goto L_0x080a;
                case 4: goto L_0x0807;
                case 5: goto L_0x0804;
                case 6: goto L_0x0801;
                case 7: goto L_0x07fe;
                case 8: goto L_0x07fb;
                case 9: goto L_0x07f8;
                case 10: goto L_0x07f5;
                default: goto L_0x07f2;
            };
     */
    /* JADX WARNING: Missing block: B:340:0x07f2, code skipped:
            r9 = "CONTAINER";
     */
    /* JADX WARNING: Missing block: B:341:0x07f5, code skipped:
            r9 = "GIF";
     */
    /* JADX WARNING: Missing block: B:342:0x07f8, code skipped:
            r9 = "WEBVIEW";
     */
    /* JADX WARNING: Missing block: B:343:0x07fb, code skipped:
            r9 = "TIMER";
     */
    /* JADX WARNING: Missing block: B:344:0x07fe, code skipped:
            r9 = "RATING";
     */
    /* JADX WARNING: Missing block: B:345:0x0801, code skipped:
            r9 = "CTA";
     */
    /* JADX WARNING: Missing block: B:346:0x0804, code skipped:
            r9 = "TEXT";
     */
    /* JADX WARNING: Missing block: B:347:0x0807, code skipped:
            r9 = com.facebook.share.internal.ShareConstants.VIDEO_URL;
     */
    /* JADX WARNING: Missing block: B:348:0x080a, code skipped:
            r9 = com.facebook.share.internal.ShareConstants.IMAGE_URL;
     */
    /* JADX WARNING: Missing block: B:349:0x080d, code skipped:
            r9 = "ICON";
     */
    /* JADX WARNING: Missing block: B:350:0x080f, code skipped:
            r9 = a(r8, r9, r5, r1);
     */
    /* JADX WARNING: Missing block: B:351:0x0813, code skipped:
            if (r9 != null) goto L_0x0820;
     */
    /* JADX WARNING: Missing block: B:352:0x0815, code skipped:
            new java.lang.StringBuilder("Cannot build asset from JSON: ").append(r8);
     */
    /* JADX WARNING: Missing block: B:353:0x0820, code skipped:
            r9.g = r5;
            r9.t = r15;
     */
    /* JADX WARNING: Missing block: B:354:0x0828, code skipped:
            if (r15.C >= 16) goto L_0x084f;
     */
    /* JADX WARNING: Missing block: B:356:0x082f, code skipped:
            if (r15.C != r15.B.length) goto L_0x0843;
     */
    /* JADX WARNING: Missing block: B:357:0x0831, code skipped:
            r5 = new com.inmobi.ads.ak[(r6 * r15.B.length)];
            java.lang.System.arraycopy(r15.B, 0, r5, 0, r15.C);
            r15.B = r5;
     */
    /* JADX WARNING: Missing block: B:359:0x0844, code skipped:
            r5 = r15.B;
            r8 = r15.C;
            r15.C = r8 + 1;
            r5[r8] = r9;
     */
    /* JADX WARNING: Missing block: B:361:0x0850, code skipped:
            r4 = r4 + 1;
     */
    /* JADX WARNING: Missing block: B:362:0x0854, code skipped:
            r2 = r15;
     */
    /* JADX WARNING: Missing block: B:363:0x0856, code skipped:
            r0 = e;
     */
    /* JADX WARNING: Missing block: B:370:0x0874, code skipped:
            r2.n = r32;
            r2.o = r33;
            r2.p = r34;
            r1 = r55;
            r2.q = r1;
     */
    /* JADX WARNING: Missing block: B:371:0x0884, code skipped:
            if (r1 == null) goto L_0x0894;
     */
    /* JADX WARNING: Missing block: B:374:0x088c, code skipped:
            r4 = r56;
            r7.p.put(r4, r1);
     */
    /* JADX WARNING: Missing block: B:375:0x0894, code skipped:
            r4 = r56;
     */
    /* JADX WARNING: Missing block: B:380:0x08a4, code skipped:
            r7.o.put(r4, r2);
     */
    /* JADX WARNING: Missing block: B:381:0x08a9, code skipped:
            r3 = r60;
     */
    /* JADX WARNING: Missing block: B:382:0x08b1, code skipped:
            if (r7.h.containsKey(r3) != false) goto L_0x08b3;
     */
    /* JADX WARNING: Missing block: B:383:0x08b3, code skipped:
            ((java.util.List) r7.h.get(r3)).add(r2);
     */
    /* JADX WARNING: Missing block: B:384:0x08bf, code skipped:
            r1 = new java.util.ArrayList();
            r1.add(r2);
            r7.h.put(r3, r1);
     */
    @android.annotation.TargetApi(15)
    private com.inmobi.ads.ak a(@android.support.annotation.NonNull org.json.JSONObject r59, java.lang.String r60, java.lang.String r61, @android.support.annotation.Nullable com.inmobi.ads.al r62) {
        /*
        r58 = this;
        r7 = r58;
        r15 = r59;
        r14 = r60;
        r13 = r61;
        r8 = r62;
        r12 = d(r59);
        r10 = e(r59);
        r9 = r58.i(r59);
        r1 = a(r9, r14);
        r21 = 0;
        if (r1 != 0) goto L_0x0029;
    L_0x001e:
        r1 = new java.lang.StringBuilder;
        r2 = "Asset style JSON: ";
        r1.<init>(r2);
        r1.append(r9);
        return r21;
    L_0x0029:
        r2 = r58.j(r59);
        r4 = r7.a(r15, r2);
        r3 = r58.k(r59);
        r5 = r7.b(r15, r3);
        r11 = b(r59);
        r6 = l(r59);
        r1 = 1;
        r13 = a(r15, r1);
        r1 = 0;
        r23 = r13;
        r13 = a(r15, r1);
        r1 = m(r59);
        r16 = "";
        r25 = r1;
        r1 = g(r59);
        r1 = r1.trim();
        r26 = r6;
        r6 = r1.hashCode();
        r27 = r13;
        r13 = -925155509; // 0xffffffffc8db3f4b float:-449018.34 double:NaN;
        r17 = -1;
        r8 = 2;
        if (r6 == r13) goto L_0x007d;
    L_0x006d:
        r13 = 1728122231; // 0x67010d77 float:6.0943366E23 double:8.53805826E-315;
        if (r6 == r13) goto L_0x0073;
    L_0x0072:
        goto L_0x0087;
    L_0x0073:
        r6 = "absolute";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0087;
    L_0x007b:
        r1 = 1;
        goto L_0x0089;
    L_0x007d:
        r6 = "reference";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0087;
    L_0x0085:
        r1 = r8;
        goto L_0x0089;
    L_0x0087:
        r1 = r17;
    L_0x0089:
        if (r1 == r8) goto L_0x008d;
    L_0x008b:
        r1 = 0;
        goto L_0x008e;
    L_0x008d:
        r1 = 1;
    L_0x008e:
        r6 = o(r59);
        if (r6 == 0) goto L_0x00e0;
    L_0x0094:
        r13 = r6.length();
        if (r13 == 0) goto L_0x00e0;
    L_0x009a:
        r13 = 0;
        r6 = r6.getString(r13);	 Catch:{ JSONException -> 0x00c8 }
        r13 = android.text.TextUtils.isEmpty(r6);	 Catch:{ JSONException -> 0x00c5 }
        if (r13 == 0) goto L_0x00a6;
    L_0x00a5:
        return r21;
    L_0x00a6:
        r13 = 1;
        if (r1 != r13) goto L_0x00c0;
    L_0x00a9:
        r1 = r7.b(r6);	 Catch:{ JSONException -> 0x00c5 }
        if (r1 != 0) goto L_0x00be;
    L_0x00af:
        r13 = r7.f;	 Catch:{ JSONException -> 0x00ba }
        if (r13 == 0) goto L_0x00be;
    L_0x00b3:
        r13 = r7.f;	 Catch:{ JSONException -> 0x00ba }
        r13 = r13.b(r6);	 Catch:{ JSONException -> 0x00ba }
        goto L_0x00c2;
    L_0x00ba:
        r0 = move-exception;
        r13 = r1;
        r1 = r0;
        goto L_0x00ce;
    L_0x00be:
        r13 = r1;
        goto L_0x00c2;
    L_0x00c0:
        r13 = r21;
    L_0x00c2:
        r1 = r13;
        r13 = r6;
        goto L_0x00e4;
    L_0x00c5:
        r0 = move-exception;
        r1 = r0;
        goto L_0x00cc;
    L_0x00c8:
        r0 = move-exception;
        r1 = r0;
        r6 = r16;
    L_0x00cc:
        r13 = r21;
    L_0x00ce:
        r8 = com.inmobi.commons.core.a.a.a();
        r29 = r6;
        r6 = new com.inmobi.commons.core.e.a;
        r6.<init>(r1);
        r8.a(r6);
        r1 = r13;
        r13 = r29;
        goto L_0x00e4;
    L_0x00e0:
        r13 = r16;
        r1 = r21;
    L_0x00e4:
        r6 = r60.hashCode();	 Catch:{ JSONException -> 0x0858 }
        r16 = 8;
        r18 = 9;
        r19 = 5;
        r20 = 4;
        r29 = 3;
        r30 = 7;
        r31 = 6;
        switch(r6) {
            case -1919329183: goto L_0x015e;
            case -1884772963: goto L_0x0153;
            case 67056: goto L_0x0148;
            case 70564: goto L_0x013d;
            case 2241657: goto L_0x0133;
            case 2571565: goto L_0x0129;
            case 69775675: goto L_0x011e;
            case 79826725: goto L_0x0113;
            case 81665115: goto L_0x0107;
            case 1942407129: goto L_0x00fb;
            default: goto L_0x00f9;
        };
    L_0x00f9:
        goto L_0x0176;
    L_0x00fb:
        r6 = "WEBVIEW";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x0103:
        r6 = r31;
        goto L_0x0178;
    L_0x0107:
        r6 = "VIDEO";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x010f:
        r6 = r30;
        goto L_0x0178;
    L_0x0113:
        r6 = "TIMER";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x011b:
        r6 = r29;
        goto L_0x0178;
    L_0x011e:
        r6 = "IMAGE";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x0126:
        r6 = r20;
        goto L_0x0178;
    L_0x0129:
        r6 = "TEXT";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x0131:
        r6 = 1;
        goto L_0x0178;
    L_0x0133:
        r6 = "ICON";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x013b:
        r6 = 2;
        goto L_0x0178;
    L_0x013d:
        r6 = "GIF";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x0145:
        r6 = r19;
        goto L_0x0178;
    L_0x0148:
        r6 = "CTA";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x0150:
        r6 = r18;
        goto L_0x0178;
    L_0x0153:
        r6 = "RATING";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x015b:
        r6 = r16;
        goto L_0x0178;
    L_0x015e:
        r6 = "CONTAINER";
        r6 = r14.equals(r6);	 Catch:{ JSONException -> 0x0168 }
        if (r6 == 0) goto L_0x0176;
    L_0x0166:
        r6 = 0;
        goto L_0x0178;
    L_0x0168:
        r0 = move-exception;
        r1 = r0;
        r56 = r12;
        r33 = r23;
        r55 = r25;
        r32 = r26;
    L_0x0172:
        r34 = r27;
        goto L_0x0864;
    L_0x0176:
        r6 = r17;
    L_0x0178:
        switch(r6) {
            case 0: goto L_0x0662;
            case 1: goto L_0x063f;
            case 2: goto L_0x061f;
            case 3: goto L_0x0564;
            case 4: goto L_0x0495;
            case 5: goto L_0x0495;
            case 6: goto L_0x042b;
            case 7: goto L_0x024d;
            case 8: goto L_0x023f;
            case 9: goto L_0x0187;
            default: goto L_0x017b;
        };
    L_0x017b:
        r56 = r12;
        r33 = r23;
        r55 = r25;
        r32 = r26;
        r34 = r27;
        goto L_0x0870;
    L_0x0187:
        r1 = p(r59);	 Catch:{ JSONException -> 0x0231 }
        if (r1 != 0) goto L_0x018e;
    L_0x018d:
        return r21;
    L_0x018e:
        r8 = r25;
        r1 = r7;
        r32 = r26;
        r6 = r9;
        r1 = r1.c(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x0225 }
        r2 = "assetOnclick";
        r2 = r15.getJSONObject(r2);	 Catch:{ JSONException -> 0x0225 }
        r3 = "openMode";
        r2 = r2.isNull(r3);	 Catch:{ JSONException -> 0x0225 }
        if (r2 != 0) goto L_0x01c2;
    L_0x01a6:
        r2 = "assetOnclick";
        r2 = r15.getJSONObject(r2);	 Catch:{ JSONException -> 0x01b9 }
        r3 = "openMode";
        r2 = r2.getString(r3);	 Catch:{ JSONException -> 0x01b9 }
        r2 = d(r2);	 Catch:{ JSONException -> 0x01b9 }
        r28 = r2;
        goto L_0x01c4;
    L_0x01b9:
        r0 = move-exception;
        r1 = r0;
        r55 = r8;
        r56 = r12;
        r33 = r23;
        goto L_0x0172;
    L_0x01c2:
        r28 = 2;
    L_0x01c4:
        r2 = "assetOnclick";
        r2 = r15.getJSONObject(r2);	 Catch:{ JSONException -> 0x0225 }
        r3 = "fallbackUrl";
        r2 = r2.optString(r3);	 Catch:{ JSONException -> 0x0225 }
        if (r11 == 0) goto L_0x01f3;
    L_0x01d2:
        r3 = r11.size();	 Catch:{ JSONException -> 0x0225 }
        if (r3 != 0) goto L_0x01d9;
    L_0x01d8:
        goto L_0x01f3;
    L_0x01d9:
        r3 = new com.inmobi.ads.an;	 Catch:{ JSONException -> 0x0225 }
        r6 = r8;
        r8 = r3;
        r9 = r12;
        r4 = r11;
        r11 = r1;
        r5 = r12;
        r12 = r13;
        r33 = r23;
        r34 = r27;
        r1 = r61;
        r13 = r4;
        r4 = r14;
        r14 = r28;
        r15 = r59;
        r8.<init>(r9, r10, r11, r12, r13, r14, r15);	 Catch:{ JSONException -> 0x021d }
        r15 = r1;
        goto L_0x0209;
    L_0x01f3:
        r6 = r8;
        r5 = r12;
        r4 = r14;
        r33 = r23;
        r34 = r27;
        r15 = r61;
        r3 = new com.inmobi.ads.an;	 Catch:{ JSONException -> 0x021d }
        r8 = r3;
        r9 = r5;
        r11 = r1;
        r12 = r13;
        r13 = r28;
        r14 = r59;
        r8.<init>(r9, r10, r11, r12, r13, r14);	 Catch:{ JSONException -> 0x021d }
    L_0x0209:
        r3.g = r15;	 Catch:{ JSONException -> 0x021d }
        r14 = r59;
        a(r3, r14);	 Catch:{ JSONException -> 0x021d }
        if (r2 == 0) goto L_0x0215;
    L_0x0212:
        r3.b(r2);	 Catch:{ JSONException -> 0x021d }
    L_0x0215:
        r21 = r3;
        r56 = r5;
        r55 = r6;
        goto L_0x0870;
    L_0x021d:
        r0 = move-exception;
        r1 = r0;
        r56 = r5;
        r55 = r6;
        goto L_0x0864;
    L_0x0225:
        r0 = move-exception;
        r33 = r23;
        r34 = r27;
        r1 = r0;
        r55 = r8;
        r56 = r12;
        goto L_0x0864;
    L_0x0231:
        r0 = move-exception;
        r33 = r23;
        r32 = r26;
        r34 = r27;
        r1 = r0;
        r56 = r12;
        r55 = r25;
        goto L_0x0864;
    L_0x023f:
        r33 = r23;
        r32 = r26;
        r34 = r27;
        r56 = r12;
        r2 = r21;
        r55 = r25;
        goto L_0x0872;
    L_0x024d:
        r8 = r11;
        r11 = r12;
        r12 = r14;
        r14 = r15;
        r33 = r23;
        r35 = r25;
        r32 = r26;
        r34 = r27;
        r15 = r61;
        r6 = r7.h;	 Catch:{ JSONException -> 0x0423 }
        r36 = r8;
        r8 = "VIDEO";
        r6.get(r8);	 Catch:{ JSONException -> 0x0423 }
        r46 = r7.s(r9);	 Catch:{ JSONException -> 0x0423 }
        r6 = new com.inmobi.ads.be$a;	 Catch:{ JSONException -> 0x0423 }
        r8 = r2.x;	 Catch:{ JSONException -> 0x0423 }
        r2 = r2.y;	 Catch:{ JSONException -> 0x0423 }
        r9 = r3.x;	 Catch:{ JSONException -> 0x0423 }
        r3 = r3.y;	 Catch:{ JSONException -> 0x0423 }
        r12 = r4.x;	 Catch:{ JSONException -> 0x0423 }
        r4 = r4.y;	 Catch:{ JSONException -> 0x0423 }
        r15 = r5.x;	 Catch:{ JSONException -> 0x0423 }
        r5 = r5.y;	 Catch:{ JSONException -> 0x0423 }
        r37 = r6;
        r38 = r8;
        r39 = r2;
        r40 = r9;
        r41 = r3;
        r42 = r12;
        r43 = r4;
        r44 = r15;
        r45 = r5;
        r37.<init>(r38, r39, r40, r41, r42, r43, r44, r45, r46);	 Catch:{ JSONException -> 0x0423 }
        r8 = r62;
        if (r8 == 0) goto L_0x02a8;
    L_0x0293:
        r2 = a(r8, r6);	 Catch:{ JSONException -> 0x0423 }
        if (r2 != 0) goto L_0x02a8;
    L_0x0299:
        com.inmobi.commons.core.e.b.a();	 Catch:{ JSONException -> 0x0423 }
        r2 = "ads";
        r3 = "InvalidVideoGeometry";
        r4 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0423 }
        r4.<init>();	 Catch:{ JSONException -> 0x0423 }
        com.inmobi.commons.core.e.b.a(r2, r3, r4);	 Catch:{ JSONException -> 0x0423 }
    L_0x02a8:
        r2 = r7.q;	 Catch:{ JSONException -> 0x0423 }
        if (r2 != 0) goto L_0x02b1;
    L_0x02ac:
        r2 = r7.a(r14, r13, r1);	 Catch:{ JSONException -> 0x0423 }
        goto L_0x02b3;
    L_0x02b1:
        r2 = r7.q;	 Catch:{ JSONException -> 0x0423 }
    L_0x02b3:
        r12 = r2;
        r2 = com.inmobi.ads.AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE;	 Catch:{ JSONException -> 0x0423 }
        r3 = r7.s;	 Catch:{ JSONException -> 0x0423 }
        r4 = 0;
        if (r2 != r3) goto L_0x0366;
    L_0x02bc:
        if (r1 == 0) goto L_0x0321;
    L_0x02be:
        r2 = r1.v;	 Catch:{ JSONException -> 0x0423 }
        r3 = "didRequestFullScreen";
        r2 = r2.get(r3);	 Catch:{ JSONException -> 0x0423 }
        r2 = (java.lang.Boolean) r2;	 Catch:{ JSONException -> 0x0423 }
        r2 = r2.booleanValue();	 Catch:{ JSONException -> 0x0423 }
        if (r2 != 0) goto L_0x02e4;
    L_0x02ce:
        r2 = r7.t;	 Catch:{ JSONException -> 0x0423 }
        if (r2 == 0) goto L_0x02d3;
    L_0x02d2:
        goto L_0x02e4;
    L_0x02d3:
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r3 = 0;
        r5 = 1;
        r13 = 1;
        r15 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r16 = 1;
        r17 = 1;
        r24 = 0;
        goto L_0x03a5;
    L_0x02e4:
        r2 = "loop";
        r3 = 0;
        r2 = r14.optBoolean(r2, r3);	 Catch:{ JSONException -> 0x0423 }
        r3 = "showProgress";
        r8 = 1;
        r3 = r14.optBoolean(r3, r8);	 Catch:{ JSONException -> 0x0423 }
        r9 = "soundOn";
        r9 = r14.optBoolean(r9, r8);	 Catch:{ JSONException -> 0x0423 }
        r13 = "showMute";
        r13 = r14.optBoolean(r13, r8);	 Catch:{ JSONException -> 0x0423 }
        r15 = "autoPlay";
        r8 = r14.optBoolean(r15, r8);	 Catch:{ JSONException -> 0x0423 }
        r15 = r1;
        r15 = (com.inmobi.ads.be) r15;	 Catch:{ JSONException -> 0x0423 }
        r15 = r15.E;	 Catch:{ JSONException -> 0x0423 }
        r47 = r2;
        r2 = "pauseAfter";
        r4 = r14.optDouble(r2, r4);	 Catch:{ JSONException -> 0x0423 }
        r2 = (int) r4;	 Catch:{ JSONException -> 0x0423 }
        r16 = r3;
        r17 = r8;
        r5 = r13;
        r24 = r47;
        r3 = r2;
        r13 = r9;
        r2 = r15;
        r15 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        goto L_0x03a5;
    L_0x0321:
        r2 = "loop";
        r3 = 1;
        r2 = r14.optBoolean(r2, r3);	 Catch:{ JSONException -> 0x0423 }
        r3 = "showProgress";
        r8 = 0;
        r3 = r14.optBoolean(r3, r8);	 Catch:{ JSONException -> 0x0423 }
        r9 = "soundOn";
        r9 = r14.optBoolean(r9, r8);	 Catch:{ JSONException -> 0x0423 }
        r13 = "showMute";
        r8 = r14.optBoolean(r13, r8);	 Catch:{ JSONException -> 0x0423 }
        r13 = "autoPlay";
        r15 = 1;
        r13 = r14.optBoolean(r13, r15);	 Catch:{ JSONException -> 0x0423 }
        r15 = "completeAfter";
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r15 = r14.optInt(r15, r4);	 Catch:{ JSONException -> 0x0423 }
        r4 = "pauseAfter";
        r48 = r2;
        r49 = r3;
        r2 = 0;
        r2 = r14.optDouble(r4, r2);	 Catch:{ JSONException -> 0x0423 }
        r2 = (int) r2;	 Catch:{ JSONException -> 0x0423 }
        r3 = r2;
        r5 = r8;
        r17 = r13;
        r2 = r15;
        r24 = r48;
        r16 = r49;
        r15 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r13 = r9;
        goto L_0x03a5;
    L_0x0366:
        r2 = "loop";
        r8 = 0;
        r2 = r14.optBoolean(r2, r8);	 Catch:{ JSONException -> 0x0423 }
        r3 = "showProgress";
        r15 = 1;
        r3 = r14.optBoolean(r3, r15);	 Catch:{ JSONException -> 0x0423 }
        r4 = "soundOn";
        r4 = r14.optBoolean(r4, r15);	 Catch:{ JSONException -> 0x0423 }
        r5 = "showMute";
        r5 = r14.optBoolean(r5, r15);	 Catch:{ JSONException -> 0x0423 }
        r8 = "autoPlay";
        r8 = r14.optBoolean(r8, r15);	 Catch:{ JSONException -> 0x0423 }
        r9 = "completeAfter";
        r15 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r9 = r14.optInt(r9, r15);	 Catch:{ JSONException -> 0x0423 }
        r13 = "pauseAfter";
        r50 = r2;
        r51 = r3;
        r2 = 0;
        r2 = r14.optDouble(r13, r2);	 Catch:{ JSONException -> 0x0423 }
        r2 = (int) r2;	 Catch:{ JSONException -> 0x0423 }
        r3 = r2;
        r13 = r4;
        r17 = r8;
        r2 = r9;
        r24 = r50;
        r16 = r51;
    L_0x03a5:
        r4 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0423 }
        r4.<init>();	 Catch:{ JSONException -> 0x0423 }
        r8 = "videoViewabilityConfig";
        r8 = r14.isNull(r8);	 Catch:{ JSONException -> 0x0423 }
        if (r8 != 0) goto L_0x03d9;
    L_0x03b2:
        r8 = "videoViewabilityConfig";
        r8 = r14.getJSONObject(r8);	 Catch:{ JSONException -> 0x0423 }
        r9 = r8.keys();	 Catch:{ JSONException -> 0x0423 }
    L_0x03bc:
        r18 = r9.hasNext();	 Catch:{ JSONException -> 0x0423 }
        if (r18 == 0) goto L_0x03d9;
    L_0x03c2:
        r18 = r9.next();	 Catch:{ JSONException -> 0x0423 }
        r15 = r18;
        r15 = (java.lang.String) r15;	 Catch:{ JSONException -> 0x0423 }
        r52 = r9;
        r9 = r8.get(r15);	 Catch:{ JSONException -> 0x0423 }
        r4.put(r15, r9);	 Catch:{ JSONException -> 0x0423 }
        r9 = r52;
        r15 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        goto L_0x03bc;
    L_0x03d9:
        r15 = new com.inmobi.ads.be;	 Catch:{ JSONException -> 0x0423 }
        r8 = r7.r;	 Catch:{ JSONException -> 0x0423 }
        r8 = r8.i;	 Catch:{ JSONException -> 0x0423 }
        r9 = r8.m;	 Catch:{ JSONException -> 0x0423 }
        r53 = r36;
        r8 = r15;
        r20 = r9;
        r9 = r11;
        r54 = r11;
        r11 = r6;
        r6 = r60;
        r14 = r5;
        r6 = r15;
        r5 = r61;
        r22 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r15 = r24;
        r18 = r53;
        r19 = r59;
        r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);	 Catch:{ JSONException -> 0x0453 }
        r15 = r6;
        r15 = (com.inmobi.ads.be) r15;	 Catch:{ JSONException -> 0x0453 }
        r8 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0453 }
        r8.<init>(r4);	 Catch:{ JSONException -> 0x0453 }
        r15.G = r8;	 Catch:{ JSONException -> 0x0453 }
        r15 = r6;
        r15 = (com.inmobi.ads.be) r15;	 Catch:{ JSONException -> 0x0453 }
        if (r2 > 0) goto L_0x040d;
    L_0x040b:
        r2 = r22;
    L_0x040d:
        r15.E = r2;	 Catch:{ JSONException -> 0x0453 }
        r6.g = r5;	 Catch:{ JSONException -> 0x0453 }
        r6.y = r1;	 Catch:{ JSONException -> 0x0453 }
        if (r1 == 0) goto L_0x0417;
    L_0x0415:
        r1.y = r6;	 Catch:{ JSONException -> 0x0453 }
    L_0x0417:
        if (r3 == 0) goto L_0x041e;
    L_0x0419:
        r15 = r6;
        r15 = (com.inmobi.ads.be) r15;	 Catch:{ JSONException -> 0x0453 }
        r15.F = r3;	 Catch:{ JSONException -> 0x0453 }
    L_0x041e:
        r2 = r6;
        r55 = r35;
        goto L_0x055a;
    L_0x0423:
        r0 = move-exception;
        r1 = r0;
        r56 = r11;
        r55 = r35;
        goto L_0x0864;
    L_0x042b:
        r54 = r12;
        r33 = r23;
        r35 = r25;
        r32 = r26;
        r34 = r27;
        r8 = 0;
        r14 = r61;
        r15 = 1;
        if (r13 != 0) goto L_0x043c;
    L_0x043b:
        return r21;
    L_0x043c:
        r1 = h(r59);	 Catch:{ JSONException -> 0x0490 }
        r11 = com.inmobi.ads.bf.c(r1);	 Catch:{ JSONException -> 0x0490 }
        r1 = "URL";
        r1 = r1.equals(r11);	 Catch:{ JSONException -> 0x0490 }
        if (r1 == 0) goto L_0x0459;
    L_0x044c:
        r1 = android.webkit.URLUtil.isValidUrl(r13);	 Catch:{ JSONException -> 0x0453 }
        if (r1 != 0) goto L_0x0459;
    L_0x0452:
        return r21;
    L_0x0453:
        r0 = move-exception;
        r1 = r0;
        r55 = r35;
        goto L_0x061b;
    L_0x0459:
        r12 = r8;
        r1 = r7;
        r55 = r35;
        r8 = r60;
        r6 = r9;
        r4 = r1.a(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x0619 }
        r9 = new com.inmobi.ads.bf;	 Catch:{ JSONException -> 0x0619 }
        r1 = "isScrollable";
        r6 = r59;
        r16 = r6.optBoolean(r1);	 Catch:{ JSONException -> 0x0619 }
        r1 = r9;
        r2 = r54;
        r3 = r10;
        r5 = r13;
        r13 = r6;
        r6 = r16;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x0619 }
        r9.z = r11;	 Catch:{ JSONException -> 0x0619 }
        r9.g = r14;	 Catch:{ JSONException -> 0x0619 }
        r1 = "preload";
        r1 = r13.optBoolean(r1, r12);	 Catch:{ JSONException -> 0x0619 }
        if (r1 == 0) goto L_0x048c;
    L_0x0485:
        r9.A = r15;	 Catch:{ JSONException -> 0x0619 }
        r7.k = r9;	 Catch:{ JSONException -> 0x0619 }
        r2 = r9;
        goto L_0x055a;
    L_0x048c:
        r21 = r9;
        goto L_0x0560;
    L_0x0490:
        r0 = move-exception;
        r55 = r35;
        goto L_0x061a;
    L_0x0495:
        r53 = r11;
        r54 = r12;
        r8 = r14;
        r13 = r15;
        r33 = r23;
        r55 = r25;
        r32 = r26;
        r34 = r27;
        r11 = 2;
        r12 = 0;
        r14 = r61;
        r1 = r7;
        r6 = r9;
        r1 = r1.a(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x0619 }
        r2 = p(r59);	 Catch:{ JSONException -> 0x0619 }
        if (r2 == 0) goto L_0x04e1;
    L_0x04b3:
        r2 = "assetOnclick";
        r2 = r13.getJSONObject(r2);	 Catch:{ JSONException -> 0x0619 }
        r3 = "openMode";
        r2 = r2.isNull(r3);	 Catch:{ JSONException -> 0x0619 }
        if (r2 != 0) goto L_0x04d2;
    L_0x04c1:
        r2 = "assetOnclick";
        r2 = r13.getJSONObject(r2);	 Catch:{ JSONException -> 0x0619 }
        r3 = "openMode";
        r2 = r2.getString(r3);	 Catch:{ JSONException -> 0x0619 }
        r2 = d(r2);	 Catch:{ JSONException -> 0x0619 }
        r11 = r2;
    L_0x04d2:
        r2 = "assetOnclick";
        r2 = r13.getJSONObject(r2);	 Catch:{ JSONException -> 0x0619 }
        r3 = "fallbackUrl";
        r2 = r2.optString(r3);	 Catch:{ JSONException -> 0x0619 }
        r3 = r2;
        r2 = r11;
        goto L_0x04e4;
    L_0x04e1:
        r2 = r12;
        r3 = r21;
    L_0x04e4:
        r6 = r53;
        if (r6 == 0) goto L_0x0525;
    L_0x04e8:
        r4 = r6.size();	 Catch:{ JSONException -> 0x0619 }
        if (r4 != 0) goto L_0x04ef;
    L_0x04ee:
        goto L_0x0525;
    L_0x04ef:
        r4 = "IMAGE";
        r4 = r4.equals(r8);	 Catch:{ JSONException -> 0x0619 }
        if (r4 == 0) goto L_0x050d;
    L_0x04f7:
        r4 = new com.inmobi.ads.as;	 Catch:{ JSONException -> 0x0619 }
        r12 = c(r59);	 Catch:{ JSONException -> 0x0619 }
        r5 = r8;
        r8 = r4;
        r9 = r54;
        r11 = r1;
        r1 = r13;
        r13 = r6;
        r6 = r14;
        r14 = r2;
        r15 = r1;
        r8.<init>(r9, r10, r11, r12, r13, r14, r15);	 Catch:{ JSONException -> 0x0619 }
        r15 = r4;
        r4 = r1;
        goto L_0x054f;
    L_0x050d:
        r5 = r8;
        r4 = r13;
        r15 = r14;
        r16 = new com.inmobi.ads.aq;	 Catch:{ JSONException -> 0x0619 }
        r12 = c(r59);	 Catch:{ JSONException -> 0x0619 }
        r8 = r16;
        r9 = r54;
        r11 = r1;
        r13 = r6;
        r14 = r2;
        r6 = r15;
        r15 = r4;
        r8.<init>(r9, r10, r11, r12, r13, r14, r15);	 Catch:{ JSONException -> 0x0619 }
        r15 = r16;
        goto L_0x054f;
    L_0x0525:
        r5 = r8;
        r4 = r13;
        r6 = r14;
        r8 = "IMAGE";
        r8 = r8.equals(r5);	 Catch:{ JSONException -> 0x0619 }
        if (r8 == 0) goto L_0x0540;
    L_0x0530:
        r15 = new com.inmobi.ads.as;	 Catch:{ JSONException -> 0x0619 }
        r12 = c(r59);	 Catch:{ JSONException -> 0x0619 }
        r8 = r15;
        r9 = r54;
        r11 = r1;
        r13 = r2;
        r14 = r4;
        r8.<init>(r9, r10, r11, r12, r13, r14);	 Catch:{ JSONException -> 0x0619 }
        goto L_0x054f;
    L_0x0540:
        r15 = new com.inmobi.ads.aq;	 Catch:{ JSONException -> 0x0619 }
        r12 = c(r59);	 Catch:{ JSONException -> 0x0619 }
        r8 = r15;
        r9 = r54;
        r11 = r1;
        r13 = r2;
        r14 = r4;
        r8.<init>(r9, r10, r11, r12, r13, r14);	 Catch:{ JSONException -> 0x0619 }
    L_0x054f:
        r15.g = r6;	 Catch:{ JSONException -> 0x0619 }
        a(r15, r4);	 Catch:{ JSONException -> 0x0619 }
        if (r3 == 0) goto L_0x055e;
    L_0x0556:
        r15.b(r3);	 Catch:{ JSONException -> 0x0619 }
        r2 = r15;
    L_0x055a:
        r56 = r54;
        goto L_0x0872;
    L_0x055e:
        r21 = r15;
    L_0x0560:
        r56 = r54;
        goto L_0x0870;
    L_0x0564:
        r54 = r12;
        r8 = r14;
        r14 = r15;
        r33 = r23;
        r55 = r25;
        r32 = r26;
        r34 = r27;
        r6 = r61;
        r11 = 2;
        r12 = 0;
        r15 = 1;
        r1 = r7;
        r13 = r6;
        r6 = r9;
        r1 = r1.a(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x0619 }
        r2 = "startOffset";
        r2 = r14.has(r2);	 Catch:{ JSONException -> 0x0619 }
        if (r2 == 0) goto L_0x058f;
    L_0x0584:
        r2 = "startOffset";
        r2 = r14.getJSONObject(r2);	 Catch:{ JSONException -> 0x0619 }
        r2 = r7.q(r2);	 Catch:{ JSONException -> 0x0619 }
        goto L_0x0591;
    L_0x058f:
        r2 = r21;
    L_0x0591:
        r3 = "timerDuration";
        r3 = r14.has(r3);	 Catch:{ JSONException -> 0x0619 }
        if (r3 == 0) goto L_0x05a4;
    L_0x0599:
        r3 = "timerDuration";
        r3 = r14.getJSONObject(r3);	 Catch:{ JSONException -> 0x0619 }
        r3 = r7.q(r3);	 Catch:{ JSONException -> 0x0619 }
        goto L_0x05a6;
    L_0x05a4:
        r3 = r21;
    L_0x05a6:
        r4 = "displayTimer";
        r4 = r14.optBoolean(r4, r15);	 Catch:{ JSONException -> 0x0619 }
        r5 = new com.inmobi.ads.ba;	 Catch:{ JSONException -> 0x0619 }
        r5.<init>(r2, r3);	 Catch:{ JSONException -> 0x0619 }
        r2 = new com.inmobi.ads.bb;	 Catch:{ JSONException -> 0x0619 }
        r6 = r54;
        r2.<init>(r6, r10, r1, r5);	 Catch:{ JSONException -> 0x0613 }
        r2.z = r4;	 Catch:{ JSONException -> 0x0613 }
        r1 = "assetOnFinish";
        r1 = r14.has(r1);	 Catch:{ JSONException -> 0x0613 }
        if (r1 == 0) goto L_0x060d;
    L_0x05c2:
        r1 = "assetOnFinish";
        r1 = r14.get(r1);	 Catch:{ JSONException -> 0x0613 }
        r1 = (org.json.JSONObject) r1;	 Catch:{ JSONException -> 0x0613 }
        r3 = "action";
        r3 = r1.has(r3);	 Catch:{ JSONException -> 0x0613 }
        if (r3 == 0) goto L_0x060d;
    L_0x05d2:
        r3 = "action";
        r1 = r1.getString(r3);	 Catch:{ JSONException -> 0x0613 }
        r3 = java.util.Locale.US;	 Catch:{ JSONException -> 0x0613 }
        r1 = r1.toUpperCase(r3);	 Catch:{ JSONException -> 0x0613 }
        r1 = r1.trim();	 Catch:{ JSONException -> 0x0613 }
        r3 = r1.hashCode();	 Catch:{ JSONException -> 0x0613 }
        r4 = 2142494; // 0x20b11e float:3.002274E-39 double:1.0585327E-317;
        if (r3 == r4) goto L_0x05fb;
    L_0x05eb:
        r4 = 2402104; // 0x24a738 float:3.366065E-39 double:1.186797E-317;
        if (r3 == r4) goto L_0x05f1;
    L_0x05f0:
        goto L_0x0605;
    L_0x05f1:
        r3 = "NONE";
        r1 = r1.equals(r3);	 Catch:{ JSONException -> 0x0613 }
        if (r1 == 0) goto L_0x0605;
    L_0x05f9:
        r1 = r15;
        goto L_0x0607;
    L_0x05fb:
        r3 = "EXIT";
        r1 = r1.equals(r3);	 Catch:{ JSONException -> 0x0613 }
        if (r1 == 0) goto L_0x0605;
    L_0x0603:
        r1 = r11;
        goto L_0x0607;
    L_0x0605:
        r1 = r17;
    L_0x0607:
        if (r1 == r11) goto L_0x060a;
    L_0x0609:
        goto L_0x060b;
    L_0x060a:
        r12 = r15;
    L_0x060b:
        r2.k = r12;	 Catch:{ JSONException -> 0x0613 }
    L_0x060d:
        r2.g = r13;	 Catch:{ JSONException -> 0x0613 }
        r56 = r6;
        goto L_0x0872;
    L_0x0613:
        r0 = move-exception;
        r1 = r0;
        r56 = r6;
        goto L_0x0864;
    L_0x0619:
        r0 = move-exception;
    L_0x061a:
        r1 = r0;
    L_0x061b:
        r56 = r54;
        goto L_0x0864;
    L_0x061f:
        r6 = r12;
        r8 = r14;
        r14 = r15;
        r33 = r23;
        r55 = r25;
        r32 = r26;
        r34 = r27;
        r13 = r61;
        r1 = r7;
        r11 = r6;
        r6 = r9;
        r1 = r1.a(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x065c }
        r2 = new com.inmobi.ads.ar;	 Catch:{ JSONException -> 0x065c }
        r3 = c(r59);	 Catch:{ JSONException -> 0x065c }
        r2.<init>(r11, r10, r1, r3);	 Catch:{ JSONException -> 0x065c }
        r2.g = r13;	 Catch:{ JSONException -> 0x065c }
        goto L_0x0658;
    L_0x063f:
        r11 = r12;
        r8 = r14;
        r33 = r23;
        r55 = r25;
        r32 = r26;
        r34 = r27;
        r12 = r61;
        r1 = r7;
        r6 = r9;
        r1 = r1.b(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x065c }
        r2 = new com.inmobi.ads.az;	 Catch:{ JSONException -> 0x065c }
        r2.<init>(r11, r10, r1, r13);	 Catch:{ JSONException -> 0x065c }
        r2.g = r12;	 Catch:{ JSONException -> 0x065c }
    L_0x0658:
        r56 = r11;
        goto L_0x0872;
    L_0x065c:
        r0 = move-exception;
        r1 = r0;
        r56 = r11;
        goto L_0x0864;
    L_0x0662:
        r6 = r11;
        r56 = r12;
        r13 = r14;
        r14 = r15;
        r33 = r23;
        r55 = r25;
        r32 = r26;
        r34 = r27;
        r8 = r62;
        r11 = 2;
        r12 = 0;
        r15 = 1;
        r1 = r7;
        r12 = r6;
        r6 = r9;
        r1 = r1.a(r2, r3, r4, r5, r6);	 Catch:{ JSONException -> 0x0856 }
        if (r8 == 0) goto L_0x0692;
    L_0x067d:
        r2 = a(r8, r1);	 Catch:{ JSONException -> 0x0856 }
        if (r2 != 0) goto L_0x0692;
    L_0x0683:
        com.inmobi.commons.core.e.b.a();	 Catch:{ JSONException -> 0x0856 }
        r2 = "ads";
        r3 = "InvalidContainerGeometry";
        r4 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0856 }
        r4.<init>();	 Catch:{ JSONException -> 0x0856 }
        com.inmobi.commons.core.e.b.a(r2, r3, r4);	 Catch:{ JSONException -> 0x0856 }
    L_0x0692:
        r2 = p(r59);	 Catch:{ JSONException -> 0x0856 }
        if (r2 == 0) goto L_0x06c7;
    L_0x0698:
        r2 = "assetOnclick";
        r2 = r14.getJSONObject(r2);	 Catch:{ JSONException -> 0x0856 }
        r3 = "openMode";
        r2 = r2.isNull(r3);	 Catch:{ JSONException -> 0x0856 }
        if (r2 != 0) goto L_0x06b7;
    L_0x06a6:
        r2 = "assetOnclick";
        r2 = r14.getJSONObject(r2);	 Catch:{ JSONException -> 0x0856 }
        r3 = "openMode";
        r2 = r2.getString(r3);	 Catch:{ JSONException -> 0x0856 }
        r8 = d(r2);	 Catch:{ JSONException -> 0x0856 }
        goto L_0x06b8;
    L_0x06b7:
        r8 = r11;
    L_0x06b8:
        r2 = "assetOnclick";
        r2 = r14.getJSONObject(r2);	 Catch:{ JSONException -> 0x0856 }
        r3 = "fallbackUrl";
        r2 = r2.optString(r3);	 Catch:{ JSONException -> 0x0856 }
        r3 = r2;
        r2 = r8;
        goto L_0x06ca;
    L_0x06c7:
        r3 = r21;
        r2 = 0;
    L_0x06ca:
        r4 = "transitionEffect";
        r4 = r9.has(r4);	 Catch:{ JSONException -> 0x0856 }
        if (r4 == 0) goto L_0x0706;
    L_0x06d2:
        r4 = "transitionEffect";
        r4 = r9.getString(r4);	 Catch:{ JSONException -> 0x0856 }
        r4 = r4.trim();	 Catch:{ JSONException -> 0x0856 }
        r5 = r4.hashCode();	 Catch:{ JSONException -> 0x0856 }
        r6 = 3151468; // 0x30166c float:4.416147E-39 double:1.557032E-317;
        if (r5 == r6) goto L_0x06f5;
    L_0x06e5:
        r6 = 106426293; // 0x657efb5 float:4.0613115E-35 double:5.2581575E-316;
        if (r5 == r6) goto L_0x06eb;
    L_0x06ea:
        goto L_0x06ff;
    L_0x06eb:
        r5 = "paged";
        r4 = r4.equals(r5);	 Catch:{ JSONException -> 0x0856 }
        if (r4 == 0) goto L_0x06ff;
    L_0x06f3:
        r4 = r15;
        goto L_0x0701;
    L_0x06f5:
        r5 = "free";
        r4 = r4.equals(r5);	 Catch:{ JSONException -> 0x0856 }
        if (r4 == 0) goto L_0x06ff;
    L_0x06fd:
        r4 = r11;
        goto L_0x0701;
    L_0x06ff:
        r4 = r17;
    L_0x0701:
        if (r4 == r11) goto L_0x0704;
    L_0x0703:
        goto L_0x0706;
    L_0x0704:
        r4 = r15;
        goto L_0x0707;
    L_0x0706:
        r4 = 0;
    L_0x0707:
        if (r12 == 0) goto L_0x0722;
    L_0x0709:
        r5 = r12.size();	 Catch:{ JSONException -> 0x0856 }
        if (r5 != 0) goto L_0x0710;
    L_0x070f:
        goto L_0x0722;
    L_0x0710:
        r5 = new com.inmobi.ads.am;	 Catch:{ JSONException -> 0x0856 }
        r8 = r5;
        r9 = r56;
        r6 = r11;
        r11 = r1;
        r13 = r2;
        r2 = r14;
        r22 = r15;
        r15 = r4;
        r8.<init>(r9, r10, r11, r12, r13, r14, r15);	 Catch:{ JSONException -> 0x0856 }
        r15 = r5;
        r5 = r2;
        goto L_0x0732;
    L_0x0722:
        r6 = r11;
        r5 = r14;
        r22 = r15;
        r15 = new com.inmobi.ads.am;	 Catch:{ JSONException -> 0x0856 }
        r8 = r15;
        r9 = r56;
        r11 = r1;
        r12 = r2;
        r13 = r5;
        r14 = r4;
        r8.<init>(r9, r10, r11, r12, r13, r14);	 Catch:{ JSONException -> 0x0856 }
    L_0x0732:
        r2 = r61;
        r15.g = r2;	 Catch:{ JSONException -> 0x0856 }
        if (r3 == 0) goto L_0x073b;
    L_0x0738:
        r15.b(r3);	 Catch:{ JSONException -> 0x0856 }
    L_0x073b:
        a(r15, r5);	 Catch:{ JSONException -> 0x0856 }
        r3 = "assetValue";
        r3 = r5.getJSONArray(r3);	 Catch:{ JSONException -> 0x0856 }
        r4 = 0;
    L_0x0745:
        r5 = r3.length();	 Catch:{ JSONException -> 0x0856 }
        if (r4 >= r5) goto L_0x0854;
    L_0x074b:
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0856 }
        r5.<init>();	 Catch:{ JSONException -> 0x0856 }
        r5.append(r2);	 Catch:{ JSONException -> 0x0856 }
        r8 = ".assetValue[";
        r5.append(r8);	 Catch:{ JSONException -> 0x0856 }
        r5.append(r4);	 Catch:{ JSONException -> 0x0856 }
        r8 = "]";
        r5.append(r8);	 Catch:{ JSONException -> 0x0856 }
        r5 = r5.toString();	 Catch:{ JSONException -> 0x0856 }
        r8 = r3.getJSONObject(r4);	 Catch:{ JSONException -> 0x0856 }
        r9 = f(r8);	 Catch:{ JSONException -> 0x0856 }
        r10 = java.util.Locale.US;	 Catch:{ JSONException -> 0x0856 }
        r9 = r9.toLowerCase(r10);	 Catch:{ JSONException -> 0x0856 }
        r9 = r9.trim();	 Catch:{ JSONException -> 0x0856 }
        r10 = r9.hashCode();	 Catch:{ JSONException -> 0x0856 }
        switch(r10) {
            case -938102371: goto L_0x07e2;
            case -410956671: goto L_0x07d7;
            case 98832: goto L_0x07cc;
            case 102340: goto L_0x07c1;
            case 3226745: goto L_0x07b7;
            case 3556653: goto L_0x07ac;
            case 100313435: goto L_0x07a1;
            case 110364485: goto L_0x0796;
            case 112202875: goto L_0x078b;
            case 1224424441: goto L_0x077f;
            default: goto L_0x077d;
        };	 Catch:{ JSONException -> 0x0856 }
    L_0x077d:
        goto L_0x07ed;
    L_0x077f:
        r10 = "webview";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x0787:
        r9 = r18;
        goto L_0x07ef;
    L_0x078b:
        r10 = "video";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x0793:
        r9 = r20;
        goto L_0x07ef;
    L_0x0796:
        r10 = "timer";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x079e:
        r9 = r16;
        goto L_0x07ef;
    L_0x07a1:
        r10 = "image";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x07a9:
        r9 = r29;
        goto L_0x07ef;
    L_0x07ac:
        r10 = "text";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x07b4:
        r9 = r19;
        goto L_0x07ef;
    L_0x07b7:
        r10 = "icon";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x07bf:
        r9 = r6;
        goto L_0x07ef;
    L_0x07c1:
        r10 = "gif";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x07c9:
        r9 = 10;
        goto L_0x07ef;
    L_0x07cc:
        r10 = "cta";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x07d4:
        r9 = r31;
        goto L_0x07ef;
    L_0x07d7:
        r10 = "container";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x07df:
        r9 = r22;
        goto L_0x07ef;
    L_0x07e2:
        r10 = "rating";
        r9 = r9.equals(r10);	 Catch:{ JSONException -> 0x0856 }
        if (r9 == 0) goto L_0x07ed;
    L_0x07ea:
        r9 = r30;
        goto L_0x07ef;
    L_0x07ed:
        r9 = r17;
    L_0x07ef:
        switch(r9) {
            case 2: goto L_0x080d;
            case 3: goto L_0x080a;
            case 4: goto L_0x0807;
            case 5: goto L_0x0804;
            case 6: goto L_0x0801;
            case 7: goto L_0x07fe;
            case 8: goto L_0x07fb;
            case 9: goto L_0x07f8;
            case 10: goto L_0x07f5;
            default: goto L_0x07f2;
        };	 Catch:{ JSONException -> 0x0856 }
    L_0x07f2:
        r9 = "CONTAINER";
        goto L_0x080f;
    L_0x07f5:
        r9 = "GIF";
        goto L_0x080f;
    L_0x07f8:
        r9 = "WEBVIEW";
        goto L_0x080f;
    L_0x07fb:
        r9 = "TIMER";
        goto L_0x080f;
    L_0x07fe:
        r9 = "RATING";
        goto L_0x080f;
    L_0x0801:
        r9 = "CTA";
        goto L_0x080f;
    L_0x0804:
        r9 = "TEXT";
        goto L_0x080f;
    L_0x0807:
        r9 = "VIDEO";
        goto L_0x080f;
    L_0x080a:
        r9 = "IMAGE";
        goto L_0x080f;
    L_0x080d:
        r9 = "ICON";
    L_0x080f:
        r9 = r7.a(r8, r9, r5, r1);	 Catch:{ JSONException -> 0x0856 }
        if (r9 != 0) goto L_0x0820;
    L_0x0815:
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0856 }
        r9 = "Cannot build asset from JSON: ";
        r5.<init>(r9);	 Catch:{ JSONException -> 0x0856 }
        r5.append(r8);	 Catch:{ JSONException -> 0x0856 }
        goto L_0x084f;
    L_0x0820:
        r9.g = r5;	 Catch:{ JSONException -> 0x0856 }
        r9.t = r15;	 Catch:{ JSONException -> 0x0856 }
        r5 = r15.C;	 Catch:{ JSONException -> 0x0856 }
        r8 = 16;
        if (r5 >= r8) goto L_0x084f;
    L_0x082a:
        r5 = r15.C;	 Catch:{ JSONException -> 0x0856 }
        r8 = r15.B;	 Catch:{ JSONException -> 0x0856 }
        r8 = r8.length;	 Catch:{ JSONException -> 0x0856 }
        if (r5 != r8) goto L_0x0843;
    L_0x0831:
        r5 = r15.B;	 Catch:{ JSONException -> 0x0856 }
        r5 = r5.length;	 Catch:{ JSONException -> 0x0856 }
        r8 = r6 * r5;
        r5 = new com.inmobi.ads.ak[r8];	 Catch:{ JSONException -> 0x0856 }
        r8 = r15.B;	 Catch:{ JSONException -> 0x0856 }
        r10 = r15.C;	 Catch:{ JSONException -> 0x0856 }
        r11 = 0;
        java.lang.System.arraycopy(r8, r11, r5, r11, r10);	 Catch:{ JSONException -> 0x0856 }
        r15.B = r5;	 Catch:{ JSONException -> 0x0856 }
        goto L_0x0844;
    L_0x0843:
        r11 = 0;
    L_0x0844:
        r5 = r15.B;	 Catch:{ JSONException -> 0x0856 }
        r8 = r15.C;	 Catch:{ JSONException -> 0x0856 }
        r10 = r8 + 1;
        r15.C = r10;	 Catch:{ JSONException -> 0x0856 }
        r5[r8] = r9;	 Catch:{ JSONException -> 0x0856 }
        goto L_0x0850;
    L_0x084f:
        r11 = 0;
    L_0x0850:
        r4 = r4 + 1;
        goto L_0x0745;
    L_0x0854:
        r2 = r15;
        goto L_0x0872;
    L_0x0856:
        r0 = move-exception;
        goto L_0x0863;
    L_0x0858:
        r0 = move-exception;
        r56 = r12;
        r33 = r23;
        r55 = r25;
        r32 = r26;
        r34 = r27;
    L_0x0863:
        r1 = r0;
    L_0x0864:
        r2 = com.inmobi.commons.core.a.a.a();
        r3 = new com.inmobi.commons.core.e.a;
        r3.<init>(r1);
        r2.a(r3);
    L_0x0870:
        r2 = r21;
    L_0x0872:
        if (r2 == 0) goto L_0x08cc;
    L_0x0874:
        r1 = r32;
        r2.n = r1;
        r1 = r33;
        r2.o = r1;
        r1 = r34;
        r2.p = r1;
        r1 = r55;
        r2.q = r1;
        if (r1 == 0) goto L_0x0894;
    L_0x0886:
        r3 = r1.length();
        if (r3 == 0) goto L_0x0894;
    L_0x088c:
        r3 = r7.p;
        r4 = r56;
        r3.put(r4, r1);
        goto L_0x0896;
    L_0x0894:
        r4 = r56;
    L_0x0896:
        r1 = r4.length();
        if (r1 == 0) goto L_0x08a9;
    L_0x089c:
        r1 = r7.o;
        r1 = r1.containsKey(r4);
        if (r1 != 0) goto L_0x08a9;
    L_0x08a4:
        r1 = r7.o;
        r1.put(r4, r2);
    L_0x08a9:
        r1 = r7.h;
        r3 = r60;
        r1 = r1.containsKey(r3);
        if (r1 == 0) goto L_0x08bf;
    L_0x08b3:
        r1 = r7.h;
        r1 = r1.get(r3);
        r1 = (java.util.List) r1;
        r1.add(r2);
        goto L_0x08cc;
    L_0x08bf:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r1.add(r2);
        r4 = r7.h;
        r4.put(r3, r1);
    L_0x08cc:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.a(org.json.JSONObject, java.lang.String, java.lang.String, com.inmobi.ads.al):com.inmobi.ads.ak");
    }

    private static void a(@NonNull ak akVar, @NonNull JSONObject jSONObject) throws JSONException {
        String str = "";
        String str2 = "";
        boolean z = false;
        if (p(jSONObject)) {
            if (jSONObject.getJSONObject("assetOnclick").isNull("itemUrl")) {
                new StringBuilder("Missing itemUrl on asset ").append(jSONObject.toString());
            } else {
                str = jSONObject.getJSONObject("assetOnclick").getString("itemUrl");
                z = true;
            }
            if (!jSONObject.getJSONObject("assetOnclick").isNull(NativeProtocol.WEB_DIALOG_ACTION)) {
                str2 = jSONObject.getJSONObject("assetOnclick").getString(NativeProtocol.WEB_DIALOG_ACTION);
                z = true;
            }
        }
        akVar.a(str);
        akVar.j = str2;
        akVar.h = z;
    }

    /* Access modifiers changed, original: final */
    @Nullable
    public final ak b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (this.o.get(str) != null) {
            return (ak) this.o.get(str);
        }
        if (this.f != null) {
            return (ak) this.f.o.get(str);
        }
        return null;
    }

    /* Access modifiers changed, original: final */
    public final List<ak> c(String str) {
        if (this.h.containsKey(str)) {
            return (List) this.h.get(str);
        }
        return Collections.emptyList();
    }

    private static boolean a(JSONObject jSONObject, String str) {
        if (jSONObject.isNull("geometry")) {
            return false;
        }
        try {
            if (!a(jSONObject.getJSONArray("geometry"))) {
                return false;
            }
            boolean z = true;
            switch (str.hashCode()) {
                case -1919329183:
                    if (str.equals("CONTAINER")) {
                        z = true;
                        break;
                    }
                    break;
                case 67056:
                    if (str.equals("CTA")) {
                        z = true;
                        break;
                    }
                    break;
                case 70564:
                    if (str.equals("GIF")) {
                        z = true;
                        break;
                    }
                    break;
                case 2241657:
                    if (str.equals("ICON")) {
                        z = true;
                        break;
                    }
                    break;
                case 2571565:
                    if (str.equals("TEXT")) {
                        z = true;
                        break;
                    }
                    break;
                case 69775675:
                    if (str.equals(ShareConstants.IMAGE_URL)) {
                        z = true;
                        break;
                    }
                    break;
                case 79826725:
                    if (str.equals("TIMER")) {
                        z = true;
                        break;
                    }
                    break;
                case 81665115:
                    if (str.equals(ShareConstants.VIDEO_URL)) {
                        z = true;
                        break;
                    }
                    break;
                case 1942407129:
                    if (str.equals("WEBVIEW")) {
                        z = true;
                        break;
                    }
                    break;
                default:
                    break;
            }
            switch (z) {
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                    return true;
                case true:
                case true:
                    if (jSONObject.isNull(MimeTypes.BASE_TYPE_TEXT)) {
                        return false;
                    }
                    try {
                        if (((int) Double.parseDouble(jSONObject.getJSONObject(MimeTypes.BASE_TYPE_TEXT).getString("size"))) > 0) {
                            return true;
                        }
                        return false;
                    } catch (NumberFormatException e) {
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        return false;
                    }
                default:
                    return false;
            }
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return false;
        }
    }

    private static boolean a(JSONArray jSONArray) {
        try {
            int i = jSONArray.getInt(2);
            int i2 = jSONArray.getInt(3);
            if (i <= 0 || i2 <= 0) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        }
    }

    private static NativeTracker a(int i, TrackerEventType trackerEventType, JSONObject jSONObject) throws JSONException {
        String trim = jSONObject.isNull("url") ? "" : jSONObject.getString("url").trim();
        HashMap hashMap = new HashMap();
        if (TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER == trackerEventType) {
            JSONArray optJSONArray = jSONObject.optJSONArray("events");
            if ((trim.length() == 0 || ((trim.startsWith("http") && !URLUtil.isValidUrl(trim)) || !trim.startsWith("http"))) && optJSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    TrackerEventType a = NativeTracker.a(optJSONArray.getString(i2));
                    if (a == TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW || a == TrackerEventType.TRACKER_EVENT_TYPE_PLAY || a == TrackerEventType.TRACKER_EVENT_TYPE_RENDER) {
                        arrayList.add(a);
                    }
                }
            }
            hashMap.put("referencedEvents", arrayList);
        } else if (trim.length() == 0 || !URLUtil.isValidUrl(trim)) {
            return null;
        }
        HashMap hashMap2 = new HashMap();
        try {
            if (!jSONObject.isNull(NativeProtocol.WEB_DIALOG_PARAMS)) {
                jSONObject = jSONObject.getJSONObject(NativeProtocol.WEB_DIALOG_PARAMS);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap2.put(str, jSONObject.getString(str));
                }
            }
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        NativeTracker nativeTracker = new NativeTracker(trim, i, trackerEventType, hashMap2);
        nativeTracker.d = new HashMap(hashMap);
        return nativeTracker;
    }

    private static List<NativeTracker> a(JSONObject jSONObject) {
        LinkedList linkedList = new LinkedList();
        try {
            jSONObject = jSONObject.getJSONObject("passThroughJson");
            HashMap hashMap = new HashMap();
            if (!jSONObject.isNull("macros")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("macros");
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap.put(str, jSONObject2.getString(str));
                }
            }
            if (!jSONObject.isNull("urls")) {
                JSONArray jSONArray = jSONObject.getJSONArray("urls");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    linkedList.add(new NativeTracker(jSONArray.getString(i), 0, TrackerEventType.TRACKER_EVENT_TYPE_IAS, hashMap));
                }
            }
            if (linkedList.isEmpty()) {
                linkedList.add(new NativeTracker("", 0, TrackerEventType.TRACKER_EVENT_TYPE_IAS, hashMap));
            }
        } catch (Exception e) {
            new StringBuilder("Failed to parse IAS tracker : ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return linkedList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0173 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x017f A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x017c A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0179 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0176 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0173 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x017f A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x017c A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0179 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0176 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0173 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x017f A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x017c A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0179 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0176 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0173 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x017f A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x017c A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0179 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0176 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x019a A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0089 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0089 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x019a A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079 A:{Catch:{ JSONException -> 0x019f }} */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x019a A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0089 A:{Catch:{ JSONException -> 0x019f }} */
    private static java.util.List<com.inmobi.ads.NativeTracker> b(@android.support.annotation.NonNull org.json.JSONObject r14) {
        /*
        r0 = "trackers";
        r0 = r14.isNull(r0);
        if (r0 == 0) goto L_0x000a;
    L_0x0008:
        r14 = 0;
        return r14;
    L_0x000a:
        r0 = new java.util.LinkedList;
        r0.<init>();
        r1 = "trackers";
        r14 = r14.getJSONArray(r1);	 Catch:{ JSONException -> 0x019f }
        r1 = r14.length();	 Catch:{ JSONException -> 0x019f }
        if (r1 != 0) goto L_0x001c;
    L_0x001b:
        return r0;
    L_0x001c:
        r2 = 0;
        r3 = r2;
    L_0x001e:
        if (r3 >= r1) goto L_0x019e;
    L_0x0020:
        r4 = r14.getJSONObject(r3);	 Catch:{ JSONException -> 0x019f }
        r5 = "trackerType";
        r5 = r4.isNull(r5);	 Catch:{ JSONException -> 0x019f }
        if (r5 != 0) goto L_0x019a;
    L_0x002c:
        r5 = "trackerType";
        r5 = r4.getString(r5);	 Catch:{ JSONException -> 0x019f }
        r6 = java.util.Locale.US;	 Catch:{ JSONException -> 0x019f }
        r5 = r5.toUpperCase(r6);	 Catch:{ JSONException -> 0x019f }
        r5 = r5.trim();	 Catch:{ JSONException -> 0x019f }
        r6 = r5.hashCode();	 Catch:{ JSONException -> 0x019f }
        r7 = -1430070305; // 0xffffffffaac2dbdf float:-3.461389E-13 double:NaN;
        r8 = 3;
        r9 = 2;
        r10 = -1;
        r11 = 1;
        if (r6 == r7) goto L_0x0068;
    L_0x0049:
        r7 = -158113182; // 0xfffffffff6936262 float:-1.4946545E33 double:NaN;
        if (r6 == r7) goto L_0x005e;
    L_0x004e:
        r7 = 1110926088; // 0x42376308 float:45.84671 double:5.48870415E-315;
        if (r6 == r7) goto L_0x0054;
    L_0x0053:
        goto L_0x0072;
    L_0x0054:
        r6 = "URL_WEBVIEW_PING";
        r5 = r5.equals(r6);	 Catch:{ JSONException -> 0x019f }
        if (r5 == 0) goto L_0x0072;
    L_0x005c:
        r5 = r9;
        goto L_0x0073;
    L_0x005e:
        r6 = "URL_PING";
        r5 = r5.equals(r6);	 Catch:{ JSONException -> 0x019f }
        if (r5 == 0) goto L_0x0072;
    L_0x0066:
        r5 = r11;
        goto L_0x0073;
    L_0x0068:
        r6 = "HTML_SCRIPT";
        r5 = r5.equals(r6);	 Catch:{ JSONException -> 0x019f }
        if (r5 == 0) goto L_0x0072;
    L_0x0070:
        r5 = r8;
        goto L_0x0073;
    L_0x0072:
        r5 = r10;
    L_0x0073:
        switch(r5) {
            case 1: goto L_0x007f;
            case 2: goto L_0x007c;
            case 3: goto L_0x0079;
            default: goto L_0x0076;
        };	 Catch:{ JSONException -> 0x019f }
    L_0x0076:
        r5 = "unknown";
        goto L_0x0081;
    L_0x0079:
        r5 = "html_script";
        goto L_0x0081;
    L_0x007c:
        r5 = "webview_ping";
        goto L_0x0081;
    L_0x007f:
        r5 = "url_ping";
    L_0x0081:
        r6 = "url_ping";
        r5 = r6.equals(r5);	 Catch:{ JSONException -> 0x019f }
        if (r5 == 0) goto L_0x019a;
    L_0x0089:
        r5 = "eventId";
        r5 = r4.optInt(r5, r2);	 Catch:{ JSONException -> 0x019f }
        r6 = "uiEvent";
        r6 = r4.isNull(r6);	 Catch:{ JSONException -> 0x019f }
        if (r6 != 0) goto L_0x019a;
    L_0x0097:
        r6 = "uiEvent";
        r6 = r4.getString(r6);	 Catch:{ JSONException -> 0x019f }
        r7 = java.util.Locale.US;	 Catch:{ JSONException -> 0x019f }
        r7 = r6.toUpperCase(r7);	 Catch:{ JSONException -> 0x019f }
        r7 = r7.trim();	 Catch:{ JSONException -> 0x019f }
        r12 = r7.hashCode();	 Catch:{ JSONException -> 0x019f }
        r13 = 4;
        switch(r12) {
            case -1881262698: goto L_0x00f7;
            case -825499301: goto L_0x00ec;
            case -45894975: goto L_0x00e2;
            case 2342118: goto L_0x00d8;
            case 2634405: goto L_0x00ce;
            case 64212328: goto L_0x00c4;
            case 1963885793: goto L_0x00ba;
            case 2008409463: goto L_0x00b0;
            default: goto L_0x00af;
        };	 Catch:{ JSONException -> 0x019f }
    L_0x00af:
        goto L_0x0101;
    L_0x00b0:
        r12 = "CLIENT_FILL";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00b8:
        r7 = r9;
        goto L_0x0102;
    L_0x00ba:
        r12 = "VIDEO_VIEWABILITY";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00c2:
        r7 = 6;
        goto L_0x0102;
    L_0x00c4:
        r12 = "CLICK";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00cc:
        r7 = 5;
        goto L_0x0102;
    L_0x00ce:
        r12 = "VIEW";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00d6:
        r7 = r13;
        goto L_0x0102;
    L_0x00d8:
        r12 = "LOAD";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00e0:
        r7 = r11;
        goto L_0x0102;
    L_0x00e2:
        r12 = "IAS_VIEWABILITY";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00ea:
        r7 = 7;
        goto L_0x0102;
    L_0x00ec:
        r12 = "FALLBACK_URL_CLICK";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00f4:
        r7 = 8;
        goto L_0x0102;
    L_0x00f7:
        r12 = "RENDER";
        r7 = r7.equals(r12);	 Catch:{ JSONException -> 0x019f }
        if (r7 == 0) goto L_0x0101;
    L_0x00ff:
        r7 = r8;
        goto L_0x0102;
    L_0x0101:
        r7 = r10;
    L_0x0102:
        switch(r7) {
            case 1: goto L_0x0124;
            case 2: goto L_0x0120;
            case 3: goto L_0x011c;
            case 4: goto L_0x0118;
            case 5: goto L_0x0114;
            case 6: goto L_0x0110;
            case 7: goto L_0x010c;
            case 8: goto L_0x0108;
            default: goto L_0x0105;
        };	 Catch:{ JSONException -> 0x019f }
    L_0x0105:
        r7 = java.util.Locale.US;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0127;
    L_0x0108:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_FALLBACK_URL;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x010c:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0110:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0114:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0118:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x011c:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0120:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0124:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_LOAD;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0127:
        r6 = r6.toUpperCase(r7);	 Catch:{ JSONException -> 0x019f }
        r6 = r6.trim();	 Catch:{ JSONException -> 0x019f }
        r7 = r6.hashCode();	 Catch:{ JSONException -> 0x019f }
        r12 = -1836567951; // 0xffffffff92883271 float:-8.595241E-28 double:NaN;
        if (r7 == r12) goto L_0x0165;
    L_0x0138:
        r8 = -1099027408; // 0xffffffffbe7e2c30 float:-0.24821544 double:NaN;
        if (r7 == r8) goto L_0x015b;
    L_0x013d:
        r8 = 1331888222; // 0x4f63005e float:3.8084521E9 double:6.580402146E-315;
        if (r7 == r8) goto L_0x0152;
    L_0x0142:
        r8 = 1346121898; // 0x503c30aa float:1.26292234E10 double:6.65072585E-315;
        if (r7 == r8) goto L_0x0148;
    L_0x0147:
        goto L_0x016f;
    L_0x0148:
        r7 = "DOWNLOADER_INITIALIZED";
        r6 = r6.equals(r7);	 Catch:{ JSONException -> 0x019f }
        if (r6 == 0) goto L_0x016f;
    L_0x0150:
        r13 = r11;
        goto L_0x0170;
    L_0x0152:
        r7 = "DOWNLOADER_ERROR";
        r6 = r6.equals(r7);	 Catch:{ JSONException -> 0x019f }
        if (r6 == 0) goto L_0x016f;
    L_0x015a:
        goto L_0x0170;
    L_0x015b:
        r7 = "DOWNLOADER_DOWNLOADING";
        r6 = r6.equals(r7);	 Catch:{ JSONException -> 0x019f }
        if (r6 == 0) goto L_0x016f;
    L_0x0163:
        r13 = r9;
        goto L_0x0170;
    L_0x0165:
        r7 = "DOWNLOADER_DOWNLOADED";
        r6 = r6.equals(r7);	 Catch:{ JSONException -> 0x019f }
        if (r6 == 0) goto L_0x016f;
    L_0x016d:
        r13 = r8;
        goto L_0x0170;
    L_0x016f:
        r13 = r10;
    L_0x0170:
        switch(r13) {
            case 1: goto L_0x017f;
            case 2: goto L_0x017c;
            case 3: goto L_0x0179;
            case 4: goto L_0x0176;
            default: goto L_0x0173;
        };	 Catch:{ JSONException -> 0x019f }
    L_0x0173:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0176:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_ERROR;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x0179:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADED;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x017c:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADING;	 Catch:{ JSONException -> 0x019f }
        goto L_0x0181;
    L_0x017f:
        r6 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_INIT;	 Catch:{ JSONException -> 0x019f }
    L_0x0181:
        r7 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;	 Catch:{ JSONException -> 0x019f }
        if (r7 == r6) goto L_0x019a;
    L_0x0185:
        r7 = com.inmobi.ads.NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS;	 Catch:{ JSONException -> 0x019f }
        if (r7 == r6) goto L_0x0193;
    L_0x0189:
        r4 = a(r5, r6, r4);	 Catch:{ JSONException -> 0x019f }
        if (r4 == 0) goto L_0x019a;
    L_0x018f:
        r0.add(r4);	 Catch:{ JSONException -> 0x019f }
        goto L_0x019a;
    L_0x0193:
        r4 = a(r4);	 Catch:{ JSONException -> 0x019f }
        r0.addAll(r4);	 Catch:{ JSONException -> 0x019f }
    L_0x019a:
        r3 = r3 + 1;
        goto L_0x001e;
    L_0x019e:
        return r0;
    L_0x019f:
        r14 = move-exception;
        r1 = com.inmobi.commons.core.a.a.a();
        r2 = new com.inmobi.commons.core.e.a;
        r2.<init>(r14);
        r1.a(r2);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.b(org.json.JSONObject):java.util.List");
    }

    @Nullable
    private by a(@NonNull JSONObject jSONObject, @NonNull String str, ak akVar) {
        if (f(jSONObject).equalsIgnoreCase(ShareConstants.VIDEO_URL)) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray != null) {
                    if (jSONArray.length() != 0) {
                        if (akVar == null || !(akVar instanceof be)) {
                            return new bu(this.r.m).a(str);
                        }
                        return (by) akVar.e;
                    }
                }
                return null;
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
        return null;
    }

    private static String c(@NonNull JSONObject jSONObject) {
        try {
            if ((f(jSONObject).equalsIgnoreCase("ICON") || f(jSONObject).equalsIgnoreCase(ShareConstants.IMAGE_URL) || f(jSONObject).equalsIgnoreCase("GIF")) && jSONObject.getJSONArray("assetValue").getString(0).length() != 0) {
                return jSONObject.getJSONArray("assetValue").getString(0);
            }
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return "";
    }

    private static String d(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetId");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return Integer.toString(jSONObject.hashCode());
        }
    }

    private static String e(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetName");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static String f(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static String g(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("valueType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static String h(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("dataType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    @NonNull
    private JSONObject i(@NonNull JSONObject jSONObject) {
        try {
            JSONObject jSONObject2;
            if (jSONObject.isNull("assetStyle")) {
                jSONObject2 = null;
            } else {
                jSONObject2 = jSONObject.getJSONObject("assetStyle");
            }
            if (jSONObject2 == null) {
                if (jSONObject.isNull("assetStyleRef")) {
                    return new JSONObject();
                }
                String string = jSONObject.getString("assetStyleRef");
                if (this.n == null) {
                    jSONObject = new JSONObject();
                } else {
                    jSONObject = this.n.getJSONObject(string);
                }
                jSONObject2 = jSONObject;
            }
            return jSONObject2;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return new JSONObject();
        }
    }

    private Point j(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            jSONObject = i(jSONObject);
            if (jSONObject.isNull("geometry")) {
                return point;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("geometry");
            point.x = c.a(jSONArray.getInt(0));
            point.y = c.a(jSONArray.getInt(1));
            return point;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    private Point a(@NonNull JSONObject jSONObject, @NonNull Point point) {
        try {
            jSONObject = i(jSONObject);
            if (jSONObject.isNull("finalGeometry")) {
                return point;
            }
            point = new Point();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("finalGeometry");
                point.x = c.a(jSONArray.getInt(0));
                point.y = c.a(jSONArray.getInt(1));
            } catch (JSONException unused) {
            }
            return point;
        } catch (JSONException unused2) {
            point = null;
        }
    }

    private Point k(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            jSONObject = i(jSONObject);
            if (jSONObject.isNull("geometry")) {
                return point;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("geometry");
            point.x = c.a(jSONArray.getInt(2));
            point.y = c.a(jSONArray.getInt(3));
            return point;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    private Point b(@NonNull JSONObject jSONObject, @NonNull Point point) {
        try {
            jSONObject = i(jSONObject);
            if (jSONObject.isNull("finalGeometry")) {
                return point;
            }
            point = new Point();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("finalGeometry");
                point.x = c.a(jSONArray.getInt(2));
                point.y = c.a(jSONArray.getInt(3));
            } catch (JSONException unused) {
            }
            return point;
        } catch (JSONException unused2) {
            point = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0059 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0059 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0059 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0057  */
    private static int l(@android.support.annotation.NonNull org.json.JSONObject r6) {
        /*
        r0 = 2;
        r6 = n(r6);	 Catch:{ JSONException -> 0x005a }
        r1 = "type";
        r1 = r6.isNull(r1);	 Catch:{ JSONException -> 0x005a }
        if (r1 == 0) goto L_0x000e;
    L_0x000d:
        return r0;
    L_0x000e:
        r1 = "type";
        r6 = r6.getString(r1);	 Catch:{ JSONException -> 0x005a }
        r6 = r6.trim();	 Catch:{ JSONException -> 0x005a }
        r1 = java.util.Locale.US;	 Catch:{ JSONException -> 0x005a }
        r6 = r6.toLowerCase(r1);	 Catch:{ JSONException -> 0x005a }
        r1 = -1;
        r2 = r6.hashCode();	 Catch:{ JSONException -> 0x005a }
        r3 = -921832806; // 0xffffffffc90df29a float:-581417.6 double:NaN;
        r4 = 3;
        r5 = 1;
        if (r2 == r3) goto L_0x0048;
    L_0x002a:
        r3 = -284840886; // 0xffffffffef05ac4a float:-4.136979E28 double:NaN;
        if (r2 == r3) goto L_0x003e;
    L_0x002f:
        r3 = 1728122231; // 0x67010d77 float:6.0943366E23 double:8.53805826E-315;
        if (r2 == r3) goto L_0x0035;
    L_0x0034:
        goto L_0x0052;
    L_0x0035:
        r2 = "absolute";
        r6 = r6.equals(r2);	 Catch:{ JSONException -> 0x005a }
        if (r6 == 0) goto L_0x0052;
    L_0x003d:
        goto L_0x0053;
    L_0x003e:
        r2 = "unknown";
        r6 = r6.equals(r2);	 Catch:{ JSONException -> 0x005a }
        if (r6 == 0) goto L_0x0052;
    L_0x0046:
        r0 = r5;
        goto L_0x0053;
    L_0x0048:
        r2 = "percentage";
        r6 = r6.equals(r2);	 Catch:{ JSONException -> 0x005a }
        if (r6 == 0) goto L_0x0052;
    L_0x0050:
        r0 = r4;
        goto L_0x0053;
    L_0x0052:
        r0 = r1;
    L_0x0053:
        switch(r0) {
            case 2: goto L_0x0059;
            case 3: goto L_0x0057;
            default: goto L_0x0056;
        };
    L_0x0056:
        return r5;
    L_0x0057:
        r6 = 4;
        return r6;
    L_0x0059:
        return r4;
    L_0x005a:
        r6 = move-exception;
        r1 = com.inmobi.commons.core.a.a.a();
        r2 = new com.inmobi.commons.core.e.a;
        r2.<init>(r6);
        r1.a(r2);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.l(org.json.JSONObject):int");
    }

    private static int a(@NonNull JSONObject jSONObject, boolean z) {
        try {
            JSONObject n = n(jSONObject);
            if (n.isNull(z ? "delay" : "hideAfterDelay")) {
                return -1;
            }
            int i = n.getInt(z ? "delay" : "hideAfterDelay");
            if (3 == l(jSONObject)) {
                return i;
            }
            if (4 != l(jSONObject) || i <= 0 || i > 100) {
                return -1;
            }
            int[] iArr = new int[]{25, 50, 75, 100};
            double d = Double.MAX_VALUE;
            int i2 = -1;
            for (int i3 = 0; i3 < 4; i3++) {
                int i4 = i - iArr[i3];
                double d2 = (double) (i4 * i4);
                if (d2 < d) {
                    i2 = i3;
                    d = d2;
                }
            }
            return iArr[i2];
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return -1;
        }
    }

    private static String m(@NonNull JSONObject jSONObject) {
        try {
            jSONObject = n(jSONObject);
            if (jSONObject.isNull("reference")) {
                return "";
            }
            return jSONObject.getString("reference");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static JSONObject n(@NonNull JSONObject jSONObject) {
        if (jSONObject.isNull(ServerProtocol.DIALOG_PARAM_DISPLAY)) {
            return new JSONObject();
        }
        try {
            return jSONObject.getJSONObject(ServerProtocol.DIALOG_PARAM_DISPLAY);
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return new JSONObject();
        }
    }

    private static JSONArray o(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("assetValue");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return new JSONArray();
        }
    }

    private static boolean p(@NonNull JSONObject jSONObject) {
        return !jSONObject.isNull("assetOnclick");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054 A:{RETURN} */
    private static int d(@android.support.annotation.NonNull java.lang.String r6) {
        /*
        r0 = java.util.Locale.US;
        r6 = r6.toUpperCase(r0);
        r6 = r6.trim();
        r0 = r6.hashCode();
        r1 = -2084521848; // 0xffffffff83c0b888 float:-1.1327112E-36 double:NaN;
        r2 = 4;
        r3 = 1;
        r4 = 2;
        r5 = 3;
        if (r0 == r1) goto L_0x0045;
    L_0x0017:
        r1 = -1038134325; // 0xffffffffc21f53cb float:-39.83183 double:NaN;
        if (r0 == r1) goto L_0x003b;
    L_0x001c:
        r1 = 69805756; // 0x42926bc float:1.988364E-36 double:3.4488626E-316;
        if (r0 == r1) goto L_0x0031;
    L_0x0021:
        r1 = 1411860198; // 0x542746e6 float:2.87379607E12 double:6.975516206E-315;
        if (r0 == r1) goto L_0x0027;
    L_0x0026:
        goto L_0x004f;
    L_0x0027:
        r0 = "DEEPLINK";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x004f;
    L_0x002f:
        r6 = r5;
        goto L_0x0050;
    L_0x0031:
        r0 = "INAPP";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x004f;
    L_0x0039:
        r6 = r4;
        goto L_0x0050;
    L_0x003b:
        r0 = "EXTERNAL";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x004f;
    L_0x0043:
        r6 = r3;
        goto L_0x0050;
    L_0x0045:
        r0 = "DOWNLOAD";
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x004f;
    L_0x004d:
        r6 = r2;
        goto L_0x0050;
    L_0x004f:
        r6 = -1;
    L_0x0050:
        switch(r6) {
            case 2: goto L_0x0056;
            case 3: goto L_0x0055;
            case 4: goto L_0x0054;
            default: goto L_0x0053;
        };
    L_0x0053:
        return r4;
    L_0x0054:
        return r2;
    L_0x0055:
        return r5;
    L_0x0056:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.d(java.lang.String):int");
    }

    private static java.lang.String e(@android.support.annotation.NonNull java.lang.String r1) {
        /*
        r0 = java.util.Locale.US;
        r1 = r1.toLowerCase(r0);
        r1 = r1.trim();
        r0 = r1.hashCode();
        switch(r0) {
            case -1178781136: goto L_0x003a;
            case -1026963764: goto L_0x0030;
            case -891985998: goto L_0x0026;
            case 3029637: goto L_0x001c;
            case 3387192: goto L_0x0012;
            default: goto L_0x0011;
        };
    L_0x0011:
        goto L_0x0044;
    L_0x0012:
        r0 = "none";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0044;
    L_0x001a:
        r1 = 1;
        goto L_0x0045;
    L_0x001c:
        r0 = "bold";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0044;
    L_0x0024:
        r1 = 2;
        goto L_0x0045;
    L_0x0026:
        r0 = "strike";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0044;
    L_0x002e:
        r1 = 4;
        goto L_0x0045;
    L_0x0030:
        r0 = "underline";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0044;
    L_0x0038:
        r1 = 5;
        goto L_0x0045;
    L_0x003a:
        r0 = "italic";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0044;
    L_0x0042:
        r1 = 3;
        goto L_0x0045;
    L_0x0044:
        r1 = -1;
    L_0x0045:
        switch(r1) {
            case 2: goto L_0x0054;
            case 3: goto L_0x0051;
            case 4: goto L_0x004e;
            case 5: goto L_0x004b;
            default: goto L_0x0048;
        };
    L_0x0048:
        r1 = "none";
        return r1;
    L_0x004b:
        r1 = "underline";
        return r1;
    L_0x004e:
        r1 = "strike";
        return r1;
    L_0x0051:
        r1 = "italic";
        return r1;
    L_0x0054:
        r1 = "bold";
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.e(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    private static java.lang.String f(@android.support.annotation.NonNull java.lang.String r3) {
        /*
        r0 = java.util.Locale.US;
        r3 = r3.toLowerCase(r0);
        r3 = r3.trim();
        r0 = r3.hashCode();
        r1 = 3321844; // 0x32aff4 float:4.654895E-39 double:1.641209E-317;
        r2 = 2;
        if (r0 == r1) goto L_0x0024;
    L_0x0014:
        r1 = 3387192; // 0x33af38 float:4.746467E-39 double:1.673495E-317;
        if (r0 == r1) goto L_0x001a;
    L_0x0019:
        goto L_0x002e;
    L_0x001a:
        r0 = "none";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x002e;
    L_0x0022:
        r3 = 1;
        goto L_0x002f;
    L_0x0024:
        r0 = "line";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x002e;
    L_0x002c:
        r3 = r2;
        goto L_0x002f;
    L_0x002e:
        r3 = -1;
    L_0x002f:
        if (r3 == r2) goto L_0x0034;
    L_0x0031:
        r3 = "none";
        return r3;
    L_0x0034:
        r3 = "line";
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.f(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    private static java.lang.String g(@android.support.annotation.NonNull java.lang.String r3) {
        /*
        r0 = java.util.Locale.US;
        r3 = r3.toLowerCase(r0);
        r3 = r3.trim();
        r0 = r3.hashCode();
        r1 = -1349116587; // 0xffffffffaf961d55 float:-2.7305683E-10 double:NaN;
        r2 = 2;
        if (r0 == r1) goto L_0x0024;
    L_0x0014:
        r1 = 1787472634; // 0x6a8aaafa float:8.381959E25 double:8.831288213E-315;
        if (r0 == r1) goto L_0x001a;
    L_0x0019:
        goto L_0x002e;
    L_0x001a:
        r0 = "straight";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x002e;
    L_0x0022:
        r3 = 1;
        goto L_0x002f;
    L_0x0024:
        r0 = "curved";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x002e;
    L_0x002c:
        r3 = r2;
        goto L_0x002f;
    L_0x002e:
        r3 = -1;
    L_0x002f:
        if (r3 == r2) goto L_0x0034;
    L_0x0031:
        r3 = "straight";
        return r3;
    L_0x0034:
        r3 = "curved";
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.g(java.lang.String):java.lang.String");
    }

    private com.inmobi.ads.ba.a q(JSONObject jSONObject) {
        return new com.inmobi.ads.ba.a(jSONObject.optLong("absolute"), jSONObject.optLong("percentage"), jSONObject.optString("reference"), this);
    }

    private com.inmobi.ads.ba.a r(JSONObject jSONObject) {
        return new com.inmobi.ads.ba.a(jSONObject.optLong("absolute"), jSONObject.optLong("percentage"), jSONObject.optString("reference"), this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0091  */
    private com.inmobi.ads.al a(@android.support.annotation.NonNull android.graphics.Point r24, @android.support.annotation.NonNull android.graphics.Point r25, @android.support.annotation.NonNull android.graphics.Point r26, @android.support.annotation.NonNull android.graphics.Point r27, @android.support.annotation.NonNull org.json.JSONObject r28) throws org.json.JSONException {
        /*
        r23 = this;
        r0 = r24;
        r1 = r25;
        r2 = r26;
        r3 = r27;
        r4 = r28;
        r5 = "border";
        r5 = r4.isNull(r5);
        if (r5 == 0) goto L_0x001f;
    L_0x0012:
        r5 = "none";
        r6 = "straight";
        r7 = "#ff000000";
    L_0x0018:
        r18 = r5;
        r19 = r6;
        r20 = r7;
        goto L_0x006f;
    L_0x001f:
        r5 = "border";
        r5 = r4.getJSONObject(r5);
        r6 = "style";
        r6 = r5.isNull(r6);
        if (r6 == 0) goto L_0x0034;
    L_0x002d:
        r5 = "none";
        r6 = "straight";
        r7 = "#ff000000";
        goto L_0x0018;
    L_0x0034:
        r6 = "style";
        r6 = r5.getString(r6);
        r6 = f(r6);
        r7 = "corner";
        r7 = r5.isNull(r7);
        if (r7 == 0) goto L_0x0049;
    L_0x0046:
        r7 = "straight";
        goto L_0x0053;
    L_0x0049:
        r7 = "corner";
        r7 = r5.getString(r7);
        r7 = g(r7);
    L_0x0053:
        r8 = "color";
        r8 = r5.isNull(r8);
        if (r8 == 0) goto L_0x0064;
    L_0x005b:
        r5 = "#ff000000";
    L_0x005d:
        r20 = r5;
        r18 = r6;
        r19 = r7;
        goto L_0x006f;
    L_0x0064:
        r8 = "color";
        r5 = r5.getString(r8);
        r5 = r5.trim();
        goto L_0x005d;
    L_0x006f:
        r5 = "backgroundColor";
        r5 = r4.isNull(r5);
        if (r5 == 0) goto L_0x007c;
    L_0x0077:
        r5 = "#00000000";
    L_0x0079:
        r21 = r5;
        goto L_0x0087;
    L_0x007c:
        r5 = "backgroundColor";
        r5 = r4.getString(r5);
        r5 = r5.trim();
        goto L_0x0079;
    L_0x0087:
        r5 = "fill";
        r6 = "contentMode";
        r6 = r4.isNull(r6);
        if (r6 != 0) goto L_0x00ea;
    L_0x0091:
        r5 = "contentMode";
        r5 = r4.getString(r5);
        r5 = r5.trim();
        r6 = -1;
        r7 = r5.hashCode();
        r8 = -1626174665; // 0xffffffff9f128b37 float:-3.103186E-20 double:NaN;
        if (r7 == r8) goto L_0x00d3;
    L_0x00a5:
        r8 = -1362001767; // 0xffffffffaed18099 float:-9.527063E-11 double:NaN;
        if (r7 == r8) goto L_0x00c9;
    L_0x00aa:
        r8 = 3143043; // 0x2ff583 float:4.404341E-39 double:1.5528696E-317;
        if (r7 == r8) goto L_0x00bf;
    L_0x00af:
        r8 = 727618043; // 0x2b5e91fb float:7.907283E-13 double:3.594910783E-315;
        if (r7 == r8) goto L_0x00b5;
    L_0x00b4:
        goto L_0x00dc;
    L_0x00b5:
        r7 = "aspectFill";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x00dc;
    L_0x00bd:
        r6 = 3;
        goto L_0x00dc;
    L_0x00bf:
        r7 = "fill";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x00dc;
    L_0x00c7:
        r6 = 2;
        goto L_0x00dc;
    L_0x00c9:
        r7 = "aspectFit";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x00dc;
    L_0x00d1:
        r6 = 4;
        goto L_0x00dc;
    L_0x00d3:
        r7 = "unspecified";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x00dc;
    L_0x00db:
        r6 = 1;
    L_0x00dc:
        switch(r6) {
            case 2: goto L_0x00e8;
            case 3: goto L_0x00e5;
            case 4: goto L_0x00e2;
            default: goto L_0x00df;
        };
    L_0x00df:
        r5 = "unspecified";
        goto L_0x00ea;
    L_0x00e2:
        r5 = "aspectFit";
        goto L_0x00ea;
    L_0x00e5:
        r5 = "aspectFill";
        goto L_0x00ea;
    L_0x00e8:
        r5 = "fill";
    L_0x00ea:
        r17 = r5;
        r5 = r23;
        r22 = r5.s(r4);
        r4 = new com.inmobi.ads.al;
        r9 = r0.x;
        r10 = r0.y;
        r11 = r1.x;
        r12 = r1.y;
        r13 = r2.x;
        r14 = r2.y;
        r15 = r3.x;
        r0 = r3.y;
        r8 = r4;
        r16 = r0;
        r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22);
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.a(android.graphics.Point, android.graphics.Point, android.graphics.Point, android.graphics.Point, org.json.JSONObject):com.inmobi.ads.al");
    }

    private ba s(JSONObject jSONObject) throws JSONException {
        com.inmobi.ads.ba.a aVar = null;
        com.inmobi.ads.ba.a r = !jSONObject.isNull("startOffset") ? r(jSONObject.getJSONObject("startOffset")) : null;
        if (!jSONObject.isNull("timerDuration")) {
            aVar = r(jSONObject.getJSONObject("timerDuration"));
        }
        return new ba(r, aVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0111  */
    private com.inmobi.ads.az.a b(@android.support.annotation.NonNull android.graphics.Point r29, @android.support.annotation.NonNull android.graphics.Point r30, @android.support.annotation.NonNull android.graphics.Point r31, @android.support.annotation.NonNull android.graphics.Point r32, @android.support.annotation.NonNull org.json.JSONObject r33) throws org.json.JSONException {
        /*
        r28 = this;
        r1 = r29;
        r2 = r30;
        r3 = r31;
        r4 = r32;
        r5 = r33;
        r6 = "border";
        r6 = r5.isNull(r6);
        if (r6 == 0) goto L_0x001f;
    L_0x0012:
        r6 = "none";
        r7 = "straight";
        r8 = "#ff000000";
    L_0x0018:
        r18 = r6;
        r19 = r7;
        r20 = r8;
        goto L_0x006f;
    L_0x001f:
        r6 = "border";
        r6 = r5.getJSONObject(r6);
        r7 = "style";
        r7 = r6.isNull(r7);
        if (r7 == 0) goto L_0x0034;
    L_0x002d:
        r6 = "none";
        r7 = "straight";
        r8 = "#ff000000";
        goto L_0x0018;
    L_0x0034:
        r7 = "style";
        r7 = r6.getString(r7);
        r7 = f(r7);
        r8 = "corner";
        r8 = r6.isNull(r8);
        if (r8 == 0) goto L_0x0049;
    L_0x0046:
        r8 = "straight";
        goto L_0x0053;
    L_0x0049:
        r8 = "corner";
        r8 = r6.getString(r8);
        r8 = g(r8);
    L_0x0053:
        r9 = "color";
        r9 = r6.isNull(r9);
        if (r9 == 0) goto L_0x0064;
    L_0x005b:
        r6 = "#ff000000";
    L_0x005d:
        r20 = r6;
        r18 = r7;
        r19 = r8;
        goto L_0x006f;
    L_0x0064:
        r9 = "color";
        r6 = r6.getString(r9);
        r6 = r6.trim();
        goto L_0x005d;
    L_0x006f:
        r6 = "backgroundColor";
        r6 = r5.isNull(r6);
        if (r6 == 0) goto L_0x007c;
    L_0x0077:
        r6 = "#00000000";
    L_0x0079:
        r21 = r6;
        goto L_0x0087;
    L_0x007c:
        r6 = "backgroundColor";
        r6 = r5.getString(r6);
        r6 = r6.trim();
        goto L_0x0079;
    L_0x0087:
        r6 = "text";
        r6 = r5.getJSONObject(r6);
        r7 = "size";
        r7 = r6.getString(r7);	 Catch:{ NumberFormatException -> 0x017b }
        r7 = java.lang.Double.parseDouble(r7);	 Catch:{ NumberFormatException -> 0x017b }
        r7 = (int) r7;
        r8 = "length";
        r8 = r6.isNull(r8);
        if (r8 == 0) goto L_0x00a6;
    L_0x00a0:
        r8 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
    L_0x00a3:
        r24 = r8;
        goto L_0x00b1;
    L_0x00a6:
        r8 = "length";
        r8 = r6.getString(r8);
        r8 = java.lang.Integer.parseInt(r8);
        goto L_0x00a3;
    L_0x00b1:
        r8 = "color";
        r8 = r6.isNull(r8);
        if (r8 == 0) goto L_0x00be;
    L_0x00b9:
        r8 = "#ff000000";
    L_0x00bb:
        r25 = r8;
        goto L_0x00c9;
    L_0x00be:
        r8 = "color";
        r8 = r6.getString(r8);
        r8 = r8.trim();
        goto L_0x00bb;
    L_0x00c9:
        r8 = "style";
        r8 = r6.isNull(r8);
        r9 = 0;
        r10 = 1;
        if (r8 == 0) goto L_0x00dc;
    L_0x00d3:
        r8 = new java.lang.String[r10];
        r11 = "none";
        r8[r9] = r11;
    L_0x00d9:
        r26 = r8;
        goto L_0x0109;
    L_0x00dc:
        r8 = "style";
        r8 = r6.getJSONArray(r8);
        r8 = r8.length();
        if (r8 != 0) goto L_0x00ef;
    L_0x00e8:
        r8 = new java.lang.String[r10];
        r11 = "none";
        r8[r9] = r11;
        goto L_0x00d9;
    L_0x00ef:
        r11 = new java.lang.String[r8];
        r12 = r9;
    L_0x00f2:
        if (r12 >= r8) goto L_0x0107;
    L_0x00f4:
        r13 = "style";
        r13 = r6.getJSONArray(r13);
        r13 = r13.getString(r12);
        r13 = e(r13);
        r11[r12] = r13;
        r12 = r12 + 1;
        goto L_0x00f2;
    L_0x0107:
        r26 = r11;
    L_0x0109:
        r8 = "align";
        r8 = r6.isNull(r8);
        if (r8 != 0) goto L_0x0156;
    L_0x0111:
        r8 = "align";
        r6 = r6.getString(r8);
        r6 = r6.trim();
        r8 = -1;
        r11 = r6.hashCode();
        r12 = -1364013605; // 0xffffffffaeb2cddb float:-8.13107E-11 double:NaN;
        r13 = 2;
        if (r11 == r12) goto L_0x0145;
    L_0x0126:
        r12 = 3317767; // 0x32a007 float:4.649182E-39 double:1.6391947E-317;
        if (r11 == r12) goto L_0x013b;
    L_0x012b:
        r12 = 108511772; // 0x677c21c float:4.6598146E-35 double:5.36119387E-316;
        if (r11 == r12) goto L_0x0131;
    L_0x0130:
        goto L_0x014f;
    L_0x0131:
        r11 = "right";
        r6 = r6.equals(r11);
        if (r6 == 0) goto L_0x014f;
    L_0x0139:
        r8 = r13;
        goto L_0x014f;
    L_0x013b:
        r11 = "left";
        r6 = r6.equals(r11);
        if (r6 == 0) goto L_0x014f;
    L_0x0143:
        r8 = r10;
        goto L_0x014f;
    L_0x0145:
        r11 = "centre";
        r6 = r6.equals(r11);
        if (r6 == 0) goto L_0x014f;
    L_0x014d:
        r6 = 3;
        r8 = r6;
    L_0x014f:
        switch(r8) {
            case 2: goto L_0x0155;
            case 3: goto L_0x0153;
            default: goto L_0x0152;
        };
    L_0x0152:
        goto L_0x0156;
    L_0x0153:
        r9 = r13;
        goto L_0x0156;
    L_0x0155:
        r9 = r10;
    L_0x0156:
        r6 = r28;
        r23 = r9;
        r27 = r6.s(r5);
        r5 = new com.inmobi.ads.az$a;
        r10 = r1.x;
        r11 = r1.y;
        r12 = r2.x;
        r13 = r2.y;
        r14 = r3.x;
        r15 = r3.y;
        r1 = r4.x;
        r2 = r4.y;
        r9 = r5;
        r16 = r1;
        r17 = r2;
        r22 = r7;
        r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27);
        return r5;
    L_0x017b:
        r0 = move-exception;
        r6 = r28;
        r1 = r0;
        r2 = new org.json.JSONException;
        r3 = r1.getMessage();
        r2.<init>(r3);
        r2.initCause(r1);
        r3 = com.inmobi.commons.core.a.a.a();
        r4 = new com.inmobi.commons.core.e.a;
        r4.<init>(r1);
        r3.a(r4);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.b(android.graphics.Point, android.graphics.Point, android.graphics.Point, android.graphics.Point, org.json.JSONObject):com.inmobi.ads.az$a");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ba  */
    private com.inmobi.ads.az.a c(@android.support.annotation.NonNull android.graphics.Point r27, @android.support.annotation.NonNull android.graphics.Point r28, @android.support.annotation.NonNull android.graphics.Point r29, @android.support.annotation.NonNull android.graphics.Point r30, @android.support.annotation.NonNull org.json.JSONObject r31) throws org.json.JSONException {
        /*
        r26 = this;
        r1 = r27;
        r2 = r28;
        r3 = r29;
        r4 = r30;
        r5 = r31;
        r6 = "border";
        r6 = r5.isNull(r6);
        if (r6 == 0) goto L_0x001f;
    L_0x0012:
        r6 = "none";
        r7 = "straight";
        r8 = "#ff000000";
    L_0x0018:
        r18 = r6;
        r19 = r7;
        r20 = r8;
        goto L_0x006f;
    L_0x001f:
        r6 = "border";
        r6 = r5.getJSONObject(r6);
        r7 = "style";
        r7 = r6.isNull(r7);
        if (r7 == 0) goto L_0x0034;
    L_0x002d:
        r6 = "none";
        r7 = "straight";
        r8 = "#ff000000";
        goto L_0x0018;
    L_0x0034:
        r7 = "style";
        r7 = r6.getString(r7);
        r7 = f(r7);
        r8 = "corner";
        r8 = r6.isNull(r8);
        if (r8 == 0) goto L_0x0049;
    L_0x0046:
        r8 = "straight";
        goto L_0x0053;
    L_0x0049:
        r8 = "corner";
        r8 = r6.getString(r8);
        r8 = g(r8);
    L_0x0053:
        r9 = "color";
        r9 = r6.isNull(r9);
        if (r9 == 0) goto L_0x0064;
    L_0x005b:
        r6 = "#ff000000";
    L_0x005d:
        r20 = r6;
        r18 = r7;
        r19 = r8;
        goto L_0x006f;
    L_0x0064:
        r9 = "color";
        r6 = r6.getString(r9);
        r6 = r6.trim();
        goto L_0x005d;
    L_0x006f:
        r6 = "backgroundColor";
        r6 = r5.isNull(r6);
        if (r6 == 0) goto L_0x007c;
    L_0x0077:
        r6 = "#00000000";
    L_0x0079:
        r21 = r6;
        goto L_0x0087;
    L_0x007c:
        r6 = "backgroundColor";
        r6 = r5.getString(r6);
        r6 = r6.trim();
        goto L_0x0079;
    L_0x0087:
        r6 = "text";
        r6 = r5.getJSONObject(r6);
        r7 = "size";
        r7 = r6.getString(r7);	 Catch:{ NumberFormatException -> 0x0114 }
        r7 = java.lang.Double.parseDouble(r7);	 Catch:{ NumberFormatException -> 0x0114 }
        r7 = (int) r7;
        r8 = "color";
        r8 = r6.isNull(r8);
        if (r8 == 0) goto L_0x00a5;
    L_0x00a0:
        r8 = "#ff000000";
    L_0x00a2:
        r23 = r8;
        goto L_0x00b0;
    L_0x00a5:
        r8 = "color";
        r8 = r6.getString(r8);
        r8 = r8.trim();
        goto L_0x00a2;
    L_0x00b0:
        r8 = "style";
        r8 = r6.isNull(r8);
        r9 = 0;
        r10 = 1;
        if (r8 == 0) goto L_0x00c5;
    L_0x00ba:
        r6 = new java.lang.String[r10];
        r8 = "none";
        r6[r9] = r8;
    L_0x00c0:
        r24 = r6;
        r6 = r26;
        goto L_0x00f3;
    L_0x00c5:
        r8 = "style";
        r8 = r6.getJSONArray(r8);
        r8 = r8.length();
        if (r8 != 0) goto L_0x00d8;
    L_0x00d1:
        r6 = new java.lang.String[r10];
        r8 = "none";
        r6[r9] = r8;
        goto L_0x00c0;
    L_0x00d8:
        r10 = new java.lang.String[r8];
    L_0x00da:
        if (r9 >= r8) goto L_0x00ef;
    L_0x00dc:
        r11 = "style";
        r11 = r6.getJSONArray(r11);
        r11 = r11.getString(r9);
        r11 = e(r11);
        r10[r9] = r11;
        r9 = r9 + 1;
        goto L_0x00da;
    L_0x00ef:
        r6 = r26;
        r24 = r10;
    L_0x00f3:
        r25 = r6.s(r5);
        r5 = new com.inmobi.ads.an$a;
        r10 = r1.x;
        r11 = r1.y;
        r12 = r2.x;
        r13 = r2.y;
        r14 = r3.x;
        r15 = r3.y;
        r1 = r4.x;
        r2 = r4.y;
        r9 = r5;
        r16 = r1;
        r17 = r2;
        r22 = r7;
        r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
        return r5;
    L_0x0114:
        r0 = move-exception;
        r6 = r26;
        r1 = r0;
        r2 = new org.json.JSONException;
        r3 = r1.getMessage();
        r2.<init>(r3);
        r2.initCause(r1);
        r3 = com.inmobi.commons.core.a.a.a();
        r4 = new com.inmobi.commons.core.e.a;
        r4.<init>(r1);
        r3.a(r4);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.c(android.graphics.Point, android.graphics.Point, android.graphics.Point, android.graphics.Point, org.json.JSONObject):com.inmobi.ads.az$a");
    }

    private static boolean a(@NonNull am amVar) {
        return "card_scrollable".equalsIgnoreCase(amVar.d);
    }

    private static void a(be beVar) {
        beVar.x = 8;
        HashMap hashMap = new HashMap();
        hashMap.put("[ERRORCODE]", "601");
        beVar.a(TrackerEventType.TRACKER_EVENT_TYPE_ERROR, (Map) hashMap);
    }

    @Nullable
    private static String a(bt btVar, bf bfVar) {
        List a;
        List a2;
        String str;
        if ("REF_HTML".equals(bfVar.z)) {
            a = btVar.a(2);
            if (a.size() > 0) {
                return ((a) a.get(0)).b;
            }
            a2 = btVar.a(3);
            if (a2.size() > 0) {
                str = ((a) a2.get(0)).b;
                if (URLUtil.isValidUrl(str)) {
                    bfVar.z = "REF_IFRAME";
                    return str;
                }
                a("MalformedURL", "Rich", "REF_HTML", null, null);
            }
        } else if ("REF_IFRAME".equals(bfVar.z)) {
            a = btVar.a(3);
            if (a.size() > 0) {
                str = ((a) a.get(0)).b;
                if (URLUtil.isValidUrl(str)) {
                    bfVar.z = "REF_IFRAME";
                    return str;
                }
                a("MalformedURL", "Rich", "REF_IFRAME", null, null);
            } else {
                a2 = btVar.a(2);
                if (a2.size() > 0) {
                    bfVar.z = "REF_HTML";
                    return ((a) a2.get(0)).b;
                }
            }
        }
        return null;
    }

    private static ak a(@NonNull ao aoVar, @NonNull ak akVar) {
        while (true) {
            String str = (String) akVar.e;
            if (str == null || str.length() == 0) {
                return null;
            }
            String[] split = str.split("\\|");
            ak b = aoVar.b(split[0]);
            if (b == null) {
                aoVar = aoVar.f;
            } else if (b.equals(akVar)) {
                return null;
            } else {
                if (1 == split.length) {
                    b.m = 1;
                    return b;
                }
                b.m = a(split[1]);
                StringBuilder stringBuilder = new StringBuilder("Referenced asset (");
                stringBuilder.append(b.d);
                stringBuilder.append(")");
                return b;
            }
        }
        return null;
    }

    private static boolean a(al alVar, al alVar2) {
        if (alVar.a.x + alVar.c.x >= alVar2.a.x + alVar2.c.x && alVar.a.y + alVar.c.y >= alVar2.a.y + alVar2.c.y) {
            return true;
        }
        return false;
    }
}
