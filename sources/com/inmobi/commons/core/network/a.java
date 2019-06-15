package com.inmobi.commons.core.network;

import com.inmobi.commons.core.network.NetworkError.ErrorCode;

public final class a {
    private static final String a = "a";
    private c b;
    private a c;

    public interface a {
        void a(d dVar);

        void b(d dVar);
    }

    public a(c cVar, a aVar) {
        this.b = cVar;
        this.c = aVar;
    }

    public final void a() {
        new Thread(new Runnable() {
            public final void run() {
                try {
                    d a = new b(a.this.b).a();
                    if (a.a()) {
                        a.this.c.b(a);
                    } else {
                        a.this.c.a(a);
                    }
                } catch (Exception e) {
                    a.a;
                    new StringBuilder("Network request failed with unexpected error: ").append(e.getMessage());
                    NetworkError networkError = new NetworkError(ErrorCode.UNKNOWN_ERROR, "Network request failed with unknown error");
                    d dVar = new d();
                    dVar.b = networkError;
                    a.this.c.b(dVar);
                }
            }
        }).start();
    }
}
