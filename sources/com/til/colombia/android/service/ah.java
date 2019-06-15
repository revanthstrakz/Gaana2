package com.til.colombia.android.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.commons.a.g;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.n;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

final class ah extends MediaPlayer implements OnAudioFocusChangeListener, OnCompletionListener, OnErrorListener, OnPreparedListener {
    View a;
    boolean b;
    private Context c;
    private Uri d;
    private VASTHelper e;
    private AdListener f;
    private Item g;
    private a h;
    private ScheduledExecutorService i;
    private q j;
    private COLOMBIA_PLAYER_STATE k;
    private com.til.colombia.android.internal.a.a l;
    private AudioManager m;
    private MediaPlayer n;

    private class a extends Handler {
        private a() {
        }

        /* synthetic */ a(ah ahVar, byte b) {
            this();
        }

        public final void handleMessage(Message message) {
            try {
                ah.c(ah.this);
            } catch (Exception unused) {
                ah.this.i.shutdownNow();
            }
        }
    }

    public ah(Context context, Item item, AdListener adListener) {
        this.k = COLOMBIA_PLAYER_STATE.NULL;
        this.n = this;
        a(context, item, adListener);
    }

    public ah(Context context, Item item, AdListener adListener, q qVar) {
        this.k = COLOMBIA_PLAYER_STATE.NULL;
        this.j = qVar;
        this.n = this;
        a(context, item, adListener);
    }

    private void a(Context context, Item item, AdListener adListener) {
        this.c = context;
        this.g = item;
        this.e = ((NativeItem) item).getVastHelper();
        this.d = g.a(this.e.getMediaFileUrl());
        this.f = adListener;
        this.h = new a(this, (byte) 0);
        this.i = Executors.newScheduledThreadPool(1);
        setOnPreparedListener(this);
        setOnErrorListener(this);
        setOnCompletionListener(this);
        try {
            setDataSource(this.c, this.d);
            this.k = COLOMBIA_PLAYER_STATE.INITIALIZED;
            prepareAsync();
            this.k = COLOMBIA_PLAYER_STATE.PREPARING;
        } catch (IOException e) {
            Log.a(i.f, "", e);
        }
        this.l = new ai(this);
        this.l.a(this.c);
        this.m = (AudioManager) this.c.getSystemService("audio");
        try {
            this.m.requestAudioFocus(this, 3, 1);
        } catch (Exception e2) {
            Log.a(i.f, "", e2);
        }
    }

    private void e() {
        setOnPreparedListener(this);
        setOnErrorListener(this);
        setOnCompletionListener(this);
    }

