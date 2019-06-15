package com.views;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class e implements OnTouchListener {
    private final GestureDetector gestureDetector;

    private final class a extends SimpleOnGestureListener {
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        private a() {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            e.this.onTap();
            return super.onSingleTapUp(motionEvent);
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return super.onSingleTapConfirmed(motionEvent);
        }

        public boolean onContextClick(MotionEvent motionEvent) {
            return super.onContextClick(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            try {
                float y = motionEvent2.getY() - motionEvent.getY();
                float x = motionEvent2.getX() - motionEvent.getX();
                if (Math.abs(x) > Math.abs(y)) {
                    if (Math.abs(x) <= 100.0f || Math.abs(f) <= 100.0f) {
                        return false;
                    }
                    if (x > 0.0f) {
                        e.this.onSwipeRight();
                    } else {
                        e.this.onSwipeLeft();
                    }
                } else if (Math.abs(y) <= 100.0f || Math.abs(f2) <= 100.0f) {
                    return false;
                } else {
                    if (y > 0.0f) {
                        e.this.onSwipeBottom();
                    } else {
                        e.this.onSwipeTop();
                    }
                }
                return true;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return false;
            }
        }
    }

    public void onSwipeBottom() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public void onSwipeTop() {
    }

    public void onTap() {
    }

    public e(Context context) {
        this.gestureDetector = new GestureDetector(context, new a());
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    public GestureDetector getGestureDetector() {
        return this.gestureDetector;
    }
}
