package com.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.gaana.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player.DefaultEventListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.ui.TimeBar.OnScrubListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.library.controls.CrossFadeImageView;
import com.managers.u;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;

public class VideoPlayerControlView extends FrameLayout {
    private Player A;
    private ControlDispatcher B;
    private b C;
    @Nullable
    private PlaybackPreparer D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private int I;
    private int J;
    private int K;
    private int L;
    private boolean M;
    private long N;
    private long[] O;
    private boolean[] P;
    private long[] Q;
    private boolean[] R;
    private final Runnable S;
    private final Runnable T;
    private Context U;
    private String V;
    private final a a;
    private final View b;
    private final View c;
    private final View d;
    private final View e;
    private final View f;
    private final View g;
    private final ImageView h;
    private final View i;
    private final CrossFadeImageView j;
    private final CrossFadeImageView k;
    private final ImageButton l;
    private final View m;
    private final TextView n;
    private final TextView o;
    private final TimeBar p;
    private final StringBuilder q;
    private final Formatter r;
    private final Period s;
    private final Window t;
    private final Drawable u;
    private final Drawable v;
    private final Drawable w;
    private final String x;
    private final String y;
    private final String z;

    public interface b {
        void a(int i);
    }

    private final class a extends DefaultEventListener implements OnClickListener, OnScrubListener {
        private a() {
        }

        /* synthetic */ a(VideoPlayerControlView videoPlayerControlView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onScrubStart(TimeBar timeBar, long j) {
            VideoPlayerControlView.this.removeCallbacks(VideoPlayerControlView.this.T);
            VideoPlayerControlView.this.H = true;
        }

        public void onScrubMove(TimeBar timeBar, long j) {
            if (VideoPlayerControlView.this.o != null) {
                VideoPlayerControlView.this.o.setText(Util.getStringForTime(VideoPlayerControlView.this.q, VideoPlayerControlView.this.r, j));
            }
        }

        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            VideoPlayerControlView.this.H = false;
            if (!(z || VideoPlayerControlView.this.A == null)) {
                VideoPlayerControlView.this.b(j);
            }
            VideoPlayerControlView.this.d();
        }

        public void onPlayerStateChanged(boolean z, int i) {
            VideoPlayerControlView.this.f();
            VideoPlayerControlView.this.k();
        }

        public void onRepeatModeChanged(int i) {
            VideoPlayerControlView.this.h();
            VideoPlayerControlView.this.g();
        }

        public void onShuffleModeEnabledChanged(boolean z) {
            VideoPlayerControlView.this.i();
            VideoPlayerControlView.this.g();
        }

