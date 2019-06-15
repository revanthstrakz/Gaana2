package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.internal.ads.zzhp.zza;
import com.til.colombia.android.internal.e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class zznk extends DefaultHandler implements zzpm<zznj> {
    private static final Pattern zzbcz = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern zzbda = Pattern.compile("CC([1-4])=.*");
    private static final Pattern zzbdb = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private final String zzbdc;
    private final XmlPullParserFactory zzbdd;

    public zznk() {
        this(null);
    }

    private zznk(String str) {
        this.zzbdc = null;
        try {
            this.zzbdd = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:230:0x05a4 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x05a1 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x05c2 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x05a1 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x05a4 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x05c2 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x05a4 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x05a1 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x05c2 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x047e A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x05a1 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x05a4 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x05c2 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x047e A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x05a4 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x05a1 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x05c2 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x047e A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x05a1 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x05a4 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x05c2 A:{Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06e8 A:{LOOP_END, LOOP:2: B:56:0x01e0->B:279:0x06e8, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0654 A:{SYNTHETIC, EDGE_INSN: B:334:0x0654->B:263:0x0654 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0654 A:{SYNTHETIC, EDGE_INSN: B:334:0x0654->B:263:0x0654 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06e8 A:{LOOP_END, LOOP:2: B:56:0x01e0->B:279:0x06e8, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06e8 A:{LOOP_END, LOOP:2: B:56:0x01e0->B:279:0x06e8, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0654 A:{SYNTHETIC, EDGE_INSN: B:334:0x0654->B:263:0x0654 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0654 A:{SYNTHETIC, EDGE_INSN: B:334:0x0654->B:263:0x0654 ?: BREAK  } */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x06e8 A:{LOOP_END, LOOP:2: B:56:0x01e0->B:279:0x06e8, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x07b2 A:{LOOP_END, LOOP:1: B:47:0x0124->B:305:0x07b2, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:333:0x0744 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:333:0x0744 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x07b2 A:{LOOP_END, LOOP:1: B:47:0x0124->B:305:0x07b2, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x07b2 A:{LOOP_END, LOOP:1: B:47:0x0124->B:305:0x07b2, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:333:0x0744 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x0807 A:{LOOP_END, LOOP:0: B:31:0x0094->B:324:0x0807, Catch:{ XmlPullParserException -> 0x081f }} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x07cf A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x07cf A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x0807 A:{LOOP_END, LOOP:0: B:31:0x0094->B:324:0x0807, Catch:{ XmlPullParserException -> 0x081f }} */
    private final com.google.android.gms.internal.ads.zznj zzc(android.net.Uri r88, java.io.InputStream r89) throws java.io.IOException {
        /*
        r87 = this;
        r1 = r87;
        r2 = r1.zzbdd;	 Catch:{ XmlPullParserException -> 0x081f }
        r2 = r2.newPullParser();	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = 0;
        r4 = r89;
        r2.setInput(r4, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = r2.next();	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = 2;
        if (r4 != r5) goto L_0x0817;
    L_0x0015:
        r4 = "MPD";
        r6 = r2.getName();	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = r4.equals(r6);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 != 0) goto L_0x0023;
    L_0x0021:
        goto L_0x0817;
    L_0x0023:
        r4 = r88.toString();	 Catch:{ XmlPullParserException -> 0x081f }
        r6 = "availabilityStartTime";
        r6 = r2.getAttributeValue(r3, r6);	 Catch:{ XmlPullParserException -> 0x081f }
        r7 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        if (r6 != 0) goto L_0x0036;
    L_0x0034:
        r12 = r7;
        goto L_0x003b;
    L_0x0036:
        r9 = com.google.android.gms.internal.ads.zzqe.zzal(r6);	 Catch:{ XmlPullParserException -> 0x081f }
        r12 = r9;
    L_0x003b:
        r6 = "mediaPresentationDuration";
        r9 = zza(r2, r6, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        r6 = "minBufferTime";
        r16 = zza(r2, r6, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        r6 = "type";
        r6 = r2.getAttributeValue(r3, r6);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r6 == 0) goto L_0x005a;
    L_0x004f:
        r15 = "dynamic";
        r6 = r6.equals(r15);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r6 == 0) goto L_0x005a;
    L_0x0057:
        r18 = 1;
        goto L_0x005c;
    L_0x005a:
        r18 = 0;
    L_0x005c:
        if (r18 == 0) goto L_0x0065;
    L_0x005e:
        r6 = "minimumUpdatePeriod";
        r19 = zza(r2, r6, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x0067;
    L_0x0065:
        r19 = r7;
    L_0x0067:
        if (r18 == 0) goto L_0x0070;
    L_0x0069:
        r6 = "timeShiftBufferDepth";
        r21 = zza(r2, r6, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x0072;
    L_0x0070:
        r21 = r7;
    L_0x0072:
        if (r18 == 0) goto L_0x007b;
    L_0x0074:
        r6 = "suggestedPresentationDelay";
        r23 = zza(r2, r6, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x007d;
    L_0x007b:
        r23 = r7;
    L_0x007d:
        r6 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r6.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r18 == 0) goto L_0x0087;
    L_0x0084:
        r25 = r7;
        goto L_0x0089;
    L_0x0087:
        r25 = 0;
    L_0x0089:
        r15 = r4;
        r28 = r12;
        r11 = r25;
        r4 = 0;
        r25 = 0;
        r13 = r3;
        r26 = r13;
    L_0x0094:
        r2.next();	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = "BaseURL";
        r5 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x00be;
    L_0x009f:
        if (r4 != 0) goto L_0x00b0;
    L_0x00a1:
        r4 = zzb(r2, r15);	 Catch:{ XmlPullParserException -> 0x081f }
        r15 = r4;
        r14 = r6;
        r46 = r9;
        r44 = r13;
        r31 = 1;
    L_0x00ad:
        r4 = r3;
        goto L_0x07c7;
    L_0x00b0:
        r31 = r4;
        r14 = r6;
        r46 = r9;
        r32 = r11;
        r44 = r13;
        r45 = r15;
        r4 = r3;
        goto L_0x07c3;
    L_0x00be:
        r5 = "UTCTiming";
        r5 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x00e1;
    L_0x00c6:
        r5 = "schemeIdUri";
        r5 = r2.getAttributeValue(r3, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r13 = "value";
        r13 = r2.getAttributeValue(r3, r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r14 = new com.google.android.gms.internal.ads.zzob;	 Catch:{ XmlPullParserException -> 0x081f }
        r14.<init>(r5, r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r31 = r4;
        r46 = r9;
        r44 = r14;
        r4 = r3;
        r14 = r6;
        goto L_0x07c7;
    L_0x00e1:
        r5 = "Location";
        r5 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x00fb;
    L_0x00e9:
        r5 = r2.nextText();	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = android.net.Uri.parse(r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r31 = r4;
        r26 = r5;
        r14 = r6;
        r46 = r9;
        r44 = r13;
        goto L_0x00ad;
    L_0x00fb:
        r5 = "Period";
        r5 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x00b0;
    L_0x0103:
        if (r25 != 0) goto L_0x00b0;
    L_0x0105:
        r5 = "id";
        r5 = r2.getAttributeValue(r3, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r14 = "start";
        r31 = r4;
        r3 = zza(r2, r14, r11);	 Catch:{ XmlPullParserException -> 0x081f }
        r14 = "duration";
        r32 = r11;
        r11 = zza(r2, r14, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        r14 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r14.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r7 = r15;
        r8 = 0;
        r27 = 0;
    L_0x0124:
        r2.next();	 Catch:{ XmlPullParserException -> 0x081f }
        r34 = r8;
        r8 = "BaseURL";
        r8 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r8);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r8 == 0) goto L_0x0161;
    L_0x0131:
        if (r27 != 0) goto L_0x014d;
    L_0x0133:
        r7 = zzb(r2, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        r52 = r3;
        r54 = r5;
        r48 = r6;
        r46 = r9;
        r49 = r11;
        r44 = r13;
        r3 = r14;
        r45 = r15;
        r8 = r34;
        r4 = 0;
        r27 = 1;
        goto L_0x073c;
    L_0x014d:
        r52 = r3;
        r54 = r5;
        r48 = r6;
        r35 = r7;
        r46 = r9;
        r49 = r11;
        r44 = r13;
        r3 = r14;
        r45 = r15;
    L_0x015e:
        r4 = 0;
        goto L_0x0738;
    L_0x0161:
        r8 = "AdaptationSet";
        r8 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r8);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r8 == 0) goto L_0x06fd;
    L_0x0169:
        r8 = "id";
        r35 = r7;
        r7 = -1;
        r37 = zza(r2, r8, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        r8 = zza(r2);	 Catch:{ XmlPullParserException -> 0x081f }
        r7 = "mimeType";
        r43 = r8;
        r8 = 0;
        r7 = r2.getAttributeValue(r8, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        r44 = r13;
        r13 = "codecs";
        r13 = r2.getAttributeValue(r8, r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r8 = "width";
        r45 = r15;
        r15 = -1;
        r8 = zza(r2, r8, r15);	 Catch:{ XmlPullParserException -> 0x081f }
        r46 = r9;
        r9 = "height";
        r9 = zza(r2, r9, r15);	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r10 = zza(r2, r10);	 Catch:{ XmlPullParserException -> 0x081f }
        r48 = r6;
        r6 = "audioSamplingRate";
        r6 = zza(r2, r6, r15);	 Catch:{ XmlPullParserException -> 0x081f }
        r15 = "lang";
        r49 = r11;
        r11 = 0;
        r12 = r2.getAttributeValue(r11, r15);	 Catch:{ XmlPullParserException -> 0x081f }
        r11 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r11.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r15 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r15.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r51 = r12;
        r12 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r12.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r52 = r3;
        r3 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r3.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r4.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r54 = r5;
        r65 = r14;
        r66 = r15;
        r40 = r34;
        r5 = r35;
        r15 = r43;
        r14 = r51;
        r36 = 0;
        r38 = 0;
        r39 = -1;
    L_0x01e0:
        r2.next();	 Catch:{ XmlPullParserException -> 0x081f }
        r67 = r4;
        r4 = "BaseURL";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x021d;
    L_0x01ed:
        if (r36 != 0) goto L_0x020c;
    L_0x01ef:
        r4 = zzb(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r85 = r3;
        r5 = r4;
        r77 = r6;
        r78 = r7;
        r79 = r8;
        r80 = r9;
        r81 = r10;
        r69 = r11;
        r82 = r13;
        r4 = r66;
        r9 = r67;
        r36 = 1;
        goto L_0x064c;
    L_0x020c:
        r85 = r3;
        r68 = r5;
        r77 = r6;
        r78 = r7;
        r79 = r8;
        r80 = r9;
        r81 = r10;
        r69 = r11;
        goto L_0x0265;
    L_0x021d:
        r4 = "ContentProtection";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x022f;
    L_0x0225:
        r4 = zzb(r2);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x020c;
    L_0x022b:
        r11.add(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x020c;
    L_0x022f:
        r4 = "ContentComponent";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x026d;
    L_0x0237:
        r4 = "lang";
        r68 = r5;
        r5 = 0;
        r4 = r2.getAttributeValue(r5, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r14 != 0) goto L_0x0244;
    L_0x0242:
        r14 = r4;
        goto L_0x024e;
    L_0x0244:
        if (r4 != 0) goto L_0x0247;
    L_0x0246:
        goto L_0x024e;
    L_0x0247:
        r4 = r14.equals(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        com.google.android.gms.internal.ads.zzpo.checkState(r4);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x024e:
        r4 = zza(r2);	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = zzd(r15, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        r85 = r3;
        r15 = r4;
        r77 = r6;
        r78 = r7;
        r79 = r8;
        r80 = r9;
        r81 = r10;
        r69 = r11;
    L_0x0265:
        r82 = r13;
        r4 = r66;
        r9 = r67;
        goto L_0x064a;
    L_0x026d:
        r68 = r5;
        r4 = "Role";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x02ab;
    L_0x0277:
        r4 = "schemeIdUri";
        r5 = 0;
        r4 = zzb(r2, r4, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r69 = r11;
        r11 = "value";
        r11 = zzb(r2, r11, r5);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0286:
        r2.next();	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = "Role";
        r5 = com.google.android.gms.internal.ads.zzqg.zzc(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x0286;
    L_0x0291:
        r5 = "urn:mpeg:dash:role:2011";
        r4 = r5.equals(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x02a3;
    L_0x0299:
        r4 = "main";
        r4 = r4.equals(r11);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x02a3;
    L_0x02a1:
        r4 = 1;
        goto L_0x02a4;
    L_0x02a3:
        r4 = 0;
    L_0x02a4:
        r4 = r38 | r4;
        r85 = r3;
        r38 = r4;
        goto L_0x02bd;
    L_0x02ab:
        r69 = r11;
        r4 = "AudioChannelConfiguration";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x02c8;
    L_0x02b5:
        r4 = zze(r2);	 Catch:{ XmlPullParserException -> 0x081f }
        r85 = r3;
        r39 = r4;
    L_0x02bd:
        r77 = r6;
        r78 = r7;
        r79 = r8;
        r80 = r9;
        r81 = r10;
        goto L_0x0265;
    L_0x02c8:
        r4 = "Accessibility";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x02e7;
    L_0x02d0:
        r4 = "Accessibility";
        r4 = zza(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        r12.add(r4);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x02d9:
        r85 = r3;
        r77 = r6;
        r78 = r7;
        r79 = r8;
        r80 = r9;
        r81 = r10;
        goto L_0x0265;
    L_0x02e7:
        r4 = "SupplementalProperty";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x02f9;
    L_0x02ef:
        r4 = "SupplementalProperty";
        r4 = zza(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        r3.add(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x02d9;
    L_0x02f9:
        r4 = "Representation";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x05ea;
    L_0x0301:
        r4 = "id";
        r5 = 0;
        r55 = r2.getAttributeValue(r5, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = "bandwidth";
        r5 = -1;
        r59 = zza(r2, r4, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = "mimeType";
        r4 = zzb(r2, r4, r7);	 Catch:{ XmlPullParserException -> 0x081f }
        r11 = "codecs";
        r11 = zzb(r2, r11, r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = "width";
        r60 = zza(r2, r5, r8);	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = "height";
        r61 = zza(r2, r5, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        r62 = zza(r2, r10);	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = "audioSamplingRate";
        r5 = zza(r2, r5, r6);	 Catch:{ XmlPullParserException -> 0x081f }
        r77 = r6;
        r6 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r6.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r78 = r7;
        r7 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r7.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r79 = r8;
        r43 = r39;
        r42 = r40;
        r8 = r68;
        r41 = 0;
    L_0x0349:
        r2.next();	 Catch:{ XmlPullParserException -> 0x081f }
        r80 = r9;
        r9 = "BaseURL";
        r9 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x035f;
    L_0x0356:
        if (r41 != 0) goto L_0x03c6;
    L_0x0358:
        r8 = zzb(r2, r8);	 Catch:{ XmlPullParserException -> 0x081f }
        r41 = 1;
        goto L_0x03c6;
    L_0x035f:
        r9 = "AudioChannelConfiguration";
        r9 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x036e;
    L_0x0367:
        r9 = zze(r2);	 Catch:{ XmlPullParserException -> 0x081f }
        r43 = r9;
        goto L_0x03c6;
    L_0x036e:
        r9 = "SegmentBase";
        r9 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0381;
    L_0x0376:
        r9 = r42;
        r9 = (com.google.android.gms.internal.ads.zzny) r9;	 Catch:{ XmlPullParserException -> 0x081f }
        r9 = r1.zza(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x037e:
        r42 = r9;
        goto L_0x03c6;
    L_0x0381:
        r9 = "SegmentList";
        r9 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0392;
    L_0x0389:
        r9 = r42;
        r9 = (com.google.android.gms.internal.ads.zznv) r9;	 Catch:{ XmlPullParserException -> 0x081f }
        r9 = r1.zza(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x037e;
    L_0x0392:
        r9 = "SegmentTemplate";
        r9 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x03a3;
    L_0x039a:
        r9 = r42;
        r9 = (com.google.android.gms.internal.ads.zznw) r9;	 Catch:{ XmlPullParserException -> 0x081f }
        r9 = r1.zza(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x037e;
    L_0x03a3:
        r9 = "ContentProtection";
        r9 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x03b5;
    L_0x03ab:
        r9 = zzb(r2);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x03c6;
    L_0x03b1:
        r6.add(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x03c6;
    L_0x03b5:
        r9 = "InbandEventStream";
        r9 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x03c6;
    L_0x03bd:
        r9 = "InbandEventStream";
        r9 = zza(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        r7.add(r9);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x03c6:
        r9 = "Representation";
        r9 = com.google.android.gms.internal.ads.zzqg.zzc(r2, r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x05e6;
    L_0x03ce:
        r9 = com.google.android.gms.internal.ads.zzpt.zzab(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0403;
    L_0x03d4:
        if (r11 == 0) goto L_0x03fc;
    L_0x03d6:
        r9 = ",";
        r9 = r11.split(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        r81 = r10;
        r10 = r9.length;	 Catch:{ XmlPullParserException -> 0x081f }
        r82 = r13;
        r13 = 0;
    L_0x03e2:
        if (r13 >= r10) goto L_0x0400;
    L_0x03e4:
        r83 = r10;
        r10 = r9[r13];	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = com.google.android.gms.internal.ads.zzpt.zzae(r10);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r10 == 0) goto L_0x03f7;
    L_0x03ee:
        r41 = com.google.android.gms.internal.ads.zzpt.zzab(r10);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r41 == 0) goto L_0x03f7;
    L_0x03f4:
        r9 = r10;
        goto L_0x047c;
    L_0x03f7:
        r13 = r13 + 1;
        r10 = r83;
        goto L_0x03e2;
    L_0x03fc:
        r81 = r10;
        r82 = r13;
    L_0x0400:
        r9 = 0;
        goto L_0x047c;
    L_0x0403:
        r81 = r10;
        r82 = r13;
        r9 = com.google.android.gms.internal.ads.zzpt.zzac(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x042f;
    L_0x040d:
        if (r11 == 0) goto L_0x0400;
    L_0x040f:
        r9 = ",";
        r9 = r11.split(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = r9.length;	 Catch:{ XmlPullParserException -> 0x081f }
        r13 = 0;
    L_0x0417:
        if (r13 >= r10) goto L_0x0400;
    L_0x0419:
        r84 = r10;
        r10 = r9[r13];	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = com.google.android.gms.internal.ads.zzpt.zzae(r10);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r10 == 0) goto L_0x042a;
    L_0x0423:
        r41 = com.google.android.gms.internal.ads.zzpt.zzac(r10);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r41 == 0) goto L_0x042a;
    L_0x0429:
        goto L_0x03f4;
    L_0x042a:
        r13 = r13 + 1;
        r10 = r84;
        goto L_0x0417;
    L_0x042f:
        r9 = zzx(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0437;
    L_0x0435:
        r9 = r4;
        goto L_0x047c;
    L_0x0437:
        r9 = "application/mp4";
        r9 = r9.equals(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0455;
    L_0x043f:
        r9 = "stpp";
        r9 = r9.equals(r11);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x044a;
    L_0x0447:
        r9 = "application/ttml+xml";
        goto L_0x047c;
    L_0x044a:
        r9 = "wvtt";
        r9 = r9.equals(r11);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0400;
    L_0x0452:
        r9 = "application/x-mp4-vtt";
        goto L_0x047c;
    L_0x0455:
        r9 = "application/x-rawcc";
        r9 = r9.equals(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0400;
    L_0x045d:
        if (r11 == 0) goto L_0x0400;
    L_0x045f:
        r9 = "cea708";
        r9 = r11.contains(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x046a;
    L_0x0467:
        r9 = "application/cea-708";
        goto L_0x047c;
    L_0x046a:
        r9 = "eia608";
        r9 = r11.contains(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 != 0) goto L_0x047a;
    L_0x0472:
        r9 = "cea608";
        r9 = r11.contains(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r9 == 0) goto L_0x0400;
    L_0x047a:
        r9 = "application/cea-608";
    L_0x047c:
        if (r9 == 0) goto L_0x058d;
    L_0x047e:
        r10 = com.google.android.gms.internal.ads.zzpt.zzac(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r10 == 0) goto L_0x0498;
    L_0x0484:
        r63 = 0;
        r56 = r4;
        r57 = r9;
        r58 = r11;
        r64 = r38;
        r4 = com.google.android.gms.internal.ads.zzfs.zza(r55, r56, r57, r58, r59, r60, r61, r62, r63, r64);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0492:
        r85 = r3;
        r72 = r4;
        goto L_0x059f;
    L_0x0498:
        r10 = com.google.android.gms.internal.ads.zzpt.zzab(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r10 == 0) goto L_0x04b3;
    L_0x049e:
        r62 = 0;
        r56 = r4;
        r57 = r9;
        r58 = r11;
        r60 = r43;
        r61 = r5;
        r63 = r38;
        r64 = r14;
        r4 = com.google.android.gms.internal.ads.zzfs.zza(r55, r56, r57, r58, r59, r60, r61, r62, r63, r64);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x0492;
    L_0x04b3:
        r5 = zzx(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x058d;
    L_0x04b9:
        r5 = "application/cea-608";
        r5 = r5.equals(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x051e;
    L_0x04c1:
        r5 = 0;
    L_0x04c2:
        r10 = r12.size();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 >= r10) goto L_0x0518;
    L_0x04c8:
        r10 = r12.get(r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = (com.google.android.gms.internal.ads.zznm) r10;	 Catch:{ XmlPullParserException -> 0x081f }
        r13 = "urn:scte:dash:cc:cea-608:2015";
        r85 = r3;
        r3 = r10.zzbdi;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = r13.equals(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x0513;
    L_0x04da:
        r3 = r10.value;	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x0513;
    L_0x04de:
        r3 = zzbda;	 Catch:{ XmlPullParserException -> 0x081f }
        r13 = r10.value;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = r3.matcher(r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r13 = r3.matches();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r13 == 0) goto L_0x04f6;
    L_0x04ec:
        r13 = 1;
        r3 = r3.group(r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x051b;
    L_0x04f6:
        r3 = "MpdParser";
        r13 = "Unable to parse CEA-608 channel number from: ";
        r10 = r10.value;	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = java.lang.String.valueOf(r10);	 Catch:{ XmlPullParserException -> 0x081f }
        r41 = r10.length();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r41 == 0) goto L_0x050b;
    L_0x0506:
        r10 = r13.concat(r10);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x0510;
    L_0x050b:
        r10 = new java.lang.String;	 Catch:{ XmlPullParserException -> 0x081f }
        r10.<init>(r13);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0510:
        android.util.Log.w(r3, r10);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0513:
        r5 = r5 + 1;
        r3 = r85;
        goto L_0x04c2;
    L_0x0518:
        r85 = r3;
    L_0x051a:
        r3 = -1;
    L_0x051b:
        r62 = r3;
        goto L_0x057e;
    L_0x051e:
        r85 = r3;
        r3 = "application/cea-708";
        r3 = r3.equals(r9);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x057c;
    L_0x0528:
        r3 = 0;
    L_0x0529:
        r5 = r12.size();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 >= r5) goto L_0x051a;
    L_0x052f:
        r5 = r12.get(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = (com.google.android.gms.internal.ads.zznm) r5;	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = "urn:scte:dash:cc:cea-708:2015";
        r13 = r5.zzbdi;	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = r10.equals(r13);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r10 == 0) goto L_0x0579;
    L_0x053f:
        r10 = r5.value;	 Catch:{ XmlPullParserException -> 0x081f }
        if (r10 == 0) goto L_0x0579;
    L_0x0543:
        r10 = zzbdb;	 Catch:{ XmlPullParserException -> 0x081f }
        r13 = r5.value;	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = r10.matcher(r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r13 = r10.matches();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r13 == 0) goto L_0x055b;
    L_0x0551:
        r13 = 1;
        r3 = r10.group(r13);	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x051b;
    L_0x055b:
        r13 = 1;
        r10 = "MpdParser";
        r13 = "Unable to parse CEA-708 service block number from: ";
        r5 = r5.value;	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r30 = r5.length();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r30 == 0) goto L_0x0571;
    L_0x056c:
        r5 = r13.concat(r5);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x0576;
    L_0x0571:
        r5 = new java.lang.String;	 Catch:{ XmlPullParserException -> 0x081f }
        r5.<init>(r13);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0576:
        android.util.Log.w(r10, r5);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0579:
        r3 = r3 + 1;
        goto L_0x0529;
    L_0x057c:
        r62 = -1;
    L_0x057e:
        r56 = r4;
        r57 = r9;
        r58 = r11;
        r60 = r38;
        r61 = r14;
        r3 = com.google.android.gms.internal.ads.zzfs.zza(r55, r56, r57, r58, r59, r60, r61, r62);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x059d;
    L_0x058d:
        r85 = r3;
        r56 = r4;
        r57 = r9;
        r58 = r11;
        r60 = r38;
        r61 = r14;
        r3 = com.google.android.gms.internal.ads.zzfs.zza(r55, r56, r57, r58, r59, r60, r61);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x059d:
        r72 = r3;
    L_0x059f:
        if (r42 == 0) goto L_0x05a4;
    L_0x05a1:
        r74 = r42;
        goto L_0x05ab;
    L_0x05a4:
        r3 = new com.google.android.gms.internal.ads.zzny;	 Catch:{ XmlPullParserException -> 0x081f }
        r3.<init>();	 Catch:{ XmlPullParserException -> 0x081f }
        r74 = r3;
    L_0x05ab:
        r3 = new com.google.android.gms.internal.ads.zznl;	 Catch:{ XmlPullParserException -> 0x081f }
        r71 = r3;
        r73 = r8;
        r75 = r6;
        r76 = r7;
        r71.<init>(r72, r73, r74, r75, r76);	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = r3.zzaad;	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = r4.zzzj;	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 != 0) goto L_0x05da;
    L_0x05c2:
        r5 = com.google.android.gms.internal.ads.zzpt.zzac(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x05ca;
    L_0x05c8:
        r5 = 2;
        goto L_0x05db;
    L_0x05ca:
        r5 = com.google.android.gms.internal.ads.zzpt.zzab(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x05d2;
    L_0x05d0:
        r5 = 1;
        goto L_0x05db;
    L_0x05d2:
        r4 = zzx(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x05da;
    L_0x05d8:
        r5 = 3;
        goto L_0x05db;
    L_0x05da:
        r5 = -1;
    L_0x05db:
        r4 = zzd(r15, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r9 = r67;
        r9.add(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        r15 = r4;
        goto L_0x060c;
    L_0x05e6:
        r9 = r80;
        goto L_0x0349;
    L_0x05ea:
        r85 = r3;
        r77 = r6;
        r78 = r7;
        r79 = r8;
        r80 = r9;
        r81 = r10;
        r82 = r13;
        r9 = r67;
        r3 = "SegmentBase";
        r3 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x060f;
    L_0x0602:
        r3 = r40;
        r3 = (com.google.android.gms.internal.ads.zzny) r3;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = r1.zza(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x060a:
        r40 = r3;
    L_0x060c:
        r4 = r66;
        goto L_0x064a;
    L_0x060f:
        r3 = "SegmentList";
        r3 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x0620;
    L_0x0617:
        r3 = r40;
        r3 = (com.google.android.gms.internal.ads.zznv) r3;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = r1.zza(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x060a;
    L_0x0620:
        r3 = "SegmentTemplate";
        r3 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x0631;
    L_0x0628:
        r3 = r40;
        r3 = (com.google.android.gms.internal.ads.zznw) r3;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = r1.zza(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x060a;
    L_0x0631:
        r3 = "InbandEventStream";
        r3 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x0645;
    L_0x0639:
        r3 = "InbandEventStream";
        r3 = zza(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = r66;
        r4.add(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x064a;
    L_0x0645:
        r4 = r66;
        com.google.android.gms.internal.ads.zzqg.zzf(r2);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x064a:
        r5 = r68;
    L_0x064c:
        r3 = "AdaptationSet";
        r3 = com.google.android.gms.internal.ads.zzqg.zzc(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x06e8;
    L_0x0654:
        r3 = new java.util.ArrayList;	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = r9.size();	 Catch:{ XmlPullParserException -> 0x081f }
        r3.<init>(r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = 0;
    L_0x065e:
        r6 = r9.size();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 >= r6) goto L_0x06d2;
    L_0x0664:
        r6 = r9.get(r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r6 = (com.google.android.gms.internal.ads.zznl) r6;	 Catch:{ XmlPullParserException -> 0x081f }
        r7 = r6.zzaad;	 Catch:{ XmlPullParserException -> 0x081f }
        r8 = r6.zzbdg;	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = r69;
        r8.addAll(r10);	 Catch:{ XmlPullParserException -> 0x081f }
        r11 = r8.isEmpty();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r11 != 0) goto L_0x0682;
    L_0x0679:
        r11 = new com.google.android.gms.internal.ads.zzhp;	 Catch:{ XmlPullParserException -> 0x081f }
        r11.<init>(r8);	 Catch:{ XmlPullParserException -> 0x081f }
        r7 = r7.zza(r11);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0682:
        r59 = r7;
        r7 = r6.zzbdh;	 Catch:{ XmlPullParserException -> 0x081f }
        r7.addAll(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        r8 = r6.zzbde;	 Catch:{ XmlPullParserException -> 0x081f }
        r6 = r6.zzbdf;	 Catch:{ XmlPullParserException -> 0x081f }
        r11 = r6 instanceof com.google.android.gms.internal.ads.zzny;	 Catch:{ XmlPullParserException -> 0x081f }
        if (r11 == 0) goto L_0x06ab;
    L_0x0691:
        r11 = new com.google.android.gms.internal.ads.zzns;	 Catch:{ XmlPullParserException -> 0x081f }
        r67 = 0;
        r68 = -1;
        r72 = r6;
        r72 = (com.google.android.gms.internal.ads.zzny) r72;	 Catch:{ XmlPullParserException -> 0x081f }
        r74 = 0;
        r75 = -1;
        r66 = r11;
        r70 = r59;
        r71 = r8;
        r73 = r7;
        r66.<init>(r67, r68, r70, r71, r72, r73, r74, r75);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x06c2;
    L_0x06ab:
        r11 = r6 instanceof com.google.android.gms.internal.ads.zznu;	 Catch:{ XmlPullParserException -> 0x081f }
        if (r11 == 0) goto L_0x06ca;
    L_0x06af:
        r11 = new com.google.android.gms.internal.ads.zznr;	 Catch:{ XmlPullParserException -> 0x081f }
        r56 = 0;
        r57 = -1;
        r61 = r6;
        r61 = (com.google.android.gms.internal.ads.zznu) r61;	 Catch:{ XmlPullParserException -> 0x081f }
        r55 = r11;
        r60 = r8;
        r62 = r7;
        r55.<init>(r56, r57, r59, r60, r61, r62);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x06c2:
        r3.add(r11);	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = r5 + 1;
        r69 = r10;
        goto L_0x065e;
    L_0x06ca:
        r2 = new java.lang.IllegalArgumentException;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = "segmentBase must be of type SingleSegmentBase or MultiSegmentBase";
        r2.<init>(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        throw r2;	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x06d2:
        r4 = new com.google.android.gms.internal.ads.zzni;	 Catch:{ XmlPullParserException -> 0x081f }
        r36 = r4;
        r38 = r15;
        r39 = r3;
        r40 = r12;
        r41 = r85;
        r36.<init>(r37, r38, r39, r40, r41);	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = r65;
        r3.add(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x015e;
    L_0x06e8:
        r66 = r4;
        r4 = r9;
        r11 = r69;
        r6 = r77;
        r7 = r78;
        r8 = r79;
        r9 = r80;
        r10 = r81;
        r13 = r82;
        r3 = r85;
        goto L_0x01e0;
    L_0x06fd:
        r52 = r3;
        r54 = r5;
        r48 = r6;
        r35 = r7;
        r46 = r9;
        r49 = r11;
        r44 = r13;
        r3 = r14;
        r45 = r15;
        r4 = "SegmentBase";
        r4 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r4 == 0) goto L_0x071d;
    L_0x0716:
        r4 = 0;
        r5 = r1.zza(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x071b:
        r8 = r5;
        goto L_0x073a;
    L_0x071d:
        r4 = 0;
        r5 = "SegmentList";
        r5 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x072b;
    L_0x0726:
        r5 = r1.zza(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x071b;
    L_0x072b:
        r5 = "SegmentTemplate";
        r5 = com.google.android.gms.internal.ads.zzqg.zzd(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x0738;
    L_0x0733:
        r5 = r1.zza(r2, r4);	 Catch:{ XmlPullParserException -> 0x081f }
        goto L_0x071b;
    L_0x0738:
        r8 = r34;
    L_0x073a:
        r7 = r35;
    L_0x073c:
        r5 = "Period";
        r5 = com.google.android.gms.internal.ads.zzqg.zzc(r2, r5);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r5 == 0) goto L_0x07b2;
    L_0x0744:
        r5 = new com.google.android.gms.internal.ads.zznn;	 Catch:{ XmlPullParserException -> 0x081f }
        r9 = r52;
        r6 = r54;
        r5.<init>(r6, r9, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        r11 = r49;
        r3 = java.lang.Long.valueOf(r11);	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = android.util.Pair.create(r5, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = r3.first;	 Catch:{ XmlPullParserException -> 0x081f }
        r5 = (com.google.android.gms.internal.ads.zznn) r5;	 Catch:{ XmlPullParserException -> 0x081f }
        r6 = r5.zzbdj;	 Catch:{ XmlPullParserException -> 0x081f }
        r8 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r10 != 0) goto L_0x0790;
    L_0x0766:
        if (r18 == 0) goto L_0x0771;
    L_0x0768:
        r11 = r32;
        r15 = r45;
        r14 = r48;
        r25 = 1;
        goto L_0x07c7;
    L_0x0771:
        r2 = new com.google.android.gms.internal.ads.zzfx;	 Catch:{ XmlPullParserException -> 0x081f }
        r14 = r48;
        r3 = r14.size();	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = 47;
        r5 = new java.lang.StringBuilder;	 Catch:{ XmlPullParserException -> 0x081f }
        r5.<init>(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        r4 = "Unable to determine start of period ";
        r5.append(r4);	 Catch:{ XmlPullParserException -> 0x081f }
        r5.append(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = r5.toString();	 Catch:{ XmlPullParserException -> 0x081f }
        r2.<init>(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        throw r2;	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x0790:
        r14 = r48;
        r3 = r3.second;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = (java.lang.Long) r3;	 Catch:{ XmlPullParserException -> 0x081f }
        r6 = r3.longValue();	 Catch:{ XmlPullParserException -> 0x081f }
        r8 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 != 0) goto L_0x07a9;
    L_0x07a3:
        r10 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        goto L_0x07ad;
    L_0x07a9:
        r8 = r5.zzbdj;	 Catch:{ XmlPullParserException -> 0x081f }
        r10 = r8 + r6;
    L_0x07ad:
        r14.add(r5);	 Catch:{ XmlPullParserException -> 0x081f }
        r11 = r10;
        goto L_0x07c5;
    L_0x07b2:
        r14 = r3;
        r13 = r44;
        r15 = r45;
        r9 = r46;
        r6 = r48;
        r11 = r49;
        r3 = r52;
        r5 = r54;
        goto L_0x0124;
    L_0x07c3:
        r11 = r32;
    L_0x07c5:
        r15 = r45;
    L_0x07c7:
        r3 = "MPD";
        r3 = com.google.android.gms.internal.ads.zzqg.zzc(r2, r3);	 Catch:{ XmlPullParserException -> 0x081f }
        if (r3 == 0) goto L_0x0807;
    L_0x07cf:
        r5 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r2 = (r46 > r5 ? 1 : (r46 == r5 ? 0 : -1));
        if (r2 != 0) goto L_0x07e9;
    L_0x07d8:
        r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1));
        if (r2 == 0) goto L_0x07df;
    L_0x07dc:
        r46 = r11;
        goto L_0x07e9;
    L_0x07df:
        if (r18 != 0) goto L_0x07e9;
    L_0x07e1:
        r2 = new com.google.android.gms.internal.ads.zzfx;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = "Unable to determine duration of static manifest.";
        r2.<init>(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        throw r2;	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x07e9:
        r2 = r14.isEmpty();	 Catch:{ XmlPullParserException -> 0x081f }
        if (r2 == 0) goto L_0x07f7;
    L_0x07ef:
        r2 = new com.google.android.gms.internal.ads.zzfx;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = "No periods found.";
        r2.<init>(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        throw r2;	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x07f7:
        r2 = new com.google.android.gms.internal.ads.zznj;	 Catch:{ XmlPullParserException -> 0x081f }
        r11 = r2;
        r12 = r28;
        r3 = r14;
        r14 = r46;
        r25 = r44;
        r27 = r3;
        r11.<init>(r12, r14, r16, r18, r19, r21, r23, r25, r26, r27);	 Catch:{ XmlPullParserException -> 0x081f }
        return r2;
    L_0x0807:
        r3 = r4;
        r6 = r14;
        r4 = r31;
        r13 = r44;
        r9 = r46;
        r5 = 2;
        r7 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        goto L_0x0094;
    L_0x0817:
        r2 = new com.google.android.gms.internal.ads.zzfx;	 Catch:{ XmlPullParserException -> 0x081f }
        r3 = "inputStream does not contain a valid media presentation description";
        r2.<init>(r3);	 Catch:{ XmlPullParserException -> 0x081f }
        throw r2;	 Catch:{ XmlPullParserException -> 0x081f }
    L_0x081f:
        r0 = move-exception;
        r2 = r0;
        r3 = new com.google.android.gms.internal.ads.zzfx;
        r3.<init>(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznk.zzc(android.net.Uri, java.io.InputStream):com.google.android.gms.internal.ads.zznj");
    }

    private static int zza(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (!TextUtils.isEmpty(attributeValue)) {
            if ("audio".equals(attributeValue)) {
                return 1;
            }
            if ("video".equals(attributeValue)) {
                return 2;
            }
            if (MimeTypes.BASE_TYPE_TEXT.equals(attributeValue)) {
                return 3;
            }
        }
        return -1;
    }

    private static zza zzb(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean equals = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95".equals(xmlPullParser.getAttributeValue(null, "schemeIdUri"));
        byte[] bArr = null;
        UUID uuid = bArr;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (bArr == null && zzqg.zzd(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                bArr = Base64.decode(xmlPullParser.getText(), 0);
                uuid = zzjq.zze(bArr);
                if (uuid == null) {
                    Log.w("MpdParser", "Skipping malformed cenc:pssh data");
                    bArr = null;
                }
            } else if (bArr == null && equals && zzqg.zzd(xmlPullParser, "mspr:pro") && xmlPullParser.next() == 4) {
                bArr = zzjq.zza(zzfe.zzwp, Base64.decode(xmlPullParser.getText(), 0));
                uuid = zzfe.zzwp;
            } else if (zzqg.zzd(xmlPullParser, "widevine:license")) {
                String attributeValue = xmlPullParser.getAttributeValue(null, "robustness_level");
                z = attributeValue != null && attributeValue.startsWith("HW");
            }
        } while (!zzqg.zzc(xmlPullParser, "ContentProtection"));
        if (bArr != null) {
            return new zza(uuid, MimeTypes.VIDEO_MP4, bArr, z);
        }
        return null;
    }

    private final zzny zza(XmlPullParser xmlPullParser, zzny zzny) throws XmlPullParserException, IOException {
        long parseLong;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        zzny zzny2 = zzny;
        long zzb = zzb(xmlPullParser2, "timescale", zzny2 != null ? zzny2.zzcr : 1);
        long j = 0;
        long zzb2 = zzb(xmlPullParser2, "presentationTimeOffset", zzny2 != null ? zzny2.zzbdw : 0);
        long j2 = zzny2 != null ? zzny2.zzbec : 0;
        if (zzny2 != null) {
            j = zzny2.zzbed;
        }
        String str = null;
        String attributeValue = xmlPullParser2.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            j = Long.parseLong(split[0]);
            parseLong = (Long.parseLong(split[1]) - j) + 1;
        } else {
            parseLong = j;
            j = j2;
        }
        if (zzny2 != null) {
            str = zzny2.zzbdv;
        }
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser2, "Initialization")) {
                str = zzd(xmlPullParser);
            }
        } while (!zzqg.zzc(xmlPullParser2, "SegmentBase"));
        return new zzny(str, zzb, zzb2, j, parseLong);
    }

    private final zznv zza(XmlPullParser xmlPullParser, zznv zznv) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        zznv zznv2 = zznv;
        long zzb = zzb(xmlPullParser2, "timescale", zznv2 != null ? zznv2.zzcr : 1);
        long zzb2 = zzb(xmlPullParser2, "presentationTimeOffset", zznv2 != null ? zznv2.zzbdw : 0);
        long zzb3 = zzb(xmlPullParser2, "duration", zznv2 != null ? zznv2.zzcs : C.TIME_UNSET);
        int zza = zza(xmlPullParser2, "startNumber", zznv2 != null ? zznv2.zzbdx : 1);
        List list = null;
        zzno zzno = null;
        List list2 = zzno;
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser2, "Initialization")) {
                zzno = zzd(xmlPullParser);
            } else if (zzqg.zzd(xmlPullParser2, "SegmentTimeline")) {
                list2 = zzc(xmlPullParser);
            } else if (zzqg.zzd(xmlPullParser2, "SegmentURL")) {
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(zza(xmlPullParser2, ShareConstants.WEB_DIALOG_PARAM_MEDIA, "mediaRange"));
            }
        } while (!zzqg.zzc(xmlPullParser2, "SegmentList"));
        if (zznv2 != null) {
            if (zzno == null) {
                zzno = zznv2.zzbdv;
            }
            if (list2 == null) {
                list2 = zznv2.zzbdy;
            }
            if (list == null) {
                list = zznv2.zzbdz;
            }
        }
        return new zznv(zzno, zzb, zzb2, zza, zzb3, list2, list);
    }

    private final zznw zza(XmlPullParser xmlPullParser, zznw zznw) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        zznw zznw2 = zznw;
        long zzb = zzb(xmlPullParser2, "timescale", zznw2 != null ? zznw2.zzcr : 1);
        long zzb2 = zzb(xmlPullParser2, "presentationTimeOffset", zznw2 != null ? zznw2.zzbdw : 0);
        long zzb3 = zzb(xmlPullParser2, "duration", zznw2 != null ? zznw2.zzcs : C.TIME_UNSET);
        int zza = zza(xmlPullParser2, "startNumber", zznw2 != null ? zznw2.zzbdx : 1);
        zzno zzno = null;
        zzoa zza2 = zza(xmlPullParser2, ShareConstants.WEB_DIALOG_PARAM_MEDIA, zznw2 != null ? zznw2.zzbeb : null);
        zzoa zza3 = zza(xmlPullParser2, "initialization", zznw2 != null ? zznw2.zzbea : null);
        List list = null;
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser2, "Initialization")) {
                zzno = zzd(xmlPullParser);
            } else if (zzqg.zzd(xmlPullParser2, "SegmentTimeline")) {
                list = zzc(xmlPullParser);
            }
        } while (!zzqg.zzc(xmlPullParser2, "SegmentTemplate"));
        if (zznw2 != null) {
            if (zzno == null) {
                zzno = zznw2.zzbdv;
            }
            if (list == null) {
                list = zznw2.zzbdy;
            }
        }
        return new zznw(zzno, zzb, zzb2, zza, zzb3, list, zza3, zza2);
    }

    private static List<zznx> zzc(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (zzqg.zzd(xmlPullParser, "S")) {
                j = zzb(xmlPullParser, "t", j);
                long zzb = zzb(xmlPullParser, "d", (long) C.TIME_UNSET);
                int i = 0;
                int zza = 1 + zza(xmlPullParser, e.o, 0);
                while (i < zza) {
                    arrayList.add(new zznx(j, zzb));
                    i++;
                    j += zzb;
                }
            }
        } while (!zzqg.zzc(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    private static zzoa zza(XmlPullParser xmlPullParser, String str, zzoa zzoa) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? zzoa.zzaa(attributeValue) : zzoa;
    }

    private final zzno zzd(XmlPullParser xmlPullParser) {
        return zza(xmlPullParser, "sourceURL", "range");
    }

    private static zzno zza(XmlPullParser xmlPullParser, String str, String str2) {
        long parseLong;
        long parseLong2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            parseLong = Long.parseLong(split[0]);
            if (split.length == 2) {
                parseLong2 = (Long.parseLong(split[1]) - parseLong) + 1;
                return new zzno(attributeValue, parseLong, parseLong2);
            }
        }
        parseLong = 0;
        parseLong2 = -1;
        return new zzno(attributeValue, parseLong, parseLong2);
    }

    private static int zze(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(zzb(xmlPullParser, "schemeIdUri", null))) {
            i = zza(xmlPullParser, "value", -1);
        }
        do {
            xmlPullParser.next();
        } while (!zzqg.zzc(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    private static boolean zzx(String str) {
        return zzpt.zzad(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_CEA608.equals(str);
    }

    private static int zzd(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        zzpo.checkState(i == i2);
        return i;
    }

    private static zznm zza(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String zzb = zzb(xmlPullParser, "schemeIdUri", "");
        String zzb2 = zzb(xmlPullParser, "value", null);
        String zzb3 = zzb(xmlPullParser, "id", null);
        do {
            xmlPullParser.next();
        } while (!zzqg.zzc(xmlPullParser, str));
        return new zznm(zzb, zzb2, zzb3);
    }

    private static float zza(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = zzbcz.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        attributeValue = matcher.group(2);
        return !TextUtils.isEmpty(attributeValue) ? ((float) parseInt) / ((float) Integer.parseInt(attributeValue)) : (float) parseInt;
    }

    private static long zza(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return zzqe.zzak(attributeValue);
    }

    private static String zzb(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return zzqd.zzc(str, xmlPullParser.getText());
    }

    private static int zza(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return i;
        }
        return Integer.parseInt(attributeValue);
    }

    private static long zzb(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (attributeValue == null) {
            return j;
        }
        return Long.parseLong(attributeValue);
    }

    private static String zzb(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }
}