    private void f() {
        setOnPreparedListener(null);
        setOnErrorListener(null);
        setOnCompletionListener(null);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(View view) {
        this.a = view;
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean a() {
        return this.b;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.k = COLOMBIA_PLAYER_STATE.COMPLETED;
        if (this.j != null) {
            this.j.c();
        }
        n.a(this.e.getVastTrackingByType(6), 5, "audio completion tracked.");
        this.f.onMediaItemCompleted(this.g, 0);
        this.f.onMediaItemClosed(this.g, USER_ACTION.AUTO_CLOSED);
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f.onMediaItemError(this.g, new Exception());
        this.k = COLOMBIA_PLAYER_STATE.ERROR;
        d();
        if (this.j != null) {
            this.j.c();
        }
        return false;
    }

    private void g() {
        pause();
        this.k = COLOMBIA_PLAYER_STATE.COMPLETED;
        d();
        this.f.onMediaItemClosed(this.g, USER_ACTION.INTERRUPTION);
        if (this.j != null) {
            this.j.c();
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        List vastTrackingByType;
        this.k = COLOMBIA_PLAYER_STATE.PREPARED;
        this.k = COLOMBIA_PLAYER_STATE.STARTED;
        start();
        if (this.g.getItemType() == ITEM_TYPE.AUDIO) {
            ((NativeItem) this.g).getItemResponse().recordItemResponseImpression(null);
        }
        ArrayList arrayList = new ArrayList();
        if (this.e.getStartNotifyTime() == 0) {
            vastTrackingByType = this.e.getVastTrackingByType(2);
            if (vastTrackingByType != null) {
                arrayList.addAll(vastTrackingByType);
            }
        }
        vastTrackingByType = this.e.getVastTrackingByType(1);
        if (vastTrackingByType != null) {
            arrayList.addAll(vastTrackingByType);
        }
        vastTrackingByType = this.e.getImpressionTrackerUrl();
        if (vastTrackingByType != null) {
            arrayList.addAll(vastTrackingByType);
        }
        n.a(arrayList, 5, "audio vast creative & start tracked.");
        this.i.scheduleWithFixedDelay(new aj(this), 1000, 1000, TimeUnit.MILLISECONDS);
        setOnCompletionListener(this);
        int duration = getDuration();
        if (duration > 0) {
            ((NativeItem) this.g).setDuration(duration);
            if (this.j != null) {
                this.j.a(CommonUtil.a(duration / 1000));
            }
        }
        this.f.onMediaItemDisplayed(this.g);
        if (this.e.getSkipOffset(this.g.getDuration().intValue()) == -1) {
            this.b = true;
            if (this.j != null) {
                this.j.b();
            } else {
                this.f.onMediaItemSkipEnabled(this.g);
            }
            if (this.a != null) {
                this.a.setVisibility(0);
            }
        }
    }

    public final void onAudioFocusChange(int i) {
        if (this.g.getItemType() == ITEM_TYPE.AUDIO_BANNER) {
            if (i == -2) {
                g();
            } else if (i != 1 && i == -1) {
                g();
            }
        }
    }

    private void h() {
        try {
            this.m.requestAudioFocus(this, 3, 1);
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    private void i() {
        this.i.scheduleWithFixedDelay(new aj(this), 1000, 1000, TimeUnit.MILLISECONDS);
    }

    private void j() {
        if (isPlaying()) {
            int duration = getDuration() / 1000;
            int currentPosition = getCurrentPosition() / 1000;
            if (this.j != null) {
                this.j.a(CommonUtil.a(duration - currentPosition));
            }
            if (currentPosition == this.e.getSkipOffset(getDuration())) {
                this.b = true;
                if (this.j != null) {
                    this.j.b();
                }
                this.f.onMediaItemSkipEnabled(this.g);
                if (this.a != null) {
                    this.a.setVisibility(0);
                }
            }
            n.a(this.e.getVastProgressEvent(currentPosition), 5, "audio progress tracked.");
            if (currentPosition != 0 && currentPosition == this.e.getStartNotifyTime()) {
                n.a(this.e.getVastTrackingByType(2), 5, "start video tracked.");
            }
            int i = currentPosition * 4;
            if (duration >= i && duration < (currentPosition + 1) * 4) {
                n.a(this.e.getVastTrackingByType(3), 5, "audio Q1 tracked.");
            } else if (duration < currentPosition * 2 || duration >= (currentPosition + 1) * 2) {
                duration *= 3;
                if (duration >= i && duration < (currentPosition + 1) * 4) {
                    n.a(this.e.getVastTrackingByType(5), 5, "audio Q3 tracked.");
                }
            } else {
                n.a(this.e.getVastTrackingByType(4), 5, "audio Q-mid tracked.");
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void b() {
        n.a(this.e.getVastTrackingByType(8), 5, "audio skip tracked.");
    }

    private void k() {
        List vastTrackingByType;
        this.k = COLOMBIA_PLAYER_STATE.STARTED;
        start();
        if (this.g.getItemType() == ITEM_TYPE.AUDIO) {
            ((NativeItem) this.g).getItemResponse().recordItemResponseImpression(null);
        }
        ArrayList arrayList = new ArrayList();
        if (this.e.getStartNotifyTime() == 0) {
            vastTrackingByType = this.e.getVastTrackingByType(2);
            if (vastTrackingByType != null) {
                arrayList.addAll(vastTrackingByType);
            }
        }
        vastTrackingByType = this.e.getVastTrackingByType(1);
        if (vastTrackingByType != null) {
            arrayList.addAll(vastTrackingByType);
        }
        vastTrackingByType = this.e.getImpressionTrackerUrl();
        if (vastTrackingByType != null) {
            arrayList.addAll(vastTrackingByType);
        }
        n.a(arrayList, 5, "audio vast creative & start tracked.");
    }

    public final void c() {
        d();
    }

    private void l() {
        try {
            if (this.k != COLOMBIA_PLAYER_STATE.NULL) {
                if ((this.k == COLOMBIA_PLAYER_STATE.STARTED || this.k == COLOMBIA_PLAYER_STATE.COMPLETED || this.k == COLOMBIA_PLAYER_STATE.PREPARED) && isPlaying()) {
                    stop();
                    this.k = COLOMBIA_PLAYER_STATE.STOPPED;
                }
                reset();
                this.k = COLOMBIA_PLAYER_STATE.IDLE;
                release();
                this.k = COLOMBIA_PLAYER_STATE.END;
                this.k = COLOMBIA_PLAYER_STATE.NULL;
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    public final void d() {
        try {
            if (this.m != null) {
                this.m.abandonAudioFocus(this);
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        } catch (Throwable th) {
            this.m = null;
        }
        this.m = null;
        try {
            if (this.l != null) {
                this.l.b(this.c);
            }
            if (this.h != null) {
                this.h.removeMessages(0);
            }
            if (this.i != null) {
                this.i.shutdown();
            }
            g.a(com.til.colombia.android.commons.a.a.a());
        } catch (Exception e2) {
            Log.a(i.f, "", e2);
        } catch (Throwable th2) {
            this.i = null;
            this.l = null;
            this.h = null;
        }
        this.i = null;
        this.l = null;
        this.h = null;
        setOnPreparedListener(null);
        setOnErrorListener(null);
        setOnCompletionListener(null);
        try {
            if (this.k != COLOMBIA_PLAYER_STATE.NULL) {
                if ((this.k == COLOMBIA_PLAYER_STATE.STARTED || this.k == COLOMBIA_PLAYER_STATE.COMPLETED || this.k == COLOMBIA_PLAYER_STATE.PREPARED) && isPlaying()) {
                    stop();
                    this.k = COLOMBIA_PLAYER_STATE.STOPPED;
                }
                reset();
                this.k = COLOMBIA_PLAYER_STATE.IDLE;
                release();
                this.k = COLOMBIA_PLAYER_STATE.END;
                this.k = COLOMBIA_PLAYER_STATE.NULL;
            }
        } catch (Exception e22) {
            Log.a(i.f, "", e22);
        }
        this.n = null;
    }

    static /* synthetic */ void c(ah ahVar) {
        if (ahVar.isPlaying()) {
            int duration = ahVar.getDuration() / 1000;
            int currentPosition = ahVar.getCurrentPosition() / 1000;
            if (ahVar.j != null) {
                ahVar.j.a(CommonUtil.a(duration - currentPosition));
            }
            if (currentPosition == ahVar.e.getSkipOffset(ahVar.getDuration())) {
                ahVar.b = true;
                if (ahVar.j != null) {
                    ahVar.j.b();
                }
                ahVar.f.onMediaItemSkipEnabled(ahVar.g);
                if (ahVar.a != null) {
                    ahVar.a.setVisibility(0);
                }
            }
            n.a(ahVar.e.getVastProgressEvent(currentPosition), 5, "audio progress tracked.");
            if (currentPosition != 0 && currentPosition == ahVar.e.getStartNotifyTime()) {
                n.a(ahVar.e.getVastTrackingByType(2), 5, "start video tracked.");
            }
            int i = currentPosition * 4;
            if (duration >= i && duration < (currentPosition + 1) * 4) {
                n.a(ahVar.e.getVastTrackingByType(3), 5, "audio Q1 tracked.");
            } else if (duration < currentPosition * 2 || duration >= (currentPosition + 1) * 2) {
                duration *= 3;
                if (duration >= i && duration < (currentPosition + 1) * 4) {
                    n.a(ahVar.e.getVastTrackingByType(5), 5, "audio Q3 tracked.");
                }
            } else {
                n.a(ahVar.e.getVastTrackingByType(4), 5, "audio Q-mid tracked.");
            }
        }
    }
}
