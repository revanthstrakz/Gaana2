package com.helpshift.account.a;

import com.helpshift.account.dao.ProfileDTO;
import com.helpshift.common.AutoRetryFailedEventDM.EventType;
import com.helpshift.common.c;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.p;
import java.util.UUID;

public class a implements com.helpshift.common.a {
    final e a;
    private final p b;
    private final com.helpshift.account.dao.a c;
    private b d;

    public a(p pVar, e eVar) {
        this.b = pVar;
        this.a = eVar;
        this.c = pVar.o();
        this.a.m().a(EventType.ACCOUNT, (com.helpshift.common.a) this);
    }

    public void a(String str) {
        this.c.c(str);
        a().d(str);
        this.a.b(new f() {
            public void a() {
                try {
                    a.this.a().d();
                } catch (RootAPIException e) {
                    a.this.a.m().a(EventType.ACCOUNT, e.a());
                    throw e;
                }
            }
        });
    }

    private String c() {
        String d = this.c.d();
        if (!c.a(d)) {
            return d;
        }
        d = UUID.randomUUID().toString();
        this.c.e(d);
        return d;
    }

    private String d() {
        String a = this.c.a();
        return c.a(a) ? c() : a;
    }

    private ProfileDTO e() {
        String d = d();
        ProfileDTO d2 = this.c.d(d);
        if (d2 != null) {
            return d2;
        }
        String str;
        if (c().equals(d)) {
            str = d;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append("_");
            stringBuilder.append(UUID.randomUUID().toString());
            str = stringBuilder.toString();
        }
        return new ProfileDTO(null, d, null, null, null, str, null, null, false);
    }

    public synchronized b a() {
        if (this.d == null) {
            this.d = new b(this.b, this.a, e());
            this.d.g = this.c.c();
            this.d.a(this.c.b());
            if (this.d.a == null) {
                this.d.a();
            }
        }
        return this.d;
    }

    public void b(String str) {
        a().a(str);
        this.c.b(str);
    }

    public void b() {
        a().d();
    }
}
