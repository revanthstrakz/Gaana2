package com.helpshift.common.platform.network.a;

import com.helpshift.common.c;
import com.helpshift.websockets.WebSocketException;
import com.helpshift.websockets.ae;
import com.helpshift.websockets.ag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a {
    private final ae a;
    private final b b;

    public static class a {
        private String a;
        private int b;
        private int c;
        private List<String> d = new ArrayList();
        private List<String> e = new ArrayList();
        private Map<String, String> f = new HashMap();
        private b g;

        public a(String str) {
            this.a = str;
        }

        public a a(int i) {
            this.b = i;
            return this;
        }

        public a a(String str) {
            this.d.add(str);
            return this;
        }

        public a b(String str) {
            this.e.add(str);
            return this;
        }

        public a a(String str, String str2) {
            if (!(str2 == null || c.a(str))) {
                this.f.put(str, str2);
            }
            return this;
        }

        public a a(b bVar) {
            this.g = bVar;
            return this;
        }

        public a a() throws IOException {
            ae a = new ag().a(this.b).a(this.a);
            a.f().setSoTimeout(this.c);
            for (String b : this.d) {
                a.b(b);
            }
            for (String b2 : this.e) {
                a.a(b2);
            }
            for (String b22 : this.f.keySet()) {
                a.a(b22, (String) this.f.get(b22));
            }
            return new a(a, this.g);
        }
    }

    a(ae aeVar, b bVar) {
        this.a = aeVar;
        this.b = bVar;
        aeVar.a(new c(this, bVar));
    }

    public void a() {
        try {
            this.a.g();
        } catch (WebSocketException e) {
            this.b.b(this, e.getMessage());
        }
    }

    public void b() {
        this.a.h();
    }

    public void a(String str) {
        try {
            this.a.c(str);
        } catch (Exception e) {
            this.b.b(this, e.getMessage());
        }
    }
}
