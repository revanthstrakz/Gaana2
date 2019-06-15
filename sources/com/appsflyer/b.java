package com.appsflyer;

import android.os.Build.VERSION;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static JSONObject a(Map<String, ?> map) {
        if (VERSION.SDK_INT >= 19) {
            return new JSONObject(map);
        }
        return b(map);
    }

    public static JSONObject b(Map<String, ?> map) {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), a(entry.getValue()));
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Missing block: B:53:0x0098, code skipped:
            return r4;
     */
    private static java.lang.Object a(java.lang.Object r4) {
        /*
        if (r4 != 0) goto L_0x0005;
    L_0x0002:
        r4 = org.json.JSONObject.NULL;
        return r4;
    L_0x0005:
        r0 = r4 instanceof org.json.JSONArray;
        if (r0 != 0) goto L_0x0098;
    L_0x0009:
        r0 = r4 instanceof org.json.JSONObject;
        if (r0 == 0) goto L_0x000f;
    L_0x000d:
        goto L_0x0098;
    L_0x000f:
        r0 = org.json.JSONObject.NULL;
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x0018;
    L_0x0017:
        return r4;
    L_0x0018:
        r0 = r4 instanceof java.util.Collection;	 Catch:{ Exception -> 0x0095 }
        if (r0 == 0) goto L_0x003a;
    L_0x001c:
        r0 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0095 }
        r0.<init>();	 Catch:{ Exception -> 0x0095 }
        r4 = (java.util.Collection) r4;	 Catch:{ Exception -> 0x0095 }
        r4 = r4.iterator();	 Catch:{ Exception -> 0x0095 }
    L_0x0027:
        r1 = r4.hasNext();	 Catch:{ Exception -> 0x0095 }
        if (r1 == 0) goto L_0x0039;
    L_0x002d:
        r1 = r4.next();	 Catch:{ Exception -> 0x0095 }
        r1 = a(r1);	 Catch:{ Exception -> 0x0095 }
        r0.put(r1);	 Catch:{ Exception -> 0x0095 }
        goto L_0x0027;
    L_0x0039:
        return r0;
    L_0x003a:
        r0 = r4.getClass();	 Catch:{ Exception -> 0x0095 }
        r0 = r0.isArray();	 Catch:{ Exception -> 0x0095 }
        if (r0 == 0) goto L_0x005f;
    L_0x0044:
        r0 = java.lang.reflect.Array.getLength(r4);	 Catch:{ Exception -> 0x0095 }
        r1 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0095 }
        r1.<init>();	 Catch:{ Exception -> 0x0095 }
        r2 = 0;
    L_0x004e:
        if (r2 >= r0) goto L_0x005e;
    L_0x0050:
        r3 = java.lang.reflect.Array.get(r4, r2);	 Catch:{ Exception -> 0x0095 }
        r3 = a(r3);	 Catch:{ Exception -> 0x0095 }
        r1.put(r3);	 Catch:{ Exception -> 0x0095 }
        r2 = r2 + 1;
        goto L_0x004e;
    L_0x005e:
        return r1;
    L_0x005f:
        r0 = r4 instanceof java.util.Map;	 Catch:{ Exception -> 0x0095 }
        if (r0 == 0) goto L_0x006a;
    L_0x0063:
        r4 = (java.util.Map) r4;	 Catch:{ Exception -> 0x0095 }
        r4 = b(r4);	 Catch:{ Exception -> 0x0095 }
        return r4;
    L_0x006a:
        r0 = r4 instanceof java.lang.Boolean;
        if (r0 != 0) goto L_0x0094;
    L_0x006e:
        r0 = r4 instanceof java.lang.Byte;
        if (r0 != 0) goto L_0x0094;
    L_0x0072:
        r0 = r4 instanceof java.lang.Character;
        if (r0 != 0) goto L_0x0094;
    L_0x0076:
        r0 = r4 instanceof java.lang.Double;
        if (r0 != 0) goto L_0x0094;
    L_0x007a:
        r0 = r4 instanceof java.lang.Float;
        if (r0 != 0) goto L_0x0094;
    L_0x007e:
        r0 = r4 instanceof java.lang.Integer;
        if (r0 != 0) goto L_0x0094;
    L_0x0082:
        r0 = r4 instanceof java.lang.Long;
        if (r0 != 0) goto L_0x0094;
    L_0x0086:
        r0 = r4 instanceof java.lang.Short;
        if (r0 != 0) goto L_0x0094;
    L_0x008a:
        r0 = r4 instanceof java.lang.String;
        if (r0 == 0) goto L_0x008f;
    L_0x008e:
        goto L_0x0094;
    L_0x008f:
        r4 = r4.toString();	 Catch:{ Exception -> 0x0095 }
        return r4;
    L_0x0094:
        return r4;
    L_0x0095:
        r4 = org.json.JSONObject.NULL;
        return r4;
    L_0x0098:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.b.a(java.lang.Object):java.lang.Object");
    }
}
