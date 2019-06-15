package com.helpshift.campaigns.c;

import com.helpshift.b.a;
import com.helpshift.campaigns.l.l;
import com.helpshift.campaigns.m.a.b;
import com.helpshift.campaigns.models.f;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.i;
import com.helpshift.util.a.c;
import com.helpshift.util.j;
import com.helpshift.util.o;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class e implements a, i {
    public final com.helpshift.d.e a;
    f b;
    public final c c;
    public final l d;
    private Integer e;

    public com.helpshift.network.a.a e() {
        return null;
    }

    protected e(com.helpshift.d.e eVar, c cVar, l lVar, Integer num) {
        this.c = cVar;
        this.d = lVar;
        this.e = num;
        this.a = eVar;
        o.a().a((a) this);
        this.d.a();
        ArrayList a = this.d.a(b.c);
        if (a != null && a.size() > 0) {
            int size = a.size();
            String[] strArr = new String[size];
            for (int i = 0; i < size; i++) {
                strArr[i] = ((f) a.get(i)).a;
            }
            this.d.a(b.a, strArr);
        }
    }

    public void a(Integer num) {
        this.e = num;
    }

    public void c() {
        this.c.b(new Runnable() {
            public void run() {
                this.b = new f();
                this.g();
            }
        });
    }

    public void f() {
        this.c.a(new Runnable() {
            public void run() {
                if (this.b != null) {
                    this.b.b();
                    this.h();
                    this.b = null;
                    this.a.a("data_type_session", 1);
                }
            }
        });
    }

    public void g() {
        this.d.a(this.b);
    }

    public void h() {
        this.d.b(this.b);
    }

    private List<f> a(ArrayList<f> arrayList, Integer num) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList == null) {
            return arrayList2;
        }
        arrayList2 = new ArrayList();
        num = Integer.valueOf((num.intValue() * 1024) * 1024);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((f) it.next()).a());
        }
        try {
            int length = new JSONArray(arrayList2).toString().getBytes("UTF-8").length;
            if (length <= num.intValue()) {
                return arrayList;
            }
            return arrayList.subList(0, Integer.valueOf(num.intValue() / Integer.valueOf(length / arrayList2.size()).intValue()).intValue());
        } catch (UnsupportedEncodingException e) {
            com.helpshift.util.l.a("HelpshiftDebug", "Unsupported exception in batching events : ", e);
            return arrayList;
        }
    }

    public com.helpshift.network.a.a d() {
        com.helpshift.network.a.a aVar;
        List<f> a = a(this.d.a(b.a), this.e);
        if (a.size() > 0) {
            List arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            String str = b.a().d.a().a;
            String a2 = b.a().a.b.a();
            for (f fVar : a) {
                if (fVar.c.equals(str) && fVar.b.equals(a2)) {
                    arrayList.addAll(fVar.a());
                    arrayList2.add(fVar.a);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            JSONArray a3 = j.a(arrayList);
            HashMap hashMap = new HashMap();
            hashMap.put("did", a2);
            hashMap.put("uid", str);
            hashMap.put(com.facebook.ads.internal.g.e.a, a3.toString());
            final String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
            this.d.a(b.c, strArr);
            com.helpshift.network.a.a aVar2 = new com.helpshift.network.a.a(1, "/ma/session/", hashMap, new com.helpshift.network.b.e.b<JSONArray>() {
                public void a(JSONArray jSONArray, Integer num) {
                    this.c.b(new Runnable() {
                        public void run() {
                            this.d.a(strArr);
                            this.a.a("data_type_session", false);
                        }
                    });
                }
            }, new com.helpshift.network.b.e.a() {
                public void a(NetworkError networkError, Integer num) {
                    this.d.a(b.a, strArr);
                    this.a.a("data_type_session", networkError);
                }
            }, new com.helpshift.network.b.b());
        } else {
            aVar = null;
        }
        return aVar;
    }

    public void a() {
        c();
    }

    public void b() {
        f();
    }
}
