package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Interpolator;

@RequiresApi(14)
class FloatingActionButtonImpl {
    static final Interpolator ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_SHOWING = 2;
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] ENABLED_STATE_SET = new int[]{16842910};
    static final int[] FOCUSED_ENABLED_STATE_SET = new int[]{16842908, 16842910};
    static final long PRESSED_ANIM_DELAY = 100;
    static final long PRESSED_ANIM_DURATION = 100;
    static final int[] PRESSED_ENABLED_STATE_SET = new int[]{16842919, 16842910};
    static final int SHOW_HIDE_ANIM_DURATION = 200;
    int mAnimState = 0;
    CircularBorderDrawable mBorderDrawable;
    Drawable mContentBackground;
    float mElevation;
    private OnPreDrawListener mPreDrawListener;
    float mPressedTranslationZ;
    Drawable mRippleDrawable;
    private float mRotation;
    ShadowDrawableWrapper mShadowDrawable;
    final ShadowViewDelegate mShadowViewDelegate;
    Drawable mShapeDrawable;
    private final StateListAnimator mStateListAnimator;
    private final Rect mTmpRect = new Rect();
    final VisibilityAwareImageButton mView;

    interface InternalVisibilityChangedListener {
        void onHidden();

        void onShown();
    }

    private abstract class ShadowAnimatorImpl extends AnimatorListenerAdapter implements AnimatorUpdateListener {
        private float mShadowSizeEnd;
        private float mShadowSizeStart;
        private boolean mValidValues;

        public abstract float getTargetShadowSize();

        private ShadowAnimatorImpl() {
        }

        /* synthetic */ ShadowAnimatorImpl(FloatingActionButtonImpl floatingActionButtonImpl, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.mValidValues) {
                this.mShadowSizeStart = FloatingActionButtonImpl.this.mShadowDrawable.getShadowSize();
                this.mShadowSizeEnd = getTargetShadowSize();
                this.mValidValues = true;
            }
            FloatingActionButtonImpl.this.mShadowDrawable.setShadowSize(this.mShadowSizeStart + ((this.mShadowSizeEnd - this.mShadowSizeStart) * valueAnimator.getAnimatedFraction()));
        }

        public void onAnimationEnd(Animator animator) {
            FloatingActionButtonImpl.this.mShadowDrawable.setShadowSize(this.mShadowSizeEnd);
            this.mValidValues = false;
        }
    }

    private class DisabledElevationAnimation extends ShadowAnimatorImpl {
        /* Access modifiers changed, original: protected */
        public float getTargetShadowSize() {
            return 0.0f;
        }

        DisabledElevationAnimation() {
            super(FloatingActionButtonImpl.this, null);
        }
    }

    private class ElevateToTranslationZAnimation extends ShadowAnimatorImpl {
        ElevateToTranslationZAnimation() {
            super(FloatingActionButtonImpl.this, null);
        }

        /* Access modifiers changed, original: protected */
        public float getTargetShadowSize() {
            return FloatingActionButtonImpl.this.mElevation + FloatingActionButtonImpl.this.mPressedTranslationZ;
        }
    }

    private class ResetElevationAnimation extends ShadowAnimatorImpl {
        ResetElevationAnimation() {
            super(FloatingActionButtonImpl.this, null);
        }

        /* Access modifiers changed, original: protected */
        public float getTargetShadowSize() {
            return FloatingActionButtonImpl.this.mElevation;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onCompatShadowChanged() {
    }

    /* Access modifiers changed, original: 0000 */
    public void onPaddingUpdated(Rect rect) {
    }

    /* Access modifiers changed, original: 0000 */
    public boolean requirePreDrawListener() {
        return true;
    }

    FloatingActionButtonImpl(VisibilityAwareImageButton visibilityAwareImageButton, ShadowViewDelegate shadowViewDelegate) {
        this.mView = visibilityAwareImageButton;
        this.mShadowViewDelegate = shadowViewDelegate;
        this.mStateListAnimator = new StateListAnimator();
        this.mStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
        this.mStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, createAnimator(new ElevateToTranslationZAnimation()));
        this.mStateListAnimator.addState(ENABLED_STATE_SET, createAnimator(new ResetElevationAnimation()));
        this.mStateListAnimator.addState(EMPTY_STATE_SET, createAnimator(new DisabledElevationAnimation()));
        this.mRotation = this.mView.getRotation();
    }

    /* Access modifiers changed, original: 0000 */
    public void setBackgroundDrawable(ColorStateList colorStateList, Mode mode, int i, int i2) {
        Drawable[] drawableArr;
        this.mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(this.mShapeDrawable, colorStateList);
        if (mode != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, mode);
        }
        this.mRippleDrawable = DrawableCompat.wrap(createShapeDrawable());
        DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(i));
        if (i2 > 0) {
            this.mBorderDrawable = createBorderDrawable(i2, colorStateList);
            drawableArr = new Drawable[]{this.mBorderDrawable, this.mShapeDrawable, this.mRippleDrawable};
        } else {
            this.mBorderDrawable = null;
            drawableArr = new Drawable[]{this.mShapeDrawable, this.mRippleDrawable};
        }
        this.mContentBackground = new LayerDrawable(drawableArr);
        this.mShadowDrawable = new ShadowDrawableWrapper(this.mView.getContext(), this.mContentBackground, this.mShadowViewDelegate.getRadius(), this.mElevation, this.mElevation + this.mPressedTranslationZ);
        this.mShadowDrawable.setAddPaddingForCorners(false);
        this.mShadowViewDelegate.setBackgroundDrawable(this.mShadowDrawable);
    }

    /* Access modifiers changed, original: 0000 */
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.mShapeDrawable != null) {
            DrawableCompat.setTintList(this.mShapeDrawable, colorStateList);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setBorderTint(colorStateList);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setBackgroundTintMode(Mode mode) {
        if (this.mShapeDrawable != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, mode);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setRippleColor(int i) {
        if (this.mRippleDrawable != null) {
            DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(i));
        }
    }

    /* Access modifiers changed, original: final */
    public final void setElevation(float f) {
        if (this.mElevation != f) {
            this.mElevation = f;
            onElevationsChanged(f, this.mPressedTranslationZ);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public float getElevation() {
        return this.mElevation;
    }

    /* Access modifiers changed, original: final */
    public final void setPressedTranslationZ(float f) {
        if (this.mPressedTranslationZ != f) {
            this.mPressedTranslationZ = f;
            onElevationsChanged(this.mElevation, f);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onElevationsChanged(float f, float f2) {
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setShadowSize(f, this.mPressedTranslationZ + f);
            updatePadding();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onDrawableStateChanged(int[] iArr) {
        this.mStateListAnimator.setState(iArr);
    }

    /* Access modifiers changed, original: 0000 */
    public void jumpDrawableToCurrentState() {
        this.mStateListAnimator.jumpToCurrentState();
    }

    /* Access modifiers changed, original: 0000 */
    public void hide(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (!isOrWillBeHidden()) {
            this.mView.animate().cancel();
            if (shouldAnimateVisibilityChange()) {
                this.mAnimState = 1;
                this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
                    private boolean mCancelled;

                    public void onAnimationStart(Animator animator) {
                        FloatingActionButtonImpl.this.mView.internalSetVisibility(0, z);
                        this.mCancelled = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.mCancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        FloatingActionButtonImpl.this.mAnimState = 0;
                        if (!this.mCancelled) {
                            FloatingActionButtonImpl.this.mView.internalSetVisibility(z ? 8 : 4, z);
                            if (internalVisibilityChangedListener != null) {
                                internalVisibilityChangedListener.onHidden();
                            }
                        }
                    }
                });
            } else {
                this.mView.internalSetVisibility(z ? 8 : 4, z);
                if (internalVisibilityChangedListener != null) {
                    internalVisibilityChangedListener.onHidden();
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void show(@Nullable final InternalVisibilityChangedListener internalVisibilityChangedListener, final boolean z) {
        if (!isOrWillBeShown()) {
            this.mView.animate().cancel();
            if (shouldAnimateVisibilityChange()) {
                this.mAnimState = 2;
                if (this.mView.getVisibility() != 0) {
                    this.mView.setAlpha(0.0f);
                    this.mView.setScaleY(0.0f);
                    this.mView.setScaleX(0.0f);
                }
                this.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        FloatingActionButtonImpl.this.mView.internalSetVisibility(0, z);
                    }

                    public void onAnimationEnd(Animator animator) {
                        FloatingActionButtonImpl.this.mAnimState = 0;
                        if (internalVisibilityChangedListener != null) {
                            internalVisibilityChangedListener.onShown();
                        }
                    }
                });
            } else {
                this.mView.internalSetVisibility(0, z);
                this.mView.setAlpha(1.0f);
                this.mView.setScaleY(1.0f);
                this.mView.setScaleX(1.0f);
                if (internalVisibilityChangedListener != null) {
                    internalVisibilityChangedListener.onShown();
                }
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final Drawable getContentBackground() {
        return this.mContentBackground;
    }

    /* Access modifiers changed, original: final */
    public final void updatePadding() {
        Rect rect = this.mTmpRect;
        getPadding(rect);
        onPaddingUpdated(rect);
        this.mShadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    /* Access modifiers changed, original: 0000 */
    public void getPadding(Rect rect) {
        this.mShadowDrawable.getPadding(rect);
    }

    /* Access modifiers changed, original: 0000 */
    public void onAttachedToWindow() {
        if (requirePreDrawListener()) {
            ensurePreDrawListener();
            this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void onDetachedFromWindow() {
        if (this.mPreDrawListener != null) {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
            this.mPreDrawListener = null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public CircularBorderDrawable createBorderDrawable(int i, ColorStateList colorStateList) {
        Context context = this.mView.getContext();
        CircularBorderDrawable newCircularDrawable = newCircularDrawable();
        newCircularDrawable.setGradientColors(ContextCompat.getColor(context, R.color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, R.color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, R.color.design_fab_stroke_end_outer_color));
        newCircularDrawable.setBorderWidth((float) i);
        newCircularDrawable.setBorderTint(colorStateList);
        return newCircularDrawable;
    }

    /* Access modifiers changed, original: 0000 */
    public CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawable();
    }

    /* Access modifiers changed, original: 0000 */
    public void onPreDraw() {
        float rotation = this.mView.getRotation();
        if (this.mRotation != rotation) {
            this.mRotation = rotation;
            updateFromViewRotation();
        }
    }

    private void ensurePreDrawListener() {
        if (this.mPreDrawListener == null) {
            this.mPreDrawListener = new OnPreDrawListener() {
                public boolean onPreDraw() {
                    FloatingActionButtonImpl.this.onPreDraw();
                    return true;
                }
            };
        }
    }

    /* Access modifiers changed, original: 0000 */
    public GradientDrawable createShapeDrawable() {
        GradientDrawable newGradientDrawableForShape = newGradientDrawableForShape();
        newGradientDrawableForShape.setShape(1);
        newGradientDrawableForShape.setColor(-1);
        return newGradientDrawableForShape;
    }

    /* Access modifiers changed, original: 0000 */
    public GradientDrawable newGradientDrawableForShape() {
        return new GradientDrawable();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isOrWillBeShown() {
        boolean z = false;
        if (this.mView.getVisibility() != 0) {
            if (this.mAnimState == 2) {
                z = true;
            }
            return z;
        }
        if (this.mAnimState != 1) {
            z = true;
        }
        return z;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isOrWillBeHidden() {
        boolean z = false;
        if (this.mView.getVisibility() == 0) {
            if (this.mAnimState == 1) {
                z = true;
            }
            return z;
        }
        if (this.mAnimState != 2) {
            z = true;
        }
        return z;
    }

    private ValueAnimator createAnimator(@NonNull ShadowAnimatorImpl shadowAnimatorImpl) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(ANIM_INTERPOLATOR);
        valueAnimator.setDuration(100);
        valueAnimator.addListener(shadowAnimatorImpl);
        valueAnimator.addUpdateListener(shadowAnimatorImpl);
        valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
        return valueAnimator;
    }

    private static ColorStateList createColorStateList(int i) {
        r1 = new int[3][];
        int[] iArr = new int[]{FOCUSED_ENABLED_STATE_SET, i, PRESSED_ENABLED_STATE_SET};
        iArr[1] = i;
        r1[2] = new int[0];
        iArr[2] = 0;
        return new ColorStateList(r1, iArr);
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this.mView) && !this.mView.isInEditMode();
    }

    private void updateFromViewRotation() {
        if (VERSION.SDK_INT == 19) {
            if (this.mRotation % 90.0f != 0.0f) {
                if (this.mView.getLayerType() != 1) {
                    this.mView.setLayerType(1, null);
                }
            } else if (this.mView.getLayerType() != 0) {
                this.mView.setLayerType(0, null);
            }
        }
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setRotation(-this.mRotation);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setRotation(-this.mRotation);
        }
    }
}
