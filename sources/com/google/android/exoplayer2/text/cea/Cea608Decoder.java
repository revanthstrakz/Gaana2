package com.google.android.exoplayer2.text.cea;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.gaana.login.GooglePlusLogin;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.api.client.http.HttpStatusCodes;
import com.moe.pushlibrary.MoEWorker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cea608Decoder extends CeaDecoder {
    private static final int[] BASIC_CHARACTER_SET = new int[]{32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, GooglePlusLogin.RC_SIGN_IN, 241, 9632};
    private static final int CC_FIELD_FLAG = 1;
    private static final byte CC_IMPLICIT_DATA_HEADER = (byte) -4;
    private static final int CC_MODE_PAINT_ON = 3;
    private static final int CC_MODE_POP_ON = 2;
    private static final int CC_MODE_ROLL_UP = 1;
    private static final int CC_MODE_UNKNOWN = 0;
    private static final int CC_TYPE_FLAG = 2;
    private static final int CC_VALID_608_ID = 4;
    private static final int CC_VALID_FLAG = 4;
    private static final int[] COLUMN_INDICES = new int[]{0, 4, 8, 12, 16, 20, 24, 28};
    private static final byte CTRL_BACKSPACE = (byte) 33;
    private static final byte CTRL_CARRIAGE_RETURN = (byte) 45;
    private static final byte CTRL_DELETE_TO_END_OF_ROW = (byte) 36;
    private static final byte CTRL_END_OF_CAPTION = (byte) 47;
    private static final byte CTRL_ERASE_DISPLAYED_MEMORY = (byte) 44;
    private static final byte CTRL_ERASE_NON_DISPLAYED_MEMORY = (byte) 46;
    private static final byte CTRL_RESUME_CAPTION_LOADING = (byte) 32;
    private static final byte CTRL_RESUME_DIRECT_CAPTIONING = (byte) 41;
    private static final byte CTRL_ROLL_UP_CAPTIONS_2_ROWS = (byte) 37;
    private static final byte CTRL_ROLL_UP_CAPTIONS_3_ROWS = (byte) 38;
    private static final byte CTRL_ROLL_UP_CAPTIONS_4_ROWS = (byte) 39;
    private static final int DEFAULT_CAPTIONS_ROW_COUNT = 4;
    private static final int NTSC_CC_FIELD_1 = 0;
    private static final int NTSC_CC_FIELD_2 = 1;
    private static final int[] ROW_INDICES = new int[]{11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] SPECIAL_CHARACTER_SET = new int[]{174, 176, PsExtractor.PRIVATE_STREAM_1, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = new int[]{193, HttpStatusCodes.STATUS_CODE_CREATED, GooglePlusLogin.RC_CREDENTIALS_SAVE, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, PsExtractor.AUDIO_STREAM, 194, 199, 200, 202, 203, 235, 206, 207, 239, GooglePlusLogin.RC_HINT, 217, 249, 219, 171, 187};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = new int[]{195, 227, 205, HttpStatusCodes.STATUS_CODE_NO_CONTENT, 236, GooglePlusLogin.RC_CREDENTIALS_READ, 242, 213, 245, MoEWorker.REQ_CODE_SEND_DATA, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private static final int[] STYLE_COLORS = new int[]{-1, -16711936, -16776961, -16711681, SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY, -65281};
    private static final int STYLE_ITALICS = 7;
    private static final int STYLE_UNCHANGED = 8;
    private int captionMode;
    private int captionRowCount;
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ArrayList<CueBuilder> cueBuilders = new ArrayList();
    private List<Cue> cues;
    private CueBuilder currentCueBuilder = new CueBuilder(0, 4);
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedField;

    private static class CueBuilder {
        private static final int BASE_ROW = 15;
        private static final int SCREEN_CHARWIDTH = 32;
        private int captionMode;
        private int captionRowCount;
        private final StringBuilder captionStringBuilder = new StringBuilder();
        private final List<CueStyle> cueStyles = new ArrayList();
        private int indent;
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private int row;
        private int tabOffset;

        private static class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int i, boolean z, int i2) {
                this.style = i;
                this.underline = z;
                this.start = i2;
            }
        }

        public CueBuilder(int i, int i2) {
            reset(i);
            setCaptionRowCount(i2);
        }

        public void reset(int i) {
            this.captionMode = i;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public void setCaptionRowCount(int i) {
            this.captionRowCount = i;
        }

        public boolean isEmpty() {
            return this.cueStyles.isEmpty() && this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
                int size = this.cueStyles.size() - 1;
                while (size >= 0) {
                    CueStyle cueStyle = (CueStyle) this.cueStyles.get(size);
                    if (cueStyle.start == length) {
                        cueStyle.start--;
                        size--;
                    } else {
                        return;
                    }
                }
            }
        }

        public int getRow() {
            return this.row;
        }

        public void setRow(int i) {
            this.row = i;
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildSpannableString());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int min = Math.min(this.captionRowCount, this.row);
            while (this.rolledUpCaptions.size() >= min) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public void setIndent(int i) {
            this.indent = i;
        }

        public void setTab(int i) {
            this.tabOffset = i;
        }

        public void setStyle(int i, boolean z) {
            this.cueStyles.add(new CueStyle(i, z, this.captionStringBuilder.length()));
        }

        public void append(char c) {
            this.captionStringBuilder.append(c);
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            int i = 0;
            int i2 = i;
            int i3 = i2;
            int i4 = -1;
            int i5 = i4;
            int i6 = i5;
            int i7 = i6;
            while (i < this.cueStyles.size()) {
                CueStyle cueStyle = (CueStyle) this.cueStyles.get(i);
                boolean z = cueStyle.underline;
                int i8 = cueStyle.style;
                if (i8 != 8) {
                    int i9 = i8 == 7 ? 1 : 0;
                    if (i8 != 7) {
                        i7 = Cea608Decoder.STYLE_COLORS[i8];
                    }
                    i3 = i9;
                }
                int i10 = cueStyle.start;
                i++;
                if (i10 != (i < this.cueStyles.size() ? ((CueStyle) this.cueStyles.get(i)).start : length)) {
                    if (i4 != -1 && !z) {
                        setUnderlineSpan(spannableStringBuilder, i4, i10);
                        i4 = -1;
                    } else if (i4 == -1 && z) {
                        i4 = i10;
                    }
                    if (i5 != -1 && i3 == 0) {
                        setItalicSpan(spannableStringBuilder, i5, i10);
                        i5 = -1;
                    } else if (i5 == -1 && i3 != 0) {
                        i5 = i10;
                    }
                    if (i7 != i6) {
                        setColorSpan(spannableStringBuilder, i2, i10, i6);
                        i6 = i7;
                        i2 = i10;
                    }
                }
            }
            if (!(i4 == -1 || i4 == length)) {
                setUnderlineSpan(spannableStringBuilder, i4, length);
            }
            if (!(i5 == -1 || i5 == length)) {
                setItalicSpan(spannableStringBuilder, i5, length);
            }
            if (i2 != length) {
                setColorSpan(spannableStringBuilder, i2, length, i6);
            }
            return new SpannableString(spannableStringBuilder);
        }

        public Cue build() {
            int i;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (i = 0; i < this.rolledUpCaptions.size(); i++) {
                spannableStringBuilder.append((CharSequence) this.rolledUpCaptions.get(i));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(buildSpannableString());
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            float f;
            int i2;
            int i3;
            i = this.indent + this.tabOffset;
            int length = (32 - i) - spannableStringBuilder.length();
            int i4 = i - length;
            if (this.captionMode == 2 && (Math.abs(i4) < 3 || length < 0)) {
                f = 0.5f;
                i2 = 1;
            } else if (this.captionMode != 2 || i4 <= 0) {
                i2 = 0;
                f = ((((float) i) / 32.0f) * 0.8f) + 0.1f;
            } else {
                f = ((((float) (32 - length)) / 32.0f) * 0.8f) + 0.1f;
                i2 = 2;
            }
            if (this.captionMode == 1 || this.row > 7) {
                i = (this.row - 15) - 2;
                i3 = 2;
            } else {
                i = this.row;
                i3 = 0;
            }
            return new Cue(spannableStringBuilder, Alignment.ALIGN_NORMAL, (float) i, 1, i3, f, i2, Float.MIN_VALUE);
        }

        public String toString() {
            return this.captionStringBuilder.toString();
        }

        private static void setUnderlineSpan(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }

        private static void setItalicSpan(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i, i2, 33);
        }

        private static void setColorSpan(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3) {
            if (i3 != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), i, i2, 33);
            }
        }
    }

    private static boolean isMidrowCtrlCode(byte b, byte b2) {
        return (b & 247) == 17 && (b2 & PsExtractor.VIDEO_STREAM_MASK) == 32;
    }

    private static boolean isMiscCode(byte b, byte b2) {
        return (b & 247) == 20 && (b2 & PsExtractor.VIDEO_STREAM_MASK) == 32;
    }

    private static boolean isPreambleAddressCode(byte b, byte b2) {
        return (b & PsExtractor.VIDEO_STREAM_MASK) == 16 && (b2 & PsExtractor.AUDIO_STREAM) == 64;
    }

    private static boolean isRepeatable(byte b) {
        return (b & PsExtractor.VIDEO_STREAM_MASK) == 16;
    }

    private static boolean isTabCtrlCode(byte b, byte b2) {
        return (b & 247) == 23 && b2 >= CTRL_BACKSPACE && b2 <= (byte) 35;
    }

    public String getName() {
        return "Cea608Decoder";
    }

    public void release() {
    }

    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    public /* bridge */ /* synthetic */ void setPositionUs(long j) {
        super.setPositionUs(j);
    }

    public Cea608Decoder(String str, int i) {
        this.packetLength = MimeTypes.APPLICATION_MP4CEA608.equals(str) ? 2 : 3;
        switch (i) {
            case 3:
            case 4:
                this.selectedField = 2;
                break;
            default:
                this.selectedField = 1;
                break;
        }
        setCaptionMode(0);
        resetCueBuilders();
    }

    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = (byte) 0;
        this.repeatableControlCc2 = (byte) 0;
    }

    /* Access modifiers changed, original: protected */
    public boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    /* Access modifiers changed, original: protected */
    public Subtitle createSubtitle() {
        this.lastCues = this.cues;
        return new CeaSubtitle(this.cues);
    }

    /* Access modifiers changed, original: protected */
    public void decode(SubtitleInputBuffer subtitleInputBuffer) {
        this.ccData.reset(subtitleInputBuffer.data.array(), subtitleInputBuffer.data.limit());
        boolean z = false;
        boolean z2 = z;
        while (this.ccData.bytesLeft() >= this.packetLength) {
            int i;
            if (this.packetLength == 2) {
                i = -4;
            } else {
                i = (byte) this.ccData.readUnsignedByte();
            }
            byte readUnsignedByte = (byte) (this.ccData.readUnsignedByte() & 127);
            byte readUnsignedByte2 = (byte) (this.ccData.readUnsignedByte() & 127);
            if ((i & 6) == 4) {
                if (this.selectedField != 1 || (i & 1) == 0) {
                    if (this.selectedField != 2 || (i & 1) == 1) {
                        if (readUnsignedByte != (byte) 0 || readUnsignedByte2 != (byte) 0) {
                            if ((readUnsignedByte & 247) == 17 && (readUnsignedByte2 & PsExtractor.VIDEO_STREAM_MASK) == 48) {
                                this.currentCueBuilder.append(getSpecialChar(readUnsignedByte2));
                            } else if ((readUnsignedByte & 246) == 18 && (readUnsignedByte2 & 224) == 32) {
                                this.currentCueBuilder.backspace();
                                if ((readUnsignedByte & 1) == 0) {
                                    this.currentCueBuilder.append(getExtendedEsFrChar(readUnsignedByte2));
                                } else {
                                    this.currentCueBuilder.append(getExtendedPtDeChar(readUnsignedByte2));
                                }
                            } else if ((readUnsignedByte & 224) == 0) {
                                z2 = handleCtrl(readUnsignedByte, readUnsignedByte2);
                            } else {
                                this.currentCueBuilder.append(getChar(readUnsignedByte));
                                if ((readUnsignedByte2 & 224) != 0) {
                                    this.currentCueBuilder.append(getChar(readUnsignedByte2));
                                }
                            }
                            z = true;
                        }
                    }
                }
            }
        }
        if (z) {
            if (!z2) {
                this.repeatableControlSet = false;
            }
            if (this.captionMode == 1 || this.captionMode == 3) {
                this.cues = getDisplayCues();
            }
        }
    }

    private boolean handleCtrl(byte b, byte b2) {
        boolean isRepeatable = isRepeatable(b);
        if (isRepeatable) {
            if (this.repeatableControlSet && this.repeatableControlCc1 == b && this.repeatableControlCc2 == b2) {
                this.repeatableControlSet = false;
                return true;
            }
            this.repeatableControlSet = true;
            this.repeatableControlCc1 = b;
            this.repeatableControlCc2 = b2;
        }
        if (isMidrowCtrlCode(b, b2)) {
            handleMidrowCtrl(b2);
        } else if (isPreambleAddressCode(b, b2)) {
            handlePreambleAddressCode(b, b2);
        } else if (isTabCtrlCode(b, b2)) {
            this.currentCueBuilder.setTab(b2 - 32);
        } else if (isMiscCode(b, b2)) {
            handleMiscCode(b2);
        }
        return isRepeatable;
    }

    private void handleMidrowCtrl(byte b) {
        this.currentCueBuilder.append(' ');
        this.currentCueBuilder.setStyle((b >> 1) & 7, (b & 1) == 1);
    }

    private void handlePreambleAddressCode(byte b, byte b2) {
        int i = ROW_INDICES[b & 7];
        boolean z = false;
        if (((b2 & 32) != 0 ? 1 : false) != 0) {
            i++;
        }
        if (i != this.currentCueBuilder.getRow()) {
            if (!(this.captionMode == 1 || this.currentCueBuilder.isEmpty())) {
                this.currentCueBuilder = new CueBuilder(this.captionMode, this.captionRowCount);
                this.cueBuilders.add(this.currentCueBuilder);
            }
            this.currentCueBuilder.setRow(i);
        }
        i = (b2 & 16) == 16 ? 1 : false;
        if ((b2 & 1) == 1) {
            z = true;
        }
        int i2 = (b2 >> 1) & 7;
        this.currentCueBuilder.setStyle(i != 0 ? 8 : i2, z);
        if (i != 0) {
            this.currentCueBuilder.setIndent(COLUMN_INDICES[i2]);
        }
    }

    private void handleMiscCode(byte b) {
        if (b == CTRL_RESUME_CAPTION_LOADING) {
            setCaptionMode(2);
        } else if (b != CTRL_RESUME_DIRECT_CAPTIONING) {
            switch (b) {
                case (byte) 37:
                    setCaptionMode(1);
                    setCaptionRowCount(2);
                    return;
                case (byte) 38:
                    setCaptionMode(1);
                    setCaptionRowCount(3);
                    return;
                case (byte) 39:
                    setCaptionMode(1);
                    setCaptionRowCount(4);
                    return;
                default:
                    if (this.captionMode != 0) {
                        if (b != CTRL_BACKSPACE) {
                            if (b != CTRL_DELETE_TO_END_OF_ROW) {
                                switch (b) {
                                    case (byte) 44:
                                        this.cues = Collections.emptyList();
                                        if (this.captionMode == 1 || this.captionMode == 3) {
                                            resetCueBuilders();
                                            break;
                                        }
                                    case (byte) 45:
                                        if (this.captionMode == 1 && !this.currentCueBuilder.isEmpty()) {
                                            this.currentCueBuilder.rollUp();
                                            break;
                                        }
                                    case (byte) 46:
                                        resetCueBuilders();
                                        break;
                                    case (byte) 47:
                                        this.cues = getDisplayCues();
                                        resetCueBuilders();
                                        break;
                                }
                            }
                        }
                        this.currentCueBuilder.backspace();
                        return;
                    }
                    return;
            }
        } else {
            setCaptionMode(3);
        }
    }

    private List<Cue> getDisplayCues() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.cueBuilders.size(); i++) {
            Cue build = ((CueBuilder) this.cueBuilders.get(i)).build();
            if (build != null) {
                arrayList.add(build);
            }
        }
        return arrayList;
    }

    private void setCaptionMode(int i) {
        if (this.captionMode != i) {
            int i2 = this.captionMode;
            this.captionMode = i;
            resetCueBuilders();
            if (i2 == 3 || i == 1 || i == 0) {
                this.cues = Collections.emptyList();
            }
        }
    }

    private void setCaptionRowCount(int i) {
        this.captionRowCount = i;
        this.currentCueBuilder.setCaptionRowCount(i);
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private static char getChar(byte b) {
        return (char) BASIC_CHARACTER_SET[(b & 127) - 32];
    }

    private static char getSpecialChar(byte b) {
        return (char) SPECIAL_CHARACTER_SET[b & 15];
    }

    private static char getExtendedEsFrChar(byte b) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b & 31];
    }

    private static char getExtendedPtDeChar(byte b) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b & 31];
    }
}
