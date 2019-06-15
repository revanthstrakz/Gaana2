package com.google.ads.interactivemedia.v3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.InputDeviceCompat;
import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.io.IOException;

public final class eb implements cc {
    private final ec a;
    private final SparseArray<a> b;
    private final fp c;
    private boolean d;
    private boolean e;
    private boolean f;
    private ce g;

    private static final class a {
        private final du a;
        private final ec b;
        private final fo c = new fo(new byte[64]);
        private boolean d;
        private boolean e;
        private boolean f;
        private int g;
        private long h;

        public a(du duVar, ec ecVar) {
            this.a = duVar;
            this.b = ecVar;
        }

        public void a() {
            this.f = false;
            this.a.a();
        }

        public void a(fp fpVar, ce ceVar) {
            fpVar.a(this.c.a, 0, 3);
            this.c.a(0);
            b();
            fpVar.a(this.c.a, 0, this.g);
            this.c.a(0);
            c();
            this.a.a(this.h, true);
            this.a.a(fpVar);
            this.a.b();
        }

        private void b() {
            this.c.b(8);
            this.d = this.c.b();
            this.e = this.c.b();
            this.c.b(6);
            this.g = this.c.c(8);
        }

        private void c() {
            this.h = 0;
            if (this.d) {
                this.c.b(4);
                long c = ((long) this.c.c(3)) << 30;
                this.c.b(1);
                long c2 = c | ((long) (this.c.c(15) << 15));
                this.c.b(1);
                long c3 = c2 | ((long) this.c.c(15));
                this.c.b(1);
                if (!this.f && this.e) {
                    this.c.b(4);
                    long c4 = ((long) this.c.c(3)) << 30;
                    this.c.b(1);
                    long c5 = c4 | ((long) (this.c.c(15) << 15));
                    this.c.b(1);
                    long c6 = c5 | ((long) this.c.c(15));
                    this.c.b(1);
                    this.b.a(c6);
                    this.f = true;
                }
                this.h = this.b.a(c3);
            }
        }
    }

    public eb() {
        this(new ec(0));
    }

    public void c() {
    }

    public eb(ec ecVar) {
        this.a = ecVar;
        this.c = new fp(4096);
        this.b = new SparseArray();
    }

    public boolean a(cd cdVar) throws IOException, InterruptedException {
        byte[] bArr = new byte[14];
        boolean z = false;
        cdVar.c(bArr, 0, 14);
        if (442 != (((((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16)) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255)) || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        cdVar.c(bArr[13] & 7);
        cdVar.c(bArr, 0, 3);
        if (1 == ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8)) | (bArr[2] & 255))) {
            z = true;
        }
        return z;
    }

    public void a(ce ceVar) {
        this.g = ceVar;
        ceVar.a(cj.f);
    }

    public void b() {
        this.a.a();
        for (int i = 0; i < this.b.size(); i++) {
            ((a) this.b.valueAt(i)).a();
        }
    }

    public int a(cd cdVar, ch chVar) throws IOException, InterruptedException {
        if (!cdVar.b(this.c.a, 0, 4, true)) {
            return -1;
        }
        this.c.c(0);
        int m = this.c.m();
        if (m == 441) {
            return -1;
        }
        if (m == 442) {
            cdVar.c(this.c.a, 0, 10);
            this.c.c(0);
            this.c.d(9);
            cdVar.b((this.c.f() & 7) + 14);
            return 0;
        } else if (m == 443) {
            cdVar.c(this.c.a, 0, 2);
            this.c.c(0);
            cdVar.b(this.c.g() + 6);
            return 0;
        } else if (((m & InputDeviceCompat.SOURCE_ANY) >> 8) != 1) {
            cdVar.b(1);
            return 0;
        } else {
            m &= 255;
            a aVar = (a) this.b.get(m);
            if (!this.d) {
                if (aVar == null) {
                    du duVar = null;
                    if (!this.e && m == PsExtractor.PRIVATE_STREAM_1) {
                        duVar = new dq(this.g.d(m), false);
                        this.e = true;
                    } else if (!this.e && (m & 224) == PsExtractor.AUDIO_STREAM) {
                        duVar = new dz(this.g.d(m));
                        this.e = true;
                    } else if (!this.f && (m & PsExtractor.VIDEO_STREAM_MASK) == 224) {
                        duVar = new dv(this.g.d(m));
                        this.f = true;
                    }
                    if (duVar != null) {
                        aVar = new a(duVar, this.a);
                        this.b.put(m, aVar);
                    }
                }
                if ((this.e && this.f) || cdVar.c() > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                    this.d = true;
                    this.g.f();
                }
            }
            cdVar.c(this.c.a, 0, 2);
            this.c.c(0);
            m = this.c.g() + 6;
            if (aVar == null) {
                cdVar.b(m);
            } else {
                if (this.c.e() < m) {
                    this.c.a(new byte[m], m);
                }
                cdVar.b(this.c.a, 0, m);
                this.c.c(6);
                this.c.b(m);
                aVar.a(this.c, this.g);
                this.c.b(this.c.e());
            }
            return 0;
        }
    }
}
