package com.helpshift.support.f;

import android.os.Bundle;
import com.helpshift.support.d.c;
import com.helpshift.support.m;
import java.util.HashMap;
import java.util.List;

public class e implements g {
    private final int a;
    private final String b;
    private final String c;
    private final HashMap d;
    private c e;

    public void a(c cVar) {
        this.e = cVar;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public void c() {
        Bundle c = m.c(m.a(this.d));
        c.putString("sectionPublishId", this.c);
        c.putInt("support_mode", 2);
        this.e.a(c, true, (List) this.d.get("customContactUsFlows"));
    }
}
