package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.WindowManager;

@TargetApi(16)
public final class br {
    private final a a;
    private final boolean b;
    private final long c;
    private final long d;
    private long e;
    private long f;
    private long g;
    private boolean h;
    private long i;
    private long j;
    private long k;

    private static final class a implements Callback, FrameCallback {
        private static final a b = new a();
        public volatile long a;
        private final Handler c;
        private final HandlerThread d = new HandlerThread("ChoreographerOwner:Handler");
        private Choreographer e;
        private int f;

        public static a a() {
            return b;
        }

        private a() {
            this.d.start();
            this.c = new Handler(this.d.getLooper(), this);
            this.c.sendEmptyMessage(0);
        }

        public void b() {
            this.c.sendEmptyMessage(1);
        }

        public void c() {
            this.c.sendEmptyMessage(2);
        }

        public void doFrame(long j) {
            this.a = j;
            this.e.postFrameCallbackDelayed(this, 500);
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    d();
                    return true;
                case 1:
                    e();
                    return true;
                case 2:
                    f();
                    return true;
                default:
                    return false;
            }
        }

        private void d() {
            this.e = Choreographer.getInstance();
        }

        private void e() {
            this.f++;
            if (this.f == 1) {
                this.e.postFrameCallback(this);
            }
        }

        private void f() {
            this.f--;
            if (this.f == 0) {
                this.e.removeFrameCallback(this);
                this.a = 0;
            }
        }
    }

    public br() {
        this(-1.0f, false);
    }

    /* Access modifiers changed, original: protected */
    public void c() {
    }

    public br(Context context) {
        this(a(context), true);
    }

    private br(float f, boolean z) {
        this.b = z;
        if (z) {
            this.a = a.a();
            this.c = (long) (1.0E9d / ((double) f));
            this.d = (this.c * 80) / 100;
            return;
        }
        this.a = null;
        this.c = -1;
        this.d = -1;
    }

    public void a() {
        this.h = false;
        if (this.b) {
            this.a.b();
        }
    }

    public void b() {
        if (this.b) {
            this.a.c();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    public long a(long r21, long r23) {
        /*
        r20 = this;
        r0 = r20;
        r1 = r21;
        r3 = r23;
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = r5 * r1;
        r7 = r0.h;
        if (r7 == 0) goto L_0x004d;
    L_0x000d:
        r7 = r0.e;
        r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1));
        if (r9 == 0) goto L_0x001f;
    L_0x0013:
        r7 = r0.k;
        r9 = 1;
        r11 = r7 + r9;
        r0.k = r11;
        r7 = r0.g;
        r0.f = r7;
    L_0x001f:
        r7 = r0.k;
        r9 = 6;
        r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        r7 = 0;
        if (r11 < 0) goto L_0x0045;
    L_0x0028:
        r8 = r0.j;
        r10 = r5 - r8;
        r8 = r0.k;
        r10 = r10 / r8;
        r8 = r0.f;
        r12 = r8 + r10;
        r8 = r0.b(r12, r3);
        if (r8 == 0) goto L_0x003c;
    L_0x0039:
        r0.h = r7;
        goto L_0x004d;
    L_0x003c:
        r7 = r0.i;
        r9 = r7 + r12;
        r7 = r0.j;
        r14 = r9 - r7;
        goto L_0x004f;
    L_0x0045:
        r8 = r0.b(r5, r3);
        if (r8 == 0) goto L_0x004d;
    L_0x004b:
        r0.h = r7;
    L_0x004d:
        r14 = r3;
        r12 = r5;
    L_0x004f:
        r7 = r0.h;
        r8 = 0;
        if (r7 != 0) goto L_0x0061;
    L_0x0055:
        r0.j = r5;
        r0.i = r3;
        r0.k = r8;
        r3 = 1;
        r0.h = r3;
        r20.c();
    L_0x0061:
        r0.e = r1;
        r0.g = r12;
        r1 = r0.a;
        if (r1 == 0) goto L_0x0085;
    L_0x0069:
        r1 = r0.a;
        r1 = r1.a;
        r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1));
        if (r3 != 0) goto L_0x0072;
    L_0x0071:
        goto L_0x0085;
    L_0x0072:
        r1 = r0.a;
        r1 = r1.a;
        r3 = r0.c;
        r16 = r1;
        r18 = r3;
        r1 = a(r14, r16, r18);
        r3 = r0.d;
        r5 = r1 - r3;
        return r5;
    L_0x0085:
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.br.a(long, long):long");
    }

    private boolean b(long j, long j2) {
        return Math.abs((j2 - this.i) - (j - this.j)) > 20000000;
    }

    private static long a(long j, long j2, long j3) {
        long j4 = j2 + (((j - j2) / j3) * j3);
        if (j <= j4) {
            j2 = j4;
            j4 -= j3;
        } else {
            j2 = j4 + j3;
        }
        return j2 - j < j - j4 ? j2 : j4;
    }

    private static float a(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }
}
