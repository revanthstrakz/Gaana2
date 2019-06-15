package com.helpshift.campaigns.h;

import com.helpshift.campaigns.c.d;
import com.helpshift.network.a.a;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.i;
import java.util.concurrent.Callable;

public class c implements Callable {
    private i a;
    private com.helpshift.network.a.c b;

    public c(d dVar, com.helpshift.network.a.c cVar) {
        this.a = dVar;
        this.b = cVar;
    }

    public Object call() throws Exception {
        return a();
    }

    public Object a() throws Exception {
        a d = this.a.d();
        if (d == null) {
            return null;
        }
        Object obj = this.b.a(d).get();
        if (!(obj instanceof NetworkError)) {
            return obj;
        }
        throw ((NetworkError) obj);
    }
}
