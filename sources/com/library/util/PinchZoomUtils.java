package com.library.util;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class PinchZoomUtils {
    static final int CLICK = 3;
    static final int DRAG = 1;
    static final int NONE = 0;
    static final int ZOOM = 2;
    OnSingleTapConfirmedlistener OnSingleTapConfirmedlistener;
    boolean doubleTap = false;
    boolean enableZoom = false;
    PointF last = new PointF();
    float[] m;
    private Context mContext;
    ImageView mImageView;
    ScaleGestureDetector mScaleDetector;
    Matrix matrix;
    float maxScale = 3.0f;
    float minScale = 1.0f;
    int mode = 0;
    int oldMeasuredHeight;
    int oldMeasuredWidth;
    protected float origHeight;
    protected float origWidth;
    float saveScale = 1.0f;
    private float scaleImage = 1.2f;
    PointF start = new PointF();
    int viewHeight;
    int viewWidth;

    public interface OnSingleTapConfirmedlistener {
        void onSingleClick();
    }

    private class ScaleListener extends SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        /* synthetic */ ScaleListener(PinchZoomUtils pinchZoomUtils, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            PinchZoomUtils.this.mode = 2;
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            float f = PinchZoomUtils.this.saveScale;
            PinchZoomUtils pinchZoomUtils = PinchZoomUtils.this;
            pinchZoomUtils.saveScale *= scaleFactor;
            if (PinchZoomUtils.this.saveScale > PinchZoomUtils.this.maxScale) {
                PinchZoomUtils.this.saveScale = PinchZoomUtils.this.maxScale;
                scaleFactor = PinchZoomUtils.this.maxScale / f;
            } else if (PinchZoomUtils.this.saveScale < PinchZoomUtils.this.minScale) {
                PinchZoomUtils.this.saveScale = PinchZoomUtils.this.minScale;
                scaleFactor = PinchZoomUtils.this.minScale / f;
            }
            if (PinchZoomUtils.this.origWidth * PinchZoomUtils.this.saveScale <= ((float) PinchZoomUtils.this.viewWidth) || PinchZoomUtils.this.origHeight * PinchZoomUtils.this.saveScale <= ((float) PinchZoomUtils.this.viewHeight)) {
                PinchZoomUtils.this.matrix.postScale(scaleFactor, scaleFactor, (float) (PinchZoomUtils.this.viewWidth / 2), (float) (PinchZoomUtils.this.viewHeight / 2));
            } else {
                PinchZoomUtils.this.matrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            }
            PinchZoomUtils.this.fixTrans();
            return true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public float getFixDragTrans(float f, float f2, float f3) {
        return f3 <= f2 ? 0.0f : f;
    }

    /* Access modifiers changed, original: 0000 */
    public float getFixTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            f3 = f2 - f3;
            f2 = 0.0f;
        } else {
            f2 -= f3;
            f3 = 0.0f;
        }
        return f < f2 ? (-f) + f2 : f > f3 ? (-f) + f3 : 0.0f;
    }

    public PinchZoomUtils(Context context) {
        this.mContext = context;
    }

    public void setOnSingleTapConfirmed(OnSingleTapConfirmedlistener onSingleTapConfirmedlistener) {
        this.OnSingleTapConfirmedlistener = onSingleTapConfirmedlistener;
    }

    public void enablePinchZoom(ImageView imageView) {
        this.mImageView = imageView;
        this.mScaleDetector = new ScaleGestureDetector(this.mContext, new ScaleListener(this, null));
        this.matrix = new Matrix();
        this.m = new float[9];
        this.mImageView.setImageMatrix(this.matrix);
        this.mImageView.setScaleType(ScaleType.MATRIX);
        final GestureDetector gestureDetector = new GestureDetector(this.mContext, new SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (PinchZoomUtils.this.doubleTap) {
                    PinchZoomUtils.this.doubleTapToZoom(1.0f / PinchZoomUtils.this.scaleImage);
                    PinchZoomUtils.this.doubleTap = false;
                } else {
                    PinchZoomUtils.this.doubleTapToZoom(PinchZoomUtils.this.scaleImage);
                    PinchZoomUtils.this.doubleTap = true;
                }
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (PinchZoomUtils.this.OnSingleTapConfirmedlistener != null) {
                    PinchZoomUtils.this.OnSingleTapConfirmedlistener.onSingleClick();
                }
                return true;
            }
        });
        imageView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                PinchZoomUtils.this.pinchToZoom(view, motionEvent);
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    private void pinchToZoom(View view, MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > 1 && this.doubleTap) {
            doubleTapToZoom(1.0f / this.scaleImage);
            this.doubleTap = false;
        }
        this.mScaleDetector.onTouchEvent(motionEvent);
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        int action = motionEvent.getAction();
        if (action != 6) {
            switch (action) {
                case 0:
                    this.last.set(pointF);
                    this.start.set(this.last);
                    this.mode = 1;
                    break;
                case 1:
                    this.mode = 0;
                    Math.abs(pointF.x - this.start.x);
                    Math.abs(pointF.y - this.start.y);
                    break;
                case 2:
                    if (this.mode == 1) {
                        float f = pointF.y - this.last.y;
                        this.matrix.postTranslate(getFixDragTrans(pointF.x - this.last.x, (float) this.viewWidth, this.origWidth * this.saveScale), getFixDragTrans(f, (float) this.viewHeight, this.origHeight * this.saveScale));
                        fixTrans();
                        this.last.set(pointF.x, pointF.y);
                        break;
                    }
                    break;
            }
        }
        this.mode = 0;
        this.mImageView.setImageMatrix(this.matrix);
        this.mImageView.invalidate();
    }

    public void setScale(float f) {
        this.scaleImage = f;
    }

    public void doubleTapToZoom(float f) {
        this.matrix.postScale(f, f, (float) (this.viewWidth / 2), (float) (this.viewHeight / 2));
        this.saveScale *= f;
        fixTrans();
    }

    /* Access modifiers changed, original: 0000 */
    public void fixTrans() {
        this.matrix.getValues(this.m);
        float f = this.m[2];
        float f2 = this.m[5];
        f = getFixTrans(f, (float) this.viewWidth, this.origWidth * this.saveScale);
        f2 = getFixTrans(f2, (float) this.viewHeight, this.origHeight * this.saveScale);
        if (f != 0.0f || f2 != 0.0f) {
            this.matrix.postTranslate(f, f2);
        }
    }

    public void onMeasureCalled(int i, int i2) {
        this.viewWidth = MeasureSpec.getSize(i);
        this.viewHeight = MeasureSpec.getSize(i2);
        if ((this.oldMeasuredHeight != this.viewWidth || this.oldMeasuredHeight != this.viewHeight) && this.viewWidth != 0 && this.viewHeight != 0) {
            this.oldMeasuredHeight = this.viewHeight;
            this.oldMeasuredWidth = this.viewWidth;
            if (this.saveScale == 1.0f) {
                Drawable drawable = this.mImageView.getDrawable();
                if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0) {
                    i2 = drawable.getIntrinsicWidth();
                    float f = (float) i2;
                    float intrinsicHeight = (float) drawable.getIntrinsicHeight();
                    float min = Math.min(((float) this.viewWidth) / f, ((float) this.viewHeight) / intrinsicHeight);
                    this.matrix.setScale(min, min);
                    float f2 = (((float) this.viewHeight) - (intrinsicHeight * min)) / 2.0f;
                    intrinsicHeight = (((float) this.viewWidth) - (min * f)) / 2.0f;
                    this.matrix.postTranslate(intrinsicHeight, f2);
                    this.origWidth = ((float) this.viewWidth) - (intrinsicHeight * 2.0f);
                    this.origHeight = ((float) this.viewHeight) - (2.0f * f2);
                    this.mImageView.setImageMatrix(this.matrix);
                } else {
                    return;
                }
            }
            fixTrans();
        }
    }
}