        public void onPositionDiscontinuity(int i) {
            VideoPlayerControlView.this.g();
            VideoPlayerControlView.this.k();
        }

        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            VideoPlayerControlView.this.g();
            VideoPlayerControlView.this.j();
            VideoPlayerControlView.this.k();
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cross_video_controller /*2131296815*/:
                    if (VideoPlayerControlView.this.U instanceof VideoPlayerActivityTwo) {
                        ((VideoPlayerActivityTwo) VideoPlayerControlView.this.U).onBackPressed();
                        break;
                    }
                    break;
                case R.id.fullscreen /*2131297216*/:
                    if (VideoPlayerControlView.this.U instanceof VideoPlayerActivityTwo) {
                        ((VideoPlayerActivityTwo) VideoPlayerControlView.this.U).a();
                        break;
                    }
                    break;
                case R.id.pause /*2131297937*/:
                    VideoPlayerControlView.this.B.dispatchSetPlayWhenReady(VideoPlayerControlView.this.A, false);
                    VideoPlayerControlView.this.d.setVisibility(0);
                    VideoPlayerControlView.this.e.setVisibility(8);
                    VideoPlayerControlView.this.i.setVisibility(8);
                    break;
                case R.id.play /*2131297975*/:
                    if (VideoPlayerControlView.this.A.getPlaybackState() == 1 && VideoPlayerControlView.this.D != null) {
                        VideoPlayerControlView.this.D.preparePlayback();
                    }
                    VideoPlayerControlView.this.B.dispatchSetPlayWhenReady(VideoPlayerControlView.this.A, true);
                    VideoPlayerControlView.this.d.setVisibility(8);
                    VideoPlayerControlView.this.e.setVisibility(0);
                    VideoPlayerControlView.this.i.setVisibility(8);
                    break;
                case R.id.replay /*2131298222*/:
                    VideoPlayerControlView.this.a(0);
                    VideoPlayerControlView.this.d.setVisibility(8);
                    VideoPlayerControlView.this.e.setVisibility(0);
                    VideoPlayerControlView.this.i.setVisibility(8);
                    break;
                case R.id.share_video_controller /*2131298401*/:
                    u.a().b("Gaana User Year Video", "MusicalJourneyShare");
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    String r = VideoPlayerControlView.this.V;
                    intent.putExtra("android.intent.extra.SUBJECT", VideoPlayerControlView.this.U.getString(R.string.my_year_in_gaana));
                    intent.putExtra("android.intent.extra.TEXT", r);
                    VideoPlayerControlView.this.U.startActivity(Intent.createChooser(intent, VideoPlayerControlView.this.U.getString(R.string.share_via)));
                    break;
            }
            VideoPlayerControlView.this.d();
        }
    }

    @SuppressLint({"InlinedApi"})
    private static boolean a(int i) {
        return i == 90 || i == 89 || i == 85 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ui");
    }

    public VideoPlayerControlView(Context context) {
        this(context, null);
    }

    public VideoPlayerControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoPlayerControlView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    public void setShareUrl(String str) {
        this.V = str;
    }

    public VideoPlayerControlView(Context context, AttributeSet attributeSet, int i, AttributeSet attributeSet2) {
        super(context, attributeSet, i);
        this.S = new Runnable() {
            public void run() {
                VideoPlayerControlView.this.k();
            }
        };
        this.T = new Runnable() {
            public void run() {
                VideoPlayerControlView.this.b();
            }
        };
        this.U = context;
        this.I = 5000;
        this.J = 15000;
        this.K = 5000;
        this.L = 0;
        this.N = C.TIME_UNSET;
        this.M = false;
        this.s = new Period();
        this.t = new Window();
        this.q = new StringBuilder();
        this.r = new Formatter(this.q, Locale.getDefault());
        this.O = new long[0];
        this.P = new boolean[0];
        this.Q = new long[0];
        this.R = new boolean[0];
        this.a = new a(this, null);
        this.B = new DefaultControlDispatcher();
        View inflate = LayoutInflater.from(context).inflate(R.layout.exo_player_control_view_custom, this);
        setDescendantFocusability(262144);
        this.n = (TextView) inflate.findViewById(R.id.time);
        this.o = (TextView) inflate.findViewById(R.id.time_current);
        this.p = (TimeBar) inflate.findViewById(R.id.mediacontroller_progress);
        if (this.p != null) {
            this.p.addListener(this.a);
        }
        this.d = (ImageButton) inflate.findViewById(R.id.play);
        if (this.d != null) {
            this.d.setOnClickListener(this.a);
        }
        this.e = (ImageButton) inflate.findViewById(R.id.pause);
        if (this.e != null) {
            this.e.setOnClickListener(this.a);
        }
        this.i = (ImageButton) inflate.findViewById(R.id.replay);
        if (this.i != null) {
            this.i.setOnClickListener(this.a);
        }
        this.j = (CrossFadeImageView) inflate.findViewById(R.id.cross_video_controller);
        if (this.j != null) {
            this.j.setOnClickListener(this.a);
        }
        this.k = (CrossFadeImageView) inflate.findViewById(R.id.share_video_controller);
        if (TextUtils.isEmpty(this.V)) {
            this.k.setVisibility(8);
        } else if (this.k != null) {
            this.k.setOnClickListener(this.a);
        }
        this.l = (ImageButton) inflate.findViewById(R.id.fullscreen);
        if (this.l != null) {
            this.l.setOnClickListener(this.a);
        }
        this.b = findViewById(R.id.exo_prev);
        if (this.b != null) {
            this.b.setOnClickListener(this.a);
        }
        this.c = findViewById(R.id.exo_next);
        if (this.c != null) {
            this.c.setOnClickListener(this.a);
        }
        this.g = findViewById(R.id.exo_rew);
        if (this.g != null) {
            this.g.setOnClickListener(this.a);
        }
        this.f = findViewById(R.id.exo_ffwd);
        if (this.f != null) {
            this.f.setOnClickListener(this.a);
        }
        this.h = (ImageView) findViewById(R.id.exo_repeat_toggle);
        if (this.h != null) {
            this.h.setOnClickListener(this.a);
        }
        this.m = findViewById(R.id.exo_shuffle);
        if (this.m != null) {
            this.m.setOnClickListener(this.a);
        }
        Resources resources = context.getResources();
        this.u = resources.getDrawable(R.drawable.exo_controls_repeat_off);
        this.v = resources.getDrawable(R.drawable.exo_controls_repeat_one);
        this.w = resources.getDrawable(R.drawable.exo_controls_repeat_all);
        this.x = resources.getString(R.string.exo_controls_repeat_off_description);
        this.y = resources.getString(R.string.exo_controls_repeat_one_description);
        this.z = resources.getString(R.string.exo_controls_repeat_all_description);
    }

    public Player getPlayer() {
        return this.A;
    }

    public void setPlayer(Player player) {
        if (this.A != player) {
            if (this.A != null) {
                this.A.removeListener(this.a);
            }
            this.A = player;
            if (player != null) {
                player.addListener(this.a);
            }
            e();
        }
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        this.F = z;
        j();
    }

    public void setExtraAdGroupMarkers(@Nullable long[] jArr, @Nullable boolean[] zArr) {
        boolean z = false;
        if (jArr == null) {
            this.Q = new long[0];
            this.R = new boolean[0];
        } else {
            if (jArr.length == zArr.length) {
                z = true;
            }
            Assertions.checkArgument(z);
            this.Q = jArr;
            this.R = zArr;
        }
        k();
    }

    public void setVisibilityListener(b bVar) {
        this.C = bVar;
    }

    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
        this.D = playbackPreparer;
    }

    public void setControlDispatcher(@Nullable ControlDispatcher controlDispatcher) {
        if (controlDispatcher == null) {
            controlDispatcher = new DefaultControlDispatcher();
        }
        this.B = controlDispatcher;
    }

    public void setRewindIncrementMs(int i) {
        this.I = i;
        g();
    }

    public void setFastForwardIncrementMs(int i) {
        this.J = i;
        g();
    }

    public int getShowTimeoutMs() {
        return this.K;
    }

    public void setShowTimeoutMs(int i) {
        this.K = i;
        if (c()) {
            d();
        }
    }

    public int getRepeatToggleModes() {
        return this.L;
    }

    public void setRepeatToggleModes(int i) {
        this.L = i;
        if (this.A != null) {
            int repeatMode = this.A.getRepeatMode();
            if (i == 0 && repeatMode != 0) {
                this.B.dispatchSetRepeatMode(this.A, 0);
            } else if (i == 1 && repeatMode == 2) {
                this.B.dispatchSetRepeatMode(this.A, 1);
            } else if (i == 2 && repeatMode == 1) {
                this.B.dispatchSetRepeatMode(this.A, 2);
            }
        }
    }

    public boolean getShowShuffleButton() {
        return this.M;
    }

    public void setShowShuffleButton(boolean z) {
        this.M = z;
        i();
    }

    public void a() {
        if (!c()) {
            setVisibility(0);
            if (this.C != null) {
                this.C.a(getVisibility());
            }
            e();
            l();
        }
        d();
    }

    public void b() {
        if (c()) {
            setVisibility(8);
            if (this.C != null) {
                this.C.a(getVisibility());
            }
            removeCallbacks(this.S);
            removeCallbacks(this.T);
            this.N = C.TIME_UNSET;
        }
    }

    public boolean c() {
        return getVisibility() == 0;
    }

    private void d() {
        removeCallbacks(this.T);
        if (this.K > 0) {
            this.N = SystemClock.uptimeMillis() + ((long) this.K);
            if (this.E) {
                postDelayed(this.T, (long) this.K);
                return;
            }
            return;
        }
        this.N = C.TIME_UNSET;
    }

    private void e() {
        f();
        g();
        h();
        i();
        k();
    }

    private void f() {
        if (c() && this.E) {
            int i;
            boolean q = q();
            int i2 = 8;
            int i3 = 1;
            if (this.d != null) {
                i = (q && this.d.isFocused()) ? 1 : 0;
                i |= 0;
                if (this.A == null || this.A.getPlaybackState() != 4) {
                    this.d.setVisibility(q ? 8 : 0);
                } else {
                    this.i.setVisibility(0);
                }
            } else {
                i = 0;
            }
            if (this.e != null) {
                if (q || !this.e.isFocused()) {
                    i3 = 0;
                }
                i |= i3;
                View view = this.e;
                if (q) {
                    i2 = 0;
                }
                view.setVisibility(i2);
            }
            if (i != 0) {
                l();
            }
        }
    }

    private void g() {
        if (c() && this.E) {
            boolean z;
            boolean z2;
            Timeline currentTimeline = this.A != null ? this.A.getCurrentTimeline() : null;
            boolean z3 = true;
            boolean z4 = (currentTimeline == null || currentTimeline.isEmpty()) ? false : true;
            if (!z4 || this.A.isPlayingAd()) {
                z = false;
                z4 = z;
                z2 = z4;
            } else {
                currentTimeline.getWindow(this.A.getCurrentWindowIndex(), this.t);
                z = this.t.isSeekable;
                z2 = (!z && this.t.isDynamic && this.A.getPreviousWindowIndex() == -1) ? false : true;
                z4 = this.t.isDynamic || this.A.getNextWindowIndex() != -1;
            }
            a(z2, this.b);
            a(z4, this.c);
            z4 = this.J > 0 && z;
            a(z4, this.f);
            if (this.I <= 0 || !z) {
                z3 = false;
            }
            a(z3, this.g);
            if (this.p != null) {
                this.p.setEnabled(z);
            }
        }
    }

    private void h() {
        if (!c() || !this.E || this.h == null) {
            return;
        }
        if (this.L == 0) {
            this.h.setVisibility(8);
        } else if (this.A == null) {
            a(false, this.h);
        } else {
            a(true, this.h);
            switch (this.A.getRepeatMode()) {
                case 0:
                    this.h.setImageDrawable(this.u);
                    this.h.setContentDescription(this.x);
                    break;
                case 1:
                    this.h.setImageDrawable(this.v);
                    this.h.setContentDescription(this.y);
                    break;
                case 2:
                    this.h.setImageDrawable(this.w);
                    this.h.setContentDescription(this.z);
                    break;
            }
            this.h.setVisibility(0);
        }
    }

    private void i() {
        if (c() && this.E && this.m != null) {
            if (!this.M) {
                this.m.setVisibility(8);
            } else if (this.A == null) {
                a(false, this.m);
            } else {
                this.m.setAlpha(this.A.getShuffleModeEnabled() ? 1.0f : 0.3f);
                this.m.setEnabled(true);
                this.m.setVisibility(0);
            }
        }
    }

    private void j() {
        if (this.A != null) {
            boolean z = this.F && a(this.A.getCurrentTimeline(), this.t);
            this.G = z;
        }
    }

    private void k() {
        if (c() && this.E) {
            long j;
            long j2;
            long usToMs;
            long contentPosition;
            int length;
            int i = 1;
            if (this.A != null) {
                long j3;
                int i2;
                Timeline currentTimeline = this.A.getCurrentTimeline();
                if (currentTimeline.isEmpty()) {
                    j3 = 0;
                    i2 = 0;
                    j = 0;
                } else {
                    int currentWindowIndex = this.A.getCurrentWindowIndex();
                    int i3 = this.G ? 0 : currentWindowIndex;
                    int windowCount = this.G ? currentTimeline.getWindowCount() - 1 : currentWindowIndex;
                    j3 = 0;
                    i2 = 0;
                    j = 0;
                    while (i3 <= windowCount) {
                        if (i3 == currentWindowIndex) {
                            j = j3;
                        }
                        currentTimeline.getWindow(i3, this.t);
                        if (this.t.durationUs == C.TIME_UNSET) {
                            Assertions.checkState(this.G ^ i);
                            break;
                        }
                        int i4 = this.t.firstPeriodIndex;
                        while (i4 <= this.t.lastPeriodIndex) {
                            int i5;
                            currentTimeline.getPeriod(i4, this.s);
                            int adGroupCount = this.s.getAdGroupCount();
                            i = i2;
                            i2 = 0;
                            while (i2 < adGroupCount) {
                                long adGroupTimeUs = this.s.getAdGroupTimeUs(i2);
                                if (adGroupTimeUs == Long.MIN_VALUE) {
                                    if (this.s.durationUs != C.TIME_UNSET) {
                                        j2 = this.s.durationUs;
                                    }
                                    i5 = currentWindowIndex;
                                    i2++;
                                    currentWindowIndex = i5;
                                } else {
                                    j2 = adGroupTimeUs;
                                }
                                long positionInWindowUs = j2 + this.s.getPositionInWindowUs();
                                if (positionInWindowUs >= 0 && positionInWindowUs <= this.t.durationUs) {
                                    if (i == this.O.length) {
                                        int length2 = this.O.length == 0 ? 1 : this.O.length * 2;
                                        this.O = Arrays.copyOf(this.O, length2);
                                        this.P = Arrays.copyOf(this.P, length2);
                                    }
                                    i5 = currentWindowIndex;
                                    this.O[i] = C.usToMs(j3 + positionInWindowUs);
                                    this.P[i] = this.s.hasPlayedAdGroup(i2);
                                    i++;
                                    i2++;
                                    currentWindowIndex = i5;
                                }
                                i5 = currentWindowIndex;
                                i2++;
                                currentWindowIndex = i5;
                            }
                            i5 = currentWindowIndex;
                            i4++;
                            i2 = i;
                        }
                        i3++;
                        j3 += this.t.durationUs;
                        currentWindowIndex = currentWindowIndex;
                        i = 1;
                    }
                }
                j2 = C.usToMs(j3);
                usToMs = C.usToMs(j);
                if (this.A.isPlayingAd()) {
                    contentPosition = usToMs + this.A.getContentPosition();
                    j = contentPosition;
                } else {
                    contentPosition = usToMs + this.A.getCurrentPosition();
                    j = usToMs + this.A.getBufferedPosition();
                }
                if (this.p != null) {
                    length = this.Q.length;
                    i = i2 + length;
                    if (i > this.O.length) {
                        this.O = Arrays.copyOf(this.O, i);
                        this.P = Arrays.copyOf(this.P, i);
                    }
                    System.arraycopy(this.Q, 0, this.O, i2, length);
                    System.arraycopy(this.R, 0, this.P, i2, length);
                    this.p.setAdGroupTimesMs(this.O, this.P, i);
                }
            } else {
                j2 = 0;
                contentPosition = 0;
                j = 0;
            }
            if (this.n != null) {
                this.n.setText(Util.getStringForTime(this.q, this.r, j2));
            }
            if (!(this.o == null || this.H)) {
                this.o.setText(Util.getStringForTime(this.q, this.r, contentPosition));
            }
            if (this.p != null) {
                this.p.setPosition(contentPosition);
                this.p.setBufferedPosition(j);
                this.p.setDuration(j2);
            }
            removeCallbacks(this.S);
            if (this.A == null) {
                length = 1;
                i = 1;
            } else {
                i = this.A.getPlaybackState();
                length = 1;
            }
            if (!(i == length || i == 4)) {
                j2 = 1000;
                if (this.A.getPlayWhenReady() && i == 3) {
                    float f = this.A.getPlaybackParameters().speed;
                    if (f > 0.1f) {
                        if (f <= 5.0f) {
                            usToMs = (long) (1000 / Math.max(1, Math.round(1.0f / f)));
                            long j4 = usToMs - (contentPosition % usToMs);
                            if (j4 < usToMs / 5) {
                                j4 += usToMs;
                            }
                            j2 = f == 1.0f ? j4 : (long) (((float) j4) / f);
                        } else {
                            j2 = 200;
                        }
                    }
                }
                postDelayed(this.S, j2);
            }
        }
    }

    private void l() {
        boolean q = q();
        if (!q && this.d != null) {
            this.d.requestFocus();
        } else if (q && this.e != null) {
            this.e.requestFocus();
        }
    }

    private void a(boolean z, View view) {
        if (view != null) {
            view.setEnabled(z);
            view.setAlpha(z ? 1.0f : 0.3f);
            view.setVisibility(0);
        }
    }

    private void m() {
        Timeline currentTimeline = this.A.getCurrentTimeline();
        if (!currentTimeline.isEmpty()) {
            currentTimeline.getWindow(this.A.getCurrentWindowIndex(), this.t);
            int previousWindowIndex = this.A.getPreviousWindowIndex();
            if (previousWindowIndex == -1 || (this.A.getCurrentPosition() > 3000 && (!this.t.isDynamic || this.t.isSeekable))) {
                a(0);
            } else {
                a(previousWindowIndex, (long) C.TIME_UNSET);
            }
        }
    }

    private void n() {
        Timeline currentTimeline = this.A.getCurrentTimeline();
        if (!currentTimeline.isEmpty()) {
            int currentWindowIndex = this.A.getCurrentWindowIndex();
            int nextWindowIndex = this.A.getNextWindowIndex();
            if (nextWindowIndex != -1) {
                a(nextWindowIndex, (long) C.TIME_UNSET);
            } else if (currentTimeline.getWindow(currentWindowIndex, this.t, false).isDynamic) {
                a(currentWindowIndex, (long) C.TIME_UNSET);
            }
        }
    }

    private void o() {
        if (this.I > 0) {
            a(Math.max(this.A.getCurrentPosition() - ((long) this.I), 0));
        }
    }

    private void p() {
        if (this.J > 0) {
            long duration = this.A.getDuration();
            long currentPosition = this.A.getCurrentPosition() + ((long) this.J);
            if (duration != C.TIME_UNSET) {
                currentPosition = Math.min(currentPosition, duration);
            }
            a(currentPosition);
        }
    }

    private void a(long j) {
        a(this.A.getCurrentWindowIndex(), j);
    }

    private void a(int i, long j) {
        if (!this.B.dispatchSeekTo(this.A, i, j)) {
            k();
        }
    }

    private void b(long j) {
        int i;
        Timeline currentTimeline = this.A.getCurrentTimeline();
        if (this.G && !currentTimeline.isEmpty()) {
            int windowCount = currentTimeline.getWindowCount();
            i = 0;
            while (true) {
                long durationMs = currentTimeline.getWindow(i, this.t).getDurationMs();
                if (j < durationMs) {
                    break;
                } else if (i == windowCount - 1) {
                    j = durationMs;
                    break;
                } else {
                    i++;
                    j -= durationMs;
                }
            }
        } else {
            i = this.A.getCurrentWindowIndex();
        }
        a(i, j);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.E = true;
        if (this.N != C.TIME_UNSET) {
            long uptimeMillis = this.N - SystemClock.uptimeMillis();
            if (uptimeMillis <= 0) {
                b();
            } else {
                postDelayed(this.T, uptimeMillis);
            }
        } else if (c()) {
            d();
        }
        e();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.E = false;
        removeCallbacks(this.S);
        removeCallbacks(this.T);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return a(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.A == null || !a(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() == 0) {
            if (keyCode != 90) {
                if (keyCode != 89) {
                    if (keyEvent.getRepeatCount() == 0) {
                        switch (keyCode) {
                            case 85:
                                this.B.dispatchSetPlayWhenReady(this.A, this.A.getPlayWhenReady() ^ 1);
                                break;
                            case 87:
                                n();
                                break;
                            case 88:
                                m();
                                break;
                            case 126:
                                this.B.dispatchSetPlayWhenReady(this.A, true);
                                break;
                            case 127:
                                this.B.dispatchSetPlayWhenReady(this.A, false);
                                break;
                        }
                    }
                }
                o();
            } else {
                p();
            }
        }
        return true;
    }

    private boolean q() {
        if (this.A == null || this.A.getPlaybackState() == 4 || this.A.getPlaybackState() == 1 || !this.A.getPlayWhenReady()) {
            return false;
        }
        return true;
    }

    private static boolean a(Timeline timeline, Window window) {
        if (timeline.getWindowCount() > 100) {
            return false;
        }
        int windowCount = timeline.getWindowCount();
        for (int i = 0; i < windowCount; i++) {
            if (timeline.getWindow(i, window).durationUs == C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }
}
