package com.facebook.ads.internal.adapters.a;

import java.io.Serializable;

public class c implements Serializable {
    private static final long serialVersionUID = 5306126965868117466L;
    private final String a;
    private final String b;
    private final String c;

    public static class a {
        private String a;
        private String b;
        private String c;

        /* Access modifiers changed, original: 0000 */
        public a a(String str) {
            this.a = str;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public c a() {
            return new c(this);
        }

        /* Access modifiers changed, original: 0000 */
        public a b(String str) {
            this.b = str;
            return this;
        }

        /* Access modifiers changed, original: 0000 */
        public a c(String str) {
            this.c = str;
            return this;
        }
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
