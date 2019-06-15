package com.gaana.FastScrollRecyclerView;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;

public class FastScrollPopup {
    private float mAlpha = 1.0f;
    private ObjectAnimator mAlphaAnimator;
    private Paint mBackgroundPaint;
    private Path mBackgroundPath = new Path();
    private RectF mBackgroundRect = new RectF();
    private int mBackgroundSize;
    private Rect mBgBounds = new Rect();
    private int mCornerRadius;
    private Rect mInvalidateRect = new Rect();
    private FastScrollRecyclerView mRecyclerView;
    private Resources mRes;
    private String mSectionName;
    private Rect mTextBounds = new Rect();
    private Paint mTextPaint;
    private Rect mTmpRect = new Rect();
    private boolean mVisible;

    public FastScrollPopup(Resources resources, FastScrollRecyclerView fastScrollRecyclerView) {
        this.mRes = resources;
        this.mRecyclerView = fastScrollRecyclerView;
        this.mBackgroundSize = Utils.toPixels(this.mRes, 58.0f);
        this.mCornerRadius = this.mBackgroundSize / 2;
        this.mBackgroundPaint = new Paint(1);
        this.mTextPaint = new Paint(1);
        this.mTextPaint.setAlpha(0);
        this.mTextPaint.setTextSize((float) Utils.toPixels(this.mRes, 36.0f));
    }

    public void setBgColor(int i) {
        this.mBackgroundPaint.setColor(i);
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public void setTextColor(int i) {
        this.mTextPaint.setColor(i);
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public void animateVisibility(boolean z) {
        if (this.mVisible != z) {
            this.mVisible = z;
            if (this.mAlphaAnimator != null) {
                this.mAlphaAnimator.cancel();
            }
            String str = "alpha";
            float[] fArr = new float[1];
            fArr[0] = z ? 1.0f : 0.0f;
            this.mAlphaAnimator = ObjectAnimator.ofFloat(this, str, fArr);
            this.mAlphaAnimator.setDuration(z ? 200 : 150);
            this.mAlphaAnimator.start();
        }
    }

    public void setAlpha(float f) {
        this.mAlpha = f;
        this.mRecyclerView.invalidate(this.mBgBounds);
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void draw(Canvas canvas) {
        if (isVisible()) {
            int save = canvas.save();
            canvas.translate((float) this.mBgBounds.left, (float) this.mBgBounds.top);
            this.mTmpRect.set(this.mBgBounds);
            this.mTmpRect.offsetTo(0, 0);
            this.mBackgroundPath.reset();
            this.mBackgroundRect.set(this.mTmpRect);
            this.mBackgroundPath.addRoundRect(this.mBackgroundRect, Utils.isRtl(this.mRes) ? new float[]{(float) this.mCornerRadius, (float) this.mCornerRadius, (float) this.mCornerRadius, (float) this.mCornerRadius, (float) this.mCornerRadius, (float) this.mCornerRadius, 0.0f, 0.0f} : new float[]{(float) this.mCornerRadius, (float) this.mCornerRadius, (float) this.mCornerRadius, (float) this.mCornerRadius, 0.0f, 0.0f, (float) this.mCornerRadius, (float) this.mCornerRadius}, Direction.CW);
            this.mBackgroundPaint.setAlpha((int) (this.mAlpha * 255.0f));
            this.mTextPaint.setAlpha((int) (this.mAlpha * 255.0f));
            canvas.drawPath(this.mBackgroundPath, this.mBackgroundPaint);
            canvas.drawText(this.mSectionName, (float) ((this.mBgBounds.width() - this.mTextBounds.width()) / 2), (float) (this.mBgBounds.height() - ((this.mBgBounds.height() - this.mTextBounds.height()) / 2)), this.mTextPaint);
            canvas.restoreToCount(save);
        }
    }

    public void setSectionName(String str) {
        if (!str.equals(this.mSectionName)) {
            this.mSectionName = str;
            this.mTextPaint.getTextBounds(str, 0, str.length(), this.mTextBounds);
            this.mTextBounds.right = (int) (((float) this.mTextBounds.left) + this.mTextPaint.measureText(str));
        }
    }

    public Rect updateFastScrollerBounds(FastScrollRecyclerView fastScrollRecyclerView, int i) {
        this.mInvalidateRect.set(this.mBgBounds);
        if (isVisible()) {
            int scrollBarWidth = fastScrollRecyclerView.getScrollBarWidth();
            int height = (this.mBackgroundSize - this.mTextBounds.height()) / 2;
            int i2 = this.mBackgroundSize;
            height = Math.max(this.mBackgroundSize, this.mTextBounds.width() + (height * 2));
            if (Utils.isRtl(this.mRes)) {
                this.mBgBounds.left = fastScrollRecyclerView.getScrollBarWidth() * 2;
                this.mBgBounds.right = this.mBgBounds.left + height;
            } else {
                this.mBgBounds.right = fastScrollRecyclerView.getWidth() - (fastScrollRecyclerView.getScrollBarWidth() * 2);
                this.mBgBounds.left = this.mBgBounds.right - height;
            }
            this.mBgBounds.top = (i - i2) + (fastScrollRecyclerView.getScrollBarThumbHeight() / 2);
            this.mBgBounds.top = Math.max(scrollBarWidth, Math.min(this.mBgBounds.top, (fastScrollRecyclerView.getHeight() - scrollBarWidth) - i2));
            this.mBgBounds.bottom = this.mBgBounds.top + i2;
        } else {
            this.mBgBounds.setEmpty();
        }
        this.mInvalidateRect.union(this.mBgBounds);
        return this.mInvalidateRect;
    }

    public boolean isVisible() {
        return this.mAlpha > 0.0f && !TextUtils.isEmpty(this.mSectionName);
    }
}
