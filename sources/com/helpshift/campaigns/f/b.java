package com.helpshift.campaigns.f;

import android.text.TextUtils;
import com.helpshift.campaigns.i.f;
import com.helpshift.campaigns.l.d;
import com.helpshift.campaigns.models.AnalyticsEvent.a;
import com.helpshift.util.l;
import java.util.ArrayList;
import java.util.List;

public class b implements f {
    private d a;
    private List<com.helpshift.campaigns.models.b> b;
    private List<com.helpshift.campaigns.models.b> c;
    private boolean d = false;
    private com.helpshift.campaigns.i.d e;
    private com.helpshift.campaigns.models.b f;
    private int g;

    public void b(String str) {
    }

    public void c(String str) {
    }

    public void d(String str) {
    }

    public b(d dVar) {
        int i = 0;
        this.a = dVar;
        this.c = i();
        this.b = this.c;
        String str = "Helpshift_CampListInt";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Campaigns to show : ");
        if (this.b != null) {
            i = this.b.size();
        }
        stringBuilder.append(i);
        l.a(str, stringBuilder.toString());
    }

    public com.helpshift.campaigns.models.b a(int i) {
        return (this.b == null || i >= this.b.size() || i < 0) ? null : (com.helpshift.campaigns.models.b) this.b.get(i);
    }

    public int a() {
        return this.b != null ? this.b.size() : 0;
    }

    private List<com.helpshift.campaigns.models.b> i() {
        return com.helpshift.campaigns.m.b.a(this.a, com.helpshift.campaigns.c.b.a().d.a().a);
    }

    private boolean h(String str) {
        if (!(TextUtils.isEmpty(str) || this.b == null)) {
            Object obj = null;
            for (com.helpshift.campaigns.models.b bVar : this.b) {
                if (bVar.k().equals(str)) {
                    obj = bVar;
                    break;
                }
            }
            if (obj != null) {
                this.g = this.b.indexOf(obj);
                this.f = obj;
                this.b.remove(obj);
                com.helpshift.util.b.a(str);
                return true;
            }
        }
        return false;
    }

    private void j() {
        if (this.f != null) {
            String k = this.f.k();
            this.a.e(k);
            com.helpshift.campaigns.c.b.a().e.a(a.e, k, Boolean.valueOf(false));
            if (this.d) {
                this.c.remove(this.f);
            }
            this.f = null;
        }
    }

    public void a(String str, boolean z) {
        if (this.f != null) {
            j();
        }
        h(str);
        if (!z) {
            j();
        }
    }

    public void b() {
        j();
    }

    public void c() {
        if (this.f != null) {
            this.b.add(this.g, this.f);
            this.f = null;
        }
    }

    public void f(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.a(str);
            if (this.b != null) {
                for (com.helpshift.campaigns.models.b bVar : this.b) {
                    if (bVar.k().equals(str)) {
                        bVar.b(true);
                        com.helpshift.campaigns.c.b.a().e.a(a.d, bVar.k(), Boolean.valueOf(false));
                        return;
                    }
                }
            }
        }
    }

    public void g(String str) {
        if (this.f != null) {
            b();
        }
        if (TextUtils.isEmpty(str) || str.length() <= 2 || this.c == null) {
            this.b = this.c;
        } else {
            ArrayList arrayList = new ArrayList();
            for (com.helpshift.campaigns.models.b bVar : this.c) {
                String[] split = str.toLowerCase().trim().split("\\s+");
                String j = bVar.j();
                String i = bVar.i();
                for (CharSequence charSequence : split) {
                    if ((i != null && i.toLowerCase().contains(charSequence)) || (j != null && j.toLowerCase().contains(charSequence))) {
                        arrayList.add(bVar);
                        break;
                    }
                }
            }
            this.b = arrayList;
        }
        if (this.e != null) {
            this.e.d();
        }
    }

    public void d() {
        if (this.f != null) {
            b();
        }
        this.d = true;
    }

    public void e() {
        this.d = false;
        if (this.f != null) {
            b();
        }
        this.c = i();
        this.b = this.c;
    }

    public void f() {
        this.c = i();
        if (!this.d) {
            this.b = this.c;
        }
    }

    public void a(com.helpshift.campaigns.models.b bVar) {
        this.c = i();
        if (!this.d) {
            this.b = this.c;
        }
        if (this.e != null) {
            this.e.a();
        }
    }

    public void a(String str) {
        if (this.c != null) {
            int i = -1;
            Object obj = null;
            for (com.helpshift.campaigns.models.b k : this.c) {
                i++;
                if (k.k().equals(str)) {
                    obj = 1;
                    break;
                }
            }
            if (i >= 0 && i < this.c.size() && obj != null) {
                this.c.set(i, this.a.d(str));
            }
            if (this.e != null) {
                this.e.b();
            }
        }
    }

    public void e(String str) {
        if (!TextUtils.isEmpty(str) && this.b != null) {
            for (com.helpshift.campaigns.models.b bVar : this.b) {
                if (bVar.k().equals(str)) {
                    bVar.a(true);
                    if (this.e != null) {
                        this.e.c();
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void a(com.helpshift.campaigns.i.d dVar) {
        this.e = dVar;
    }

    public void g() {
        this.a.a((f) this);
    }

    public void h() {
        this.a.b((f) this);
    }
}
