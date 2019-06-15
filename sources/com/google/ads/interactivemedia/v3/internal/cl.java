package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import com.google.ads.interactivemedia.v3.internal.co.a;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;

final class cl extends co {
    private static final int[] b = new int[]{5500, 11000, 22000, 44000};
    private boolean c;
    private boolean d;

    public cl(ck ckVar) {
        super(ckVar);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(fp fpVar) throws a {
        if (this.c) {
            fpVar.d(1);
        } else {
            int f = fpVar.f();
            int i = (f >> 4) & 15;
            f = (f >> 2) & 3;
            StringBuilder stringBuilder;
            if (f < 0 || f >= b.length) {
                stringBuilder = new StringBuilder(38);
                stringBuilder.append("Invalid sample rate index: ");
                stringBuilder.append(f);
                throw new a(stringBuilder.toString());
            } else if (i != 10) {
                stringBuilder = new StringBuilder(39);
                stringBuilder.append("Audio format not supported: ");
                stringBuilder.append(i);
                throw new a(stringBuilder.toString());
            } else {
                this.c = true;
            }
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void a(fp fpVar, long j) {
        int f = fpVar.f();
        if (f == 0 && !this.d) {
            byte[] bArr = new byte[fpVar.b()];
            fpVar.a(bArr, 0, bArr.length);
            Pair a = ff.a(bArr);
            this.a.a(bj.a(null, MimeTypes.AUDIO_AAC, -1, -1, a(), ((Integer) a.second).intValue(), ((Integer) a.first).intValue(), Collections.singletonList(bArr), null));
            this.d = true;
        } else if (f == 1) {
            int b = fpVar.b();
            this.a.a(fpVar, b);
            this.a.a(j, 1, b, 0, null);
        }
    }
}
