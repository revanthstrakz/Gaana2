package com.helpshift.meta;

import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.helpshift.common.c;
import com.helpshift.common.domain.e;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.Device;
import com.helpshift.common.platform.n;
import com.helpshift.common.platform.p;
import com.helpshift.meta.dto.b;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;

public class a {
    private e a;
    private final com.helpshift.configuration.a.a b;
    private final n c;
    private final Device d;
    private com.helpshift.meta.a.a e;
    private b f;
    private LinkedList<com.helpshift.meta.dto.a> g = new LinkedList();

    public a(e eVar, p pVar, com.helpshift.configuration.a.a aVar) {
        this.a = eVar;
        this.b = aVar;
        this.e = pVar.g();
        this.c = pVar.n();
        this.d = pVar.d();
    }

    private Object c() {
        return this.c.c(this.e.a());
    }

    private Object d() {
        b s = this.d.s();
        Map hashMap = new HashMap();
        if (s != null) {
            hashMap.put("total-space-phone", s.a);
            hashMap.put("free-space-phone", s.b);
        }
        return this.c.b(hashMap);
    }

    private Object e() {
        Map hashMap = new HashMap();
        hashMap.put("platform", this.d.a());
        hashMap.put("library-version", this.d.b());
        hashMap.put("device-model", this.d.k());
        hashMap.put("os-version", this.d.c());
        try {
            String c = this.b.c("sdkLanguage");
            if (c.a(c)) {
                c = this.d.h();
            }
            if (!c.a(c)) {
                hashMap.put("language-code", c);
            }
        } catch (MissingResourceException unused) {
        }
        hashMap.put(AvidJSONUtil.KEY_TIMESTAMP, this.d.n());
        hashMap.put("application-identifier", this.d.g());
        Object f = this.d.f();
        if (c.a(f)) {
            f = "(unknown)";
        }
        hashMap.put("application-name", f);
        hashMap.put("application-version", this.d.e());
        hashMap.put("disk-space", d());
        if (!this.b.a("fullPrivacy")) {
            hashMap.put("country-code", this.d.m());
            hashMap.put("carrier-name", this.d.o());
        }
        hashMap.put("network-type", this.d.p());
        hashMap.put("battery-level", this.d.r());
        hashMap.put("battery-status", this.d.q());
        return this.c.b(hashMap);
    }

    private Object a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("api-version", this.d.i());
        hashMap.put("library-version", this.d.b());
        if (!c.a(str)) {
            hashMap.put("user-id", str);
        }
        return this.c.b(hashMap);
    }

    private synchronized Object f() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        int size = this.g.size();
        int intValue = this.b.b("debugLogLimit").intValue();
        int i = 0;
        while (i < size && i < intValue) {
            try {
                arrayList.add(this.g.removeFirst());
                i++;
            } catch (NoSuchElementException e) {
                throw RootAPIException.a(e);
            }
        }
        this.g.clear();
        return this.c.d(arrayList);
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    private void a(Map<String, Serializable> map) {
        HashMap hashMap;
        if (map != null) {
            hashMap = new HashMap();
            hashMap.putAll(map);
        } else {
            hashMap = null;
        }
        this.e.a(hashMap);
    }

    public Object a() {
        Map g;
        if (this.f != null) {
            g = g();
            a(g);
        } else {
            g = this.e.b();
        }
        if (g == null) {
            return null;
        }
        if (this.b.a("fullPrivacy")) {
            g.remove("private-data");
        }
        return this.c.c(g);
    }

    public void b() {
        this.e.a(null);
    }

    public Object a(String str, String str2, com.helpshift.meta.dto.c cVar) {
        Map hashMap = new HashMap();
        hashMap.put("breadcrumbs", c());
        hashMap.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, e());
        hashMap.put("logs", f());
        hashMap.put("device_token", str2);
        if (a() != null) {
            hashMap.put("custom_meta", a());
        }
        hashMap.put("extra", a(str));
        if (cVar != null) {
            Map hashMap2 = new HashMap();
            if (cVar.a != null) {
                hashMap2.put("name", cVar.a);
            }
            if (cVar.b != null && cVar.b.trim().length() > 0) {
                hashMap2.put("email", cVar.b.trim());
            }
            hashMap2.put("fp_status", Boolean.valueOf(this.b.a("fullPrivacy")));
            hashMap.put("user_info", this.c.b(hashMap2));
        }
        return this.c.b(hashMap);
    }

    private Map<String, Serializable> g() {
        if (this.f == null) {
            return null;
        }
        Map<String, Serializable> a = this.f.a();
        return a != null ? a(b(a), "hs-tags") : a;
    }

    private Map<String, Serializable> b(Map<String, Serializable> map) {
        HashMap hashMap = new HashMap(map);
        for (Object next : map.keySet()) {
            Object obj = map.get(next);
            if ((next instanceof String) && c.a(((String) next).trim())) {
                hashMap.remove(next);
            }
            if ((obj instanceof String) && c.a(((String) obj).trim())) {
                hashMap.remove(next);
            }
        }
        return hashMap;
    }

    private Map<String, Serializable> a(Map<String, Serializable> map, String str) {
        Object obj = new String[0];
        Object obj2 = map.get(str);
        map.remove(str);
        if (obj2 instanceof String[]) {
            obj = a((String[]) obj2);
        }
        if (obj.length > 0) {
            map.put(str, obj);
        }
        return map;
    }

    private static String[] a(String[] strArr) {
        HashSet hashSet = new HashSet();
        if (strArr != null) {
            for (String str : strArr) {
                if (!c.a(str) && str.trim().length() > 0) {
                    hashSet.add(str.trim());
                }
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }
}
