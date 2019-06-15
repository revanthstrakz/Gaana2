package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.ads.zzhp.zza;
import com.google.firebase.FirebaseError;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

public final class zzip implements zzhz {
    private static final zzic zzahq = new zziq();
    private static final byte[] zzahr = new byte[]{(byte) 49, (byte) 10, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 32, (byte) 45, (byte) 45, (byte) 62, (byte) 32, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 10};
    private static final byte[] zzahs = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
    private static final UUID zzaht = new UUID(72057594037932032L, -9223371306706625679L);
    private long zzaan;
    private final zziu zzahk;
    private final zzin zzahu;
    private final SparseArray<zzis> zzahv;
    private final boolean zzahw;
    private final zzpx zzahx;
    private final zzpx zzahy;
    private final zzpx zzahz;
    private final zzpx zzaia;
    private final zzpx zzaib;
    private final zzpx zzaic;
    private final zzpx zzaid;
    private final zzpx zzaie;
    private final zzpx zzaif;
    private ByteBuffer zzaig;
    private long zzaih;
    private long zzaii;
    private long zzaij;
    private long zzaik;
    private zzis zzail;
    private boolean zzaim;
    private int zzain;
    private long zzaio;
    private boolean zzaip;
    private long zzaiq;
    private long zzair;
    private long zzais;
    private zzpr zzait;
    private zzpr zzaiu;
    private boolean zzaiv;
    private int zzaiw;
    private long zzaix;
    private long zzaiy;
    private int zzaiz;
    private int zzaja;
    private int[] zzajb;
    private int zzajc;
    private int zzajd;
    private int zzaje;
    private int zzajf;
    private boolean zzajg;
    private boolean zzajh;
    private boolean zzaji;
    private boolean zzajj;
    private byte zzajk;
    private int zzajl;
    private int zzajm;
    private int zzajn;
    private boolean zzajo;
    private boolean zzajp;
    private zzib zzajq;

    public zzip() {
        this(0);
    }

