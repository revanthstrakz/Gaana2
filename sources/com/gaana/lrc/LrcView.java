package com.gaana.lrc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.InputDeviceCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.gaana.R;
import com.gaana.lrc.ILrcView.LrcViewListener;
import com.utilities.Util;
import com.utilities.d;
import java.io.Serializable;
import java.util.List;

public class LrcView extends View implements ILrcView, Serializable {
    public static final int DISPLAY_MODE_NORMAL = 0;
    public static final int DISPLAY_MODE_SCALE = 2;
    public static final int DISPLAY_MODE_SEEK = 1;
    public static final String TAG = "LrcView";
    int[] attrs = new int[]{R.attr.first_line_color, R.attr.first_line_color_70};
    private int currentLyricsRow = 0;
    private Handler handler;
    private boolean isFullscreenMode;
    private Context mContext;
    private int mDisplayMode = 0;
    private int mHignlightRow = 0;
    private int mHignlightRowColor = InputDeviceCompat.SOURCE_ANY;
    private boolean mIsFirstMove = false;
    private float mLastMotionY;
    private String mLoadingLrcTip = "";
    private int mLrcFontSize = 23;
    private List<LrcRow> mLrcRows;
    private LrcViewListener mLrcViewListener;
    private int mMaxLrcFontSize = 45;
    private int mMaxSeekLineTextSize = 25;
    private int mMinLrcFontSize = 20;
    private int mMinSeekFiredOffset = 10;
    private int mMinSeekLineTextSize = 20;
    private int mNormalRowColor = -1;
    private int mPaddingY = 30;
    private Paint mPaint;
    private PointF mPointerOneLastMotion = new PointF();
    private PointF mPointerTwoLastMotion = new PointF();
    private int mSeekLineColor = -16711681;
    private int mSeekLinePaddingX = 30;
    private int mSeekLineTextColor = -16711681;
    private int mSeekLineTextSize = 20;
    private int mSeekbarRow = 0;
    private TextPaint mTextPaint;
    private boolean moveActionPerformed = false;
    private boolean touchEventProcessed = false;
    TypedArray typedArray;

