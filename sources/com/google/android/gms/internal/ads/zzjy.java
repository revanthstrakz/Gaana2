package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@TargetApi(16)
public abstract class zzjy extends zzfd {
    private static final byte[] zzatt = zzqe.zzan("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private zzfs zzaad;
    private ByteBuffer[] zzadk;
    private final zzka zzatu;
    private final zzhu<Object> zzatv;
    private final boolean zzatw;
    private final zzho zzatx;
    private final zzho zzaty;
    private final zzfu zzatz;
    private final List<Long> zzaua;
    private final BufferInfo zzaub;
    private zzhs<Object> zzauc;
    private zzhs<Object> zzaud;
    private MediaCodec zzaue;
    private zzjx zzauf;
    private boolean zzaug;
    private boolean zzauh;
    private boolean zzaui;
    private boolean zzauj;
    private boolean zzauk;
    private boolean zzaul;
    private boolean zzaum;
    private boolean zzaun;
    private boolean zzauo;
    private ByteBuffer[] zzaup;
    private long zzauq;
    private int zzaur;
    private int zzaus;
    private boolean zzaut;
    private boolean zzauu;
    private int zzauv;
    private int zzauw;
    private boolean zzaux;
    private boolean zzauy;
    private boolean zzauz;
    private boolean zzava;
    private boolean zzavb;
    private boolean zzavc;
    protected zzhn zzavd;

    public zzjy(int i, zzka zzka, zzhu<Object> zzhu, boolean z) {
        super(i);
        zzpo.checkState(zzqe.SDK_INT >= 16);
        this.zzatu = (zzka) zzpo.checkNotNull(zzka);
        this.zzatv = zzhu;
        this.zzatw = z;
        this.zzatx = new zzho(0);
        this.zzaty = new zzho(0);
        this.zzatz = new zzfu();
        this.zzaua = new ArrayList();
        this.zzaub = new BufferInfo();
        this.zzauv = 0;
        this.zzauw = 0;
    }

    /* Access modifiers changed, original: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzff {
    }

    /* Access modifiers changed, original: protected */
    public void onStarted() {
    }

    /* Access modifiers changed, original: protected */
    public void onStopped() {
    }

    public abstract int zza(zzka zzka, zzfs zzfs) throws zzke;

    /* Access modifiers changed, original: protected */
    public void zza(zzho zzho) {
    }

    public abstract void zza(zzjx zzjx, MediaCodec mediaCodec, zzfs zzfs, MediaCrypto mediaCrypto) throws zzke;

    public abstract boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws zzff;

    /* Access modifiers changed, original: protected */
    public boolean zza(MediaCodec mediaCodec, boolean z, zzfs zzfs, zzfs zzfs2) {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(zzjx zzjx) {
        return true;
    }

    public final int zzbl() {
        return 4;
    }

    /* Access modifiers changed, original: protected */
    public void zzc(String str, long j, long j2) {
    }

    /* Access modifiers changed, original: protected */
    public void zzdj() throws zzff {
    }

    public final int zzb(zzfs zzfs) throws zzff {
        try {
            return zza(this.zzatu, zzfs);
        } catch (zzke e) {
            throw zzff.zza(e, getIndex());
        }
    }

    /* Access modifiers changed, original: protected */
    public zzjx zza(zzka zzka, zzfs zzfs, boolean z) throws zzke {
        return zzka.zzb(zzfs.zzzj, z);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzek() throws zzff {
        if (this.zzaue == null && this.zzaad != null) {
            this.zzauc = this.zzaud;
            String str = this.zzaad.zzzj;
            if (this.zzauc != null) {
                int state = this.zzauc.getState();
                if (state == 0) {
                    throw zzff.zza(this.zzauc.zzdv(), getIndex());
                } else if (state == 3 || state == 4) {
                    this.zzauc.zzdu();
                    throw new NoSuchMethodError();
                } else {
                    return;
                }
            }
            if (this.zzauf == null) {
                try {
                    this.zzauf = zza(this.zzatu, this.zzaad, false);
                } catch (zzke e) {
                    zza(new zzjz(this.zzaad, e, false, -49998));
                }
                if (this.zzauf == null) {
                    zza(new zzjz(this.zzaad, null, false, -49999));
                }
            }
            if (zza(this.zzauf)) {
                str = this.zzauf.name;
                boolean z = zzqe.SDK_INT < 21 && this.zzaad.zzzl.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
                this.zzaug = z;
                z = zzqe.SDK_INT < 18 || ((zzqe.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (zzqe.SDK_INT == 19 && zzqe.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str))));
                this.zzauh = z;
                z = zzqe.SDK_INT < 24 && (("OMX.Nvidia.h264.decode".equals(str) || "OMX.Nvidia.h264.decode.secure".equals(str)) && ("flounder".equals(zzqe.DEVICE) || "flounder_lte".equals(zzqe.DEVICE) || "grouper".equals(zzqe.DEVICE) || "tilapia".equals(zzqe.DEVICE)));
                this.zzaui = z;
                z = zzqe.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str));
                this.zzauj = z;
                z = (zzqe.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (zzqe.SDK_INT <= 19 && "hb2000".equals(zzqe.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
                this.zzauk = z;
                z = zzqe.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
                this.zzaul = z;
                z = zzqe.SDK_INT <= 18 && this.zzaad.zzzt == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
                this.zzaum = z;
                try {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    String str2 = "createCodec:";
                    String valueOf = String.valueOf(str);
                    zzqc.beginSection(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    this.zzaue = MediaCodec.createByCodecName(str);
                    zzqc.endSection();
                    zzqc.beginSection("configureCodec");
                    zza(this.zzauf, this.zzaue, this.zzaad, null);
                    zzqc.endSection();
                    zzqc.beginSection("startCodec");
                    this.zzaue.start();
                    zzqc.endSection();
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    zzc(str, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
                    this.zzaup = this.zzaue.getInputBuffers();
                    this.zzadk = this.zzaue.getOutputBuffers();
                } catch (Exception e2) {
                    zza(new zzjz(this.zzaad, e2, false, str));
                }
                this.zzauq = getState() == 2 ? SystemClock.elapsedRealtime() + 1000 : C.TIME_UNSET;
                this.zzaur = -1;
                this.zzaus = -1;
                this.zzavc = true;
                zzhn zzhn = this.zzavd;
                zzhn.zzagg++;
            }
        }
    }

    private final void zza(zzjz zzjz) throws zzff {
        throw zzff.zza(zzjz, getIndex());
    }

    /* Access modifiers changed, original: protected|final */
    public final MediaCodec zzel() {
        return this.zzaue;
    }

    /* Access modifiers changed, original: protected|final */
    public final zzjx zzem() {
        return this.zzauf;
    }

    /* Access modifiers changed, original: protected */
    public void zzb(boolean z) throws zzff {
        this.zzavd = new zzhn();
    }

    /* Access modifiers changed, original: protected */
    public void zza(long j, boolean z) throws zzff {
        this.zzauz = false;
        this.zzava = false;
        if (this.zzaue != null) {
            this.zzauq = C.TIME_UNSET;
            this.zzaur = -1;
            this.zzaus = -1;
            this.zzavc = true;
            this.zzavb = false;
            this.zzaut = false;
            this.zzaua.clear();
            this.zzaun = false;
            this.zzauo = false;
            if (this.zzauh || (this.zzauk && this.zzauy)) {
                zzen();
                zzek();
            } else if (this.zzauw != 0) {
                zzen();
                zzek();
            } else {
                this.zzaue.flush();
                this.zzaux = false;
            }
            if (this.zzauu && this.zzaad != null) {
                this.zzauv = 1;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzbm() {
        this.zzaad = null;
        try {
            zzen();
            try {
                if (this.zzauc != null) {
                    this.zzatv.zza(this.zzauc);
                }
                try {
                    if (!(this.zzaud == null || this.zzaud == this.zzauc)) {
                        this.zzatv.zza(this.zzaud);
                    }
                    this.zzauc = null;
                    this.zzaud = null;
                } catch (Throwable th) {
                    this.zzauc = null;
                    this.zzaud = null;
                }
            } catch (Throwable th2) {
                this.zzauc = null;
                this.zzaud = null;
            }
        } catch (Throwable th3) {
            this.zzauc = null;
            this.zzaud = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzen() {
        this.zzauq = C.TIME_UNSET;
        this.zzaur = -1;
        this.zzaus = -1;
        this.zzavb = false;
        this.zzaut = false;
        this.zzaua.clear();
        this.zzaup = null;
        this.zzadk = null;
        this.zzauf = null;
        this.zzauu = false;
        this.zzaux = false;
        this.zzaug = false;
        this.zzauh = false;
        this.zzaui = false;
        this.zzauj = false;
        this.zzauk = false;
        this.zzaum = false;
        this.zzaun = false;
        this.zzauo = false;
        this.zzauy = false;
        this.zzauv = 0;
        this.zzauw = 0;
        this.zzatx.zzdd = null;
        if (this.zzaue != null) {
            zzhn zzhn = this.zzavd;
            zzhn.zzagh++;
            try {
                this.zzaue.stop();
                try {
                    this.zzaue.release();
                    this.zzaue = null;
                    if (this.zzauc != null && this.zzaud != this.zzauc) {
                        try {
                            this.zzatv.zza(this.zzauc);
                        } finally {
                            this.zzauc = null;
                        }
                    }
                } catch (Throwable th) {
                    this.zzaue = null;
                    if (this.zzauc != null && this.zzaud != this.zzauc) {
                        this.zzatv.zza(this.zzauc);
                    }
                } finally {
                    this.zzauc = null;
                }
            } catch (Throwable th2) {
                this.zzaue = null;
                if (this.zzauc != null && this.zzaud != this.zzauc) {
                    try {
                        this.zzatv.zza(this.zzauc);
                    } catch (Throwable th3) {
                        this.zzauc = null;
                    }
                }
            } finally {
                this.zzauc = null;
            }
        }
    }

    public final void zzb(long j, long j2) throws zzff {
        if (this.zzava) {
            zzdj();
            return;
        }
        if (this.zzaad == null) {
            this.zzaty.clear();
            int zza = zza(this.zzatz, this.zzaty, true);
            if (zza == -5) {
                zze(this.zzatz.zzaad);
            } else if (zza == -4) {
                zzpo.checkState(this.zzaty.zzdp());
                this.zzauz = true;
                zzep();
                return;
            } else {
                return;
            }
        }
        zzek();
        if (this.zzaue != null) {
            zzqc.beginSection("drainAndFeed");
            do {
            } while (zzd(j, j2));
            do {
            } while (zzeo());
            zzqc.endSection();
        } else {
            zze(j);
            this.zzaty.clear();
            int zza2 = zza(this.zzatz, this.zzaty, false);
            if (zza2 == -5) {
                zze(this.zzatz.zzaad);
            } else if (zza2 == -4) {
                zzpo.checkState(this.zzaty.zzdp());
                this.zzauz = true;
                zzep();
            }
        }
        this.zzavd.zzds();
    }

    /* JADX WARNING: Removed duplicated region for block: B:85:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0153 A:{RETURN} */
    private final boolean zzeo() throws com.google.android.gms.internal.ads.zzff {
        /*
        r14 = this;
        r0 = r14.zzaue;
        r1 = 0;
        if (r0 == 0) goto L_0x01de;
    L_0x0005:
        r0 = r14.zzauw;
        r2 = 2;
        if (r0 == r2) goto L_0x01de;
    L_0x000a:
        r0 = r14.zzauz;
        if (r0 == 0) goto L_0x0010;
    L_0x000e:
        goto L_0x01de;
    L_0x0010:
        r0 = r14.zzaur;
        if (r0 >= 0) goto L_0x0032;
    L_0x0014:
        r0 = r14.zzaue;
        r3 = 0;
        r0 = r0.dequeueInputBuffer(r3);
        r14.zzaur = r0;
        r0 = r14.zzaur;
        if (r0 >= 0) goto L_0x0023;
    L_0x0022:
        return r1;
    L_0x0023:
        r0 = r14.zzatx;
        r3 = r14.zzaup;
        r4 = r14.zzaur;
        r3 = r3[r4];
        r0.zzdd = r3;
        r0 = r14.zzatx;
        r0.clear();
    L_0x0032:
        r0 = r14.zzauw;
        r3 = -1;
        r4 = 1;
        if (r0 != r4) goto L_0x004f;
    L_0x0038:
        r0 = r14.zzauj;
        if (r0 != 0) goto L_0x004c;
    L_0x003c:
        r14.zzauy = r4;
        r5 = r14.zzaue;
        r6 = r14.zzaur;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r11 = 4;
        r5.queueInputBuffer(r6, r7, r8, r9, r11);
        r14.zzaur = r3;
    L_0x004c:
        r14.zzauw = r2;
        return r1;
    L_0x004f:
        r0 = r14.zzaun;
        if (r0 == 0) goto L_0x0071;
    L_0x0053:
        r14.zzaun = r1;
        r0 = r14.zzatx;
        r0 = r0.zzdd;
        r1 = zzatt;
        r0.put(r1);
        r5 = r14.zzaue;
        r6 = r14.zzaur;
        r7 = 0;
        r0 = zzatt;
        r8 = r0.length;
        r9 = 0;
        r11 = 0;
        r5.queueInputBuffer(r6, r7, r8, r9, r11);
        r14.zzaur = r3;
        r14.zzaux = r4;
        return r4;
    L_0x0071:
        r0 = r14.zzavb;
        if (r0 == 0) goto L_0x0078;
    L_0x0075:
        r0 = -4;
        r5 = r1;
        goto L_0x00b0;
    L_0x0078:
        r0 = r14.zzauv;
        if (r0 != r4) goto L_0x009d;
    L_0x007c:
        r0 = r1;
    L_0x007d:
        r5 = r14.zzaad;
        r5 = r5.zzzl;
        r5 = r5.size();
        if (r0 >= r5) goto L_0x009b;
    L_0x0087:
        r5 = r14.zzaad;
        r5 = r5.zzzl;
        r5 = r5.get(r0);
        r5 = (byte[]) r5;
        r6 = r14.zzatx;
        r6 = r6.zzdd;
        r6.put(r5);
        r0 = r0 + 1;
        goto L_0x007d;
    L_0x009b:
        r14.zzauv = r2;
    L_0x009d:
        r0 = r14.zzatx;
        r0 = r0.zzdd;
        r0 = r0.position();
        r5 = r14.zzatz;
        r6 = r14.zzatx;
        r5 = r14.zza(r5, r6, r1);
        r13 = r5;
        r5 = r0;
        r0 = r13;
    L_0x00b0:
        r6 = -3;
        if (r0 != r6) goto L_0x00b4;
    L_0x00b3:
        return r1;
    L_0x00b4:
        r6 = -5;
        if (r0 != r6) goto L_0x00ca;
    L_0x00b7:
        r0 = r14.zzauv;
        if (r0 != r2) goto L_0x00c2;
    L_0x00bb:
        r0 = r14.zzatx;
        r0.clear();
        r14.zzauv = r4;
    L_0x00c2:
        r0 = r14.zzatz;
        r0 = r0.zzaad;
        r14.zze(r0);
        return r4;
    L_0x00ca:
        r0 = r14.zzatx;
        r0 = r0.zzdp();
        if (r0 == 0) goto L_0x0106;
    L_0x00d2:
        r0 = r14.zzauv;
        if (r0 != r2) goto L_0x00dd;
    L_0x00d6:
        r0 = r14.zzatx;
        r0.clear();
        r14.zzauv = r4;
    L_0x00dd:
        r14.zzauz = r4;
        r0 = r14.zzaux;
        if (r0 != 0) goto L_0x00e7;
    L_0x00e3:
        r14.zzep();
        return r1;
    L_0x00e7:
        r0 = r14.zzauj;	 Catch:{ CryptoException -> 0x00fc }
        if (r0 != 0) goto L_0x00fb;
    L_0x00eb:
        r14.zzauy = r4;	 Catch:{ CryptoException -> 0x00fc }
        r5 = r14.zzaue;	 Catch:{ CryptoException -> 0x00fc }
        r6 = r14.zzaur;	 Catch:{ CryptoException -> 0x00fc }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r11 = 4;
        r5.queueInputBuffer(r6, r7, r8, r9, r11);	 Catch:{ CryptoException -> 0x00fc }
        r14.zzaur = r3;	 Catch:{ CryptoException -> 0x00fc }
    L_0x00fb:
        return r1;
    L_0x00fc:
        r0 = move-exception;
        r1 = r14.getIndex();
        r0 = com.google.android.gms.internal.ads.zzff.zza(r0, r1);
        throw r0;
    L_0x0106:
        r0 = r14.zzavc;
        if (r0 == 0) goto L_0x011e;
    L_0x010a:
        r0 = r14.zzatx;
        r0 = r0.zzdq();
        if (r0 != 0) goto L_0x011e;
    L_0x0112:
        r0 = r14.zzatx;
        r0.clear();
        r0 = r14.zzauv;
        if (r0 != r2) goto L_0x011d;
    L_0x011b:
        r14.zzauv = r4;
    L_0x011d:
        return r4;
    L_0x011e:
        r14.zzavc = r1;
        r0 = r14.zzatx;
        r0 = r0.zzdt();
        r2 = r14.zzauc;
        if (r2 == 0) goto L_0x014c;
    L_0x012a:
        r2 = r14.zzauc;
        r2 = r2.getState();
        if (r2 != 0) goto L_0x0141;
    L_0x0132:
        r0 = r14.zzauc;
        r0 = r0.zzdv();
        r1 = r14.getIndex();
        r0 = com.google.android.gms.internal.ads.zzff.zza(r0, r1);
        throw r0;
    L_0x0141:
        r6 = 4;
        if (r2 == r6) goto L_0x014c;
    L_0x0144:
        if (r0 != 0) goto L_0x014a;
    L_0x0146:
        r2 = r14.zzatw;
        if (r2 != 0) goto L_0x014c;
    L_0x014a:
        r2 = r4;
        goto L_0x014d;
    L_0x014c:
        r2 = r1;
    L_0x014d:
        r14.zzavb = r2;
        r2 = r14.zzavb;
        if (r2 == 0) goto L_0x0154;
    L_0x0153:
        return r1;
    L_0x0154:
        r2 = r14.zzaug;
        if (r2 == 0) goto L_0x016e;
    L_0x0158:
        if (r0 != 0) goto L_0x016e;
    L_0x015a:
        r2 = r14.zzatx;
        r2 = r2.zzdd;
        com.google.android.gms.internal.ads.zzpu.zzk(r2);
        r2 = r14.zzatx;
        r2 = r2.zzdd;
        r2 = r2.position();
        if (r2 != 0) goto L_0x016c;
    L_0x016b:
        return r4;
    L_0x016c:
        r14.zzaug = r1;
    L_0x016e:
        r2 = r14.zzatx;	 Catch:{ CryptoException -> 0x01d4 }
        r10 = r2.zzago;	 Catch:{ CryptoException -> 0x01d4 }
        r2 = r14.zzatx;	 Catch:{ CryptoException -> 0x01d4 }
        r2 = r2.zzdo();	 Catch:{ CryptoException -> 0x01d4 }
        if (r2 == 0) goto L_0x0183;
    L_0x017a:
        r2 = r14.zzaua;	 Catch:{ CryptoException -> 0x01d4 }
        r6 = java.lang.Long.valueOf(r10);	 Catch:{ CryptoException -> 0x01d4 }
        r2.add(r6);	 Catch:{ CryptoException -> 0x01d4 }
    L_0x0183:
        r2 = r14.zzatx;	 Catch:{ CryptoException -> 0x01d4 }
        r2 = r2.zzdd;	 Catch:{ CryptoException -> 0x01d4 }
        r2.flip();	 Catch:{ CryptoException -> 0x01d4 }
        r2 = r14.zzatx;	 Catch:{ CryptoException -> 0x01d4 }
        r14.zza(r2);	 Catch:{ CryptoException -> 0x01d4 }
        if (r0 == 0) goto L_0x01b5;
    L_0x0191:
        r0 = r14.zzatx;	 Catch:{ CryptoException -> 0x01d4 }
        r0 = r0.zzagn;	 Catch:{ CryptoException -> 0x01d4 }
        r9 = r0.zzdr();	 Catch:{ CryptoException -> 0x01d4 }
        if (r5 != 0) goto L_0x019c;
    L_0x019b:
        goto L_0x01ab;
    L_0x019c:
        r0 = r9.numBytesOfClearData;	 Catch:{ CryptoException -> 0x01d4 }
        if (r0 != 0) goto L_0x01a4;
    L_0x01a0:
        r0 = new int[r4];	 Catch:{ CryptoException -> 0x01d4 }
        r9.numBytesOfClearData = r0;	 Catch:{ CryptoException -> 0x01d4 }
    L_0x01a4:
        r0 = r9.numBytesOfClearData;	 Catch:{ CryptoException -> 0x01d4 }
        r2 = r0[r1];	 Catch:{ CryptoException -> 0x01d4 }
        r2 = r2 + r5;
        r0[r1] = r2;	 Catch:{ CryptoException -> 0x01d4 }
    L_0x01ab:
        r6 = r14.zzaue;	 Catch:{ CryptoException -> 0x01d4 }
        r7 = r14.zzaur;	 Catch:{ CryptoException -> 0x01d4 }
        r8 = 0;
        r12 = 0;
        r6.queueSecureInputBuffer(r7, r8, r9, r10, r12);	 Catch:{ CryptoException -> 0x01d4 }
        goto L_0x01c6;
    L_0x01b5:
        r6 = r14.zzaue;	 Catch:{ CryptoException -> 0x01d4 }
        r7 = r14.zzaur;	 Catch:{ CryptoException -> 0x01d4 }
        r8 = 0;
        r0 = r14.zzatx;	 Catch:{ CryptoException -> 0x01d4 }
        r0 = r0.zzdd;	 Catch:{ CryptoException -> 0x01d4 }
        r9 = r0.limit();	 Catch:{ CryptoException -> 0x01d4 }
        r12 = 0;
        r6.queueInputBuffer(r7, r8, r9, r10, r12);	 Catch:{ CryptoException -> 0x01d4 }
    L_0x01c6:
        r14.zzaur = r3;	 Catch:{ CryptoException -> 0x01d4 }
        r14.zzaux = r4;	 Catch:{ CryptoException -> 0x01d4 }
        r14.zzauv = r1;	 Catch:{ CryptoException -> 0x01d4 }
        r0 = r14.zzavd;	 Catch:{ CryptoException -> 0x01d4 }
        r1 = r0.zzagi;	 Catch:{ CryptoException -> 0x01d4 }
        r1 = r1 + r4;
        r0.zzagi = r1;	 Catch:{ CryptoException -> 0x01d4 }
        return r4;
    L_0x01d4:
        r0 = move-exception;
        r1 = r14.getIndex();
        r0 = com.google.android.gms.internal.ads.zzff.zza(r0, r1);
        throw r0;
    L_0x01de:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjy.zzeo():boolean");
    }

    /* Access modifiers changed, original: protected */
    public void zze(zzfs zzfs) throws zzff {
        Object obj;
        zzfs zzfs2 = this.zzaad;
        this.zzaad = zzfs;
        Object obj2 = this.zzaad.zzzm;
        if (zzfs2 == null) {
            obj = null;
        } else {
            obj = zzfs2.zzzm;
        }
        boolean zza = zzqe.zza(obj2, obj);
        boolean z = true;
        if ((zza ^ 1) != 0) {
            if (this.zzaad.zzzm == null) {
                this.zzaud = null;
            } else if (this.zzatv == null) {
                throw zzff.zza(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
            } else {
                this.zzaud = this.zzatv.zza(Looper.myLooper(), this.zzaad.zzzm);
                if (this.zzaud == this.zzauc) {
                    this.zzatv.zza(this.zzaud);
                }
            }
        }
        if (this.zzaud == this.zzauc && this.zzaue != null && zza(this.zzaue, this.zzauf.zzatq, zzfs2, this.zzaad)) {
            this.zzauu = true;
            this.zzauv = 1;
            if (!(this.zzaui && this.zzaad.width == zzfs2.width && this.zzaad.height == zzfs2.height)) {
                z = false;
            }
            this.zzaun = z;
        } else if (this.zzaux) {
            this.zzauw = 1;
        } else {
            zzen();
            zzek();
        }
    }

    public boolean zzcj() {
        return this.zzava;
    }

    public boolean isReady() {
        return (this.zzaad == null || this.zzavb || (!zzbo() && this.zzaus < 0 && (this.zzauq == C.TIME_UNSET || SystemClock.elapsedRealtime() >= this.zzauq))) ? false : true;
    }

    private final boolean zzd(long j, long j2) throws zzff {
        long j3;
        boolean z;
        if (this.zzaus < 0) {
            if (this.zzaul && this.zzauy) {
                try {
                    this.zzaus = this.zzaue.dequeueOutputBuffer(this.zzaub, 0);
                } catch (IllegalStateException unused) {
                    zzep();
                    if (this.zzava) {
                        zzen();
                    }
                    return false;
                }
            }
            this.zzaus = this.zzaue.dequeueOutputBuffer(this.zzaub, 0);
            if (this.zzaus >= 0) {
                if (this.zzauo) {
                    this.zzauo = false;
                    this.zzaue.releaseOutputBuffer(this.zzaus, false);
                    this.zzaus = -1;
                    return true;
                } else if ((this.zzaub.flags & 4) != 0) {
                    zzep();
                    this.zzaus = -1;
                    return false;
                } else {
                    ByteBuffer byteBuffer = this.zzadk[this.zzaus];
                    if (byteBuffer != null) {
                        byteBuffer.position(this.zzaub.offset);
                        byteBuffer.limit(this.zzaub.offset + this.zzaub.size);
                    }
                    j3 = this.zzaub.presentationTimeUs;
                    int size = this.zzaua.size();
                    for (int i = 0; i < size; i++) {
                        if (((Long) this.zzaua.get(i)).longValue() == j3) {
                            this.zzaua.remove(i);
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    this.zzaut = z;
                }
            } else if (this.zzaus == -2) {
                MediaFormat outputFormat = this.zzaue.getOutputFormat();
                if (this.zzaui && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
                    this.zzauo = true;
                } else {
                    if (this.zzaum) {
                        outputFormat.setInteger("channel-count", 1);
                    }
                    onOutputFormatChanged(this.zzaue, outputFormat);
                }
                return true;
            } else if (this.zzaus == -3) {
                this.zzadk = this.zzaue.getOutputBuffers();
                return true;
            } else {
                if (this.zzauj && (this.zzauz || this.zzauw == 2)) {
                    zzep();
                }
                return false;
            }
        }
        if (this.zzaul && this.zzauy) {
            try {
                z = zza(j, j2, this.zzaue, this.zzadk[this.zzaus], this.zzaus, this.zzaub.flags, this.zzaub.presentationTimeUs, this.zzaut);
            } catch (IllegalStateException unused2) {
                zzep();
                if (this.zzava) {
                    zzen();
                }
                return false;
            }
        }
        z = zza(j, j2, this.zzaue, this.zzadk[this.zzaus], this.zzaus, this.zzaub.flags, this.zzaub.presentationTimeUs, this.zzaut);
        if (!z) {
            return false;
        }
        j3 = this.zzaub.presentationTimeUs;
        this.zzaus = -1;
        return true;
    }

    private final void zzep() throws zzff {
        if (this.zzauw == 2) {
            zzen();
            zzek();
            return;
        }
        this.zzava = true;
        zzdj();
    }
}
