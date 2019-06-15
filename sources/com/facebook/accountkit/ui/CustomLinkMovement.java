package com.facebook.accountkit.ui;

import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.widget.TextView;

final class CustomLinkMovement extends LinkMovementMethod {
    private final OnURLClickedListener listener;

    interface OnURLClickedListener {
        void onURLClicked(String str);
    }

    public CustomLinkMovement(OnURLClickedListener onURLClickedListener) {
        this.listener = onURLClickedListener;
    }

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            int x = (((int) motionEvent.getX()) - textView.getTotalPaddingLeft()) + textView.getScrollX();
            int y = (((int) motionEvent.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY();
            Layout layout = textView.getLayout();
            x = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(x, x, URLSpan.class);
            if (uRLSpanArr.length != 0) {
                this.listener.onURLClicked(uRLSpanArr[0].getURL());
            }
        }
        return super.onTouchEvent(textView, spannable, motionEvent);
    }
}
