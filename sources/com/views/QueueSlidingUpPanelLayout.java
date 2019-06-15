package com.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ListView;
import android.widget.ScrollView;
import com.constants.Constants;
import com.gaana.R;
import java.util.ArrayList;
import java.util.List;

public class QueueSlidingUpPanelLayout extends ViewGroup {
    public static boolean d = false;
    private static final String e = "QueueSlidingUpPanelLayout";
    private static final int[] f = new int[]{16842927};
    private static int x;
    private float A;
    private int B;
    private float C;
    private boolean D;
    private boolean E;
    private boolean F;
    private float G;
    private float H;
    private float I;
    private boolean J;
    private b K;
    private List<b> L;
    private OnClickListener M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private final j R;
    private boolean S;
    private final Rect T;
    private boolean U;
    View a;
    boolean b;
    float c;
    private int g;
    private int h;
    private final Paint i;
    private final Drawable j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private View q;
    private int r;
    private View s;
    private int t;
    private h u;
    private View v;
    private View w;
    private int y;
    private int z;

    public static class LayoutParams extends MarginLayoutParams {
        private static final int[] b = new int[]{16843137};
        public float a = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b);
            if (obtainStyledAttributes != null) {
                this.a = obtainStyledAttributes.getFloat(0, 0.0f);
            }
            obtainStyledAttributes.recycle();
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int a;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            try {
                this.a = parcel.readInt();
            } catch (IllegalArgumentException unused) {
                this.a = 0;
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public interface b {
        void a(View view, float f);

        void a(View view, int i, int i2);
    }

    private class a extends com.views.j.a {
        private a() {
        }

        /* synthetic */ a(QueueSlidingUpPanelLayout queueSlidingUpPanelLayout, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean a(View view, int i) {
            boolean z = false;
            if (QueueSlidingUpPanelLayout.this.D) {
                return false;
            }
            if (view == QueueSlidingUpPanelLayout.this.v) {
                z = true;
            }
            return z;
        }

        public void a(int i) {
            if (QueueSlidingUpPanelLayout.this.R.a() == 0) {
                QueueSlidingUpPanelLayout.this.A = QueueSlidingUpPanelLayout.this.a(QueueSlidingUpPanelLayout.this.v.getTop());
                QueueSlidingUpPanelLayout.this.i();
                if (QueueSlidingUpPanelLayout.this.A == 1.0f) {
                    QueueSlidingUpPanelLayout.this.c();
                    QueueSlidingUpPanelLayout.this.setPanelStateInternal(1);
                } else if (QueueSlidingUpPanelLayout.this.A == 0.0f) {
                    QueueSlidingUpPanelLayout.this.setPanelStateInternal(0);
                } else if (QueueSlidingUpPanelLayout.this.A < 0.0f) {
                    QueueSlidingUpPanelLayout.this.setPanelStateInternal(2);
                    QueueSlidingUpPanelLayout.this.v.setVisibility(4);
                } else {
                    QueueSlidingUpPanelLayout.this.c();
                    QueueSlidingUpPanelLayout.this.setPanelStateInternal(5);
                }
            }
        }

        public void b(View view, int i) {
            QueueSlidingUpPanelLayout.this.d();
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            QueueSlidingUpPanelLayout.this.b(i2);
            QueueSlidingUpPanelLayout.this.invalidate();
        }

        public void a(View view, float f, float f2) {
            int b;
            if (QueueSlidingUpPanelLayout.this.n) {
                f2 = -f2;
            }
            if (f2 > 0.0f && QueueSlidingUpPanelLayout.this.A <= QueueSlidingUpPanelLayout.this.C) {
                b = QueueSlidingUpPanelLayout.this.a(QueueSlidingUpPanelLayout.this.C);
            } else if (f2 > 0.0f && QueueSlidingUpPanelLayout.this.A > QueueSlidingUpPanelLayout.this.C) {
                b = QueueSlidingUpPanelLayout.this.a(1.0f);
            } else if (f2 < 0.0f && QueueSlidingUpPanelLayout.this.A >= QueueSlidingUpPanelLayout.this.C) {
                b = QueueSlidingUpPanelLayout.this.a(QueueSlidingUpPanelLayout.this.C);
            } else if (f2 < 0.0f && QueueSlidingUpPanelLayout.this.A < QueueSlidingUpPanelLayout.this.C) {
                b = QueueSlidingUpPanelLayout.this.a(0.0f);
            } else if (QueueSlidingUpPanelLayout.this.A >= (QueueSlidingUpPanelLayout.this.C + 1.0f) / 2.0f) {
                b = QueueSlidingUpPanelLayout.this.a(1.0f);
            } else if (QueueSlidingUpPanelLayout.this.A >= QueueSlidingUpPanelLayout.this.C / 2.0f) {
                b = QueueSlidingUpPanelLayout.this.a(QueueSlidingUpPanelLayout.this.C);
            } else {
                b = QueueSlidingUpPanelLayout.this.a(0.0f);
            }
            QueueSlidingUpPanelLayout.this.R.a(view.getLeft(), b);
            QueueSlidingUpPanelLayout.this.invalidate();
        }

        public int a(View view) {
            return QueueSlidingUpPanelLayout.this.B;
        }

        public int a(View view, int i, int i2) {
            int b = QueueSlidingUpPanelLayout.this.a(0.0f);
            i2 = QueueSlidingUpPanelLayout.this.a(1.0f);
            if (QueueSlidingUpPanelLayout.this.n) {
                return Math.min(Math.max(i, i2), b);
            }
            return Math.min(Math.max(i, b), i2);
        }
    }

    public void setEnableDragViewTouchEvents(boolean z) {
        this.F = z;
    }

    public QueueSlidingUpPanelLayout(Context context) {
        this(context, null);
    }

    public QueueSlidingUpPanelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QueueSlidingUpPanelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 400;
        this.h = -1728053248;
        this.i = new Paint();
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.o = false;
        this.p = true;
        this.r = -1;
        this.u = new h();
        this.b = false;
        this.y = x;
        this.z = x;
        this.C = 1.0f;
        this.J = false;
        this.L = new ArrayList();
        this.N = -1;
        this.O = -1;
        this.S = true;
        this.T = new Rect();
        this.U = false;
        if (isInEditMode()) {
            this.j = null;
            this.R = null;
            return;
        }
        Interpolator loadInterpolator;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f);
            if (obtainStyledAttributes != null) {
                setGravity(obtainStyledAttributes.getInt(0, 0));
                obtainStyledAttributes.recycle();
            }
            this.N = context.getResources().getDimensionPixelSize(R.dimen.sliding_action_bar_height);
            this.O = context.getResources().getDimensionPixelOffset(R.dimen.sliding_player_height);
            this.P = context.getResources().getDisplayMetrics().heightPixels;
            this.Q = context.getResources().getDimensionPixelSize(R.dimen.player_row_height);
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingUpPanelLayout);
            if (obtainStyledAttributes2 != null) {
                this.k = obtainStyledAttributes2.getDimensionPixelSize(7, -1);
                this.l = obtainStyledAttributes2.getDimensionPixelSize(11, -1);
                this.m = obtainStyledAttributes2.getDimensionPixelSize(8, -1);
                this.g = obtainStyledAttributes2.getInt(4, 400);
                this.h = obtainStyledAttributes2.getColor(3, -1728053248);
                this.r = obtainStyledAttributes2.getResourceId(2, -1);
                this.t = obtainStyledAttributes2.getResourceId(10, -1);
                this.o = obtainStyledAttributes2.getBoolean(6, false);
                this.p = obtainStyledAttributes2.getBoolean(1, true);
                this.C = obtainStyledAttributes2.getFloat(0, 0.88f);
                this.y = x;
                i = obtainStyledAttributes2.getResourceId(9, -1);
                if (i != -1) {
                    loadInterpolator = AnimationUtils.loadInterpolator(context, i);
                    obtainStyledAttributes2.recycle();
                }
            }
            loadInterpolator = null;
            obtainStyledAttributes2.recycle();
        } else {
            loadInterpolator = null;
        }
        float f = context.getResources().getDisplayMetrics().density;
        if (this.k == -1) {
            this.k = (int) ((68.0f * f) + 0.5f);
        }
        if (this.l == -1) {
            this.l = (int) ((4.0f * f) + 0.5f);
            this.l = context.getResources().getDimensionPixelSize(R.dimen.action_bar_shadow_height);
        }
        if (this.m == -1) {
            this.m = (int) (0.0f * f);
        }
        this.j = null;
        setWillNotDraw(false);
        this.R = j.a((ViewGroup) this, 0.5f, loadInterpolator, new a(this, null));
        this.R.a(((float) this.g) * f);
        this.E = true;
    }

    /* Access modifiers changed, original: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (this.r != -1) {
            setDragView(findViewById(this.r));
        }
        if (this.t != -1) {
            setScrollableView(findViewById(this.t));
        }
    }

    public void setGravity(int i) {
        if (i == 48 || i == 80) {
            this.n = i == 80;
            if (!this.S) {
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("gravity must be set to either top or bottom");
    }

    public void setCoveredFadeColor(int i) {
        this.h = i;
        requestLayout();
    }

    public int getCoveredFadeColor() {
        return this.h;
    }

    public void setTouchEnabled(boolean z) {
        this.E = z;
    }

    public boolean a() {
        return (!this.E || this.v == null || this.y == 2) ? false : true;
    }

    public void setPanelHeight(int i) {
        if (getPanelHeight() != i) {
            this.k = i;
            if (!this.S) {
                requestLayout();
            }
            if (getPanelState() == 0) {
                b();
                invalidate();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        a(0.0f, 0);
    }

    public int getShadowHeight() {
        return this.l;
    }

    public void setShadowHeight(int i) {
        this.l = i;
        if (!this.S) {
            invalidate();
        }
    }

    public int getPanelHeight() {
        return this.k;
    }

    public int getCurrentParallaxOffset() {
        int max = (int) (((float) this.m) * Math.max(this.A, 0.0f));
        return this.n ? -max : max;
    }

    public void setParallaxOffset(int i) {
        this.m = i;
        if (!this.S) {
            requestLayout();
        }
    }

    public int getMinFlingVelocity() {
        return this.g;
    }

    public void setMinFlingVelocity(int i) {
        this.g = i;
    }

    public void setPanelSlideListener(b bVar) {
        this.K = bVar;
    }

    public void setFadeOnClickListener(OnClickListener onClickListener) {
        this.M = onClickListener;
    }

    public void setDragView(View view) {
        if (this.q != null) {
            this.q.setOnClickListener(null);
        }
        this.q = view;
        if (this.q != null) {
            this.q.setClickable(true);
            this.q.setFocusable(false);
            this.q.setFocusableInTouchMode(false);
            this.q.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (QueueSlidingUpPanelLayout.this.isEnabled() && QueueSlidingUpPanelLayout.this.a()) {
                        if (QueueSlidingUpPanelLayout.this.y == 1 || QueueSlidingUpPanelLayout.this.y == 5) {
                            QueueSlidingUpPanelLayout.this.setPanelState(0);
                        } else if (QueueSlidingUpPanelLayout.this.C < 1.0f) {
                            QueueSlidingUpPanelLayout.this.setPanelState(5);
                        } else {
                            QueueSlidingUpPanelLayout.this.setPanelState(1);
                        }
                    }
                }
            });
        }
    }

    public void setDragView(int i) {
        this.r = i;
        setDragView(findViewById(i));
    }

    public void setScrollableView(View view) {
        this.s = view;
    }

    public void setScrollingView(View view) {
        this.a = view;
    }

    public void setScrollableViewHelper(h hVar) {
        this.u = hVar;
    }

    public void setAnchorPoint(float f) {
        if (f > 0.0f && f <= 1.0f) {
            this.C = f;
            this.S = true;
            requestLayout();
        }
    }

    public float getAnchorPoint() {
        return this.C;
    }

    public void setOverlayed(boolean z) {
        this.o = z;
    }

    public void setClipPanel(boolean z) {
        this.p = z;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(View view) {
        for (int i = 0; i < this.L.size(); i++) {
            ((b) this.L.get(i)).a(view, this.A);
        }
        if (this.K != null) {
            this.K.a(view, this.A);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(View view, int i, int i2) {
        for (int i3 = 0; i3 < this.L.size(); i3++) {
            ((b) this.L.get(i3)).a(view, i, i2);
        }
        if (this.K != null) {
            this.K.a(view, i, i2);
        }
        sendAccessibilityEvent(32);
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        if (getChildCount() != 0) {
            int i;
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int i2 = 0;
            int i3;
            int i4;
            int i5;
            if (this.v == null || !b(this.v)) {
                i = 0;
                i3 = i;
                i4 = i3;
                i5 = i4;
            } else {
                i = this.v.getLeft();
                i3 = this.v.getRight();
                i4 = this.v.getTop();
                i5 = this.v.getBottom();
            }
            View childAt = getChildAt(0);
            paddingLeft = Math.max(paddingLeft, childAt.getLeft());
            paddingTop = Math.max(paddingTop, childAt.getTop());
            width = Math.min(width, childAt.getRight());
            height = Math.min(height, childAt.getBottom());
            if (paddingLeft >= i && paddingTop >= i4 && width <= i3 && height <= i5) {
                i2 = 4;
            }
            childAt.setVisibility(i2);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    private static boolean b(View view) {
        Drawable background = view.getBackground();
        return background != null && background.getOpacity() == -1;
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.S = true;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.S = true;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        } else if (mode2 == 1073741824 || mode2 == Integer.MIN_VALUE) {
            mode = getChildCount();
            mode2 = 2;
            if (mode != 2) {
                throw new IllegalStateException("Sliding up panel layout must have exactly 2 children!");
            }
            int i3 = 0;
            this.w = getChildAt(0);
            this.v = getChildAt(1);
            if (this.q == null) {
                setDragView(this.v);
            }
            if (this.v.getVisibility() != 0) {
                this.y = 2;
            }
            int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            while (i3 < mode) {
                View childAt = getChildAt(i3);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (childAt.getVisibility() != 8 || i3 != 0) {
                    int i4;
                    int i5;
                    if (childAt == this.w) {
                        i4 = (this.o || this.y == mode2) ? paddingTop : paddingTop - this.k;
                        i5 = paddingLeft - (layoutParams.leftMargin + layoutParams.rightMargin);
                    } else {
                        i4 = childAt == this.v ? paddingTop - layoutParams.topMargin : paddingTop;
                        i5 = paddingLeft;
                    }
                    if (layoutParams.width == -2) {
                        i5 = MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
                    } else if (layoutParams.width == -1) {
                        i5 = MeasureSpec.makeMeasureSpec(i5, 1073741824);
                    } else {
                        i5 = MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                    }
                    if (layoutParams.height == -2) {
                        mode2 = MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
                    } else {
                        if (layoutParams.a > 0.0f && layoutParams.a < 1.0f) {
                            i4 = (int) (((float) i4) * layoutParams.a);
                        } else if (layoutParams.height != -1) {
                            i4 = layoutParams.height;
                        }
                        mode2 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
                    }
                    childAt.measure(i5, mode2);
                    if (childAt == this.v) {
                        this.B = this.v.getMeasuredHeight() - this.k;
                    }
                }
                i3++;
                mode2 = 2;
            }
            setMeasuredDimension(size, size2);
        } else {
            throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
        }
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        i = getPaddingTop();
        i2 = getChildCount();
        if (this.S) {
            i3 = this.y;
            if (i3 != 5) {
                switch (i3) {
                    case 1:
                        this.A = 1.0f;
                        break;
                    case 2:
                        this.A = a(a(0.0f) + (this.n ? this.k : -this.k));
                        break;
                    default:
                        this.A = 0.0f;
                        break;
                }
            }
            this.A = this.C;
        }
        i4 = 0;
        while (i4 < i2) {
            View childAt = getChildAt(i4);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (!(childAt.getVisibility() == 8 && (i4 == 0 || this.S))) {
                int measuredHeight = childAt.getMeasuredHeight();
                int a = childAt == this.v ? a(this.A) : i;
                if (!(this.n || childAt != this.w || this.o)) {
                    a = a(this.A) + this.v.getMeasuredHeight();
                }
                int i5 = layoutParams.leftMargin + paddingLeft;
                childAt.layout(i5, a, childAt.getMeasuredWidth() + i5, measuredHeight + a);
            }
            i4++;
        }
        if (this.S) {
            c();
        }
        i();
        this.S = false;
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 != i4) {
            this.S = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !a()) {
            return super.onTouchEvent(motionEvent);
        }
        try {
            this.R.b(motionEvent);
            if ((motionEvent.getAction() & 255) == 1) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                float f = x - this.H;
                float f2 = y - this.I;
                int b = this.R.b();
                View view;
                if (this.q != null) {
                    view = this.q;
                } else {
                    view = this.v;
                }
                if ((f * f) + (f2 * f2) < ((float) (b * b))) {
                    int i = (int) x;
                    int i2 = (int) y;
                    if (b(i, i2)) {
                        boolean a = a(i, i2);
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked == 0) {
            this.R.a(motionEvent);
            this.c = x;
            this.H = x;
            this.G = y;
            this.I = y;
            if (y < ((float) this.N)) {
                this.U = true;
            }
            this.b = false;
        } else if (actionMasked == 2) {
            float f = this.c;
            f = y - this.G;
            this.c = x;
            this.G = y;
            if (!a((int) x, (int) y)) {
                return onTouchEvent(motionEvent);
            }
            if (this.U) {
                return onTouchEvent(motionEvent);
            }
            if (f > 0.0f) {
                if (j()) {
                    this.b = true;
                    return super.dispatchTouchEvent(motionEvent);
                }
                if (this.b) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction(1);
                    super.dispatchTouchEvent(obtain);
                    obtain.recycle();
                    motionEvent.setAction(0);
                }
                this.b = false;
                return onTouchEvent(motionEvent);
            } else if (f < 0.0f) {
                if (this.A < 1.0f) {
                    this.b = false;
                    return onTouchEvent(motionEvent);
                }
                if (!this.b) {
                    this.R.c();
                    motionEvent.setAction(0);
                }
                this.b = true;
                return super.dispatchTouchEvent(motionEvent);
            }
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.U = false;
            if (!this.b) {
                x -= this.H;
                y -= this.I;
                actionMasked = this.R.b();
                if (!this.F || (x * x) + (y * y) >= ((float) (actionMasked * actionMasked))) {
                    return onTouchEvent(motionEvent);
                }
                return super.dispatchTouchEvent(motionEvent);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean a(int i, int i2) {
        if (this.a == null) {
            return false;
        }
        int[] iArr = new int[2];
        this.a.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr2);
        int i3 = iArr2[0] + i;
        boolean z = true;
        int i4 = iArr2[1] + i2;
        if (i3 < iArr[0] || i3 >= iArr[0] + this.a.getWidth() || i4 < iArr[1] || i4 >= iArr[1] + this.a.getHeight()) {
            z = false;
        }
        return z;
    }

    private boolean b(int i, int i2) {
        if (this.q == null) {
            return false;
        }
        int[] iArr = new int[2];
        this.q.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr2);
        int i3 = iArr2[0] + i;
        boolean z = true;
        int i4 = iArr2[1] + i2;
        if (i3 < iArr[0] || i3 >= iArr[0] + this.q.getWidth() || i4 < iArr[1] || i4 >= iArr[1] + this.q.getHeight()) {
            z = false;
        }
        return z;
    }

    private int a(float f) {
        int measuredHeight = this.v != null ? this.v.getMeasuredHeight() : 0;
        int i = (int) (f * ((float) this.B));
        if (this.n) {
            return ((getMeasuredHeight() - getPaddingBottom()) - this.k) - i;
        }
        return ((getPaddingTop() - measuredHeight) + this.k) + i;
    }

    private float a(int i) {
        int a = a(0.0f);
        return (this.n ? (float) (a - i) : (float) (i - a)) / ((float) this.B);
    }

    public int getPanelState() {
        return this.y;
    }

    public boolean e() {
        return this.A >= 0.5f || this.y == 1;
    }

    public boolean f() {
        return this.y == 0;
    }

    public void g() {
        setPanelState(0);
    }

    public void h() {
        setPanelState(1);
    }

    public void setPanelState(int i) {
        if (i == 4) {
            throw new IllegalArgumentException("Panel state cannot be null or DRAGGING.");
        } else if (isEnabled() && ((this.S || this.v != null) && i != this.y && this.y != 4)) {
            if (this.S) {
                setPanelStateInternal(i);
            } else {
                if (this.y == 2) {
                    this.v.setVisibility(0);
                    requestLayout();
                }
                if (i != 5) {
                    switch (i) {
                        case 0:
                            d = false;
                            a(0.0f, 0);
                            break;
                        case 1:
                            d = true;
                            a(0.88f, 0);
                            break;
                        case 2:
                            a(a(a(0.0f) + (this.n ? this.k : -this.k)), 0);
                            break;
                    }
                }
                d = true;
                a(this.C, 0);
            }
        }
    }

    private void setPanelStateInternal(int i) {
        if (this.y != i) {
            int i2 = this.y;
            this.y = i;
            if (this.y == 1 || this.y == 4) {
                d = true;
            } else if (this.y == 0) {
                d = false;
            }
            a(this, i2, i);
        }
    }

    @SuppressLint({"NewApi"})
    private void i() {
        if (this.m > 0) {
            this.w.setTranslationY((float) getCurrentParallaxOffset());
        }
    }

    private void b(int i) {
        this.z = this.y;
        setPanelStateInternal(4);
        this.A = a(i);
        i();
        a(this.v);
        LayoutParams layoutParams = (LayoutParams) this.w.getLayoutParams();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) - this.k;
        if (this.A <= 0.0f && !this.o) {
            layoutParams.height = this.n ? i - getPaddingBottom() : ((getHeight() - getPaddingBottom()) - this.v.getMeasuredHeight()) - i;
            if (layoutParams.height == height) {
                layoutParams.height = -1;
            }
            this.w.requestLayout();
        } else if (layoutParams.height != -1 && !this.o) {
            layoutParams.height = -1;
            this.w.requestLayout();
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild;
        int save = canvas.save();
        if (this.v != view) {
            canvas.getClipBounds(this.T);
            if (!this.o) {
                if (this.n) {
                    this.T.bottom = Math.min(this.T.bottom, this.v.getTop());
                } else {
                    this.T.top = Math.max(this.T.top, this.v.getBottom());
                }
            }
            if (this.p) {
                canvas.clipRect(this.T);
            }
            drawChild = super.drawChild(canvas, view, j);
            if (this.h != 0 && this.A > 0.0f) {
                this.i.setColor((((int) (((float) ((this.h & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.A)) << 24) | (this.h & ViewCompat.MEASURED_SIZE_MASK));
                canvas.drawRect(this.T, this.i);
            }
        } else {
            drawChild = super.drawChild(canvas, view, j);
        }
        canvas.restoreToCount(save);
        return drawChild;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(float f, int i) {
        if (!isEnabled() || this.v == null) {
            return false;
        }
        if (!this.R.a(this.v, this.v.getLeft(), a(f))) {
            return false;
        }
        d();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (this.R != null && this.R.a(true)) {
            if (isEnabled()) {
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                this.R.d();
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.j != null && this.v != null) {
            int top;
            int top2;
            int right = this.v.getRight();
            if (this.n) {
                top = this.v.getTop() - this.l;
                top2 = this.v.getTop();
            } else {
                top = this.v.getBottom();
                top2 = this.v.getBottom() + this.l;
            }
            this.j.setBounds(this.v.getLeft(), top, right, top2);
            this.j.draw(canvas);
        }
    }

    /* Access modifiers changed, original: protected */
    public android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    /* Access modifiers changed, original: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    /* Access modifiers changed, original: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(((SavedState) parcelable).getSuperState());
    }

    public void setSlidingEnabled(boolean z) {
        z = !Constants.cY && z;
        setTouchEnabled(z);
    }

    private boolean j() {
        boolean z = false;
        if (this.a == null) {
            return false;
        }
        int i;
        if (this.a instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) this.a;
            int findFirstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition > 0) {
                return true;
            }
            View childAt = recyclerView.getChildAt(0);
            if (childAt == null) {
                i = 0;
            } else {
                i = this.N + ((findFirstVisibleItemPosition * recyclerView.getHeight()) - childAt.getTop());
            }
            if (i > 0) {
                z = true;
            }
            return z;
        } else if (this.a instanceof ListView) {
            ListView listView = (ListView) this.a;
            if (listView.getFirstVisiblePosition() > 0) {
                return true;
            }
            View childAt2 = listView.getChildAt(0);
            if (childAt2 == null) {
                i = 0;
            } else {
                i = (-childAt2.getTop()) + (listView.getFirstVisiblePosition() * listView.getHeight());
            }
            if (i > 0) {
                z = true;
            }
            return z;
        } else if (!(this.a instanceof ScrollView)) {
            return false;
        } else {
            if (this.a.getScrollY() > 0) {
                z = true;
            }
            return z;
        }
    }
}
