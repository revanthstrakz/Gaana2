package com.facebook.ads.internal.o;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.g.c;
import com.facebook.ads.internal.g.d;
import com.facebook.ads.internal.h.e;
import com.facebook.ads.internal.o.b.a;
import com.facebook.ads.internal.s.a.v;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g implements a {
    private static final String a = "g";
    private Context b;
    private d c;

    @VisibleForTesting
    public g(Context context, d dVar) {
        this.b = context;
        this.c = dVar;
    }

    private static JSONArray a(JSONArray jSONArray, JSONArray jSONArray2) {
        int i = 0;
        if (jSONArray != null) {
            i = 0 + jSONArray.length();
        }
        if (jSONArray2 != null) {
            i += jSONArray2.length();
        }
        return a(jSONArray, jSONArray2, i);
    }

    private static JSONArray a(JSONArray jSONArray, JSONArray jSONArray2, int i) {
        JSONArray jSONArray3 = jSONArray;
        JSONArray jSONArray4 = jSONArray2;
        if (jSONArray3 == null) {
            return jSONArray4;
        }
        if (jSONArray4 == null) {
            return jSONArray3;
        }
        int length = jSONArray.length();
        int length2 = jSONArray2.length();
        JSONArray jSONArray5 = new JSONArray();
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        double d = Double.MAX_VALUE;
        double d2 = d;
        Object obj = null;
        Object obj2 = obj;
        while (true) {
            if ((i2 < length || i4 < length2) && i3 > 0) {
                if (i2 < length && obj == null) {
                    try {
                        obj = jSONArray3.getJSONObject(i2);
                        d = obj.getDouble("time");
                    } catch (JSONException unused) {
                        d = Double.MAX_VALUE;
                        obj = null;
                    }
                    i2++;
                }
                if (i4 < length2 && obj2 == null) {
                    try {
                        obj2 = jSONArray4.getJSONObject(i4);
                        d2 = obj2.getDouble("time");
                    } catch (JSONException unused2) {
                        d2 = Double.MAX_VALUE;
                        obj2 = null;
                    }
                    i4++;
                }
                if (obj != null || obj2 != null) {
                    if (obj == null || d2 < d) {
                        jSONArray5.put(obj2);
                        d2 = Double.MAX_VALUE;
                        obj2 = null;
                    } else {
                        jSONArray5.put(obj);
                        d = Double.MAX_VALUE;
                        obj = null;
                    }
                    i3--;
                }
            }
        }
        if (i3 > 0) {
            if (obj != null) {
                jSONArray5.put(obj);
                return jSONArray5;
            } else if (obj2 != null) {
                jSONArray5.put(obj2);
            }
        }
        return jSONArray5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0069  */
    private org.json.JSONObject a(int r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = r7.c;	 Catch:{ JSONException -> 0x006d, all -> 0x005f }
        r1 = r1.d();	 Catch:{ JSONException -> 0x006d, all -> 0x005f }
        r2 = r7.c;	 Catch:{ JSONException -> 0x005d, all -> 0x005a }
        r2 = r2.a(r8);	 Catch:{ JSONException -> 0x005d, all -> 0x005a }
        r3 = r2.getCount();	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        if (r3 <= 0) goto L_0x001c;
    L_0x0013:
        r3 = r7.a(r2);	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        r4 = r7.c(r2);	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        goto L_0x001e;
    L_0x001c:
        r3 = r0;
        r4 = r3;
    L_0x001e:
        r5 = r7.b;	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        r5 = com.facebook.ads.internal.n.a.j(r5);	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        if (r5 == 0) goto L_0x0038;
    L_0x0026:
        r5 = r7.b;	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        r5 = com.facebook.ads.internal.h.e.a(r5, r8);	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        if (r5 == 0) goto L_0x0038;
    L_0x002e:
        r6 = r5.length();	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        if (r6 <= 0) goto L_0x0038;
    L_0x0034:
        r4 = a(r5, r4, r8);	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
    L_0x0038:
        if (r4 == 0) goto L_0x004c;
    L_0x003a:
        r8 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        r8.<init>();	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        if (r3 == 0) goto L_0x0046;
    L_0x0041:
        r5 = "tokens";
        r8.put(r5, r3);	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
    L_0x0046:
        r3 = "events";
        r8.put(r3, r4);	 Catch:{ JSONException -> 0x006f, all -> 0x0058 }
        goto L_0x004d;
    L_0x004c:
        r8 = r0;
    L_0x004d:
        if (r1 == 0) goto L_0x0052;
    L_0x004f:
        r1.close();
    L_0x0052:
        if (r2 == 0) goto L_0x0057;
    L_0x0054:
        r2.close();
    L_0x0057:
        return r8;
    L_0x0058:
        r8 = move-exception;
        goto L_0x0062;
    L_0x005a:
        r8 = move-exception;
        r2 = r0;
        goto L_0x0062;
    L_0x005d:
        r2 = r0;
        goto L_0x006f;
    L_0x005f:
        r8 = move-exception;
        r1 = r0;
        r2 = r1;
    L_0x0062:
        if (r1 == 0) goto L_0x0067;
    L_0x0064:
        r1.close();
    L_0x0067:
        if (r2 == 0) goto L_0x006c;
    L_0x0069:
        r2.close();
    L_0x006c:
        throw r8;
    L_0x006d:
        r1 = r0;
        r2 = r1;
    L_0x006f:
        if (r1 == 0) goto L_0x0074;
    L_0x0071:
        r1.close();
    L_0x0074:
        if (r2 == 0) goto L_0x0079;
    L_0x0076:
        r2.close();
    L_0x0079:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.o.g.a(int):org.json.JSONObject");
    }

    private JSONObject a(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        while (cursor.moveToNext()) {
            jSONObject.put(cursor.getString(0), cursor.getString(1));
        }
        return jSONObject;
    }

    private void a(String str) {
        if (e.c(str)) {
            e.a(str);
        } else {
            this.c.a(str);
        }
    }

    private JSONArray b(Cursor cursor) {
        JSONArray jSONArray = new JSONArray();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", cursor.getString(c.a.a));
            jSONObject.put("token_id", cursor.getString(c.b.a));
            jSONObject.put("type", cursor.getString(c.d.a));
            jSONObject.put("time", v.a(cursor.getDouble(c.e.a)));
            jSONObject.put("session_time", v.a(cursor.getDouble(c.f.a)));
            jSONObject.put("session_id", cursor.getString(c.g.a));
            String string = cursor.getString(c.h.a);
            jSONObject.put("data", string != null ? new JSONObject(string) : new JSONObject());
            jSONObject.put("attempt", cursor.getString(c.i.a));
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private JSONArray c(Cursor cursor) {
        JSONArray jSONArray = new JSONArray();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", cursor.getString(2));
            jSONObject.put("token_id", cursor.getString(0));
            jSONObject.put("type", cursor.getString(4));
            jSONObject.put("time", v.a(cursor.getDouble(5)));
            jSONObject.put("session_time", v.a(cursor.getDouble(6)));
            jSONObject.put("session_id", cursor.getString(7));
            String string = cursor.getString(8);
            jSONObject.put("data", string != null ? new JSONObject(string) : new JSONObject());
            jSONObject.put("attempt", cursor.getString(9));
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0071  */
    private org.json.JSONObject e() {
        /*
        r8 = this;
        r0 = 0;
        r1 = r8.c;	 Catch:{ JSONException -> 0x0075, all -> 0x0066 }
        r1 = r1.f();	 Catch:{ JSONException -> 0x0075, all -> 0x0066 }
        r2 = r8.c;	 Catch:{ JSONException -> 0x0064, all -> 0x005f }
        r2 = r2.e();	 Catch:{ JSONException -> 0x0064, all -> 0x005f }
        r3 = r1.getCount();	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        if (r3 <= 0) goto L_0x0022;
    L_0x0013:
        r3 = r2.getCount();	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        if (r3 <= 0) goto L_0x0022;
    L_0x0019:
        r3 = r8.a(r1);	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        r4 = r8.b(r2);	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        goto L_0x0024;
    L_0x0022:
        r3 = r0;
        r4 = r3;
    L_0x0024:
        r5 = r8.b;	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        r5 = com.facebook.ads.internal.n.a.j(r5);	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        if (r5 == 0) goto L_0x003e;
    L_0x002c:
        r5 = r8.b;	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        r5 = com.facebook.ads.internal.h.e.a(r5);	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        if (r5 == 0) goto L_0x003e;
    L_0x0034:
        r6 = r5.length();	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        if (r6 <= 0) goto L_0x003e;
    L_0x003a:
        r4 = a(r5, r4);	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
    L_0x003e:
        if (r4 == 0) goto L_0x0052;
    L_0x0040:
        r5 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        r5.<init>();	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        if (r3 == 0) goto L_0x004c;
    L_0x0047:
        r6 = "tokens";
        r5.put(r6, r3);	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
    L_0x004c:
        r3 = "events";
        r5.put(r3, r4);	 Catch:{ JSONException -> 0x0077, all -> 0x005d }
        r0 = r5;
    L_0x0052:
        if (r1 == 0) goto L_0x0057;
    L_0x0054:
        r1.close();
    L_0x0057:
        if (r2 == 0) goto L_0x005c;
    L_0x0059:
        r2.close();
    L_0x005c:
        return r0;
    L_0x005d:
        r0 = move-exception;
        goto L_0x006a;
    L_0x005f:
        r2 = move-exception;
        r7 = r2;
        r2 = r0;
        r0 = r7;
        goto L_0x006a;
    L_0x0064:
        r2 = r0;
        goto L_0x0077;
    L_0x0066:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r2;
    L_0x006a:
        if (r1 == 0) goto L_0x006f;
    L_0x006c:
        r1.close();
    L_0x006f:
        if (r2 == 0) goto L_0x0074;
    L_0x0071:
        r2.close();
    L_0x0074:
        throw r0;
    L_0x0075:
        r1 = r0;
        r2 = r1;
    L_0x0077:
        if (r1 == 0) goto L_0x007c;
    L_0x0079:
        r1.close();
    L_0x007c:
        if (r2 == 0) goto L_0x0081;
    L_0x007e:
        r2.close();
    L_0x0081:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.o.g.e():org.json.JSONObject");
    }

    public JSONObject a() {
        int p = com.facebook.ads.internal.n.a.p(this.b);
        return p > 0 ? a(p) : e();
    }

    public boolean a(JSONArray jSONArray) {
        boolean j = com.facebook.ads.internal.n.a.j(this.b);
        boolean z = true;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("id");
                int i2 = jSONObject.getInt("code");
                if (i2 == 1) {
                    if (!this.c.b(string)) {
                        if (!j) {
                        }
                    }
                } else if (i2 < 1000 || i2 >= 2000) {
                    if (i2 >= 2000) {
                        if (i2 < 3000) {
                            if (!this.c.b(string)) {
                                if (!j) {
                                }
                            }
                        }
                    }
                } else {
                    a(string);
                    z = false;
                }
                e.b(string);
            } catch (JSONException unused) {
            }
        }
        return z;
    }

    public void b() {
        this.c.g();
        this.c.b();
        e.d(this.b);
    }

    public void b(JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                a(jSONArray.getJSONObject(i).getString("id"));
            } catch (JSONException unused) {
            }
        }
    }

    public void c() {
        this.c.h();
        e.b(this.b);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0035  */
    public boolean d() {
        /*
        r6 = this;
        r0 = r6.b;
        r0 = com.facebook.ads.internal.n.a.p(r0);
        r1 = 1;
        r2 = 0;
        if (r0 >= r1) goto L_0x000b;
    L_0x000a:
        return r2;
    L_0x000b:
        r3 = 0;
        r4 = r6.c;	 Catch:{ all -> 0x0031 }
        r4 = r4.d();	 Catch:{ all -> 0x0031 }
        r3 = r4.moveToFirst();	 Catch:{ all -> 0x002f }
        if (r3 == 0) goto L_0x001d;
    L_0x0018:
        r3 = r4.getInt(r2);	 Catch:{ all -> 0x002f }
        goto L_0x001e;
    L_0x001d:
        r3 = r2;
    L_0x001e:
        r5 = r6.b;	 Catch:{ all -> 0x002f }
        r5 = com.facebook.ads.internal.h.e.c(r5);	 Catch:{ all -> 0x002f }
        r3 = r3 + r5;
        if (r3 <= r0) goto L_0x0028;
    L_0x0027:
        goto L_0x0029;
    L_0x0028:
        r1 = r2;
    L_0x0029:
        if (r4 == 0) goto L_0x002e;
    L_0x002b:
        r4.close();
    L_0x002e:
        return r1;
    L_0x002f:
        r0 = move-exception;
        goto L_0x0033;
    L_0x0031:
        r0 = move-exception;
        r4 = r3;
    L_0x0033:
        if (r4 == 0) goto L_0x0038;
    L_0x0035:
        r4.close();
    L_0x0038:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.o.g.d():boolean");
    }
}
