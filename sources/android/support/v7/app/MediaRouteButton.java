package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.ProviderInfo;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.support.v7.mediarouter.R;
import android.support.v7.widget.TooltipCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;

public class MediaRouteButton extends View {
    private static final int[] CHECKABLE_STATE_SET = new int[]{16842911};
    private static final int[] CHECKED_STATE_SET = new int[]{16842912};
    private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
    private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
    private static final String TAG = "MediaRouteButton";
    private static final SparseArray<ConstantState> sRemoteIndicatorCache = new SparseArray(2);
    private boolean mAttachedToWindow;
    private ColorStateList mButtonTint;
    private final MediaRouterCallback mCallback;
    private MediaRouteDialogFactory mDialogFactory;
    private boolean mIsConnecting;
    private int mMinHeight;
    private int mMinWidth;
    private boolean mRemoteActive;
    private Drawable mRemoteIndicator;
    private RemoteIndicatorLoader mRemoteIndicatorLoader;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;

    private final class MediaRouterCallback extends Callback {
        MediaRouterCallback() {
        }

        public void onRouteAdded(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        public void onRouteRemoved(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        public void onRouteChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        public void onProviderAdded(MediaRouter mediaRouter, ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        public void onProviderRemoved(MediaRouter mediaRouter, ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }

        public void onProviderChanged(MediaRouter mediaRouter, ProviderInfo providerInfo) {
            MediaRouteButton.this.refreshRoute();
        }
    }

    private final class RemoteIndicatorLoader extends AsyncTask<Void, Void, Drawable> {
        private final int mResId;

        RemoteIndicatorLoader(int i) {
            this.mResId = i;
        }

        /* Access modifiers changed, original: protected|varargs */
        public Drawable doInBackground(Void... voidArr) {
            return MediaRouteButton.this.getContext().getResources().getDrawable(this.mResId);
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Drawable drawable) {
            cacheAndReset(drawable);
            MediaRouteButton.this.setRemoteIndicatorDrawable(drawable);
        }

        /* Access modifiers changed, original: protected */
        public void onCancelled(Drawable drawable) {
            cacheAndReset(drawable);
        }

        private void cacheAndReset(Drawable drawable) {
            if (drawable != null) {
                MediaRouteButton.sRemoteIndicatorCache.put(this.mResId, drawable.getConstantState());
            }
            MediaRouteButton.this.mRemoteIndicatorLoader = null;
        }
    }

    public MediaRouteButton(Context context) {
        this(context, null);
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(Context context, AttributeSet attributeSet, int i) {
        super(MediaRouterThemeHelper.createThemedButtonContext(context), attributeSet, i);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mDialogFactory = MediaRouteDialogFactory.getDefault();
        context = getContext();
        this.mRouter = MediaRouter.getInstance(context);
        this.mCallback = new MediaRouterCallback();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MediaRouteButton, i, 0);
        this.mButtonTint = obtainStyledAttributes.getColorStateList(R.styleable.MediaRouteButton_mediaRouteButtonTint);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MediaRouteButton_android_minWidth, 0);
        this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MediaRouteButton_android_minHeight, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        obtainStyledAttributes.recycle();
        if (resourceId != 0) {
            ConstantState constantState = (ConstantState) sRemoteIndicatorCache.get(resourceId);
            if (constantState != null) {
                setRemoteIndicatorDrawable(constantState.newDrawable());
            } else {
                this.mRemoteIndicatorLoader = new RemoteIndicatorLoader(resourceId);
                this.mRemoteIndicatorLoader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }
        updateContentDescription();
        setClickable(true);
    }

    @NonNull
    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    public void setRouteSelector(MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (!this.mSelector.equals(mediaRouteSelector)) {
            if (this.mAttachedToWindow) {
                if (!this.mSelector.isEmpty()) {
                    this.mRouter.removeCallback(this.mCallback);
                }
                if (!mediaRouteSelector.isEmpty()) {
                    this.mRouter.addCallback(mediaRouteSelector, this.mCallback);
                }
            }
            this.mSelector = mediaRouteSelector;
            refreshRoute();
        }
    }

    @NonNull
    public MediaRouteDialogFactory getDialogFactory() {
        return this.mDialogFactory;
    }

    public void setDialogFactory(@NonNull MediaRouteDialogFactory mediaRouteDialogFactory) {
        if (mediaRouteDialogFactory == null) {
            throw new IllegalArgumentException("factory must not be null");
        }
        this.mDialogFactory = mediaRouteDialogFactory;
    }

    public boolean showDialog() {
        if (!this.mAttachedToWindow) {
            return false;
        }
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
        }
        RouteInfo selectedRoute = this.mRouter.getSelectedRoute();
        if (selectedRoute.isDefaultOrBluetooth() || !selectedRoute.matchesSelector(this.mSelector)) {
            if (fragmentManager.findFragmentByTag(CHOOSER_FRAGMENT_TAG) != null) {
                Log.w(TAG, "showDialog(): Route chooser dialog already showing!");
                return false;
            }
            MediaRouteChooserDialogFragment onCreateChooserDialogFragment = this.mDialogFactory.onCreateChooserDialogFragment();
            onCreateChooserDialogFragment.setRouteSelector(this.mSelector);
            onCreateChooserDialogFragment.show(fragmentManager, CHOOSER_FRAGMENT_TAG);
        } else if (fragmentManager.findFragmentByTag(CONTROLLER_FRAGMENT_TAG) != null) {
            Log.w(TAG, "showDialog(): Route controller dialog already showing!");
            return false;
        } else {
            this.mDialogFactory.onCreateControllerDialogFragment().show(fragmentManager, CONTROLLER_FRAGMENT_TAG);
        }
        return true;
    }

    private FragmentManager getFragmentManager() {
        Activity activity = getActivity();
        return activity instanceof FragmentActivity ? ((FragmentActivity) activity).getSupportFragmentManager() : null;
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public void setCheatSheetEnabled(boolean z) {
        TooltipCompat.setTooltipText(this, z ? getContext().getString(R.string.mr_button_content_description) : null);
    }

    public boolean performClick() {
        boolean performClick = super.performClick();
        if (!performClick) {
            playSoundEffect(0);
        }
        if (showDialog() || performClick) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mIsConnecting) {
            mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        } else if (this.mRemoteActive) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    /* Access modifiers changed, original: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mRemoteIndicator != null) {
            this.mRemoteIndicator.setState(getDrawableState());
            invalidate();
        }
    }

    public void setRemoteIndicatorDrawable(Drawable drawable) {
        if (this.mRemoteIndicatorLoader != null) {
            this.mRemoteIndicatorLoader.cancel(false);
        }
        if (this.mRemoteIndicator != null) {
            this.mRemoteIndicator.setCallback(null);
            unscheduleDrawable(this.mRemoteIndicator);
        }
        if (drawable != null) {
            if (this.mButtonTint != null) {
                drawable = DrawableCompat.wrap(drawable.mutate());
                DrawableCompat.setTintList(drawable, this.mButtonTint);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            drawable.setVisible(getVisibility() == 0, false);
        }
        this.mRemoteIndicator = drawable;
        refreshDrawableState();
        if (this.mAttachedToWindow && this.mRemoteIndicator != null && (this.mRemoteIndicator.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.mRemoteIndicator.getCurrent();
            if (this.mIsConnecting) {
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else if (this.mRemoteActive) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mRemoteIndicator;
    }

    public void jumpDrawablesToCurrentState() {
        if (getBackground() != null) {
            DrawableCompat.jumpToCurrentState(getBackground());
        }
        if (this.mRemoteIndicator != null) {
            DrawableCompat.jumpToCurrentState(this.mRemoteIndicator);
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (this.mRemoteIndicator != null) {
            this.mRemoteIndicator.setVisible(getVisibility() == 0, false);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        if (!this.mSelector.isEmpty()) {
            this.mRouter.addCallback(this.mSelector, this.mCallback);
        }
        refreshRoute();
    }

    public void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        if (!this.mSelector.isEmpty()) {
            this.mRouter.removeCallback(this.mCallback);
        }
        super.onDetachedFromWindow();
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        i = MeasureSpec.getMode(i);
        i2 = MeasureSpec.getMode(i2);
        int i3 = 0;
        int max = Math.max(this.mMinWidth, this.mRemoteIndicator != null ? (this.mRemoteIndicator.getIntrinsicWidth() + getPaddingLeft()) + getPaddingRight() : 0);
        int i4 = this.mMinHeight;
        if (this.mRemoteIndicator != null) {
            i3 = (this.mRemoteIndicator.getIntrinsicHeight() + getPaddingTop()) + getPaddingBottom();
        }
        i4 = Math.max(i4, i3);
        if (i == Integer.MIN_VALUE) {
            size = Math.min(size, max);
        } else if (i != 1073741824) {
            size = max;
        }
        if (i2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, i4);
        } else if (i2 != 1073741824) {
            size2 = i4;
        }
        setMeasuredDimension(size, size2);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRemoteIndicator != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int intrinsicWidth = this.mRemoteIndicator.getIntrinsicWidth();
            int intrinsicHeight = this.mRemoteIndicator.getIntrinsicHeight();
            paddingLeft += ((width - paddingLeft) - intrinsicWidth) / 2;
            paddingTop += ((height - paddingTop) - intrinsicHeight) / 2;
            this.mRemoteIndicator.setBounds(paddingLeft, paddingTop, intrinsicWidth + paddingLeft, intrinsicHeight + paddingTop);
            this.mRemoteIndicator.draw(canvas);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void refreshRoute() {
        RouteInfo selectedRoute = this.mRouter.getSelectedRoute();
        int i = 0;
        boolean z = !selectedRoute.isDefaultOrBluetooth() && selectedRoute.matchesSelector(this.mSelector);
        boolean z2 = z && selectedRoute.isConnecting();
        if (this.mRemoteActive != z) {
            this.mRemoteActive = z;
            i = 1;
        }
        if (this.mIsConnecting != z2) {
            this.mIsConnecting = z2;
            i = 1;
        }
        if (i != 0) {
            updateContentDescription();
            refreshDrawableState();
        }
        if (this.mAttachedToWindow) {
            setEnabled(this.mRouter.isRouteAvailable(this.mSelector, 1));
        }
        if (this.mRemoteIndicator != null && (this.mRemoteIndicator.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.mRemoteIndicator.getCurrent();
            if (this.mAttachedToWindow) {
                if ((i != 0 || z2) && !animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else if (z && !z2) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    private void updateContentDescription() {
        int i;
        if (this.mIsConnecting) {
            i = R.string.mr_cast_button_connecting;
        } else if (this.mRemoteActive) {
            i = R.string.mr_cast_button_connected;
        } else {
            i = R.string.mr_cast_button_disconnected;
        }
        setContentDescription(getContext().getString(i));
    }
}
