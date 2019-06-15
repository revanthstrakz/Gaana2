package com.facebook.ads.internal.q;

import com.facebook.ads.internal.j.c;

class f {
    private final a a;
    private final c b;
    private final String c;
    private final String d;
    private final String e;

    enum a {
        UNKNOWN,
        ERROR,
        ADS
    }

    f(a aVar) {
        this(aVar, null, null, null, null);
    }

    f(a aVar, c cVar, String str, String str2, String str3) {
        this.a = aVar;
        this.b = cVar;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public c a() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public a b() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public String c() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public String d() {
        return this.d;
    }

    /* Access modifiers changed, original: 0000 */
    public String e() {
        return this.e;
    }
}
