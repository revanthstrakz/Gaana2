package com.helpshift.common.platform;

import com.helpshift.g.a.a;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class f implements a {
    private o a;

    public f(o oVar) {
        this.a = oVar;
    }

    public void a(String str, boolean z) {
        Serializable b = b();
        b.put(str, Boolean.valueOf(z));
        this.a.a("key_faq_mark_event", b);
    }

    public void a(String str) {
        Serializable b = b();
        if (b.containsKey(str)) {
            b.remove(str);
            this.a.a("key_faq_mark_event", b);
        }
    }

    public Map<String, Boolean> a() {
        return b();
    }

    private synchronized HashMap<String, Boolean> b() {
        HashMap<String, Boolean> hashMap;
        Object b = this.a.b("key_faq_mark_event");
        if (b instanceof HashMap) {
            hashMap = (HashMap) b;
        } else {
            hashMap = new HashMap();
        }
        return hashMap;
    }
}
