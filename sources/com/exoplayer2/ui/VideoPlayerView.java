package com.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exoplayer2.ui.VideoPlayerControlView.b;
import com.gaana.R;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player.DefaultEventListener;
import com.google.android.exoplayer2.Player.TextComponent;
import com.google.android.exoplayer2.Player.VideoComponent;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.AspectRatioListener;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import java.util.List;

public class VideoPlayerView extends FrameLayout {
    private final AspectRatioFrameLayout a;
    private final View b;
    private final View c;
    private final ImageView d;
    private final SubtitleView e;
    @Nullable
    private final View f;
    @Nullable
    private final TextView g;
    private final VideoPlayerControlView h;
    private final a i;
    private final FrameLayout j;
    private Player k;
    private boolean l;
    private boolean m;
    private Bitmap n;
    private boolean o;
    private boolean p;
    @Nullable
    private ErrorMessageProvider<? super ExoPlaybackException> q;
    @Nullable
    private CharSequence r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;

    private final class a extends DefaultEventListener implements OnLayoutChangeListener, TextOutput, VideoListener {
        public void onSurfaceSizeChanged(int i, int i2) {
            VideoListener$$CC.onSurfaceSizeChanged(this, i, i2);
        }

        private a() {
        }

        public void onCues(List<Cue> list) {
            if (VideoPlayerView.this.e != null) {
                VideoPlayerView.this.e.onCues(list);
            }
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (VideoPlayerView.this.a != null) {
                float f2 = (i2 == 0 || i == 0) ? 1.0f : (((float) i) * f) / ((float) i2);
                if (VideoPlayerView.this.c instanceof TextureView) {
                    if (i3 == 90 || i3 == 270) {
                        f2 = 1.0f / f2;
                    }
                    if (VideoPlayerView.this.w != 0) {
                        VideoPlayerView.this.c.removeOnLayoutChangeListener(this);
                    }
                    VideoPlayerView.this.w = i3;
                    if (VideoPlayerView.this.w != 0) {
                        VideoPlayerView.this.c.addOnLayoutChangeListener(this);
                    }
                    VideoPlayerView.b((TextureView) VideoPlayerView.this.c, VideoPlayerView.this.w);
                }
                VideoPlayerView.this.a.setAspectRatio(f2);
            }
        }

        public void onRenderedFirstFrame() {
            if (VideoPlayerView.this.b != null) {
                VideoPlayerView.this.b.setVisibility(4);
            }
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            VideoPlayerView.this.c(false);
        }

        public void onPlayerStateChanged(boolean z, int i) {
            VideoPlayerView.this.g();
            VideoPlayerView.this.h();
            if (VideoPlayerView.this.d() && VideoPlayerView.this.u) {
                VideoPlayerView.this.b();
            } else {
                VideoPlayerView.this.a(false);
            }
        }

        public void onPositionDiscontinuity(int i) {
            if (VideoPlayerView.this.d() && VideoPlayerView.this.u) {
                VideoPlayerView.this.b();
            }
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            VideoPlayerView.b((TextureView) view, VideoPlayerView.this.w);
        }
    }

