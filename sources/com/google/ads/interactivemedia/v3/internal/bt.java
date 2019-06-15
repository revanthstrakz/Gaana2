package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.media.PlaybackParams;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public final class bt {
    public static boolean a = false;
    public static boolean b = false;
    private int A;
    private int B;
    private long C;
    private long D;
    private long E;
    private float F;
    private byte[] G;
    private int H;
    private int I;
    private ByteBuffer J;
    private boolean K;
    private final bs c;
    private final ConditionVariable d;
    private final long[] e;
    private final a f;
    private AudioTrack g;
    private AudioTrack h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private int o;
    private int p;
    private long q;
    private int r;
    private int s;
    private long t;
    private long u;
    private boolean v;
    private long w;
    private Method x;
    private long y;
    private long z;

    private static class a {
        protected AudioTrack a;
        private boolean b;
        private int c;
        private long d;
        private long e;
        private long f;
        private long g;
        private long h;
        private long i;
        private long j;

        private a() {
        }

        public boolean d() {
            return false;
        }

        public float g() {
            return 1.0f;
        }

        public void a(AudioTrack audioTrack, boolean z) {
            this.a = audioTrack;
            this.b = z;
            this.g = -1;
            this.h = -1;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            if (audioTrack != null) {
                this.c = audioTrack.getSampleRate();
            }
        }

        public void a(long j) {
            this.i = b();
            this.g = SystemClock.elapsedRealtime() * 1000;
            this.j = j;
            this.a.stop();
        }

        public void a() {
            if (this.g == -1) {
                this.a.pause();
            }
        }

        public boolean b(long j) {
            return this.h != -1 && j > 0 && SystemClock.elapsedRealtime() - this.h >= 200;
        }

        public long b() {
            if (this.g != -1) {
                return Math.min(this.j, this.i + ((((SystemClock.elapsedRealtime() * 1000) - this.g) * ((long) this.c)) / 1000000));
            }
            int playState = this.a.getPlayState();
            if (playState == 1) {
                return 0;
            }
            long j;
            long playbackHeadPosition = 4294967295L & ((long) this.a.getPlaybackHeadPosition());
            if (this.b) {
                if (playState == 2 && playbackHeadPosition == 0) {
                    this.f = this.d;
                }
                j = playbackHeadPosition + this.f;
            } else {
                j = playbackHeadPosition;
            }
            if (ft.a <= 26) {
                if (j == 0 && this.d > 0 && playState == 3) {
                    if (this.h == -1) {
                        this.h = SystemClock.elapsedRealtime();
                    }
                    return this.d;
                }
                this.h = -1;
            }
            if (this.d > j) {
                this.e++;
            }
            this.d = j;
            return j + (this.e << 32);
        }

        public long c() {
            return (b() * 1000000) / ((long) this.c);
        }

        public long e() {
            throw new UnsupportedOperationException();
        }

        public long f() {
            throw new UnsupportedOperationException();
        }

        public void a(PlaybackParams playbackParams) {
            throw new UnsupportedOperationException();
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static final class d extends Exception {
        public final int a;

        public d(int i, int i2, int i3, int i4) {
            StringBuilder stringBuilder = new StringBuilder(82);
            stringBuilder.append("AudioTrack init failed: ");
            stringBuilder.append(i);
            stringBuilder.append(", Config(");
            stringBuilder.append(i2);
            stringBuilder.append(", ");
            stringBuilder.append(i3);
            stringBuilder.append(", ");
            stringBuilder.append(i4);
            stringBuilder.append(")");
            super(stringBuilder.toString());
            this.a = i;
        }
    }

    public static final class e extends RuntimeException {
        public e(String str) {
            super(str);
        }
    }

    public static final class f extends Exception {
        public final int a;

        public f(int i) {
            StringBuilder stringBuilder = new StringBuilder(36);
            stringBuilder.append("AudioTrack write failed: ");
            stringBuilder.append(i);
            super(stringBuilder.toString());
            this.a = i;
        }
    }

    @TargetApi(19)
    private static class b extends a {
        private final AudioTimestamp b = new AudioTimestamp();
        private long c;
        private long d;
        private long e;

        public b() {
            super();
        }

        public void a(AudioTrack audioTrack, boolean z) {
            super.a(audioTrack, z);
            this.c = 0;
            this.d = 0;
            this.e = 0;
        }

        public boolean d() {
            boolean timestamp = this.a.getTimestamp(this.b);
            if (timestamp) {
                long j = this.b.framePosition;
                if (this.d > j) {
                    this.c++;
                }
                this.d = j;
                this.e = j + (this.c << 32);
            }
            return timestamp;
        }

        public long e() {
            return this.b.nanoTime;
        }

        public long f() {
            return this.e;
        }
    }

    @TargetApi(23)
    private static class c extends b {
        private PlaybackParams b;
        private float c = 1.0f;

        public void a(AudioTrack audioTrack, boolean z) {
            super.a(audioTrack, z);
            h();
        }

        public void a(PlaybackParams playbackParams) {
            if (playbackParams == null) {
                playbackParams = new PlaybackParams();
            }
            playbackParams = playbackParams.allowDefaults();
            this.b = playbackParams;
            this.c = playbackParams.getSpeed();
            h();
        }

        public float g() {
            return this.c;
        }

        private void h() {
            if (this.a != null && this.b != null) {
                this.a.setPlaybackParams(this.b);
            }
        }
    }

    public bt() {
        this(null, 3);
    }

    public bt(bs bsVar, int i) {
        this.c = bsVar;
        this.d = new ConditionVariable(true);
        if (ft.a >= 18) {
            try {
                this.x = AudioTrack.class.getMethod("getLatency", (Class[]) null);
            } catch (NoSuchMethodException unused) {
            }
        }
        if (ft.a >= 23) {
            this.f = new c();
        } else if (ft.a >= 19) {
            this.f = new b();
        } else {
            this.f = new a();
        }
        this.e = new long[10];
        this.k = i;
        this.F = 1.0f;
        this.B = 0;
    }

    public boolean a(String str) {
        return this.c != null && this.c.a(b(str));
    }

    public boolean a() {
        return this.h != null;
    }

    public long a(boolean z) {
        if (!n()) {
            return Long.MIN_VALUE;
        }
        long b;
        if (this.h.getPlayState() == 3) {
            o();
        }
        long nanoTime = System.nanoTime() / 1000;
        if (this.v) {
            b = b(this.f.f() + c((long) (((float) (nanoTime - (this.f.e() / 1000))) * this.f.g()))) + this.C;
        } else {
            if (this.s == 0) {
                b = this.f.c() + this.C;
            } else {
                b = (nanoTime + this.t) + this.C;
            }
            if (!z) {
                b -= this.E;
            }
        }
        return b;
    }

    public void a(String str, int i, int i2, int i3) {
        a(str, i, i2, i3, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0082 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0123  */
    public void a(java.lang.String r9, int r10, int r11, int r12, int r13) {
        /*
        r8 = this;
        r0 = 12;
        r1 = 252; // 0xfc float:3.53E-43 double:1.245E-321;
        switch(r10) {
            case 1: goto L_0x0033;
            case 2: goto L_0x0031;
            case 3: goto L_0x002e;
            case 4: goto L_0x002b;
            case 5: goto L_0x0028;
            case 6: goto L_0x0026;
            case 7: goto L_0x0023;
            case 8: goto L_0x0020;
            default: goto L_0x0007;
        };
    L_0x0007:
        r9 = new java.lang.IllegalArgumentException;
        r11 = 38;
        r12 = new java.lang.StringBuilder;
        r12.<init>(r11);
        r11 = "Unsupported channel count: ";
        r12.append(r11);
        r12.append(r10);
        r10 = r12.toString();
        r9.<init>(r10);
        throw r9;
    L_0x0020:
        r2 = com.google.ads.interactivemedia.v3.internal.au.a;
        goto L_0x0034;
    L_0x0023:
        r2 = 1276; // 0x4fc float:1.788E-42 double:6.304E-321;
        goto L_0x0034;
    L_0x0026:
        r2 = r1;
        goto L_0x0034;
    L_0x0028:
        r2 = 220; // 0xdc float:3.08E-43 double:1.087E-321;
        goto L_0x0034;
    L_0x002b:
        r2 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        goto L_0x0034;
    L_0x002e:
        r2 = 28;
        goto L_0x0034;
    L_0x0031:
        r2 = r0;
        goto L_0x0034;
    L_0x0033:
        r2 = 4;
    L_0x0034:
        r3 = com.google.ads.interactivemedia.v3.internal.ft.a;
        r4 = 23;
        r5 = 5;
        r6 = 3;
        if (r3 > r4) goto L_0x005b;
    L_0x003c:
        r3 = "foster";
        r4 = com.google.ads.interactivemedia.v3.internal.ft.b;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005b;
    L_0x0046:
        r3 = "NVIDIA";
        r4 = com.google.ads.interactivemedia.v3.internal.ft.c;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005b;
    L_0x0050:
        if (r10 == r6) goto L_0x005c;
    L_0x0052:
        if (r10 == r5) goto L_0x005c;
    L_0x0054:
        r1 = 7;
        if (r10 == r1) goto L_0x0058;
    L_0x0057:
        goto L_0x005b;
    L_0x0058:
        r1 = com.google.ads.interactivemedia.v3.internal.au.a;
        goto L_0x005c;
    L_0x005b:
        r1 = r2;
    L_0x005c:
        r2 = "audio/raw";
        r2 = r2.equals(r9);
        r3 = 1;
        r2 = r2 ^ r3;
        r4 = com.google.ads.interactivemedia.v3.internal.ft.a;
        r7 = 25;
        if (r4 > r7) goto L_0x0079;
    L_0x006a:
        r4 = "fugu";
        r7 = com.google.ads.interactivemedia.v3.internal.ft.b;
        r4 = r4.equals(r7);
        if (r4 == 0) goto L_0x0079;
    L_0x0074:
        if (r2 == 0) goto L_0x0079;
    L_0x0076:
        if (r10 != r3) goto L_0x0079;
    L_0x0078:
        goto L_0x007a;
    L_0x0079:
        r0 = r1;
    L_0x007a:
        r1 = 2;
        if (r2 == 0) goto L_0x0082;
    L_0x007d:
        r12 = b(r9);
        goto L_0x00a8;
    L_0x0082:
        if (r12 == r6) goto L_0x00a8;
    L_0x0084:
        if (r12 == r1) goto L_0x00a8;
    L_0x0086:
        r9 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r12 == r9) goto L_0x00a8;
    L_0x008a:
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 != r9) goto L_0x008f;
    L_0x008e:
        goto L_0x00a8;
    L_0x008f:
        r9 = new java.lang.IllegalArgumentException;
        r10 = 37;
        r11 = new java.lang.StringBuilder;
        r11.<init>(r10);
        r10 = "Unsupported PCM encoding: ";
        r11.append(r10);
        r11.append(r12);
        r10 = r11.toString();
        r9.<init>(r10);
        throw r9;
    L_0x00a8:
        r9 = r8.a();
        if (r9 == 0) goto L_0x00bb;
    L_0x00ae:
        r9 = r8.l;
        if (r9 != r12) goto L_0x00bb;
    L_0x00b2:
        r9 = r8.i;
        if (r9 != r11) goto L_0x00bb;
    L_0x00b6:
        r9 = r8.j;
        if (r9 != r0) goto L_0x00bb;
    L_0x00ba:
        return;
    L_0x00bb:
        r8.j();
        r8.l = r12;
        r8.n = r2;
        r8.i = r11;
        r8.j = r0;
        if (r2 == 0) goto L_0x00c9;
    L_0x00c8:
        goto L_0x00ca;
    L_0x00c9:
        r12 = r1;
    L_0x00ca:
        r8.m = r12;
        r1 = r1 * r10;
        r8.o = r1;
        if (r13 == 0) goto L_0x00d4;
    L_0x00d1:
        r8.p = r13;
        goto L_0x0121;
    L_0x00d4:
        if (r2 == 0) goto L_0x00eb;
    L_0x00d6:
        r9 = r8.m;
        if (r9 == r5) goto L_0x00e6;
    L_0x00da:
        r9 = r8.m;
        r10 = 6;
        if (r9 != r10) goto L_0x00e0;
    L_0x00df:
        goto L_0x00e6;
    L_0x00e0:
        r9 = 49152; // 0xc000 float:6.8877E-41 double:2.42843E-319;
        r8.p = r9;
        goto L_0x0121;
    L_0x00e6:
        r9 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        r8.p = r9;
        goto L_0x0121;
    L_0x00eb:
        r9 = r8.m;
        r9 = android.media.AudioTrack.getMinBufferSize(r11, r0, r9);
        r10 = -2;
        if (r9 == r10) goto L_0x00f5;
    L_0x00f4:
        goto L_0x00f6;
    L_0x00f5:
        r3 = 0;
    L_0x00f6:
        com.google.ads.interactivemedia.v3.internal.fe.b(r3);
        r10 = r9 * 4;
        r11 = 250000; // 0x3d090 float:3.50325E-40 double:1.235164E-318;
        r11 = r8.c(r11);
        r11 = (int) r11;
        r12 = r8.o;
        r11 = r11 * r12;
        r12 = (long) r9;
        r0 = 750000; // 0xb71b0 float:1.050974E-39 double:3.70549E-318;
        r0 = r8.c(r0);
        r9 = r8.o;
        r3 = (long) r9;
        r0 = r0 * r3;
        r12 = java.lang.Math.max(r12, r0);
        r9 = (int) r12;
        if (r10 >= r11) goto L_0x011b;
    L_0x0119:
        r9 = r11;
        goto L_0x011f;
    L_0x011b:
        if (r10 <= r9) goto L_0x011e;
    L_0x011d:
        goto L_0x011f;
    L_0x011e:
        r9 = r10;
    L_0x011f:
        r8.p = r9;
    L_0x0121:
        if (r2 == 0) goto L_0x0126;
    L_0x0123:
        r9 = -1;
        goto L_0x0131;
    L_0x0126:
        r9 = r8.p;
        r9 = (long) r9;
        r9 = r8.a(r9);
        r9 = r8.b(r9);
    L_0x0131:
        r8.q = r9;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.bt.a(java.lang.String, int, int, int, int):void");
    }

    public int b() throws d {
        return a(0);
    }

    public int a(int i) throws d {
        this.d.block();
        if (i == 0) {
            this.h = new AudioTrack(this.k, this.i, this.j, this.m, this.p, 1);
        } else {
            this.h = new AudioTrack(this.k, this.i, this.j, this.m, this.p, 1, i);
        }
        p();
        int audioSessionId = this.h.getAudioSessionId();
        if (a && ft.a < 21) {
            if (!(this.g == null || audioSessionId == this.g.getAudioSessionId())) {
                m();
            }
            if (this.g == null) {
                this.g = new AudioTrack(this.k, SsoErrorCodes.SDK_NOT_INITIALIZED, 4, 2, 2, 0, audioSessionId);
            }
        }
        this.f.a(this.h, s());
        l();
        return audioSessionId;
    }

    public int c() {
        return this.p;
    }

    public long d() {
        return this.q;
    }

    public void e() {
        if (a()) {
            this.D = System.nanoTime() / 1000;
            this.h.play();
        }
    }

    public void f() {
        if (this.B == 1) {
            this.B = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ec  */
    public int a(java.nio.ByteBuffer r17, int r18, int r19, long r20) throws com.google.ads.interactivemedia.v3.internal.bt.f {
        /*
        r16 = this;
        r0 = r16;
        r1 = r19;
        r2 = r20;
        r4 = r16.s();
        r5 = 1;
        r6 = 2;
        r7 = 0;
        if (r4 == 0) goto L_0x0029;
    L_0x000f:
        r4 = r0.h;
        r4 = r4.getPlayState();
        if (r4 != r6) goto L_0x0018;
    L_0x0017:
        return r7;
    L_0x0018:
        r4 = r0.h;
        r4 = r4.getPlayState();
        if (r4 != r5) goto L_0x0029;
    L_0x0020:
        r4 = r0.h;
        r4 = r4.getPlaybackHeadPosition();
        if (r4 == 0) goto L_0x0029;
    L_0x0028:
        return r7;
    L_0x0029:
        r4 = r0.I;
        if (r4 != 0) goto L_0x0104;
    L_0x002d:
        if (r1 != 0) goto L_0x0030;
    L_0x002f:
        return r6;
    L_0x0030:
        r4 = r0.m;
        r9 = r0.l;
        if (r4 == r9) goto L_0x0038;
    L_0x0036:
        r4 = r5;
        goto L_0x0039;
    L_0x0038:
        r4 = r7;
    L_0x0039:
        r0.K = r4;
        r4 = r0.K;
        if (r4 == 0) goto L_0x0068;
    L_0x003f:
        r4 = r0.m;
        if (r4 != r6) goto L_0x0045;
    L_0x0043:
        r4 = r5;
        goto L_0x0046;
    L_0x0045:
        r4 = r7;
    L_0x0046:
        com.google.ads.interactivemedia.v3.internal.fe.b(r4);
        r4 = r0.l;
        r9 = r0.J;
        r10 = r17;
        r11 = r18;
        r1 = a(r10, r11, r1, r4, r9);
        r0.J = r1;
        r1 = r0.J;
        r4 = r0.J;
        r4 = r4.position();
        r9 = r0.J;
        r9 = r9.limit();
        r10 = r1;
        r11 = r4;
        goto L_0x006d;
    L_0x0068:
        r10 = r17;
        r11 = r18;
        r9 = r1;
    L_0x006d:
        r0.I = r9;
        r10.position(r11);
        r1 = r0.n;
        if (r1 == 0) goto L_0x0082;
    L_0x0076:
        r1 = r0.A;
        if (r1 != 0) goto L_0x0082;
    L_0x007a:
        r1 = r0.m;
        r1 = a(r1, r10);
        r0.A = r1;
    L_0x0082:
        r1 = r0.B;
        if (r1 != 0) goto L_0x0091;
    L_0x0086:
        r11 = 0;
        r1 = java.lang.Math.max(r11, r2);
        r0.C = r1;
        r0.B = r5;
        goto L_0x00e5;
    L_0x0091:
        r11 = r0.C;
        r13 = r16.q();
        r13 = r0.b(r13);
        r7 = r11 + r13;
        r1 = r0.B;
        if (r1 != r5) goto L_0x00d5;
    L_0x00a1:
        r11 = r7 - r2;
        r11 = java.lang.Math.abs(r11);
        r13 = 200000; // 0x30d40 float:2.8026E-40 double:9.8813E-319;
        r1 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1));
        if (r1 <= 0) goto L_0x00d5;
    L_0x00ae:
        r1 = "AudioTrack";
        r4 = 80;
        r11 = new java.lang.StringBuilder;
        r11.<init>(r4);
        r4 = "Discontinuity detected [expected ";
        r11.append(r4);
        r11.append(r7);
        r4 = ", got ";
        r11.append(r4);
        r11.append(r2);
        r4 = "]";
        r11.append(r4);
        r4 = r11.toString();
        android.util.Log.e(r1, r4);
        r0.B = r6;
    L_0x00d5:
        r1 = r0.B;
        if (r1 != r6) goto L_0x00e5;
    L_0x00d9:
        r11 = r0.C;
        r13 = r2 - r7;
        r1 = r11 + r13;
        r0.C = r1;
        r0.B = r5;
        r7 = r5;
        goto L_0x00e6;
    L_0x00e5:
        r7 = 0;
    L_0x00e6:
        r1 = com.google.ads.interactivemedia.v3.internal.ft.a;
        r2 = 21;
        if (r1 >= r2) goto L_0x0102;
    L_0x00ec:
        r1 = r0.G;
        if (r1 == 0) goto L_0x00f5;
    L_0x00f0:
        r1 = r0.G;
        r1 = r1.length;
        if (r1 >= r9) goto L_0x00f9;
    L_0x00f5:
        r1 = new byte[r9];
        r0.G = r1;
    L_0x00f9:
        r1 = r0.G;
        r2 = 0;
        r10.get(r1, r2, r9);
        r0.H = r2;
        goto L_0x0107;
    L_0x0102:
        r2 = 0;
        goto L_0x0107;
    L_0x0104:
        r10 = r17;
        r2 = r7;
    L_0x0107:
        r1 = com.google.ads.interactivemedia.v3.internal.ft.a;
        r3 = 21;
        if (r1 >= r3) goto L_0x013b;
    L_0x010d:
        r3 = r0.y;
        r1 = r0.f;
        r5 = r1.b();
        r1 = r0.o;
        r8 = (long) r1;
        r5 = r5 * r8;
        r8 = r3 - r5;
        r1 = (int) r8;
        r3 = r0.p;
        r3 = r3 - r1;
        if (r3 <= 0) goto L_0x0139;
    L_0x0121:
        r1 = r0.I;
        r1 = java.lang.Math.min(r1, r3);
        r2 = r0.h;
        r3 = r0.G;
        r4 = r0.H;
        r1 = r2.write(r3, r4, r1);
        if (r1 < 0) goto L_0x0149;
    L_0x0133:
        r2 = r0.H;
        r2 = r2 + r1;
        r0.H = r2;
        goto L_0x0149;
    L_0x0139:
        r1 = r2;
        goto L_0x0149;
    L_0x013b:
        r1 = r0.K;
        if (r1 == 0) goto L_0x0141;
    L_0x013f:
        r10 = r0.J;
    L_0x0141:
        r1 = r0.h;
        r2 = r0.I;
        r1 = a(r1, r10, r2);
    L_0x0149:
        if (r1 >= 0) goto L_0x0151;
    L_0x014b:
        r2 = new com.google.ads.interactivemedia.v3.internal.bt$f;
        r2.<init>(r1);
        throw r2;
    L_0x0151:
        r2 = r0.I;
        r2 = r2 - r1;
        r0.I = r2;
        r2 = r0.n;
        if (r2 != 0) goto L_0x0161;
    L_0x015a:
        r2 = r0.y;
        r4 = (long) r1;
        r8 = r2 + r4;
        r0.y = r8;
    L_0x0161:
        r1 = r0.I;
        if (r1 != 0) goto L_0x0174;
    L_0x0165:
        r1 = r0.n;
        if (r1 == 0) goto L_0x0172;
    L_0x0169:
        r1 = r0.z;
        r3 = r0.A;
        r3 = (long) r3;
        r5 = r1 + r3;
        r0.z = r5;
    L_0x0172:
        r7 = r7 | 2;
    L_0x0174:
        r1 = r0.f;
        r2 = r16.q();
        r1 = r1.b(r2);
        if (r1 == 0) goto L_0x018c;
    L_0x0180:
        r1 = "AudioTrack";
        r2 = "Resetting stalled audio track";
        android.util.Log.w(r1, r2);
        r16.j();
        r7 = r7 | 2;
    L_0x018c:
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.bt.a(java.nio.ByteBuffer, int, int, long):int");
    }

    public void g() {
        if (a()) {
            this.f.a(q());
        }
    }

    public boolean h() {
        return a() && (q() > this.f.b() || t());
    }

    public void a(PlaybackParams playbackParams) {
        this.f.a(playbackParams);
    }

    public boolean b(int i) {
        if (this.k == i) {
            return false;
        }
        this.k = i;
        j();
        return true;
    }

    public void a(float f) {
        if (this.F != f) {
            this.F = f;
            l();
        }
    }

    private void l() {
        if (!a()) {
            return;
        }
        if (ft.a >= 21) {
            a(this.h, this.F);
        } else {
            b(this.h, this.F);
        }
    }

    public void i() {
        if (a()) {
            r();
            this.f.a();
        }
    }

    public void j() {
        if (a()) {
            this.y = 0;
            this.z = 0;
            this.A = 0;
            this.I = 0;
            this.B = 0;
            this.E = 0;
            r();
            if (this.h.getPlayState() == 3) {
                this.h.pause();
            }
            final AudioTrack audioTrack = this.h;
            this.h = null;
            this.f.a(null, false);
            this.d.close();
            new Thread() {
                public void run() {
                    try {
                        audioTrack.flush();
                        audioTrack.release();
                    } finally {
                        bt.this.d.open();
                    }
                }
            }.start();
        }
    }

    public void k() {
        j();
        m();
    }

    private void m() {
        if (this.g != null) {
            final AudioTrack audioTrack = this.g;
            this.g = null;
            new Thread(this) {
                public void run() {
                    audioTrack.release();
                }
            }.start();
        }
    }

    private boolean n() {
        return a() && this.B != 0;
    }

    private void o() {
        long c = this.f.c();
        if (c != 0) {
            long nanoTime = System.nanoTime() / 1000;
            if (nanoTime - this.u >= 30000) {
                this.e[this.r] = c - nanoTime;
                this.r = (this.r + 1) % 10;
                if (this.s < 10) {
                    this.s++;
                }
                this.u = nanoTime;
                this.t = 0;
                for (int i = 0; i < this.s; i++) {
                    this.t += this.e[i] / ((long) this.s);
                }
            }
            if (!s() && nanoTime - this.w >= 500000) {
                long e;
                StringBuilder stringBuilder;
                this.v = this.f.d();
                if (this.v) {
                    e = this.f.e() / 1000;
                    long f = this.f.f();
                    String stringBuilder2;
                    if (e < this.D) {
                        this.v = false;
                    } else if (Math.abs(e - nanoTime) > 5000000) {
                        StringBuilder stringBuilder3 = new StringBuilder(136);
                        stringBuilder3.append("Spurious audio timestamp (system clock mismatch): ");
                        stringBuilder3.append(f);
                        stringBuilder3.append(", ");
                        stringBuilder3.append(e);
                        stringBuilder3.append(", ");
                        stringBuilder3.append(nanoTime);
                        stringBuilder3.append(", ");
                        stringBuilder3.append(c);
                        stringBuilder2 = stringBuilder3.toString();
                        if (b) {
                            throw new e(stringBuilder2);
                        }
                        Log.w("AudioTrack", stringBuilder2);
                        this.v = false;
                    } else if (Math.abs(b(f) - c) > 5000000) {
                        stringBuilder = new StringBuilder(TsExtractor.TS_STREAM_TYPE_DTS);
                        stringBuilder.append("Spurious audio timestamp (frame position mismatch): ");
                        stringBuilder.append(f);
                        stringBuilder.append(", ");
                        stringBuilder.append(e);
                        stringBuilder.append(", ");
                        stringBuilder.append(nanoTime);
                        stringBuilder.append(", ");
                        stringBuilder.append(c);
                        stringBuilder2 = stringBuilder.toString();
                        if (b) {
                            throw new e(stringBuilder2);
                        }
                        Log.w("AudioTrack", stringBuilder2);
                        this.v = false;
                    }
                }
                if (!(this.x == null || this.n)) {
                    try {
                        this.E = (((long) ((Integer) this.x.invoke(this.h, (Object[]) null)).intValue()) * 1000) - this.q;
                        this.E = Math.max(this.E, 0);
                        if (this.E > 5000000) {
                            e = this.E;
                            stringBuilder = new StringBuilder(61);
                            stringBuilder.append("Ignoring impossibly large audio latency: ");
                            stringBuilder.append(e);
                            Log.w("AudioTrack", stringBuilder.toString());
                            this.E = 0;
                        }
                    } catch (Exception unused) {
                        this.x = null;
                    }
                }
                this.w = nanoTime;
            }
        }
    }

    private void p() throws d {
        int state = this.h.getState();
        if (state != 1) {
            try {
                this.h.release();
            } catch (Exception unused) {
            } finally {
                this.h = null;
            }
            throw new d(state, this.i, this.j, this.p);
        }
    }

    private long a(long j) {
        return j / ((long) this.o);
    }

    private long b(long j) {
        return (j * 1000000) / ((long) this.i);
    }

    private long c(long j) {
        return (j * ((long) this.i)) / 1000000;
    }

    private long q() {
        return this.n ? this.z : a(this.y);
    }

    private void r() {
        this.t = 0;
        this.s = 0;
        this.r = 0;
        this.u = 0;
        this.v = false;
        this.w = 0;
    }

    private boolean s() {
        return ft.a < 23 && (this.m == 5 || this.m == 6);
    }

    private boolean t() {
        return s() && this.h.getPlayState() == 2 && this.h.getPlaybackHeadPosition() == 0;
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2) {
        int i4;
        if (i3 == Integer.MIN_VALUE) {
            i4 = (i2 / 3) * 2;
        } else if (i3 == 3) {
            i4 = i2 * 2;
        } else if (i3 != 1073741824) {
            throw new IllegalStateException();
        } else {
            i4 = i2 / 2;
        }
        if (byteBuffer2 == null || byteBuffer2.capacity() < i4) {
            byteBuffer2 = ByteBuffer.allocateDirect(i4);
        }
        byteBuffer2.position(0);
        byteBuffer2.limit(i4);
        i2 += i;
        if (i3 == Integer.MIN_VALUE) {
            while (i < i2) {
                byteBuffer2.put(byteBuffer.get(i + 1));
                byteBuffer2.put(byteBuffer.get(i + 2));
                i += 3;
            }
        } else if (i3 == 3) {
            while (i < i2) {
                byteBuffer2.put((byte) 0);
                byteBuffer2.put((byte) ((byteBuffer.get(i) & 255) - 128));
                i++;
            }
        } else if (i3 != 1073741824) {
            throw new IllegalStateException();
        } else {
            while (i < i2) {
                byteBuffer2.put(byteBuffer.get(i + 2));
                byteBuffer2.put(byteBuffer.get(i + 3));
                i += 4;
            }
        }
        byteBuffer2.position(0);
        return byteBuffer2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    private static int b(java.lang.String r3) {
        /*
        r0 = r3.hashCode();
        r1 = -1095064472; // 0xffffffffbebaa468 float:-0.36453557 double:NaN;
        r2 = 0;
        if (r0 == r1) goto L_0x0038;
    L_0x000a:
        r1 = 187078296; // 0xb269698 float:3.208373E-32 double:9.2428959E-316;
        if (r0 == r1) goto L_0x002e;
    L_0x000f:
        r1 = 1504578661; // 0x59ae0c65 float:6.1237842E15 double:7.43360628E-315;
        if (r0 == r1) goto L_0x0024;
    L_0x0014:
        r1 = 1505942594; // 0x59c2dc42 float:6.8560402E15 double:7.440345003E-315;
        if (r0 == r1) goto L_0x001a;
    L_0x0019:
        goto L_0x0042;
    L_0x001a:
        r0 = "audio/vnd.dts.hd";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0042;
    L_0x0022:
        r3 = 3;
        goto L_0x0043;
    L_0x0024:
        r0 = "audio/eac3";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0042;
    L_0x002c:
        r3 = 1;
        goto L_0x0043;
    L_0x002e:
        r0 = "audio/ac3";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0042;
    L_0x0036:
        r3 = r2;
        goto L_0x0043;
    L_0x0038:
        r0 = "audio/vnd.dts";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0042;
    L_0x0040:
        r3 = 2;
        goto L_0x0043;
    L_0x0042:
        r3 = -1;
    L_0x0043:
        switch(r3) {
            case 0: goto L_0x004e;
            case 1: goto L_0x004c;
            case 2: goto L_0x004a;
            case 3: goto L_0x0047;
            default: goto L_0x0046;
        };
    L_0x0046:
        return r2;
    L_0x0047:
        r3 = 8;
        return r3;
    L_0x004a:
        r3 = 7;
        return r3;
    L_0x004c:
        r3 = 6;
        return r3;
    L_0x004e:
        r3 = 5;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.bt.b(java.lang.String):int");
    }

    private static int a(int i, ByteBuffer byteBuffer) {
        if (i == 7 || i == 8) {
            return fg.a(byteBuffer);
        }
        if (i == 5) {
            return fd.a();
        }
        if (i == 6) {
            return fd.a(byteBuffer);
        }
        StringBuilder stringBuilder = new StringBuilder(38);
        stringBuilder.append("Unexpected audio encoding: ");
        stringBuilder.append(i);
        throw new IllegalStateException(stringBuilder.toString());
    }

    @TargetApi(21)
    private static int a(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        return audioTrack.write(byteBuffer, i, 1);
    }

    @TargetApi(21)
    private static void a(AudioTrack audioTrack, float f) {
        audioTrack.setVolume(f);
    }

    private static void b(AudioTrack audioTrack, float f) {
        audioTrack.setStereoVolume(f, f);
    }
}
