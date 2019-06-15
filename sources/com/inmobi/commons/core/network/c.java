package com.inmobi.commons.core.network;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.api.client.http.HttpMethods;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.configs.b;
import com.inmobi.commons.core.configs.g;
import com.inmobi.commons.core.utilities.b.f;
import com.inmobi.commons.core.utilities.uid.d;
import com.til.colombia.android.internal.e;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONObject;

public class c {
    private static final String a = "c";
    private d b;
    private boolean c;
    private byte[] d;
    private byte[] e;
    private boolean f;
    protected Map<String, String> m;
    protected Map<String, String> n;
    public Map<String, String> o;
    String p;
    public String q;
    public int r;
    public int s;
    public boolean t;
    public boolean u;
    public long v;
    boolean w;
    public int x;
    public boolean y;
    public g z;

    public c(String str, String str2, boolean z, d dVar) {
        this(str, str2, z, dVar, false, 0);
    }

    public c(String str, String str2) {
        this(str, str2, false, null, false, 0);
        this.f = false;
    }

    public c(String str, String str2, d dVar, int i) {
        this(str, str2, true, dVar, false, i);
    }

    public c(String str, String str2, boolean z, d dVar, boolean z2, int i) {
        this.m = new HashMap();
        this.r = 60000;
        this.s = 60000;
        this.t = true;
        this.u = true;
        this.v = -1;
        this.x = 0;
        this.f = true;
        this.y = false;
        this.p = str;
        this.q = str2;
        this.c = z;
        this.b = dVar;
        this.m.put(e.c, a.f());
        this.w = z2;
        this.x = i;
        if (HttpMethods.GET.equals(str)) {
            this.n = new HashMap();
        } else if (HttpMethods.POST.equals(str)) {
            this.o = new HashMap();
        }
        this.z = new g();
        b.a().a(this.z, null);
    }

    public final void a(Map<String, String> map) {
        if (map != null) {
            this.m.putAll(map);
        }
    }

    public final void b(Map<String, String> map) {
        if (map != null) {
            this.n.putAll(map);
        }
    }

    public final void c(Map<String, String> map) {
        this.o.putAll(map);
    }

    public final Map<String, String> d() {
        com.inmobi.commons.core.utilities.d.a(this.m);
        return this.m;
    }

    public final String e() {
        String str = this.q;
        if (this.n == null) {
            return str;
        }
        String c = c();
        if (c == null || c.trim().length() == 0) {
            return str;
        }
        StringBuilder stringBuilder;
        if (!str.contains("?")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("?");
            str = stringBuilder.toString();
        }
        if (!(str.endsWith("&") || str.endsWith("?"))) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&");
            str = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(c);
        return stringBuilder.toString();
    }

    @CallSuper
    public void a() {
        com.inmobi.commons.core.utilities.b.e.c();
        boolean z = true;
        if (this.x != 1) {
            z = false;
        }
        this.x = com.inmobi.commons.core.utilities.b.e.a(z);
        if (this.u) {
            if (HttpMethods.GET.equals(this.p)) {
                d(this.n);
            } else if (HttpMethods.POST.equals(this.p)) {
                d(this.o);
            }
        }
        if (this.f) {
            JSONObject a = com.inmobi.commons.core.utilities.b.e.a();
            if (a != null) {
                if (HttpMethods.GET.equals(this.p)) {
                    this.n.put("consentObject", a.toString());
                } else if (HttpMethods.POST.equals(this.p)) {
                    this.o.put("consentObject", a.toString());
                }
            }
        }
        if (HttpMethods.GET.equals(this.p)) {
            this.n.put("u-appsecure", Integer.toString(com.inmobi.commons.core.utilities.b.a.a().c));
            return;
        }
        if (HttpMethods.POST.equals(this.p)) {
            this.o.put("u-appsecure", Integer.toString(com.inmobi.commons.core.utilities.b.a.a().c));
        }
    }

    private String c() {
        com.inmobi.commons.core.utilities.d.a(this.n);
        return com.inmobi.commons.core.utilities.d.a(this.n, "&");
    }

    public final String f() {
        com.inmobi.commons.core.utilities.d.a(this.o);
        String a = com.inmobi.commons.core.utilities.d.a(this.o, "&");
        new StringBuilder("Post body url: ").append(this.q);
        if (!b()) {
            return a;
        }
        this.d = com.inmobi.commons.core.utilities.a.b.a(16);
        this.e = com.inmobi.commons.core.utilities.a.b.a();
        byte[] bArr = this.d;
        byte[] bArr2 = this.e;
        g gVar = this.z;
        byte[] a2 = com.inmobi.commons.core.utilities.a.b.a(8);
        Map hashMap = new HashMap();
        hashMap.put("sm", com.inmobi.commons.core.utilities.a.b.a(a, bArr2, bArr, a2, gVar.b, gVar.a));
        hashMap.put("sn", gVar.c);
        return com.inmobi.commons.core.utilities.d.a(hashMap, "&");
    }

    public boolean b() {
        return this.c;
    }

    private void d(Map<String, String> map) {
        map.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
        map.putAll(com.inmobi.commons.core.utilities.b.b.a(this.y));
        map.putAll(f.a());
        if (this.b != null) {
            d dVar;
            if (b()) {
                dVar = this.b;
                HashMap hashMap = new HashMap();
                hashMap.put("u-id-map", new JSONObject(dVar.a(null, false)).toString());
                map.putAll(hashMap);
                return;
            }
            dVar = this.b;
            String num = Integer.toString(new Random().nextInt());
            String a = com.inmobi.commons.core.utilities.a.c.a(new JSONObject(dVar.a(num, true)).toString());
            HashMap hashMap2 = new HashMap();
            hashMap2.put("u-id-map", a);
            hashMap2.put("u-id-key", num);
            com.inmobi.commons.core.utilities.uid.c.a();
            hashMap2.put("u-key-ver", com.inmobi.commons.core.utilities.uid.c.d());
            map.putAll(hashMap2);
        }
    }

    @Nullable
    public final byte[] a(byte[] bArr) {
        try {
            return com.inmobi.commons.core.utilities.a.b.a(Base64.decode(bArr, 0), this.e, this.d);
        } catch (IllegalArgumentException e) {
            new StringBuilder("Msg : ").append(e.getMessage());
            return null;
        }
    }

    public final long g() {
        try {
            long length;
            if (HttpMethods.GET.equals(this.p)) {
                length = 0 + ((long) c().length());
            } else if (!HttpMethods.POST.equals(this.p)) {
                return 0;
            } else {
                length = 0 + ((long) f().length());
            }
            return length;
        } catch (Exception unused) {
            return 0;
        }
    }
}
