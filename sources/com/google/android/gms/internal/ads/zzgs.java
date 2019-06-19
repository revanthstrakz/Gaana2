package com.google.android.gms.internal.ads;

import android.media.AudioAttributes.Builder;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

public final class zzgs {
    private static boolean zzabu;
    private static boolean zzabv;
    private int streamType;
    private final zzgh zzabw = null;
    private final zzhb zzabx;
    private final zzhi zzaby;
    private final zzgi[] zzabz;
    private final zzgy zzaca;
    private final ConditionVariable zzacb;
    private final long[] zzacc;
    private final zzgu zzacd;
    private final LinkedList<zzgz> zzace;
    private AudioTrack zzacf;
    private int zzacg;
    private int zzach;
    private int zzaci;
    private boolean zzacj;
    private int zzack;
    private long zzacl;
    private zzfy zzacm;
    private long zzacn;
    private long zzaco;
    private ByteBuffer zzacp;
    private int zzacq;
    private int zzacr;
    private int zzacs;
    private long zzact;
    private long zzacu;
    private boolean zzacv;
    private long zzacw;
    private Method zzacx;
    private int zzacy;
    private long zzacz;
    private long zzada;
    private int zzadb;
    private long zzadc;
    private long zzadd;
    private int zzade;
    private int zzadf;
    private long zzadg;
    private long zzadh;
    private long zzadi;
    private zzgi[] zzadj;
    private ByteBuffer[] zzadk;
    private ByteBuffer zzadl;
    private ByteBuffer zzadm;
    private byte[] zzadn;
    private int zzado;
    private int zzadp;
    private boolean zzadq;
    private boolean zzadr;
    private int zzads;
    private boolean zzadt;
    private boolean zzadu;
    private long zzadv;
    private float zzcu;
    private zzfy zzxm;
    private int zzzu;

