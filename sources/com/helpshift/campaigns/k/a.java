package com.helpshift.campaigns.k;

import android.app.Activity;
import android.text.TextUtils;
import com.helpshift.campaigns.i.b;
import com.helpshift.campaigns.models.ActionModel;
import com.helpshift.e.e;
import com.helpshift.util.o;
import com.helpshift.util.p;
import com.payu.custombrowser.util.CBConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a implements com.helpshift.campaigns.i.a {
    private com.helpshift.campaigns.f.a a;
    private List<b> b = new ArrayList();

    public void b() {
    }

    public a(com.helpshift.campaigns.f.a aVar) {
        this.a = aVar;
    }

    public HashMap<String, Object> d() {
        Object a;
        CharSequence charSequence;
        HashMap hashMap = new HashMap();
        com.helpshift.campaigns.models.b b = this.a.b();
        String str = "";
        if (b != null) {
            a = p.a(b.d, -1);
            charSequence = b.b;
        } else {
            charSequence = str;
            a = null;
        }
        if (a != null || b == null || TextUtils.isEmpty(charSequence)) {
            com.helpshift.campaigns.c.b.a().f.e(charSequence);
        } else {
            a = p.a(o.b().getResources(), e.hs__cam_inbox_default_cover, -1);
            hashMap.put(CBConstant.DEFAULT_VALUE, Boolean.valueOf(true));
            String str2 = b.d;
            if (!TextUtils.isEmpty(str2)) {
                File file = new File(str2);
                if (file.exists()) {
                    file.delete();
                }
            }
            com.helpshift.campaigns.c.b.a().f.d(charSequence, b.k());
        }
        hashMap.put("bitmap", a);
        return hashMap;
    }

    public String e() {
        return this.a.b() != null ? this.a.b().j() : "";
    }

    public String f() {
        return this.a.b() != null ? this.a.b().g() : "";
    }

    public String g() {
        return this.a.b() != null ? this.a.b().i() : "";
    }

    public String h() {
        return this.a.b() != null ? this.a.b().f() : "";
    }

    public String i() {
        return this.a.b() != null ? this.a.b().h() : "";
    }

    public boolean j() {
        com.helpshift.campaigns.models.b b = this.a.b();
        return b != null && b.c();
    }

    public int k() {
        List list = this.a.b() != null ? this.a.b().f : null;
        return list != null ? list.size() : 0;
    }

    public String a(int i) {
        String str = "";
        return (this.a.b() == null || i < 0 || i >= this.a.b().f.size()) ? str : ((ActionModel) this.a.b().f.get(i)).a;
    }

    public String b(int i) {
        String str = "";
        return (this.a.b() == null || i < 0 || i >= this.a.b().f.size()) ? str : ((ActionModel) this.a.b().f.get(i)).d;
    }

    public void l() {
        if (!j()) {
            this.a.a();
        }
    }

    public void a(int i, Activity activity) {
        this.a.a(i, activity);
    }

    public void a() {
        for (b c : this.b) {
            c.c();
        }
    }

    public void c() {
        for (b c : this.b) {
            c.c();
        }
    }

    public void a(b bVar) {
        this.b.add(bVar);
    }

    public void b(b bVar) {
        this.b.remove(bVar);
    }

    public void m() {
        this.a.c();
        this.a.a((com.helpshift.campaigns.i.a) this);
    }

    public void n() {
        this.a.d();
        this.a.b((com.helpshift.campaigns.i.a) this);
    }
}
