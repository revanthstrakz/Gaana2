package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.webvtt.WebvttCue.Builder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

public final class WebvttDecoder extends SimpleSubtitleDecoder {
    private static final String COMMENT_START = "NOTE";
    private static final int EVENT_COMMENT = 1;
    private static final int EVENT_CUE = 3;
    private static final int EVENT_END_OF_FILE = 0;
    private static final int EVENT_NONE = -1;
    private static final int EVENT_STYLE_BLOCK = 2;
    private static final String STYLE_START = "STYLE";
    private final CssParser cssParser = new CssParser();
    private final WebvttCueParser cueParser = new WebvttCueParser();
    private final List<WebvttCssStyle> definedStyles = new ArrayList();
    private final ParsableByteArray parsableWebvttData = new ParsableByteArray();
    private final Builder webvttCueBuilder = new Builder();

    public WebvttDecoder() {
        super("WebvttDecoder");
    }

    /* Access modifiers changed, original: protected */
    public WebvttSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.parsableWebvttData.reset(bArr, i);
        this.webvttCueBuilder.reset();
        this.definedStyles.clear();
        try {
            WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
            while (!TextUtils.isEmpty(this.parsableWebvttData.readLine())) {
            }
            ArrayList arrayList = new ArrayList();
            while (true) {
                i = getNextEvent(this.parsableWebvttData);
                if (i == 0) {
                    return new WebvttSubtitle(arrayList);
                }
                if (i == 1) {
                    skipComment(this.parsableWebvttData);
                } else if (i == 2) {
                    if (arrayList.isEmpty()) {
                        this.parsableWebvttData.readLine();
                        WebvttCssStyle parseBlock = this.cssParser.parseBlock(this.parsableWebvttData);
                        if (parseBlock != null) {
                            this.definedStyles.add(parseBlock);
                        }
                    } else {
                        throw new SubtitleDecoderException("A style block was found after the first cue.");
                    }
                } else if (i == 3 && this.cueParser.parseCue(this.parsableWebvttData, this.webvttCueBuilder, this.definedStyles)) {
                    arrayList.add(this.webvttCueBuilder.build());
                    this.webvttCueBuilder.reset();
                }
            }
        } catch (ParserException e) {
            throw new SubtitleDecoderException(e);
        }
    }

    private static int getNextEvent(ParsableByteArray parsableByteArray) {
        int i = 0;
        int i2 = -1;
        while (i2 == -1) {
            i = parsableByteArray.getPosition();
            String readLine = parsableByteArray.readLine();
            i2 = readLine == null ? 0 : STYLE_START.equals(readLine) ? 2 : readLine.startsWith(COMMENT_START) ? 1 : 3;
        }
        parsableByteArray.setPosition(i);
        return i2;
    }

    private static void skipComment(ParsableByteArray parsableByteArray) {
        while (!TextUtils.isEmpty(parsableByteArray.readLine())) {
        }
    }
}
