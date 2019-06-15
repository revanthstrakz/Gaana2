package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import com.comscore.streaming.Constants;
import com.google.ads.interactivemedia.v3.internal.bg.b;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;

@TargetApi(16)
public class bi extends bg {
    private final br c;
    private final a d;
    private final long e;
    private final int f;
    private final int g;
    private Surface h;
    private boolean i;
    private boolean j;
    private long k;
    private long l;
    private int m;
    private int n;
    private int o;
    private float p;
    private int q;
    private int r;
    private int s;
    private float t;
    private int u;
    private int v;
    private int w;
    private float x;

    public interface a extends b {
        void a(int i, int i2, int i3, float f);

        void a(int i, long j);

        void a(Surface surface);
    }

    public bi(Context context, bn bnVar, bf bfVar, int i, long j, Handler handler, a aVar, int i2) {
        this(context, bnVar, bfVar, i, j, null, false, handler, aVar, i2);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(long j, long j2) {
        return j < -30000;
    }

    public bi(Context context, bn bnVar, bf bfVar, int i, long j, bv<bx> bvVar, boolean z, Handler handler, a aVar, int i2) {
        super(bnVar, bfVar, (bv) bvVar, z, handler, (b) aVar);
        this.c = new br(context);
        this.f = i;
        this.e = 1000 * j;
        this.d = aVar;
        this.g = i2;
        this.k = -1;
        this.q = -1;
        this.r = -1;
        this.t = -1.0f;
        this.p = -1.0f;
        this.u = -1;
        this.v = -1;
        this.x = -1.0f;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(bf bfVar, bj bjVar) throws bh.b {
        String str = bjVar.b;
        if (!fl.b(str)) {
            return false;
        }
        if (MimeTypes.VIDEO_UNKNOWN.equals(str) || bfVar.a(str, false) != null) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, long j, boolean z) throws az {
        super.a(i, j, z);
        if (z && this.e > 0) {
            this.k = (SystemClock.elapsedRealtime() * 1000) + this.e;
        }
        this.c.a();
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) throws az {
        super.a(j);
        this.j = false;
        this.n = 0;
        this.k = -1;
    }

    /* Access modifiers changed, original: protected */
    public boolean f() {
        if (super.f() && (this.j || !l() || o() == 2)) {
            this.k = -1;
            return true;
        } else if (this.k == -1) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() * 1000 < this.k) {
                return true;
            }
            this.k = -1;
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void c() {
        super.c();
        this.m = 0;
        this.l = SystemClock.elapsedRealtime();
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        this.k = -1;
        A();
        super.d();
    }

    /* Access modifiers changed, original: protected */
    public void g() throws az {
        this.q = -1;
        this.r = -1;
        this.t = -1.0f;
        this.p = -1.0f;
        this.u = -1;
        this.v = -1;
        this.x = -1.0f;
        this.c.b();
        super.g();
    }

    public void a(int i, Object obj) throws az {
        if (i == 1) {
            a((Surface) obj);
        } else {
            super.a(i, obj);
        }
    }

    private void a(Surface surface) throws az {
        if (this.h != surface) {
            this.h = surface;
            this.i = false;
            int v = v();
            if (v == 2 || v == 3) {
                m();
                j();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean k() {
        return super.k() && this.h != null && this.h.isValid();
    }

    /* Access modifiers changed, original: protected */
    public void a(MediaCodec mediaCodec, boolean z, MediaFormat mediaFormat, MediaCrypto mediaCrypto) {
        a(mediaFormat, z);
        mediaCodec.configure(mediaFormat, this.h, mediaCrypto, 0);
    }

    /* Access modifiers changed, original: protected */
    public void a(bk bkVar) throws az {
        float f;
        int i;
        super.a(bkVar);
        if (bkVar.a.m == -1.0f) {
            f = 1.0f;
        } else {
            f = bkVar.a.m;
        }
        this.p = f;
        if (bkVar.a.l == -1) {
            i = 0;
        } else {
            i = bkVar.a.l;
        }
        this.o = i;
    }

    /* Access modifiers changed, original: protected */
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int integer;
        int i = (mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top")) ? 1 : 0;
        if (i != 0) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.q = integer;
        if (i != 0) {
            i = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i = mediaFormat.getInteger("height");
        }
        this.r = i;
        this.t = this.p;
        if (ft.a < 21) {
            this.s = this.o;
        } else if (this.o == 90 || this.o == 270) {
            int i2 = this.q;
            this.q = this.r;
            this.r = i2;
            this.t = 1.0f / this.t;
        }
        mediaCodec.setVideoScalingMode(this.f);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(MediaCodec mediaCodec, boolean z, bj bjVar, bj bjVar2) {
        return bjVar2.b.equals(bjVar.b) && (z || (bjVar.h == bjVar2.h && bjVar.i == bjVar2.i));
    }

    /* Access modifiers changed, original: protected */
    public boolean a(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, BufferInfo bufferInfo, int i, boolean z) {
        long j3 = j2;
        MediaCodec mediaCodec2 = mediaCodec;
        BufferInfo bufferInfo2 = bufferInfo;
        int i2 = i;
        if (z) {
            a(mediaCodec2, i2);
            this.n = 0;
            return true;
        } else if (!this.j) {
            if (ft.a >= 21) {
                a(mediaCodec2, i2, System.nanoTime());
            } else {
                c(mediaCodec2, i2);
            }
            this.n = 0;
            return true;
        } else if (v() != 3) {
            return false;
        } else {
            long elapsedRealtime = (bufferInfo2.presentationTimeUs - j) - ((SystemClock.elapsedRealtime() * 1000) - j3);
            long nanoTime = System.nanoTime();
            long a = this.c.a(bufferInfo2.presentationTimeUs, nanoTime + (elapsedRealtime * 1000));
            elapsedRealtime = (a - nanoTime) / 1000;
            if (a(elapsedRealtime, j3)) {
                b(mediaCodec2, i2);
                return true;
            }
            if (ft.a >= 21) {
                if (elapsedRealtime < 50000) {
                    a(mediaCodec2, i2, a);
                    this.n = 0;
                    return true;
                }
            } else if (elapsedRealtime < 30000) {
                if (elapsedRealtime > 11000) {
                    try {
                        Thread.sleep((elapsedRealtime - Constants.HEARTBEAT_STAGE_ONE_INTERVAL) / 1000);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                c(mediaCodec2, i2);
                this.n = 0;
                return true;
            }
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(MediaCodec mediaCodec, int i) {
        fs.a("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        fs.a();
        av avVar = this.a;
        avVar.g++;
    }

    /* Access modifiers changed, original: protected */
    public void b(MediaCodec mediaCodec, int i) {
        fs.a("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        fs.a();
        av avVar = this.a;
        avVar.h++;
        this.m++;
        this.n++;
        this.a.i = Math.max(this.n, this.a.i);
        if (this.m == this.g) {
            A();
        }
    }

    /* Access modifiers changed, original: protected */
    public void c(MediaCodec mediaCodec, int i) {
        a();
        fs.a("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        fs.a();
        av avVar = this.a;
        avVar.f++;
        this.j = true;
        i();
    }

    /* Access modifiers changed, original: protected */
    @TargetApi(21)
    public void a(MediaCodec mediaCodec, int i, long j) {
        a();
        fs.a("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j);
        fs.a();
        av avVar = this.a;
        avVar.f++;
        this.j = true;
        i();
    }

    @SuppressLint({"InlinedApi"})
    private void a(MediaFormat mediaFormat, boolean z) {
        if (!mediaFormat.containsKey("max-input-size")) {
            int integer = mediaFormat.getInteger("height");
            if (z && mediaFormat.containsKey("max-height")) {
                integer = Math.max(integer, mediaFormat.getInteger("max-height"));
            }
            int integer2 = mediaFormat.getInteger("width");
            if (z && mediaFormat.containsKey("max-width")) {
                integer2 = Math.max(integer, mediaFormat.getInteger("max-width"));
            }
            String string = mediaFormat.getString("mime");
            int i = -1;
            int i2 = 4;
            switch (string.hashCode()) {
                case -1664118616:
                    if (string.equals(MimeTypes.VIDEO_H263)) {
                        i = 0;
                        break;
                    }
                    break;
                case -1662541442:
                    if (string.equals(MimeTypes.VIDEO_H265)) {
                        i = 4;
                        break;
                    }
                    break;
                case 1187890754:
                    if (string.equals(MimeTypes.VIDEO_MP4V)) {
                        i = 1;
                        break;
                    }
                    break;
                case 1331836730:
                    if (string.equals(MimeTypes.VIDEO_H264)) {
                        i = 2;
                        break;
                    }
                    break;
                case 1599127256:
                    if (string.equals(MimeTypes.VIDEO_VP8)) {
                        i = 3;
                        break;
                    }
                    break;
                case 1599127257:
                    if (string.equals(MimeTypes.VIDEO_VP9)) {
                        i = 5;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                    integer2 *= integer;
                    break;
                case 2:
                    if (!"BRAVIA 4K 2015".equals(ft.d)) {
                        integer2 = ((((integer2 + 15) / 16) * ((integer + 15) / 16)) * 16) * 16;
                        break;
                    }
                    return;
                case 3:
                    integer2 *= integer;
                    break;
                case 4:
                case 5:
                    integer2 *= integer;
                    break;
                default:
                    return;
            }
            i2 = 2;
            mediaFormat.setInteger("max-input-size", (integer2 * 3) / (2 * i2));
        }
    }

    private void a() {
        if (this.b != null && this.d != null && (this.u != this.q || this.v != this.r || this.w != this.s || this.x != this.t)) {
            int i = this.q;
            int i2 = this.r;
            int i3 = this.s;
            float f = this.t;
            final int i4 = i;
            final int i5 = i2;
            final int i6 = i3;
            final float f2 = f;
            this.b.post(new Runnable() {
                public void run() {
                    bi.this.d.a(i4, i5, i6, f2);
                }
            });
            this.u = i;
            this.v = i2;
            this.w = i3;
            this.x = f;
        }
    }

    private void i() {
        if (this.b != null && this.d != null && !this.i) {
            final Surface surface = this.h;
            this.b.post(new Runnable() {
                public void run() {
                    bi.this.d.a(surface);
                }
            });
            this.i = true;
        }
    }

    private void A() {
        if (this.b != null && this.d != null && this.m != 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            final int i = this.m;
            final long j = elapsedRealtime - this.l;
            this.b.post(new Runnable() {
                public void run() {
                    bi.this.d.a(i, j);
                }
            });
            this.m = 0;
            this.l = elapsedRealtime;
        }
    }
}
