package com.til.colombia.android.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import com.comscore.streaming.Constants;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.CommonUtil.AutoPlay;
import com.til.colombia.android.commons.CommonUtil.VideoPauseMode;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.n;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class am extends MediaPlayer implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener {
    private static final String f = "ColombiaMediaPlayer";
    b a;
    boolean b = true;
    VideoPauseMode c;
    boolean d;
    COLOMBIA_PLAYER_STATE e = COLOMBIA_PLAYER_STATE.NULL;
    private Handler g;
    private ProgressBar h;
    private Context i;
    private Uri j;
    private VASTHelper k;
    private CmItem l;
    private int m;
    private long n = 0;
    private ScheduledExecutorService o;

    private class a extends Handler {
        private a() {
        }

        /* synthetic */ a(am amVar, byte b) {
            this();
        }

        public final void handleMessage(Message message) {
            try {
                am.c(am.this);
            } catch (IllegalStateException unused) {
                am.this.o.shutdownNow();
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
        }
    }

    public interface b {
        void b();

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();
    }

    public am(Context context, Uri uri, CmItem cmItem, ProgressBar progressBar) {
        this.i = context;
        this.j = uri;
        this.l = cmItem;
        this.k = ((NativeItem) cmItem).getVastHelper();
        this.h = progressBar;
        this.g = new a(this, (byte) 0);
        this.o = Executors.newScheduledThreadPool(1);
        try {
            setDataSource(this.i, this.j);
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    public final void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.e = COLOMBIA_PLAYER_STATE.INITIALIZED;
        super.setDataSource(context, uri);
    }

    public final void prepareAsync() {
        if (this.e == COLOMBIA_PLAYER_STATE.INITIALIZED) {
            this.e = COLOMBIA_PLAYER_STATE.PREPARING;
            super.prepareAsync();
        }
    }

    public final void start() {
        if (this.e == COLOMBIA_PLAYER_STATE.PREPARED || this.e == COLOMBIA_PLAYER_STATE.STARTED || this.e == COLOMBIA_PLAYER_STATE.PAUSED || this.e == COLOMBIA_PLAYER_STATE.COMPLETED) {
            if (this.e == COLOMBIA_PLAYER_STATE.PREPARED) {
                this.o.scheduleWithFixedDelay(new ao(this), 1000, 1000, TimeUnit.MILLISECONDS);
                setOnCompletionListener(this);
            }
            this.e = COLOMBIA_PLAYER_STATE.STARTED;
            this.c = VideoPauseMode.NONE;
            this.d = false;
            super.start();
        }
    }

    public final void pause() {
        if (this.e == COLOMBIA_PLAYER_STATE.STARTED || this.e == COLOMBIA_PLAYER_STATE.PAUSED) {
            this.e = COLOMBIA_PLAYER_STATE.PAUSED;
            super.pause();
        }
    }

    public final boolean isPlaying() {
        return (this.e == COLOMBIA_PLAYER_STATE.NULL || this.e == COLOMBIA_PLAYER_STATE.END || this.e == COLOMBIA_PLAYER_STATE.ERROR) ? false : super.isPlaying();
    }

    public final void release() {
        this.e = COLOMBIA_PLAYER_STATE.END;
        this.g.removeMessages(1);
        this.o.shutdown();
        stop();
        new Thread(new an(this)).start();
    }

    private void k() {
        super.release();
    }

    public final void stop() {
        if (this.e != COLOMBIA_PLAYER_STATE.NULL && this.e != COLOMBIA_PLAYER_STATE.END && this.e != COLOMBIA_PLAYER_STATE.ERROR) {
            super.stop();
        }
    }

    public final int getCurrentPosition() {
        return (this.e == COLOMBIA_PLAYER_STATE.NULL || this.e == COLOMBIA_PLAYER_STATE.END || this.e == COLOMBIA_PLAYER_STATE.ERROR) ? 0 : super.getCurrentPosition();
    }

    public final int getDuration() {
        return (this.e == COLOMBIA_PLAYER_STATE.NULL || this.e == COLOMBIA_PLAYER_STATE.INITIALIZED || this.e == COLOMBIA_PLAYER_STATE.END || this.e == COLOMBIA_PLAYER_STATE.ERROR) ? 0 : super.getDuration();
    }

    /* Access modifiers changed, original: protected|final */
    public final void a() {
        setOnPreparedListener(this);
        setOnErrorListener(this);
        setOnInfoListener(this);
        setOnCompletionListener(this);
        setOnBufferingUpdateListener(this);
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        Log.a(f, "MediaPlayer Prepared");
        this.e = COLOMBIA_PLAYER_STATE.PREPARED;
        this.a.b();
        n.a(this.k.getVastTrackingByType(1), 5, "VAST creative view tracked.");
        if ((((NativeItem) this.l).getPlayMode() == AutoPlay.ON && !CommonUtil.a(this.i) && com.til.colombia.android.internal.a.b(this.i)) || this.h.getVisibility() == 0) {
            a(false);
        }
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = f;
        StringBuilder stringBuilder = new StringBuilder("MediaPlayer Error ");
        stringBuilder.append(i);
        stringBuilder.append(" : ");
        stringBuilder.append(i2);
        Log.a(str, stringBuilder.toString());
        this.e = COLOMBIA_PLAYER_STATE.ERROR;
        setOnCompletionListener(null);
        e();
        reset();
        release();
        this.a.f();
        return false;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = f;
        StringBuilder stringBuilder = new StringBuilder("MediaPlayer Info ");
        stringBuilder.append(i);
        Log.a(str, stringBuilder.toString());
        if (3 == i) {
            e();
        } else if (701 == i) {
            f();
        } else if (702 == i) {
            e();
        }
        return false;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.m = i;
        this.d = false;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (this.e != COLOMBIA_PLAYER_STATE.COMPLETED) {
            n.a(this.k.getVastTrackingByType(6), 5, "video completion tracked.");
        }
        this.e = COLOMBIA_PLAYER_STATE.COMPLETED;
        this.a.e();
    }

    private void a(COLOMBIA_PLAYER_STATE colombia_player_state) {
        this.e = colombia_player_state;
    }

    public final COLOMBIA_PLAYER_STATE b() {
        return this.e;
    }

    public final boolean c() {
        return this.d;
    }

    private void d(boolean z) {
        this.d = z;
    }

    /* Access modifiers changed, original: protected|final */
    public final VideoPauseMode d() {
        if (this.e != COLOMBIA_PLAYER_STATE.PAUSED) {
            return VideoPauseMode.NONE;
        }
        return this.c;
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(VideoPauseMode videoPauseMode) {
        this.c = videoPauseMode;
    }

    public final void a(b bVar) {
        this.a = bVar;
    }

    /* Access modifiers changed, original: protected|final */
    public final void e() {
        if (this.h != null) {
            this.h.setVisibility(8);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void f() {
        if (this.h != null) {
            this.h.setVisibility(0);
            this.h.bringToFront();
        }
    }

    private void l() {
        this.o.scheduleWithFixedDelay(new ao(this), 1000, 1000, TimeUnit.MILLISECONDS);
    }

    private void m() {
        int duration = getDuration() / 1000;
        int currentPosition = getCurrentPosition() / 1000;
        int i = (currentPosition * 100) / duration;
        if (this.n != 0 && this.n < System.currentTimeMillis() - Constants.HEARTBEAT_STAGE_ONE_INTERVAL) {
            this.n = 0;
            this.c = VideoPauseMode.USER_PAUSE;
            e();
            this.a.d();
        }
        n.a(this.k.getVastProgressEvent(currentPosition), 5, "audio progress tracked.");
        if (!isPlaying()) {
            if (((i > 0 && ((double) i) <= 0.7d * ((double) this.m)) || this.m == 100) && d() == VideoPauseMode.BUFFERING) {
                this.n = 0;
                this.d = false;
                start();
                e();
            }
        } else if (((double) i) <= 0.7d * ((double) this.m) || this.m == 100) {
            if (currentPosition == 1) {
                if (this.e == COLOMBIA_PLAYER_STATE.STARTED || this.e == COLOMBIA_PLAYER_STATE.COMPLETED) {
                    ((NativeItem) this.l).getItemResponse().recordItemResponseImpression(null);
                    this.k.resetVtEvents();
                    n.a(this.k.getImpressionTrackerUrl(), 5, "VAST impression tracked.");
                } else if (!(d() == VideoPauseMode.AUTO_PAUSE || d() == VideoPauseMode.BUFFERING)) {
                    n.a(this.k.getVastTrackingByType(12), 5, "resume video tracked.");
                }
            }
            this.a.h();
            if (currentPosition == this.k.getStartNotifyTime()) {
                n.a(this.k.getVastTrackingByType(2), 5, "start video tracked.");
                this.a.g();
            }
            i = currentPosition * 4;
            if (duration >= i && duration < (currentPosition + 1) * 4) {
                n.a(this.k.getVastTrackingByType(3), 5, "Q1 tracked.");
            } else if (duration < currentPosition * 2 || duration >= (currentPosition + 1) * 2) {
                duration *= 3;
                if (duration >= i && duration < (currentPosition + 1) * 4) {
                    n.a(this.k.getVastTrackingByType(5), 5, "Q3 tracked.");
                }
            } else {
                n.a(this.k.getVastTrackingByType(4), 5, "mid Q tracked.");
            }
            List customEvents = this.k.getCustomEvents(currentPosition);
            StringBuilder stringBuilder = new StringBuilder("custom event at ");
            stringBuilder.append(currentPosition);
            stringBuilder.append(" tracked.");
            n.a(customEvents, 5, stringBuilder.toString());
        } else {
            pause();
            this.c = VideoPauseMode.BUFFERING;
            this.d = true;
            f();
            this.n = System.currentTimeMillis();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(boolean z) {
        start();
        this.a.c();
    }

    private void e(boolean z) {
        if (z) {
            h();
        }
        pause();
    }

    /* Access modifiers changed, original: protected|final */
    public final void b(boolean z) {
        try {
            setVolume(0.0f, 0.0f);
            this.b = true;
            if (z) {
                n.a(this.k.getVastTrackingByType(9), 5, "mute mode tracked.");
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void c(boolean z) {
        try {
            setVolume(1.0f, 1.0f);
            this.b = false;
            if (z) {
                n.a(this.k.getVastTrackingByType(10), 5, "unmute mode tracked.");
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean g() {
        return this.b;
    }

    private void n() {
        if (this.e == COLOMBIA_PLAYER_STATE.STARTED || this.e == COLOMBIA_PLAYER_STATE.COMPLETED) {
            ((NativeItem) this.l).getItemResponse().recordItemResponseImpression(null);
            this.k.resetVtEvents();
            n.a(this.k.getImpressionTrackerUrl(), 5, "VAST impression tracked.");
            return;
        }
        if (!(d() == VideoPauseMode.AUTO_PAUSE || d() == VideoPauseMode.BUFFERING)) {
            n.a(this.k.getVastTrackingByType(12), 5, "resume video tracked.");
        }
    }

    private void o() {
        n.a(this.k.getVastTrackingByType(1), 5, "VAST creative view tracked.");
    }

    public final void h() {
        n.a(this.k.getVastTrackingByType(11), 5, "pause video tracked.");
    }

    private void p() {
        n.a(this.k.getImpressionTrackerUrl(), 5, "VAST impression tracked.");
    }

    private void a(int i) {
        n.a(this.k.getVastProgressEvent(i), 5, "audio progress tracked.");
    }

    private void q() {
        n.a(this.k.getVastTrackingByType(2), 5, "start video tracked.");
    }

    public final void i() {
        n.a(this.k.getVastTrackingByType(9), 5, "mute mode tracked.");
    }

    public final void j() {
        n.a(this.k.getVastTrackingByType(10), 5, "unmute mode tracked.");
    }

    private void r() {
        n.a(this.k.getVastTrackingByType(3), 5, "Q1 tracked.");
    }

    private void s() {
        n.a(this.k.getVastTrackingByType(4), 5, "mid Q tracked.");
    }

    private void t() {
        n.a(this.k.getVastTrackingByType(5), 5, "Q3 tracked.");
    }

    private void u() {
        n.a(this.k.getVastTrackingByType(6), 5, "video completion tracked.");
    }

    private void v() {
        n.a(this.k.getVastTrackingByType(13), 5, "fullscreen mode tracked.");
    }

    private void b(int i) {
        List customEvents = this.k.getCustomEvents(i);
        StringBuilder stringBuilder = new StringBuilder("custom event at ");
        stringBuilder.append(i);
        stringBuilder.append(" tracked.");
        n.a(customEvents, 5, stringBuilder.toString());
    }
}
