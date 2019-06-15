package com.helpshift.campaigns.c;

import android.text.TextUtils;
import com.helpshift.b.a;
import com.helpshift.d.e;
import com.helpshift.k.c;
import com.helpshift.network.b.e.b;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.i;
import com.helpshift.q.d;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.util.HashMap;
import org.json.JSONArray;

public class f implements a, i {
    public final e a;
    c b;
    String c = "";
    private com.helpshift.d.c d;
    private String e = "";
    private d f;

    public void a() {
    }

    public void a(Integer num) {
    }

    public com.helpshift.network.a.a e() {
        return null;
    }

    protected f(com.helpshift.d.c cVar, e eVar, d dVar, c cVar2) {
        this.a = eVar;
        this.b = cVar2;
        o.a().a((a) this);
        this.d = cVar;
        this.f = dVar;
        Object a = this.f.a("__hs_switch_prev_user");
        Object a2 = this.f.a("__hs_switch_current_user");
        if (a != null && (a instanceof String)) {
            this.e = (String) a;
        }
        if (a2 != null && (a2 instanceof String)) {
            this.c = (String) a2;
        }
    }

    public void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Switch user done : Id : ");
        stringBuilder.append(str);
        l.a("Helpshift_SUControl", stringBuilder.toString());
        this.e = "";
        this.c = "";
        this.f.a("__hs_switch_prev_user", this.e);
        this.f.a("__hs_switch_current_user", this.c);
        this.d.d(str);
    }

    /* JADX WARNING: Missing block: B:19:0x0071, code skipped:
            return;
     */
    public void a(java.lang.String r4, java.lang.String r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = "Helpshift_SUControl";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0072 }
        r1.<init>();	 Catch:{ all -> 0x0072 }
        r2 = "Requesting switch user : New Id : ";
        r1.append(r2);	 Catch:{ all -> 0x0072 }
        r1.append(r4);	 Catch:{ all -> 0x0072 }
        r2 = ", Old Id : ";
        r1.append(r2);	 Catch:{ all -> 0x0072 }
        r1.append(r5);	 Catch:{ all -> 0x0072 }
        r1 = r1.toString();	 Catch:{ all -> 0x0072 }
        com.helpshift.util.l.a(r0, r1);	 Catch:{ all -> 0x0072 }
        r0 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0072 }
        if (r0 != 0) goto L_0x0070;
    L_0x0025:
        r0 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0072 }
        if (r0 != 0) goto L_0x0070;
    L_0x002b:
        r0 = r3.e;	 Catch:{ all -> 0x0072 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0072 }
        if (r0 != 0) goto L_0x004b;
    L_0x0033:
        r0 = r3.c;	 Catch:{ all -> 0x0072 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0072 }
        if (r0 != 0) goto L_0x004b;
    L_0x003b:
        r0 = r3.e;	 Catch:{ all -> 0x0072 }
        r0 = r0.equals(r4);	 Catch:{ all -> 0x0072 }
        if (r0 != 0) goto L_0x0046;
    L_0x0043:
        r3.c = r4;	 Catch:{ all -> 0x0072 }
        goto L_0x004f;
    L_0x0046:
        r3.a(r5);	 Catch:{ all -> 0x0072 }
        monitor-exit(r3);	 Catch:{ all -> 0x0072 }
        return;
    L_0x004b:
        r3.c = r4;	 Catch:{ all -> 0x0072 }
        r3.e = r5;	 Catch:{ all -> 0x0072 }
    L_0x004f:
        r4 = r3.f;	 Catch:{ all -> 0x0072 }
        r5 = "__hs_switch_prev_user";
        r0 = r3.e;	 Catch:{ all -> 0x0072 }
        r4.a(r5, r0);	 Catch:{ all -> 0x0072 }
        r4 = r3.f;	 Catch:{ all -> 0x0072 }
        r5 = "__hs_switch_current_user";
        r0 = r3.c;	 Catch:{ all -> 0x0072 }
        r4.a(r5, r0);	 Catch:{ all -> 0x0072 }
        r4 = r3.a;	 Catch:{ all -> 0x0072 }
        r5 = "data_type_switch_user";
        r0 = 1;
        r4.a(r5, r0);	 Catch:{ all -> 0x0072 }
        r4 = r3.d;	 Catch:{ all -> 0x0072 }
        r5 = r3.c;	 Catch:{ all -> 0x0072 }
        r4.c(r5);	 Catch:{ all -> 0x0072 }
    L_0x0070:
        monitor-exit(r3);	 Catch:{ all -> 0x0072 }
        return;
    L_0x0072:
        r4 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0072 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.c.f.a(java.lang.String, java.lang.String):void");
    }

    public com.helpshift.network.a.a d() {
        if (TextUtils.isEmpty(this.e) || TextUtils.isEmpty(this.c) || this.c.equals(this.e)) {
            return null;
        }
        String a = b.a().a.b.a();
        HashMap hashMap = new HashMap();
        hashMap.put("did", a);
        hashMap.put("uid", this.c);
        hashMap.put("prev-uid", this.e);
        return new com.helpshift.network.a.a(1, "/ma/su/", hashMap, new b<JSONArray>() {
            public void a(JSONArray jSONArray, Integer num) {
                this.a.a("data_type_switch_user", false);
                this.b.f(f.this.c);
                this.a(f.this.c);
            }
        }, new com.helpshift.network.b.e.a() {
            public void a(NetworkError networkError, Integer num) {
                this.a.a("data_type_switch_user", networkError);
            }
        }, new com.helpshift.network.b.b());
    }

    public void b() {
        if (!TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.e)) {
            this.a.b("data_type_switch_user", 1);
        }
    }
}
