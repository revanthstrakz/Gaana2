package com.helpshift.support.f;

import android.os.Bundle;
import com.helpshift.support.d.c;
import com.helpshift.support.m;
import java.util.HashMap;

public class a implements g {
    private final int a;
    private final String b;
    private final HashMap c;
    private c d;

    public void a(c cVar) {
        this.d = cVar;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public void c() {
        Bundle c = m.c(m.b(this.c));
        c.putString("chatLaunchSource", "support");
        this.d.a(c, true);
    }
}