    static int zzab(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /*134*/:
            case FirebaseError.ERROR_WEAK_PASSWORD /*17026*/:
            case 2274716:
                return 3;
            case MoEHelperUtils.BASELINE_SCREEN_DPI /*160*/:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
                return 5;
            default:
                return 0;
        }
    }

    static boolean zzac(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    public final void release() {
    }

    public zzip(int i) {
        this(new zzik(), i);
    }

    private zzip(zzin zzin, int i) {
        this.zzaii = -1;
        this.zzaij = C.TIME_UNSET;
        this.zzaik = C.TIME_UNSET;
        this.zzaan = C.TIME_UNSET;
        this.zzaiq = -1;
        this.zzair = -1;
        this.zzais = C.TIME_UNSET;
        this.zzahu = zzin;
        this.zzahu.zza(new zzir(this, null));
        boolean z = true;
        if ((i & 1) != 0) {
            z = false;
        }
        this.zzahw = z;
        this.zzahk = new zziu();
        this.zzahv = new SparseArray();
        this.zzahz = new zzpx(4);
        this.zzaia = new zzpx(ByteBuffer.allocate(4).putInt(-1).array());
        this.zzaib = new zzpx(4);
        this.zzahx = new zzpx(zzpu.zzbhi);
        this.zzahy = new zzpx(4);
        this.zzaic = new zzpx();
        this.zzaid = new zzpx();
        this.zzaie = new zzpx(8);
        this.zzaif = new zzpx();
    }

    public final boolean zza(zzia zzia) throws IOException, InterruptedException {
        return new zzit().zza(zzia);
    }

    public final void zza(zzib zzib) {
        this.zzajq = zzib;
    }

    public final void zzc(long j, long j2) {
        this.zzais = C.TIME_UNSET;
        this.zzaiw = 0;
        this.zzahu.reset();
        this.zzahk.reset();
        zzeb();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0005 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003b A:{SYNTHETIC} */
    public final int zza(com.google.android.gms.internal.ads.zzia r9, com.google.android.gms.internal.ads.zzif r10) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r8 = this;
        r0 = 0;
        r8.zzajo = r0;
        r1 = 1;
        r2 = r1;
    L_0x0005:
        if (r2 == 0) goto L_0x003c;
    L_0x0007:
        r3 = r8.zzajo;
        if (r3 != 0) goto L_0x003c;
    L_0x000b:
        r2 = r8.zzahu;
        r2 = r2.zzb(r9);
        if (r2 == 0) goto L_0x0005;
    L_0x0013:
        r3 = r9.getPosition();
        r5 = r8.zzaip;
        if (r5 == 0) goto L_0x0025;
    L_0x001b:
        r8.zzair = r3;
        r3 = r8.zzaiq;
        r10.zzaha = r3;
        r8.zzaip = r0;
    L_0x0023:
        r3 = r1;
        goto L_0x0039;
    L_0x0025:
        r3 = r8.zzaim;
        if (r3 == 0) goto L_0x0038;
    L_0x0029:
        r3 = r8.zzair;
        r5 = -1;
        r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r7 == 0) goto L_0x0038;
    L_0x0031:
        r3 = r8.zzair;
        r10.zzaha = r3;
        r8.zzair = r5;
        goto L_0x0023;
    L_0x0038:
        r3 = r0;
    L_0x0039:
        if (r3 == 0) goto L_0x0005;
    L_0x003b:
        return r1;
    L_0x003c:
        if (r2 == 0) goto L_0x003f;
    L_0x003e:
        return r0;
    L_0x003f:
        r9 = -1;
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzip.zza(com.google.android.gms.internal.ads.zzia, com.google.android.gms.internal.ads.zzif):int");
    }

    /* Access modifiers changed, original: final */
    public final void zzd(int i, long j, long j2) throws zzfx {
        if (i == MoEHelperUtils.BASELINE_SCREEN_DPI) {
            this.zzajp = false;
        } else if (i == 174) {
            this.zzail = new zzis();
        } else if (i == 187) {
            this.zzaiv = false;
        } else if (i == 19899) {
            this.zzain = -1;
            this.zzaio = -1;
        } else if (i != 20533) {
            if (i == 21968) {
                this.zzail.zzakb = true;
            } else if (i == 25152) {
            } else {
                if (i != 408125543) {
                    if (i == 475249515) {
                        this.zzait = new zzpr();
                        this.zzaiu = new zzpr();
                    } else if (i == 524531317 && !this.zzaim) {
                        if (!this.zzahw || this.zzaiq == -1) {
                            this.zzajq.zza(new zzih(this.zzaan));
                            this.zzaim = true;
                            return;
                        }
                        this.zzaip = true;
                    }
                } else if (this.zzaii == -1 || this.zzaii == j) {
                    this.zzaii = j;
                    this.zzaih = j2;
                } else {
                    throw new zzfx("Multiple Segment elements not supported");
                }
            }
        } else {
            this.zzail.zzaju = true;
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzad(int i) throws zzfx {
        int i2 = i;
        int i3 = 0;
        if (i2 != 160) {
            if (i2 != 174) {
                if (i2 != 19899) {
                    if (i2 != 25152) {
                        if (i2 != 28032) {
                            if (i2 == 357149030) {
                                if (this.zzaij == C.TIME_UNSET) {
                                    this.zzaij = 1000000;
                                }
                                if (this.zzaik != C.TIME_UNSET) {
                                    this.zzaan = zzs(this.zzaik);
                                    return;
                                }
                            } else if (i2 != 374648427) {
                                if (i2 == 475249515 && !this.zzaim) {
                                    zzig zzih;
                                    zzib zzib = this.zzajq;
                                    if (this.zzaii == -1 || this.zzaan == C.TIME_UNSET || this.zzait == null || this.zzait.size() == 0 || this.zzaiu == null || this.zzaiu.size() != this.zzait.size()) {
                                        this.zzait = null;
                                        this.zzaiu = null;
                                        zzih = new zzih(this.zzaan);
                                    } else {
                                        int i4;
                                        int size = this.zzait.size();
                                        int[] iArr = new int[size];
                                        long[] jArr = new long[size];
                                        long[] jArr2 = new long[size];
                                        long[] jArr3 = new long[size];
                                        for (i4 = 0; i4 < size; i4++) {
                                            jArr3[i4] = this.zzait.get(i4);
                                            jArr[i4] = this.zzaii + this.zzaiu.get(i4);
                                        }
                                        while (true) {
                                            i4 = size - 1;
                                            if (i3 >= i4) {
                                                break;
                                            }
                                            i4 = i3 + 1;
                                            iArr[i3] = (int) (jArr[i4] - jArr[i3]);
                                            jArr2[i3] = jArr3[i4] - jArr3[i3];
                                            i3 = i4;
                                        }
                                        iArr[i4] = (int) ((this.zzaii + this.zzaih) - jArr[i4]);
                                        jArr2[i4] = this.zzaan - jArr3[i4];
                                        this.zzait = null;
                                        this.zzaiu = null;
                                        zzih = new zzhw(iArr, jArr, jArr2, jArr3);
                                    }
                                    zzib.zza(zzih);
                                    this.zzaim = true;
                                    return;
                                }
                            } else if (this.zzahv.size() == 0) {
                                throw new zzfx("No valid tracks were found");
                            } else {
                                this.zzajq.zzdy();
                            }
                        } else if (this.zzail.zzaju && this.zzail.zzajv != null) {
                            throw new zzfx("Combining encryption and compression is not supported");
                        }
                    } else if (this.zzail.zzaju) {
                        if (this.zzail.zzajw == null) {
                            throw new zzfx("Encrypted Track found but ContentEncKeyID was not found");
                        }
                        this.zzail.zzzm = new zzhp(new zza(zzfe.zzwm, MimeTypes.VIDEO_WEBM, this.zzail.zzajw.zzahh));
                        return;
                    }
                } else if (this.zzain == -1 || this.zzaio == -1) {
                    throw new zzfx("Mandatory element SeekID or SeekPosition not found");
                } else if (this.zzain == 475249515) {
                    this.zzaiq = this.zzaio;
                    return;
                }
                return;
            }
            String str = this.zzail.zzajs;
            int i5 = ("V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str)) ? 1 : 0;
            if (i5 != 0) {
                this.zzail.zza(this.zzajq, this.zzail.number);
                this.zzahv.put(this.zzail.number, this.zzail);
            }
            this.zzail = null;
        } else if (this.zzaiw == 2) {
            if (!this.zzajp) {
                this.zzaje |= 1;
            }
            zza((zzis) this.zzahv.get(this.zzajc), this.zzaix);
            this.zzaiw = 0;
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzc(int i, long j) throws zzfx {
        boolean z = false;
        zzis zzis;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        switch (i) {
            case 131:
                this.zzail.type = (int) j;
                return;
            case 136:
                zzis = this.zzail;
                if (j == 1) {
                    z = true;
                }
                zzis.zzaku = z;
                return;
            case 155:
                this.zzaiy = zzs(j);
                return;
            case 159:
                this.zzail.zzzt = (int) j;
                return;
            case 176:
                this.zzail.width = (int) j;
                return;
            case 179:
                this.zzait.add(zzs(j));
                return;
            case 186:
                this.zzail.height = (int) j;
                return;
            case 215:
                this.zzail.number = (int) j;
                return;
            case 231:
                this.zzais = zzs(j);
                return;
            case 241:
                if (!this.zzaiv) {
                    this.zzaiu.add(j);
                    this.zzaiv = true;
                    return;
                }
                break;
            case 251:
                this.zzajp = true;
                return;
            case 16980:
                if (j != 3) {
                    stringBuilder = new StringBuilder(50);
                    stringBuilder.append("ContentCompAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new zzfx(stringBuilder.toString());
                }
                break;
            case 17029:
                if (j < 1 || j > 2) {
                    stringBuilder = new StringBuilder(53);
                    stringBuilder.append("DocTypeReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new zzfx(stringBuilder.toString());
                }
            case 17143:
                if (j != 1) {
                    stringBuilder = new StringBuilder(50);
                    stringBuilder.append("EBMLReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new zzfx(stringBuilder.toString());
                }
                break;
            case 18401:
                if (j != 5) {
                    stringBuilder = new StringBuilder(49);
                    stringBuilder.append("ContentEncAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new zzfx(stringBuilder.toString());
                }
                break;
            case 18408:
                if (j != 1) {
                    stringBuilder = new StringBuilder(56);
                    stringBuilder.append("AESSettingsCipherMode ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new zzfx(stringBuilder.toString());
                }
                break;
            case 20529:
                if (j != 0) {
                    stringBuilder2 = new StringBuilder(55);
                    stringBuilder2.append("ContentEncodingOrder ");
                    stringBuilder2.append(j);
                    stringBuilder2.append(" not supported");
                    throw new zzfx(stringBuilder2.toString());
                }
                break;
            case 20530:
                if (j != 1) {
                    stringBuilder2 = new StringBuilder(55);
                    stringBuilder2.append("ContentEncodingScope ");
                    stringBuilder2.append(j);
                    stringBuilder2.append(" not supported");
                    throw new zzfx(stringBuilder2.toString());
                }
                break;
            case 21420:
                this.zzaio = j + this.zzaii;
                return;
            case 21432:
                i = (int) j;
                if (i == 3) {
                    this.zzail.zzzq = 1;
                    return;
                } else if (i != 15) {
                    switch (i) {
                        case 0:
                            this.zzail.zzzq = 0;
                            return;
                        case 1:
                            this.zzail.zzzq = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.zzail.zzzq = 3;
                    return;
                }
            case 21680:
                this.zzail.zzajy = (int) j;
                return;
            case 21682:
                this.zzail.zzaka = (int) j;
                return;
            case 21690:
                this.zzail.zzajz = (int) j;
                return;
            case 21930:
                zzis = this.zzail;
                if (j == 1) {
                    z = true;
                }
                zzis.zzakv = z;
                return;
            case 21945:
                switch ((int) j) {
                    case 1:
                        this.zzail.zzake = 2;
                        return;
                    case 2:
                        this.zzail.zzake = 1;
                        return;
                    default:
                        return;
                }
            case 21946:
                i = (int) j;
                if (i != 1) {
                    if (i == 16) {
                        this.zzail.zzakd = 6;
                        return;
                    } else if (i != 18) {
                        switch (i) {
                            case 6:
                            case 7:
                                break;
                            default:
                                return;
                        }
                    } else {
                        this.zzail.zzakd = 7;
                        return;
                    }
                }
                this.zzail.zzakd = 3;
                return;
            case 21947:
                this.zzail.zzakb = true;
                i = (int) j;
                if (i == 1) {
                    this.zzail.zzakc = 1;
                    return;
                } else if (i != 9) {
                    switch (i) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            this.zzail.zzakc = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.zzail.zzakc = 6;
                    return;
                }
            case 21948:
                this.zzail.zzakf = (int) j;
                return;
            case 21949:
                this.zzail.zzakg = (int) j;
                break;
            case 22186:
                this.zzail.zzaks = j;
                return;
            case 22203:
                this.zzail.zzakt = j;
                return;
            case 25188:
                this.zzail.zzakr = (int) j;
                return;
            case 2352003:
                this.zzail.zzajt = (int) j;
                return;
            case 2807729:
                this.zzaij = j;
                return;
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(int i, double d) {
        if (i == 181) {
            this.zzail.zzzu = (int) d;
        } else if (i != 17545) {
            switch (i) {
                case 21969:
                    this.zzail.zzakh = (float) d;
                    return;
                case 21970:
                    this.zzail.zzaki = (float) d;
                    return;
                case 21971:
                    this.zzail.zzakj = (float) d;
                    return;
                case 21972:
                    this.zzail.zzakk = (float) d;
                    return;
                case 21973:
                    this.zzail.zzakl = (float) d;
                    return;
                case 21974:
                    this.zzail.zzakm = (float) d;
                    return;
                case 21975:
                    this.zzail.zzakn = (float) d;
                    return;
                case 21976:
                    this.zzail.zzako = (float) d;
                    return;
                case 21977:
                    this.zzail.zzakp = (float) d;
                    return;
                case 21978:
                    this.zzail.zzakq = (float) d;
                    break;
            }
        } else {
            this.zzaik = (long) d;
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(int i, String str) throws zzfx {
        if (i != TsExtractor.TS_STREAM_TYPE_SPLICE_INFO) {
            if (i != FirebaseError.ERROR_WEAK_PASSWORD) {
                if (i == 2274716) {
                    this.zzail.zzaaa = str;
                }
            } else if (!("webm".equals(str) || "matroska".equals(str))) {
                StringBuilder stringBuilder = new StringBuilder(22 + String.valueOf(str).length());
                stringBuilder.append("DocType ");
                stringBuilder.append(str);
                stringBuilder.append(" not supported");
                throw new zzfx(stringBuilder.toString());
            }
            return;
        }
        this.zzail.zzajs = str;
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x020a A:{SYNTHETIC, SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01e7  */
    public final void zza(int r24, int r25, com.google.android.gms.internal.ads.zzia r26) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r23 = this;
        r0 = r23;
        r1 = r24;
        r2 = r25;
        r3 = r26;
        r4 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r5 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r6 = 4;
        r7 = 0;
        r8 = 1;
        if (r1 == r4) goto L_0x0097;
    L_0x0011:
        if (r1 == r5) goto L_0x0097;
    L_0x0013:
        r4 = 16981; // 0x4255 float:2.3795E-41 double:8.3897E-320;
        if (r1 == r4) goto L_0x0089;
    L_0x0017:
        r4 = 18402; // 0x47e2 float:2.5787E-41 double:9.092E-320;
        if (r1 == r4) goto L_0x007a;
    L_0x001b:
        r4 = 21419; // 0x53ab float:3.0014E-41 double:1.05824E-319;
        if (r1 == r4) goto L_0x005c;
    L_0x001f:
        r4 = 25506; // 0x63a2 float:3.5742E-41 double:1.26016E-319;
        if (r1 == r4) goto L_0x004e;
    L_0x0023:
        r4 = 30322; // 0x7672 float:4.249E-41 double:1.4981E-319;
        if (r1 == r4) goto L_0x0040;
    L_0x0027:
        r2 = new com.google.android.gms.internal.ads.zzfx;
        r3 = 26;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "Unexpected id: ";
        r4.append(r3);
        r4.append(r1);
        r1 = r4.toString();
        r2.<init>(r1);
        throw r2;
    L_0x0040:
        r1 = r0.zzail;
        r4 = new byte[r2];
        r1.zzzr = r4;
        r1 = r0.zzail;
        r1 = r1.zzzr;
        r3.readFully(r1, r7, r2);
        return;
    L_0x004e:
        r1 = r0.zzail;
        r4 = new byte[r2];
        r1.zzajx = r4;
        r1 = r0.zzail;
        r1 = r1.zzajx;
        r3.readFully(r1, r7, r2);
        return;
    L_0x005c:
        r1 = r0.zzaib;
        r1 = r1.data;
        java.util.Arrays.fill(r1, r7);
        r1 = r0.zzaib;
        r1 = r1.data;
        r6 = r6 - r2;
        r3.readFully(r1, r6, r2);
        r1 = r0.zzaib;
        r1.setPosition(r7);
        r1 = r0.zzaib;
        r1 = r1.zzhd();
        r1 = (int) r1;
        r0.zzain = r1;
        return;
    L_0x007a:
        r1 = new byte[r2];
        r3.readFully(r1, r7, r2);
        r2 = r0.zzail;
        r3 = new com.google.android.gms.internal.ads.zzij;
        r3.<init>(r8, r1);
        r2.zzajw = r3;
        return;
    L_0x0089:
        r1 = r0.zzail;
        r4 = new byte[r2];
        r1.zzajv = r4;
        r1 = r0.zzail;
        r1 = r1.zzajv;
        r3.readFully(r1, r7, r2);
        return;
    L_0x0097:
        r4 = r0.zzaiw;
        r9 = 8;
        if (r4 != 0) goto L_0x00bc;
    L_0x009d:
        r4 = r0.zzahk;
        r10 = r4.zza(r3, r7, r8, r9);
        r4 = (int) r10;
        r0.zzajc = r4;
        r4 = r0.zzahk;
        r4 = r4.zzed();
        r0.zzajd = r4;
        r10 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r0.zzaiy = r10;
        r0.zzaiw = r8;
        r4 = r0.zzahz;
        r4.reset();
    L_0x00bc:
        r4 = r0.zzahv;
        r10 = r0.zzajc;
        r4 = r4.get(r10);
        r4 = (com.google.android.gms.internal.ads.zzis) r4;
        if (r4 != 0) goto L_0x00d2;
    L_0x00c8:
        r1 = r0.zzajd;
        r1 = r2 - r1;
        r3.zzw(r1);
        r0.zzaiw = r7;
        return;
    L_0x00d2:
        r10 = r0.zzaiw;
        if (r10 != r8) goto L_0x028b;
    L_0x00d6:
        r10 = 3;
        r0.zzb(r3, r10);
        r11 = r0.zzahz;
        r11 = r11.data;
        r12 = 2;
        r11 = r11[r12];
        r13 = 6;
        r11 = r11 & r13;
        r11 = r11 >> r8;
        r14 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r11 != 0) goto L_0x00fc;
    L_0x00e8:
        r0.zzaja = r8;
        r6 = r0.zzajb;
        r6 = zza(r6, r8);
        r0.zzajb = r6;
        r6 = r0.zzajb;
        r11 = r0.zzajd;
        r2 = r2 - r11;
        r2 = r2 - r10;
        r6[r7] = r2;
        goto L_0x021f;
    L_0x00fc:
        if (r1 == r5) goto L_0x0106;
    L_0x00fe:
        r1 = new com.google.android.gms.internal.ads.zzfx;
        r2 = "Lacing only supported in SimpleBlocks.";
        r1.<init>(r2);
        throw r1;
    L_0x0106:
        r0.zzb(r3, r6);
        r15 = r0.zzahz;
        r15 = r15.data;
        r15 = r15[r10];
        r15 = r15 & r14;
        r15 = r15 + r8;
        r0.zzaja = r15;
        r15 = r0.zzajb;
        r5 = r0.zzaja;
        r5 = zza(r15, r5);
        r0.zzajb = r5;
        if (r11 != r12) goto L_0x012f;
    L_0x011f:
        r5 = r0.zzajd;
        r2 = r2 - r5;
        r2 = r2 - r6;
        r5 = r0.zzaja;
        r2 = r2 / r5;
        r5 = r0.zzajb;
        r6 = r0.zzaja;
        java.util.Arrays.fill(r5, r7, r6, r2);
        goto L_0x021f;
    L_0x012f:
        if (r11 != r8) goto L_0x0168;
    L_0x0131:
        r5 = r7;
        r10 = r5;
    L_0x0133:
        r11 = r0.zzaja;
        r11 = r11 - r8;
        if (r5 >= r11) goto L_0x015a;
    L_0x0138:
        r11 = r0.zzajb;
        r11[r5] = r7;
    L_0x013c:
        r6 = r6 + r8;
        r0.zzb(r3, r6);
        r11 = r0.zzahz;
        r11 = r11.data;
        r13 = r6 + -1;
        r11 = r11[r13];
        r11 = r11 & r14;
        r13 = r0.zzajb;
        r15 = r13[r5];
        r15 = r15 + r11;
        r13[r5] = r15;
        if (r11 == r14) goto L_0x013c;
    L_0x0152:
        r11 = r0.zzajb;
        r11 = r11[r5];
        r10 = r10 + r11;
        r5 = r5 + 1;
        goto L_0x0133;
    L_0x015a:
        r5 = r0.zzajb;
        r11 = r0.zzaja;
        r11 = r11 - r8;
        r13 = r0.zzajd;
        r2 = r2 - r13;
        r2 = r2 - r6;
        r2 = r2 - r10;
        r5[r11] = r2;
        goto L_0x021f;
    L_0x0168:
        if (r11 != r10) goto L_0x0272;
    L_0x016a:
        r5 = r7;
        r10 = r5;
    L_0x016c:
        r11 = r0.zzaja;
        r11 = r11 - r8;
        if (r5 >= r11) goto L_0x0212;
    L_0x0171:
        r11 = r0.zzajb;
        r11[r5] = r7;
        r6 = r6 + 1;
        r0.zzb(r3, r6);
        r11 = r0.zzahz;
        r11 = r11.data;
        r15 = r6 + -1;
        r11 = r11[r15];
        if (r11 != 0) goto L_0x018c;
    L_0x0184:
        r1 = new com.google.android.gms.internal.ads.zzfx;
        r2 = "No valid varint length mask found";
        r1.<init>(r2);
        throw r1;
    L_0x018c:
        r16 = 0;
        r11 = r7;
    L_0x018f:
        if (r11 >= r9) goto L_0x01de;
    L_0x0191:
        r18 = 7 - r11;
        r18 = r8 << r18;
        r12 = r0.zzahz;
        r12 = r12.data;
        r12 = r12[r15];
        r12 = r12 & r18;
        if (r12 == 0) goto L_0x01d8;
    L_0x019f:
        r6 = r6 + r11;
        r0.zzb(r3, r6);
        r12 = r0.zzahz;
        r12 = r12.data;
        r16 = r15 + 1;
        r12 = r12[r15];
        r12 = r12 & r14;
        r15 = r18 ^ -1;
        r12 = r12 & r15;
        r7 = (long) r12;
        r21 = r7;
        r7 = r16;
        r16 = r21;
    L_0x01b6:
        if (r7 >= r6) goto L_0x01ca;
    L_0x01b8:
        r16 = r16 << r9;
        r8 = r0.zzahz;
        r8 = r8.data;
        r12 = r7 + 1;
        r7 = r8[r7];
        r7 = r7 & r14;
        r7 = (long) r7;
        r19 = r16 | r7;
        r7 = r12;
        r16 = r19;
        goto L_0x01b6;
    L_0x01ca:
        if (r5 <= 0) goto L_0x01de;
    L_0x01cc:
        r11 = r11 * 7;
        r11 = r11 + r13;
        r7 = 1;
        r11 = r7 << r11;
        r19 = r11 - r7;
        r7 = r16 - r19;
        goto L_0x01e0;
    L_0x01d8:
        r11 = r11 + 1;
        r7 = 0;
        r8 = 1;
        r12 = 2;
        goto L_0x018f;
    L_0x01de:
        r7 = r16;
    L_0x01e0:
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r15 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
        if (r15 < 0) goto L_0x020a;
    L_0x01e7:
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r15 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
        if (r15 <= 0) goto L_0x01ef;
    L_0x01ee:
        goto L_0x020a;
    L_0x01ef:
        r7 = (int) r7;
        r8 = r0.zzajb;
        if (r5 != 0) goto L_0x01f5;
    L_0x01f4:
        goto L_0x01fc;
    L_0x01f5:
        r11 = r0.zzajb;
        r12 = r5 + -1;
        r11 = r11[r12];
        r7 = r7 + r11;
    L_0x01fc:
        r8[r5] = r7;
        r7 = r0.zzajb;
        r7 = r7[r5];
        r10 = r10 + r7;
        r5 = r5 + 1;
        r7 = 0;
        r8 = 1;
        r12 = 2;
        goto L_0x016c;
    L_0x020a:
        r1 = new com.google.android.gms.internal.ads.zzfx;
        r2 = "EBML lacing sample size out of range.";
        r1.<init>(r2);
        throw r1;
    L_0x0212:
        r5 = r0.zzajb;
        r7 = r0.zzaja;
        r8 = 1;
        r7 = r7 - r8;
        r8 = r0.zzajd;
        r2 = r2 - r8;
        r2 = r2 - r6;
        r2 = r2 - r10;
        r5[r7] = r2;
    L_0x021f:
        r2 = r0.zzahz;
        r2 = r2.data;
        r5 = 0;
        r2 = r2[r5];
        r2 = r2 << r9;
        r5 = r0.zzahz;
        r5 = r5.data;
        r6 = 1;
        r5 = r5[r6];
        r5 = r5 & r14;
        r2 = r2 | r5;
        r5 = r0.zzais;
        r7 = (long) r2;
        r7 = r0.zzs(r7);
        r10 = r5 + r7;
        r0.zzaix = r10;
        r2 = r0.zzahz;
        r2 = r2.data;
        r5 = 2;
        r2 = r2[r5];
        r2 = r2 & r9;
        if (r2 != r9) goto L_0x0247;
    L_0x0245:
        r2 = 1;
        goto L_0x0248;
    L_0x0247:
        r2 = 0;
    L_0x0248:
        r6 = r4.type;
        if (r6 == r5) goto L_0x025e;
    L_0x024c:
        r6 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        if (r1 != r6) goto L_0x025c;
    L_0x0250:
        r6 = r0.zzahz;
        r6 = r6.data;
        r6 = r6[r5];
        r5 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r6 = r6 & r5;
        if (r6 != r5) goto L_0x025c;
    L_0x025b:
        goto L_0x025e;
    L_0x025c:
        r5 = 0;
        goto L_0x025f;
    L_0x025e:
        r5 = 1;
    L_0x025f:
        if (r2 == 0) goto L_0x0264;
    L_0x0261:
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        goto L_0x0265;
    L_0x0264:
        r7 = 0;
    L_0x0265:
        r2 = r5 | r7;
        r0.zzaje = r2;
        r2 = 2;
        r0.zzaiw = r2;
        r2 = 0;
        r0.zzaiz = r2;
        r2 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        goto L_0x028c;
    L_0x0272:
        r1 = new com.google.android.gms.internal.ads.zzfx;
        r2 = 36;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r2);
        r2 = "Unexpected lacing value: ";
        r3.append(r2);
        r3.append(r11);
        r2 = r3.toString();
        r1.<init>(r2);
        throw r1;
    L_0x028b:
        r2 = r5;
    L_0x028c:
        if (r1 != r2) goto L_0x02b7;
    L_0x028e:
        r1 = r0.zzaiz;
        r2 = r0.zzaja;
        if (r1 >= r2) goto L_0x02b3;
    L_0x0294:
        r1 = r0.zzajb;
        r2 = r0.zzaiz;
        r1 = r1[r2];
        r0.zza(r3, r4, r1);
        r1 = r0.zzaix;
        r5 = r0.zzaiz;
        r6 = r4.zzajt;
        r5 = r5 * r6;
        r5 = r5 / 1000;
        r5 = (long) r5;
        r7 = r1 + r5;
        r0.zza(r4, r7);
        r1 = r0.zzaiz;
        r2 = 1;
        r1 = r1 + r2;
        r0.zzaiz = r1;
        goto L_0x028e;
    L_0x02b3:
        r1 = 0;
        r0.zzaiw = r1;
        return;
    L_0x02b7:
        r1 = 0;
        r2 = r0.zzajb;
        r1 = r2[r1];
        r0.zza(r3, r4, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzip.zza(int, int, com.google.android.gms.internal.ads.zzia):void");
    }

    private final void zza(zzis zzis, long j) {
        if ("S_TEXT/UTF8".equals(zzis.zzajs)) {
            Object obj;
            byte[] bArr = this.zzaid.data;
            long j2 = this.zzaiy;
            if (j2 == C.TIME_UNSET) {
                obj = zzahs;
            } else {
                long j3 = j2 - (((long) ((int) (j2 / 3600000000L))) * 3600000000L);
                long j4 = j3 - ((long) (60000000 * ((int) (j3 / 60000000))));
                int i = (int) ((j4 - ((long) (1000000 * ((int) (j4 / 1000000))))) / 1000);
                obj = zzqe.zzaj(String.format(Locale.US, "%02d:%02d:%02d,%03d", new Object[]{Integer.valueOf((int) (j2 / 3600000000L)), Integer.valueOf((int) (j3 / 60000000)), Integer.valueOf((int) (j4 / 1000000)), Integer.valueOf(i)}));
            }
            System.arraycopy(obj, 0, bArr, 19, 12);
            zzis.zzakw.zza(this.zzaid, this.zzaid.limit());
            this.zzajn += this.zzaid.limit();
        }
        zzis.zzakw.zza(j, this.zzaje, this.zzajn, 0, zzis.zzajw);
        this.zzajo = true;
        zzeb();
    }

    private final void zzeb() {
        this.zzajf = 0;
        this.zzajn = 0;
        this.zzajm = 0;
        this.zzajg = false;
        this.zzajh = false;
        this.zzajj = false;
        this.zzajl = 0;
        this.zzajk = (byte) 0;
        this.zzaji = false;
        this.zzaic.reset();
    }

    private final void zzb(zzia zzia, int i) throws IOException, InterruptedException {
        if (this.zzahz.limit() < i) {
            if (this.zzahz.capacity() < i) {
                this.zzahz.zzc(Arrays.copyOf(this.zzahz.data, Math.max(this.zzahz.data.length << 1, i)), this.zzahz.limit());
            }
            zzia.readFully(this.zzahz.data, this.zzahz.limit(), i - this.zzahz.limit());
            this.zzahz.zzbk(i);
        }
    }

    private final void zza(zzia zzia, zzis zzis, int i) throws IOException, InterruptedException {
        if ("S_TEXT/UTF8".equals(zzis.zzajs)) {
            int length = zzahr.length + i;
            if (this.zzaid.capacity() < length) {
                this.zzaid.data = Arrays.copyOf(zzahr, length + i);
            }
            zzia.readFully(this.zzaid.data, zzahr.length, i);
            this.zzaid.setPosition(0);
            this.zzaid.zzbk(length);
            return;
        }
        int i2;
        zzii zzii = zzis.zzakw;
        if (!this.zzajg) {
            if (zzis.zzaju) {
                this.zzaje &= -1073741825;
                int i3 = 128;
                if (!this.zzajh) {
                    zzia.readFully(this.zzahz.data, 0, 1);
                    this.zzajf++;
                    if ((this.zzahz.data[0] & 128) == 128) {
                        throw new zzfx("Extension bit is set in signal byte");
                    }
                    this.zzajk = this.zzahz.data[0];
                    this.zzajh = true;
                }
                if ((this.zzajk & 1) == 1) {
                    int i4 = (this.zzajk & 2) == 2 ? 1 : 0;
                    this.zzaje |= 1073741824;
                    if (!this.zzaji) {
                        zzia.readFully(this.zzaie.data, 0, 8);
                        this.zzajf += 8;
                        this.zzaji = true;
                        byte[] bArr = this.zzahz.data;
                        if (i4 == 0) {
                            i3 = 0;
                        }
                        bArr[0] = (byte) (i3 | 8);
                        this.zzahz.setPosition(0);
                        zzii.zza(this.zzahz, 1);
                        this.zzajn++;
                        this.zzaie.setPosition(0);
                        zzii.zza(this.zzaie, 8);
                        this.zzajn += 8;
                    }
                    if (i4 != 0) {
                        if (!this.zzajj) {
                            zzia.readFully(this.zzahz.data, 0, 1);
                            this.zzajf++;
                            this.zzahz.setPosition(0);
                            this.zzajl = this.zzahz.readUnsignedByte();
                            this.zzajj = true;
                        }
                        i4 = this.zzajl << 2;
                        this.zzahz.reset(i4);
                        zzia.readFully(this.zzahz.data, 0, i4);
                        this.zzajf += i4;
                        short s = (short) ((this.zzajl / 2) + 1);
                        i3 = (s * 6) + 2;
                        if (this.zzaig == null || this.zzaig.capacity() < i3) {
                            this.zzaig = ByteBuffer.allocate(i3);
                        }
                        this.zzaig.position(0);
                        this.zzaig.putShort(s);
                        i4 = 0;
                        i2 = i4;
                        while (i4 < this.zzajl) {
                            int zzhg = this.zzahz.zzhg();
                            if (i4 % 2 == 0) {
                                this.zzaig.putShort((short) (zzhg - i2));
                            } else {
                                this.zzaig.putInt(zzhg - i2);
                            }
                            i4++;
                            i2 = zzhg;
                        }
                        i4 = (i - this.zzajf) - i2;
                        if (this.zzajl % 2 == 1) {
                            this.zzaig.putInt(i4);
                        } else {
                            this.zzaig.putShort((short) i4);
                            this.zzaig.putInt(0);
                        }
                        this.zzaif.zzc(this.zzaig.array(), i3);
                        zzii.zza(this.zzaif, i3);
                        this.zzajn += i3;
                    }
                }
            } else if (zzis.zzajv != null) {
                this.zzaic.zzc(zzis.zzajv, zzis.zzajv.length);
            }
            this.zzajg = true;
        }
        i += this.zzaic.limit();
        if ("V_MPEG4/ISO/AVC".equals(zzis.zzajs) || "V_MPEGH/ISO/HEVC".equals(zzis.zzajs)) {
            byte[] bArr2 = this.zzahy.data;
            bArr2[0] = (byte) 0;
            bArr2[1] = (byte) 0;
            bArr2[2] = (byte) 0;
            int i5 = zzis.zzakx;
            int i6 = 4 - zzis.zzakx;
            while (this.zzajf < i) {
                if (this.zzajm == 0) {
                    i2 = Math.min(i5, this.zzaic.zzhb());
                    zzia.readFully(bArr2, i6 + i2, i5 - i2);
                    if (i2 > 0) {
                        this.zzaic.zze(bArr2, i6, i2);
                    }
                    this.zzajf += i5;
                    this.zzahy.setPosition(0);
                    this.zzajm = this.zzahy.zzhg();
                    this.zzahx.setPosition(0);
                    zzii.zza(this.zzahx, 4);
                    this.zzajn += 4;
                } else {
                    this.zzajm -= zza(zzia, zzii, this.zzajm);
                }
            }
        } else {
            while (this.zzajf < i) {
                zza(zzia, zzii, i - this.zzajf);
            }
        }
        if ("A_VORBIS".equals(zzis.zzajs)) {
            this.zzaia.setPosition(0);
            zzii.zza(this.zzaia, 4);
            this.zzajn += 4;
        }
    }

    private final int zza(zzia zzia, zzii zzii, int i) throws IOException, InterruptedException {
        int min;
        int zzhb = this.zzaic.zzhb();
        if (zzhb > 0) {
            min = Math.min(i, zzhb);
            zzii.zza(this.zzaic, min);
        } else {
            min = zzii.zza(zzia, i, false);
        }
        this.zzajf += min;
        this.zzajn += min;
        return min;
    }

    private final long zzs(long j) throws zzfx {
        if (this.zzaij == C.TIME_UNSET) {
            throw new zzfx("Can't scale timecode prior to timecodeScale being set.");
        }
        return zzqe.zza(j, this.zzaij, 1000);
    }

    private static int[] zza(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length << 1, i)];
    }
}
