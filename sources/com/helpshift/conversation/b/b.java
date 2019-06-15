package com.helpshift.conversation.b;

import com.helpshift.account.a.a;
import com.helpshift.common.domain.e;
import com.helpshift.common.platform.p;

public class b {
    private final p a;
    private final e b;
    private final a c;
    private a d;

    public b(p pVar, e eVar, a aVar) {
        this.a = pVar;
        this.b = eVar;
        this.c = aVar;
    }

    public synchronized a a() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new a(this.a, this.b, this.c.a());
                }
            }
        }
        return this.d;
    }
}
