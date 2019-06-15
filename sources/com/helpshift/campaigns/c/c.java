package com.helpshift.campaigns.c;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.helpshift.b.a;
import com.helpshift.campaigns.m.a.b;
import com.helpshift.campaigns.models.d;
import com.helpshift.d.e;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.i;
import com.helpshift.util.j;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class c implements a, i {
    public final e a;
    public final d b;
    private f c;
    private com.helpshift.d.c d;
    private com.helpshift.o.d e;
    private com.helpshift.campaigns.j.a f;
    private com.helpshift.k.c g;
    private com.helpshift.k.a h;

    public void a(Integer num) {
    }

    protected c(com.helpshift.d.c cVar, e eVar, f fVar, d dVar, com.helpshift.o.d dVar2, com.helpshift.k.c cVar2, com.helpshift.k.a aVar) {
        this.d = cVar;
        this.b = dVar;
        this.a = eVar;
        this.e = dVar2;
        this.c = fVar;
        this.g = cVar2;
        this.h = aVar;
        o.a().a((a) this);
        HashMap e = this.b.e();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(e.keySet());
        this.b.a(b.a, arrayList);
    }

    public com.helpshift.network.a.a d() {
        Map c = this.b.c();
        final String str = b.a().d.a().a;
        final ArrayList arrayList = new ArrayList(c.keySet());
        return a(c, new com.helpshift.network.b.e.b<JSONArray>() {
            public void a(JSONArray jSONArray, Integer num) {
                c.this.a(this, arrayList, str, false);
            }
        }, new com.helpshift.network.b.e.a() {
            public void a(NetworkError networkError, Integer num) {
                c.this.a(this, arrayList, networkError);
            }
        }, str);
    }

    @Nullable
    public com.helpshift.network.a.a e() {
        Map d = this.b.d();
        if (d.size() == 0) {
            return null;
        }
        final ArrayList arrayList = new ArrayList(this.b.c().keySet());
        final ArrayList arrayList2 = new ArrayList(d.keySet());
        final String str = b.a().d.a().a;
        return a(d, new com.helpshift.network.b.e.b<JSONArray>() {
            public void a(JSONArray jSONArray, Integer num) {
                c.this.a(this, arrayList2, str, true);
            }
        }, new com.helpshift.network.b.e.a() {
            public void a(NetworkError networkError, Integer num) {
                arrayList2.removeAll(arrayList);
                this.b.a(arrayList2);
                c.this.a(this, arrayList, networkError);
            }
        }, str);
    }

    private com.helpshift.network.a.a a(Map<String, ArrayList> map, com.helpshift.network.b.e.b<JSONArray> bVar, com.helpshift.network.b.e.a aVar, String str) {
        if (map.size() == 0) {
            return null;
        }
        JSONObject a = j.a((Map) map);
        HashMap hashMap = new HashMap();
        hashMap.put("did", this.b.a());
        hashMap.put("uid", str);
        hashMap.put(TtmlNode.TAG_P, a.toString());
        this.b.a(b.c, new ArrayList(map.keySet()));
        return new com.helpshift.network.a.a(1, "/ma/dp/", hashMap, bVar, aVar, new com.helpshift.network.b.b());
    }

    /* Access modifiers changed, original: 0000 */
    public void a(c cVar, ArrayList<String> arrayList, String str, boolean z) {
        cVar.g.c(Boolean.valueOf(false));
        cVar.a.a("data_type_device", z);
        cVar.b.a((List) arrayList);
        cVar.a.b("data_type_device", this.b.c().size());
        if (!cVar.d.b()) {
            cVar.d.a();
            cVar.c.a(str);
            String h = cVar.g.h();
            if (!(TextUtils.isEmpty(h) || h.equals(str))) {
                cVar.c.a(str, h);
            }
            cVar.e = new com.helpshift.o.a(4, "data_type_device");
            cVar.a.a(this.e);
            cVar.a.a(com.helpshift.campaigns.h.d.a().a);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(c cVar, ArrayList<String> arrayList, NetworkError networkError) {
        cVar.b.a(b.a, (ArrayList) arrayList);
        if (!cVar.d.b() && (cVar.e instanceof com.helpshift.o.b)) {
            ((com.helpshift.o.b) cVar.e).b();
        }
        cVar.a.a("data_type_device", networkError);
    }

    public HashMap<String, Object> c() {
        HashMap hashMap = new HashMap();
        Object a = this.b.a(com.helpshift.campaigns.m.a.a.a.d);
        if (a != null) {
            hashMap.put(TtmlNode.TAG_P, a);
        }
        a = this.b.a(com.helpshift.campaigns.m.a.a.a.f);
        if (a != null) {
            hashMap.put("cc", a);
        }
        a = this.b.a(com.helpshift.campaigns.m.a.a.a.g);
        if (a != null) {
            hashMap.put("ln", a);
        }
        String a2 = this.b.a();
        if (a2 != null) {
            hashMap.put("did", a2);
        }
        a = this.b.a(com.helpshift.campaigns.m.a.a.a.a);
        if (a != null) {
            hashMap.put("osv", a);
        }
        a = this.b.a(com.helpshift.campaigns.m.a.a.a.e);
        if (a != null) {
            hashMap.put("dm", a);
        }
        a = this.b.a(com.helpshift.campaigns.m.a.a.a.b);
        if (a != null) {
            hashMap.put("av", a);
        }
        return hashMap;
    }

    public void a() {
        this.b.b();
        HashMap c = this.b.c();
        if (c.size() > 0) {
            this.a.b("data_type_device", c.size());
        }
        Boolean bool = this.h.h;
        Object obj = null;
        if (bool != null && bool.booleanValue()) {
            if (this.f == null) {
                this.f = new com.helpshift.campaigns.j.a(com.helpshift.campaigns.h.d.a().b);
                this.f.b();
            } else {
                f();
            }
            obj = 1;
        }
        bool = this.g.d();
        Boolean e = this.g.e();
        if (obj != null) {
            return;
        }
        if ((bool != null && bool.booleanValue()) || (e != null && !e.booleanValue())) {
            try {
                com.helpshift.campaigns.h.d.a().b.a();
            } catch (Exception e2) {
                l.a("HelpshiftDebug", "Exception while fetching campaigns", e2);
            }
        }
    }

    public void b() {
        this.g.a(Boolean.valueOf(false));
    }

    public void f() {
        if (this.f != null) {
            this.f.a();
            this.f = new com.helpshift.campaigns.j.a(com.helpshift.campaigns.h.d.a().b);
            this.f.b();
        }
    }
}
