package com.helpshift.campaigns.c;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.helpshift.campaigns.m.a.b;
import com.helpshift.campaigns.models.PropertyValue;
import com.helpshift.campaigns.models.h;
import com.helpshift.d.e;
import com.helpshift.k.c;
import com.helpshift.network.a.a;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.i;
import com.helpshift.util.j;
import com.helpshift.util.l;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class g implements i {
    public final e a;
    h b;
    c c;
    private e d;
    private f e;
    private com.helpshift.util.a.c f;
    private com.helpshift.campaigns.l.i g;
    private Integer h;

    protected g(e eVar, e eVar2, f fVar, com.helpshift.util.a.c cVar, com.helpshift.campaigns.l.i iVar, Integer num, c cVar2) {
        this.f = cVar;
        this.h = num;
        this.c = cVar2;
        this.d = eVar2;
        this.e = fVar;
        this.g = iVar;
        String b = this.c.b();
        if (TextUtils.isEmpty(b)) {
            b = this.c.c();
        }
        if (TextUtils.isEmpty(b)) {
            throw new IllegalArgumentException("Found no valid ID in user controller constructor.");
        }
        a(b);
        this.a = eVar;
    }

    public void a(Integer num) {
        this.h = num;
    }

    public h a() {
        return this.b;
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Object obj = null;
            if (this.b != null) {
                obj = this.b.a;
            }
            if (this.b == null || !str.equals(obj)) {
                this.g.a(str);
                this.b = new h(str, this.g);
                this.c.c(str);
            }
            HashMap c = c();
            a().a(b.a, new ArrayList(Arrays.asList((String[]) c.keySet().toArray(new String[c.keySet().size()]))));
        }
    }

    public HashMap<String, PropertyValue> b() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(a().a());
        return hashMap;
    }

    public HashMap<String, PropertyValue> c() {
        return a().c();
    }

    private HashMap<String, ArrayList> a(HashMap<String, PropertyValue> hashMap, Integer num) {
        HashMap hashMap2 = new HashMap();
        Integer valueOf = Integer.valueOf(0);
        num = Integer.valueOf((num.intValue() * 1024) * 1024);
        for (Entry entry : hashMap.entrySet()) {
            ArrayList d = ((PropertyValue) entry.getValue()).d();
            try {
                int length = new JSONArray(d).toString().getBytes("UTF-8").length;
                if (valueOf.intValue() + length > num.intValue()) {
                    break;
                }
                hashMap2.put(entry.getKey(), d);
                valueOf = Integer.valueOf(valueOf.intValue() + length);
            } catch (UnsupportedEncodingException e) {
                l.a("Helpshift_UserControl", "Exception in batching : ", e);
            }
        }
        return hashMap2;
    }

    @Nullable
    public a d() {
        Map a = a(b(), this.h);
        final ArrayList arrayList = new ArrayList(a.keySet());
        return a(a, new com.helpshift.network.b.e.b() {
            public void a(Object obj, Integer num) {
                g.this.a(this, arrayList, false);
            }
        }, new com.helpshift.network.b.e.a() {
            public void a(NetworkError networkError, Integer num) {
                g.this.a(this, arrayList, networkError);
            }
        });
    }

    @Nullable
    public a e() {
        Map a = a(a().b(), this.h);
        if (a.size() == 0) {
            return null;
        }
        final ArrayList arrayList = new ArrayList(b().keySet());
        final ArrayList arrayList2 = new ArrayList(a.keySet());
        return a(a, new com.helpshift.network.b.e.b<JSONArray>() {
            public void a(JSONArray jSONArray, Integer num) {
                g.this.a(this, arrayList2, true);
            }
        }, new com.helpshift.network.b.e.a() {
            public void a(NetworkError networkError, Integer num) {
                arrayList2.removeAll(arrayList);
                this.a().a(arrayList2);
                g.this.a(this, arrayList, networkError);
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void a(g gVar, ArrayList<String> arrayList, boolean z) {
        gVar.a.a("data_type_user", z);
        gVar.a().a(arrayList);
        gVar.a.b("data_type_user", b().size());
    }

    /* Access modifiers changed, original: 0000 */
    public void a(g gVar, ArrayList<String> arrayList, NetworkError networkError) {
        gVar.a().a(b.a, arrayList);
        gVar.a.a("data_type_user", networkError);
    }

    private a a(Map<String, ArrayList> map, com.helpshift.network.b.e.b<JSONArray> bVar, com.helpshift.network.b.e.a aVar) {
        if (map.size() == 0) {
            return null;
        }
        JSONObject a = j.a((Map) map);
        HashMap hashMap = new HashMap();
        hashMap.put("did", this.c.c());
        hashMap.put("uid", a().a);
        hashMap.put(TtmlNode.TAG_P, a.toString());
        a().a(b.c, new ArrayList(map.keySet()));
        return new a(1, "/ma/up/", hashMap, bVar, aVar, new com.helpshift.network.b.b());
    }
}
