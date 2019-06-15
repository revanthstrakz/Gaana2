package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.SystemClock;
import com.google.ads.interactivemedia.v3.internal.bg.b;
import com.google.ads.interactivemedia.v3.internal.bt.d;
import com.google.ads.interactivemedia.v3.internal.bt.f;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;

@TargetApi(16)
public class be extends bg implements bd {
    private final a c;
    private final bt d;
    private boolean e;
    private MediaFormat f;
    private int g;
    private int h;
    private long i;
    private boolean j;
    private boolean k;
    private long l;

    public interface a extends b {
        void a(int i, long j, long j2);

        void a(d dVar);

        void a(f fVar);
    }

    public be(bn bnVar, bf bfVar, bv bvVar, boolean z, Handler handler, a aVar, bs bsVar, int i) {
        this(new bn[]{bnVar}, bfVar, bvVar, z, handler, aVar, bsVar, i);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
    }

    /* Access modifiers changed, original: protected */
    public bd b() {
        return this;
    }

    /* Access modifiers changed, original: protected */
    public void i() {
    }

    public be(bn[] bnVarArr, bf bfVar, bv bvVar, boolean z, Handler handler, a aVar, bs bsVar, int i) {
        super(bnVarArr, bfVar, bvVar, z, handler, (b) aVar);
        this.c = aVar;
        this.h = 0;
        this.d = new bt(bsVar, i);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(bf bfVar, bj bjVar) throws bh.b {
        String str = bjVar.b;
        if (!fl.a(str)) {
            return false;
        }
        if (MimeTypes.AUDIO_UNKNOWN.equals(str) || ((a(str) && bfVar.a() != null) || bfVar.a(str, false) != null)) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public ay a(bf bfVar, String str, boolean z) throws bh.b {
        if (a(str)) {
            ay a = bfVar.a();
            if (a != null) {
                this.e = true;
                return a;
            }
        }
        this.e = false;
        return super.a(bfVar, str, z);
    }

    /* Access modifiers changed, original: protected */
    public boolean a(String str) {
        return this.d.a(str);
    }

    /* Access modifiers changed, original: protected */
    public void a(MediaCodec mediaCodec, boolean z, MediaFormat mediaFormat, MediaCrypto mediaCrypto) {
        String string = mediaFormat.getString("mime");
        if (this.e) {
            mediaFormat.setString("mime", MimeTypes.AUDIO_RAW);
            mediaCodec.configure(mediaFormat, null, mediaCrypto, 0);
            mediaFormat.setString("mime", string);
            this.f = mediaFormat;
            return;
        }
        mediaCodec.configure(mediaFormat, null, mediaCrypto, 0);
        this.f = null;
    }

    /* Access modifiers changed, original: protected */
    public void a(bk bkVar) throws az {
        super.a(bkVar);
        this.g = MimeTypes.AUDIO_RAW.equals(bkVar.a.b) ? bkVar.a.s : 2;
    }

    /* Access modifiers changed, original: protected */
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        Object obj = this.f != null ? 1 : null;
        String string = obj != null ? this.f.getString("mime") : MimeTypes.AUDIO_RAW;
        if (obj != null) {
            mediaFormat = this.f;
        }
        this.d.a(string, mediaFormat.getInteger("channel-count"), mediaFormat.getInteger("sample-rate"), this.g);
    }

    /* Access modifiers changed, original: protected */
    public void c() {
        super.c();
        this.d.e();
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        this.d.i();
        super.d();
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return super.e() && !this.d.h();
    }

    /* Access modifiers changed, original: protected */
    public boolean f() {
        return this.d.h() || super.f();
    }

    public long a() {
        long a = this.d.a(e());
        if (a != Long.MIN_VALUE) {
            if (!this.j) {
                a = Math.max(this.i, a);
            }
            this.i = a;
            this.j = false;
        }
        return this.i;
    }

    /* Access modifiers changed, original: protected */
    public void g() throws az {
        this.h = 0;
        try {
            this.d.k();
        } finally {
            super.g();
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) throws az {
        super.a(j);
        this.d.j();
        this.i = j;
        this.j = true;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, BufferInfo bufferInfo, int i, boolean z) throws az {
        Throwable th;
        MediaCodec mediaCodec2 = mediaCodec;
        BufferInfo bufferInfo2 = bufferInfo;
        int i2 = i;
        av avVar;
        if (this.e && (bufferInfo2.flags & 2) != 0) {
            mediaCodec2.releaseOutputBuffer(i2, false);
            return true;
        } else if (z) {
            mediaCodec2.releaseOutputBuffer(i2, false);
            avVar = this.a;
            avVar.g++;
            this.d.f();
            return true;
        } else {
            if (this.d.a()) {
                boolean z2 = this.k;
                this.k = this.d.h();
                if (z2 && !this.k && v() == 3) {
                    long elapsedRealtime = SystemClock.elapsedRealtime() - this.l;
                    long d = this.d.d();
                    long j3 = -1;
                    if (d != -1) {
                        j3 = d / 1000;
                    }
                    a(this.d.c(), j3, elapsedRealtime);
                }
            } else {
                try {
                    if (this.h != 0) {
                        this.d.a(this.h);
                    } else {
                        this.h = this.d.b();
                        a(this.h);
                    }
                    this.k = false;
                    if (v() == 3) {
                        this.d.e();
                    }
                } catch (d e) {
                    th = e;
                    a((d) th);
                    throw new az(th);
                }
            }
            try {
                int a = this.d.a(byteBuffer, bufferInfo2.offset, bufferInfo2.size, bufferInfo2.presentationTimeUs);
                this.l = SystemClock.elapsedRealtime();
                if ((a & 1) != 0) {
                    i();
                    this.j = true;
                }
                if ((a & 2) == 0) {
                    return false;
                }
                mediaCodec2.releaseOutputBuffer(i2, false);
                avVar = this.a;
                avVar.f++;
                return true;
            } catch (f e2) {
                th = e2;
                a((f) th);
                throw new az(th);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void h() {
        this.d.g();
    }

    public void a(int i, Object obj) throws az {
        switch (i) {
            case 1:
                this.d.a(((Float) obj).floatValue());
                return;
            case 2:
                this.d.a((PlaybackParams) obj);
                return;
            case 3:
                if (this.d.b(((Integer) obj).intValue())) {
                    this.h = 0;
                    return;
                }
                return;
            default:
                super.a(i, obj);
                return;
        }
    }

    private void a(final d dVar) {
        if (this.b != null && this.c != null) {
            this.b.post(new Runnable() {
                public void run() {
                    be.this.c.a(dVar);
                }
            });
        }
    }

    private void a(final f fVar) {
        if (this.b != null && this.c != null) {
            this.b.post(new Runnable() {
                public void run() {
                    be.this.c.a(fVar);
                }
            });
        }
    }

    private void a(int i, long j, long j2) {
        if (this.b != null && this.c != null) {
            final int i2 = i;
            final long j3 = j;
            final long j4 = j2;
            this.b.post(new Runnable() {
                public void run() {
                    be.this.c.a(i2, j3, j4);
                }
            });
        }
    }
}
