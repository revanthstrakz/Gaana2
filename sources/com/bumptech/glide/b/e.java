package com.bumptech.glide.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.bumptech.glide.b.a.a;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class e implements a {
    private static final String a = "e";
    @ColorInt
    private int[] b;
    @ColorInt
    private final int[] c;
    private ByteBuffer d;
    private byte[] e;
    @Nullable
    private byte[] f;
    private int g;
    private int h;
    private short[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    @ColorInt
    private int[] m;
    private int n;
    private c o;
    private a p;
    private Bitmap q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private int v;
    private boolean w;
    @NonNull
    private Config x;

    public e(a aVar, c cVar, ByteBuffer byteBuffer, int i) {
        this(aVar);
        a(cVar, byteBuffer, i);
    }

    public e(a aVar) {
        this.c = new int[256];
        this.g = 0;
        this.h = 0;
        this.x = Config.ARGB_8888;
        this.p = aVar;
        this.o = new c();
    }

    public ByteBuffer a() {
        return this.d;
    }

    public void b() {
        this.n = (this.n + 1) % this.o.c;
    }

    public int a(int i) {
        return (i < 0 || i >= this.o.c) ? -1 : ((b) this.o.e.get(i)).i;
    }

    public int c() {
        return (this.o.c <= 0 || this.n < 0) ? 0 : a(this.n);
    }

    public int d() {
        return this.o.c;
    }

    public int e() {
        return this.n;
    }

    public void f() {
        this.n = -1;
    }

    public int g() {
        return (this.d.limit() + this.l.length) + (this.m.length * 4);
    }

    /* JADX WARNING: Missing block: B:43:0x00db, code skipped:
            return null;
     */
    public synchronized android.graphics.Bitmap h() {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = r7.o;	 Catch:{ all -> 0x00dc }
        r0 = r0.c;	 Catch:{ all -> 0x00dc }
        r1 = 3;
        r2 = 1;
        if (r0 <= 0) goto L_0x000d;
    L_0x0009:
        r0 = r7.n;	 Catch:{ all -> 0x00dc }
        if (r0 >= 0) goto L_0x003b;
    L_0x000d:
        r0 = a;	 Catch:{ all -> 0x00dc }
        r0 = android.util.Log.isLoggable(r0, r1);	 Catch:{ all -> 0x00dc }
        if (r0 == 0) goto L_0x0039;
    L_0x0015:
        r0 = a;	 Catch:{ all -> 0x00dc }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00dc }
        r3.<init>();	 Catch:{ all -> 0x00dc }
        r4 = "Unable to decode frame, frameCount=";
        r3.append(r4);	 Catch:{ all -> 0x00dc }
        r4 = r7.o;	 Catch:{ all -> 0x00dc }
        r4 = r4.c;	 Catch:{ all -> 0x00dc }
        r3.append(r4);	 Catch:{ all -> 0x00dc }
        r4 = ", framePointer=";
        r3.append(r4);	 Catch:{ all -> 0x00dc }
        r4 = r7.n;	 Catch:{ all -> 0x00dc }
        r3.append(r4);	 Catch:{ all -> 0x00dc }
        r3 = r3.toString();	 Catch:{ all -> 0x00dc }
        android.util.Log.d(r0, r3);	 Catch:{ all -> 0x00dc }
    L_0x0039:
        r7.s = r2;	 Catch:{ all -> 0x00dc }
    L_0x003b:
        r0 = r7.s;	 Catch:{ all -> 0x00dc }
        r3 = 0;
        if (r0 == r2) goto L_0x00ba;
    L_0x0040:
        r0 = r7.s;	 Catch:{ all -> 0x00dc }
        r4 = 2;
        if (r0 != r4) goto L_0x0047;
    L_0x0045:
        goto L_0x00ba;
    L_0x0047:
        r0 = 0;
        r7.s = r0;	 Catch:{ all -> 0x00dc }
        r4 = r7.o;	 Catch:{ all -> 0x00dc }
        r4 = r4.e;	 Catch:{ all -> 0x00dc }
        r5 = r7.n;	 Catch:{ all -> 0x00dc }
        r4 = r4.get(r5);	 Catch:{ all -> 0x00dc }
        r4 = (com.bumptech.glide.b.b) r4;	 Catch:{ all -> 0x00dc }
        r5 = r7.n;	 Catch:{ all -> 0x00dc }
        r5 = r5 - r2;
        if (r5 < 0) goto L_0x0066;
    L_0x005b:
        r6 = r7.o;	 Catch:{ all -> 0x00dc }
        r6 = r6.e;	 Catch:{ all -> 0x00dc }
        r5 = r6.get(r5);	 Catch:{ all -> 0x00dc }
        r5 = (com.bumptech.glide.b.b) r5;	 Catch:{ all -> 0x00dc }
        goto L_0x0067;
    L_0x0066:
        r5 = r3;
    L_0x0067:
        r6 = r4.k;	 Catch:{ all -> 0x00dc }
        if (r6 == 0) goto L_0x006e;
    L_0x006b:
        r6 = r4.k;	 Catch:{ all -> 0x00dc }
        goto L_0x0072;
    L_0x006e:
        r6 = r7.o;	 Catch:{ all -> 0x00dc }
        r6 = r6.a;	 Catch:{ all -> 0x00dc }
    L_0x0072:
        r7.b = r6;	 Catch:{ all -> 0x00dc }
        r6 = r7.b;	 Catch:{ all -> 0x00dc }
        if (r6 != 0) goto L_0x009c;
    L_0x0078:
        r0 = a;	 Catch:{ all -> 0x00dc }
        r0 = android.util.Log.isLoggable(r0, r1);	 Catch:{ all -> 0x00dc }
        if (r0 == 0) goto L_0x0098;
    L_0x0080:
        r0 = a;	 Catch:{ all -> 0x00dc }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00dc }
        r1.<init>();	 Catch:{ all -> 0x00dc }
        r4 = "No valid color table found for frame #";
        r1.append(r4);	 Catch:{ all -> 0x00dc }
        r4 = r7.n;	 Catch:{ all -> 0x00dc }
        r1.append(r4);	 Catch:{ all -> 0x00dc }
        r1 = r1.toString();	 Catch:{ all -> 0x00dc }
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x00dc }
    L_0x0098:
        r7.s = r2;	 Catch:{ all -> 0x00dc }
        monitor-exit(r7);
        return r3;
    L_0x009c:
        r1 = r4.f;	 Catch:{ all -> 0x00dc }
        if (r1 == 0) goto L_0x00b4;
    L_0x00a0:
        r1 = r7.b;	 Catch:{ all -> 0x00dc }
        r2 = r7.c;	 Catch:{ all -> 0x00dc }
        r3 = r7.b;	 Catch:{ all -> 0x00dc }
        r3 = r3.length;	 Catch:{ all -> 0x00dc }
        java.lang.System.arraycopy(r1, r0, r2, r0, r3);	 Catch:{ all -> 0x00dc }
        r1 = r7.c;	 Catch:{ all -> 0x00dc }
        r7.b = r1;	 Catch:{ all -> 0x00dc }
        r1 = r7.b;	 Catch:{ all -> 0x00dc }
        r2 = r4.h;	 Catch:{ all -> 0x00dc }
        r1[r2] = r0;	 Catch:{ all -> 0x00dc }
    L_0x00b4:
        r0 = r7.a(r4, r5);	 Catch:{ all -> 0x00dc }
        monitor-exit(r7);
        return r0;
    L_0x00ba:
        r0 = a;	 Catch:{ all -> 0x00dc }
        r0 = android.util.Log.isLoggable(r0, r1);	 Catch:{ all -> 0x00dc }
        if (r0 == 0) goto L_0x00da;
    L_0x00c2:
        r0 = a;	 Catch:{ all -> 0x00dc }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00dc }
        r1.<init>();	 Catch:{ all -> 0x00dc }
        r2 = "Unable to decode frame, status=";
        r1.append(r2);	 Catch:{ all -> 0x00dc }
        r2 = r7.s;	 Catch:{ all -> 0x00dc }
        r1.append(r2);	 Catch:{ all -> 0x00dc }
        r1 = r1.toString();	 Catch:{ all -> 0x00dc }
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x00dc }
    L_0x00da:
        monitor-exit(r7);
        return r3;
    L_0x00dc:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.e.h():android.graphics.Bitmap");
    }

    public void i() {
        this.o = null;
        if (this.l != null) {
            this.p.a(this.l);
        }
        if (this.m != null) {
            this.p.a(this.m);
        }
        if (this.q != null) {
            this.p.a(this.q);
        }
        this.q = null;
        this.d = null;
        this.w = false;
        if (this.e != null) {
            this.p.a(this.e);
        }
        if (this.f != null) {
            this.p.a(this.f);
        }
    }

    public synchronized void a(c cVar, ByteBuffer byteBuffer, int i) {
        if (i <= 0) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Sample size must be >=0, not: ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
            } catch (Throwable th) {
            }
        } else {
            i = Integer.highestOneBit(i);
            this.s = 0;
            this.o = cVar;
            this.w = false;
            this.n = -1;
            this.d = byteBuffer.asReadOnlyBuffer();
            this.d.position(0);
            this.d.order(ByteOrder.LITTLE_ENDIAN);
            this.r = false;
            for (b bVar : cVar.e) {
                if (bVar.g == 3) {
                    this.r = true;
                    break;
                }
            }
            this.t = i;
            this.v = cVar.f / i;
            this.u = cVar.g / i;
            this.l = this.p.a(cVar.f * cVar.g);
            this.m = this.p.b(this.v * this.u);
        }
    }

    public void a(Config config) {
        if (config == Config.ARGB_8888 || config == Config.RGB_565) {
            this.x = config;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unsupported format: ");
        stringBuilder.append(config);
        stringBuilder.append(", must be one of ");
        stringBuilder.append(Config.ARGB_8888);
        stringBuilder.append(" or ");
        stringBuilder.append(Config.RGB_565);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Missing block: B:24:0x0048, code skipped:
            if (r0.o.j == r1.h) goto L_0x0051;
     */
    private android.graphics.Bitmap a(com.bumptech.glide.b.b r19, com.bumptech.glide.b.b r20) {
        /*
        r18 = this;
        r0 = r18;
        r1 = r19;
        r2 = r20;
        r10 = r0.m;
        r11 = 0;
        if (r2 != 0) goto L_0x001c;
    L_0x000b:
        r3 = r0.q;
        if (r3 == 0) goto L_0x0016;
    L_0x000f:
        r3 = r0.p;
        r4 = r0.q;
        r3.a(r4);
    L_0x0016:
        r3 = 0;
        r0.q = r3;
        java.util.Arrays.fill(r10, r11);
    L_0x001c:
        r3 = 3;
        if (r2 == 0) goto L_0x002a;
    L_0x001f:
        r4 = r2.g;
        if (r4 != r3) goto L_0x002a;
    L_0x0023:
        r4 = r0.q;
        if (r4 != 0) goto L_0x002a;
    L_0x0027:
        java.util.Arrays.fill(r10, r11);
    L_0x002a:
        r12 = 2;
        r13 = 1;
        if (r2 == 0) goto L_0x0095;
    L_0x002e:
        r4 = r2.g;
        if (r4 <= 0) goto L_0x0095;
    L_0x0032:
        r4 = r2.g;
        if (r4 != r12) goto L_0x007e;
    L_0x0036:
        r3 = r1.f;
        if (r3 != 0) goto L_0x004b;
    L_0x003a:
        r3 = r0.o;
        r3 = r3.l;
        r4 = r1.k;
        if (r4 == 0) goto L_0x0052;
    L_0x0042:
        r4 = r0.o;
        r4 = r4.j;
        r5 = r1.h;
        if (r4 != r5) goto L_0x0052;
    L_0x004a:
        goto L_0x0051;
    L_0x004b:
        r3 = r0.n;
        if (r3 != 0) goto L_0x0051;
    L_0x004f:
        r0.w = r13;
    L_0x0051:
        r3 = r11;
    L_0x0052:
        r4 = r2.d;
        r5 = r0.t;
        r4 = r4 / r5;
        r5 = r2.b;
        r6 = r0.t;
        r5 = r5 / r6;
        r6 = r2.c;
        r7 = r0.t;
        r6 = r6 / r7;
        r2 = r2.a;
        r7 = r0.t;
        r2 = r2 / r7;
        r7 = r0.v;
        r5 = r5 * r7;
        r5 = r5 + r2;
        r2 = r0.v;
        r4 = r4 * r2;
        r4 = r4 + r5;
    L_0x006e:
        if (r5 >= r4) goto L_0x0095;
    L_0x0070:
        r2 = r5 + r6;
        r7 = r5;
    L_0x0073:
        if (r7 >= r2) goto L_0x007a;
    L_0x0075:
        r10[r7] = r3;
        r7 = r7 + 1;
        goto L_0x0073;
    L_0x007a:
        r2 = r0.v;
        r5 = r5 + r2;
        goto L_0x006e;
    L_0x007e:
        r2 = r2.g;
        if (r2 != r3) goto L_0x0095;
    L_0x0082:
        r2 = r0.q;
        if (r2 == 0) goto L_0x0095;
    L_0x0086:
        r2 = r0.q;
        r4 = 0;
        r5 = r0.v;
        r6 = 0;
        r7 = 0;
        r8 = r0.v;
        r9 = r0.u;
        r3 = r10;
        r2.getPixels(r3, r4, r5, r6, r7, r8, r9);
    L_0x0095:
        r18.a(r19);
        r2 = r1.d;
        r3 = r0.t;
        r2 = r2 / r3;
        r3 = r1.b;
        r4 = r0.t;
        r3 = r3 / r4;
        r4 = r1.c;
        r5 = r0.t;
        r4 = r4 / r5;
        r5 = r1.a;
        r6 = r0.t;
        r5 = r5 / r6;
        r6 = 8;
        r7 = r0.n;
        if (r7 != 0) goto L_0x00b4;
    L_0x00b2:
        r7 = r13;
        goto L_0x00b5;
    L_0x00b4:
        r7 = r11;
    L_0x00b5:
        r9 = r6;
        r6 = r11;
        r8 = r13;
    L_0x00b8:
        if (r11 >= r2) goto L_0x0133;
    L_0x00ba:
        r14 = r1.e;
        if (r14 == 0) goto L_0x00d1;
    L_0x00be:
        r14 = 4;
        if (r6 < r2) goto L_0x00ce;
    L_0x00c1:
        r8 = r8 + 1;
        switch(r8) {
            case 2: goto L_0x00cd;
            case 3: goto L_0x00ca;
            case 4: goto L_0x00c7;
            default: goto L_0x00c6;
        };
    L_0x00c6:
        goto L_0x00ce;
    L_0x00c7:
        r9 = r12;
        r6 = r13;
        goto L_0x00ce;
    L_0x00ca:
        r6 = r12;
        r9 = r14;
        goto L_0x00ce;
    L_0x00cd:
        r6 = r14;
    L_0x00ce:
        r14 = r6 + r9;
        goto L_0x00d3;
    L_0x00d1:
        r14 = r6;
        r6 = r11;
    L_0x00d3:
        r6 = r6 + r3;
        r15 = r0.u;
        if (r6 >= r15) goto L_0x0125;
    L_0x00d8:
        r15 = r0.v;
        r6 = r6 * r15;
        r15 = r6 + r5;
        r12 = r15 + r4;
        r13 = r0.v;
        r13 = r13 + r6;
        if (r13 >= r12) goto L_0x00e7;
    L_0x00e4:
        r12 = r0.v;
        r12 = r12 + r6;
    L_0x00e7:
        r6 = r0.t;
        r6 = r6 * r11;
        r13 = r1.c;
        r6 = r6 * r13;
        r13 = r12 - r15;
        r16 = r2;
        r2 = r0.t;
        r13 = r13 * r2;
        r13 = r13 + r6;
    L_0x00f5:
        if (r15 >= r12) goto L_0x0127;
    L_0x00f7:
        r2 = r0.t;
        r17 = r3;
        r3 = 1;
        if (r2 != r3) goto L_0x0109;
    L_0x00fe:
        r2 = r0.l;
        r2 = r2[r6];
        r2 = r2 & 255;
        r3 = r0.b;
        r2 = r3[r2];
        goto L_0x010f;
    L_0x0109:
        r2 = r1.c;
        r2 = r0.a(r6, r13, r2);
    L_0x010f:
        if (r2 == 0) goto L_0x0114;
    L_0x0111:
        r10[r15] = r2;
        goto L_0x011d;
    L_0x0114:
        r2 = r0.w;
        if (r2 != 0) goto L_0x011d;
    L_0x0118:
        if (r7 == 0) goto L_0x011d;
    L_0x011a:
        r2 = 1;
        r0.w = r2;
    L_0x011d:
        r2 = r0.t;
        r6 = r6 + r2;
        r15 = r15 + 1;
        r3 = r17;
        goto L_0x00f5;
    L_0x0125:
        r16 = r2;
    L_0x0127:
        r17 = r3;
        r11 = r11 + 1;
        r6 = r14;
        r2 = r16;
        r3 = r17;
        r12 = 2;
        r13 = 1;
        goto L_0x00b8;
    L_0x0133:
        r2 = r0.r;
        if (r2 == 0) goto L_0x0159;
    L_0x0137:
        r2 = r1.g;
        if (r2 == 0) goto L_0x0140;
    L_0x013b:
        r1 = r1.g;
        r2 = 1;
        if (r1 != r2) goto L_0x0159;
    L_0x0140:
        r1 = r0.q;
        if (r1 != 0) goto L_0x014a;
    L_0x0144:
        r1 = r18.m();
        r0.q = r1;
    L_0x014a:
        r1 = r0.q;
        r3 = 0;
        r4 = r0.v;
        r5 = 0;
        r6 = 0;
        r7 = r0.v;
        r8 = r0.u;
        r2 = r10;
        r1.setPixels(r2, r3, r4, r5, r6, r7, r8);
    L_0x0159:
        r9 = r18.m();
        r3 = 0;
        r4 = r0.v;
        r5 = 0;
        r6 = 0;
        r7 = r0.v;
        r8 = r0.u;
        r1 = r9;
        r2 = r10;
        r1.setPixels(r2, r3, r4, r5, r6, r7, r8);
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.b.e.a(com.bumptech.glide.b.b, com.bumptech.glide.b.b):android.graphics.Bitmap");
    }

    @ColorInt
    private int a(int i, int i2, int i3) {
        int i4 = i;
        int i5 = 0;
        int i6 = i5;
        int i7 = i6;
        int i8 = i7;
        int i9 = i8;
        while (i4 < this.t + i && i4 < this.l.length && i4 < i2) {
            int i10 = this.b[this.l[i4] & 255];
            if (i10 != 0) {
                i5 += (i10 >> 24) & 255;
                i6 += (i10 >> 16) & 255;
                i7 += (i10 >> 8) & 255;
                i8 += i10 & 255;
                i9++;
            }
            i4++;
        }
        i += i3;
        i3 = i;
        while (i3 < this.t + i && i3 < this.l.length && i3 < i2) {
            i4 = this.b[this.l[i3] & 255];
            if (i4 != 0) {
                i5 += (i4 >> 24) & 255;
                i6 += (i4 >> 16) & 255;
                i7 += (i4 >> 8) & 255;
                i8 += i4 & 255;
                i9++;
            }
            i3++;
        }
        if (i9 == 0) {
            return 0;
        }
        return ((((i5 / i9) << 24) | ((i6 / i9) << 16)) | ((i7 / i9) << 8)) | (i8 / i9);
    }

    private void a(b bVar) {
        int i;
        int i2;
        b bVar2 = bVar;
        this.g = 0;
        this.h = 0;
        if (bVar2 != null) {
            this.d.position(bVar2.j);
        }
        if (bVar2 == null) {
            i = this.o.f * this.o.g;
        } else {
            i = bVar2.d * bVar2.c;
        }
        if (this.l == null || this.l.length < i) {
            this.l = this.p.a(i);
        }
        if (this.i == null) {
            this.i = new short[4096];
        }
        if (this.j == null) {
            this.j = new byte[4096];
        }
        if (this.k == null) {
            this.k = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        int k = k();
        int i3 = 1;
        int i4 = 1 << k;
        int i5 = i4 + 1;
        int i6 = i4 + 2;
        k++;
        int i7 = (1 << k) - 1;
        for (i2 = 0; i2 < i4; i2++) {
            this.i[i2] = (short) 0;
            this.j[i2] = (byte) i2;
        }
        i2 = -1;
        int i8 = 0;
        int i9 = i8;
        int i10 = i9;
        int i11 = i10;
        int i12 = i11;
        int i13 = i12;
        int i14 = i13;
        int i15 = i14;
        int i16 = k;
        int i17 = i6;
        int i18 = i7;
        int i19 = -1;
        while (i8 < i) {
            int i20 = 3;
            if (i9 == 0) {
                i9 = l();
                if (i9 <= 0) {
                    this.s = 3;
                    break;
                }
                i12 = 0;
            }
            i11 += (this.e[i12] & 255) << i13;
            i12 += i3;
            i9 += i2;
            int i21 = i13 + 8;
            int i22 = i14;
            i3 = i19;
            i13 = i8;
            i14 = i10;
            i10 = i17;
            i8 = i16;
            while (i21 >= i8) {
                i2 = i11 & i18;
                i11 >>= i8;
                i21 -= i8;
                if (i2 != i4) {
                    if (i2 > i10) {
                        this.s = i20;
                    } else if (i2 != i5) {
                        if (i3 == -1) {
                            i17 = i15 + 1;
                            this.k[i15] = this.j[i2];
                            i3 = i2;
                            i22 = i3;
                            i15 = i17;
                        } else {
                            int i23;
                            int i24;
                            if (i2 >= i10) {
                                i17 = i15 + 1;
                                i23 = k;
                                this.k[i15] = (byte) i22;
                                i20 = i3;
                                i15 = i17;
                            } else {
                                i23 = k;
                                i20 = i2;
                            }
                            while (i20 >= i4) {
                                i17 = i15 + 1;
                                i24 = i21;
                                this.k[i15] = this.j[i20];
                                i20 = this.i[i20];
                                i15 = i17;
                                i21 = i24;
                            }
                            i24 = i21;
                            i20 = this.j[i20] & 255;
                            int i25 = i15 + 1;
                            byte b = (byte) i20;
                            this.k[i15] = b;
                            if (i10 < 4096) {
                                this.i[i10] = (short) i3;
                                this.j[i10] = b;
                                i10++;
                                if ((i10 & i18) == 0) {
                                    if (i10 < 4096) {
                                        i8++;
                                        i18 += i10;
                                    }
                                }
                            }
                            i15 = i25;
                            while (i15 > 0) {
                                i3 = i14 + 1;
                                i15--;
                                this.l[i14] = this.k[i15];
                                i13++;
                                i14 = i3;
                            }
                            i22 = i20;
                            i3 = i2;
                            k = i23;
                            i21 = i24;
                        }
                        i20 = 3;
                    }
                    i19 = i3;
                    i16 = i8;
                    i17 = i10;
                    i8 = i13;
                    i10 = i14;
                    i14 = i22;
                    i3 = 1;
                    i2 = -1;
                    i13 = i21;
                    break;
                }
                i8 = k;
                i10 = i6;
                i18 = i7;
                i3 = -1;
                i2 = -1;
            }
            i19 = i3;
            i16 = i8;
            i17 = i10;
            i8 = i13;
            i10 = i14;
            i3 = 1;
            i14 = i22;
            i13 = i21;
            k = k;
        }
        while (i10 < i) {
            this.l[i10] = (byte) 0;
            i10++;
        }
    }

    private void j() {
        if (this.g <= this.h) {
            if (this.f == null) {
                this.f = this.p.a(16384);
            }
            this.h = 0;
            this.g = Math.min(this.d.remaining(), 16384);
            this.d.get(this.f, 0, this.g);
        }
    }

    private int k() {
        try {
            j();
            byte[] bArr = this.f;
            int i = this.h;
            this.h = i + 1;
            return bArr[i] & 255;
        } catch (Exception unused) {
            this.s = 1;
            return 0;
        }
    }

    private int l() {
        int k = k();
        if (k > 0) {
            try {
                if (this.e == null) {
                    this.e = this.p.a(255);
                }
                int i = this.g - this.h;
                if (i >= k) {
                    System.arraycopy(this.f, this.h, this.e, 0, k);
                    this.h += k;
                } else if (this.d.remaining() + i >= k) {
                    System.arraycopy(this.f, this.h, this.e, 0, i);
                    this.h = this.g;
                    j();
                    int i2 = k - i;
                    System.arraycopy(this.f, 0, this.e, i, i2);
                    this.h += i2;
                } else {
                    this.s = 1;
                }
            } catch (Exception e) {
                Log.w(a, "Error Reading Block", e);
                this.s = 1;
            }
        }
        return k;
    }

    private Bitmap m() {
        Bitmap a = this.p.a(this.v, this.u, this.w ? Config.ARGB_8888 : this.x);
        a.setHasAlpha(true);
        return a;
    }
}
