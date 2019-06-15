package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bt {
    static final ArrayList<String> f = new ArrayList(Arrays.asList(new String[]{"image/jpeg", "image/png"}));
    private static final String h = "bt";
    int a;
    int b;
    List<a> c = new ArrayList();
    List<NativeTracker> d = new ArrayList();
    String e;
    boolean g;
    @Nullable
    private String i;

    static final class a {
        int a;
        String b;

        a(int i, String str) {
            this.a = i;
            this.b = str;
        }

        /* JADX WARNING: Removed duplicated region for block: B:29:0x0063 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0066 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0064 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0063 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0066 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0064 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0063 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0066 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0064 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0063 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0066 A:{Catch:{ JSONException -> 0x0075 }} */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0064 A:{Catch:{ JSONException -> 0x0075 }} */
        static com.inmobi.ads.bt.a a(org.json.JSONObject r8) {
            /*
            r0 = "type";
            r0 = r8.getString(r0);	 Catch:{ JSONException -> 0x0075 }
            r1 = 0;
            r2 = 2;
            r3 = 1;
            r4 = 3;
            if (r0 == 0) goto L_0x0069;
        L_0x000c:
            r5 = r0.trim();	 Catch:{ JSONException -> 0x0075 }
            r5 = r5.length();	 Catch:{ JSONException -> 0x0075 }
            if (r5 != 0) goto L_0x0017;
        L_0x0016:
            goto L_0x0069;
        L_0x0017:
            r5 = java.util.Locale.US;	 Catch:{ JSONException -> 0x0075 }
            r0 = r0.toLowerCase(r5);	 Catch:{ JSONException -> 0x0075 }
            r5 = -1;
            r6 = r0.hashCode();	 Catch:{ JSONException -> 0x0075 }
            r7 = -1191214428; // 0xffffffffb8ff82a4 float:-1.2183681E-4 double:NaN;
            if (r6 == r7) goto L_0x0055;
        L_0x0027:
            r7 = -892481938; // 0xffffffffcacdce6e float:-6743863.0 double:NaN;
            if (r6 == r7) goto L_0x004b;
        L_0x002c:
            r7 = -284840886; // 0xffffffffef05ac4a float:-4.136979E28 double:NaN;
            if (r6 == r7) goto L_0x0041;
        L_0x0031:
            r7 = 3213227; // 0x3107ab float:4.50269E-39 double:1.587545E-317;
            if (r6 == r7) goto L_0x0037;
        L_0x0036:
            goto L_0x005f;
        L_0x0037:
            r6 = "html";
            r0 = r0.equals(r6);	 Catch:{ JSONException -> 0x0075 }
            if (r0 == 0) goto L_0x005f;
        L_0x003f:
            r0 = r4;
            goto L_0x0060;
        L_0x0041:
            r6 = "unknown";
            r0 = r0.equals(r6);	 Catch:{ JSONException -> 0x0075 }
            if (r0 == 0) goto L_0x005f;
        L_0x0049:
            r0 = r3;
            goto L_0x0060;
        L_0x004b:
            r6 = "static";
            r0 = r0.equals(r6);	 Catch:{ JSONException -> 0x0075 }
            if (r0 == 0) goto L_0x005f;
        L_0x0053:
            r0 = r2;
            goto L_0x0060;
        L_0x0055:
            r6 = "iframe";
            r0 = r0.equals(r6);	 Catch:{ JSONException -> 0x0075 }
            if (r0 == 0) goto L_0x005f;
        L_0x005d:
            r0 = 4;
            goto L_0x0060;
        L_0x005f:
            r0 = r5;
        L_0x0060:
            switch(r0) {
                case 2: goto L_0x0068;
                case 3: goto L_0x0066;
                case 4: goto L_0x0064;
                default: goto L_0x0063;
            };	 Catch:{ JSONException -> 0x0075 }
        L_0x0063:
            goto L_0x0069;
        L_0x0064:
            r1 = r4;
            goto L_0x0069;
        L_0x0066:
            r1 = r2;
            goto L_0x0069;
        L_0x0068:
            r1 = r3;
        L_0x0069:
            r0 = "content";
            r8 = r8.getString(r0);	 Catch:{ JSONException -> 0x0075 }
            r0 = new com.inmobi.ads.bt$a;	 Catch:{ JSONException -> 0x0075 }
            r0.<init>(r1, r8);	 Catch:{ JSONException -> 0x0075 }
            return r0;
        L_0x0075:
            r8 = move-exception;
            com.inmobi.ads.bt.h;
            r0 = new java.lang.StringBuilder;
            r1 = "Error building resource from JSONObject; ";
            r0.<init>(r1);
            r1 = r8.getMessage();
            r0.append(r1);
            r0 = com.inmobi.commons.core.a.a.a();
            r1 = new com.inmobi.commons.core.e.a;
            r1.<init>(r8);
            r0.a(r1);
            r8 = 0;
            return r8;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.bt$a.a(org.json.JSONObject):com.inmobi.ads.bt$a");
        }

        public final String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                Object obj;
                String str = "type";
                switch (this.a) {
                    case 1:
                        obj = "static";
                        break;
                    case 2:
                        obj = "html";
                        break;
                    case 3:
                        obj = "iframe";
                        break;
                    default:
                        obj = "unknown";
                        break;
                }
                jSONObject.put(str, obj);
                jSONObject.put("content", this.b);
                return jSONObject.toString();
            } catch (JSONException e) {
                bt.h;
                new StringBuilder("Error serializing resource: ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return "";
            }
        }
    }

    public bt(int i, int i2, String str, @Nullable String str2) {
        this.i = str2;
        this.a = i;
        this.b = i2;
        this.e = str;
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final List<a> a(int i) {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.c) {
            if (aVar.a == i) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull a aVar) {
        this.c.add(aVar);
    }

    /* Access modifiers changed, original: final */
    @NonNull
    public final List<NativeTracker> a(TrackerEventType trackerEventType) {
        ArrayList arrayList = new ArrayList();
        for (NativeTracker nativeTracker : this.d) {
            if (nativeTracker.b.equals(trackerEventType)) {
                arrayList.add(nativeTracker);
            }
        }
        return arrayList;
    }

    public final void a(@NonNull NativeTracker nativeTracker) {
        this.d.add(nativeTracker);
    }

    @Nullable
    static bt a(JSONObject jSONObject) {
        try {
            bt btVar = new bt(jSONObject.getInt("width"), jSONObject.getInt("height"), jSONObject.has("clickThroughUrl") ? jSONObject.getString("clickThroughUrl") : null, jSONObject.optString("id", null));
            int i = 0;
            try {
                JSONArray jSONArray = new JSONArray(jSONObject.getString("trackers"));
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    NativeTracker a = NativeTracker.a(new JSONObject(jSONArray.getString(i2)));
                    if (a != null) {
                        btVar.a(a);
                    }
                }
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            try {
                JSONArray jSONArray2 = new JSONArray(jSONObject.getString("resources"));
                while (i < jSONArray2.length()) {
                    a a2 = a.a(new JSONObject(jSONArray2.getString(i)));
                    if (a2 != null) {
                        btVar.a(a2);
                    }
                    i++;
                }
            } catch (JSONException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            return btVar;
        } catch (JSONException e22) {
            new StringBuilder("Error building companion from JSON: ").append(e22.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e22));
            return null;
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.i != null) {
                jSONObject.put("id", this.i);
            }
            jSONObject.put("width", this.a);
            jSONObject.put("height", this.b);
            jSONObject.put("clickThroughUrl", this.e);
            JSONArray jSONArray = new JSONArray();
            for (a aVar : this.c) {
                jSONArray.put(aVar.toString());
            }
            jSONObject.put("resources", jSONArray);
            jSONArray = new JSONArray();
            for (NativeTracker nativeTracker : this.d) {
                jSONArray.put(nativeTracker.toString());
            }
            jSONObject.put("trackers", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            StringBuilder stringBuilder = new StringBuilder("Error serializing an ");
            stringBuilder.append(h);
            stringBuilder.append(" instance: ");
            stringBuilder.append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }
}
