package com.helpshift.common.domain.network;

import com.helpshift.common.domain.e;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.Device;
import com.helpshift.common.platform.n;
import com.helpshift.common.platform.network.Method;
import com.helpshift.common.platform.network.b;
import com.helpshift.common.platform.network.c;
import com.helpshift.common.platform.network.d;
import com.helpshift.common.platform.network.f;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.p;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

abstract class a implements h {
    final String a;
    final d b;
    private final b c;
    private final String d;
    private final com.helpshift.i.a.a e;
    private final com.helpshift.e.a f;
    private final String g;
    private final String h;
    private final Device i;
    private final n j;

    public abstract f a(Map<String, String> map);

    a(String str, e eVar, p pVar) {
        this.a = str;
        this.e = eVar.k();
        this.f = eVar.h();
        this.b = pVar.q();
        this.c = pVar.k();
        this.d = pVar.a();
        this.g = pVar.b();
        this.h = pVar.c();
        this.i = pVar.d();
        this.j = pVar.n();
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, String> b(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (String str : map.keySet()) {
            if (str != null) {
                String str2 = (String) map.get(str);
                if (str2 != null) {
                    hashMap.put(str, str2);
                }
            }
        }
        return hashMap;
    }

    /* Access modifiers changed, original: protected */
    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i.a);
        stringBuilder.append(this.g);
        stringBuilder.append(c());
        return stringBuilder.toString();
    }

    private String c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/api/lib/2");
        stringBuilder.append(this.a);
        return stringBuilder.toString();
    }

    private String d() {
        return new DecimalFormat("0.000", new DecimalFormatSymbols(Locale.US)).format(((double) (System.currentTimeMillis() / 1000)) + ((double) this.b.a()));
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, String> a(Method method, Map<String, String> map) {
        map.put("platform-id", this.h);
        map.put("method", method.name());
        map.put("uri", c());
        map.put(AvidJSONUtil.KEY_TIMESTAMP, d());
        map.put("sm", e());
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        Iterable arrayList2 = new ArrayList();
        for (String str : arrayList) {
            if (!(str.equals("screenshot") || str.equals("meta") || str.equals("originalFileName"))) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("=");
                stringBuilder.append((String) map.get(str));
                arrayList2.add(stringBuilder.toString());
            }
        }
        try {
            map.put("signature", this.f.a(a((CharSequence) "&", arrayList2), this.d));
            map.remove("method");
            map.remove("uri");
            return map;
        } catch (GeneralSecurityException e) {
            NetworkException networkException = NetworkException.UNABLE_TO_GENERATE_SIGNATURE;
            networkException.route = this.a;
            throw RootAPIException.a(e, networkException, "Network error");
        }
    }

    private String e() {
        Map hashMap = new HashMap();
        hashMap.put("ia", Boolean.valueOf(true));
        hashMap.put("rs", Boolean.valueOf(true));
        hashMap.put("clc", Boolean.valueOf(true));
        hashMap.put("atai", Boolean.valueOf(true));
        hashMap.put("fp", Boolean.valueOf(true));
        return this.j.a(hashMap);
    }

    /* Access modifiers changed, original: 0000 */
    public String a(CharSequence charSequence, Iterable iterable) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(next);
        }
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public List<c> b() {
        String format = String.format(Locale.ENGLISH, "Helpshift-%s/%s/%s", new Object[]{this.i.a(), this.i.b(), this.i.c()});
        String format2 = String.format(Locale.ENGLISH, "%s;q=1.0", new Object[]{this.e.e()});
        String format3 = String.format(Locale.ENGLISH, "Helpshift-%s/%s", new Object[]{this.i.a(), this.i.b()});
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(com.til.colombia.android.internal.e.c, format));
        arrayList.add(new c("Accept-Language", format2));
        arrayList.add(new c("Accept-Encoding", "gzip"));
        arrayList.add(new c("X-HS-V", format3));
        return arrayList;
    }

    public g c(Map<String, String> map) {
        return this.c.a(a(map));
    }
}
