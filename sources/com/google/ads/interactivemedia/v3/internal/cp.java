package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.fn.b;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.List;

final class cp extends co {
    private final fp b = new fp(fn.a);
    private final fp c = new fp(4);
    private int d;
    private boolean e;
    private int f;

    private static final class a {
        public final List<byte[]> a;
        public final int b;
        public final float c;
        public final int d;
        public final int e;

        public a(List<byte[]> list, int i, int i2, int i3, float f) {
            this.a = list;
            this.b = i;
            this.c = f;
            this.d = i2;
            this.e = i3;
        }
    }

    public cp(ck ckVar) {
        super(ckVar);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(fp fpVar) throws com.google.ads.interactivemedia.v3.internal.co.a {
        int f = fpVar.f();
        int i = (f >> 4) & 15;
        f &= 15;
        if (f != 7) {
            StringBuilder stringBuilder = new StringBuilder(39);
            stringBuilder.append("Video format not supported: ");
            stringBuilder.append(f);
            throw new com.google.ads.interactivemedia.v3.internal.co.a(stringBuilder.toString());
        }
        this.f = i;
        return i != 5;
    }

    /* Access modifiers changed, original: protected */
    public void a(fp fpVar, long j) throws bl {
        int f = fpVar.f();
        long j2 = j + (((long) fpVar.j()) * 1000);
        if (f == 0 && !this.e) {
            fp fpVar2 = new fp(new byte[fpVar.b()]);
            fpVar.a(fpVar2.a, 0, fpVar.b());
            a b = b(fpVar2);
            this.d = b.b;
            this.a.a(bj.a(null, MimeTypes.VIDEO_H264, -1, -1, a(), b.d, b.e, b.a, -1, b.c));
            this.e = true;
        } else if (f == 1) {
            byte[] bArr = this.c.a;
            bArr[0] = (byte) 0;
            bArr[1] = (byte) 0;
            bArr[2] = (byte) 0;
            f = 4 - this.d;
            int i = 0;
            while (fpVar.b() > 0) {
                fpVar.a(this.c.a, f, this.d);
                this.c.c(0);
                int s = this.c.s();
                this.b.c(0);
                this.a.a(this.b, 4);
                i += 4;
                this.a.a(fpVar, s);
                i += s;
            }
            this.a.a(j2, this.f == 1 ? 1 : 0, i, 0, null);
        }
    }

    private a b(fp fpVar) throws bl {
        int i;
        float f;
        int i2;
        int i3;
        fpVar.c(4);
        boolean z = true;
        int f2 = (fpVar.f() & 3) + 1;
        if (f2 == 3) {
            z = false;
        }
        fe.b(z);
        ArrayList arrayList = new ArrayList();
        int f3 = fpVar.f() & 31;
        for (i = 0; i < f3; i++) {
            arrayList.add(fn.a(fpVar));
        }
        i = fpVar.f();
        for (int i4 = 0; i4 < i; i4++) {
            arrayList.add(fn.a(fpVar));
        }
        if (f3 > 0) {
            fo foVar = new fo((byte[]) arrayList.get(0));
            foVar.a(8 * (f2 + 1));
            b a = fn.a(foVar);
            int i5 = a.b;
            f3 = a.c;
            f = a.d;
            i2 = i5;
            i3 = f3;
        } else {
            f = 1.0f;
            i2 = -1;
            i3 = i2;
        }
        return new a(arrayList, f2, i2, i3, f);
    }
}
