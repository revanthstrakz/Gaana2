package com.google.android.exoplayer2.text.ssa;

import com.comscore.streaming.Constants;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SsaDecoder extends SimpleSubtitleDecoder {
    private static final String DIALOGUE_LINE_PREFIX = "Dialogue: ";
    private static final String FORMAT_LINE_PREFIX = "Format: ";
    private static final Pattern SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)(?::|\\.)(\\d+)");
    private static final String TAG = "SsaDecoder";
    private int formatEndIndex;
    private int formatKeyCount;
    private int formatStartIndex;
    private int formatTextIndex;
    private final boolean haveInitializationData;

    public SsaDecoder() {
        this(null);
    }

    public SsaDecoder(List<byte[]> list) {
        super(TAG);
        if (list == null || list.isEmpty()) {
            this.haveInitializationData = false;
            return;
        }
        this.haveInitializationData = true;
        String fromUtf8Bytes = Util.fromUtf8Bytes((byte[]) list.get(0));
        Assertions.checkArgument(fromUtf8Bytes.startsWith(FORMAT_LINE_PREFIX));
        parseFormatLine(fromUtf8Bytes);
        parseHeader(new ParsableByteArray((byte[]) list.get(1)));
    }

    /* Access modifiers changed, original: protected */
    public SsaSubtitle decode(byte[] bArr, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        if (!this.haveInitializationData) {
            parseHeader(parsableByteArray);
        }
        parseEventBody(parsableByteArray, arrayList, longArray);
        Cue[] cueArr = new Cue[arrayList.size()];
        arrayList.toArray(cueArr);
        return new SsaSubtitle(cueArr, longArray.toArray());
    }

    private void parseHeader(ParsableByteArray parsableByteArray) {
        String readLine;
        do {
            readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
        } while (!readLine.startsWith("[Events]"));
    }

    private void parseEventBody(ParsableByteArray parsableByteArray, List<Cue> list, LongArray longArray) {
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
            if (!this.haveInitializationData && readLine.startsWith(FORMAT_LINE_PREFIX)) {
                parseFormatLine(readLine);
            } else if (readLine.startsWith(DIALOGUE_LINE_PREFIX)) {
                parseDialogueLine(readLine, list, longArray);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0069 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    private void parseFormatLine(java.lang.String r7) {
        /*
        r6 = this;
        r0 = "Format: ";
        r0 = r0.length();
        r7 = r7.substring(r0);
        r0 = ",";
        r7 = android.text.TextUtils.split(r7, r0);
        r0 = 0;
        r1 = -1;
        r2 = r7.length;
        r6.formatKeyCount = r2;
        r6.formatStartIndex = r1;
        r6.formatEndIndex = r1;
        r6.formatTextIndex = r1;
        r2 = r0;
    L_0x001c:
        r3 = r6.formatKeyCount;
        if (r2 >= r3) goto L_0x006c;
    L_0x0020:
        r3 = r7[r2];
        r3 = r3.trim();
        r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3);
        r4 = r3.hashCode();
        r5 = 100571; // 0x188db float:1.4093E-40 double:4.96887E-319;
        if (r4 == r5) goto L_0x0052;
    L_0x0033:
        r5 = 3556653; // 0x36452d float:4.983932E-39 double:1.75722E-317;
        if (r4 == r5) goto L_0x0048;
    L_0x0038:
        r5 = 109757538; // 0x68ac462 float:5.219839E-35 double:5.4227429E-316;
        if (r4 == r5) goto L_0x003e;
    L_0x003d:
        goto L_0x005c;
    L_0x003e:
        r4 = "start";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005c;
    L_0x0046:
        r3 = r0;
        goto L_0x005d;
    L_0x0048:
        r4 = "text";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005c;
    L_0x0050:
        r3 = 2;
        goto L_0x005d;
    L_0x0052:
        r4 = "end";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x005c;
    L_0x005a:
        r3 = 1;
        goto L_0x005d;
    L_0x005c:
        r3 = r1;
    L_0x005d:
        switch(r3) {
            case 0: goto L_0x0067;
            case 1: goto L_0x0064;
            case 2: goto L_0x0061;
            default: goto L_0x0060;
        };
    L_0x0060:
        goto L_0x0069;
    L_0x0061:
        r6.formatTextIndex = r2;
        goto L_0x0069;
    L_0x0064:
        r6.formatEndIndex = r2;
        goto L_0x0069;
    L_0x0067:
        r6.formatStartIndex = r2;
    L_0x0069:
        r2 = r2 + 1;
        goto L_0x001c;
    L_0x006c:
        r7 = r6.formatStartIndex;
        if (r7 == r1) goto L_0x0078;
    L_0x0070:
        r7 = r6.formatEndIndex;
        if (r7 == r1) goto L_0x0078;
    L_0x0074:
        r7 = r6.formatTextIndex;
        if (r7 != r1) goto L_0x007a;
    L_0x0078:
        r6.formatKeyCount = r0;
    L_0x007a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ssa.SsaDecoder.parseFormatLine(java.lang.String):void");
    }

    private void parseDialogueLine(String str, List<Cue> list, LongArray longArray) {
        String str2;
        StringBuilder stringBuilder;
        if (this.formatKeyCount == 0) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping dialogue line before complete format: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
            return;
        }
        String[] split = str.substring(DIALOGUE_LINE_PREFIX.length()).split(",", this.formatKeyCount);
        if (split.length != this.formatKeyCount) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping dialogue line with fewer columns than format: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
            return;
        }
        long parseTimecodeUs = parseTimecodeUs(split[this.formatStartIndex]);
        if (parseTimecodeUs == C.TIME_UNSET) {
            str2 = TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Skipping invalid timing: ");
            stringBuilder.append(str);
            Log.w(str2, stringBuilder.toString());
            return;
        }
        long j;
        String str3 = split[this.formatEndIndex];
        if (str3.trim().isEmpty()) {
            j = C.TIME_UNSET;
        } else {
            j = parseTimecodeUs(str3);
            if (j == C.TIME_UNSET) {
                str2 = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Skipping invalid timing: ");
                stringBuilder.append(str);
                Log.w(str2, stringBuilder.toString());
                return;
            }
        }
        list.add(new Cue(split[this.formatTextIndex].replaceAll("\\{.*?\\}", "").replaceAll("\\\\N", "\n").replaceAll("\\\\n", "\n")));
        longArray.add(parseTimecodeUs);
        if (j != C.TIME_UNSET) {
            list.add(null);
            longArray.add(j);
        }
    }

    public static long parseTimecodeUs(String str) {
        Matcher matcher = SSA_TIMECODE_PATTERN.matcher(str);
        if (matcher.matches()) {
            return (((((Long.parseLong(matcher.group(1)) * 60) * 60) * 1000000) + ((Long.parseLong(matcher.group(2)) * 60) * 1000000)) + (Long.parseLong(matcher.group(3)) * 1000000)) + (Long.parseLong(matcher.group(4)) * Constants.HEARTBEAT_STAGE_ONE_INTERVAL);
        }
        return C.TIME_UNSET;
    }
}
