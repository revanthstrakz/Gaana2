package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.CodecException;
import android.media.MediaCodec.CryptoException;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@TargetApi(16)
public abstract class bg extends bo {
    private static final byte[] c = ft.d("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private ByteBuffer[] A;
    private long B;
    private int C;
    private int D;
    private boolean E;
    private boolean F;
    private int G;
    private int H;
    private boolean I;
    private boolean J;
    private int K;
    private boolean L;
    private boolean M;
    private boolean N;
    private boolean O;
    public final av a;
    protected final Handler b;
    private final bf d;
    private final bv<bx> e;
    private final boolean f;
    private final bm g;
    private final bk h;
    private final List<Long> i;
    private final BufferInfo j;
    private final b k;
    private final boolean l;
    private bj m;
    private bu n;
    private MediaCodec o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private ByteBuffer[] z;

    public static class a extends Exception {
        public final String a;
        public final boolean b;
        public final String c;
        public final String d;

        public a(bj bjVar, Throwable th, boolean z, int i) {
            String valueOf = String.valueOf(bjVar);
            StringBuilder stringBuilder = new StringBuilder(36 + String.valueOf(valueOf).length());
            stringBuilder.append("Decoder init failed: [");
            stringBuilder.append(i);
            stringBuilder.append("], ");
            stringBuilder.append(valueOf);
            super(stringBuilder.toString(), th);
            this.a = bjVar.b;
            this.b = z;
            this.c = null;
            this.d = a(i);
        }

        public a(bj bjVar, Throwable th, boolean z, String str) {
            String valueOf = String.valueOf(bjVar);
            StringBuilder stringBuilder = new StringBuilder((23 + String.valueOf(str).length()) + String.valueOf(valueOf).length());
            stringBuilder.append("Decoder init failed: ");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(valueOf);
            super(stringBuilder.toString(), th);
            this.a = bjVar.b;
            this.b = z;
            this.c = str;
            this.d = ft.a >= 21 ? a(th) : null;
        }

        @TargetApi(21)
        private static String a(Throwable th) {
            return th instanceof CodecException ? ((CodecException) th).getDiagnosticInfo() : null;
        }

        private static String a(int i) {
            Object obj = i < 0 ? "neg_" : "";
            i = Math.abs(i);
            StringBuilder stringBuilder = new StringBuilder(64 + String.valueOf(obj).length());
            stringBuilder.append("com.google.ads.interactivemedia.v3.exoplayer.MediaCodecTrackRenderer_");
            stringBuilder.append(obj);
            stringBuilder.append(i);
            return stringBuilder.toString();
        }
    }

    public interface b {
        void a(CryptoException cryptoException);

        void a(a aVar);

        void a(String str, long j, long j2);
    }

    public bg(bn bnVar, bf bfVar, bv<bx> bvVar, boolean z, Handler handler, b bVar) {
        this(new bn[]{bnVar}, bfVar, (bv) bvVar, z, handler, bVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(long j, ByteBuffer byteBuffer, int i, boolean z) {
    }

    /* Access modifiers changed, original: protected */
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) throws az {
    }

    public abstract void a(MediaCodec mediaCodec, boolean z, MediaFormat mediaFormat, MediaCrypto mediaCrypto);

    public abstract boolean a(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, BufferInfo bufferInfo, int i, boolean z) throws az;

    /* Access modifiers changed, original: protected */
    public boolean a(MediaCodec mediaCodec, boolean z, bj bjVar, bj bjVar2) {
        return false;
    }

    public abstract boolean a(bf bfVar, bj bjVar) throws com.google.ads.interactivemedia.v3.internal.bh.b;

    /* Access modifiers changed, original: protected */
    public void b(long j) {
    }

    /* Access modifiers changed, original: protected */
    public void c() {
    }

    /* Access modifiers changed, original: protected */
    public void d() {
    }

    /* Access modifiers changed, original: protected */
    public void h() {
    }

    /* Access modifiers changed, original: protected */
    public long p() {
        return 0;
    }

    public bg(bn[] bnVarArr, bf bfVar, bv<bx> bvVar, boolean z, Handler handler, b bVar) {
        super(bnVarArr);
        fe.b(ft.a >= 16);
        this.d = (bf) fe.a((Object) bfVar);
        this.e = bvVar;
        this.f = z;
        this.b = handler;
        this.k = bVar;
        this.l = B();
        this.a = new av();
        this.g = new bm(0);
        this.h = new bk();
        this.i = new ArrayList();
        this.j = new BufferInfo();
        this.G = 0;
        this.H = 0;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean a(bj bjVar) throws com.google.ads.interactivemedia.v3.internal.bh.b {
        return a(this.d, bjVar);
    }

    /* Access modifiers changed, original: protected */
    public ay a(bf bfVar, String str, boolean z) throws com.google.ads.interactivemedia.v3.internal.bh.b {
        return bfVar.a(str, z);
    }

    /* Access modifiers changed, original: protected|final */
    public final void j() throws az {
        if (k()) {
            boolean z;
            MediaCrypto mediaCrypto;
            ay a;
            String str = this.m.b;
            boolean z2 = false;
            if (this.n == null) {
                z = false;
                mediaCrypto = null;
            } else if (this.e == null) {
                throw new az("Media requires a DrmSessionManager");
            } else {
                if (!this.E) {
                    this.e.a(this.n);
                    this.E = true;
                }
                int b = this.e.b();
                if (b == 0) {
                    throw new az(this.e.d());
                } else if (b == 3 || b == 4) {
                    mediaCrypto = ((bx) this.e.c()).a();
                    z = this.e.a(str);
                } else {
                    return;
                }
            }
            try {
                a = a(this.d, str, z);
            } catch (com.google.ads.interactivemedia.v3.internal.bh.b e) {
                a(new a(this.m, e, z, -49998));
                a = null;
            }
            if (a == null) {
                a(new a(this.m, null, z, -49999));
            }
            String str2 = a.a;
            if (a.c && !f(str2)) {
                z2 = true;
            }
            this.p = z2;
            this.q = a(str2, this.m);
            this.r = a(str2);
            this.s = b(str2);
            this.t = c(str2);
            this.u = d(str2);
            this.v = e(str2);
            this.w = b(str2, this.m);
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                StringBuilder stringBuilder = new StringBuilder(19 + String.valueOf(str2).length());
                stringBuilder.append("createByCodecName(");
                stringBuilder.append(str2);
                stringBuilder.append(")");
                fs.a(stringBuilder.toString());
                this.o = MediaCodec.createByCodecName(str2);
                fs.a();
                fs.a("configureCodec");
                a(this.o, a.c, b(this.m), mediaCrypto);
                fs.a();
                fs.a("codec.start()");
                this.o.start();
                fs.a();
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                a(str2, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
                this.z = this.o.getInputBuffers();
                this.A = this.o.getOutputBuffers();
            } catch (Exception e2) {
                a(new a(this.m, e2, z, str2));
            }
            this.B = v() == 3 ? SystemClock.elapsedRealtime() : -1;
            this.C = -1;
            this.D = -1;
            this.O = true;
            av avVar = this.a;
            avVar.a++;
        }
    }

    private void a(a aVar) throws az {
        b(aVar);
        throw new az((Throwable) aVar);
    }

    /* Access modifiers changed, original: protected */
    public boolean k() {
        return this.o == null && this.m != null;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean l() {
        return this.o != null;
    }

    /* Access modifiers changed, original: protected */
    public void g() throws az {
        this.m = null;
        this.n = null;
        try {
            m();
            try {
                if (this.E) {
                    this.e.a();
                    this.E = false;
                }
                super.g();
            } catch (Throwable th) {
                super.g();
            }
        } catch (Throwable th2) {
            super.g();
        }
    }

    /* Access modifiers changed, original: protected */
    public void m() {
        if (this.o != null) {
            this.B = -1;
            this.C = -1;
            this.D = -1;
            this.N = false;
            this.i.clear();
            this.z = null;
            this.A = null;
            this.F = false;
            this.I = false;
            this.p = false;
            this.q = false;
            this.r = false;
            this.s = false;
            this.t = false;
            this.u = false;
            this.w = false;
            this.x = false;
            this.y = false;
            this.J = false;
            this.G = 0;
            this.H = 0;
            av avVar = this.a;
            avVar.b++;
            try {
                this.o.stop();
                try {
                    this.o.release();
                } finally {
                    this.o = null;
                }
            } catch (Throwable th) {
                this.o.release();
            } finally {
                this.o = null;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) throws az {
        this.K = 0;
        this.L = false;
        this.M = false;
        if (this.o != null) {
            n();
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(long j, long j2, boolean z) throws az {
        int i = z ? this.K == 0 ? 1 : this.K : 0;
        this.K = i;
        if (this.m == null) {
            g(j);
        }
        j();
        if (this.o != null) {
            fs.a("drainAndFeed");
            while (a(j, j2)) {
            }
            if (a(j, true)) {
                while (a(j, false)) {
                }
            }
            fs.a();
        }
        this.a.a();
    }

    private void g(long j) throws az {
        if (a(j, this.h, null) == -4) {
            a(this.h);
        }
    }

    /* Access modifiers changed, original: protected */
    public void n() throws az {
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.O = true;
        this.N = false;
        this.i.clear();
        this.x = false;
        this.y = false;
        if (this.r || (this.u && this.J)) {
            m();
            j();
        } else if (this.H != 0) {
            m();
            j();
        } else {
            this.o.flush();
            this.I = false;
        }
        if (this.F && this.m != null) {
            this.G = 1;
        }
    }

    private boolean a(long j, boolean z) throws az {
        Throwable th;
        if (this.L || this.H == 2) {
            return false;
        }
        if (this.C < 0) {
            this.C = this.o.dequeueInputBuffer(0);
            if (this.C < 0) {
                return false;
            }
            this.g.b = this.z[this.C];
            this.g.d();
        }
        if (this.H == 1) {
            if (!this.t) {
                this.J = true;
                this.o.queueInputBuffer(this.C, 0, 0, 0, 4);
                this.C = -1;
            }
            this.H = 2;
            return false;
        } else if (this.x) {
            this.x = false;
            this.g.b.put(c);
            this.o.queueInputBuffer(this.C, 0, c.length, 0, 0);
            this.C = -1;
            this.I = true;
            return true;
        } else {
            int i;
            if (this.N) {
                i = -3;
            } else {
                if (this.G == 1) {
                    for (i = 0; i < this.m.f.size(); i++) {
                        this.g.b.put((byte[]) this.m.f.get(i));
                    }
                    this.G = 2;
                }
                i = a(j, this.h, this.g);
                if (z && this.K == 1 && i == -2) {
                    this.K = 2;
                }
            }
            if (i == -2) {
                return false;
            }
            if (i == -4) {
                if (this.G == 2) {
                    this.g.d();
                    this.G = 1;
                }
                a(this.h);
                return true;
            } else if (i == -1) {
                if (this.G == 2) {
                    this.g.d();
                    this.G = 1;
                }
                this.L = true;
                if (this.I) {
                    try {
                        if (!this.t) {
                            this.J = true;
                            this.o.queueInputBuffer(this.C, 0, 0, 0, 4);
                            this.C = -1;
                        }
                        return false;
                    } catch (CryptoException e) {
                        th = e;
                        a((CryptoException) th);
                        throw new az(th);
                    }
                }
                A();
                return false;
            } else {
                if (this.O) {
                    if (this.g.c()) {
                        this.O = false;
                    } else {
                        this.g.d();
                        if (this.G == 2) {
                            this.G = 1;
                        }
                        return true;
                    }
                }
                boolean a = this.g.a();
                this.N = a(a);
                if (this.N) {
                    return false;
                }
                if (this.q && !a) {
                    fn.a(this.g.b);
                    if (this.g.b.position() == 0) {
                        return true;
                    }
                    this.q = false;
                }
                try {
                    int position = this.g.b.position();
                    int i2 = position - this.g.c;
                    long j2 = this.g.e;
                    if (this.g.b()) {
                        this.i.add(Long.valueOf(j2));
                    }
                    a(j2, this.g.b, position, a);
                    if (a) {
                        CryptoInfo a2 = a(this.g, i2);
                        this.o.queueSecureInputBuffer(this.C, 0, a2, j2, 0);
                    } else {
                        this.o.queueInputBuffer(this.C, 0, position, j2, 0);
                    }
                    this.C = -1;
                    this.I = true;
                    this.G = 0;
                    av avVar = this.a;
                    avVar.c++;
                    return true;
                } catch (CryptoException e2) {
                    th = e2;
                    a((CryptoException) th);
                    throw new az(th);
                }
            }
        }
    }

    private static CryptoInfo a(bm bmVar, int i) {
        CryptoInfo a = bmVar.a.a();
        if (i == 0) {
            return a;
        }
        if (a.numBytesOfClearData == null) {
            a.numBytesOfClearData = new int[1];
        }
        int[] iArr = a.numBytesOfClearData;
        iArr[0] = iArr[0] + i;
        return a;
    }

    private MediaFormat b(bj bjVar) {
        MediaFormat b = bjVar.b();
        if (this.l) {
            b.setInteger("auto-frc", 0);
        }
        return b;
    }

    private boolean a(boolean z) throws az {
        if (!this.E) {
            return false;
        }
        int b = this.e.b();
        if (b == 0) {
            throw new az(this.e.d());
        } else if (b == 4 || (!z && this.f)) {
            return false;
        } else {
            return true;
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(bk bkVar) throws az {
        bj bjVar = this.m;
        this.m = bkVar.a;
        this.n = bkVar.b;
        boolean z = false;
        boolean z2 = (this.n == null || this.E) ? false : true;
        if (!ft.a(this.m, (Object) bjVar) || z2) {
            if (this.o != null && !z2 && a(this.o, this.p, bjVar, this.m)) {
                this.F = true;
                this.G = 1;
                if (this.s && this.m.h == bjVar.h && this.m.i == bjVar.i) {
                    z = true;
                }
                this.x = z;
            } else if (this.I) {
                this.H = 1;
            } else {
                m();
                j();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return this.M;
    }

    /* Access modifiers changed, original: protected */
    public boolean f() {
        return (this.m == null || this.N || (this.K == 0 && this.D < 0 && !a())) ? false : true;
    }

    /* Access modifiers changed, original: protected|final */
    public final int o() {
        return this.K;
    }

    private boolean a() {
        return SystemClock.elapsedRealtime() < this.B + 1000;
    }

    private boolean a(long j, long j2) throws az {
        if (this.M) {
            return false;
        }
        if (this.D < 0) {
            if (this.v && this.J) {
                try {
                    this.D = this.o.dequeueOutputBuffer(this.j, p());
                } catch (IllegalStateException unused) {
                    A();
                    if (this.M) {
                        m();
                    }
                    return false;
                }
            }
            this.D = this.o.dequeueOutputBuffer(this.j, p());
        }
        if (this.D == -2) {
            i();
            return true;
        } else if (this.D == -3) {
            this.A = this.o.getOutputBuffers();
            av avVar = this.a;
            avVar.e++;
            return true;
        } else if (this.D < 0) {
            if (!this.t || (!this.L && this.H != 2)) {
                return false;
            }
            A();
            return true;
        } else if (this.y) {
            this.y = false;
            this.o.releaseOutputBuffer(this.D, false);
            this.D = -1;
            return true;
        } else if ((this.j.flags & 4) != 0) {
            A();
            return false;
        } else {
            boolean a;
            int h = h(this.j.presentationTimeUs);
            if (this.v && this.J) {
                try {
                    a = a(j, j2, this.o, this.A[this.D], this.j, this.D, h != -1);
                } catch (IllegalStateException unused2) {
                    A();
                    if (this.M) {
                        m();
                    }
                    return false;
                }
            }
            a = a(j, j2, this.o, this.A[this.D], this.j, this.D, h != -1);
            if (!a) {
                return false;
            }
            b(this.j.presentationTimeUs);
            if (h != -1) {
                this.i.remove(h);
            }
            this.D = -1;
            return true;
        }
    }

    private void i() throws az {
        MediaFormat outputFormat = this.o.getOutputFormat();
        if (this.s && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
            this.y = true;
            return;
        }
        if (this.w) {
            outputFormat.setInteger("channel-count", 1);
        }
        a(this.o, outputFormat);
        av avVar = this.a;
        avVar.d++;
    }

    private void A() throws az {
        if (this.H == 2) {
            m();
            j();
            return;
        }
        this.M = true;
        h();
    }

    private void b(final a aVar) {
        if (this.b != null && this.k != null) {
            this.b.post(new Runnable() {
                public void run() {
                    bg.this.k.a(aVar);
                }
            });
        }
    }

    private void a(final CryptoException cryptoException) {
        if (this.b != null && this.k != null) {
            this.b.post(new Runnable() {
                public void run() {
                    bg.this.k.a(cryptoException);
                }
            });
        }
    }

    private void a(String str, long j, long j2) {
        if (this.b != null && this.k != null) {
            final String str2 = str;
            final long j3 = j;
            final long j4 = j2;
            this.b.post(new Runnable() {
                public void run() {
                    bg.this.k.a(str2, j3, j4);
                }
            });
        }
    }

    private int h(long j) {
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            if (((Long) this.i.get(i)).longValue() == j) {
                return i;
            }
        }
        return -1;
    }

    private static boolean a(String str) {
        return ft.a < 18 || ((ft.a == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (ft.a == 19 && ft.d.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str))));
    }

    private static boolean b(String str) {
        return ft.a < 24 && (("OMX.Nvidia.h264.decode".equals(str) || "OMX.Nvidia.h264.decode.secure".equals(str)) && (ft.b.equals("flounder") || ft.b.equals("flounder_lte") || ft.b.equals("grouper") || ft.b.equals("tilapia")));
    }

    private static boolean a(String str, bj bjVar) {
        return ft.a < 21 && bjVar.f.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    private static boolean c(String str) {
        return ft.a <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str));
    }

    private static boolean d(String str) {
        return (ft.a <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (ft.a <= 19 && "hb2000".equals(ft.b) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
    }

    private static boolean e(String str) {
        return ft.a == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static boolean b(String str, bj bjVar) {
        if (ft.a <= 18 && bjVar.q == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str)) {
            return true;
        }
        return false;
    }

    private static boolean f(String str) {
        return ft.a <= 19 && ft.d.equals("ODROID-XU3") && ("OMX.Exynos.AVC.Decoder".equals(str) || "OMX.Exynos.AVC.Decoder.secure".equals(str));
    }

    private static boolean B() {
        return ft.a <= 22 && "foster".equals(ft.b) && "NVIDIA".equals(ft.c);
    }
}
