package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.design.R;
import android.support.design.widget.SwipeDismissBehavior.OnDismissListener;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    private static final boolean USE_OFFSET_API;
    static final Handler sHandler = new Handler(Looper.getMainLooper(), new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    ((BaseTransientBottomBar) message.obj).showView();
                    return true;
                case 1:
                    ((BaseTransientBottomBar) message.obj).hideView(message.arg1);
                    return true;
                default:
                    return false;
            }
        }
    });
    private final AccessibilityManager mAccessibilityManager;
    private List<BaseCallback<B>> mCallbacks;
    private final ContentViewCallback mContentViewCallback;
    private final Context mContext;
    private int mDuration;
    final Callback mManagerCallback = new Callback() {
        public void show() {
            BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(0, BaseTransientBottomBar.this));
        }

        public void dismiss(int i) {
            BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }
    };
    private final ViewGroup mTargetParent;
    final SnackbarBaseLayout mView;

    public interface ContentViewCallback {
        void animateContentIn(int i, int i2);

        void animateContentOut(int i, int i2);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    interface OnAttachStateChangeListener {
        void onViewAttachedToWindow(View view);

        void onViewDetachedFromWindow(View view);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    interface OnLayoutChangeListener {
        void onLayoutChange(View view, int i, int i2, int i3, int i4);
    }

    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @RestrictTo({Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public void onDismissed(B b, int i) {
        }

        public void onShown(B b) {
        }
    }

    final class Behavior extends SwipeDismissBehavior<SnackbarBaseLayout> {
        Behavior() {
        }

        public boolean canSwipeDismissView(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, SnackbarBaseLayout snackbarBaseLayout, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 3) {
                switch (actionMasked) {
                    case 0:
                        if (coordinatorLayout.isPointInChildBounds(snackbarBaseLayout, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                            SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.mManagerCallback);
                            break;
                        }
                        break;
                    case 1:
                        break;
                }
            }
            SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.mManagerCallback);
            return super.onInterceptTouchEvent(coordinatorLayout, snackbarBaseLayout, motionEvent);
        }
    }

    @IntRange(from = 1)
    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static class SnackbarBaseLayout extends FrameLayout {
        private OnAttachStateChangeListener mOnAttachStateChangeListener;
        private OnLayoutChangeListener mOnLayoutChangeListener;

        SnackbarBaseLayout(Context context) {
            this(context, null);
        }

        SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
            }
            obtainStyledAttributes.recycle();
            setClickable(true);
        }

        /* Access modifiers changed, original: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.mOnLayoutChangeListener != null) {
                this.mOnLayoutChangeListener.onLayoutChange(this, i, i2, i3, i4);
            }
        }

        /* Access modifiers changed, original: protected */
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.mOnAttachStateChangeListener != null) {
                this.mOnAttachStateChangeListener.onViewAttachedToWindow(this);
            }
            ViewCompat.requestApplyInsets(this);
        }

        /* Access modifiers changed, original: protected */
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.mOnAttachStateChangeListener != null) {
                this.mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            this.mOnLayoutChangeListener = onLayoutChangeListener;
        }

        /* Access modifiers changed, original: 0000 */
        public void setOnAttachStateChangeListener(OnAttachStateChangeListener onAttachStateChangeListener) {
            this.mOnAttachStateChangeListener = onAttachStateChangeListener;
        }
    }

    static {
        boolean z = VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 19;
        USE_OFFSET_API = z;
    }

    protected BaseTransientBottomBar(@NonNull ViewGroup viewGroup, @NonNull View view, @NonNull ContentViewCallback contentViewCallback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        } else if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        } else if (contentViewCallback == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        } else {
            this.mTargetParent = viewGroup;
            this.mContentViewCallback = contentViewCallback;
            this.mContext = viewGroup.getContext();
            ThemeUtils.checkAppCompatTheme(this.mContext);
            this.mView = (SnackbarBaseLayout) LayoutInflater.from(this.mContext).inflate(R.layout.design_layout_snackbar, this.mTargetParent, false);
            this.mView.addView(view);
            ViewCompat.setAccessibilityLiveRegion(this.mView, 1);
            ViewCompat.setImportantForAccessibility(this.mView, 1);
            ViewCompat.setFitsSystemWindows(this.mView, true);
            ViewCompat.setOnApplyWindowInsetsListener(this.mView, new OnApplyWindowInsetsListener() {
                public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), windowInsetsCompat.getSystemWindowInsetBottom());
                    return windowInsetsCompat;
                }
            });
            this.mAccessibilityManager = (AccessibilityManager) this.mContext.getSystemService("accessibility");
        }
    }

    @NonNull
    public B setDuration(int i) {
        this.mDuration = i;
        return this;
    }

    public int getDuration() {
        return this.mDuration;
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    @NonNull
    public View getView() {
        return this.mView;
    }

    public void show() {
        SnackbarManager.getInstance().show(this.mDuration, this.mManagerCallback);
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    /* Access modifiers changed, original: 0000 */
    public void dispatchDismiss(int i) {
        SnackbarManager.getInstance().dismiss(this.mManagerCallback, i);
    }

    @NonNull
    public B addCallback(@NonNull BaseCallback<B> baseCallback) {
        if (baseCallback == null) {
            return this;
        }
        if (this.mCallbacks == null) {
            this.mCallbacks = new ArrayList();
        }
        this.mCallbacks.add(baseCallback);
        return this;
    }

    @NonNull
    public B removeCallback(@NonNull BaseCallback<B> baseCallback) {
        if (baseCallback == null || this.mCallbacks == null) {
            return this;
        }
        this.mCallbacks.remove(baseCallback);
        return this;
    }

    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.mManagerCallback);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.mManagerCallback);
    }

    /* Access modifiers changed, original: final */
    public final void showView() {
        if (this.mView.getParent() == null) {
            LayoutParams layoutParams = this.mView.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
                Behavior behavior = new Behavior();
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(0);
                behavior.setListener(new OnDismissListener() {
                    public void onDismiss(View view) {
                        view.setVisibility(8);
                        BaseTransientBottomBar.this.dispatchDismiss(0);
                    }

                    public void onDragStateChanged(int i) {
                        switch (i) {
                            case 0:
                                SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.mManagerCallback);
                                return;
                            case 1:
                            case 2:
                                SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.mManagerCallback);
                                return;
                            default:
                                return;
                        }
                    }
                });
                layoutParams2.setBehavior(behavior);
                layoutParams2.insetEdge = 80;
            }
            this.mTargetParent.addView(this.mView);
        }
        this.mView.setOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
            }

            public void onViewDetachedFromWindow(View view) {
                if (BaseTransientBottomBar.this.isShownOrQueued()) {
                    BaseTransientBottomBar.sHandler.post(new Runnable() {
                        public void run() {
                            BaseTransientBottomBar.this.onViewHidden(3);
                        }
                    });
                }
            }
        });
        if (!ViewCompat.isLaidOut(this.mView)) {
            this.mView.setOnLayoutChangeListener(new OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4) {
                    BaseTransientBottomBar.this.mView.setOnLayoutChangeListener(null);
                    if (BaseTransientBottomBar.this.shouldAnimate()) {
                        BaseTransientBottomBar.this.animateViewIn();
                    } else {
                        BaseTransientBottomBar.this.onViewShown();
                    }
                }
            });
        } else if (shouldAnimate()) {
            animateViewIn();
        } else {
            onViewShown();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void animateViewIn() {
        if (VERSION.SDK_INT >= 12) {
            final int height = this.mView.getHeight();
            if (USE_OFFSET_API) {
                ViewCompat.offsetTopAndBottom(this.mView, height);
            } else {
                this.mView.setTranslationY((float) height);
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(new int[]{height, 0});
            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            valueAnimator.setDuration(250);
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    BaseTransientBottomBar.this.mContentViewCallback.animateContentIn(70, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
                }

                public void onAnimationEnd(Animator animator) {
                    BaseTransientBottomBar.this.onViewShown();
                }
            });
            valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
                private int mPreviousAnimatedIntValue = height;

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    if (BaseTransientBottomBar.USE_OFFSET_API) {
                        ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.mView, intValue - this.mPreviousAnimatedIntValue);
                    } else {
                        BaseTransientBottomBar.this.mView.setTranslationY((float) intValue);
                    }
                    this.mPreviousAnimatedIntValue = intValue;
                }
            });
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_snackbar_in);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                BaseTransientBottomBar.this.onViewShown();
            }
        });
        this.mView.startAnimation(loadAnimation);
    }

    private void animateViewOut(final int i) {
        if (VERSION.SDK_INT >= 12) {
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(new int[]{0, this.mView.getHeight()});
            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            valueAnimator.setDuration(250);
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    BaseTransientBottomBar.this.mContentViewCallback.animateContentOut(0, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
                }

                public void onAnimationEnd(Animator animator) {
                    BaseTransientBottomBar.this.onViewHidden(i);
                }
            });
            valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
                private int mPreviousAnimatedIntValue = 0;

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    if (BaseTransientBottomBar.USE_OFFSET_API) {
                        ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.mView, intValue - this.mPreviousAnimatedIntValue);
                    } else {
                        BaseTransientBottomBar.this.mView.setTranslationY((float) intValue);
                    }
                    this.mPreviousAnimatedIntValue = intValue;
                }
            });
            valueAnimator.start();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_snackbar_out);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(250);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                BaseTransientBottomBar.this.onViewHidden(i);
            }
        });
        this.mView.startAnimation(loadAnimation);
    }

    /* Access modifiers changed, original: final */
    public final void hideView(int i) {
        if (shouldAnimate() && this.mView.getVisibility() == 0) {
            animateViewOut(i);
        } else {
            onViewHidden(i);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onViewShown() {
        SnackbarManager.getInstance().onShown(this.mManagerCallback);
        if (this.mCallbacks != null) {
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                ((BaseCallback) this.mCallbacks.get(size)).onShown(this);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onViewHidden(int i) {
        SnackbarManager.getInstance().onDismissed(this.mManagerCallback);
        if (this.mCallbacks != null) {
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                ((BaseCallback) this.mCallbacks.get(size)).onDismissed(this, i);
            }
        }
        if (VERSION.SDK_INT < 11) {
            this.mView.setVisibility(8);
        }
        ViewParent parent = this.mView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.mView);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean shouldAnimate() {
        return this.mAccessibilityManager.isEnabled() ^ 1;
    }
}
