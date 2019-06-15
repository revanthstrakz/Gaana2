package com.facebook.accountkit.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

public final class CountryCodeSpinner extends Spinner {
    private OnSpinnerEventsListener listener;
    private boolean openStarted = false;

    interface OnSpinnerEventsListener {
        void onSpinnerClosed();

        void onSpinnerOpened();
    }

    public CountryCodeSpinner(Context context) {
        super(context);
    }

    public CountryCodeSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CountryCodeSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean performClick() {
        this.openStarted = true;
        if (this.listener != null) {
            this.listener.onSpinnerOpened();
        }
        return super.performClick();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.openStarted && z) {
            performClosedEvent();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setOnSpinnerEventsListener(OnSpinnerEventsListener onSpinnerEventsListener) {
        this.listener = onSpinnerEventsListener;
    }

    private void performClosedEvent() {
        this.openStarted = false;
        if (this.listener != null) {
            this.listener.onSpinnerClosed();
        }
    }
}
