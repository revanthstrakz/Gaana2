package com.helpshift.campaigns.models;

import android.os.SystemClock;
import com.facebook.ads.internal.g.e;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.helpshift.campaigns.c.b;
import com.helpshift.util.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class f {
    public final String a;
    public final String b;
    public final String c;
    public final long d;
    public long e;
    public final ArrayList<Long> f;
    public final Integer g;
    private long h;
    private long i;

    public f() {
        long a = x.a();
        this.i = SystemClock.elapsedRealtime();
        d dVar = b.a().a.b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("__hs_session_");
        stringBuilder.append(dVar.a());
        stringBuilder.append("_");
        stringBuilder.append(a);
        this.a = stringBuilder.toString();
        this.b = dVar.a();
        this.c = b.a().d.a().a;
        this.d = a;
        this.e = 0;
        this.h = this.d;
        this.g = com.helpshift.campaigns.m.a.b.a;
        this.f = new ArrayList();
    }

    public f(String str, String str2, String str3, long j, long j2, ArrayList<Long> arrayList, Integer num) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = j;
        this.e = j2;
        this.f = arrayList;
        this.g = num;
        long j3 = this.d;
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            j3 += ((Long) it.next()).longValue();
        }
        this.h = j3;
    }

    public ArrayList<HashMap> a() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put("t", "s");
        hashMap.put("sid", this.a);
        hashMap.put(HlsSegmentFormat.TS, Long.valueOf(this.d));
        arrayList.add(hashMap);
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            Long l = (Long) it.next();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("t", "d");
            hashMap2.put("sid", this.a);
            hashMap2.put("d", l);
            arrayList.add(hashMap2);
        }
        hashMap = new HashMap();
        hashMap.put("t", e.a);
        hashMap.put("sid", this.a);
        hashMap.put(HlsSegmentFormat.TS, Long.valueOf(this.e));
        hashMap.put("d", Long.valueOf(this.e - this.h));
        arrayList.add(hashMap);
        return arrayList;
    }

    public void b() {
        if (this.e == 0) {
            this.e = this.d + (SystemClock.elapsedRealtime() - this.i);
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.a.equals(fVar.a) && this.b.equals(fVar.b) && this.c.equals(fVar.c) && this.d == fVar.d && this.e == fVar.e && this.g.equals(fVar.g) && this.f.equals(fVar.f)) {
            z = true;
        }
        return z;
    }
}
