package com.helpshift.network.a;

import android.net.Uri;
import android.text.TextUtils;
import com.google.api.client.http.HttpMethods;
import com.helpshift.common.domain.network.i;
import com.helpshift.exceptions.InstallException;
import com.helpshift.network.b.d;
import com.helpshift.network.b.e;
import com.helpshift.network.b.e.b;
import com.helpshift.network.b.g;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.t;
import com.helpshift.util.u;
import com.helpshift.util.x;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class a {
    private static AtomicInteger c = new AtomicInteger();
    public final int a;
    public final String b;
    private final com.helpshift.network.b.e.a d;
    private Integer e;
    private boolean f = false;
    private Map<String, String> g;
    private b h;
    private g i;

    /* Access modifiers changed, original: protected */
    public NetworkError a(NetworkError networkError) {
        return networkError;
    }

    public <T> a(int i, String str, Map<String, String> map, b<T> bVar, com.helpshift.network.b.e.a aVar, g<T> gVar) {
        this.a = i;
        this.b = a(str);
        this.h = bVar;
        this.d = aVar;
        this.g = map;
        this.e = Integer.valueOf(c.incrementAndGet());
        this.i = gVar;
    }

    private String a(String str) {
        if (str.startsWith("/")) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public String a() {
        switch (this.a) {
            case 0:
                return HttpMethods.GET;
            case 1:
                return HttpMethods.POST;
            default:
                return "";
        }
    }

    public int b() {
        if (this.e != null) {
            return this.e.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public Map<String, String> c() {
        Map a = com.helpshift.network.c.b.a();
        if (this.a == 0) {
            String a2 = com.helpshift.k.b.a().b.a(this.b);
            if (!TextUtils.isEmpty(a2)) {
                a.put("If-None-Match", a2);
            }
        } else if (this.a == 1) {
            a.put("Content-type", "application/x-www-form-urlencoded");
        }
        return a;
    }

    private String j() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/api/lib/2");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }

    public String d() throws InstallException {
        if (com.helpshift.k.b.a().a.b()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i.a);
            stringBuilder.append(com.helpshift.k.b.a().a.b);
            stringBuilder.append(j());
            return stringBuilder.toString();
        }
        throw new InstallException("Install information missing");
    }

    public URL e() throws InstallException, MalformedURLException {
        String d = d();
        if (this.a == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append("?");
            stringBuilder.append(a(k()));
            d = stringBuilder.toString();
        }
        return new URL(d);
    }

    private Map<String, String> k() throws InstallException {
        Map hashMap;
        String j = j();
        if (this.g != null) {
            hashMap = new HashMap(this.g);
        } else {
            hashMap = new HashMap();
        }
        if (com.helpshift.k.b.a().a.b()) {
            hashMap.put("platform-id", com.helpshift.k.b.a().a.c);
            hashMap.put("method", a());
            hashMap.put("uri", j);
            j = x.b();
            if (t.b(j)) {
                hashMap.put(AvidJSONUtil.KEY_TIMESTAMP, j);
            }
            ArrayList<String> arrayList = new ArrayList(hashMap.keySet());
            ArrayList arrayList2 = new ArrayList();
            Collections.sort(arrayList);
            for (String str : arrayList) {
                if (!(str.equals("screenshot") || str.equals("meta"))) {
                    String a = u.a(hashMap.get(str));
                    if (a != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        stringBuilder.append("=");
                        stringBuilder.append(a);
                        arrayList2.add(stringBuilder.toString());
                    }
                }
            }
            try {
                j = com.helpshift.k.b.a().a.a;
                if (com.helpshift.k.b.a().a.b()) {
                    hashMap.put("signature", o.d().s().a(TextUtils.join("&", arrayList2), j));
                    hashMap.remove("method");
                    hashMap.remove("uri");
                    return hashMap;
                }
                throw new InstallException("Install information missing");
            } catch (GeneralSecurityException unused) {
            }
        } else {
            throw new InstallException("appId Missing");
        }
    }

    private String a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (String str : new ArrayList(map.keySet())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("=");
            stringBuilder.append(Uri.encode((String) map.get(str)));
            arrayList.add(stringBuilder.toString());
        }
        return TextUtils.join("&", arrayList);
    }

    private List<com.helpshift.network.g> b(Map<String, String> map) {
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : arrayList) {
            String a = u.a(map.get(str));
            if (a != null) {
                arrayList2.add(new com.helpshift.network.g(str, a));
            }
        }
        return arrayList2;
    }

    public String f() throws InstallException {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (com.helpshift.network.g gVar : b(k())) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            try {
                stringBuilder.append(URLEncoder.encode(gVar.a, "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(gVar.b, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                l.b("HS_Request", "Exception Unsupported Encoding", e);
            }
        }
        return stringBuilder.toString();
    }

    public void g() {
        this.f = true;
    }

    public boolean h() {
        return this.f;
    }

    /* Access modifiers changed, original: protected */
    public <T> e<T> a(d dVar) {
        return this.i.a(dVar);
    }

    public <T> void a(T t) {
        this.h.a(t, Integer.valueOf(b()));
    }

    public void b(NetworkError networkError) {
        if (this.d != null) {
            this.d.a(networkError, Integer.valueOf(b()));
        }
    }

    public boolean i() {
        return this.a == 1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.b);
        stringBuilder.append(" ");
        stringBuilder.append("HS_Request");
        stringBuilder.append(" ");
        stringBuilder.append(" ");
        stringBuilder.append(this.e);
        return stringBuilder.toString();
    }
}
