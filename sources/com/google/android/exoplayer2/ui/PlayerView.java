package com.google.android.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.Player.TextComponent;
import com.google.android.exoplayer2.Player.VideoComponent;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.AspectRatioListener;
import com.google.android.exoplayer2.ui.PlayerControlView.VisibilityListener;
import com.google.android.exoplayer2.ui.spherical.SingleTapListener;
import com.google.android.exoplayer2.ui.spherical.SphericalSurfaceView;
import com.google.android.exoplayer2.ui.spherical.SphericalSurfaceView.SurfaceListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class PlayerView extends FrameLayout {
    public static final int SHOW_BUFFERING_ALWAYS = 2;
    public static final int SHOW_BUFFERING_NEVER = 0;
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;
    private static final int SURFACE_TYPE_MONO360_VIEW = 3;
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private final ImageView artworkView;
    @Nullable
    private final View bufferingView;
    private final ComponentListener componentListener;
    @Nullable
    private final AspectRatioFrameLayout contentFrame;
    private final PlayerControlView controller;
    private boolean controllerAutoShow;
    private boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    @Nullable
    private CharSequence customErrorMessage;
    @Nullable
    private Drawable defaultArtwork;
    @Nullable
    private ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider;
    @Nullable
    private final TextView errorMessageView;
    private boolean keepContentOnPlayerReset;
    private final FrameLayout overlayFrameLayout;
    private Player player;
    private int showBuffering;
    private final View shutterView;
    private final SubtitleView subtitleView;
    @Nullable
    private final View surfaceView;
    private int textureViewRotation;
    private boolean useArtwork;
    private boolean useController;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowBuffering {
    }

    private final class ComponentListener implements OnLayoutChangeListener, EventListener, TextOutput, SingleTapListener, SurfaceListener, VideoListener {
        public void onLoadingChanged(boolean z) {
            Player$EventListener$$CC.onLoadingChanged(this, z);
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player$EventListener$$CC.onPlaybackParametersChanged(this, playbackParameters);
        }

        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            Player$EventListener$$CC.onPlayerError(this, exoPlaybackException);
        }

        public void onRepeatModeChanged(int i) {
            Player$EventListener$$CC.onRepeatModeChanged(this, i);
        }

        public void onSeekProcessed() {
            Player$EventListener$$CC.onSeekProcessed(this);
        }

        public void onShuffleModeEnabledChanged(boolean z) {
            Player$EventListener$$CC.onShuffleModeEnabledChanged(this, z);
        }

        public void onSurfaceSizeChanged(int i, int i2) {
            VideoListener$$CC.onSurfaceSizeChanged(this, i, i2);
        }

        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            Player$EventListener$$CC.onTimelineChanged(this, timeline, obj, i);
        }

        private ComponentListener() {
        }

        public void onCues(List<Cue> list) {
            if (PlayerView.this.subtitleView != null) {
                PlayerView.this.subtitleView.onCues(list);
            }
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            float f2 = (i2 == 0 || i == 0) ? 1.0f : (((float) i) * f) / ((float) i2);
            if (PlayerView.this.surfaceView instanceof TextureView) {
                if (i3 == 90 || i3 == 270) {
                    f2 = 1.0f / f2;
                }
                if (PlayerView.this.textureViewRotation != 0) {
                    PlayerView.this.surfaceView.removeOnLayoutChangeListener(this);
                }
                PlayerView.this.textureViewRotation = i3;
                if (PlayerView.this.textureViewRotation != 0) {
                    PlayerView.this.surfaceView.addOnLayoutChangeListener(this);
                }
                PlayerView.applyTextureViewRotation((TextureView) PlayerView.this.surfaceView, PlayerView.this.textureViewRotation);
            }
            PlayerView.this.onContentAspectRatioChanged(f2, PlayerView.this.contentFrame, PlayerView.this.surfaceView);
        }

        public void onRenderedFirstFrame() {
            if (PlayerView.this.shutterView != null) {
                PlayerView.this.shutterView.setVisibility(4);
            }
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            PlayerView.this.updateForCurrentTrackSelections(false);
        }

        public void onPlayerStateChanged(boolean z, int i) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateErrorMessage();
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            } else {
                PlayerView.this.maybeShowController(false);
            }
        }

        public void onPositionDiscontinuity(int i) {
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            }
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            PlayerView.applyTextureViewRotation((TextureView) view, PlayerView.this.textureViewRotation);
        }

        public void surfaceChanged(@Nullable Surface surface) {
            if (PlayerView.this.player != null) {
                VideoComponent videoComponent = PlayerView.this.player.getVideoComponent();
                if (videoComponent != null) {
                    videoComponent.setVideoSurface(surface);
                }
            }
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return PlayerView.this.toggleControllerVisibility();
        }
    }

    @SuppressLint({"InlinedApi"})
    private boolean isDpadKey(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    public PlayerView(Context context) {
        this(context, null);
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayerView(Context context, AttributeSet attributeSet, int i) {
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        super(context, attributeSet, i);
        if (isInEditMode()) {
            this.contentFrame = null;
            this.shutterView = null;
            this.surfaceView = null;
            this.artworkView = null;
            this.subtitleView = null;
            this.bufferingView = null;
            this.errorMessageView = null;
            this.controller = null;
            this.componentListener = null;
            this.overlayFrameLayout = null;
            ImageView imageView = new ImageView(context2);
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(getResources(), imageView);
            } else {
                configureEditModeLogo(getResources(), imageView);
            }
            addView(imageView);
            return;
        }
        int color;
        boolean z;
        int resourceId;
        boolean z2;
        int i2;
        int i3;
        boolean z3;
        int integer;
        boolean z4;
        boolean z5;
        int i4;
        boolean z6;
        boolean z7;
        int i5 = R.layout.exo_player_view;
        boolean hasValue;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.PlayerView, 0, 0);
            try {
                hasValue = obtainStyledAttributes.hasValue(R.styleable.PlayerView_shutter_background_color);
                color = obtainStyledAttributes.getColor(R.styleable.PlayerView_shutter_background_color, 0);
                i5 = obtainStyledAttributes.getResourceId(R.styleable.PlayerView_player_layout_id, i5);
                z = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_use_artwork, true);
                resourceId = obtainStyledAttributes.getResourceId(R.styleable.PlayerView_default_artwork, 0);
                z2 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_use_controller, true);
                i2 = obtainStyledAttributes.getInt(R.styleable.PlayerView_surface_type, 1);
                int i6 = obtainStyledAttributes.getInt(R.styleable.PlayerView_resize_mode, 0);
                i3 = obtainStyledAttributes.getInt(R.styleable.PlayerView_show_timeout, 5000);
                z3 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_hide_on_touch, true);
                int i7 = i5;
                boolean z8 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_auto_show, true);
                integer = obtainStyledAttributes.getInteger(R.styleable.PlayerView_show_buffering, 0);
                int i8 = i6;
                this.keepContentOnPlayerReset = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_keep_content_on_player_reset, this.keepContentOnPlayerReset);
                z4 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_hide_during_ads, true);
                obtainStyledAttributes.recycle();
                z5 = z4;
                i4 = i3;
                z6 = z3;
                i5 = i7;
                i3 = i8;
                z7 = z2;
                z2 = z8;
            } catch (Throwable th) {
                Throwable th2 = th;
                obtainStyledAttributes.recycle();
            }
        } else {
            z5 = true;
            z6 = z5;
            z = z6;
            z2 = z;
            i2 = z2;
            z7 = i2;
            i4 = 5000;
            i3 = 0;
            integer = 0;
            hasValue = false;
            color = 0;
            resourceId = 0;
        }
        LayoutInflater.from(context).inflate(i5, this);
        this.componentListener = new ComponentListener();
        setDescendantFocusability(262144);
        this.contentFrame = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        if (this.contentFrame != null) {
            setResizeModeRaw(this.contentFrame, i3);
        }
        this.shutterView = findViewById(R.id.exo_shutter);
        if (this.shutterView != null && hasValue) {
            this.shutterView.setBackgroundColor(color);
        }
        if (this.contentFrame == null || i2 == 0) {
            this.surfaceView = null;
        } else {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            switch (i2) {
                case 2:
                    this.surfaceView = new TextureView(context2);
                    break;
                case 3:
                    Assertions.checkState(Util.SDK_INT >= 15);
                    SphericalSurfaceView sphericalSurfaceView = new SphericalSurfaceView(context2);
                    sphericalSurfaceView.setSurfaceListener(this.componentListener);
                    sphericalSurfaceView.setSingleTapListener(this.componentListener);
                    this.surfaceView = sphericalSurfaceView;
                    break;
                default:
                    this.surfaceView = new SurfaceView(context2);
                    break;
            }
            this.surfaceView.setLayoutParams(layoutParams);
            this.contentFrame.addView(this.surfaceView, 0);
        }
        this.overlayFrameLayout = (FrameLayout) findViewById(R.id.exo_overlay);
        this.artworkView = (ImageView) findViewById(R.id.exo_artwork);
        z4 = z && this.artworkView != null;
        this.useArtwork = z4;
        if (resourceId != 0) {
            this.defaultArtwork = ContextCompat.getDrawable(getContext(), resourceId);
        }
        this.subtitleView = (SubtitleView) findViewById(R.id.exo_subtitles);
        if (this.subtitleView != null) {
            this.subtitleView.setUserDefaultStyle();
            this.subtitleView.setUserDefaultTextSize();
        }
        this.bufferingView = findViewById(R.id.exo_buffering);
        if (this.bufferingView != null) {
            this.bufferingView.setVisibility(8);
        }
        this.showBuffering = integer;
        this.errorMessageView = (TextView) findViewById(R.id.exo_error_message);
        if (this.errorMessageView != null) {
            this.errorMessageView.setVisibility(8);
        }
        PlayerControlView playerControlView = (PlayerControlView) findViewById(R.id.exo_controller);
        View findViewById = findViewById(R.id.exo_controller_placeholder);
        if (playerControlView != null) {
            this.controller = playerControlView;
            z3 = false;
        } else if (findViewById != null) {
            z3 = false;
            this.controller = new PlayerControlView(context2, null, 0, attributeSet2);
            this.controller.setLayoutParams(findViewById.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById);
            viewGroup.removeView(findViewById);
            viewGroup.addView(this.controller, indexOfChild);
        } else {
            z3 = false;
            this.controller = null;
        }
        this.controllerShowTimeoutMs = this.controller != null ? i4 : z3;
        this.controllerHideOnTouch = z6;
        this.controllerAutoShow = z2;
        this.controllerHideDuringAds = z5;
        if (z7 && this.controller != null) {
            z3 = true;
        }
        this.useController = z3;
        hideController();
    }

    public static void switchTargetView(Player player, @Nullable PlayerView playerView, @Nullable PlayerView playerView2) {
        if (playerView != playerView2) {
            if (playerView2 != null) {
                playerView2.setPlayer(player);
            }
            if (playerView != null) {
                playerView.setPlayer(null);
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(@Nullable Player player) {
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        boolean z = player == null || player.getApplicationLooper() == Looper.getMainLooper();
        Assertions.checkArgument(z);
        if (this.player != player) {
            VideoComponent videoComponent;
            TextComponent textComponent;
            if (this.player != null) {
                this.player.removeListener(this.componentListener);
                videoComponent = this.player.getVideoComponent();
                if (videoComponent != null) {
                    videoComponent.removeVideoListener(this.componentListener);
                    if (this.surfaceView instanceof TextureView) {
                        videoComponent.clearVideoTextureView((TextureView) this.surfaceView);
                    } else if (this.surfaceView instanceof SphericalSurfaceView) {
                        ((SphericalSurfaceView) this.surfaceView).setVideoComponent(null);
                    } else if (this.surfaceView instanceof SurfaceView) {
                        videoComponent.clearVideoSurfaceView((SurfaceView) this.surfaceView);
                    }
                }
                textComponent = this.player.getTextComponent();
                if (textComponent != null) {
                    textComponent.removeTextOutput(this.componentListener);
                }
            }
            this.player = player;
            if (this.useController) {
                this.controller.setPlayer(player);
            }
            if (this.subtitleView != null) {
                this.subtitleView.setCues(null);
            }
            updateBuffering();
            updateErrorMessage();
            updateForCurrentTrackSelections(true);
            if (player != null) {
                videoComponent = player.getVideoComponent();
                if (videoComponent != null) {
                    if (this.surfaceView instanceof TextureView) {
                        videoComponent.setVideoTextureView((TextureView) this.surfaceView);
                    } else if (this.surfaceView instanceof SphericalSurfaceView) {
                        ((SphericalSurfaceView) this.surfaceView).setVideoComponent(videoComponent);
                    } else if (this.surfaceView instanceof SurfaceView) {
                        videoComponent.setVideoSurfaceView((SurfaceView) this.surfaceView);
                    }
                    videoComponent.addVideoListener(this.componentListener);
                }
                textComponent = player.getTextComponent();
                if (textComponent != null) {
                    textComponent.addTextOutput(this.componentListener);
                }
                player.addListener(this.componentListener);
                maybeShowController(false);
            } else {
                hideController();
            }
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (this.surfaceView instanceof SurfaceView) {
            this.surfaceView.setVisibility(i);
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkState(this.contentFrame != null);
        this.contentFrame.setResizeMode(i);
    }

    public int getResizeMode() {
        Assertions.checkState(this.contentFrame != null);
        return this.contentFrame.getResizeMode();
    }

    public boolean getUseArtwork() {
        return this.useArtwork;
    }

    public void setUseArtwork(boolean z) {
        boolean z2 = (z && this.artworkView == null) ? false : true;
        Assertions.checkState(z2);
        if (this.useArtwork != z) {
            this.useArtwork = z;
            updateForCurrentTrackSelections(false);
        }
    }

    @Nullable
    public Drawable getDefaultArtwork() {
        return this.defaultArtwork;
    }

    @Deprecated
    public void setDefaultArtwork(@Nullable Bitmap bitmap) {
        Drawable drawable;
        if (bitmap == null) {
            drawable = null;
        } else {
            drawable = new BitmapDrawable(getResources(), bitmap);
        }
        setDefaultArtwork(drawable);
    }

    public void setDefaultArtwork(@Nullable Drawable drawable) {
        if (this.defaultArtwork != drawable) {
            this.defaultArtwork = drawable;
            updateForCurrentTrackSelections(false);
        }
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setUseController(boolean z) {
        boolean z2 = (z && this.controller == null) ? false : true;
        Assertions.checkState(z2);
        if (this.useController != z) {
            this.useController = z;
            if (z) {
                this.controller.setPlayer(this.player);
            } else if (this.controller != null) {
                this.controller.hide();
                this.controller.setPlayer(null);
            }
        }
    }

    public void setShutterBackgroundColor(int i) {
        if (this.shutterView != null) {
            this.shutterView.setBackgroundColor(i);
        }
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.keepContentOnPlayerReset != z) {
            this.keepContentOnPlayerReset = z;
            updateForCurrentTrackSelections(false);
        }
    }

    @Deprecated
    public void setShowBuffering(boolean z) {
        setShowBuffering((int) z);
    }

    public void setShowBuffering(int i) {
        if (this.showBuffering != i) {
            this.showBuffering = i;
            updateBuffering();
        }
    }

    public void setErrorMessageProvider(@Nullable ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider) {
        if (this.errorMessageProvider != errorMessageProvider) {
            this.errorMessageProvider = errorMessageProvider;
            updateErrorMessage();
        }
    }

    public void setCustomErrorMessage(@Nullable CharSequence charSequence) {
        Assertions.checkState(this.errorMessageView != null);
        this.customErrorMessage = charSequence;
        updateErrorMessage();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.player == null || !this.player.isPlayingAd()) {
            boolean z = false;
            boolean z2 = isDpadKey(keyEvent.getKeyCode()) && this.useController && !this.controller.isVisible();
            if (z2 || dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
                z = true;
            }
            if (z) {
                maybeShowController(true);
            }
            return z;
        }
        this.overlayFrameLayout.requestFocus();
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        return this.useController && this.controller.dispatchMediaKeyEvent(keyEvent);
    }

    public boolean isControllerVisible() {
        return this.controller != null && this.controller.isVisible();
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public void hideController() {
        if (this.controller != null) {
            this.controller.hide();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.checkState(this.controller != null);
        this.controllerShowTimeoutMs = i;
        if (this.controller.isVisible()) {
            showController();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public void setControllerHideOnTouch(boolean z) {
        Assertions.checkState(this.controller != null);
        this.controllerHideOnTouch = z;
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public void setControllerAutoShow(boolean z) {
        this.controllerAutoShow = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.controllerHideDuringAds = z;
    }

    public void setControllerVisibilityListener(VisibilityListener visibilityListener) {
        Assertions.checkState(this.controller != null);
        this.controller.setVisibilityListener(visibilityListener);
    }

    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
        Assertions.checkState(this.controller != null);
        this.controller.setPlaybackPreparer(playbackPreparer);
    }

    public void setControlDispatcher(@Nullable ControlDispatcher controlDispatcher) {
        Assertions.checkState(this.controller != null);
        this.controller.setControlDispatcher(controlDispatcher);
    }

    public void setRewindIncrementMs(int i) {
        Assertions.checkState(this.controller != null);
        this.controller.setRewindIncrementMs(i);
    }

    public void setFastForwardIncrementMs(int i) {
        Assertions.checkState(this.controller != null);
        this.controller.setFastForwardIncrementMs(i);
    }

    public void setRepeatToggleModes(int i) {
        Assertions.checkState(this.controller != null);
        this.controller.setRepeatToggleModes(i);
    }

    public void setShowShuffleButton(boolean z) {
        Assertions.checkState(this.controller != null);
        this.controller.setShowShuffleButton(z);
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        Assertions.checkState(this.controller != null);
        this.controller.setShowMultiWindowTimeBar(z);
    }

    public void setExtraAdGroupMarkers(@Nullable long[] jArr, @Nullable boolean[] zArr) {
        Assertions.checkState(this.controller != null);
        this.controller.setExtraAdGroupMarkers(jArr, zArr);
    }

    public void setAspectRatioListener(AspectRatioListener aspectRatioListener) {
        Assertions.checkState(this.contentFrame != null);
        this.contentFrame.setAspectRatioListener(aspectRatioListener);
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0) {
            return false;
        }
        return toggleControllerVisibility();
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!this.useController || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public void onResume() {
        if (this.surfaceView instanceof SphericalSurfaceView) {
            ((SphericalSurfaceView) this.surfaceView).onResume();
        }
    }

    public void onPause() {
        if (this.surfaceView instanceof SphericalSurfaceView) {
            ((SphericalSurfaceView) this.surfaceView).onPause();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onContentAspectRatioChanged(float f, @Nullable AspectRatioFrameLayout aspectRatioFrameLayout, @Nullable View view) {
        if (aspectRatioFrameLayout != null) {
            if (view instanceof SphericalSurfaceView) {
                f = 0.0f;
            }
            aspectRatioFrameLayout.setAspectRatio(f);
        }
    }

    private boolean toggleControllerVisibility() {
        if (!this.useController || this.player == null) {
            return false;
        }
        if (!this.controller.isVisible()) {
            maybeShowController(true);
        } else if (this.controllerHideOnTouch) {
            this.controller.hide();
        }
        return true;
    }

    private void maybeShowController(boolean z) {
        if (!(isPlayingAd() && this.controllerHideDuringAds) && this.useController) {
            Object obj = (!this.controller.isVisible() || this.controller.getShowTimeoutMs() > 0) ? null : 1;
            boolean shouldShowControllerIndefinitely = shouldShowControllerIndefinitely();
            if (z || obj != null || shouldShowControllerIndefinitely) {
                showController(shouldShowControllerIndefinitely);
            }
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        boolean z = true;
        if (this.player == null) {
            return true;
        }
        int playbackState = this.player.getPlaybackState();
        if (!(this.controllerAutoShow && (playbackState == 1 || playbackState == 4 || !this.player.getPlayWhenReady()))) {
            z = false;
        }
        return z;
    }

    private void showController(boolean z) {
        if (this.useController) {
            this.controller.setShowTimeoutMs(z ? 0 : this.controllerShowTimeoutMs);
            this.controller.show();
        }
    }

    private boolean isPlayingAd() {
        return this.player != null && this.player.isPlayingAd() && this.player.getPlayWhenReady();
    }

    private void updateForCurrentTrackSelections(boolean z) {
        if (this.player == null || this.player.getCurrentTrackGroups().isEmpty()) {
            if (!this.keepContentOnPlayerReset) {
                hideArtwork();
                closeShutter();
            }
            return;
        }
        if (z && !this.keepContentOnPlayerReset) {
            closeShutter();
        }
        TrackSelectionArray currentTrackSelections = this.player.getCurrentTrackSelections();
        int i = 0;
        while (i < currentTrackSelections.length) {
            if (this.player.getRendererType(i) != 2 || currentTrackSelections.get(i) == null) {
                i++;
            } else {
                hideArtwork();
                return;
            }
        }
        closeShutter();
        if (this.useArtwork) {
            for (i = 0; i < currentTrackSelections.length; i++) {
                TrackSelection trackSelection = currentTrackSelections.get(i);
                if (trackSelection != null) {
                    int i2 = 0;
                    while (i2 < trackSelection.length()) {
                        Metadata metadata = trackSelection.getFormat(i2).metadata;
                        if (metadata == null || !setArtworkFromMetadata(metadata)) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    continue;
                }
            }
            if (setDrawableArtwork(this.defaultArtwork)) {
                return;
            }
        }
        hideArtwork();
    }

    private boolean setArtworkFromMetadata(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Entry entry = metadata.get(i);
            if (entry instanceof ApicFrame) {
                byte[] bArr = ((ApicFrame) entry).pictureData;
                return setDrawableArtwork(new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length)));
            }
        }
        return false;
    }

    private boolean setDrawableArtwork(@Nullable Drawable drawable) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                onContentAspectRatioChanged(((float) intrinsicWidth) / ((float) intrinsicHeight), this.contentFrame, this.artworkView);
                this.artworkView.setImageDrawable(drawable);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void hideArtwork() {
        if (this.artworkView != null) {
            this.artworkView.setImageResource(17170445);
            this.artworkView.setVisibility(4);
        }
    }

    private void closeShutter() {
        if (this.shutterView != null) {
            this.shutterView.setVisibility(0);
        }
    }

    private void updateBuffering() {
        if (this.bufferingView != null) {
            int i = 0;
            int i2 = 1;
            if (!(this.player != null && this.player.getPlaybackState() == 2 && (this.showBuffering == 2 || (this.showBuffering == 1 && this.player.getPlayWhenReady())))) {
                i2 = 0;
            }
            View view = this.bufferingView;
            if (i2 == 0) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    private void updateErrorMessage() {
        if (this.errorMessageView != null) {
            if (this.customErrorMessage != null) {
                this.errorMessageView.setText(this.customErrorMessage);
                this.errorMessageView.setVisibility(0);
                return;
            }
            Throwable th = null;
            if (!(this.player == null || this.player.getPlaybackState() != 1 || this.errorMessageProvider == null)) {
                th = this.player.getPlaybackError();
            }
            if (th != null) {
                this.errorMessageView.setText((CharSequence) this.errorMessageProvider.getErrorMessage(th).second);
                this.errorMessageView.setVisibility(0);
            } else {
                this.errorMessageView.setVisibility(8);
            }
        }
    }

    @TargetApi(23)
    private static void configureEditModeLogoV23(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, null));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
    }

    private static void configureEditModeLogo(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    private static void applyTextureViewRotation(TextureView textureView, int i) {
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        if (width == 0.0f || height == 0.0f || i == 0) {
            textureView.setTransform(null);
            return;
        }
        Matrix matrix = new Matrix();
        float f = width / 2.0f;
        float f2 = height / 2.0f;
        matrix.postRotate((float) i, f, f2);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        RectF rectF2 = new RectF();
        matrix.mapRect(rectF2, rectF);
        matrix.postScale(width / rectF2.width(), height / rectF2.height(), f, f2);
        textureView.setTransform(matrix);
    }
}