    public zzgs(zzgh zzgh, zzgi[] zzgiArr, zzgy zzgy) {
        this.zzaca = zzgy;
        this.zzacb = new ConditionVariable(true);
        if (zzqe.SDK_INT >= 18) {
            try {
                this.zzacx = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        if (zzqe.SDK_INT >= 19) {
            this.zzacd = new zzgv();
        } else {
            this.zzacd = new zzgu();
        }
        this.zzabx = new zzhb();
        this.zzaby = new zzhi();
        this.zzabz = new zzgi[(zzgiArr.length + 3)];
        this.zzabz[0] = new zzhg();
        this.zzabz[1] = this.zzabx;
        System.arraycopy(zzgiArr, 0, this.zzabz, 2, zzgiArr.length);
        this.zzabz[2 + zzgiArr.length] = this.zzaby;
        this.zzacc = new long[10];
        this.zzcu = 1.0f;
        this.zzadf = 0;
        this.streamType = 3;
        this.zzads = 0;
        this.zzxm = zzfy.zzaaf;
        this.zzadp = -1;
        this.zzadj = new zzgi[0];
        this.zzadk = new ByteBuffer[0];
        this.zzace = new LinkedList();
    }

    public final boolean zzq(String str) {
        return this.zzabw != null && this.zzabw.zzk(zzr(str));
    }

    public final long zzg(boolean z) {
        int i = (!isInitialized() || this.zzadf == 0) ? false : 1;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        long zzde;
        long zzdg;
        long j;
        if (this.zzacf.getPlayState() == 3) {
            zzde = this.zzacd.zzde();
            if (zzde != 0) {
                long nanoTime = System.nanoTime() / 1000;
                if (nanoTime - this.zzacu >= 30000) {
                    this.zzacc[this.zzacr] = zzde - nanoTime;
                    this.zzacr = (this.zzacr + 1) % 10;
                    if (this.zzacs < 10) {
                        this.zzacs++;
                    }
                    this.zzacu = nanoTime;
                    this.zzact = 0;
                    for (i = 0; i < this.zzacs; i++) {
                        this.zzact += this.zzacc[i] / ((long) this.zzacs);
                    }
                }
                if (!zzdc() && nanoTime - this.zzacw >= 500000) {
                    this.zzacv = this.zzacd.zzdf();
                    if (this.zzacv) {
                        zzdg = this.zzacd.zzdg() / 1000;
                        long zzdh = this.zzacd.zzdh();
                        StringBuilder stringBuilder;
                        if (zzdg < this.zzadh) {
                            this.zzacv = false;
                        } else if (Math.abs(zzdg - nanoTime) > 5000000) {
                            stringBuilder = new StringBuilder(136);
                            stringBuilder.append("Spurious audio timestamp (system clock mismatch): ");
                            stringBuilder.append(zzdh);
                            stringBuilder.append(", ");
                            stringBuilder.append(zzdg);
                            stringBuilder.append(", ");
                            stringBuilder.append(nanoTime);
                            stringBuilder.append(", ");
                            stringBuilder.append(zzde);
                            Log.w("AudioTrack", stringBuilder.toString());
                            this.zzacv = false;
                        } else {
                            long j2 = nanoTime;
                            if (Math.abs(zzn(zzdh) - zzde) > 5000000) {
                                stringBuilder = new StringBuilder(TsExtractor.TS_STREAM_TYPE_DTS);
                                stringBuilder.append("Spurious audio timestamp (frame position mismatch): ");
                                stringBuilder.append(zzdh);
                                stringBuilder.append(", ");
                                stringBuilder.append(zzdg);
                                stringBuilder.append(", ");
                                nanoTime = j2;
                                stringBuilder.append(nanoTime);
                                stringBuilder.append(", ");
                                stringBuilder.append(zzde);
                                Log.w("AudioTrack", stringBuilder.toString());
                                this.zzacv = false;
                            } else {
                                nanoTime = j2;
                            }
                        }
                    }
                    if (!(this.zzacx == null || this.zzacj)) {
                        try {
                            this.zzadi = (((long) ((Integer) this.zzacx.invoke(this.zzacf, null)).intValue()) * 1000) - this.zzacl;
                            this.zzadi = Math.max(this.zzadi, 0);
                            if (this.zzadi > 5000000) {
                                j = this.zzadi;
                                StringBuilder stringBuilder2 = new StringBuilder(61);
                                stringBuilder2.append("Ignoring impossibly large audio latency: ");
                                stringBuilder2.append(j);
                                Log.w("AudioTrack", stringBuilder2.toString());
                                this.zzadi = 0;
                            }
                        } catch (Exception unused) {
                            this.zzacx = null;
                        }
                    }
                    this.zzacw = nanoTime;
                }
            }
        }
        zzdg = System.nanoTime() / 1000;
        if (this.zzacv) {
            zzdg = zzn(this.zzacd.zzdh() + zzo(zzdg - (this.zzacd.zzdg() / 1000)));
        } else {
            long zzde2;
            if (this.zzacs == 0) {
                zzde2 = this.zzacd.zzde();
            } else {
                zzde2 = zzdg + this.zzact;
            }
            zzdg = !z ? zzde2 - this.zzadi : zzde2;
        }
        j = this.zzadg;
        while (!this.zzace.isEmpty() && zzdg >= ((zzgz) this.zzace.getFirst()).zzyz) {
            zzgz zzgz = (zzgz) this.zzace.remove();
            this.zzxm = zzgz.zzxm;
            this.zzaco = zzgz.zzyz;
            this.zzacn = zzgz.zzaek - this.zzadg;
        }
        if (this.zzxm.zzaag == 1.0f) {
            zzde = (zzdg + this.zzacn) - this.zzaco;
        } else if (!this.zzace.isEmpty() || this.zzaby.zzdn() < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            zzde = this.zzacn + ((long) (((double) this.zzxm.zzaag) * ((double) (zzdg - this.zzaco))));
        } else {
            zzde = this.zzacn + zzqe.zza(zzdg - this.zzaco, this.zzaby.zzdm(), this.zzaby.zzdn());
        }
        return j + zzde;
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x013a  */
    public final void zza(java.lang.String r8, int r9, int r10, int r11, int r12, int[] r13) throws com.google.android.gms.internal.ads.zzgw {
        /*
        r7 = this;
        r12 = "audio/raw";
        r12 = r12.equals(r8);
        r0 = 1;
        r12 = r12 ^ r0;
        if (r12 == 0) goto L_0x000f;
    L_0x000a:
        r8 = zzr(r8);
        goto L_0x0010;
    L_0x000f:
        r8 = r11;
    L_0x0010:
        r1 = 0;
        if (r12 != 0) goto L_0x004f;
    L_0x0013:
        r11 = com.google.android.gms.internal.ads.zzqe.zzg(r11, r9);
        r7.zzacy = r11;
        r11 = r7.zzabx;
        r11.zzb(r13);
        r11 = r7.zzabz;
        r13 = r11.length;
        r3 = r8;
        r2 = r9;
        r8 = r1;
        r9 = r8;
    L_0x0025:
        if (r8 >= r13) goto L_0x0046;
    L_0x0027:
        r4 = r11[r8];
        r5 = r4.zzb(r10, r2, r3);	 Catch:{ zzgj -> 0x003f }
        r9 = r9 | r5;
        r5 = r4.isActive();
        if (r5 == 0) goto L_0x003c;
    L_0x0034:
        r2 = r4.zzco();
        r3 = r4.zzcp();
    L_0x003c:
        r8 = r8 + 1;
        goto L_0x0025;
    L_0x003f:
        r8 = move-exception;
        r9 = new com.google.android.gms.internal.ads.zzgw;
        r9.<init>(r8);
        throw r9;
    L_0x0046:
        if (r9 == 0) goto L_0x004b;
    L_0x0048:
        r7.zzcs();
    L_0x004b:
        r11 = r9;
        r9 = r2;
        r8 = r3;
        goto L_0x0050;
    L_0x004f:
        r11 = r1;
    L_0x0050:
        r13 = 12;
        r2 = 252; // 0xfc float:3.53E-43 double:1.245E-321;
        switch(r9) {
            case 1: goto L_0x0083;
            case 2: goto L_0x0081;
            case 3: goto L_0x007e;
            case 4: goto L_0x007b;
            case 5: goto L_0x0078;
            case 6: goto L_0x0076;
            case 7: goto L_0x0073;
            case 8: goto L_0x0070;
            default: goto L_0x0057;
        };
    L_0x0057:
        r8 = new com.google.android.gms.internal.ads.zzgw;
        r10 = 38;
        r11 = new java.lang.StringBuilder;
        r11.<init>(r10);
        r10 = "Unsupported channel count: ";
        r11.append(r10);
        r11.append(r9);
        r9 = r11.toString();
        r8.<init>(r9);
        throw r8;
    L_0x0070:
        r3 = com.google.android.gms.internal.ads.zzfe.CHANNEL_OUT_7POINT1_SURROUND;
        goto L_0x0084;
    L_0x0073:
        r3 = 1276; // 0x4fc float:1.788E-42 double:6.304E-321;
        goto L_0x0084;
    L_0x0076:
        r3 = r2;
        goto L_0x0084;
    L_0x0078:
        r3 = 220; // 0xdc float:3.08E-43 double:1.087E-321;
        goto L_0x0084;
    L_0x007b:
        r3 = 204; // 0xcc float:2.86E-43 double:1.01E-321;
        goto L_0x0084;
    L_0x007e:
        r3 = 28;
        goto L_0x0084;
    L_0x0081:
        r3 = r13;
        goto L_0x0084;
    L_0x0083:
        r3 = 4;
    L_0x0084:
        r4 = com.google.android.gms.internal.ads.zzqe.SDK_INT;
        r5 = 23;
        r6 = 5;
        if (r4 > r5) goto L_0x00ab;
    L_0x008b:
        r4 = "foster";
        r5 = com.google.android.gms.internal.ads.zzqe.DEVICE;
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x00ab;
    L_0x0095:
        r4 = "NVIDIA";
        r5 = com.google.android.gms.internal.ads.zzqe.MANUFACTURER;
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x00ab;
    L_0x009f:
        r4 = 3;
        if (r9 == r4) goto L_0x00ac;
    L_0x00a2:
        if (r9 == r6) goto L_0x00ac;
    L_0x00a4:
        r2 = 7;
        if (r9 == r2) goto L_0x00a8;
    L_0x00a7:
        goto L_0x00ab;
    L_0x00a8:
        r2 = com.google.android.gms.internal.ads.zzfe.CHANNEL_OUT_7POINT1_SURROUND;
        goto L_0x00ac;
    L_0x00ab:
        r2 = r3;
    L_0x00ac:
        r3 = com.google.android.gms.internal.ads.zzqe.SDK_INT;
        r4 = 25;
        if (r3 > r4) goto L_0x00c1;
    L_0x00b2:
        r3 = "fugu";
        r4 = com.google.android.gms.internal.ads.zzqe.DEVICE;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c1;
    L_0x00bc:
        if (r12 == 0) goto L_0x00c1;
    L_0x00be:
        if (r9 != r0) goto L_0x00c1;
    L_0x00c0:
        goto L_0x00c2;
    L_0x00c1:
        r13 = r2;
    L_0x00c2:
        if (r11 != 0) goto L_0x00d7;
    L_0x00c4:
        r11 = r7.isInitialized();
        if (r11 == 0) goto L_0x00d7;
    L_0x00ca:
        r11 = r7.zzach;
        if (r11 != r8) goto L_0x00d7;
    L_0x00ce:
        r11 = r7.zzzu;
        if (r11 != r10) goto L_0x00d7;
    L_0x00d2:
        r11 = r7.zzacg;
        if (r11 != r13) goto L_0x00d7;
    L_0x00d6:
        return;
    L_0x00d7:
        r7.reset();
        r7.zzach = r8;
        r7.zzacj = r12;
        r7.zzzu = r10;
        r7.zzacg = r13;
        r11 = 2;
        if (r12 == 0) goto L_0x00e6;
    L_0x00e5:
        goto L_0x00e7;
    L_0x00e6:
        r8 = r11;
    L_0x00e7:
        r7.zzaci = r8;
        r8 = com.google.android.gms.internal.ads.zzqe.zzg(r11, r9);
        r7.zzadb = r8;
        if (r12 == 0) goto L_0x0102;
    L_0x00f1:
        r8 = r7.zzaci;
        if (r8 == r6) goto L_0x00ff;
    L_0x00f5:
        r8 = r7.zzaci;
        r9 = 6;
        if (r8 != r9) goto L_0x00fb;
    L_0x00fa:
        goto L_0x00ff;
    L_0x00fb:
        r8 = 49152; // 0xc000 float:6.8877E-41 double:2.42843E-319;
        goto L_0x0136;
    L_0x00ff:
        r8 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        goto L_0x0136;
    L_0x0102:
        r8 = r7.zzaci;
        r8 = android.media.AudioTrack.getMinBufferSize(r10, r13, r8);
        r9 = -2;
        if (r8 == r9) goto L_0x010c;
    L_0x010b:
        goto L_0x010d;
    L_0x010c:
        r0 = r1;
    L_0x010d:
        com.google.android.gms.internal.ads.zzpo.checkState(r0);
        r9 = r8 << 2;
        r10 = 250000; // 0x3d090 float:3.50325E-40 double:1.235164E-318;
        r10 = r7.zzo(r10);
        r10 = (int) r10;
        r11 = r7.zzadb;
        r10 = r10 * r11;
        r0 = (long) r8;
        r2 = 750000; // 0xb71b0 float:1.050974E-39 double:3.70549E-318;
        r2 = r7.zzo(r2);
        r8 = r7.zzadb;
        r4 = (long) r8;
        r2 = r2 * r4;
        r0 = java.lang.Math.max(r0, r2);
        r8 = (int) r0;
        if (r9 >= r10) goto L_0x0132;
    L_0x0130:
        r8 = r10;
        goto L_0x0136;
    L_0x0132:
        if (r9 <= r8) goto L_0x0135;
    L_0x0134:
        goto L_0x0136;
    L_0x0135:
        r8 = r9;
    L_0x0136:
        r7.zzack = r8;
        if (r12 == 0) goto L_0x0140;
    L_0x013a:
        r8 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        goto L_0x014a;
    L_0x0140:
        r8 = r7.zzack;
        r9 = r7.zzadb;
        r8 = r8 / r9;
        r8 = (long) r8;
        r8 = r7.zzn(r8);
    L_0x014a:
        r7.zzacl = r8;
        r8 = r7.zzxm;
        r7.zzb(r8);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgs.zza(java.lang.String, int, int, int, int, int[]):void");
    }

    private final void zzcs() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (zzgi zzgi : this.zzabz) {
            if (zzgi.isActive()) {
                arrayList.add(zzgi);
            } else {
                zzgi.flush();
            }
        }
        int size = arrayList.size();
        this.zzadj = (zzgi[]) arrayList.toArray(new zzgi[size]);
        this.zzadk = new ByteBuffer[size];
        while (i < size) {
            zzgi zzgi2 = this.zzadj[i];
            zzgi2.flush();
            this.zzadk[i] = zzgi2.zzcr();
            i++;
        }
    }

    public final void play() {
        this.zzadr = true;
        if (isInitialized()) {
            this.zzadh = System.nanoTime() / 1000;
            this.zzacf.play();
        }
    }

    public final void zzct() {
        if (this.zzadf == 1) {
            this.zzadf = 2;
        }
    }

    public final boolean zza(ByteBuffer byteBuffer, long j) throws zzgx, zzha {
        int state;
        ByteBuffer byteBuffer2 = byteBuffer;
        long j2 = j;
        boolean z = this.zzadl == null || byteBuffer2 == this.zzadl;
        zzpo.checkArgument(z);
        if (!isInitialized()) {
            this.zzacb.block();
            if (this.zzadt) {
                this.zzacf = new AudioTrack(new Builder().setUsage(1).setContentType(3).setFlags(16).build(), new AudioFormat.Builder().setChannelMask(this.zzacg).setEncoding(this.zzaci).setSampleRate(this.zzzu).build(), this.zzack, 1, this.zzads);
            } else if (this.zzads == 0) {
                this.zzacf = new AudioTrack(this.streamType, this.zzzu, this.zzacg, this.zzaci, this.zzack, 1);
            } else {
                this.zzacf = new AudioTrack(this.streamType, this.zzzu, this.zzacg, this.zzaci, this.zzack, 1, this.zzads);
            }
            state = this.zzacf.getState();
            if (state != 1) {
                try {
                    this.zzacf.release();
                    this.zzacf = null;
                } catch (Exception unused) {
                    this.zzacf = null;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    this.zzacf = null;
                }
                throw new zzgx(state, this.zzzu, this.zzacg, this.zzack);
            }
            state = this.zzacf.getAudioSessionId();
            if (this.zzads != state) {
                this.zzads = state;
                this.zzaca.zzl(state);
            }
            this.zzacd.zza(this.zzacf, zzdc());
            zzcz();
            this.zzadu = false;
            if (this.zzadr) {
                play();
            }
        }
        if (zzdc()) {
            if (this.zzacf.getPlayState() == 2) {
                this.zzadu = false;
                return false;
            } else if (this.zzacf.getPlayState() == 1 && this.zzacd.zzdd() != 0) {
                return false;
            }
        }
        z = this.zzadu;
        this.zzadu = zzcw();
        if (!(!z || this.zzadu || this.zzacf.getPlayState() == 1)) {
            this.zzaca.zzc(this.zzack, zzfe.zzf(this.zzacl), SystemClock.elapsedRealtime() - this.zzadv);
        }
        if (this.zzadl == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (this.zzacj && this.zzade == 0) {
                state = this.zzaci;
                if (state == 7 || state == 8) {
                    state = zzhc.zzj(byteBuffer);
                } else if (state == 5) {
                    state = zzgg.zzcn();
                } else if (state == 6) {
                    state = zzgg.zzh(byteBuffer);
                } else {
                    StringBuilder stringBuilder = new StringBuilder(38);
                    stringBuilder.append("Unexpected audio encoding: ");
                    stringBuilder.append(state);
                    throw new IllegalStateException(stringBuilder.toString());
                }
                this.zzade = state;
            }
            if (this.zzacm != null) {
                if (!zzcv()) {
                    return false;
                }
                LinkedList linkedList = this.zzace;
                zzgz zzgz = r12;
                zzgz zzgz2 = new zzgz(this.zzacm, Math.max(0, j2), zzn(zzda()), null);
                linkedList.add(zzgz);
                this.zzacm = null;
                zzcs();
            }
            if (this.zzadf == 0) {
                this.zzadg = Math.max(0, j2);
                this.zzadf = 1;
            } else {
                long zzn = this.zzadg + zzn(this.zzacj ? this.zzada : this.zzacz / ((long) this.zzacy));
                if (this.zzadf != 1 || Math.abs(zzn - j2) <= 200000) {
                    state = 2;
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder(80);
                    stringBuilder2.append("Discontinuity detected [expected ");
                    stringBuilder2.append(zzn);
                    stringBuilder2.append(", got ");
                    stringBuilder2.append(j2);
                    stringBuilder2.append("]");
                    Log.e("AudioTrack", stringBuilder2.toString());
                    state = 2;
                    this.zzadf = 2;
                }
                if (this.zzadf == state) {
                    this.zzadg += j2 - zzn;
                    this.zzadf = 1;
                    this.zzaca.zzbs();
                }
            }
            if (this.zzacj) {
                this.zzada += (long) this.zzade;
            } else {
                this.zzacz += (long) byteBuffer.remaining();
            }
            this.zzadl = byteBuffer2;
        }
        if (this.zzacj) {
            zzb(this.zzadl, j2);
        } else {
            zzm(j2);
        }
        if (this.zzadl.hasRemaining()) {
            return false;
        }
        this.zzadl = null;
        return true;
    }

    private final void zzm(long j) throws zzha {
        int length = this.zzadj.length;
        int i = length;
        while (i >= 0) {
            ByteBuffer byteBuffer = i > 0 ? this.zzadk[i - 1] : this.zzadl != null ? this.zzadl : zzgi.zzabh;
            if (i == length) {
                zzb(byteBuffer, j);
            } else {
                zzgi zzgi = this.zzadj[i];
                zzgi.zzi(byteBuffer);
                ByteBuffer zzcr = zzgi.zzcr();
                this.zzadk[i] = zzcr;
                if (zzcr.hasRemaining()) {
                    i++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i--;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0103  */
    /* JADX WARNING: Missing block: B:43:0x00dc, code skipped:
            if (r13 < r12) goto L_0x007a;
     */
    private final boolean zzb(java.nio.ByteBuffer r11, long r12) throws com.google.android.gms.internal.ads.zzha {
        /*
        r10 = this;
        r0 = r11.hasRemaining();
        r1 = 1;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = r10.zzadm;
        r2 = 21;
        r3 = 0;
        if (r0 == 0) goto L_0x001a;
    L_0x000f:
        r0 = r10.zzadm;
        if (r0 != r11) goto L_0x0015;
    L_0x0013:
        r0 = r1;
        goto L_0x0016;
    L_0x0015:
        r0 = r3;
    L_0x0016:
        com.google.android.gms.internal.ads.zzpo.checkArgument(r0);
        goto L_0x003f;
    L_0x001a:
        r10.zzadm = r11;
        r0 = com.google.android.gms.internal.ads.zzqe.SDK_INT;
        if (r0 >= r2) goto L_0x003f;
    L_0x0020:
        r0 = r11.remaining();
        r4 = r10.zzadn;
        if (r4 == 0) goto L_0x002d;
    L_0x0028:
        r4 = r10.zzadn;
        r4 = r4.length;
        if (r4 >= r0) goto L_0x0031;
    L_0x002d:
        r4 = new byte[r0];
        r10.zzadn = r4;
    L_0x0031:
        r4 = r11.position();
        r5 = r10.zzadn;
        r11.get(r5, r3, r0);
        r11.position(r4);
        r10.zzado = r3;
    L_0x003f:
        r0 = r11.remaining();
        r4 = com.google.android.gms.internal.ads.zzqe.SDK_INT;
        if (r4 >= r2) goto L_0x007d;
    L_0x0047:
        r12 = r10.zzadc;
        r2 = r10.zzacd;
        r4 = r2.zzdd();
        r2 = r10.zzadb;
        r6 = (long) r2;
        r4 = r4 * r6;
        r6 = r12 - r4;
        r12 = (int) r6;
        r13 = r10.zzack;
        r13 = r13 - r12;
        if (r13 <= 0) goto L_0x007a;
    L_0x005b:
        r12 = java.lang.Math.min(r0, r13);
        r13 = r10.zzacf;
        r2 = r10.zzadn;
        r4 = r10.zzado;
        r12 = r13.write(r2, r4, r12);
        if (r12 <= 0) goto L_0x00f5;
    L_0x006b:
        r13 = r10.zzado;
        r13 = r13 + r12;
        r10.zzado = r13;
        r13 = r11.position();
        r13 = r13 + r12;
        r11.position(r13);
        goto L_0x00f5;
    L_0x007a:
        r12 = r3;
        goto L_0x00f5;
    L_0x007d:
        r2 = r10.zzadt;
        if (r2 == 0) goto L_0x00ef;
    L_0x0081:
        r4 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r2 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r2 == 0) goto L_0x008c;
    L_0x008a:
        r2 = r1;
        goto L_0x008d;
    L_0x008c:
        r2 = r3;
    L_0x008d:
        com.google.android.gms.internal.ads.zzpo.checkState(r2);
        r2 = r10.zzacf;
        r4 = r10.zzacp;
        if (r4 != 0) goto L_0x00ad;
    L_0x0096:
        r4 = 16;
        r4 = java.nio.ByteBuffer.allocate(r4);
        r10.zzacp = r4;
        r4 = r10.zzacp;
        r5 = java.nio.ByteOrder.BIG_ENDIAN;
        r4.order(r5);
        r4 = r10.zzacp;
        r5 = 1431633921; // 0x55550001 float:1.46372496E13 double:7.07321138E-315;
        r4.putInt(r5);
    L_0x00ad:
        r4 = r10.zzacq;
        if (r4 != 0) goto L_0x00c8;
    L_0x00b1:
        r4 = r10.zzacp;
        r5 = 4;
        r4.putInt(r5, r0);
        r4 = r10.zzacp;
        r5 = 8;
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r12 = r12 * r6;
        r4.putLong(r5, r12);
        r12 = r10.zzacp;
        r12.position(r3);
        r10.zzacq = r0;
    L_0x00c8:
        r12 = r10.zzacp;
        r12 = r12.remaining();
        if (r12 <= 0) goto L_0x00df;
    L_0x00d0:
        r13 = r10.zzacp;
        r13 = r2.write(r13, r12, r1);
        if (r13 >= 0) goto L_0x00dc;
    L_0x00d8:
        r10.zzacq = r3;
        r12 = r13;
        goto L_0x00f5;
    L_0x00dc:
        if (r13 >= r12) goto L_0x00df;
    L_0x00de:
        goto L_0x007a;
    L_0x00df:
        r11 = r2.write(r11, r0, r1);
        if (r11 >= 0) goto L_0x00e8;
    L_0x00e5:
        r10.zzacq = r3;
        goto L_0x00ed;
    L_0x00e8:
        r12 = r10.zzacq;
        r12 = r12 - r11;
        r10.zzacq = r12;
    L_0x00ed:
        r12 = r11;
        goto L_0x00f5;
    L_0x00ef:
        r12 = r10.zzacf;
        r12 = r12.write(r11, r0, r1);
    L_0x00f5:
        r4 = android.os.SystemClock.elapsedRealtime();
        r10.zzadv = r4;
        if (r12 >= 0) goto L_0x0103;
    L_0x00fd:
        r11 = new com.google.android.gms.internal.ads.zzha;
        r11.<init>(r12);
        throw r11;
    L_0x0103:
        r11 = r10.zzacj;
        if (r11 != 0) goto L_0x010e;
    L_0x0107:
        r4 = r10.zzadc;
        r6 = (long) r12;
        r8 = r4 + r6;
        r10.zzadc = r8;
    L_0x010e:
        if (r12 != r0) goto L_0x0121;
    L_0x0110:
        r11 = r10.zzacj;
        if (r11 == 0) goto L_0x011d;
    L_0x0114:
        r11 = r10.zzadd;
        r13 = r10.zzade;
        r2 = (long) r13;
        r4 = r11 + r2;
        r10.zzadd = r4;
    L_0x011d:
        r11 = 0;
        r10.zzadm = r11;
        return r1;
    L_0x0121:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgs.zzb(java.nio.ByteBuffer, long):boolean");
    }

    /* JADX WARNING: Missing block: B:8:0x0021, code skipped:
            return;
     */
    public final void zzcu() throws com.google.android.gms.internal.ads.zzha {
        /*
        r3 = this;
        r0 = r3.zzadq;
        if (r0 != 0) goto L_0x0021;
    L_0x0004:
        r0 = r3.isInitialized();
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        goto L_0x0021;
    L_0x000b:
        r0 = r3.zzcv();
        if (r0 == 0) goto L_0x0020;
    L_0x0011:
        r0 = r3.zzacd;
        r1 = r3.zzda();
        r0.zzp(r1);
        r0 = 0;
        r3.zzacq = r0;
        r0 = 1;
        r3.zzadq = r0;
    L_0x0020:
        return;
    L_0x0021:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgs.zzcu():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    private final boolean zzcv() throws com.google.android.gms.internal.ads.zzha {
        /*
        r8 = this;
        r0 = r8.zzadp;
        r1 = -1;
        r2 = 1;
        r3 = 0;
        if (r0 != r1) goto L_0x0014;
    L_0x0007:
        r0 = r8.zzacj;
        if (r0 == 0) goto L_0x000f;
    L_0x000b:
        r0 = r8.zzadj;
        r0 = r0.length;
        goto L_0x0010;
    L_0x000f:
        r0 = r3;
    L_0x0010:
        r8.zzadp = r0;
    L_0x0012:
        r0 = r2;
        goto L_0x0015;
    L_0x0014:
        r0 = r3;
    L_0x0015:
        r4 = r8.zzadp;
        r5 = r8.zzadj;
        r6 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r5 = r5.length;
        if (r4 >= r5) goto L_0x003c;
    L_0x0021:
        r4 = r8.zzadj;
        r5 = r8.zzadp;
        r4 = r4[r5];
        if (r0 == 0) goto L_0x002c;
    L_0x0029:
        r4.zzcq();
    L_0x002c:
        r8.zzm(r6);
        r0 = r4.zzcj();
        if (r0 != 0) goto L_0x0036;
    L_0x0035:
        return r3;
    L_0x0036:
        r0 = r8.zzadp;
        r0 = r0 + r2;
        r8.zzadp = r0;
        goto L_0x0012;
    L_0x003c:
        r0 = r8.zzadm;
        if (r0 == 0) goto L_0x004a;
    L_0x0040:
        r0 = r8.zzadm;
        r8.zzb(r0, r6);
        r0 = r8.zzadm;
        if (r0 == 0) goto L_0x004a;
    L_0x0049:
        return r3;
    L_0x004a:
        r8.zzadp = r1;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgs.zzcv():boolean");
    }

    public final boolean zzcj() {
        return !isInitialized() || (this.zzadq && !zzcw());
    }

    public final boolean zzcw() {
        if (isInitialized()) {
            if (zzda() <= this.zzacd.zzdd()) {
                boolean z = zzdc() && this.zzacf.getPlayState() == 2 && this.zzacf.getPlaybackHeadPosition() == 0;
                if (z) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    public final zzfy zzb(zzfy zzfy) {
        if (this.zzacj) {
            this.zzxm = zzfy.zzaaf;
            return this.zzxm;
        }
        Object obj;
        zzfy zzfy2 = new zzfy(this.zzaby.zzb(zzfy.zzaag), this.zzaby.zzc(zzfy.zzaah));
        if (this.zzacm != null) {
            obj = this.zzacm;
        } else if (this.zzace.isEmpty()) {
            obj = this.zzxm;
        } else {
            obj = ((zzgz) this.zzace.getLast()).zzxm;
        }
        if (!zzfy2.equals(obj)) {
            if (isInitialized()) {
                this.zzacm = zzfy2;
            } else {
                this.zzxm = zzfy2;
            }
        }
        return this.zzxm;
    }

    public final zzfy zzcx() {
        return this.zzxm;
    }

    public final void setStreamType(int i) {
        if (this.streamType != i) {
            this.streamType = i;
            if (!this.zzadt) {
                reset();
                this.zzads = 0;
            }
        }
    }

    public final void zzn(int i) {
        zzpo.checkState(zzqe.SDK_INT >= 21);
        if (!this.zzadt || this.zzads != i) {
            this.zzadt = true;
            this.zzads = i;
            reset();
        }
    }

    public final void zzcy() {
        if (this.zzadt) {
            this.zzadt = false;
            this.zzads = 0;
            reset();
        }
    }

    public final void setVolume(float f) {
        if (this.zzcu != f) {
            this.zzcu = f;
            zzcz();
        }
    }

    private final void zzcz() {
        if (isInitialized()) {
            if (zzqe.SDK_INT >= 21) {
                this.zzacf.setVolume(this.zzcu);
                return;
            }
            AudioTrack audioTrack = this.zzacf;
            float f = this.zzcu;
            audioTrack.setStereoVolume(f, f);
        }
    }

    public final void pause() {
        this.zzadr = false;
        if (isInitialized()) {
            zzdb();
            this.zzacd.pause();
        }
    }

    public final void reset() {
        if (isInitialized()) {
            this.zzacz = 0;
            this.zzada = 0;
            this.zzadc = 0;
            this.zzadd = 0;
            this.zzade = 0;
            if (this.zzacm != null) {
                this.zzxm = this.zzacm;
                this.zzacm = null;
            } else if (!this.zzace.isEmpty()) {
                this.zzxm = ((zzgz) this.zzace.getLast()).zzxm;
            }
            this.zzace.clear();
            this.zzacn = 0;
            this.zzaco = 0;
            this.zzadl = null;
            this.zzadm = null;
            for (int i = 0; i < this.zzadj.length; i++) {
                zzgi zzgi = this.zzadj[i];
                zzgi.flush();
                this.zzadk[i] = zzgi.zzcr();
            }
            this.zzadq = false;
            this.zzadp = -1;
            this.zzacp = null;
            this.zzacq = 0;
            this.zzadf = 0;
            this.zzadi = 0;
            zzdb();
            if (this.zzacf.getPlayState() == 3) {
                this.zzacf.pause();
            }
            AudioTrack audioTrack = this.zzacf;
            this.zzacf = null;
            this.zzacd.zza(null, false);
            this.zzacb.close();
            new zzgt(this, audioTrack).start();
        }
    }

    public final void release() {
        reset();
        for (zzgi reset : this.zzabz) {
            reset.reset();
        }
        this.zzads = 0;
        this.zzadr = false;
    }

    private final boolean isInitialized() {
        return this.zzacf != null;
    }

    private final long zzn(long j) {
        return (j * 1000000) / ((long) this.zzzu);
    }

    private final long zzo(long j) {
        return (j * ((long) this.zzzu)) / 1000000;
    }

    private final long zzda() {
        return this.zzacj ? this.zzadd : this.zzadc / ((long) this.zzadb);
    }

    private final void zzdb() {
        this.zzact = 0;
        this.zzacs = 0;
        this.zzacr = 0;
        this.zzacu = 0;
        this.zzacv = false;
        this.zzacw = 0;
    }

    private final boolean zzdc() {
        return zzqe.SDK_INT < 23 && (this.zzaci == 5 || this.zzaci == 6);
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
    private static int zzr(java.lang.String r3) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgs.zzr(java.lang.String):int");
    }
}
