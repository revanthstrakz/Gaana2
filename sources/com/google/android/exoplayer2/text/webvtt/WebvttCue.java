package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Log;

public final class WebvttCue extends Cue {
    public final long endTime;
    public final long startTime;

    /* renamed from: com.google.android.exoplayer2.text.webvtt.WebvttCue$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment = new int[Alignment.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        static {
            /*
            r0 = android.text.Layout.Alignment.values();
            r0 = r0.length;
            r0 = new int[r0];
            $SwitchMap$android$text$Layout$Alignment = r0;
            r0 = $SwitchMap$android$text$Layout$Alignment;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = android.text.Layout.Alignment.ALIGN_NORMAL;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = $SwitchMap$android$text$Layout$Alignment;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = android.text.Layout.Alignment.ALIGN_CENTER;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = $SwitchMap$android$text$Layout$Alignment;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = android.text.Layout.Alignment.ALIGN_OPPOSITE;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.webvtt.WebvttCue$AnonymousClass1.<clinit>():void");
        }
    }

    public static class Builder {
        private static final String TAG = "WebvttCueBuilder";
        private long endTime;
        private float line;
        private int lineAnchor;
        private int lineType;
        private float position;
        private int positionAnchor;
        private long startTime;
        private SpannableStringBuilder text;
        private Alignment textAlignment;
        private float width;

        public Builder() {
            reset();
        }

        public void reset() {
            this.startTime = 0;
            this.endTime = 0;
            this.text = null;
            this.textAlignment = null;
            this.line = Float.MIN_VALUE;
            this.lineType = Integer.MIN_VALUE;
            this.lineAnchor = Integer.MIN_VALUE;
            this.position = Float.MIN_VALUE;
            this.positionAnchor = Integer.MIN_VALUE;
            this.width = Float.MIN_VALUE;
        }

        public WebvttCue build() {
            if (this.position != Float.MIN_VALUE && this.positionAnchor == Integer.MIN_VALUE) {
                derivePositionAnchorFromAlignment();
            }
            return new WebvttCue(this.startTime, this.endTime, this.text, this.textAlignment, this.line, this.lineType, this.lineAnchor, this.position, this.positionAnchor, this.width);
        }

        public Builder setStartTime(long j) {
            this.startTime = j;
            return this;
        }

        public Builder setEndTime(long j) {
            this.endTime = j;
            return this;
        }

        public Builder setText(SpannableStringBuilder spannableStringBuilder) {
            this.text = spannableStringBuilder;
            return this;
        }

        public Builder setTextAlignment(Alignment alignment) {
            this.textAlignment = alignment;
            return this;
        }

        public Builder setLine(float f) {
            this.line = f;
            return this;
        }

        public Builder setLineType(int i) {
            this.lineType = i;
            return this;
        }

        public Builder setLineAnchor(int i) {
            this.lineAnchor = i;
            return this;
        }

        public Builder setPosition(float f) {
            this.position = f;
            return this;
        }

        public Builder setPositionAnchor(int i) {
            this.positionAnchor = i;
            return this;
        }

        public Builder setWidth(float f) {
            this.width = f;
            return this;
        }

        private Builder derivePositionAnchorFromAlignment() {
            if (this.textAlignment != null) {
                switch (AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[this.textAlignment.ordinal()]) {
                    case 1:
                        this.positionAnchor = 0;
                        break;
                    case 2:
                        this.positionAnchor = 1;
                        break;
                    case 3:
                        this.positionAnchor = 2;
                        break;
                    default:
                        String str = TAG;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unrecognized alignment: ");
                        stringBuilder.append(this.textAlignment);
                        Log.w(str, stringBuilder.toString());
                        this.positionAnchor = 0;
                        break;
                }
            }
            this.positionAnchor = Integer.MIN_VALUE;
            return this;
        }
    }

    public WebvttCue(CharSequence charSequence) {
        this(0, 0, charSequence);
    }

    public WebvttCue(long j, long j2, CharSequence charSequence) {
        this(j, j2, charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public WebvttCue(long j, long j2, CharSequence charSequence, Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        super(charSequence, alignment, f, i, i2, f2, i3, f3);
        this.startTime = j;
        this.endTime = j2;
    }

    public boolean isNormalCue() {
        return this.line == Float.MIN_VALUE && this.position == Float.MIN_VALUE;
    }
}
