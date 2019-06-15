package com.facebook.accountkit.ui;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

final class KeyboardObserver {
    private static final int MINIMUM_KEYBOARD_HEIGHT_DP = (100 + (VERSION.SDK_INT >= 21 ? 48 : 0));
    private boolean didCalculateVisibleFrame = false;
    private final Rect lastRootViewVisibleFrame = new Rect();
    private final Rect lastViewVisibleFrame = new Rect();
    private OnVisibleFrameChangedListener onVisibleFrameChangedListener;
    private final Rect rootViewVisibleFrame = new Rect();

    public interface OnVisibleFrameChangedListener {
        void onVisibleFrameChanged(Rect rect);
    }

    public KeyboardObserver(View view) {
        configureGlobalObserver(view);
    }

    public void setOnVisibleFrameChangedListener(@Nullable OnVisibleFrameChangedListener onVisibleFrameChangedListener) {
        this.onVisibleFrameChangedListener = onVisibleFrameChangedListener;
        if (this.didCalculateVisibleFrame && onVisibleFrameChangedListener != null) {
            onVisibleFrameChangedListener.onVisibleFrameChanged(this.lastViewVisibleFrame);
        }
    }

    private void configureGlobalObserver(final View view) {
        if (view != null) {
            final View rootView = view.getRootView();
            if (rootView != null) {
                rootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        KeyboardObserver.this.checkVisibleFrame(view, rootView);
                    }
                });
                checkVisibleFrame(view, rootView);
            }
        }
    }

    private void checkVisibleFrame(View view, View view2) {
        int dimensionPixelSize = ViewUtility.getDimensionPixelSize(view2.getContext(), MINIMUM_KEYBOARD_HEIGHT_DP);
        view2.getWindowVisibleDisplayFrame(this.rootViewVisibleFrame);
        if ((view2.getHeight() - (this.rootViewVisibleFrame.bottom - this.rootViewVisibleFrame.top) >= dimensionPixelSize) && !this.rootViewVisibleFrame.equals(this.lastRootViewVisibleFrame)) {
            this.lastRootViewVisibleFrame.set(this.rootViewVisibleFrame);
            view.getGlobalVisibleRect(this.lastViewVisibleFrame);
            this.didCalculateVisibleFrame = true;
            if (this.onVisibleFrameChangedListener != null) {
                this.onVisibleFrameChangedListener.onVisibleFrameChanged(this.lastViewVisibleFrame);
            }
        }
    }
}