    @SuppressLint({"InlinedApi"})
    private boolean a(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    public VideoPlayerView(Context context) {
        this(context, null);
    }

    public VideoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoPlayerView(Context context, AttributeSet attributeSet, int i) {
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        super(context, attributeSet, i);
        if (isInEditMode()) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            this.j = null;
            ImageView imageView = new ImageView(context2);
            if (Util.SDK_INT >= 23) {
                a(getResources(), imageView);
            } else {
                b(getResources(), imageView);
            }
            addView(imageView);
            return;
        }
        int color;
        boolean z;
        int resourceId;
        boolean z2;
        int i2;
        boolean z3;
        boolean z4;
        boolean z5;
        int i3;
        boolean z6;
        boolean z7;
        int i4 = R.layout.exo_player_view;
        int i5 = 3;
        boolean hasValue;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, com.google.android.exoplayer2.ui.R.styleable.PlayerView, 0, 0);
            try {
                hasValue = obtainStyledAttributes.hasValue(14);
                color = obtainStyledAttributes.getColor(14, 0);
                i4 = obtainStyledAttributes.getResourceId(7, R.layout.exo_player_view);
                z = obtainStyledAttributes.getBoolean(16, true);
                resourceId = obtainStyledAttributes.getResourceId(2, 0);
                z2 = obtainStyledAttributes.getBoolean(17, true);
                int i6 = obtainStyledAttributes.getInt(15, 1);
                i5 = obtainStyledAttributes.getInt(9, 3);
                i2 = obtainStyledAttributes.getInt(13, 5000);
                boolean z8 = obtainStyledAttributes.getBoolean(5, true);
                int i7 = i4;
                boolean z9 = obtainStyledAttributes.getBoolean(0, true);
                z3 = obtainStyledAttributes.getBoolean(11, false);
                int i8 = i6;
                this.p = obtainStyledAttributes.getBoolean(6, this.p);
                z4 = obtainStyledAttributes.getBoolean(4, true);
                obtainStyledAttributes.recycle();
                z5 = z4;
                i3 = i2;
                i4 = i7;
                z6 = z9;
                i2 = i8;
                z7 = z2;
                z2 = z8;
            } catch (Throwable th) {
                Throwable th2 = th;
                obtainStyledAttributes.recycle();
            }
        } else {
            z5 = true;
            z2 = z5;
            z6 = z2;
            z = z6;
            z7 = z;
            i3 = 5000;
            z3 = false;
            color = 0;
            hasValue = false;
            resourceId = 0;
            i2 = z7;
        }
        LayoutInflater.from(context).inflate(i4, this);
        this.i = new a();
        setDescendantFocusability(262144);
        this.a = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        if (this.a != null) {
            a(this.a, i5);
        }
        this.b = findViewById(R.id.exo_shutter);
        if (this.b != null && hasValue) {
            this.b.setBackgroundColor(color);
        }
        if (this.a == null || i2 == 0) {
            this.c = null;
        } else {
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            this.c = i2 == 2 ? new TextureView(context2) : new SurfaceView(context2);
            this.c.setLayoutParams(layoutParams);
            this.a.addView(this.c, 0);
        }
        this.j = (FrameLayout) findViewById(R.id.exo_overlay);
        this.d = (ImageView) findViewById(R.id.exo_artwork);
        z4 = z && this.d != null;
        this.m = z4;
        if (resourceId != 0) {
            this.n = BitmapFactory.decodeResource(context.getResources(), resourceId);
        }
        this.e = (SubtitleView) findViewById(R.id.exo_subtitles);
        if (this.e != null) {
            this.e.setUserDefaultStyle();
            this.e.setUserDefaultTextSize();
        }
        this.f = findViewById(R.id.exo_buffering);
        if (this.f != null) {
            this.f.setVisibility(8);
        }
        this.o = z3;
        this.g = (TextView) findViewById(R.id.exo_error_message);
        if (this.g != null) {
            this.g.setVisibility(8);
        }
        VideoPlayerControlView videoPlayerControlView = (VideoPlayerControlView) findViewById(R.id.exo_controller);
        View findViewById = findViewById(R.id.exo_controller_placeholder);
        if (videoPlayerControlView != null) {
            this.h = videoPlayerControlView;
            z3 = false;
        } else if (findViewById != null) {
            z3 = false;
            this.h = new VideoPlayerControlView(context2, null, 0, attributeSet2);
            this.h.setLayoutParams(findViewById.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById);
            viewGroup.removeView(findViewById);
            viewGroup.addView(this.h, indexOfChild);
        } else {
            z3 = false;
            this.h = null;
        }
        this.s = this.h != null ? i3 : z3;
        this.v = z2;
        this.t = z6;
        this.u = z5;
        if (z7 && this.h != null) {
            z3 = true;
        }
        this.l = z3;
        b();
    }

    public void setShareUrl(String str) {
        this.h.setShareUrl(str);
    }

    public Player getPlayer() {
        return this.k;
    }

    public void setPlayer(Player player) {
        if (this.k != player) {
            VideoComponent videoComponent;
            TextComponent textComponent;
            if (this.k != null) {
                this.k.removeListener(this.i);
                videoComponent = this.k.getVideoComponent();
                if (videoComponent != null) {
                    videoComponent.removeVideoListener(this.i);
                    if (this.c instanceof TextureView) {
                        videoComponent.clearVideoTextureView((TextureView) this.c);
                    } else if (this.c instanceof SurfaceView) {
                        videoComponent.clearVideoSurfaceView((SurfaceView) this.c);
                    }
                }
                textComponent = this.k.getTextComponent();
                if (textComponent != null) {
                    textComponent.removeTextOutput(this.i);
                }
            }
            this.k = player;
            if (this.l) {
                this.h.setPlayer(player);
            }
            if (this.e != null) {
                this.e.setCues(null);
            }
            g();
            h();
            c(true);
            if (player != null) {
                videoComponent = player.getVideoComponent();
                if (videoComponent != null) {
                    if (this.c instanceof TextureView) {
                        videoComponent.setVideoTextureView((TextureView) this.c);
                    } else if (this.c instanceof SurfaceView) {
                        videoComponent.setVideoSurfaceView((SurfaceView) this.c);
                    }
                    videoComponent.addVideoListener(this.i);
                }
                textComponent = player.getTextComponent();
                if (textComponent != null) {
                    textComponent.addTextOutput(this.i);
                }
                player.addListener(this.i);
                a(false);
            } else {
                b();
            }
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (this.c instanceof SurfaceView) {
            this.c.setVisibility(i);
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkState(this.a != null);
        this.a.setResizeMode(i);
    }

    public int getResizeMode() {
        Assertions.checkState(this.a != null);
        return this.a.getResizeMode();
    }

    public boolean getUseArtwork() {
        return this.m;
    }

    public void setUseArtwork(boolean z) {
        boolean z2 = (z && this.d == null) ? false : true;
        Assertions.checkState(z2);
        if (this.m != z) {
            this.m = z;
            c(false);
        }
    }

    public Bitmap getDefaultArtwork() {
        return this.n;
    }

    public void setDefaultArtwork(Bitmap bitmap) {
        if (this.n != bitmap) {
            this.n = bitmap;
            c(false);
        }
    }

    public boolean getUseController() {
        return this.l;
    }

    public void setUseController(boolean z) {
        boolean z2 = (z && this.h == null) ? false : true;
        Assertions.checkState(z2);
        if (this.l != z) {
            this.l = z;
            if (z) {
                this.h.setPlayer(this.k);
            } else if (this.h != null) {
                this.h.b();
                this.h.setPlayer(null);
            }
        }
    }

    public void setShutterBackgroundColor(int i) {
        if (this.b != null) {
            this.b.setBackgroundColor(i);
        }
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.p != z) {
            this.p = z;
            c(false);
        }
    }

    public void setShowBuffering(boolean z) {
        if (this.o != z) {
            this.o = z;
            g();
        }
    }

    public void setErrorMessageProvider(@Nullable ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider) {
        if (this.q != errorMessageProvider) {
            this.q = errorMessageProvider;
            h();
        }
    }

    public void setCustomErrorMessage(@Nullable CharSequence charSequence) {
        Assertions.checkState(this.g != null);
        this.r = charSequence;
        h();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.k == null || !this.k.isPlayingAd()) {
            boolean z = false;
            boolean z2 = a(keyEvent.getKeyCode()) && this.l && !this.h.c();
            a(true);
            if (z2 || a(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
                z = true;
            }
            return z;
        }
        this.j.requestFocus();
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        return this.l && this.h.a(keyEvent);
    }

    public void a() {
        b(c());
    }

    public void b() {
        if (this.h != null) {
            this.h.b();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.s;
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.checkState(this.h != null);
        this.s = i;
        if (this.h.c()) {
            a();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.v;
    }

    public void setControllerHideOnTouch(boolean z) {
        Assertions.checkState(this.h != null);
        this.v = z;
    }

    public boolean getControllerAutoShow() {
        return this.t;
    }

    public void setControllerAutoShow(boolean z) {
        this.t = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.u = z;
    }

    public void setControllerVisibilityListener(b bVar) {
        Assertions.checkState(this.h != null);
        this.h.setVisibilityListener(bVar);
    }

    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
        Assertions.checkState(this.h != null);
        this.h.setPlaybackPreparer(playbackPreparer);
    }

    public void setControlDispatcher(@Nullable ControlDispatcher controlDispatcher) {
        Assertions.checkState(this.h != null);
        this.h.setControlDispatcher(controlDispatcher);
    }

    public void setRewindIncrementMs(int i) {
        Assertions.checkState(this.h != null);
        this.h.setRewindIncrementMs(i);
    }

    public void setFastForwardIncrementMs(int i) {
        Assertions.checkState(this.h != null);
        this.h.setFastForwardIncrementMs(i);
    }

    public void setRepeatToggleModes(int i) {
        Assertions.checkState(this.h != null);
        this.h.setRepeatToggleModes(i);
    }

    public void setShowShuffleButton(boolean z) {
        Assertions.checkState(this.h != null);
        this.h.setShowShuffleButton(z);
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        Assertions.checkState(this.h != null);
        this.h.setShowMultiWindowTimeBar(z);
    }

    public void setExtraAdGroupMarkers(@Nullable long[] jArr, @Nullable boolean[] zArr) {
        Assertions.checkState(this.h != null);
        this.h.setExtraAdGroupMarkers(jArr, zArr);
    }

    public void setAspectRatioListener(AspectRatioListener aspectRatioListener) {
        Assertions.checkState(this.a != null);
        this.a.setAspectRatioListener(aspectRatioListener);
    }

    public View getVideoSurfaceView() {
        return this.c;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.j;
    }

    public SubtitleView getSubtitleView() {
        return this.e;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.l || this.k == null || motionEvent.getActionMasked() != 0) {
            return false;
        }
        if (!this.h.c()) {
            a(true);
        } else if (this.v) {
            this.h.b();
        }
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!this.l || this.k == null) {
            return false;
        }
        a(true);
        return true;
    }

    private void a(boolean z) {
        if (!(d() && this.u) && this.l) {
            Object obj = (!this.h.c() || this.h.getShowTimeoutMs() > 0) ? null : 1;
            boolean c = c();
            if (z || obj != null || c) {
                b(c);
            }
        }
    }

    private boolean c() {
        boolean z = true;
        if (this.k == null) {
            return true;
        }
        int playbackState = this.k.getPlaybackState();
        if (!(this.t && (playbackState == 1 || playbackState == 4 || !this.k.getPlayWhenReady()))) {
            z = false;
        }
        return z;
    }

    private void b(boolean z) {
        if (this.l) {
            this.h.setShowTimeoutMs(z ? 0 : this.s);
            this.h.a();
        }
    }

    private boolean d() {
        return this.k != null && this.k.isPlayingAd() && this.k.getPlayWhenReady();
    }

    private void c(boolean z) {
        if (this.k == null || this.k.getCurrentTrackGroups().isEmpty()) {
            if (!this.p) {
                e();
                f();
            }
            return;
        }
        if (z && !this.p) {
            f();
        }
        TrackSelectionArray currentTrackSelections = this.k.getCurrentTrackSelections();
        int i = 0;
        while (i < currentTrackSelections.length) {
            if (this.k.getRendererType(i) != 2 || currentTrackSelections.get(i) == null) {
                i++;
            } else {
                e();
                return;
            }
        }
        f();
        if (this.m) {
            for (i = 0; i < currentTrackSelections.length; i++) {
                TrackSelection trackSelection = currentTrackSelections.get(i);
                if (trackSelection != null) {
                    int i2 = 0;
                    while (i2 < trackSelection.length()) {
                        Metadata metadata = trackSelection.getFormat(i2).metadata;
                        if (metadata == null || !a(metadata)) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    continue;
                }
            }
            if (a(this.n)) {
                return;
            }
        }
        e();
    }

    private boolean a(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Entry entry = metadata.get(i);
            if (entry instanceof ApicFrame) {
                byte[] bArr = ((ApicFrame) entry).pictureData;
                return a(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }
        }
        return false;
    }

    private boolean a(Bitmap bitmap) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0 && height > 0) {
                if (this.a != null) {
                    this.a.setAspectRatio(((float) width) / ((float) height));
                }
                this.d.setImageBitmap(bitmap);
                this.d.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void e() {
        if (this.d != null) {
            this.d.setImageResource(17170445);
            this.d.setVisibility(4);
        }
    }

    private void f() {
        if (this.b != null) {
            this.b.setVisibility(0);
        }
    }

    private void g() {
        if (this.f != null) {
            int i = 0;
            int i2 = (this.o && this.k != null && this.k.getPlaybackState() == 2 && this.k.getPlayWhenReady()) ? 1 : 0;
            View view = this.f;
            if (i2 == 0) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    private void h() {
        if (this.g != null) {
            if (this.r != null) {
                this.g.setText(this.r);
                this.g.setVisibility(0);
                return;
            }
            Throwable th = null;
            if (!(this.k == null || this.k.getPlaybackState() != 1 || this.q == null)) {
                th = this.k.getPlaybackError();
            }
            if (th != null) {
                this.g.setText((CharSequence) this.q.getErrorMessage(th).second);
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(8);
            }
        }
    }

    @TargetApi(23)
    private static void a(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, null));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
    }

    private static void b(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void a(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    private static void b(TextureView textureView, int i) {
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
