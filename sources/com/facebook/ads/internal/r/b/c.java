package com.facebook.ads.internal.r.b;

import com.facebook.ads.internal.r.b.a.a;
import java.io.File;

class c {
    public final File a;
    public final com.facebook.ads.internal.r.b.a.c b;
    public final a c;

    c(File file, com.facebook.ads.internal.r.b.a.c cVar, a aVar) {
        this.a = file;
        this.b = cVar;
        this.c = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    public File a(String str) {
        return new File(this.a, this.b.a(str));
    }
}
