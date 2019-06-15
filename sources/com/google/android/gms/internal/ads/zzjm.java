package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;

final class zzjm {
    private static final int zzarb = zzqe.zzam("nam");
    private static final int zzarc = zzqe.zzam("trk");
    private static final int zzard = zzqe.zzam("cmt");
    private static final int zzare = zzqe.zzam("day");
    private static final int zzarf = zzqe.zzam("ART");
    private static final int zzarg = zzqe.zzam("too");
    private static final int zzarh = zzqe.zzam("alb");
    private static final int zzari = zzqe.zzam("com");
    private static final int zzarj = zzqe.zzam("wrt");
    private static final int zzark = zzqe.zzam("lyr");
    private static final int zzarl = zzqe.zzam("gen");
    private static final int zzarm = zzqe.zzam("covr");
    private static final int zzarn = zzqe.zzam("gnre");
    private static final int zzaro = zzqe.zzam("grp");
    private static final int zzarp = zzqe.zzam("disk");
    private static final int zzarq = zzqe.zzam("trkn");
    private static final int zzarr = zzqe.zzam("tmpo");
    private static final int zzars = zzqe.zzam("cpil");
    private static final int zzart = zzqe.zzam("aART");
    private static final int zzaru = zzqe.zzam("sonm");
    private static final int zzarv = zzqe.zzam("soal");
    private static final int zzarw = zzqe.zzam("soar");
    private static final int zzarx = zzqe.zzam("soaa");
    private static final int zzary = zzqe.zzam("soco");
    private static final int zzarz = zzqe.zzam("rtng");
    private static final int zzasa = zzqe.zzam("pgap");
    private static final int zzasb = zzqe.zzam("sosn");
    private static final int zzasc = zzqe.zzam("tvsh");
    private static final int zzasd = zzqe.zzam(InternalFrame.ID);
    private static final String[] zzase = new String[]{"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    /* JADX WARNING: Unknown top exception splitter block from list: {B:224:0x02c4=Splitter:B:224:0x02c4, B:212:0x0297=Splitter:B:212:0x0297, B:220:0x02ba=Splitter:B:220:0x02ba} */
    public static com.google.android.gms.internal.ads.zzki.zza zzd(com.google.android.gms.internal.ads.zzpx r13) {
        /*
        r0 = r13.getPosition();
        r1 = r13.readInt();
        r0 = r0 + r1;
        r1 = r13.readInt();
        r2 = r1 >>> 24;
        r3 = 169; // 0xa9 float:2.37E-43 double:8.35E-322;
        r4 = 16;
        r5 = 0;
        if (r2 == r3) goto L_0x01d8;
    L_0x0016:
        r3 = 65533; // 0xfffd float:9.1831E-41 double:3.23776E-319;
        if (r2 != r3) goto L_0x001d;
    L_0x001b:
        goto L_0x01d8;
    L_0x001d:
        r2 = zzarn;	 Catch:{ all -> 0x02ce }
        r3 = 1;
        if (r1 != r2) goto L_0x004a;
    L_0x0022:
        r1 = zze(r13);	 Catch:{ all -> 0x02ce }
        if (r1 <= 0) goto L_0x0033;
    L_0x0028:
        r2 = zzase;	 Catch:{ all -> 0x02ce }
        r2 = r2.length;	 Catch:{ all -> 0x02ce }
        if (r1 > r2) goto L_0x0033;
    L_0x002d:
        r2 = zzase;	 Catch:{ all -> 0x02ce }
        r1 = r1 - r3;
        r1 = r2[r1];	 Catch:{ all -> 0x02ce }
        goto L_0x0034;
    L_0x0033:
        r1 = r5;
    L_0x0034:
        if (r1 == 0) goto L_0x003e;
    L_0x0036:
        r2 = new com.google.android.gms.internal.ads.zzkq;	 Catch:{ all -> 0x02ce }
        r3 = "TCON";
        r2.<init>(r3, r5, r1);	 Catch:{ all -> 0x02ce }
        goto L_0x0046;
    L_0x003e:
        r1 = "MetadataUtil";
        r2 = "Failed to parse standard genre code";
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x02ce }
        r2 = r5;
    L_0x0046:
        r13.setPosition(r0);
        return r2;
    L_0x004a:
        r2 = zzarp;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0058;
    L_0x004e:
        r2 = "TPOS";
        r1 = zzb(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0058:
        r2 = zzarq;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0066;
    L_0x005c:
        r2 = "TRCK";
        r1 = zzb(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0066:
        r2 = zzarr;	 Catch:{ all -> 0x02ce }
        r6 = 0;
        if (r1 != r2) goto L_0x0075;
    L_0x006b:
        r2 = "TBPM";
        r1 = zza(r1, r2, r13, r3, r6);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0075:
        r2 = zzars;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0083;
    L_0x0079:
        r2 = "TCMP";
        r1 = zza(r1, r2, r13, r3, r3);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0083:
        r2 = zzarm;	 Catch:{ all -> 0x02ce }
        r7 = 4;
        if (r1 != r2) goto L_0x00e3;
    L_0x0088:
        r1 = r13.readInt();	 Catch:{ all -> 0x02ce }
        r2 = r13.readInt();	 Catch:{ all -> 0x02ce }
        r3 = com.google.android.gms.internal.ads.zziv.zzaof;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x00d8;
    L_0x0094:
        r2 = r13.readInt();	 Catch:{ all -> 0x02ce }
        r2 = com.google.android.gms.internal.ads.zziv.zzag(r2);	 Catch:{ all -> 0x02ce }
        r3 = 13;
        if (r2 != r3) goto L_0x00a3;
    L_0x00a0:
        r3 = "image/jpeg";
        goto L_0x00ab;
    L_0x00a3:
        r3 = 14;
        if (r2 != r3) goto L_0x00aa;
    L_0x00a7:
        r3 = "image/png";
        goto L_0x00ab;
    L_0x00aa:
        r3 = r5;
    L_0x00ab:
        if (r3 != 0) goto L_0x00c6;
    L_0x00ad:
        r1 = "MetadataUtil";
        r3 = 41;
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02ce }
        r4.<init>(r3);	 Catch:{ all -> 0x02ce }
        r3 = "Unrecognized cover art flags: ";
        r4.append(r3);	 Catch:{ all -> 0x02ce }
        r4.append(r2);	 Catch:{ all -> 0x02ce }
        r2 = r4.toString();	 Catch:{ all -> 0x02ce }
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x02ce }
        goto L_0x00df;
    L_0x00c6:
        r13.zzbl(r7);	 Catch:{ all -> 0x02ce }
        r1 = r1 - r4;
        r1 = new byte[r1];	 Catch:{ all -> 0x02ce }
        r2 = r1.length;	 Catch:{ all -> 0x02ce }
        r13.zze(r1, r6, r2);	 Catch:{ all -> 0x02ce }
        r2 = new com.google.android.gms.internal.ads.zzkk;	 Catch:{ all -> 0x02ce }
        r4 = 3;
        r2.<init>(r3, r5, r4, r1);	 Catch:{ all -> 0x02ce }
        r5 = r2;
        goto L_0x00df;
    L_0x00d8:
        r1 = "MetadataUtil";
        r2 = "Failed to parse cover art attribute";
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x02ce }
    L_0x00df:
        r13.setPosition(r0);
        return r5;
    L_0x00e3:
        r2 = zzart;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x00f1;
    L_0x00e7:
        r2 = "TPE2";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x00f1:
        r2 = zzaru;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x00ff;
    L_0x00f5:
        r2 = "TSOT";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x00ff:
        r2 = zzarv;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x010d;
    L_0x0103:
        r2 = "TSO2";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x010d:
        r2 = zzarw;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x011b;
    L_0x0111:
        r2 = "TSOA";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x011b:
        r2 = zzarx;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0129;
    L_0x011f:
        r2 = "TSOP";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0129:
        r2 = zzary;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0137;
    L_0x012d:
        r2 = "TSOC";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0137:
        r2 = zzarz;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0145;
    L_0x013b:
        r2 = "ITUNESADVISORY";
        r1 = zza(r1, r2, r13, r6, r6);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0145:
        r2 = zzasa;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0153;
    L_0x0149:
        r2 = "ITUNESGAPLESS";
        r1 = zza(r1, r2, r13, r6, r3);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0153:
        r2 = zzasb;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0161;
    L_0x0157:
        r2 = "TVSHOWSORT";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0161:
        r2 = zzasc;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x016f;
    L_0x0165:
        r2 = "TVSHOW";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x016f:
        r2 = zzasd;	 Catch:{ all -> 0x02ce }
        if (r1 != r2) goto L_0x0297;
    L_0x0173:
        r1 = -1;
        r6 = r1;
        r8 = r6;
        r2 = r5;
        r3 = r2;
    L_0x0178:
        r9 = r13.getPosition();	 Catch:{ all -> 0x02ce }
        if (r9 >= r0) goto L_0x01af;
    L_0x017e:
        r9 = r13.getPosition();	 Catch:{ all -> 0x02ce }
        r10 = r13.readInt();	 Catch:{ all -> 0x02ce }
        r11 = r13.readInt();	 Catch:{ all -> 0x02ce }
        r13.zzbl(r7);	 Catch:{ all -> 0x02ce }
        r12 = com.google.android.gms.internal.ads.zziv.zzaod;	 Catch:{ all -> 0x02ce }
        if (r11 != r12) goto L_0x0198;
    L_0x0191:
        r10 = r10 + -12;
        r2 = r13.zzbm(r10);	 Catch:{ all -> 0x02ce }
        goto L_0x0178;
    L_0x0198:
        r12 = com.google.android.gms.internal.ads.zziv.zzaoe;	 Catch:{ all -> 0x02ce }
        if (r11 != r12) goto L_0x01a3;
    L_0x019c:
        r10 = r10 + -12;
        r3 = r13.zzbm(r10);	 Catch:{ all -> 0x02ce }
        goto L_0x0178;
    L_0x01a3:
        r12 = com.google.android.gms.internal.ads.zziv.zzaof;	 Catch:{ all -> 0x02ce }
        if (r11 != r12) goto L_0x01a9;
    L_0x01a7:
        r6 = r9;
        r8 = r10;
    L_0x01a9:
        r10 = r10 + -12;
        r13.zzbl(r10);	 Catch:{ all -> 0x02ce }
        goto L_0x0178;
    L_0x01af:
        r7 = "com.apple.iTunes";
        r2 = r7.equals(r2);	 Catch:{ all -> 0x02ce }
        if (r2 == 0) goto L_0x01d4;
    L_0x01b7:
        r2 = "iTunSMPB";
        r2 = r2.equals(r3);	 Catch:{ all -> 0x02ce }
        if (r2 == 0) goto L_0x01d4;
    L_0x01bf:
        if (r6 != r1) goto L_0x01c2;
    L_0x01c1:
        goto L_0x01d4;
    L_0x01c2:
        r13.setPosition(r6);	 Catch:{ all -> 0x02ce }
        r13.zzbl(r4);	 Catch:{ all -> 0x02ce }
        r8 = r8 - r4;
        r1 = r13.zzbm(r8);	 Catch:{ all -> 0x02ce }
        r5 = new com.google.android.gms.internal.ads.zzkm;	 Catch:{ all -> 0x02ce }
        r2 = "und";
        r5.<init>(r2, r3, r1);	 Catch:{ all -> 0x02ce }
    L_0x01d4:
        r13.setPosition(r0);
        return r5;
    L_0x01d8:
        r2 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r2 = r2 & r1;
        r3 = zzard;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x0221;
    L_0x01e0:
        r2 = r13.readInt();	 Catch:{ all -> 0x02ce }
        r3 = r13.readInt();	 Catch:{ all -> 0x02ce }
        r6 = com.google.android.gms.internal.ads.zziv.zzaof;	 Catch:{ all -> 0x02ce }
        if (r3 != r6) goto L_0x01fe;
    L_0x01ec:
        r1 = 8;
        r13.zzbl(r1);	 Catch:{ all -> 0x02ce }
        r2 = r2 - r4;
        r1 = r13.zzbm(r2);	 Catch:{ all -> 0x02ce }
        r5 = new com.google.android.gms.internal.ads.zzkm;	 Catch:{ all -> 0x02ce }
        r2 = "und";
        r5.<init>(r2, r1, r1);	 Catch:{ all -> 0x02ce }
        goto L_0x021d;
    L_0x01fe:
        r2 = "MetadataUtil";
        r3 = "Failed to parse comment attribute: ";
        r1 = com.google.android.gms.internal.ads.zziv.zzah(r1);	 Catch:{ all -> 0x02ce }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x02ce }
        r4 = r1.length();	 Catch:{ all -> 0x02ce }
        if (r4 == 0) goto L_0x0215;
    L_0x0210:
        r1 = r3.concat(r1);	 Catch:{ all -> 0x02ce }
        goto L_0x021a;
    L_0x0215:
        r1 = new java.lang.String;	 Catch:{ all -> 0x02ce }
        r1.<init>(r3);	 Catch:{ all -> 0x02ce }
    L_0x021a:
        android.util.Log.w(r2, r1);	 Catch:{ all -> 0x02ce }
    L_0x021d:
        r13.setPosition(r0);
        return r5;
    L_0x0221:
        r3 = zzarb;	 Catch:{ all -> 0x02ce }
        if (r2 == r3) goto L_0x02c4;
    L_0x0225:
        r3 = zzarc;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x022b;
    L_0x0229:
        goto L_0x02c4;
    L_0x022b:
        r3 = zzari;	 Catch:{ all -> 0x02ce }
        if (r2 == r3) goto L_0x02ba;
    L_0x022f:
        r3 = zzarj;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x0235;
    L_0x0233:
        goto L_0x02ba;
    L_0x0235:
        r3 = zzare;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x0243;
    L_0x0239:
        r2 = "TDRC";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0243:
        r3 = zzarf;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x0251;
    L_0x0247:
        r2 = "TPE1";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0251:
        r3 = zzarg;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x025f;
    L_0x0255:
        r2 = "TSSE";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x025f:
        r3 = zzarh;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x026d;
    L_0x0263:
        r2 = "TALB";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x026d:
        r3 = zzark;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x027b;
    L_0x0271:
        r2 = "USLT";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x027b:
        r3 = zzarl;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x0289;
    L_0x027f:
        r2 = "TCON";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0289:
        r3 = zzaro;	 Catch:{ all -> 0x02ce }
        if (r2 != r3) goto L_0x0297;
    L_0x028d:
        r2 = "TIT1";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x0297:
        r2 = "MetadataUtil";
        r3 = "Skipped unknown metadata entry: ";
        r1 = com.google.android.gms.internal.ads.zziv.zzah(r1);	 Catch:{ all -> 0x02ce }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x02ce }
        r4 = r1.length();	 Catch:{ all -> 0x02ce }
        if (r4 == 0) goto L_0x02ae;
    L_0x02a9:
        r1 = r3.concat(r1);	 Catch:{ all -> 0x02ce }
        goto L_0x02b3;
    L_0x02ae:
        r1 = new java.lang.String;	 Catch:{ all -> 0x02ce }
        r1.<init>(r3);	 Catch:{ all -> 0x02ce }
    L_0x02b3:
        android.util.Log.d(r2, r1);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r5;
    L_0x02ba:
        r2 = "TCOM";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x02c4:
        r2 = "TIT2";
        r1 = zza(r1, r2, r13);	 Catch:{ all -> 0x02ce }
        r13.setPosition(r0);
        return r1;
    L_0x02ce:
        r1 = move-exception;
        r13.setPosition(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjm.zzd(com.google.android.gms.internal.ads.zzpx):com.google.android.gms.internal.ads.zzki$zza");
    }

    private static zzkq zza(int i, String str, zzpx zzpx) {
        int readInt = zzpx.readInt();
        if (zzpx.readInt() == zziv.zzaof) {
            zzpx.zzbl(8);
            return new zzkq(str, null, zzpx.zzbm(readInt - 16));
        }
        str = "MetadataUtil";
        String str2 = "Failed to parse text attribute: ";
        String valueOf = String.valueOf(zziv.zzah(i));
        Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        return null;
    }

    private static zzkp zza(int i, String str, zzpx zzpx, boolean z, boolean z2) {
        int zze = zze(zzpx);
        if (z2) {
            zze = Math.min(1, zze);
        }
        if (zze < 0) {
            str = "MetadataUtil";
            String str2 = "Failed to parse uint8 attribute: ";
            String valueOf = String.valueOf(zziv.zzah(i));
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return null;
        } else if (z) {
            return new zzkq(str, null, Integer.toString(zze));
        } else {
            return new zzkm(C.LANGUAGE_UNDETERMINED, str, Integer.toString(zze));
        }
    }

    private static zzkq zzb(int i, String str, zzpx zzpx) {
        String stringBuilder;
        int readInt = zzpx.readInt();
        if (zzpx.readInt() == zziv.zzaof && readInt >= 22) {
            zzpx.zzbl(10);
            readInt = zzpx.readUnsignedShort();
            if (readInt > 0) {
                StringBuilder stringBuilder2 = new StringBuilder(11);
                stringBuilder2.append(readInt);
                stringBuilder = stringBuilder2.toString();
                int readUnsignedShort = zzpx.readUnsignedShort();
                if (readUnsignedShort > 0) {
                    stringBuilder = String.valueOf(stringBuilder);
                    stringBuilder2 = new StringBuilder(12 + String.valueOf(stringBuilder).length());
                    stringBuilder2.append(stringBuilder);
                    stringBuilder2.append("/");
                    stringBuilder2.append(readUnsignedShort);
                    stringBuilder = stringBuilder2.toString();
                }
                return new zzkq(str, null, stringBuilder);
            }
        }
        str = "MetadataUtil";
        String str2 = "Failed to parse index/count attribute: ";
        stringBuilder = String.valueOf(zziv.zzah(i));
        Log.w(str, stringBuilder.length() != 0 ? str2.concat(stringBuilder) : new String(str2));
        return null;
    }

    private static int zze(zzpx zzpx) {
        zzpx.zzbl(4);
        if (zzpx.readInt() == zziv.zzaof) {
            zzpx.zzbl(8);
            return zzpx.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
