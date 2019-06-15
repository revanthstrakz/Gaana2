package com.helpshift.common.platform;

import android.content.Context;
import com.helpshift.common.a.a;
import com.helpshift.common.c;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements com.helpshift.conversation.a.b {
    private a a;
    private o b;

    public b(Context context, o oVar) {
        this.a = a.a(context);
        this.b = oVar;
    }

    private synchronized com.helpshift.conversation.dto.a.a.a i(long j) {
        com.helpshift.conversation.dto.a.a.a aVar;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        if (b == null) {
            aVar = new com.helpshift.conversation.dto.a.a.a(j);
        } else {
            aVar = new com.helpshift.conversation.dto.a.a.a(b);
        }
        return aVar;
    }

    public synchronized void a(long j, com.helpshift.conversation.dto.a aVar) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.c(aVar.a);
        i.a(aVar.b);
        i.a(aVar.c);
        this.a.a(i.a());
    }

    public synchronized com.helpshift.conversation.dto.a a(long j) {
        com.helpshift.conversation.dto.a aVar;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        aVar = null;
        if (b != null) {
            String str = b.d;
            long j2 = b.e;
            int i = b.g;
            if (!c.a(str)) {
                aVar = new com.helpshift.conversation.dto.a(str, j2, i);
            }
        }
        return aVar;
    }

    public synchronized void a(long j, String str) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.a(str);
        this.a.a(i.a());
    }

    public synchronized String b(long j) {
        String str;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        str = null;
        if (b != null) {
            str = b.b;
        }
        return str;
    }

    public synchronized void b(long j, String str) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.b(str);
        this.a.a(i.a());
    }

    public synchronized String c(long j) {
        String str;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        str = null;
        if (b != null) {
            str = b.c;
        }
        return str;
    }

    public synchronized void a(long j, com.helpshift.conversation.dto.c cVar) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.a(cVar);
        this.a.a(i.a());
    }

    public synchronized com.helpshift.conversation.dto.c d(long j) {
        com.helpshift.conversation.dto.c cVar;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        cVar = null;
        if (b != null) {
            cVar = b.f;
        }
        return cVar;
    }

    public synchronized void c(long j, String str) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.f(str);
        this.a.a(i.a());
    }

    public synchronized String e(long j) {
        String str;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        str = null;
        if (b != null) {
            str = b.k;
        }
        return str;
    }

    public synchronized void d(long j, String str) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.d(str);
        this.a.a(i.a());
    }

    public synchronized String f(long j) {
        String str;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        str = null;
        if (b != null) {
            str = b.h;
        }
        return str;
    }

    public synchronized void e(long j, String str) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.e(str);
        this.a.a(i.a());
    }

    public synchronized String g(long j) {
        String str;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        str = null;
        if (b != null) {
            str = b.i;
        }
        return str;
    }

    public synchronized void a(long j, boolean z) {
        com.helpshift.conversation.dto.a.a.a i = i(j);
        i.a(z);
        this.a.a(i.a());
    }

    public com.helpshift.conversation.a.c a(String str) {
        String a = this.b.a("push_notification_data");
        com.helpshift.conversation.a.c cVar = null;
        if (c.a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            if (jSONObject.has(str)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                cVar = new com.helpshift.conversation.a.c(jSONObject2.getInt("notification_count"), jSONObject2.getString("notification_title"));
            }
        } catch (JSONException unused) {
        }
        return cVar;
    }

    public void a(String str, com.helpshift.conversation.a.c cVar) {
        String a = this.b.a("push_notification_data");
        if (c.a(a)) {
            a = "{}";
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            if (cVar == null) {
                jSONObject.remove(str);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("notification_count", cVar.a);
                jSONObject2.put("notification_title", cVar.b);
                jSONObject.put(str, jSONObject2);
            }
            this.b.a("push_notification_data", jSONObject.toString());
        } catch (JSONException unused) {
        }
    }

    public synchronized boolean h(long j) {
        boolean z;
        com.helpshift.conversation.dto.a.a b = this.a.b(j);
        z = false;
        if (b != null) {
            z = b.j;
        }
        return z;
    }
}
