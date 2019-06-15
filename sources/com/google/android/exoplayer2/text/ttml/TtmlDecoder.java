package com.google.android.exoplayer2.text.ttml;

import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final CellResolution DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;

    private static final class CellResolution {
        final int columns;
        final int rows;

        CellResolution(int i, int i2) {
            this.columns = i;
            this.rows = i2;
        }
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }

    private static final class TtsExtent {
        final int height;
        final int width;

        TtsExtent(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public TtmlDecoder() {
        super(TAG);
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            this.xmlParserFactory.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* Access modifiers changed, original: protected */
    public TtmlSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            String str = null;
            hashMap2.put("", new TtmlRegion(null));
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), null);
            ArrayDeque arrayDeque = new ArrayDeque();
            FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution cellResolution = DEFAULT_CELL_RESOLUTION;
            int i2 = 0;
            String str2 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                String str3;
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i2 == 0) {
                    CellResolution parseCellResolution;
                    TtsExtent parseTtsExtent;
                    FrameAndTickRate frameAndTickRate2;
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        FrameAndTickRate frameAndTickRate3;
                        if (TtmlNode.TAG_TT.equals(name)) {
                            frameAndTickRate = parseFrameAndTickRates(newPullParser);
                            parseCellResolution = parseCellResolution(newPullParser, DEFAULT_CELL_RESOLUTION);
                            parseTtsExtent = parseTtsExtent(newPullParser);
                            frameAndTickRate3 = frameAndTickRate;
                        } else {
                            parseTtsExtent = str;
                            frameAndTickRate3 = frameAndTickRate;
                            parseCellResolution = cellResolution;
                        }
                        if (!isSupportedTag(name)) {
                            String str4 = TAG;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Ignoring unsupported tag: ");
                            stringBuilder.append(newPullParser.getName());
                            Log.i(str4, stringBuilder.toString());
                            i2++;
                            str3 = str2;
                            frameAndTickRate2 = frameAndTickRate3;
                        } else if (TtmlNode.TAG_HEAD.equals(name)) {
                            str3 = str2;
                            frameAndTickRate2 = frameAndTickRate3;
                            parseHeader(newPullParser, hashMap, parseCellResolution, parseTtsExtent, hashMap2, hashMap3);
                        } else {
                            str3 = str2;
                            frameAndTickRate2 = frameAndTickRate3;
                            try {
                                TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate2);
                                arrayDeque.push(parseNode);
                                if (ttmlNode != null) {
                                    ttmlNode.addChild(parseNode);
                                }
                            } catch (SubtitleDecoderException e) {
                                Log.w(TAG, "Suppressing parser error", e);
                                i2++;
                            }
                        }
                    } else {
                        str3 = str2;
                        if (eventType == 4) {
                            ttmlNode.addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                        } else if (eventType == 3) {
                            TtmlSubtitle ttmlSubtitle = newPullParser.getName().equals(TtmlNode.TAG_TT) ? new TtmlSubtitle((TtmlNode) arrayDeque.peek(), hashMap, hashMap2, hashMap3) : str3;
                            arrayDeque.pop();
                            parseTtsExtent = str;
                            parseCellResolution = cellResolution;
                            str3 = ttmlSubtitle;
                            frameAndTickRate2 = frameAndTickRate;
                        }
                        parseTtsExtent = str;
                        frameAndTickRate2 = frameAndTickRate;
                        parseCellResolution = cellResolution;
                    }
                    frameAndTickRate = frameAndTickRate2;
                    cellResolution = parseCellResolution;
                    str = parseTtsExtent;
                } else {
                    str3 = str2;
                    if (eventType == 2) {
                        i2++;
                    } else if (eventType == 3) {
                        i2--;
                    }
                }
                str2 = str3;
                newPullParser.next();
            }
            return str2;
        } catch (XmlPullParserException e2) {
            throw new SubtitleDecoderException("Unable to decode source", e2);
        } catch (IOException e22) {
            throw new IllegalStateException("Unexpected error when reading input.", e22);
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = Util.split(attributeValue2, " ");
            if (split.length != 2) {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
            f = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
        }
        int i = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(((float) parseInt) * f, i, i2);
    }

    private CellResolution parseCellResolution(XmlPullParser xmlPullParser, CellResolution cellResolution) throws SubtitleDecoderException {
        String str;
        StringBuilder stringBuilder;
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (matcher.matches()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1));
                int parseInt2 = Integer.parseInt(matcher.group(2));
                if (parseInt != 0) {
                    if (parseInt2 != 0) {
                        return new CellResolution(parseInt, parseInt2);
                    }
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Invalid cell resolution ");
                stringBuilder2.append(parseInt);
                stringBuilder2.append(" ");
                stringBuilder2.append(parseInt2);
                throw new SubtitleDecoderException(stringBuilder2.toString());
            } catch (NumberFormatException unused) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Ignoring malformed cell resolution: ");
                stringBuilder.append(attributeValue);
                Log.w(str, stringBuilder.toString());
                return cellResolution;
            }
        }
        str = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring malformed cell resolution: ");
        stringBuilder.append(attributeValue);
        Log.w(str, stringBuilder.toString());
        return cellResolution;
    }

    private TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String str;
        StringBuilder stringBuilder;
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (matcher.matches()) {
            try {
                return new TtsExtent(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            } catch (NumberFormatException unused) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Ignoring malformed tts extent: ");
                stringBuilder.append(attributeValue);
                Log.w(str, stringBuilder.toString());
                return null;
            }
        }
        str = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Ignoring non-pixel tts extent: ");
        stringBuilder.append(attributeValue);
        Log.w(str, stringBuilder.toString());
        return null;
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, CellResolution cellResolution, TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (Object obj : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain((TtmlStyle) map.get(obj));
                    }
                }
                if (parseStyleAttributes.getId() != null) {
                    map.put(parseStyleAttributes.getId(), parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region")) {
                TtmlRegion parseRegionAttributes = parseRegionAttributes(xmlPullParser, cellResolution, ttsExtent);
                if (parseRegionAttributes != null) {
                    map2.put(parseRegionAttributes.id, parseRegionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, TtmlNode.TAG_METADATA)) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    private void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, TtmlNode.TAG_IMAGE)) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id");
                if (attributeValue != null) {
                    map.put(attributeValue, xmlPullParser.nextText());
                }
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_METADATA));
    }

    private TtmlRegion parseRegionAttributes(XmlPullParser xmlPullParser, CellResolution cellResolution, TtsExtent ttsExtent) {
        String str;
        StringBuilder stringBuilder;
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id");
        if (attributeValue == null) {
            return null;
        }
        String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, "origin");
        if (attributeValue2 != null) {
            float parseFloat;
            float f;
            Matcher matcher = PERCENTAGE_COORDINATES.matcher(attributeValue2);
            Matcher matcher2 = PIXEL_COORDINATES.matcher(attributeValue2);
            if (matcher.matches()) {
                try {
                    float parseFloat2 = Float.parseFloat(matcher.group(1)) / 100.0f;
                    parseFloat = Float.parseFloat(matcher.group(2)) / 100.0f;
                    f = parseFloat2;
                } catch (NumberFormatException unused) {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Ignoring region with malformed origin: ");
                    stringBuilder.append(attributeValue2);
                    Log.w(str, stringBuilder.toString());
                    return null;
                }
            } else if (!matcher2.matches()) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Ignoring region with unsupported origin: ");
                stringBuilder.append(attributeValue2);
                Log.w(str, stringBuilder.toString());
                return null;
            } else if (ttsExtent == null) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Ignoring region with missing tts:extent: ");
                stringBuilder.append(attributeValue2);
                Log.w(str, stringBuilder.toString());
                return null;
            } else {
                try {
                    int parseInt = Integer.parseInt(matcher2.group(1));
                    f = ((float) parseInt) / ((float) ttsExtent.width);
                    parseFloat = ((float) Integer.parseInt(matcher2.group(2))) / ((float) ttsExtent.height);
                } catch (NumberFormatException unused2) {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Ignoring region with malformed origin: ");
                    stringBuilder.append(attributeValue2);
                    Log.w(str, stringBuilder.toString());
                    return null;
                }
            }
            String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
            if (attributeValue3 != null) {
                float parseFloat3;
                float parseFloat4;
                int parseInt2;
                Matcher matcher3 = PERCENTAGE_COORDINATES.matcher(attributeValue3);
                Matcher matcher4 = PIXEL_COORDINATES.matcher(attributeValue3);
                if (matcher3.matches()) {
                    try {
                        parseFloat3 = Float.parseFloat(matcher3.group(1)) / 100.0f;
                        parseFloat4 = Float.parseFloat(matcher3.group(2)) / 100.0f;
                    } catch (NumberFormatException unused3) {
                        str = TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Ignoring region with malformed extent: ");
                        stringBuilder.append(attributeValue2);
                        Log.w(str, stringBuilder.toString());
                        return null;
                    }
                } else if (!matcher4.matches()) {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Ignoring region with unsupported extent: ");
                    stringBuilder.append(attributeValue2);
                    Log.w(str, stringBuilder.toString());
                    return null;
                } else if (ttsExtent == null) {
                    str = TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Ignoring region with missing tts:extent: ");
                    stringBuilder.append(attributeValue2);
                    Log.w(str, stringBuilder.toString());
                    return null;
                } else {
                    try {
                        parseInt2 = Integer.parseInt(matcher4.group(1));
                        parseFloat4 = ((float) Integer.parseInt(matcher4.group(2))) / ((float) ttsExtent.height);
                        parseFloat3 = ((float) parseInt2) / ((float) ttsExtent.width);
                    } catch (NumberFormatException unused4) {
                        str = TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Ignoring region with malformed extent: ");
                        stringBuilder.append(attributeValue2);
                        Log.w(str, stringBuilder.toString());
                        return null;
                    }
                }
                str = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_DISPLAY_ALIGN);
                if (str != null) {
                    str = Util.toLowerInvariant(str);
                    int i = -1;
                    parseInt2 = str.hashCode();
                    if (parseInt2 != -1364013995) {
                        if (parseInt2 == 92734940 && str.equals("after")) {
                            i = 1;
                        }
                    } else if (str.equals("center")) {
                        i = 0;
                    }
                    switch (i) {
                        case 0:
                            parseFloat += parseFloat4 / 2.0f;
                            parseInt2 = 1;
                            break;
                        case 1:
                            parseFloat += parseFloat4;
                            parseInt2 = 2;
                            break;
                    }
                }
                parseInt2 = 0;
                return new TtmlRegion(attributeValue, f, parseFloat, 0, parseInt2, parseFloat3, 1, 1.0f / ((float) cellResolution.rows));
            }
            Log.w(TAG, "Ignoring region without an extent");
            return null;
        }
        Log.w(TAG, "Ignoring region without an origin");
        return null;
    }

    private String[] parseStyleIds(String str) {
        str = str.trim();
        return str.isEmpty() ? new String[0] : Util.split(str, "\\s+");
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x0216 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0216 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0216 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ca  */
    /* JADX WARNING: Missing block: B:51:0x00c1, code skipped:
            if (r3.equals(com.google.android.exoplayer2.text.ttml.TtmlNode.NO_UNDERLINE) != false) goto L_0x00c5;
     */
    /* JADX WARNING: Missing block: B:73:0x012c, code skipped:
            if (r3.equals("center") != false) goto L_0x0130;
     */
    private com.google.android.exoplayer2.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r12, com.google.android.exoplayer2.text.ttml.TtmlStyle r13) {
        /*
        r11 = this;
        r0 = r12.getAttributeCount();
        r1 = 0;
        r2 = r13;
        r13 = r1;
    L_0x0007:
        if (r13 >= r0) goto L_0x021a;
    L_0x0009:
        r3 = r12.getAttributeValue(r13);
        r4 = r12.getAttributeName(r13);
        r5 = r4.hashCode();
        r6 = 4;
        r7 = 3;
        r8 = 2;
        r9 = -1;
        r10 = 1;
        switch(r5) {
            case -1550943582: goto L_0x0070;
            case -1224696685: goto L_0x0066;
            case -1065511464: goto L_0x005c;
            case -879295043: goto L_0x0051;
            case -734428249: goto L_0x0047;
            case 3355: goto L_0x003d;
            case 94842723: goto L_0x0033;
            case 365601008: goto L_0x0029;
            case 1287124693: goto L_0x001f;
            default: goto L_0x001d;
        };
    L_0x001d:
        goto L_0x007a;
    L_0x001f:
        r5 = "backgroundColor";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x0027:
        r4 = r10;
        goto L_0x007b;
    L_0x0029:
        r5 = "fontSize";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x0031:
        r4 = r6;
        goto L_0x007b;
    L_0x0033:
        r5 = "color";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x003b:
        r4 = r8;
        goto L_0x007b;
    L_0x003d:
        r5 = "id";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x0045:
        r4 = r1;
        goto L_0x007b;
    L_0x0047:
        r5 = "fontWeight";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x004f:
        r4 = 5;
        goto L_0x007b;
    L_0x0051:
        r5 = "textDecoration";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x0059:
        r4 = 8;
        goto L_0x007b;
    L_0x005c:
        r5 = "textAlign";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x0064:
        r4 = 7;
        goto L_0x007b;
    L_0x0066:
        r5 = "fontFamily";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x006e:
        r4 = r7;
        goto L_0x007b;
    L_0x0070:
        r5 = "fontStyle";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x0078:
        r4 = 6;
        goto L_0x007b;
    L_0x007a:
        r4 = r9;
    L_0x007b:
        switch(r4) {
            case 0: goto L_0x0202;
            case 1: goto L_0x01df;
            case 2: goto L_0x01bc;
            case 3: goto L_0x01b3;
            case 4: goto L_0x0191;
            case 5: goto L_0x0181;
            case 6: goto L_0x0171;
            case 7: goto L_0x00f2;
            case 8: goto L_0x0080;
            default: goto L_0x007e;
        };
    L_0x007e:
        goto L_0x0216;
    L_0x0080:
        r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3);
        r4 = r3.hashCode();
        r5 = -1461280213; // 0xffffffffa8e6a22b float:-2.5605459E-14 double:NaN;
        if (r4 == r5) goto L_0x00bb;
    L_0x008d:
        r5 = -1026963764; // 0xffffffffc2c9c6cc float:-100.888275 double:NaN;
        if (r4 == r5) goto L_0x00b1;
    L_0x0092:
        r5 = 913457136; // 0x36723ff0 float:3.6098027E-6 double:4.5130779E-315;
        if (r4 == r5) goto L_0x00a7;
    L_0x0097:
        r5 = 1679736913; // 0x641ec051 float:1.1713774E22 double:8.29900303E-315;
        if (r4 == r5) goto L_0x009d;
    L_0x009c:
        goto L_0x00c4;
    L_0x009d:
        r4 = "linethrough";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c4;
    L_0x00a5:
        r7 = r1;
        goto L_0x00c5;
    L_0x00a7:
        r4 = "nolinethrough";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c4;
    L_0x00af:
        r7 = r10;
        goto L_0x00c5;
    L_0x00b1:
        r4 = "underline";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c4;
    L_0x00b9:
        r7 = r8;
        goto L_0x00c5;
    L_0x00bb:
        r4 = "nounderline";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x00c4;
    L_0x00c3:
        goto L_0x00c5;
    L_0x00c4:
        r7 = r9;
    L_0x00c5:
        switch(r7) {
            case 0: goto L_0x00e8;
            case 1: goto L_0x00de;
            case 2: goto L_0x00d4;
            case 3: goto L_0x00ca;
            default: goto L_0x00c8;
        };
    L_0x00c8:
        goto L_0x0216;
    L_0x00ca:
        r2 = r11.createIfNull(r2);
        r2 = r2.setUnderline(r1);
        goto L_0x0216;
    L_0x00d4:
        r2 = r11.createIfNull(r2);
        r2 = r2.setUnderline(r10);
        goto L_0x0216;
    L_0x00de:
        r2 = r11.createIfNull(r2);
        r2 = r2.setLinethrough(r1);
        goto L_0x0216;
    L_0x00e8:
        r2 = r11.createIfNull(r2);
        r2 = r2.setLinethrough(r10);
        goto L_0x0216;
    L_0x00f2:
        r3 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r3);
        r4 = r3.hashCode();
        switch(r4) {
            case -1364013995: goto L_0x0126;
            case 100571: goto L_0x011c;
            case 3317767: goto L_0x0112;
            case 108511772: goto L_0x0108;
            case 109757538: goto L_0x00fe;
            default: goto L_0x00fd;
        };
    L_0x00fd:
        goto L_0x012f;
    L_0x00fe:
        r4 = "start";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012f;
    L_0x0106:
        r6 = r10;
        goto L_0x0130;
    L_0x0108:
        r4 = "right";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012f;
    L_0x0110:
        r6 = r8;
        goto L_0x0130;
    L_0x0112:
        r4 = "left";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012f;
    L_0x011a:
        r6 = r1;
        goto L_0x0130;
    L_0x011c:
        r4 = "end";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012f;
    L_0x0124:
        r6 = r7;
        goto L_0x0130;
    L_0x0126:
        r4 = "center";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x012f;
    L_0x012e:
        goto L_0x0130;
    L_0x012f:
        r6 = r9;
    L_0x0130:
        switch(r6) {
            case 0: goto L_0x0165;
            case 1: goto L_0x0159;
            case 2: goto L_0x014d;
            case 3: goto L_0x0141;
            case 4: goto L_0x0135;
            default: goto L_0x0133;
        };
    L_0x0133:
        goto L_0x0216;
    L_0x0135:
        r2 = r11.createIfNull(r2);
        r3 = android.text.Layout.Alignment.ALIGN_CENTER;
        r2 = r2.setTextAlign(r3);
        goto L_0x0216;
    L_0x0141:
        r2 = r11.createIfNull(r2);
        r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE;
        r2 = r2.setTextAlign(r3);
        goto L_0x0216;
    L_0x014d:
        r2 = r11.createIfNull(r2);
        r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE;
        r2 = r2.setTextAlign(r3);
        goto L_0x0216;
    L_0x0159:
        r2 = r11.createIfNull(r2);
        r3 = android.text.Layout.Alignment.ALIGN_NORMAL;
        r2 = r2.setTextAlign(r3);
        goto L_0x0216;
    L_0x0165:
        r2 = r11.createIfNull(r2);
        r3 = android.text.Layout.Alignment.ALIGN_NORMAL;
        r2 = r2.setTextAlign(r3);
        goto L_0x0216;
    L_0x0171:
        r2 = r11.createIfNull(r2);
        r4 = "italic";
        r3 = r4.equalsIgnoreCase(r3);
        r2 = r2.setItalic(r3);
        goto L_0x0216;
    L_0x0181:
        r2 = r11.createIfNull(r2);
        r4 = "bold";
        r3 = r4.equalsIgnoreCase(r3);
        r2 = r2.setBold(r3);
        goto L_0x0216;
    L_0x0191:
        r4 = r11.createIfNull(r2);	 Catch:{ SubtitleDecoderException -> 0x019c }
        parseFontSize(r3, r4);	 Catch:{ SubtitleDecoderException -> 0x019b }
        r2 = r4;
        goto L_0x0216;
    L_0x019b:
        r2 = r4;
    L_0x019c:
        r4 = "TtmlDecoder";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Failed parsing fontSize value: ";
        r5.append(r6);
        r5.append(r3);
        r3 = r5.toString();
        com.google.android.exoplayer2.util.Log.w(r4, r3);
        goto L_0x0216;
    L_0x01b3:
        r2 = r11.createIfNull(r2);
        r2 = r2.setFontFamily(r3);
        goto L_0x0216;
    L_0x01bc:
        r2 = r11.createIfNull(r2);
        r4 = com.google.android.exoplayer2.util.ColorParser.parseTtmlColor(r3);	 Catch:{ IllegalArgumentException -> 0x01c8 }
        r2.setFontColor(r4);	 Catch:{ IllegalArgumentException -> 0x01c8 }
        goto L_0x0216;
    L_0x01c8:
        r4 = "TtmlDecoder";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Failed parsing color value: ";
        r5.append(r6);
        r5.append(r3);
        r3 = r5.toString();
        com.google.android.exoplayer2.util.Log.w(r4, r3);
        goto L_0x0216;
    L_0x01df:
        r2 = r11.createIfNull(r2);
        r4 = com.google.android.exoplayer2.util.ColorParser.parseTtmlColor(r3);	 Catch:{ IllegalArgumentException -> 0x01eb }
        r2.setBackgroundColor(r4);	 Catch:{ IllegalArgumentException -> 0x01eb }
        goto L_0x0216;
    L_0x01eb:
        r4 = "TtmlDecoder";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "Failed parsing background value: ";
        r5.append(r6);
        r5.append(r3);
        r3 = r5.toString();
        com.google.android.exoplayer2.util.Log.w(r4, r3);
        goto L_0x0216;
    L_0x0202:
        r4 = "style";
        r5 = r12.getName();
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x0216;
    L_0x020e:
        r2 = r11.createIfNull(r2);
        r2 = r2.setId(r3);
    L_0x0216:
        r13 = r13 + 1;
        goto L_0x0007;
    L_0x021a:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlStyle):com.google.android.exoplayer2.text.ttml.TtmlStyle");
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    private com.google.android.exoplayer2.text.ttml.TtmlNode parseNode(org.xmlpull.v1.XmlPullParser r24, com.google.android.exoplayer2.text.ttml.TtmlNode r25, java.util.Map<java.lang.String, com.google.android.exoplayer2.text.ttml.TtmlRegion> r26, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r27) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
        r23 = this;
        r0 = r23;
        r1 = r24;
        r2 = r25;
        r3 = r27;
        r4 = "";
        r5 = r24.getAttributeCount();
        r6 = 0;
        r12 = r0.parseStyleAttributes(r1, r6);
        r17 = r6;
        r18 = r17;
        r10 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r13 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r15 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r6 = r4;
        r4 = 0;
    L_0x0028:
        if (r4 >= r5) goto L_0x00be;
    L_0x002a:
        r7 = r1.getAttributeName(r4);
        r8 = r1.getAttributeValue(r4);
        r21 = r7.hashCode();
        switch(r21) {
            case -934795532: goto L_0x006c;
            case 99841: goto L_0x0062;
            case 100571: goto L_0x0058;
            case 93616297: goto L_0x004e;
            case 109780401: goto L_0x0044;
            case 1292595405: goto L_0x003a;
            default: goto L_0x0039;
        };
    L_0x0039:
        goto L_0x0076;
    L_0x003a:
        r9 = "backgroundImage";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0076;
    L_0x0042:
        r7 = 5;
        goto L_0x0077;
    L_0x0044:
        r9 = "style";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0076;
    L_0x004c:
        r7 = 3;
        goto L_0x0077;
    L_0x004e:
        r9 = "begin";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0076;
    L_0x0056:
        r7 = 0;
        goto L_0x0077;
    L_0x0058:
        r9 = "end";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0076;
    L_0x0060:
        r7 = 1;
        goto L_0x0077;
    L_0x0062:
        r9 = "dur";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0076;
    L_0x006a:
        r7 = 2;
        goto L_0x0077;
    L_0x006c:
        r9 = "region";
        r7 = r7.equals(r9);
        if (r7 == 0) goto L_0x0076;
    L_0x0074:
        r7 = 4;
        goto L_0x0077;
    L_0x0076:
        r7 = -1;
    L_0x0077:
        switch(r7) {
            case 0: goto L_0x00b3;
            case 1: goto L_0x00ab;
            case 2: goto L_0x00a3;
            case 3: goto L_0x0097;
            case 4: goto L_0x008d;
            case 5: goto L_0x007b;
            default: goto L_0x007a;
        };
    L_0x007a:
        goto L_0x008a;
    L_0x007b:
        r7 = "#";
        r7 = r8.startsWith(r7);
        if (r7 == 0) goto L_0x008a;
    L_0x0083:
        r7 = 1;
        r7 = r8.substring(r7);
        r17 = r7;
    L_0x008a:
        r7 = r26;
        goto L_0x00ba;
    L_0x008d:
        r7 = r26;
        r9 = r7.containsKey(r8);
        if (r9 == 0) goto L_0x00ba;
    L_0x0095:
        r6 = r8;
        goto L_0x00ba;
    L_0x0097:
        r7 = r26;
        r8 = r0.parseStyleIds(r8);
        r9 = r8.length;
        if (r9 <= 0) goto L_0x00ba;
    L_0x00a0:
        r18 = r8;
        goto L_0x00ba;
    L_0x00a3:
        r7 = r26;
        r8 = parseTimeExpression(r8, r3);
        r15 = r8;
        goto L_0x00ba;
    L_0x00ab:
        r7 = r26;
        r8 = parseTimeExpression(r8, r3);
        r13 = r8;
        goto L_0x00ba;
    L_0x00b3:
        r7 = r26;
        r8 = parseTimeExpression(r8, r3);
        r10 = r8;
    L_0x00ba:
        r4 = r4 + 1;
        goto L_0x0028;
    L_0x00be:
        if (r2 == 0) goto L_0x00e0;
    L_0x00c0:
        r3 = r2.startTimeUs;
        r7 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r5 == 0) goto L_0x00e5;
    L_0x00cb:
        r3 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00d5;
    L_0x00cf:
        r3 = r2.startTimeUs;
        r19 = r10 + r3;
        r10 = r19;
    L_0x00d5:
        r3 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00e5;
    L_0x00d9:
        r3 = r2.startTimeUs;
        r19 = r13 + r3;
        r13 = r19;
        goto L_0x00e5;
    L_0x00e0:
        r7 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
    L_0x00e5:
        r3 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1));
        if (r3 != 0) goto L_0x00fb;
    L_0x00e9:
        r3 = (r15 > r7 ? 1 : (r15 == r7 ? 0 : -1));
        if (r3 == 0) goto L_0x00f0;
    L_0x00ed:
        r2 = r10 + r15;
        goto L_0x00fc;
    L_0x00f0:
        if (r2 == 0) goto L_0x00fb;
    L_0x00f2:
        r3 = r2.endTimeUs;
        r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r5 == 0) goto L_0x00fb;
    L_0x00f8:
        r2 = r2.endTimeUs;
        goto L_0x00fc;
    L_0x00fb:
        r2 = r13;
    L_0x00fc:
        r7 = r24.getName();
        r8 = r10;
        r10 = r2;
        r13 = r18;
        r14 = r6;
        r15 = r17;
        r1 = com.google.android.exoplayer2.text.ttml.TtmlNode.buildNode(r7, r8, r10, r12, r13, r14, r15);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseNode(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlNode, java.util.Map, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):com.google.android.exoplayer2.text.ttml.TtmlNode");
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals("body") || str.equals(TtmlNode.TAG_DIV) || str.equals(TtmlNode.TAG_P) || str.equals(TtmlNode.TAG_SPAN) || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals(TtmlNode.TAG_IMAGE) || str.equals("data") || str.equals(TtmlNode.TAG_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        StringBuilder stringBuilder;
        String[] split = Util.split(str, "\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid number of entries for fontSize: ");
            stringBuilder.append(split.length);
            stringBuilder.append(".");
            throw new SubtitleDecoderException(stringBuilder.toString());
        }
        if (matcher.matches()) {
            String group = matcher.group(3);
            int i = -1;
            int hashCode = group.hashCode();
            if (hashCode != 37) {
                if (hashCode != 3240) {
                    if (hashCode == 3592 && group.equals("px")) {
                        i = 0;
                    }
                } else if (group.equals("em")) {
                    i = 1;
                }
            } else if (group.equals("%")) {
                i = 2;
            }
            switch (i) {
                case 0:
                    ttmlStyle.setFontSizeUnit(1);
                    break;
                case 1:
                    ttmlStyle.setFontSizeUnit(2);
                    break;
                case 2:
                    ttmlStyle.setFontSizeUnit(3);
                    break;
                default:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Invalid unit for fontSize: '");
                    stringBuilder.append(group);
                    stringBuilder.append("'.");
                    throw new SubtitleDecoderException(stringBuilder.toString());
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid expression for fontSize: '");
        stringBuilder2.append(str);
        stringBuilder2.append("'.");
        throw new SubtitleDecoderException(stringBuilder2.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4  */
    /* JADX WARNING: Missing block: B:27:0x00aa, code skipped:
            if (r14.equals("t") != false) goto L_0x00e0;
     */
    private static long parseTimeExpression(java.lang.String r14, com.google.android.exoplayer2.text.ttml.TtmlDecoder.FrameAndTickRate r15) throws com.google.android.exoplayer2.text.SubtitleDecoderException {
        /*
        r0 = CLOCK_TIME;
        r0 = r0.matcher(r14);
        r1 = r0.matches();
        r2 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r4 = 5;
        r5 = 4;
        r6 = 3;
        r7 = 2;
        r8 = 1;
        if (r1 == 0) goto L_0x0073;
    L_0x0016:
        r14 = r0.group(r8);
        r8 = java.lang.Long.parseLong(r14);
        r10 = 3600; // 0xe10 float:5.045E-42 double:1.7786E-320;
        r8 = r8 * r10;
        r8 = (double) r8;
        r14 = r0.group(r7);
        r10 = java.lang.Long.parseLong(r14);
        r12 = 60;
        r10 = r10 * r12;
        r10 = (double) r10;
        r8 = r8 + r10;
        r14 = r0.group(r6);
        r6 = java.lang.Long.parseLong(r14);
        r6 = (double) r6;
        r8 = r8 + r6;
        r14 = r0.group(r5);
        r5 = 0;
        if (r14 == 0) goto L_0x0046;
    L_0x0041:
        r10 = java.lang.Double.parseDouble(r14);
        goto L_0x0047;
    L_0x0046:
        r10 = r5;
    L_0x0047:
        r8 = r8 + r10;
        r14 = r0.group(r4);
        if (r14 == 0) goto L_0x0058;
    L_0x004e:
        r10 = java.lang.Long.parseLong(r14);
        r14 = (float) r10;
        r1 = r15.effectiveFrameRate;
        r14 = r14 / r1;
        r10 = (double) r14;
        goto L_0x0059;
    L_0x0058:
        r10 = r5;
    L_0x0059:
        r8 = r8 + r10;
        r14 = 6;
        r14 = r0.group(r14);
        if (r14 == 0) goto L_0x006f;
    L_0x0061:
        r0 = java.lang.Long.parseLong(r14);
        r0 = (double) r0;
        r14 = r15.subFrameRate;
        r4 = (double) r14;
        r0 = r0 / r4;
        r14 = r15.effectiveFrameRate;
        r14 = (double) r14;
        r5 = r0 / r14;
    L_0x006f:
        r8 = r8 + r5;
        r8 = r8 * r2;
        r14 = (long) r8;
        return r14;
    L_0x0073:
        r0 = OFFSET_TIME;
        r0 = r0.matcher(r14);
        r1 = r0.matches();
        if (r1 == 0) goto L_0x0102;
    L_0x007f:
        r14 = r0.group(r8);
        r9 = java.lang.Double.parseDouble(r14);
        r14 = r0.group(r7);
        r0 = -1;
        r1 = r14.hashCode();
        r11 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r1 == r11) goto L_0x00d5;
    L_0x0094:
        r5 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        if (r1 == r5) goto L_0x00cb;
    L_0x0098:
        r5 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        if (r1 == r5) goto L_0x00c1;
    L_0x009c:
        r5 = 3494; // 0xda6 float:4.896E-42 double:1.7263E-320;
        if (r1 == r5) goto L_0x00b7;
    L_0x00a0:
        switch(r1) {
            case 115: goto L_0x00ad;
            case 116: goto L_0x00a4;
            default: goto L_0x00a3;
        };
    L_0x00a3:
        goto L_0x00df;
    L_0x00a4:
        r1 = "t";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00df;
    L_0x00ac:
        goto L_0x00e0;
    L_0x00ad:
        r1 = "s";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00df;
    L_0x00b5:
        r4 = r7;
        goto L_0x00e0;
    L_0x00b7:
        r1 = "ms";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00df;
    L_0x00bf:
        r4 = r6;
        goto L_0x00e0;
    L_0x00c1:
        r1 = "m";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00df;
    L_0x00c9:
        r4 = r8;
        goto L_0x00e0;
    L_0x00cb:
        r1 = "h";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00df;
    L_0x00d3:
        r4 = 0;
        goto L_0x00e0;
    L_0x00d5:
        r1 = "f";
        r14 = r14.equals(r1);
        if (r14 == 0) goto L_0x00df;
    L_0x00dd:
        r4 = r5;
        goto L_0x00e0;
    L_0x00df:
        r4 = r0;
    L_0x00e0:
        switch(r4) {
            case 0: goto L_0x00f9;
            case 1: goto L_0x00f5;
            case 2: goto L_0x00ff;
            case 3: goto L_0x00ee;
            case 4: goto L_0x00e9;
            case 5: goto L_0x00e4;
            default: goto L_0x00e3;
        };
    L_0x00e3:
        goto L_0x00ff;
    L_0x00e4:
        r14 = r15.tickRate;
        r14 = (double) r14;
        r9 = r9 / r14;
        goto L_0x00ff;
    L_0x00e9:
        r14 = r15.effectiveFrameRate;
        r14 = (double) r14;
        r9 = r9 / r14;
        goto L_0x00ff;
    L_0x00ee:
        r14 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r9 = r9 / r14;
        goto L_0x00ff;
    L_0x00f5:
        r14 = 4633641066610819072; // 0x404e000000000000 float:0.0 double:60.0;
        r9 = r9 * r14;
        goto L_0x00ff;
    L_0x00f9:
        r14 = 4660134898793709568; // 0x40ac200000000000 float:0.0 double:3600.0;
        r9 = r9 * r14;
    L_0x00ff:
        r9 = r9 * r2;
        r14 = (long) r9;
        return r14;
    L_0x0102:
        r15 = new com.google.android.exoplayer2.text.SubtitleDecoderException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Malformed time expression: ";
        r0.append(r1);
        r0.append(r14);
        r14 = r0.toString();
        r15.<init>(r14);
        throw r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseTimeExpression(java.lang.String, com.google.android.exoplayer2.text.ttml.TtmlDecoder$FrameAndTickRate):long");
    }
}
