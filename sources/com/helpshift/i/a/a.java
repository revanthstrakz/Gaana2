package com.helpshift.i.a;

import com.helpshift.common.c;
import com.helpshift.common.platform.Device;
import com.helpshift.common.platform.p;
import java.util.Locale;

public class a {
    private com.helpshift.configuration.a.a a;
    private Device b;
    private Locale c;

    public a(com.helpshift.configuration.a.a aVar, p pVar) {
        this.a = aVar;
        this.b = pVar.d();
    }

    public void a() {
        if (this.c == null) {
            this.c = this.b.j();
        }
    }

    public void b() {
        if (this.c != null) {
            this.b.a(this.c);
            this.c = null;
        }
    }

    public Locale c() {
        String c = this.a.c("sdkLanguage");
        if (c.a(c)) {
            return Locale.getDefault();
        }
        if (!c.contains("_")) {
            return new Locale(c);
        }
        String[] split = c.split("_");
        return new Locale(split[0], split[1]);
    }

    public Locale d() {
        String c = this.a.c("sdkLanguage");
        if (c.a(c)) {
            return null;
        }
        if (!c.contains("_")) {
            return new Locale(c);
        }
        String[] split = c.split("_");
        return new Locale(split[0], split[1]);
    }

    public String e() {
        String c = this.a.c("sdkLanguage");
        return c.a(c) ? Locale.getDefault().toString() : c;
    }
}
