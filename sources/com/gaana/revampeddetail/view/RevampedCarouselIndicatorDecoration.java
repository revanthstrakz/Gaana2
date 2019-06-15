package com.gaana.revampeddetail.view;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;

public class RevampedCarouselIndicatorDecoration extends ItemDecoration {
    private static final float DP = Resources.getSystem().getDisplayMetrics().density;
    private int colorActive;
    private int colorInactive;
    private final int mIndicatorHeight = ((int) (DP * 12.0f));
    private final float mIndicatorItemLength = (DP * 2.0f);
    private final float mIndicatorItemPadding = (DP * 5.0f);
    private final float mIndicatorStrokeWidth = (DP * 2.0f);
    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private final Paint mPaint = new Paint();

    public RevampedCarouselIndicatorDecoration() {
        this.mPaint.setStrokeWidth(this.mIndicatorStrokeWidth);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setAntiAlias(true);
        if (Constants.l) {
            this.colorInactive = ContextCompat.getColor(GaanaApplication.getContext(), R.color.black_alfa_30);
            this.colorActive = ContextCompat.getColor(GaanaApplication.getContext(), R.color.black_alfa_90);
            return;
        }
        this.colorInactive = ContextCompat.getColor(GaanaApplication.getContext(), R.color.white_alfa_30);
        this.colorActive = ContextCompat.getColor(GaanaApplication.getContext(), R.color.white_alfa_90);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        super.onDrawOver(canvas, recyclerView, state);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (itemCount >= 2) {
            float width = (((float) recyclerView.getWidth()) - ((this.mIndicatorItemLength * ((float) itemCount)) + (((float) Math.max(0, itemCount - 1)) * this.mIndicatorItemPadding))) / 2.0f;
            float height = ((float) recyclerView.getHeight()) - (((float) this.mIndicatorHeight) / 2.0f);
            drawInactiveIndicators(canvas, width, height, itemCount);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition != -1) {
                View findViewByPosition = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                int left = findViewByPosition.getLeft();
                int width2 = findViewByPosition.getWidth();
                findViewByPosition.getRight();
                drawHighlights(canvas, width, height, findFirstVisibleItemPosition, this.mInterpolator.getInterpolation(((float) (left * -1)) / ((float) width2)));
            }
        }
    }

    private void drawInactiveIndicators(Canvas canvas, float f, float f2, int i) {
        this.mPaint.setColor(this.colorInactive);
        float f3 = this.mIndicatorItemLength + this.mIndicatorItemPadding;
        for (int i2 = 0; i2 < i; i2++) {
            canvas.drawCircle(f, f2, this.mIndicatorItemLength / 2.0f, this.mPaint);
            f += f3;
        }
    }

    private void drawHighlights(Canvas canvas, float f, float f2, int i, float f3) {
        this.mPaint.setColor(this.colorActive);
        float f4 = this.mIndicatorItemLength + this.mIndicatorItemPadding;
        if (f3 == 0.0f) {
            canvas.drawCircle(f + (f4 * ((float) i)), f2, this.mIndicatorItemLength / 2.0f, this.mPaint);
        } else {
            canvas.drawCircle((f + (f4 * ((float) i))) + ((this.mIndicatorItemLength * f3) + (this.mIndicatorItemPadding * f3)), f2, this.mIndicatorItemLength / 2.0f, this.mPaint);
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        rect.bottom = this.mIndicatorHeight;
    }
}