    public LrcView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.handler = new Handler(this.mContext.getMainLooper());
        this.mPaint = new Paint(1);
        this.mTextPaint = new TextPaint(1);
        this.mLrcFontSize = Util.b(16);
        this.mPaddingY = Util.b(8);
        this.mPaint.setTextSize((float) this.mLrcFontSize);
        this.mTextPaint.setTextSize((float) this.mLrcFontSize);
        this.typedArray = context.obtainStyledAttributes(this.attrs);
        this.mHignlightRowColor = getResources().getColor(R.color.first_line_color);
        this.mNormalRowColor = getResources().getColor(R.color.header_first_line_70);
        this.mSeekLineColor = getResources().getColor(R.color.first_line_color);
    }

    public void setListener(LrcViewListener lrcViewListener) {
        this.mLrcViewListener = lrcViewListener;
    }

    public void setLoadingTipText(String str) {
        this.mLoadingLrcTip = str;
    }

    public String getHighlightRowTime() {
        if (this.mLrcRows == null) {
            return "";
        }
        if (this.mHignlightRow < this.mLrcRows.size()) {
            return ((LrcRow) this.mLrcRows.get(this.mHignlightRow)).strTime;
        }
        return ((LrcRow) this.mLrcRows.get(this.mLrcRows.size() - 1)).strTime;
    }

    public void setFullscreenMode(boolean z) {
        this.isFullscreenMode = z;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        int height = getHeight();
        int width = getWidth() - Util.b(20);
        int i;
        if (this.mLrcRows == null || this.mLrcRows.size() == 0) {
            i = width;
            if (this.mLoadingLrcTip != null) {
                this.mPaint.setColor(this.mHignlightRowColor);
                this.mPaint.setTextSize((float) this.mLrcFontSize);
                this.mPaint.setTextAlign(Align.CENTER);
                canvas2.drawText(this.mLoadingLrcTip, (float) (i / 2), (float) ((height / 2) - this.mLrcFontSize), this.mPaint);
            }
            return;
        }
        int b = Util.b(20);
        Alignment alignment = Alignment.ALIGN_NORMAL;
        try {
            StaticLayout build;
            StaticLayout staticLayout;
            float f;
            float f2;
            float f3;
            int i2;
            float f4;
            int i3;
            StaticLayout build2;
            int i4;
            int i5;
            String str = ((LrcRow) this.mLrcRows.get(this.mHignlightRow)).content;
            int i6 = height / 2;
            int i7 = this.mLrcFontSize;
            this.mTextPaint.setColor(this.mHignlightRowColor);
            this.mTextPaint.setTextSize((float) this.mLrcFontSize);
            this.mTextPaint.setTextAlign(Align.LEFT);
            this.mTextPaint.setTypeface(Util.h(this.mContext));
            if (d.i()) {
                build = Builder.obtain(str, 0, str.length(), this.mTextPaint, width).setAlignment(Alignment.ALIGN_NORMAL).setLineSpacing(1.0f, 1.0f).setIncludePad(false).setMaxLines(5).build();
            } else {
                staticLayout = new StaticLayout(str, this.mTextPaint, width, alignment, 1.0f, 1.0f, false);
            }
            build.getLineCount();
            canvas.save();
            int i8 = 1;
            float lineCount = (float) (((build.getLineCount() * this.mLrcFontSize) - ((build.getLineCount() - 1) * this.mPaddingY)) / 2);
            int lineCount2 = (build.getLineCount() - 1) * this.mPaddingY;
            int i9 = i6 - ((int) lineCount);
            float f5 = (float) b;
            float f6 = (float) i9;
            canvas2.translate(f5, f6);
            build.draw(canvas2);
            canvas.restore();
            if (this.mDisplayMode == 1) {
                this.mTextPaint.setColor(this.mSeekLineColor);
                f = (float) this.mSeekLinePaddingX;
                float f7 = ((float) i6) + lineCount;
                f2 = (float) (width - this.mSeekLinePaddingX);
                float f8 = f;
                f = f7;
                f3 = lineCount;
                i2 = 5;
                float f9 = f2;
                f2 = 1.0f;
                f4 = f7;
                i3 = width;
                canvas2.drawLine(f8, f, f9, f4, this.mTextPaint);
            } else {
                f2 = 1.0f;
                i3 = width;
                f3 = lineCount;
                i2 = 5;
            }
            this.mTextPaint.setColor(this.mNormalRowColor);
            this.mTextPaint.setTextSize((float) this.mLrcFontSize);
            this.mTextPaint.setTextAlign(Align.LEFT);
            i6 = this.mHignlightRow - 1;
            b = i9;
            while (b > (-this.mLrcFontSize) && i6 >= 0) {
                if (i6 == this.currentLyricsRow) {
                    this.mTextPaint.setColor(this.mHignlightRowColor);
                } else {
                    this.mTextPaint.setColor(this.mNormalRowColor);
                }
                try {
                    String str2 = ((LrcRow) this.mLrcRows.get(i6)).content;
                    if (d.i()) {
                        width = 0;
                        build2 = Builder.obtain(str2, 0, str2.length(), this.mTextPaint, i3).setAlignment(Alignment.ALIGN_NORMAL).setLineSpacing(f2, f2).setIncludePad(false).setMaxLines(i2).build();
                        i4 = i3;
                        f4 = f6;
                        f = f5;
                        i5 = i8;
                    } else {
                        width = 0;
                        i4 = i3;
                        str = str2;
                        f = f2;
                        f4 = f6;
                        f = f5;
                        i5 = i8;
                        staticLayout = new StaticLayout(str, this.mTextPaint, i4, alignment, 1.0f, 1.0f, false);
                    }
                    canvas.save();
                    b = (b - (build2.getLineCount() * this.mLrcFontSize)) - (build2.getLineCount() * this.mPaddingY);
                    canvas2.translate(f, (float) b);
                    build2.draw(canvas2);
                    canvas.restore();
                    if (!this.isFullscreenMode) {
                        break;
                    }
                    i6--;
                    f5 = f;
                    f6 = f4;
                    i3 = i4;
                    i8 = i5;
                    f2 = 1.0f;
                    i2 = 5;
                } catch (Exception unused) {
                    return;
                }
            }
            i4 = i3;
            f4 = f6;
            f = f5;
            i5 = i8;
            width = 0;
            b = this.mHignlightRow + 1;
            i6 = ((int) (f4 + (f3 * 2.0f))) + lineCount2;
            while (i6 < height && b < this.mLrcRows.size()) {
                if (b == this.currentLyricsRow) {
                    this.mTextPaint.setColor(this.mHignlightRowColor);
                } else {
                    this.mTextPaint.setColor(this.mNormalRowColor);
                }
                try {
                    str = ((LrcRow) this.mLrcRows.get(b)).content;
                    int i10;
                    if (d.i()) {
                        i8 = i4;
                        build2 = Builder.obtain(str, width, str.length(), this.mTextPaint, i8).setAlignment(Alignment.ALIGN_NORMAL).setLineSpacing(1.0f, 1.0f).setIncludePad(width).setMaxLines(5).build();
                        i10 = 5;
                        i = i8;
                    } else {
                        i8 = i4;
                        i10 = 5;
                        i = i8;
                        staticLayout = new StaticLayout(str, this.mTextPaint, i8, alignment, 1.0f, 1.0f, false);
                    }
                    canvas.save();
                    i6 += this.mPaddingY;
                    canvas2.translate(f, (float) i6);
                    build2.draw(canvas2);
                    canvas.restore();
                    if (!this.isFullscreenMode) {
                        break;
                    }
                    i6 += (build2.getLineCount() * this.mLrcFontSize) + ((build2.getLineCount() - 1) * this.mPaddingY);
                    b++;
                    i4 = i;
                } catch (Exception unused2) {
                    return;
                }
            }
        } catch (Exception unused3) {
        }
    }

    private float getTextHeight(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return (float) rect.height();
    }

    public void seekLrc() {
        seekLrc(this.mHignlightRow, true);
    }

    public void seekLrc(int i, boolean z) {
        if (this.mLrcRows != null && i >= 0 && i <= this.mLrcRows.size()) {
            LrcRow lrcRow = (LrcRow) this.mLrcRows.get(i);
            this.mHignlightRow = i;
            invalidate();
            if (this.mLrcViewListener != null && z) {
                this.mLrcViewListener.onLrcSeeked(i, lrcRow);
            }
        }
    }

    public void lrcViewClicked() {
        if (!(this.mLrcRows == null || this.mLrcViewListener == null)) {
            this.mLrcViewListener.onLrcClicked();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mLrcRows == null || this.mLrcRows.size() == 0) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.touchEventProcessed = false;
                this.mLastMotionY = motionEvent.getY();
                this.mIsFirstMove = true;
                invalidate();
                break;
            case 1:
                if (!this.moveActionPerformed) {
                    performClick();
                    this.mDisplayMode = 0;
                    invalidate();
                    break;
                }
                this.moveActionPerformed = false;
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        LrcView.this.mLrcViewListener.onLrcScrollStateChanged(false);
                        LrcView.this.mDisplayMode = 0;
                        LrcView.this.invalidate();
                    }
                }, 3000);
                break;
            case 2:
                if (motionEvent.getPointerCount() != 2) {
                    if (this.mDisplayMode != 2) {
                        this.mLrcViewListener.onLrcScrollStateChanged(true);
                        doSeek(motionEvent);
                        break;
                    }
                    return true;
                }
                doScale(motionEvent);
                return true;
        }
        return true;
    }

    private void doScale(MotionEvent motionEvent) {
        if (this.mDisplayMode == 1) {
            this.mDisplayMode = 2;
            return;
        }
        if (this.mIsFirstMove) {
            this.mDisplayMode = 2;
            invalidate();
            this.mIsFirstMove = false;
            setTwoPointerLocation(motionEvent);
        }
        int scale = getScale(motionEvent);
        if (scale != 0) {
            setNewFontSize(scale);
            invalidate();
        }
        setTwoPointerLocation(motionEvent);
    }

    private void doSeek(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        float f = y - this.mLastMotionY;
        if (Math.abs(f) >= ((float) this.mMinSeekFiredOffset)) {
            this.mDisplayMode = 1;
            this.moveActionPerformed = true;
            int abs = Math.abs(((int) f) / this.mLrcFontSize);
            if (f < 0.0f) {
                this.mHignlightRow += abs;
            } else if (f > 0.0f) {
                this.mHignlightRow -= abs;
            }
            this.mHignlightRow = Math.max(0, this.mHignlightRow);
            this.mHignlightRow = Math.min(this.mHignlightRow, this.mLrcRows.size() - 1);
            if (abs > 0) {
                this.mLastMotionY = y;
                invalidate();
            }
        }
    }

    private void setTwoPointerLocation(MotionEvent motionEvent) {
        this.mPointerOneLastMotion.x = motionEvent.getX(0);
        this.mPointerOneLastMotion.y = motionEvent.getY(0);
        this.mPointerTwoLastMotion.x = motionEvent.getX(1);
        this.mPointerTwoLastMotion.y = motionEvent.getY(1);
    }

    private void setNewFontSize(int i) {
        this.mLrcFontSize += i;
        this.mSeekLineTextSize += i;
        this.mLrcFontSize = Math.max(this.mLrcFontSize, this.mMinLrcFontSize);
        this.mLrcFontSize = Math.min(this.mLrcFontSize, this.mMaxLrcFontSize);
        this.mSeekLineTextSize = Math.max(this.mSeekLineTextSize, this.mMinSeekLineTextSize);
        this.mSeekLineTextSize = Math.min(this.mSeekLineTextSize, this.mMaxSeekLineTextSize);
    }

    private int getScale(MotionEvent motionEvent) {
        int i = 0;
        float x = motionEvent.getX(0);
        float y = motionEvent.getY(0);
        float x2 = motionEvent.getX(1);
        float y2 = motionEvent.getY(1);
        float abs = Math.abs(this.mPointerOneLastMotion.x - this.mPointerTwoLastMotion.x);
        x = Math.abs(x2 - x);
        x2 = Math.abs(this.mPointerOneLastMotion.y - this.mPointerTwoLastMotion.y);
        y2 = Math.abs(y2 - y);
        y = x - abs;
        float max = Math.max(Math.abs(y), Math.abs(y2 - x2));
        if (max != Math.abs(y) ? y2 <= x2 : x <= abs) {
            i = 1;
        }
        return i != 0 ? (int) (max / 10.0f) : -((int) (max / 10.0f));
    }

    public void setLrc(List<LrcRow> list) {
        this.mLrcRows = list;
        if (list == null) {
            this.mHignlightRow = 0;
        }
        invalidate();
    }

    public List<LrcRow> getLrcRows() {
        return this.mLrcRows;
    }

    /* JADX WARNING: Missing block: B:26:0x0057, code skipped:
            return;
     */
    public void seekLrcToTime(long r9) {
        /*
        r8 = this;
        r0 = r8.mLrcRows;
        if (r0 == 0) goto L_0x0057;
    L_0x0004:
        r0 = r8.mLrcRows;
        r0 = r0.size();
        if (r0 != 0) goto L_0x000d;
    L_0x000c:
        goto L_0x0057;
    L_0x000d:
        r0 = r8.mDisplayMode;
        if (r0 == 0) goto L_0x0012;
    L_0x0011:
        return;
    L_0x0012:
        r0 = 0;
        r1 = r0;
    L_0x0014:
        r2 = r8.mLrcRows;
        r2 = r2.size();
        if (r1 >= r2) goto L_0x0056;
    L_0x001c:
        r2 = r8.mLrcRows;
        r2 = r2.get(r1);
        r2 = (com.gaana.lrc.LrcRow) r2;
        r3 = r1 + 1;
        r4 = r8.mLrcRows;
        r4 = r4.size();
        if (r3 != r4) goto L_0x0030;
    L_0x002e:
        r4 = 0;
        goto L_0x0038;
    L_0x0030:
        r4 = r8.mLrcRows;
        r4 = r4.get(r3);
        r4 = (com.gaana.lrc.LrcRow) r4;
    L_0x0038:
        r5 = r2.time;
        r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r7 < 0) goto L_0x0046;
    L_0x003e:
        if (r4 == 0) goto L_0x0046;
    L_0x0040:
        r5 = r4.time;
        r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r7 < 0) goto L_0x004e;
    L_0x0046:
        r5 = r2.time;
        r2 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1));
        if (r2 <= 0) goto L_0x0054;
    L_0x004c:
        if (r4 != 0) goto L_0x0054;
    L_0x004e:
        r8.currentLyricsRow = r1;
        r8.seekLrc(r1, r0);
        return;
    L_0x0054:
        r1 = r3;
        goto L_0x0014;
    L_0x0056:
        return;
    L_0x0057:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.lrc.LrcView.seekLrcToTime(long):void");
    }
}
