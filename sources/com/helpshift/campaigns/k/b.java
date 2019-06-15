package com.helpshift.campaigns.k;

import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.view.MenuItem;
import com.helpshift.campaigns.i.d;
import com.helpshift.campaigns.i.e;
import com.helpshift.util.o;
import com.helpshift.util.p;
import com.payu.custombrowser.util.CBConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class b implements OnActionExpandListener, OnQueryTextListener, MenuItem.OnActionExpandListener, d {
    private static String c;
    private static boolean d;
    private static boolean e;
    private final com.helpshift.campaigns.f.b a;
    private List<e> b = new ArrayList();

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public b(com.helpshift.campaigns.f.b bVar) {
        this.a = bVar;
    }

    public int e() {
        return this.a.a();
    }

    public String a(int i) {
        String str = "";
        com.helpshift.campaigns.models.b a = this.a.a(i);
        return a != null ? a.j() : str;
    }

    public String b(int i) {
        String str = "";
        com.helpshift.campaigns.models.b a = this.a.a(i);
        return a != null ? a.k() : str;
    }

    public HashMap<String, Object> c(int i) {
        Object a;
        CharSequence charSequence;
        HashMap hashMap = new HashMap();
        com.helpshift.campaigns.models.b a2 = this.a.a(i);
        String str = "";
        if (a2 != null) {
            a = p.a(a2.e, -1);
            charSequence = a2.c;
        } else {
            charSequence = str;
            a = null;
        }
        if (a == null) {
            hashMap.put(CBConstant.DEFAULT_VALUE, Boolean.valueOf(true));
            a = p.a(o.b().getResources(), com.helpshift.e.e.hs__cam_inbox_default_icon, -1);
            if (!(a2 == null || TextUtils.isEmpty(charSequence))) {
                String str2 = a2.e;
                if (!TextUtils.isEmpty(str2)) {
                    File file = new File(str2);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                com.helpshift.campaigns.c.b.a().f.c(charSequence, a2.k());
            }
        } else {
            com.helpshift.campaigns.c.b.a().f.e(charSequence);
        }
        hashMap.put("bitmap", a);
        return hashMap;
    }

    public String d(int i) {
        String str = "";
        com.helpshift.campaigns.models.b a = this.a.a(i);
        return a != null ? a.i() : str;
    }

    public boolean e(int i) {
        com.helpshift.campaigns.models.b a = this.a.a(i);
        return a != null ? a.e() : false;
    }

    public boolean f(int i) {
        com.helpshift.campaigns.models.b a = this.a.a(i);
        return a != null ? a.d() : false;
    }

    public long g(int i) {
        com.helpshift.campaigns.models.b a = this.a.a(i);
        return a != null ? a.a() : 0;
    }

    public void a(int i, boolean z) {
        com.helpshift.campaigns.models.b a = this.a.a(i);
        if (a != null) {
            this.a.a(a.k(), z);
        }
    }

    public void f() {
        this.a.b();
    }

    public void g() {
        this.a.c();
    }

    public void h(int i) {
        com.helpshift.campaigns.models.b a = this.a.a(i);
        if (a != null) {
            this.a.f(a.k());
        }
    }

    public void a(e eVar) {
        this.b.add(eVar);
    }

    public void b(e eVar) {
        this.b.remove(eVar);
    }

    public void a() {
        for (e f : this.b) {
            f.f();
        }
    }

    public void b() {
        for (e f : this.b) {
            f.f();
        }
    }

    public void c() {
        for (e f : this.b) {
            f.f();
        }
    }

    public void d() {
        for (e f : this.b) {
            f.f();
        }
    }

    public boolean onQueryTextChange(String str) {
        for (e h : this.b) {
            h.h();
        }
        if (e) {
            e = false;
        } else {
            c = str;
            a(str);
        }
        return true;
    }

    public void a(String str) {
        this.a.g(str);
    }

    public void a(boolean z) {
        d = z;
    }

    public boolean h() {
        return d;
    }

    public void b(boolean z) {
        e = z;
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        for (e g : this.b) {
            g.g();
        }
        this.a.d();
        return true;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        for (e i : this.b) {
            i.i();
        }
        this.a.e();
        return true;
    }

    public String i() {
        return c;
    }

    public void j() {
        this.a.g();
        this.a.a((d) this);
    }

    public void k() {
        this.a.h();
        this.a.a(null);
    }

    public void l() {
        this.a.f();
    }
}
