package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.d.b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class d {
    private static final String a = "d";
    private static d b;
    private static final Object c = new Object();
    private static final String[] d = new String[]{"id", "ad_content", "video_url", "video_track_duration", "click_url", "video_trackers", "companion_ads", "web_vast", "preload_webView", "asset_urls", "ad_type", "ad_size", "placement_id", "tp_key", "insertion_ts", "expiry_duration", "imp_id", "m10_context", "client_request_id", "bid", "bidInfo", "marked"};

    public static d a() {
        d dVar = b;
        if (dVar == null) {
            synchronized (c) {
                dVar = b;
                if (dVar == null) {
                    dVar = new d();
                    b = dVar;
                }
            }
        }
        return dVar;
    }

    private static String[] a(long j, MonetizationContext monetizationContext, String str) {
        return new String[]{String.valueOf(j), monetizationContext.a, str, "0"};
    }

    private static String[] e(long j, String str, MonetizationContext monetizationContext, String str2) {
        return new String[]{String.valueOf(j), str, monetizationContext.a, str2, "0"};
    }

    private d() {
        b a = b.a();
        a.a("ad", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL, ad_content TEXT NOT NULL, ad_type TEXT NOT NULL, ad_size TEXT, asset_urls TEXT, video_url TEXT, video_track_duration TEXT, click_url TEXT, video_trackers TEXT, companion_ads TEXT, web_vast TEXT, preload_webView INTEGER DEFAULT 0, insertion_ts INTEGER NOT NULL, imp_id TEXT NOT NULL UNIQUE, m10_context TEXT NOT NULL, tp_key TEXT, expiry_duration INTEGER NOT NULL, client_request_id TEXT NOT NULL,bid INTEGER NOT NULL,bidInfo TEXT,marked INTEGER DEFAULT 0)");
        d();
        a.b();
    }

    static List<a> a(String str, long j) {
        ArrayList arrayList = new ArrayList();
        b a = b.a();
        String[] strArr = new String[1];
        int i = 0;
        strArr[0] = str;
        List<ContentValues> a2 = a.a("ad", d, "ad_type=?", strArr, null, null, null, null);
        if (a2.size() == 0) {
            a.b();
            return arrayList;
        }
        for (ContentValues a3 : a2) {
            long toMillis;
            a a4 = a.a(a3);
            if (a4.c() == -1) {
                toMillis = (a4.e + TimeUnit.SECONDS.toMillis(j)) - System.currentTimeMillis();
            } else {
                toMillis = a4.c() - System.currentTimeMillis();
            }
            if (toMillis < 0) {
                i += a(a4.g);
                arrayList.add(a4);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Deleted ");
        stringBuilder.append(i);
        stringBuilder.append(" expired ads from cache of type: ");
        stringBuilder.append(str);
        a.b();
        return arrayList;
    }

    static List<a> b() {
        ArrayList arrayList = new ArrayList();
        for (ContentValues a : b.a().a("ad", d, null, null, null, null, null, null)) {
            arrayList.add(a.a(a));
        }
        return arrayList;
    }

    private static void d() {
        b a = b.a();
        b bVar = a;
        for (ContentValues contentValues : bVar.a("ad", d, "marked=?", new String[]{"1"}, null, null, null, null)) {
            contentValues.put("marked", "0");
            a.a("ad", contentValues, "imp_id=?", new String[]{contentValues.getAsString("imp_id")});
        }
        a.b();
    }

    static int a(long j, String str, MonetizationContext monetizationContext, String str2) {
        int b;
        b a = b.a();
        if (str == null || str.trim().length() == 0) {
            b = a.b("ad", "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2));
        } else {
            b = a.b("ad", "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2));
        }
        a.b();
        return b;
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized a b(long j, String str, MonetizationContext monetizationContext, String str2) {
        a f;
        f = f(j, str, monetizationContext, str2);
        if (f != null) {
            b.a().a("ad", "id=?", new String[]{String.valueOf(f.a)});
        }
        return f;
    }

    private synchronized a f(long j, String str, MonetizationContext monetizationContext, String str2) {
        List a;
        b a2 = b.a();
        if (str == null || str.trim().length() == 0) {
            a = a2.a("ad", d, "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2), null, null, "insertion_ts", "1");
        } else {
            a = a2.a("ad", d, "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2), null, null, "insertion_ts", "1");
        }
        if (a.size() == 0) {
            return null;
        }
        return a.a((ContentValues) a.get(0));
    }

    @NonNull
    public final synchronized List<a> c(long j, String str, MonetizationContext monetizationContext, String str2) {
        return a(j, str, monetizationContext, str2, false);
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized List<a> a(String str, String str2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        b a = b.a();
        List a2;
        if (str2 == null || str2.trim().length() == 0) {
            a2 = a.a("ad", d, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        } else {
            a2 = a.a("ad", d, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        }
        for (ContentValues a3 : a2) {
            arrayList.add(a.a(a3));
        }
        return arrayList;
    }

    public final synchronized List<a> d(long j, String str, MonetizationContext monetizationContext, String str2) {
        return a(j, str, monetizationContext, str2, true);
    }

    private static List<a> a(long j, String str, MonetizationContext monetizationContext, String str2, boolean z) {
        ArrayList arrayList = new ArrayList();
        b a = b.a();
        List a2;
        if (str == null || str.trim().length() == 0) {
            a2 = a.a("ad", d, "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2), null, null, z ? "bid" : "insertion_ts", null);
        } else {
            a2 = a.a("ad", d, "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2), null, null, z ? "bid" : "insertion_ts", null);
        }
        for (ContentValues a3 : a2) {
            arrayList.add(a.a(a3));
        }
        return arrayList;
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized List<a> b(String str, String str2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        b a = b.a();
        b bVar;
        List a2;
        if (str2 == null || str2.trim().length() == 0) {
            bVar = a;
            a2 = bVar.a("ad", d, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        } else {
            bVar = a;
            a2 = bVar.a("ad", d, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        }
        for (ContentValues asInteger : a2) {
            a.a("ad", "id=?", new String[]{String.valueOf(asInteger.getAsInteger("id").intValue())});
            arrayList.add(a.a(asInteger));
        }
        return arrayList;
    }

    public static int a(String str) {
        b a = b.a();
        int a2 = a.a("ad", "imp_id = ?", new String[]{String.valueOf(str)});
        a.b();
        return a2;
    }

    public static void b(String str) {
        b a = b.a();
        a c = c(str);
        if (c != null) {
            ContentValues a2 = c.a();
            a2.put("marked", "1");
            a.b("ad", a2, "imp_id=?", new String[]{str});
        }
    }

    @Nullable
    public static a c(String str) {
        List a = b.a().a("ad", d, "imp_id=?", new String[]{str}, null, null, null, "1");
        if (a.size() == 0) {
            return null;
        }
        return a.a((ContentValues) a.get(0));
    }

    /* JADX WARNING: Missing block: B:9:0x0016, code skipped:
            return;
     */
    public final synchronized void a(java.util.List<com.inmobi.ads.a> r19, long r20, int r22, java.lang.String r23, com.inmobi.ads.InMobiAdRequest.MonetizationContext r24, java.lang.String r25, @android.support.annotation.Nullable java.lang.String r26) {
        /*
        r18 = this;
        r1 = r20;
        r4 = r24;
        r5 = r25;
        monitor-enter(r18);
        r6 = android.text.TextUtils.isEmpty(r26);	 Catch:{ all -> 0x00da }
        if (r6 == 0) goto L_0x000f;
    L_0x000d:
        if (r22 == 0) goto L_0x0015;
    L_0x000f:
        r7 = r19.size();	 Catch:{ all -> 0x00da }
        if (r7 != 0) goto L_0x0017;
    L_0x0015:
        monitor-exit(r18);
        return;
    L_0x0017:
        r7 = com.inmobi.commons.core.d.b.a();	 Catch:{ all -> 0x00da }
        r8 = r19.iterator();	 Catch:{ all -> 0x00da }
    L_0x001f:
        r9 = r8.hasNext();	 Catch:{ all -> 0x00da }
        if (r9 == 0) goto L_0x0040;
    L_0x0025:
        r9 = r8.next();	 Catch:{ all -> 0x00da }
        r9 = (com.inmobi.ads.a) r9;	 Catch:{ all -> 0x00da }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00da }
        r9.e = r10;	 Catch:{ all -> 0x00da }
        r9 = r9.a();	 Catch:{ all -> 0x00da }
        r10 = "tp_key";
        r9.put(r10, r5);	 Catch:{ all -> 0x00da }
        r10 = "ad";
        r7.a(r10, r9);	 Catch:{ all -> 0x00da }
        goto L_0x001f;
    L_0x0040:
        if (r6 != 0) goto L_0x0045;
    L_0x0042:
        b(r26);	 Catch:{ all -> 0x00da }
    L_0x0045:
        r6 = 0;
        r8 = a(r1, r6, r4, r5);	 Catch:{ all -> 0x00da }
        r8 = r8 - r22;
        if (r8 <= 0) goto L_0x00d5;
    L_0x004e:
        r3 = new java.util.HashMap;	 Catch:{ all -> 0x00da }
        r3.<init>();	 Catch:{ all -> 0x00da }
        r9 = "type";
        r10 = r23;
        r3.put(r9, r10);	 Catch:{ all -> 0x00da }
        r9 = "count";
        r10 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x00da }
        r3.put(r9, r10);	 Catch:{ all -> 0x00da }
        com.inmobi.commons.core.e.b.a();	 Catch:{ all -> 0x00da }
        r9 = "ads";
        r10 = "DbSpaceOverflow";
        com.inmobi.commons.core.e.b.a(r9, r10, r3);	 Catch:{ all -> 0x00da }
        r9 = "ad";
        r3 = 1;
        r10 = new java.lang.String[r3];	 Catch:{ all -> 0x00da }
        r3 = "id";
        r17 = 0;
        r10[r17] = r3;	 Catch:{ all -> 0x00da }
        r11 = "placement_id=? AND m10_context=? AND tp_key=? AND marked=?";
        r12 = a(r1, r4, r5);	 Catch:{ all -> 0x00da }
        r13 = 0;
        r14 = 0;
        r15 = "insertion_ts ASC";
        r16 = java.lang.String.valueOf(r8);	 Catch:{ all -> 0x00da }
        r8 = r7;
        r1 = r8.a(r9, r10, r11, r12, r13, r14, r15, r16);	 Catch:{ all -> 0x00da }
        r2 = r1.size();	 Catch:{ all -> 0x00da }
        r2 = new java.lang.String[r2];	 Catch:{ all -> 0x00da }
        r3 = r17;
    L_0x0093:
        r4 = r1.size();	 Catch:{ all -> 0x00da }
        if (r3 >= r4) goto L_0x00ae;
    L_0x0099:
        r4 = r1.get(r3);	 Catch:{ all -> 0x00da }
        r4 = (android.content.ContentValues) r4;	 Catch:{ all -> 0x00da }
        r5 = "id";
        r4 = r4.getAsInteger(r5);	 Catch:{ all -> 0x00da }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ all -> 0x00da }
        r2[r3] = r4;	 Catch:{ all -> 0x00da }
        r3 = r3 + 1;
        goto L_0x0093;
    L_0x00ae:
        r1 = java.util.Arrays.toString(r2);	 Catch:{ all -> 0x00da }
        r2 = "[";
        r3 = "(";
        r1 = r1.replace(r2, r3);	 Catch:{ all -> 0x00da }
        r2 = "]";
        r3 = ")";
        r1 = r1.replace(r2, r3);	 Catch:{ all -> 0x00da }
        r2 = "ad";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00da }
        r4 = "id IN ";
        r3.<init>(r4);	 Catch:{ all -> 0x00da }
        r3.append(r1);	 Catch:{ all -> 0x00da }
        r1 = r3.toString();	 Catch:{ all -> 0x00da }
        r7.a(r2, r1, r6);	 Catch:{ all -> 0x00da }
    L_0x00d5:
        r7.b();	 Catch:{ all -> 0x00da }
        monitor-exit(r18);
        return;
    L_0x00da:
        r0 = move-exception;
        r1 = r0;
        monitor-exit(r18);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.d.a(java.util.List, long, int, java.lang.String, com.inmobi.ads.InMobiAdRequest$MonetizationContext, java.lang.String, java.lang.String):void");
    }

    public static void c() {
        b a = b.a();
        a.a("ad", null, null);
        a.b();
    }

    public static int a(a aVar) {
        return a(aVar.g);
    }
}
