package com.helpshift.common.domain;

import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.p;
import com.helpshift.conversation.dto.c;
import java.io.File;

public class a {
    p a;
    private e b;

    public interface a {
        void a(RootAPIException rootAPIException);

        void a(c cVar);
    }

    public a(e eVar, p pVar) {
        this.b = eVar;
        this.a = pVar;
    }

    public void a(final c cVar, final String str, final a aVar) {
        this.b.b(new f() {
            public void a() {
                try {
                    a.this.a.a(cVar, str);
                    aVar.a(cVar);
                } catch (RootAPIException e) {
                    aVar.a(e);
                    throw e;
                }
            }
        });
    }

    public void a(c cVar) {
        if (cVar != null && cVar.b != null && cVar.e) {
            new File(cVar.b).delete();
        }
    }
}
