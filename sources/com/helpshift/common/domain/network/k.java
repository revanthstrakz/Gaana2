package com.helpshift.common.domain.network;

import com.helpshift.common.domain.e;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.Method;
import com.helpshift.common.platform.network.c;
import com.helpshift.common.platform.network.f;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.p;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class k extends a implements h {
    public /* bridge */ /* synthetic */ g c(Map map) {
        return super.c(map);
    }

    public k(String str, e eVar, p pVar) {
        super(str, eVar, pVar);
    }

    /* Access modifiers changed, original: 0000 */
    public f a(Map<String, String> map) {
        return new com.helpshift.common.platform.network.e(Method.POST, a(), d(b(map)), c(), 5000);
    }

    private List<c> c() {
        List b = b();
        b.add(new c("Content-type", "application/x-www-form-urlencoded"));
        return b;
    }

    private String d(Map<String, String> map) {
        Map a = a(Method.POST, (Map) map);
        ArrayList<String> arrayList = new ArrayList(a.keySet());
        ArrayList<c> arrayList2 = new ArrayList(arrayList.size());
        for (String str : arrayList) {
            arrayList2.add(new c(str, (String) a.get(str)));
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (c cVar : arrayList2) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            try {
                stringBuilder.append(URLEncoder.encode(cVar.a, "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(cVar.b, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw RootAPIException.a(e, NetworkException.UNSUPPORTED_ENCODING_EXCEPTION);
            }
        }
        return stringBuilder.toString();
    }
}
