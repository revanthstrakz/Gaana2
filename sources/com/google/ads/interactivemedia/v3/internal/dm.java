package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

abstract class dm {
    protected final fp a = new fp(new byte[OggPageHeader.MAX_PAGE_PAYLOAD], 0);
    protected final dj b = new dj();
    protected ck c;
    protected ce d;

    dm() {
    }

    public abstract int a(cd cdVar, ch chVar) throws IOException, InterruptedException;

    /* Access modifiers changed, original: 0000 */
    public void a(ce ceVar, ck ckVar) {
        this.d = ceVar;
        this.c = ckVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        this.b.a();
        this.a.a();
    }
}
