package com.inmobi.ads.cache;

import android.content.ContentValues;
import android.support.annotation.Nullable;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.inmobi.commons.core.d.b;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class d {
    public static final String[] a = new String[]{"id", "pending_attempts", "url", "disk_uri", HlsSegmentFormat.TS, "created_ts", "ttl", "soft_ttl"};
    private static final String b = "d";
    private static d c;
    private static final Object d = new Object();

    public static d a() {
        d dVar = c;
        if (dVar == null) {
            synchronized (d) {
                dVar = c;
                if (dVar == null) {
                    dVar = new d();
                    c = dVar;
                }
            }
        }
        return dVar;
    }

    private d() {
        b a = b.a();
        a.a("asset", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, disk_uri TEXT, ts TEXT NOT NULL, created_ts TEXT NOT NULL, ttl TEXT NOT NULL, soft_ttl TEXT NOT NULL)");
        a.b();
    }

    public final synchronized void a(a aVar) {
        if (b(aVar) <= 0) {
            ContentValues d = d(aVar);
            b a = b.a();
            a.a("asset", d);
            a.b();
        }
    }

    static List<a> a(int i) {
        ArrayList arrayList = new ArrayList();
        b a = b.a();
        if (a.a("asset") == 0) {
            return arrayList;
        }
        String[] strArr = a;
        String str = HlsSegmentFormat.TS;
        StringBuilder stringBuilder = new StringBuilder("ts < ");
        stringBuilder.append(System.currentTimeMillis() - ((long) i));
        List<ContentValues> a2 = a.a("asset", strArr, null, null, str, stringBuilder.toString(), "ts ASC ", null);
        a.b();
        for (ContentValues a3 : a2) {
            arrayList.add(a(a3));
        }
        return arrayList;
    }

    public static List<a> b() {
        ArrayList arrayList = new ArrayList();
        b a = b.a();
        if (a.a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> a2 = a.a("asset", a, "disk_uri IS NOT NULL", null, null, null, "created_ts DESC ", null);
        a.b();
        for (ContentValues a3 : a2) {
            arrayList.add(a(a3));
        }
        return arrayList;
    }

    public static String c() {
        List<a> b = b();
        if (b.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (a aVar : b) {
            try {
                jSONArray.put(URLEncoder.encode(aVar.d, "UTF-8"));
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return jSONArray.toString();
    }

    @Nullable
    static a a(String str) {
        b a = b.a();
        List a2 = a.a("asset", a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", "1");
        a.b();
        return a2.isEmpty() ? null : a((ContentValues) a2.get(0));
    }

    public static List<String> d() {
        ArrayList arrayList = new ArrayList();
        b a = b.a();
        if (a.a("asset") == 0) {
            return arrayList;
        }
        b bVar = a;
        List<ContentValues> a2 = bVar.a("asset", new String[]{"url"}, null, null, null, null, "created_ts DESC ", null);
        a.b();
        for (ContentValues asString : a2) {
            arrayList.add(asString.getAsString("url"));
        }
        return arrayList;
    }

    public static a b(String str) {
        b a = b.a();
        List a2 = a.a("asset", a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", "1");
        a.b();
        return a2.isEmpty() ? null : a((ContentValues) a2.get(0));
    }

    public static int b(a aVar) {
        b a = b.a();
        int b = a.b("asset", d(aVar), "url = ?", new String[]{String.valueOf(aVar.d)});
        a.b();
        return b;
    }

    public static void c(a aVar) {
        b a = b.a();
        a.a("asset", "id = ?", new String[]{String.valueOf(aVar.b)});
        a.b();
    }

    public static a a(ContentValues contentValues) {
        return new a(contentValues.getAsInteger("id").intValue(), contentValues.getAsString("url"), contentValues.getAsString("disk_uri"), contentValues.getAsInteger("pending_attempts").intValue(), Long.valueOf(contentValues.getAsString(HlsSegmentFormat.TS)).longValue(), Long.valueOf(contentValues.getAsString("created_ts")).longValue(), Long.valueOf(contentValues.getAsString("ttl")).longValue(), Long.valueOf(contentValues.getAsString("soft_ttl")).longValue());
    }

    private static ContentValues d(a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(aVar.b));
        contentValues.put("url", aVar.d);
        contentValues.put("disk_uri", aVar.e);
        contentValues.put("pending_attempts", Integer.valueOf(aVar.c));
        contentValues.put(HlsSegmentFormat.TS, Long.toString(aVar.f));
        contentValues.put("created_ts", Long.toString(aVar.g));
        contentValues.put("ttl", Long.toString(aVar.h));
        contentValues.put("soft_ttl", Long.toString(aVar.i));
        return contentValues;
    }
}
