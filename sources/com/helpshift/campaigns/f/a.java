package com.helpshift.campaigns.f;

import android.app.Activity;
import com.helpshift.campaigns.i.f;
import com.helpshift.campaigns.l.d;
import com.helpshift.campaigns.models.b;
import java.util.ArrayList;
import java.util.List;

public class a implements f {
    private d a;
    private String b;
    private List<com.helpshift.campaigns.i.a> c = new ArrayList();
    private b d;

    public void c(String str) {
    }

    public void d(String str) {
    }

    public void e(String str) {
    }

    private a(String str, d dVar) {
        this.a = dVar;
        this.b = str;
        this.d = this.a.d(str);
    }

    public static a a(String str, d dVar, com.helpshift.campaigns.l.f fVar) {
        Object obj = (dVar.d(str) == null && fVar.d(str, com.helpshift.campaigns.c.b.a().d.a().a) == null) ? null : 1;
        return obj != null ? new a(str, dVar) : null;
    }

    public void a() {
        this.a.b(this.b);
        com.helpshift.campaigns.c.b.a().e.a(com.helpshift.campaigns.models.AnalyticsEvent.a.c, this.b, Boolean.valueOf(false));
    }

    public void a(int i, Activity activity) {
        this.d.a(i, activity);
    }

    public b b() {
        return this.d;
    }

    public void a(b bVar) {
        if (bVar.k().equals(this.b)) {
            this.d = this.a.d(this.b);
            for (com.helpshift.campaigns.i.a a : this.c) {
                a.a();
            }
        }
    }

    public void a(String str) {
        if (str.equals(this.b)) {
            this.d = this.a.d(str);
            for (com.helpshift.campaigns.i.a b : this.c) {
                b.b();
            }
        }
    }

    public void b(String str) {
        if (str.equals(this.b)) {
            this.d = this.a.d(str);
            for (com.helpshift.campaigns.i.a c : this.c) {
                c.c();
            }
        }
    }

    public void a(com.helpshift.campaigns.i.a aVar) {
        this.c.add(aVar);
    }

    public void b(com.helpshift.campaigns.i.a aVar) {
        this.c.remove(aVar);
    }

    public void c() {
        this.a.a((f) this);
    }

    public void d() {
        this.a.b((f) this);
    }
}
