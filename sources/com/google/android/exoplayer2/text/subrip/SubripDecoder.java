package com.google.android.exoplayer2.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubripDecoder extends SimpleSubtitleDecoder {
    private static final String ALIGN_BOTTOM_LEFT = "{\\an1}";
    private static final String ALIGN_BOTTOM_MID = "{\\an2}";
    private static final String ALIGN_BOTTOM_RIGHT = "{\\an3}";
    private static final String ALIGN_MID_LEFT = "{\\an4}";
    private static final String ALIGN_MID_MID = "{\\an5}";
    private static final String ALIGN_MID_RIGHT = "{\\an6}";
    private static final String ALIGN_TOP_LEFT = "{\\an7}";
    private static final String ALIGN_TOP_MID = "{\\an8}";
    private static final String ALIGN_TOP_RIGHT = "{\\an9}";
    static final float END_FRACTION = 0.92f;
    static final float MID_FRACTION = 0.5f;
    static final float START_FRACTION = 0.08f;
    private static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    private static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");
    private static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+),(\\d+)";
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    private static final String TAG = "SubripDecoder";
    private final ArrayList<String> tags = new ArrayList();
    private final StringBuilder textBuilder = new StringBuilder();

    static float getFractionalPositionForAnchorType(int i) {
        switch (i) {
            case 0:
                return 0.08f;
            case 1:
                return MID_FRACTION;
            default:
                return END_FRACTION;
        }
    }

    public SubripDecoder() {
        super(TAG);
    }

    /* Access modifiers changed, original: protected */
    public SubripSubtitle decode(byte[] bArr, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                break;
            } else if (readLine.length() != 0) {
                String readLine2;
                StringBuilder stringBuilder;
                try {
                    Integer.parseInt(readLine);
                    readLine = parsableByteArray.readLine();
                    if (readLine == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(readLine);
                    if (matcher.matches()) {
                        String str;
                        int i2 = 1;
                        longArray.add(parseTimecode(matcher, 1));
                        int i3 = 0;
                        if (TextUtils.isEmpty(matcher.group(6))) {
                            i2 = 0;
                        } else {
                            longArray.add(parseTimecode(matcher, 6));
                        }
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        while (true) {
                            readLine2 = parsableByteArray.readLine();
                            if (TextUtils.isEmpty(readLine2)) {
                                break;
                            }
                            if (this.textBuilder.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(readLine2, this.tags));
                        }
                        Spanned fromHtml = Html.fromHtml(this.textBuilder.toString());
                        while (i3 < this.tags.size()) {
                            str = (String) this.tags.get(i3);
                            if (str.matches(SUBRIP_ALIGNMENT_TAG)) {
                                break;
                            }
                            i3++;
                        }
                        str = null;
                        arrayList.add(buildCue(fromHtml, str));
                        if (i2 != 0) {
                            arrayList.add(null);
                        }
                    } else {
                        readLine2 = TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Skipping invalid timing: ");
                        stringBuilder.append(readLine);
                        Log.w(readLine2, stringBuilder.toString());
                    }
                } catch (NumberFormatException unused) {
                    readLine2 = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Skipping invalid index: ");
                    stringBuilder.append(readLine);
                    Log.w(readLine2, stringBuilder.toString());
                }
            }
        }
        Cue[] cueArr = new Cue[arrayList.size()];
        arrayList.toArray(cueArr);
        return new SubripSubtitle(cueArr, longArray.toArray());
    }

    private String processLine(String str, ArrayList<String> arrayList) {
        str = str.trim();
        StringBuilder stringBuilder = new StringBuilder(str);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(str);
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group();
            arrayList.add(group);
            int start = matcher.start() - i;
            int length = group.length();
            stringBuilder.replace(start, start + length, "");
            i += length;
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: Missing block: B:56:0x00c4, code skipped:
            if (r0.equals(ALIGN_MID_LEFT) != false) goto L_0x00e6;
     */
    private com.google.android.exoplayer2.text.Cue buildCue(android.text.Spanned r15, @android.support.annotation.Nullable java.lang.String r16) {
        /*
        r14 = this;
        r0 = r16;
        if (r0 != 0) goto L_0x000b;
    L_0x0004:
        r0 = new com.google.android.exoplayer2.text.Cue;
        r2 = r15;
        r0.<init>(r2);
        return r0;
    L_0x000b:
        r2 = r15;
        r1 = r16.hashCode();
        r3 = 6;
        r4 = 3;
        r5 = 7;
        r6 = 4;
        r7 = 8;
        r8 = 5;
        r9 = -1;
        r10 = 0;
        r11 = 1;
        r12 = 2;
        switch(r1) {
            case -685620710: goto L_0x0070;
            case -685620679: goto L_0x0066;
            case -685620648: goto L_0x005c;
            case -685620617: goto L_0x0052;
            case -685620586: goto L_0x0048;
            case -685620555: goto L_0x003e;
            case -685620524: goto L_0x0034;
            case -685620493: goto L_0x002a;
            case -685620462: goto L_0x0020;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x007a;
    L_0x0020:
        r1 = "{\\an9}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x0028:
        r1 = r8;
        goto L_0x007b;
    L_0x002a:
        r1 = "{\\an8}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x0032:
        r1 = r7;
        goto L_0x007b;
    L_0x0034:
        r1 = "{\\an7}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x003c:
        r1 = r12;
        goto L_0x007b;
    L_0x003e:
        r1 = "{\\an6}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x0046:
        r1 = r6;
        goto L_0x007b;
    L_0x0048:
        r1 = "{\\an5}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x0050:
        r1 = r5;
        goto L_0x007b;
    L_0x0052:
        r1 = "{\\an4}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x005a:
        r1 = r11;
        goto L_0x007b;
    L_0x005c:
        r1 = "{\\an3}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x0064:
        r1 = r4;
        goto L_0x007b;
    L_0x0066:
        r1 = "{\\an2}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x006e:
        r1 = r3;
        goto L_0x007b;
    L_0x0070:
        r1 = "{\\an1}";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x007a;
    L_0x0078:
        r1 = r10;
        goto L_0x007b;
    L_0x007a:
        r1 = r9;
    L_0x007b:
        switch(r1) {
            case 0: goto L_0x0082;
            case 1: goto L_0x0082;
            case 2: goto L_0x0082;
            case 3: goto L_0x0080;
            case 4: goto L_0x0080;
            case 5: goto L_0x0080;
            default: goto L_0x007e;
        };
    L_0x007e:
        r13 = r11;
        goto L_0x0083;
    L_0x0080:
        r13 = r12;
        goto L_0x0083;
    L_0x0082:
        r13 = r10;
    L_0x0083:
        r1 = r16.hashCode();
        switch(r1) {
            case -685620710: goto L_0x00db;
            case -685620679: goto L_0x00d1;
            case -685620648: goto L_0x00c7;
            case -685620617: goto L_0x00be;
            case -685620586: goto L_0x00b4;
            case -685620555: goto L_0x00aa;
            case -685620524: goto L_0x00a0;
            case -685620493: goto L_0x0096;
            case -685620462: goto L_0x008c;
            default: goto L_0x008a;
        };
    L_0x008a:
        goto L_0x00e5;
    L_0x008c:
        r1 = "{\\an9}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x0094:
        r3 = r8;
        goto L_0x00e6;
    L_0x0096:
        r1 = "{\\an8}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x009e:
        r3 = r6;
        goto L_0x00e6;
    L_0x00a0:
        r1 = "{\\an7}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x00a8:
        r3 = r4;
        goto L_0x00e6;
    L_0x00aa:
        r1 = "{\\an6}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x00b2:
        r3 = r7;
        goto L_0x00e6;
    L_0x00b4:
        r1 = "{\\an5}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x00bc:
        r3 = r5;
        goto L_0x00e6;
    L_0x00be:
        r1 = "{\\an4}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x00c6:
        goto L_0x00e6;
    L_0x00c7:
        r1 = "{\\an3}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x00cf:
        r3 = r12;
        goto L_0x00e6;
    L_0x00d1:
        r1 = "{\\an2}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x00d9:
        r3 = r11;
        goto L_0x00e6;
    L_0x00db:
        r1 = "{\\an1}";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00e5;
    L_0x00e3:
        r3 = r10;
        goto L_0x00e6;
    L_0x00e5:
        r3 = r9;
    L_0x00e6:
        switch(r3) {
            case 0: goto L_0x00ed;
            case 1: goto L_0x00ed;
            case 2: goto L_0x00ed;
            case 3: goto L_0x00eb;
            case 4: goto L_0x00eb;
            case 5: goto L_0x00eb;
            default: goto L_0x00e9;
        };
    L_0x00e9:
        r6 = r11;
        goto L_0x00ee;
    L_0x00eb:
        r6 = r10;
        goto L_0x00ee;
    L_0x00ed:
        r6 = r12;
    L_0x00ee:
        r0 = new com.google.android.exoplayer2.text.Cue;
        r3 = 0;
        r4 = getFractionalPositionForAnchorType(r6);
        r5 = 0;
        r7 = getFractionalPositionForAnchorType(r13);
        r9 = 1;
        r1 = r0;
        r8 = r13;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.subrip.SubripDecoder.buildCue(android.text.Spanned, java.lang.String):com.google.android.exoplayer2.text.Cue");
    }

    private static long parseTimecode(Matcher matcher, int i) {
        return ((((((Long.parseLong(matcher.group(i + 1)) * 60) * 60) * 1000) + ((Long.parseLong(matcher.group(i + 2)) * 60) * 1000)) + (Long.parseLong(matcher.group(i + 3)) * 1000)) + Long.parseLong(matcher.group(i + 4))) * 1000;
    }
}
