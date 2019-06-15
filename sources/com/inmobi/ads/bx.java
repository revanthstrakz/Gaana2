package com.inmobi.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import com.inmobi.a.n;
import com.inmobi.ads.c.c;
import com.inmobi.ads.c.j;
import com.inmobi.ads.cache.d;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.network.NetworkError;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class bx implements by {
    List<bv> a;
    String b;
    String c;
    List<NativeTracker> d;
    List<bt> e;
    int f;
    private String g;
    private bt h;
    private j i;
    private bv j;

    private static boolean a(double d, double d2, double d3) {
        return d3 > d && d3 <= d2;
    }

    public bx(j jVar) {
        this.j = null;
        this.a = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.i = jVar;
        this.f = 0;
    }

    private bx(List<NativeTracker> list, j jVar) {
        this(jVar);
        if (list.size() != 0) {
            this.d = new ArrayList(list);
        }
    }

    public bx(String str, String str2, String str3, List<NativeTracker> list, List<bt> list2, j jVar) {
        this(list, jVar);
        if (list2.size() != 0) {
            this.e = new ArrayList(list2);
        }
        this.g = str;
        this.a.add(new bv(str));
        this.b = str2;
        this.c = str3;
    }

    public final String a() {
        return this.c;
    }

    public final String b() {
        if (this.g != null) {
            return this.g;
        }
        Iterator it;
        d.a();
        List d = d.d();
        bv bvVar = null;
        if (!d.isEmpty()) {
            for (bv bvVar2 : this.a) {
                if (d.contains(bvVar2.a)) {
                    break;
                }
            }
        }
        bv bvVar22 = null;
        if (bvVar22 != null) {
            this.j = bvVar22;
            this.g = bvVar22.a;
            return this.g;
        }
        double d2;
        bv bvVar3;
        double d3 = (2.0d * ((double) this.i.b)) / 1048576.0d;
        double d4 = (((double) this.i.c) * 1.0d) / 1048576.0d;
        for (bv bvVar32 : this.a) {
            int parseInt;
            String[] split = this.b.split(":");
            try {
                parseInt = (Integer.parseInt(split[1]) * 60) + Integer.parseInt(split[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                parseInt = 0;
                a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            d2 = ((((double) bvVar32.b) * 1.0d) * ((double) parseInt)) / 8192.0d;
            bvVar32.c = d2;
            double d5 = d2;
            if (a(0.0d, d3, d2)) {
                bvVar22 = a(bvVar22, bvVar32, d5);
            } else {
                d2 = d5;
                double d6 = d2;
                if (a(d3, d4, d2)) {
                    bvVar = b(bvVar, bvVar32, d6);
                }
            }
        }
        a(bvVar22, bvVar);
        if (TextUtils.isEmpty(this.g)) {
            c cVar = this.i.d;
            if (cVar.a || this.a.size() == 0) {
                return this.g;
            }
            CountDownLatch countDownLatch = new CountDownLatch(this.a.size());
            try {
                a(cVar, countDownLatch);
                countDownLatch.await((long) cVar.b, TimeUnit.MILLISECONDS);
                for (bv bvVar322 : this.a) {
                    d2 = bvVar322.c;
                    double d7 = d2;
                    if (a(0.0d, d3, d2)) {
                        bvVar22 = a(bvVar22, bvVar322, d7);
                    } else {
                        d2 = d7;
                        double d8 = d2;
                        if (a(d3, d4, d2)) {
                            bvVar = b(bvVar, bvVar322, d8);
                        }
                    }
                }
            } catch (Exception e2) {
                Exception exception = e2;
                new StringBuilder("SDK encountered an unexpected error in getting vast header response; ").append(exception.getMessage());
                a.a().a(new com.inmobi.commons.core.e.a(exception));
                for (bv bvVar3222 : this.a) {
                    d2 = bvVar3222.c;
                    double d9 = d2;
                    if (a(0.0d, d3, d2)) {
                        bvVar22 = a(bvVar22, bvVar3222, d9);
                    } else {
                        d2 = d9;
                        double d10 = d2;
                        if (a(d3, d4, d2)) {
                            bvVar = b(bvVar, bvVar3222, d10);
                        }
                    }
                }
            } catch (Throwable e3) {
                Throwable th = e3;
                it = this.a.iterator();
                while (it.hasNext()) {
                    bv bvVar4 = (bv) it.next();
                    double d11 = bvVar4.c;
                    Iterator it2 = it;
                    bvVar3222 = bvVar4;
                    if (a(0.0d, d3, d11)) {
                        bvVar22 = a(bvVar22, bvVar3222, d11);
                    } else {
                        d2 = d11;
                        double d12 = d2;
                        if (a(d3, d4, d2)) {
                            bvVar = b(bvVar, bvVar3222, d12);
                        }
                    }
                    it = it2;
                }
                a(bvVar22, bvVar);
            }
            a(bvVar22, bvVar);
        }
        return this.g;
    }

    private void a(c cVar, CountDownLatch countDownLatch) {
        for (bv bwVar : this.a) {
            bw bwVar2 = new bw(bwVar, cVar.b, countDownLatch);
            bwVar2.c = SystemClock.elapsedRealtime();
            bw.d.execute(new Runnable() {
                public final void run() {
                    bw bwVar;
                    try {
                        com.inmobi.commons.core.network.d a = new f(bw.this.a).a();
                        if (a == null) {
                            return;
                        }
                        if (a.a()) {
                            bw.this.a(a);
                            return;
                        }
                        bwVar = bw.this;
                        try {
                            n.a().a(bwVar.a.g());
                            n.a().b(a.c());
                            n.a().c(SystemClock.elapsedRealtime() - bwVar.c);
                            if (bwVar.b.get() != null) {
                                ((bv) bwVar.b.get()).c = (double) (a.c / 1048576);
                            }
                            bwVar.a();
                        } catch (Exception e) {
                            new StringBuilder("Handling Vast Media Header Request success encountered an unexpected error: ").append(e.getMessage());
                            a.a().a(new com.inmobi.commons.core.e.a(e));
                            bwVar.a();
                        }
                    } catch (Exception e2) {
                        bw.e;
                        new StringBuilder("Network request failed with unexpected error: ").append(e2.getMessage());
                        NetworkError networkError = new NetworkError(ErrorCode.UNKNOWN_ERROR, "Network request failed with unknown error");
                        com.inmobi.commons.core.network.d dVar = new com.inmobi.commons.core.network.d();
                        dVar.b = networkError;
                        bw.this.a(dVar);
                    } catch (Throwable th) {
                        bwVar.a();
                    }
                }
            });
        }
    }

    private void a(bv bvVar, bv bvVar2) {
        if (bvVar != null) {
            this.j = bvVar;
            this.g = bvVar.a;
            return;
        }
        if (bvVar2 != null) {
            this.j = bvVar2;
            this.g = bvVar2.a;
        }
    }

    public final List<bv> c() {
        return this.a;
    }

    public final List<NativeTracker> d() {
        return this.d;
    }

    public final List<bt> e() {
        return this.e;
    }

    public final void a(bt btVar) {
        this.h = btVar;
    }

    public final bt f() {
        return this.h;
    }

    /* Access modifiers changed, original: final */
    public final void a(NativeTracker nativeTracker) {
        this.d.add(nativeTracker);
    }

    private static bv a(bv bvVar, bv bvVar2, double d) {
        return (bvVar != null && d <= bvVar.c) ? bvVar : bvVar2;
    }

    private static bv b(bv bvVar, bv bvVar2, double d) {
        return (bvVar != null && d >= bvVar.c) ? bvVar : bvVar2;
    }
}
