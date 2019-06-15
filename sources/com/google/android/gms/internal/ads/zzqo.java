package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.comscore.streaming.Constants;
import com.google.android.exoplayer2.C;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class zzqo extends zzjy {
    private static final int[] zzbis = new int[]{1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private int zzaak;
    private boolean zzadt;
    private final zzqs zzbit;
    private final zzqv zzbiu;
    private final long zzbiv;
    private final int zzbiw;
    private final boolean zzbix;
    private final long[] zzbiy;
    private zzfs[] zzbiz;
    private zzqq zzbja;
    private Surface zzbjb;
    private Surface zzbjc;
    private int zzbjd;
    private boolean zzbje;
    private long zzbjf;
    private long zzbjg;
    private int zzbjh;
    private int zzbji;
    private int zzbjj;
    private float zzbjk;
    private int zzbjl;
    private int zzbjm;
    private int zzbjn;
    private float zzbjo;
    private int zzbjp;
    private int zzbjq;
    private int zzbjr;
    private float zzbjs;
    zzqr zzbjt;
    private long zzbju;
    private int zzbjv;
    private final Context zzsp;

    public zzqo(Context context, zzka zzka, long j, Handler handler, zzqu zzqu, int i) {
        this(context, zzka, 0, null, false, handler, zzqu, -1);
    }

    private static boolean zzan(long j) {
        return j < -30000;
    }

    private zzqo(Context context, zzka zzka, long j, zzhu<Object> zzhu, boolean z, Handler handler, zzqu zzqu, int i) {
        boolean z2 = false;
        super(2, zzka, null, false);
        this.zzbiv = 0;
        this.zzbiw = -1;
        this.zzsp = context.getApplicationContext();
        this.zzbit = new zzqs(context);
        this.zzbiu = new zzqv(handler, zzqu);
        if (zzqe.SDK_INT <= 22 && "foster".equals(zzqe.DEVICE) && "NVIDIA".equals(zzqe.MANUFACTURER)) {
            z2 = true;
        }
        this.zzbix = z2;
        this.zzbiy = new long[10];
        this.zzbju = C.TIME_UNSET;
        this.zzbjf = C.TIME_UNSET;
        this.zzbjl = -1;
        this.zzbjm = -1;
        this.zzbjo = -1.0f;
        this.zzbjk = -1.0f;
        this.zzbjd = 1;
        zzhr();
    }

    /* Access modifiers changed, original: protected|final */
    public final int zza(zzka zzka, zzfs zzfs) throws zzke {
        String str = zzfs.zzzj;
        int i = 0;
        if (!zzpt.zzac(str)) {
            return 0;
        }
        int i2;
        boolean z;
        zzhp zzhp = zzfs.zzzm;
        if (zzhp != null) {
            i2 = 0;
            z = i2;
            while (i2 < zzhp.zzagr) {
                z |= zzhp.zzu(i2).zzags;
                i2++;
            }
        } else {
            z = false;
        }
        zzjx zzb = zzka.zzb(str, z);
        if (zzb == null) {
            return 1;
        }
        int i3;
        boolean zzu = zzb.zzu(zzfs.zzzg);
        if (zzu && zzfs.width > 0 && zzfs.height > 0) {
            if (zzqe.SDK_INT >= 21) {
                zzu = zzb.zza(zzfs.width, zzfs.height, (double) zzfs.zzzn);
            } else {
                zzu = zzfs.width * zzfs.height <= zzkc.zzer();
                if (!zzu) {
                    i2 = zzfs.width;
                    i3 = zzfs.height;
                    String str2 = zzqe.zzbic;
                    StringBuilder stringBuilder = new StringBuilder(56 + String.valueOf(str2).length());
                    stringBuilder.append("FalseCheck [legacyFrameSize, ");
                    stringBuilder.append(i2);
                    stringBuilder.append(AvidJSONUtil.KEY_X);
                    stringBuilder.append(i3);
                    stringBuilder.append("] [");
                    stringBuilder.append(str2);
                    stringBuilder.append("]");
                    Log.d("MediaCodecVideoRenderer", stringBuilder.toString());
                }
            }
        }
        i3 = zzb.zzatq ? 8 : 4;
        if (zzb.zzadt) {
            i = 16;
        }
        return (zzu ? 3 : 2) | (i3 | i);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzb(boolean z) throws zzff {
        super.zzb(z);
        this.zzaak = zzbn().zzaak;
        this.zzadt = this.zzaak != 0;
        this.zzbiu.zzc(this.zzavd);
        this.zzbit.enable();
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(zzfs[] zzfsArr, long j) throws zzff {
        this.zzbiz = zzfsArr;
        if (this.zzbju == C.TIME_UNSET) {
            this.zzbju = j;
        } else {
            if (this.zzbjv == this.zzbiy.length) {
                long j2 = this.zzbiy[this.zzbjv - 1];
                StringBuilder stringBuilder = new StringBuilder(65);
                stringBuilder.append("Too many stream changes, so dropping offset: ");
                stringBuilder.append(j2);
                Log.w("MediaCodecVideoRenderer", stringBuilder.toString());
            } else {
                this.zzbjv++;
            }
            this.zzbiy[this.zzbjv - 1] = j;
        }
        super.zza(zzfsArr, j);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(long j, boolean z) throws zzff {
        super.zza(j, z);
        zzhp();
        this.zzbji = 0;
        if (this.zzbjv != 0) {
            this.zzbju = this.zzbiy[this.zzbjv - 1];
            this.zzbjv = 0;
        }
        if (z) {
            zzho();
        } else {
            this.zzbjf = C.TIME_UNSET;
        }
    }

    public final boolean isReady() {
        if (super.isReady() && (this.zzbje || ((this.zzbjc != null && this.zzbjb == this.zzbjc) || zzel() == null))) {
            this.zzbjf = C.TIME_UNSET;
            return true;
        } else if (this.zzbjf == C.TIME_UNSET) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.zzbjf) {
                return true;
            }
            this.zzbjf = C.TIME_UNSET;
            return false;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void onStarted() {
        super.onStarted();
        this.zzbjh = 0;
        this.zzbjg = SystemClock.elapsedRealtime();
        this.zzbjf = C.TIME_UNSET;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onStopped() {
        zzhu();
        super.onStopped();
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzbm() {
        this.zzbjl = -1;
        this.zzbjm = -1;
        this.zzbjo = -1.0f;
        this.zzbjk = -1.0f;
        this.zzbju = C.TIME_UNSET;
        this.zzbjv = 0;
        zzhr();
        zzhp();
        this.zzbit.disable();
        this.zzbjt = null;
        this.zzadt = false;
        try {
            super.zzbm();
        } finally {
            this.zzavd.zzds();
            this.zzbiu.zzd(this.zzavd);
        }
    }

    public final void zza(int i, Object obj) throws zzff {
        if (i == 1) {
            Surface surface = (Surface) obj;
            if (surface == null) {
                if (this.zzbjc != null) {
                    surface = this.zzbjc;
                } else {
                    zzjx zzem = zzem();
                    if (zzem != null && zzl(zzem.zzatr)) {
                        this.zzbjc = zzqk.zzc(this.zzsp, zzem.zzatr);
                        surface = this.zzbjc;
                    }
                }
            }
            if (this.zzbjb != surface) {
                this.zzbjb = surface;
                i = getState();
                if (i == 1 || i == 2) {
                    MediaCodec zzel = zzel();
                    if (zzqe.SDK_INT < 23 || zzel == null || surface == null) {
                        zzen();
                        zzek();
                    } else {
                        zzel.setOutputSurface(surface);
                    }
                }
                if (surface == null || surface == this.zzbjc) {
                    zzhr();
                    zzhp();
                } else {
                    zzht();
                    zzhp();
                    if (i == 2) {
                        zzho();
                        return;
                    }
                }
                return;
            }
            if (!(surface == null || surface == this.zzbjc)) {
                zzht();
                if (this.zzbje) {
                    this.zzbiu.zzb(this.zzbjb);
                }
            }
        } else if (i == 4) {
            this.zzbjd = ((Integer) obj).intValue();
            MediaCodec zzel2 = zzel();
            if (zzel2 != null) {
                zzel2.setVideoScalingMode(this.zzbjd);
            }
        } else {
            super.zza(i, obj);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzjx zzjx) {
        return this.zzbjb != null || zzl(zzjx.zzatr);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(zzjx zzjx, MediaCodec mediaCodec, zzfs zzfs, MediaCrypto mediaCrypto) throws zzke {
        zzqq zzqq;
        zzjx zzjx2 = zzjx;
        MediaCodec mediaCodec2 = mediaCodec;
        zzfs zzfs2 = zzfs;
        zzfs[] zzfsArr = this.zzbiz;
        int i = zzfs2.width;
        int i2 = zzfs2.height;
        int zzj = zzj(zzfs);
        if (zzfsArr.length == 1) {
            zzqq = new zzqq(i, i2, zzj);
        } else {
            int i3 = i2;
            int i4 = zzj;
            i2 = 0;
            zzj = i;
            for (zzfs zzfs3 : zzfsArr) {
                if (zza(zzjx2.zzatq, zzfs2, zzfs3)) {
                    int i5 = (zzfs3.width == -1 || zzfs3.height == -1) ? 1 : 0;
                    i2 |= i5;
                    zzj = Math.max(zzj, zzfs3.width);
                    i5 = Math.max(i3, zzfs3.height);
                    i4 = Math.max(i4, zzj(zzfs3));
                    i3 = i5;
                }
            }
            if (i2 != 0) {
                Point point;
                StringBuilder stringBuilder = new StringBuilder(66);
                stringBuilder.append("Resolutions unknown. Codec max resolution: ");
                stringBuilder.append(zzj);
                stringBuilder.append(AvidJSONUtil.KEY_X);
                stringBuilder.append(i3);
                Log.w("MediaCodecVideoRenderer", stringBuilder.toString());
                Object obj = zzfs2.height > zzfs2.width ? 1 : null;
                i = obj != null ? zzfs2.height : zzfs2.width;
                i2 = obj != null ? zzfs2.width : zzfs2.height;
                float f = ((float) i2) / ((float) i);
                int[] iArr = zzbis;
                int length = iArr.length;
                int i6 = 0;
                while (i6 < length) {
                    int i7 = iArr[i6];
                    int i8 = (int) (((float) i7) * f);
                    if (i7 <= i || i8 <= i2) {
                        break;
                    }
                    int i9 = i;
                    int i10 = i2;
                    if (zzqe.SDK_INT >= 21) {
                        i = obj != null ? i8 : i7;
                        if (obj == null) {
                            i7 = i8;
                        }
                        Point zzc = zzjx2.zzc(i, i7);
                        Point point2 = zzc;
                        if (zzjx2.zza(zzc.x, zzc.y, (double) zzfs2.zzzn)) {
                            point = point2;
                            break;
                        }
                    } else {
                        i2 = zzqe.zzf(i7, 16) << 4;
                        i = zzqe.zzf(i8, 16) << 4;
                        if (i2 * i <= zzkc.zzer()) {
                            i8 = obj != null ? i : i2;
                            if (obj != null) {
                                i = i2;
                            }
                            point = new Point(i8, i);
                        }
                    }
                    i6++;
                    i = i9;
                    i2 = i10;
                }
                point = null;
                if (point != null) {
                    zzj = Math.max(zzj, point.x);
                    i3 = Math.max(i3, point.y);
                    i4 = Math.max(i4, zza(zzfs2.zzzj, zzj, i3));
                    stringBuilder = new StringBuilder(57);
                    stringBuilder.append("Codec max resolution adjusted to: ");
                    stringBuilder.append(zzj);
                    stringBuilder.append(AvidJSONUtil.KEY_X);
                    stringBuilder.append(i3);
                    Log.w("MediaCodecVideoRenderer", stringBuilder.toString());
                }
            }
            zzqq = new zzqq(zzj, i3, i4);
        }
        this.zzbja = zzqq;
        zzqq = this.zzbja;
        boolean z = this.zzbix;
        i2 = this.zzaak;
        MediaFormat zzcf = zzfs.zzcf();
        zzcf.setInteger("max-width", zzqq.width);
        zzcf.setInteger("max-height", zzqq.height);
        if (zzqq.zzbjw != -1) {
            zzcf.setInteger("max-input-size", zzqq.zzbjw);
        }
        if (z) {
            zzcf.setInteger("auto-frc", 0);
        }
        if (i2 != 0) {
            zzcf.setFeatureEnabled("tunneled-playback", true);
            zzcf.setInteger("audio-session-id", i2);
        }
        if (this.zzbjb == null) {
            zzpo.checkState(zzl(zzjx2.zzatr));
            if (this.zzbjc == null) {
                this.zzbjc = zzqk.zzc(this.zzsp, zzjx2.zzatr);
            }
            this.zzbjb = this.zzbjc;
        }
        mediaCodec2.configure(zzcf, this.zzbjb, null, 0);
        if (zzqe.SDK_INT >= 23 && this.zzadt) {
            this.zzbjt = new zzqr(this, mediaCodec2);
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* JADX WARNING: Missing block: B:9:0x0017, code skipped:
            return;
     */
    public final void zzen() {
        /*
        r4 = this;
        r0 = 0;
        super.zzen();	 Catch:{ all -> 0x0019 }
        r1 = r4.zzbjc;
        if (r1 == 0) goto L_0x0018;
    L_0x0008:
        r1 = r4.zzbjb;
        r2 = r4.zzbjc;
        if (r1 != r2) goto L_0x0010;
    L_0x000e:
        r4.zzbjb = r0;
    L_0x0010:
        r1 = r4.zzbjc;
        r1.release();
        r4.zzbjc = r0;
        return;
    L_0x0018:
        return;
    L_0x0019:
        r1 = move-exception;
        r2 = r4.zzbjc;
        if (r2 == 0) goto L_0x002d;
    L_0x001e:
        r2 = r4.zzbjb;
        r3 = r4.zzbjc;
        if (r2 != r3) goto L_0x0026;
    L_0x0024:
        r4.zzbjb = r0;
    L_0x0026:
        r2 = r4.zzbjc;
        r2.release();
        r4.zzbjc = r0;
    L_0x002d:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqo.zzen():void");
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzc(String str, long j, long j2) {
        this.zzbiu.zzb(str, j, j2);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zze(zzfs zzfs) throws zzff {
        super.zze(zzfs);
        this.zzbiu.zzd(zzfs);
        this.zzbjk = zzfs.zzzp == -1.0f ? 1.0f : zzfs.zzzp;
        this.zzbjj = zzk(zzfs);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(zzho zzho) {
        if (zzqe.SDK_INT < 23 && this.zzadt) {
            zzhq();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int integer;
        int i = (mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top")) ? 1 : 0;
        if (i != 0) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.zzbjl = integer;
        if (i != 0) {
            i = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i = mediaFormat.getInteger("height");
        }
        this.zzbjm = i;
        this.zzbjo = this.zzbjk;
        if (zzqe.SDK_INT < 21) {
            this.zzbjn = this.zzbjj;
        } else if (this.zzbjj == 90 || this.zzbjj == 270) {
            int i2 = this.zzbjl;
            this.zzbjl = this.zzbjm;
            this.zzbjm = i2;
            this.zzbjo = 1.0f / this.zzbjo;
        }
        mediaCodec.setVideoScalingMode(this.zzbjd);
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(MediaCodec mediaCodec, boolean z, zzfs zzfs, zzfs zzfs2) {
        return zza(z, zzfs, zzfs2) && zzfs2.width <= this.zzbja.width && zzfs2.height <= this.zzbja.height && zzfs2.zzzk <= this.zzbja.zzbjw;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) {
        MediaCodec mediaCodec2 = mediaCodec;
        int i3 = i;
        long j4 = j3;
        while (this.zzbjv != 0 && j4 >= this.zzbiy[0]) {
            this.zzbju = this.zzbiy[0];
            this.zzbjv--;
            System.arraycopy(this.zzbiy, 1, this.zzbiy, 0, this.zzbjv);
        }
        long j5 = j4 - this.zzbju;
        if (z) {
            zza(mediaCodec2, i3, j5);
            return true;
        }
        long j6 = j4 - j;
        if (this.zzbjb == this.zzbjc) {
            if (!zzan(j6)) {
                return false;
            }
            zza(mediaCodec2, i3, j5);
            return true;
        } else if (!this.zzbje) {
            if (zzqe.SDK_INT >= 21) {
                zza(mediaCodec2, i3, j5, System.nanoTime());
            } else {
                zzb(mediaCodec2, i3, j5);
            }
            return true;
        } else if (getState() != 2) {
            return false;
        } else {
            long elapsedRealtime = j6 - ((SystemClock.elapsedRealtime() * 1000) - j2);
            j6 = System.nanoTime();
            long zzh = this.zzbit.zzh(j4, j6 + (elapsedRealtime * 1000));
            j4 = (zzh - j6) / 1000;
            if (zzan(j4)) {
                zzqc.beginSection("dropVideoBuffer");
                mediaCodec2.releaseOutputBuffer(i3, false);
                zzqc.endSection();
                zzhn zzhn = this.zzavd;
                zzhn.zzagl++;
                this.zzbjh++;
                this.zzbji++;
                this.zzavd.zzagm = Math.max(this.zzbji, this.zzavd.zzagm);
                if (this.zzbjh == this.zzbiw) {
                    zzhu();
                }
                return true;
            }
            if (zzqe.SDK_INT >= 21) {
                if (j4 < 50000) {
                    zza(mediaCodec2, i3, j5, zzh);
                    return true;
                }
            } else if (j4 < 30000) {
                if (j4 > 11000) {
                    try {
                        Thread.sleep((j4 - Constants.HEARTBEAT_STAGE_ONE_INTERVAL) / 1000);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                zzb(mediaCodec2, i3, j5);
                return true;
            }
            return false;
        }
    }

    private final void zza(MediaCodec mediaCodec, int i, long j) {
        zzqc.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        zzqc.endSection();
        zzhn zzhn = this.zzavd;
        zzhn.zzagk++;
    }

    private final void zzb(MediaCodec mediaCodec, int i, long j) {
        zzhs();
        zzqc.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        zzqc.endSection();
        zzhn zzhn = this.zzavd;
        zzhn.zzagj++;
        this.zzbji = 0;
        zzhq();
    }

    @TargetApi(21)
    private final void zza(MediaCodec mediaCodec, int i, long j, long j2) {
        zzhs();
        zzqc.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        zzqc.endSection();
        zzhn zzhn = this.zzavd;
        zzhn.zzagj++;
        this.zzbji = 0;
        zzhq();
    }

    private final boolean zzl(boolean z) {
        return zzqe.SDK_INT >= 23 && !this.zzadt && (!z || zzqk.zzb(this.zzsp));
    }

    private final void zzho() {
        this.zzbjf = this.zzbiv > 0 ? SystemClock.elapsedRealtime() + this.zzbiv : C.TIME_UNSET;
    }

    private final void zzhp() {
        this.zzbje = false;
        if (zzqe.SDK_INT >= 23 && this.zzadt) {
            MediaCodec zzel = zzel();
            if (zzel != null) {
                this.zzbjt = new zzqr(this, zzel);
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzhq() {
        if (!this.zzbje) {
            this.zzbje = true;
            this.zzbiu.zzb(this.zzbjb);
        }
    }

    private final void zzhr() {
        this.zzbjp = -1;
        this.zzbjq = -1;
        this.zzbjs = -1.0f;
        this.zzbjr = -1;
    }

    private final void zzhs() {
        if (this.zzbjp != this.zzbjl || this.zzbjq != this.zzbjm || this.zzbjr != this.zzbjn || this.zzbjs != this.zzbjo) {
            this.zzbiu.zzb(this.zzbjl, this.zzbjm, this.zzbjn, this.zzbjo);
            this.zzbjp = this.zzbjl;
            this.zzbjq = this.zzbjm;
            this.zzbjr = this.zzbjn;
            this.zzbjs = this.zzbjo;
        }
    }

    private final void zzht() {
        if (this.zzbjp != -1 || this.zzbjq != -1) {
            this.zzbiu.zzb(this.zzbjl, this.zzbjm, this.zzbjn, this.zzbjo);
        }
    }

    private final void zzhu() {
        if (this.zzbjh > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzbiu.zzi(this.zzbjh, elapsedRealtime - this.zzbjg);
            this.zzbjh = 0;
            this.zzbjg = elapsedRealtime;
        }
    }

    private static int zzj(zzfs zzfs) {
        if (zzfs.zzzk != -1) {
            return zzfs.zzzk;
        }
        return zza(zzfs.zzzj, zzfs.width, zzfs.height);
    }

    private static int zza(java.lang.String r5, int r6, int r7) {
        /*
        r0 = -1;
        if (r6 == r0) goto L_0x0078;
    L_0x0003:
        if (r7 != r0) goto L_0x0007;
    L_0x0005:
        goto L_0x0078;
    L_0x0007:
        r1 = r5.hashCode();
        r2 = 3;
        r3 = 4;
        r4 = 2;
        switch(r1) {
            case -1664118616: goto L_0x0044;
            case -1662541442: goto L_0x003a;
            case 1187890754: goto L_0x0030;
            case 1331836730: goto L_0x0026;
            case 1599127256: goto L_0x001c;
            case 1599127257: goto L_0x0012;
            default: goto L_0x0011;
        };
    L_0x0011:
        goto L_0x004e;
    L_0x0012:
        r1 = "video/x-vnd.on2.vp9";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x004e;
    L_0x001a:
        r5 = 5;
        goto L_0x004f;
    L_0x001c:
        r1 = "video/x-vnd.on2.vp8";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x004e;
    L_0x0024:
        r5 = r2;
        goto L_0x004f;
    L_0x0026:
        r1 = "video/avc";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x004e;
    L_0x002e:
        r5 = r4;
        goto L_0x004f;
    L_0x0030:
        r1 = "video/mp4v-es";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x004e;
    L_0x0038:
        r5 = 1;
        goto L_0x004f;
    L_0x003a:
        r1 = "video/hevc";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x004e;
    L_0x0042:
        r5 = r3;
        goto L_0x004f;
    L_0x0044:
        r1 = "video/3gpp";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x004e;
    L_0x004c:
        r5 = 0;
        goto L_0x004f;
    L_0x004e:
        r5 = r0;
    L_0x004f:
        switch(r5) {
            case 0: goto L_0x0072;
            case 1: goto L_0x0072;
            case 2: goto L_0x0057;
            case 3: goto L_0x0055;
            case 4: goto L_0x0053;
            case 5: goto L_0x0053;
            default: goto L_0x0052;
        };
    L_0x0052:
        return r0;
    L_0x0053:
        r6 = r6 * r7;
        goto L_0x0074;
    L_0x0055:
        r6 = r6 * r7;
        goto L_0x0073;
    L_0x0057:
        r5 = "BRAVIA 4K 2015";
        r1 = com.google.android.gms.internal.ads.zzqe.MODEL;
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0062;
    L_0x0061:
        return r0;
    L_0x0062:
        r5 = 16;
        r6 = com.google.android.gms.internal.ads.zzqe.zzf(r6, r5);
        r5 = com.google.android.gms.internal.ads.zzqe.zzf(r7, r5);
        r6 = r6 * r5;
        r5 = r6 << 4;
        r6 = r5 << 4;
        goto L_0x0073;
    L_0x0072:
        r6 = r6 * r7;
    L_0x0073:
        r3 = r4;
    L_0x0074:
        r6 = r6 * r2;
        r4 = r4 * r3;
        r6 = r6 / r4;
        return r6;
    L_0x0078:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqo.zza(java.lang.String, int, int):int");
    }

    private static boolean zza(boolean z, zzfs zzfs, zzfs zzfs2) {
        return zzfs.zzzj.equals(zzfs2.zzzj) && zzk(zzfs) == zzk(zzfs2) && (z || (zzfs.width == zzfs2.width && zzfs.height == zzfs2.height));
    }

    private static int zzk(zzfs zzfs) {
        return zzfs.zzzo == -1 ? 0 : zzfs.zzzo;
    }
}
