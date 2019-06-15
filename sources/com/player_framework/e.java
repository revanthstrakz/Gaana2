package com.player_framework;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import com.gaana.BuildConfig;
import com.gaana.application.GaanaApplication;
import com.managers.ad;
import com.managers.u;
import com.utilities.Util;

public class e extends MediaPlayer implements f {
    public boolean a = false;
    OnInfoListener b = new OnInfoListener() {
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            for (m mVar : o.b().values()) {
                if (mVar != null) {
                    mVar.onInfo(e.this, i, i2);
                }
            }
            return false;
        }
    };
    OnErrorListener c = new OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            if (Util.j(GaanaApplication.getContext())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Buffer not fetched - Media Player Error Code:");
                stringBuilder.append(i);
                stringBuilder.append("-");
                stringBuilder.append(i2);
                u.a().a("StreamingFailure ", stringBuilder.toString(), Util.P());
            } else {
                u.a().a("StreamingFailure ", "Buffer not fetched - Network Failure", Util.P());
            }
            for (m mVar : o.b().values()) {
                if (mVar != null) {
                    mVar.onError(e.this, i, i2);
                }
            }
            e.this.reset();
            return false;
        }
    };
    OnCompletionListener d = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            e.this.c();
            if (e.this.h == 0) {
                for (m mVar : o.b().values()) {
                    if (mVar != null) {
                        mVar.onCompletion(e.this);
                    }
                }
                e.this.h = e.this.h + 1;
            }
        }
    };
    OnBufferingUpdateListener e = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            if (e.this.a()) {
                for (m mVar : o.b().values()) {
                    if (mVar != null) {
                        mVar.onBufferingUpdate(e.this, i);
                    }
                }
            }
        }
    };
    OnPreparedListener f = new OnPreparedListener() {
        public void onPrepared(MediaPlayer mediaPlayer) {
            e.this.m = true;
            if (e.this.a()) {
                e.this.c(false);
                e.this.start();
                if (e.this.l()) {
                    e.this.pause();
                }
                for (m mVar : o.b().values()) {
                    if (mVar != null) {
                        mVar.onPrepared(e.this);
                    }
                }
                e.this.h = 0;
                return;
            }
            e.this.c(false);
            e.this.b(true);
        }
    };
    private String g = null;
    private int h = 0;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private GaanaApplication l;
    private boolean m = false;

    public void a(boolean z) {
    }

    public void e(boolean z) {
    }

    public int t() {
        return 0;
    }

    public void y() {
    }

    public e() {
        setAudioStreamType(3);
    }

    public boolean isPlaying() {
        try {
            return super.isPlaying();
        } catch (Exception unused) {
            return false;
        }
    }

    public void setWakeMode(Context context, int i) {
        super.setWakeMode(context, i);
    }

    public void a(Context context, String str, Object obj, int i, boolean z) throws IllegalArgumentException, SecurityException {
        setOnCompletionListener(null);
        setOnPreparedListener(null);
        setOnErrorListener(null);
        setOnInfoListener(null);
        setOnBufferingUpdateListener(null);
        setOnSeekCompleteListener(null);
        this.m = false;
        this.g = str;
        try {
            if (!PlayerStatus.a(context).e() && isPlaying()) {
                stop();
            }
            if (a()) {
                reset();
                this.l = (GaanaApplication) context.getApplicationContext();
                if (ad.a(this.l).o().booleanValue()) {
                    setDataSource(context, Uri.parse(this.g));
                } else {
                    setDataSource(this.g);
                }
            }
            setOnErrorListener(this.c);
            setOnInfoListener(this.b);
            setOnPreparedListener(this.f);
            setOnCompletionListener(this.d);
            setOnBufferingUpdateListener(this.e);
            setOnSeekCompleteListener(new OnSeekCompleteListener() {
                public void onSeekComplete(MediaPlayer mediaPlayer) {
                }
            });
            prepareAsync();
        } catch (IllegalStateException unused) {
            if (this.c != null) {
                this.c.onError(this, -1000, 0);
            }
        } catch (Exception unused2) {
            if (this.c != null) {
                this.c.onError(this, -1001, 0);
            }
        }
    }

    public int getDuration() {
        if (this != null && this.m) {
            try {
                return super.getDuration();
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public int getCurrentPosition() {
        if (this != null && this.m) {
            try {
                return super.getCurrentPosition();
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public void setVolume(float f, float f2) {
        if (this != null && this.m) {
            try {
                super.setVolume(f, f2);
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void seekTo(int i) {
        if (this != null && this.m) {
            try {
                super.seekTo(i);
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void reset() {
        this.m = false;
        try {
            super.reset();
        } catch (Exception unused) {
        }
    }

    public void pause() {
        if (this != null && this.m) {
            try {
                super.pause();
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void stop() {
        if (this != null && this.m) {
            try {
                super.stop();
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void start() {
        if (this != null && this.m) {
            try {
                super.start();
                b();
            } catch (IllegalStateException unused) {
            }
        }
    }

    public boolean k() {
        return (isPlaying() || m() || l()) ? false : true;
    }

    public void x() {
        setWakeMode(GaanaApplication.getContext(), 1);
    }

    public boolean l() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public boolean m() {
        return this.k;
    }

    public boolean n() {
        return this.a;
    }

    public void c(boolean z) {
        this.k = z;
    }

    public boolean a() {
        return this.i;
    }

    public void d(boolean z) {
        this.i = z;
    }

    private void b() {
        Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        intent.putExtra("android.media.extra.AUDIO_SESSION", getAudioSessionId());
        GaanaApplication.getContext().sendBroadcast(intent);
    }

    private void c() {
        Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        intent.putExtra("android.media.extra.AUDIO_SESSION", getAudioSessionId());
        GaanaApplication.getContext().sendBroadcast(intent);
    }

    public int u() {
        return getDuration();
    }

    public int v() {
        return getCurrentPosition();
    }

    public void b(int i) {
        seekTo(i);
    }

    public void w() {
        release();
    }

    public void p() {
        stop();
    }

    public void q() {
        start();
    }

    public void r() {
        pause();
    }

    public String s() {
        return this.g;
    }
}
