package com.exoplayer2.upstream.cache;

import com.constants.Constants;
import com.exoplayer2.CookieSpan;
import com.exoplayer2.TrackSpan;
import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class n {
    private static r b;
    private final long a;

    class a implements Comparator<CookieSpan> {
        a() {
        }

        /* renamed from: a */
        public int compare(CookieSpan cookieSpan, CookieSpan cookieSpan2) {
            return cookieSpan.c() < cookieSpan2.c() ? -1 : 1;
        }
    }

    public n(long j, TreeSet<TrackSpan> treeSet) {
        this.a = j;
        Constants.eg = new TreeSet(new a());
    }

    public void a(File file) {
        if (b == null) {
            Constants.eh = new HashMap();
            b = new r(file, 200);
            b.a();
        }
    }

    public void a(CookieSpan cookieSpan) {
        if (b != null) {
            b.b(cookieSpan);
            b.b();
        }
    }
}
