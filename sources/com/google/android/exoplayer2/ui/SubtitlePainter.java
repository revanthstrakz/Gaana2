package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

final class SubtitlePainter {
    private static final float INNER_PADDING_RATIO = 0.125f;
    private static final String TAG = "SubtitlePainter";
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Alignment cueTextAlignment;
    private float cueTextSizePx;
    private float defaultTextSizePx;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint = new TextPaint();
    private int textTop;
    private int windowColor;

    public SubtitlePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((2.0f * ((float) context.getResources().getDisplayMetrics().densityDpi)) / 160.0f);
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        this.textPaint.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Style.FILL);
    }

    public void draw(Cue cue, boolean z, boolean z2, CaptionStyleCompat captionStyleCompat, float f, float f2, float f3, Canvas canvas, int i, int i2, int i3, int i4) {
        Cue cue2 = cue;
        boolean z3 = z;
        boolean z4 = z2;
        CaptionStyleCompat captionStyleCompat2 = captionStyleCompat;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        Canvas canvas2 = canvas;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        boolean z5 = cue2.bitmap == null;
        int i9 = ViewCompat.MEASURED_STATE_MASK;
        if (z5) {
            if (!TextUtils.isEmpty(cue2.text)) {
                i9 = (cue2.windowColorSet && z3) ? cue2.windowColor : captionStyleCompat2.windowColor;
            } else {
                return;
            }
        }
        if (areCharSequencesEqual(this.cueText, cue2.text) && Util.areEqual(this.cueTextAlignment, cue2.textAlignment) && this.cueBitmap == cue2.bitmap && this.cueLine == cue2.line && this.cueLineType == cue2.lineType && Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue2.lineAnchor)) && this.cuePosition == cue2.position && Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue2.positionAnchor)) && this.cueSize == cue2.size && this.cueBitmapHeight == cue2.bitmapHeight && this.applyEmbeddedStyles == z3 && this.applyEmbeddedFontSizes == z4 && this.foregroundColor == captionStyleCompat2.foregroundColor && this.backgroundColor == captionStyleCompat2.backgroundColor && this.windowColor == i9 && this.edgeType == captionStyleCompat2.edgeType && this.edgeColor == captionStyleCompat2.edgeColor && Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat2.typeface) && this.defaultTextSizePx == f4 && this.cueTextSizePx == f5 && this.bottomPaddingFraction == f6 && this.parentLeft == i5 && this.parentTop == i6 && this.parentRight == i7 && this.parentBottom == i8) {
            drawLayout(canvas, z5);
            return;
        }
        canvas2 = canvas;
        this.cueText = cue2.text;
        this.cueTextAlignment = cue2.textAlignment;
        this.cueBitmap = cue2.bitmap;
        this.cueLine = cue2.line;
        this.cueLineType = cue2.lineType;
        this.cueLineAnchor = cue2.lineAnchor;
        this.cuePosition = cue2.position;
        this.cuePositionAnchor = cue2.positionAnchor;
        this.cueSize = cue2.size;
        this.cueBitmapHeight = cue2.bitmapHeight;
        this.applyEmbeddedStyles = z3;
        this.applyEmbeddedFontSizes = z4;
        this.foregroundColor = captionStyleCompat2.foregroundColor;
        this.backgroundColor = captionStyleCompat2.backgroundColor;
        this.windowColor = i9;
        this.edgeType = captionStyleCompat2.edgeType;
        this.edgeColor = captionStyleCompat2.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat2.typeface);
        this.defaultTextSizePx = f4;
        this.cueTextSizePx = f5;
        this.bottomPaddingFraction = f6;
        this.parentLeft = i5;
        this.parentTop = i6;
        this.parentRight = i7;
        this.parentBottom = i8;
        if (z5) {
            setupTextLayout();
        } else {
            setupBitmapLayout();
        }
        drawLayout(canvas2, z5);
    }

    private void setupTextLayout() {
        int i = this.parentRight - this.parentLeft;
        int i2 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.defaultTextSizePx);
        int i3 = (int) ((this.defaultTextSizePx * INNER_PADDING_RATIO) + 0.5f);
        int i4 = i3 * 2;
        int i5 = i - i4;
        if (this.cueSize != Float.MIN_VALUE) {
            i5 = (int) (((float) i5) * this.cueSize);
        }
        if (i5 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        SpannableStringBuilder spannableStringBuilder;
        int length;
        CharSequence charSequence;
        CharSequence charSequence2 = this.cueText;
        if (this.applyEmbeddedStyles) {
            if (!this.applyEmbeddedFontSizes) {
                spannableStringBuilder = new SpannableStringBuilder(charSequence2);
                length = spannableStringBuilder.length();
                AbsoluteSizeSpan[] absoluteSizeSpanArr = (AbsoluteSizeSpan[]) spannableStringBuilder.getSpans(0, length, AbsoluteSizeSpan.class);
                RelativeSizeSpan[] relativeSizeSpanArr = (RelativeSizeSpan[]) spannableStringBuilder.getSpans(0, length, RelativeSizeSpan.class);
                for (Object removeSpan : absoluteSizeSpanArr) {
                    spannableStringBuilder.removeSpan(removeSpan);
                }
                for (Object removeSpan2 : relativeSizeSpanArr) {
                    spannableStringBuilder.removeSpan(removeSpan2);
                }
            } else if (this.cueTextSizePx > 0.0f) {
                spannableStringBuilder = new SpannableStringBuilder(charSequence2);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) this.cueTextSizePx), 0, spannableStringBuilder.length(), 16711680);
            }
            charSequence2 = spannableStringBuilder;
        } else {
            charSequence2 = charSequence2.toString();
        }
        if (Color.alpha(this.backgroundColor) > 0) {
            spannableStringBuilder = new SpannableStringBuilder(charSequence2);
            spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), 0, spannableStringBuilder.length(), 16711680);
            charSequence = spannableStringBuilder;
        } else {
            charSequence = charSequence2;
        }
        Alignment alignment = this.cueTextAlignment == null ? Alignment.ALIGN_CENTER : this.cueTextAlignment;
        this.textLayout = new StaticLayout(charSequence, this.textPaint, i5, alignment, this.spacingMult, this.spacingAdd, true);
        length = this.textLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int i6 = 0;
        for (int i7 = 0; i7 < lineCount; i7++) {
            i6 = Math.max((int) Math.ceil((double) this.textLayout.getLineWidth(i7)), i6);
        }
        if (this.cueSize == Float.MIN_VALUE || i6 >= i5) {
            i5 = i6;
        }
        i5 += i4;
        if (this.cuePosition != Float.MIN_VALUE) {
            i = Math.round(((float) i) * this.cuePosition) + this.parentLeft;
            if (this.cuePositionAnchor == 2) {
                i -= i5;
            } else if (this.cuePositionAnchor == 1) {
                i = ((i * 2) - i5) / 2;
            }
            i = Math.max(i, this.parentLeft);
            i4 = Math.min(i5 + i, this.parentRight);
        } else {
            i = ((i - i5) / 2) + this.parentLeft;
            i4 = i + i5;
        }
        int i8 = i4 - i;
        if (i8 <= 0) {
            Log.w(TAG, "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        if (this.cueLine != Float.MIN_VALUE) {
            if (this.cueLineType == 0) {
                i2 = Math.round(((float) i2) * this.cueLine) + this.parentTop;
            } else {
                i2 = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                if (this.cueLine >= 0.0f) {
                    i2 = Math.round(this.cueLine * ((float) i2)) + this.parentTop;
                } else {
                    i2 = Math.round((this.cueLine + 1.0f) * ((float) i2)) + this.parentBottom;
                }
            }
            if (this.cueLineAnchor == 2) {
                i2 -= length;
            } else if (this.cueLineAnchor == 1) {
                i2 = ((i2 * 2) - length) / 2;
            }
            if (i2 + length > this.parentBottom) {
                i2 = this.parentBottom - length;
            } else if (i2 < this.parentTop) {
                i2 = this.parentTop;
            }
        } else {
            i2 = (this.parentBottom - length) - ((int) (((float) i2) * this.bottomPaddingFraction));
        }
        this.textLayout = new StaticLayout(charSequence, this.textPaint, i8, alignment, this.spacingMult, this.spacingAdd, true);
        this.textLeft = i;
        this.textTop = i2;
        this.textPaddingX = i3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x005e  */
    private void setupBitmapLayout() {
        /*
        r7 = this;
        r0 = r7.parentRight;
        r1 = r7.parentLeft;
        r0 = r0 - r1;
        r1 = r7.parentBottom;
        r2 = r7.parentTop;
        r1 = r1 - r2;
        r2 = r7.parentLeft;
        r2 = (float) r2;
        r0 = (float) r0;
        r3 = r7.cuePosition;
        r3 = r3 * r0;
        r2 = r2 + r3;
        r3 = r7.parentTop;
        r3 = (float) r3;
        r1 = (float) r1;
        r4 = r7.cueLine;
        r4 = r4 * r1;
        r3 = r3 + r4;
        r4 = r7.cueSize;
        r0 = r0 * r4;
        r0 = java.lang.Math.round(r0);
        r4 = r7.cueBitmapHeight;
        r5 = 1;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 == 0) goto L_0x0030;
    L_0x0028:
        r4 = r7.cueBitmapHeight;
        r1 = r1 * r4;
        r1 = java.lang.Math.round(r1);
        goto L_0x0045;
    L_0x0030:
        r1 = (float) r0;
        r4 = r7.cueBitmap;
        r4 = r4.getHeight();
        r4 = (float) r4;
        r5 = r7.cueBitmap;
        r5 = r5.getWidth();
        r5 = (float) r5;
        r4 = r4 / r5;
        r1 = r1 * r4;
        r1 = java.lang.Math.round(r1);
    L_0x0045:
        r4 = r7.cueLineAnchor;
        r5 = 1;
        r6 = 2;
        if (r4 != r6) goto L_0x004e;
    L_0x004b:
        r4 = (float) r0;
    L_0x004c:
        r2 = r2 - r4;
        goto L_0x0056;
    L_0x004e:
        r4 = r7.cueLineAnchor;
        if (r4 != r5) goto L_0x0056;
    L_0x0052:
        r4 = r0 / 2;
        r4 = (float) r4;
        goto L_0x004c;
    L_0x0056:
        r2 = java.lang.Math.round(r2);
        r4 = r7.cuePositionAnchor;
        if (r4 != r6) goto L_0x0061;
    L_0x005e:
        r4 = (float) r1;
    L_0x005f:
        r3 = r3 - r4;
        goto L_0x0069;
    L_0x0061:
        r4 = r7.cuePositionAnchor;
        if (r4 != r5) goto L_0x0069;
    L_0x0065:
        r4 = r1 / 2;
        r4 = (float) r4;
        goto L_0x005f;
    L_0x0069:
        r3 = java.lang.Math.round(r3);
        r4 = new android.graphics.Rect;
        r0 = r0 + r2;
        r1 = r1 + r3;
        r4.<init>(r2, r3, r0, r1);
        r7.bitmapRect = r4;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupBitmapLayout():void");
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
        } else {
            drawBitmapLayout(canvas);
        }
    }

    private void drawTextLayout(Canvas canvas) {
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout != null) {
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.paint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), 0.0f, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.paint);
            }
            int i = 1;
            if (this.edgeType == 1) {
                this.textPaint.setStrokeJoin(Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (this.edgeType == 2) {
                this.textPaint.setShadowLayer(this.shadowRadius, this.shadowOffset, this.shadowOffset, this.edgeColor);
            } else if (this.edgeType == 3 || this.edgeType == 4) {
                int i2;
                if (this.edgeType != 3) {
                    i = 0;
                }
                int i3 = -1;
                if (i != 0) {
                    i2 = -1;
                } else {
                    i2 = this.edgeColor;
                }
                if (i != 0) {
                    i3 = this.edgeColor;
                }
                float f = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Style.FILL);
                float f2 = -f;
                this.textPaint.setShadowLayer(this.shadowRadius, f2, f2, i2);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f, f, i3);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            canvas.restoreToCount(save);
        }
    }

    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, null, this.bitmapRect, null);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
