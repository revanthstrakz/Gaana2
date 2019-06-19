package com.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
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
import com.gaana.R;
import java.util.ArrayList;
import java.util.List;

public class VideoSlidingUpPanelLayout extends ViewGroup {
    private static final String d = "VideoSlidingUpPanelLayout";
    private static PanelState e = PanelState.COLLAPSED;
    private static final int[] f = new int[]{16842927};
    private int A;
    private float B;
    private boolean C;
    private boolean D;
    private boolean E;
    private float F;
    private float G;
    private float H;
    private boolean I;
    private List<b> J;
    private OnClickListener K;
    private int L;
    private int M;
    private int N;
    private int O;
    private final j P;
    private boolean Q;
    private final Rect R;
    private boolean S;
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
    private PanelState x;
    private PanelState y;
    private float z;

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

    public enum PanelState {
        EXPANDED,
        COLLAPSED,
        ANCHORED,
        HIDDEN,
        currentPanelState,
        DRAGGING
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
        PanelState a;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            PanelState panelState;
            super(parcel);
            String readString = parcel.readString();
            if (readString != null) {
                try {
                    panelState = (PanelState) Enum.valueOf(PanelState.class, readString);
                } catch (IllegalArgumentException unused) {
                    this.a = PanelState.COLLAPSED;
                    return;
                }
            }
            panelState = PanelState.COLLAPSED;
            this.a = panelState;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.a == null ? null : this.a.toString());
        }
    }

    private class a extends com.views.j.a {
        private a() {
        }

        /* synthetic */ a(VideoSlidingUpPanelLayout videoSlidingUpPanelLayout, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean a(View view, int i) {
            boolean z = false;
            if (VideoSlidingUpPanelLayout.this.C) {
                return false;
            }
            if (view == VideoSlidingUpPanelLayout.this.v) {
                z = true;
            }
            return z;
        }

        public void a(int i) {
            if (VideoSlidingUpPanelLayout.this.P.a() == 0) {
                VideoSlidingUpPanelLayout.this.z = VideoSlidingUpPanelLayout.this.a(VideoSlidingUpPanelLayout.this.v.getTop());
                VideoSlidingUpPanelLayout.this.i();
                if (VideoSlidingUpPanelLayout.this.z == 0.5f || (VideoSlidingUpPanelLayout.this.z > 0.499f && VideoSlidingUpPanelLayout.this.z < 0.5f)) {
                    VideoSlidingUpPanelLayout.this.c();
                    VideoSlidingUpPanelLayout.this.setPanelStateInternal(PanelState.EXPANDED);
                } else if (VideoSlidingUpPanelLayout.this.z == 0.0f) {
                    VideoSlidingUpPanelLayout.this.setPanelStateInternal(PanelState.COLLAPSED);
                } else if (VideoSlidingUpPanelLayout.this.z < 0.0f) {
                    VideoSlidingUpPanelLayout.this.setPanelStateInternal(PanelState.HIDDEN);
                    VideoSlidingUpPanelLayout.this.v.setVisibility(4);
                } else {
                    VideoSlidingUpPanelLayout.this.c();
                    VideoSlidingUpPanelLayout.this.setPanelStateInternal(PanelState.ANCHORED);
                }
            }
        }

        public void b(View view, int i) {
            VideoSlidingUpPanelLayout.this.d();
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            VideoSlidingUpPanelLayout.this.b(i2);
            VideoSlidingUpPanelLayout.this.invalidate();
        }

        public void a(View view, float f, float f2) {
            int b;
            if (VideoSlidingUpPanelLayout.this.n) {
                f2 = -f2;
            }
            if (f2 > 0.0f && VideoSlidingUpPanelLayout.this.z <= VideoSlidingUpPanelLayout.this.B) {
                b = VideoSlidingUpPanelLayout.this.a(VideoSlidingUpPanelLayout.this.B);
            } else if (f2 > 0.0f && VideoSlidingUpPanelLayout.this.z > VideoSlidingUpPanelLayout.this.B) {
                b = VideoSlidingUpPanelLayout.this.a(0.5f);
            } else if (f2 < 0.0f && VideoSlidingUpPanelLayout.this.z >= VideoSlidingUpPanelLayout.this.B) {
                b = VideoSlidingUpPanelLayout.this.a(VideoSlidingUpPanelLayout.this.B);
            } else if (f2 < 0.0f && VideoSlidingUpPanelLayout.this.z < VideoSlidingUpPanelLayout.this.B) {
                b = VideoSlidingUpPanelLayout.this.a(0.0f);
            } else if (VideoSlidingUpPanelLayout.this.z >= (VideoSlidingUpPanelLayout.this.B + 0.5f) / 2.0f) {
                b = VideoSlidingUpPanelLayout.this.a(0.5f);
            } else if (VideoSlidingUpPanelLayout.this.z >= VideoSlidingUpPanelLayout.this.B / 2.0f) {
                b = VideoSlidingUpPanelLayout.this.a(VideoSlidingUpPanelLayout.this.B);
            } else {
                b = VideoSlidingUpPanelLayout.this.a(0.0f);
            }
            VideoSlidingUpPanelLayout.this.P.a(view.getLeft(), b);
            VideoSlidingUpPanelLayout.this.invalidate();
        }

        public int a(View view) {
            return VideoSlidingUpPanelLayout.this.A;
        }

        public int a(View view, int i, int i2) {
            int b = VideoSlidingUpPanelLayout.this.a(0.0f);
            i2 = VideoSlidingUpPanelLayout.this.a(0.5f);
            if (VideoSlidingUpPanelLayout.this.n) {
                return Math.min(Math.max(i, i2), b);
            }
            return Math.min(Math.max(i, b), i2);
        }
    }

    public interface b {
        void a(View view, float f);

        void a(View view, PanelState panelState, PanelState panelState2);
    }

    public void setmSlideState(PanelState panelState) {
        this.x = panelState;
    }

    public void setEnableDragViewTouchEvents(boolean z) {
        this.E = z;
    }

    public VideoSlidingUpPanelLayout(Context context) {
        this(context, null);
    }

    public VideoSlidingUpPanelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoSlidingUpPanelLayout(Context context, AttributeSet attributeSet, int i) {
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
        this.x = e;
        this.y = e;
        this.B = 0.5f;
        this.I = false;
        this.J = new ArrayList();
        this.L = -1;
        this.M = -1;
        this.Q = true;
        this.R = new Rect();
        this.S = false;
        if (isInEditMode()) {
            this.j = null;
            this.P = null;
            return;
        }
        Interpolator loadInterpolator;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f);
            if (obtainStyledAttributes != null) {
                setGravity(obtainStyledAttributes.getInt(0, 0));
            }
            obtainStyledAttributes.recycle();
            this.L = context.getResources().getDimensionPixelSize(R.dimen.sliding_action_bar_height);
            this.M = context.getResources().getDimensionPixelOffset(R.dimen.sliding_player_height);
            this.N = context.getResources().getDisplayMetrics().heightPixels;
            this.O = context.getResources().getDimensionPixelSize(R.dimen.player_row_height);
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
                this.B = obtainStyledAttributes2.getFloat(0, 0.5f);
                this.x = PanelState.values()[obtainStyledAttributes2.getInt(5, e.ordinal())];
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
            this.k = (int) ((180.0f * f) + 0.5f);
        }
        if (this.l == -1) {
            this.l = (int) ((4.0f * f) + 0.5f);
            this.l = context.getResources().getDimensionPixelSize(R.dimen.action_bar_shadow_height);
        }
        if (this.m == -1) {
            this.m = (int) (0.0f * f);
        }
        if (this.l <= 0) {
            this.j = null;
        } else if (this.n) {
            this.j = getResources().getDrawable(R.drawable.above_shadow);
        } else {
            this.j = getResources().getDrawable(R.drawable.below_shadow);
        }
        setWillNotDraw(false);
        this.P = j.a((ViewGroup) this, 0.5f, loadInterpolator, new a(this, null));
        this.P.a(((float) this.g) * f);
        this.D = true;
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
            if (!this.Q) {
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
        this.D = z;
    }

    public boolean a() {
        return (!this.D || this.v == null || this.x == PanelState.HIDDEN) ? false : true;
    }

    public void setPanelHeight(int i) {
        if (getPanelHeight() != i) {
            this.k = i;
            if (!this.Q) {
                requestLayout();
            }
            if (getPanelState() == PanelState.COLLAPSED) {
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
        if (!this.Q) {
            invalidate();
        }
    }

    public int getPanelHeight() {
        return this.k;
    }

    public int getCurrentParallaxOffset() {
        int max = (int) (((float) this.m) * Math.max(this.z, 0.0f));
        return this.n ? -max : max;
    }

    public void setParallaxOffset(int i) {
        this.m = i;
        if (!this.Q) {
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
        this.J.add(bVar);
    }

    public void setFadeOnClickListener(OnClickListener onClickListener) {
        this.K = onClickListener;
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
                    if (VideoSlidingUpPanelLayout.this.isEnabled() && VideoSlidingUpPanelLayout.this.a()) {
                        if (VideoSlidingUpPanelLayout.this.x == PanelState.EXPANDED || VideoSlidingUpPanelLayout.this.x == PanelState.ANCHORED) {
                            VideoSlidingUpPanelLayout.this.setPanelState(PanelState.COLLAPSED);
                        } else if (VideoSlidingUpPanelLayout.this.B < 0.5f) {
                            VideoSlidingUpPanelLayout.this.setPanelState(PanelState.ANCHORED);
                        } else {
                            VideoSlidingUpPanelLayout.this.setPanelState(PanelState.EXPANDED);
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
            this.B = f;
            this.Q = true;
            requestLayout();
        }
    }

    public float getAnchorPoint() {
        return this.B;
    }

    public void setOverlayed(boolean z) {
        this.o = z;
    }

    public void setClipPanel(boolean z) {
        this.p = z;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(View view) {
        for (int i = 0; i < this.J.size(); i++) {
            ((b) this.J.get(i)).a(view, this.z);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(View view, PanelState panelState, PanelState panelState2) {
        for (int i = 0; i < this.J.size(); i++) {
            ((b) this.J.get(i)).a(view, panelState, panelState2);
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
        this.Q = true;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.Q = true;
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        i = MeasureSpec.getSize(i);
        MeasureSpec.getMode(i2);
        i2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        }
        mode = getChildCount();
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
            this.x = PanelState.HIDDEN;
        }
        int paddingTop = (i2 - getPaddingTop()) - getPaddingBottom();
        int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
        while (i3 < mode) {
            View childAt = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8 || i3 != 0) {
                int i4;
                int i5;
                int makeMeasureSpec;
                if (childAt == this.w) {
                    i4 = (this.o || this.x == PanelState.HIDDEN) ? paddingTop : paddingTop - this.k;
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
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
                } else {
                    if (layoutParams.a > 0.0f && layoutParams.a < 1.0f) {
                        i4 = (int) (((float) i4) * layoutParams.a);
                    } else if (layoutParams.height != -1) {
                        i4 = layoutParams.height;
                    }
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(i4, 1073741824);
                }
                childAt.measure(i5, makeMeasureSpec);
                if (childAt == this.v) {
                    this.A = this.v.getMeasuredHeight() - this.k;
                }
            }
            i3++;
        }
        setMeasuredDimension(i, i2);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        i = getPaddingTop();
        i2 = getChildCount();
        if (this.Q) {
            switch (this.x) {
                case EXPANDED:
                    this.z = 0.5f;
                    break;
                case ANCHORED:
                    this.z = this.B;
                    break;
                case HIDDEN:
                    this.z = a(a(0.0f) + (this.n ? this.k : -this.k));
                    break;
                default:
                    this.z = 0.0f;
                    break;
            }
        }
        i4 = 0;
        while (i4 < i2) {
            View childAt = getChildAt(i4);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (!(childAt.getVisibility() == 8 && (i4 == 0 || this.Q))) {
                int measuredHeight = childAt.getMeasuredHeight();
                int a = childAt == this.v ? a(this.z) : i;
                if (!(this.n || childAt != this.w || this.o)) {
                    a = a(this.z) + this.v.getMeasuredHeight();
                }
                int i5 = layoutParams.leftMargin + paddingLeft;
                childAt.layout(i5, a, childAt.getMeasuredWidth() + i5, measuredHeight + a);
            }
            i4++;
        }
        if (this.Q) {
            c();
        }
        i();
        this.Q = false;
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 != i4) {
            this.Q = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !a() || QueueSlidingUpPanelLayout.d) {
            return super.onTouchEvent(motionEvent);
        }
        try {
            this.P.b(motionEvent);
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (QueueSlidingUpPanelLayout.d) {
            return super.dispatchTouchEvent(motionEvent);
        }
        super.dispatchTouchEvent(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked == 0) {
            this.P.a(motionEvent);
            this.c = x;
            this.G = x;
            this.F = y;
            this.H = y;
            if (y < ((float) this.L)) {
                this.S = true;
            }
            this.b = false;
        } else if (actionMasked == 2) {
            float f = this.c;
            f = y - this.F;
            this.c = x;
            this.F = y;
            if (!a((int) x, (int) y)) {
                return onTouchEvent(motionEvent);
            }
            if (this.S) {
                return onTouchEvent(motionEvent);
            }
            if (f > 0.0f) {
                if (j() || QueueSlidingUpPanelLayout.d) {
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
                if (this.z < 1.0f) {
                    this.b = false;
                    return onTouchEvent(motionEvent);
                }
                if (!this.b) {
                    this.P.c();
                    motionEvent.setAction(0);
                }
                this.b = true;
                return super.dispatchTouchEvent(motionEvent);
            }
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.S = false;
            if (!this.b) {
                x -= this.G;
                y -= this.H;
                actionMasked = this.P.b();
                if (!this.E || (x * x) + (y * y) >= ((float) (actionMasked * actionMasked))) {
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

    private int a(float f) {
        int measuredHeight = this.v != null ? this.v.getMeasuredHeight() : 0;
        int i = (int) (f * ((float) this.A));
        if (this.n) {
            return ((getMeasuredHeight() - getPaddingBottom()) - this.k) - i;
        }
        return ((getPaddingTop() - measuredHeight) + this.k) + i;
    }

    private float a(int i) {
        int a = a(0.0f);
        return (this.n ? (float) (a - i) : (float) (i - a)) / ((float) this.A);
    }

    public PanelState getPanelState() {
        return this.x;
    }

    public boolean e() {
        return this.x == PanelState.EXPANDED;
    }

    public boolean f() {
        return this.x == PanelState.COLLAPSED;
    }

    public void g() {
        setPanelState(PanelState.COLLAPSED);
    }

    public void h() {
        setPanelState(PanelState.EXPANDED);
    }

    public void setPanelState(PanelState panelState) {
        if (panelState == null || panelState == PanelState.DRAGGING) {
            throw new IllegalArgumentException("Panel state cannot be null or DRAGGING.");
        } else if (isEnabled() && ((this.Q || this.v != null) && panelState != this.x && this.x != PanelState.DRAGGING)) {
            if (!this.Q) {
                if (this.x == PanelState.HIDDEN) {
                    this.v.setVisibility(0);
                    requestLayout();
                }
                switch (panelState) {
                    case EXPANDED:
                        a(0.5f, 0);
                        break;
                    case ANCHORED:
                        a(this.B, 0);
                        break;
                    case HIDDEN:
                        a(a(a(0.0f) + (this.n ? this.k : -this.k)), 0);
                        break;
                    case COLLAPSED:
                        a(0.0f, 0);
                        break;
                }
            }
            setPanelStateInternal(panelState);
        }
    }

    private void setPanelStateInternal(PanelState panelState) {
        if (this.x != panelState) {
            PanelState panelState2 = this.x;
            this.x = panelState;
            a(this, panelState2, panelState);
        }
    }

    @SuppressLint({"NewApi"})
    private void i() {
        if (this.m > 0) {
            int currentParallaxOffset = getCurrentParallaxOffset();
            if (VERSION.SDK_INT >= 11) {
                this.w.setTranslationY((float) currentParallaxOffset);
            }
        }
    }

    private void b(int i) {
        this.y = this.x;
        setPanelStateInternal(PanelState.DRAGGING);
        this.z = a(i);
        i();
        a(this.v);
        LayoutParams layoutParams = (LayoutParams) this.w.getLayoutParams();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) - this.k;
        if (this.z <= 0.0f && !this.o) {
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
        int save = canvas.save(2);
        if (this.v != view) {
            canvas.getClipBounds(this.R);
            if (!this.o) {
                if (this.n) {
                    this.R.bottom = Math.min(this.R.bottom, this.v.getTop());
                } else {
                    this.R.top = Math.max(this.R.top, this.v.getBottom());
                }
            }
            if (this.p) {
                canvas.clipRect(this.R);
            }
            drawChild = super.drawChild(canvas, view, j);
            if (this.h != 0 && this.z > 0.0f) {
                this.i.setColor((((int) (((float) ((this.h & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.z)) << 24) | (this.h & ViewCompat.MEASURED_SIZE_MASK));
                canvas.drawRect(this.R, this.i);
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
        if (!this.P.a(this.v, this.v.getLeft(), a(f))) {
            return false;
        }
        d();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (this.P != null && this.P.a(true)) {
            if (isEnabled()) {
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                this.P.d();
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
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.x != PanelState.DRAGGING) {
            savedState.a = this.x;
        } else {
            savedState.a = this.y;
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.x = savedState.a != null ? savedState.a : e;
    }

    public void setSlidingEnabled(boolean z) {
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
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager == null) {
                return false;
            }
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            if (findFirstVisibleItemPosition > 0) {
                return true;
            }
            View childAt = recyclerView.getChildAt(0);
            if (childAt == null) {
                i = 0;
            } else {
                i = this.L + ((findFirstVisibleItemPosition * recyclerView.getHeight()) - childAt.getTop());
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
