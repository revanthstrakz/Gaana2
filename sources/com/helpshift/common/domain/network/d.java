package com.helpshift.common.domain.network;

import com.helpshift.common.domain.e;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.Method;
import com.helpshift.common.platform.network.a;
import com.helpshift.common.platform.network.c;
import com.helpshift.common.platform.network.f;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.p;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class d extends a {
    public /* bridge */ /* synthetic */ g c(Map map) {
        return super.c(map);
    }

    public d(String str, e eVar, p pVar) {
        super(str, eVar, pVar);
    }

    /* Access modifiers changed, original: 0000 */
    public f a(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a());
        stringBuilder.append("?");
        stringBuilder.append(d(b(map)));
        return new a(Method.GET, stringBuilder.toString(), c(), 5000);
    }

    private String d(Map<String, String> map) {
        Map a = a(Method.GET, (Map) map);
        ArrayList arrayList = new ArrayList();
        for (String str : new ArrayList(a.keySet())) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode((String) a.get(str), "UTF-8"));
                arrayList.add(stringBuilder.toString());
            } catch (UnsupportedEncodingException e) {
                throw RootAPIException.a(e, NetworkException.UNSUPPORTED_ENCODING_EXCEPTION);
            }
        }
        return a((CharSequence) "&", (Iterable) arrayList);
    }

    private List<c> c() {
        List b = b();
        String a = this.b.a(this.a);
        if (a != null) {
            b.add(new c("If-None-Match", a));
        }
        return b;
    }
}
