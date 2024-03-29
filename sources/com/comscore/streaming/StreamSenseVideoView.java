package com.comscore.streaming;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.VideoView;
import com.comscore.utils.CSLog;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Timer;

@Deprecated
public class StreamSenseVideoView extends VideoView {
    private final String a = "StreamSenseVideoView";
    private final boolean b = true;
    private final int c = 500;
    private final int d = 500;
    private long e = -1;
    private StreamSense f = null;
    private String g = "0x0";
    private long h = 0;
    private long i = -1;
    private String j;
    private boolean k = false;
    private Timer l = null;
    private Timer m = null;
    private OnCompletionListener n = null;
    private final OnCompletionListener o = new o(this);

    public StreamSenseVideoView(Context context) {
        super(context);
        h();
    }

    public StreamSenseVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        h();
    }

    public StreamSenseVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        h();
    }

    private void a(MediaPlayer mediaPlayer) {
        CSLog.d((Object) "StreamSenseVideoView", "onPlaybackCompleted");
        c(i(), getCurrentPlayerSafePosition());
    }

    private synchronized void a(HashMap<String, String> hashMap) {
        f();
        if (this.l == null) {
            long currentPlayerSafePosition = getCurrentPlayerSafePosition();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startStartTimer:");
            stringBuilder.append(currentPlayerSafePosition);
            CSLog.d((Object) "StreamSenseVideoView", stringBuilder.toString());
            this.l = new Timer();
            this.l.schedule(new m(this, currentPlayerSafePosition, hashMap), 500);
        }
    }

    private void a(HashMap<String, String> hashMap, long j) {
        f();
        d();
        if (this.f != null) {
            this.f.notify(StreamSenseEventType.END, hashMap, j);
        }
    }

    private boolean a() {
        try {
            return isPlaying();
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    private void b(HashMap<String, String> hashMap, long j) {
        this.k = false;
        d();
        if (this.f != null) {
            this.f.notify(StreamSenseEventType.PLAY, hashMap, j);
        }
    }

    private boolean b() {
        return this.k;
    }

    private void c() {
        CSLog.d((Object) "StreamSenseVideoView", "onPauseForBuffering");
        this.k = true;
        this.i = System.currentTimeMillis();
        if (a()) {
            c(i(), getCurrentPlayerSafePosition());
            e();
        }
    }

    private void c(HashMap<String, String> hashMap, long j) {
        if (!d() && this.f != null) {
            this.f.notify(StreamSenseEventType.PAUSE, hashMap, j);
        }
    }

    private synchronized boolean d() {
        boolean z;
        if (this.l != null) {
            CSLog.d((Object) "StreamSenseVideoView", "cancelStartTimer");
            this.l.cancel();
            this.l = null;
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private void e() {
        a(i());
    }

    private synchronized boolean f() {
        boolean z;
        CSLog.d((Object) "StreamSenseVideoView", "cancelPlayingPollTimer()");
        if (this.m != null) {
            this.m.cancel();
            this.m = null;
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private synchronized void g() {
        if (this.m == null) {
            CSLog.d((Object) "StreamSenseVideoView", "startPlayingPollTimer");
            long currentPlayerSafePosition = getCurrentPlayerSafePosition();
            this.m = new Timer();
            this.m.schedule(new n(this, currentPlayerSafePosition), 250);
        }
    }

    private long getCurrentPlayerSafePosition() {
        try {
            return (long) getCurrentPosition();
        } catch (IllegalStateException unused) {
            CSLog.e((Object) "StreamSenseVideoView", "getCurrentSafePlayerPosition");
            return 0;
        }
    }

    private HashMap<String, String> getPlayerMetadata() {
        HashMap hashMap = new HashMap();
        if (this.e <= 0) {
            this.e = (long) getDuration();
        }
        hashMap.put("ns_st_cl", String.valueOf(this.e));
        if (this.g == null || this.g.equals("0x0")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getWidth());
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(getHeight());
            this.g = stringBuilder.toString();
        }
        hashMap.put("ns_st_cs", this.g);
        hashMap.put("ns_st_cu", this.j);
        hashMap.put("ns_st_mp", StreamSenseVideoView.class.getSimpleName());
        hashMap.put("ns_st_mv", Integer.toString(VERSION.SDK_INT));
        return hashMap;
    }

    private void h() {
        super.setOnCompletionListener(this.o);
    }

    private HashMap<String, String> i() {
        HashMap hashMap = new HashMap();
        hashMap.put("ns_ts", String.valueOf(System.currentTimeMillis()));
        hashMap.putAll(getPlayerMetadata());
        return hashMap;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CSLog.d((Object) "StreamSenseVideoView", "onDetachedFromWindow");
        a(i(), getCurrentPlayerSafePosition());
    }

    public void pause() {
        super.pause();
        CSLog.d((Object) "StreamSenseVideoView", "pause");
        f();
        if (this.k) {
            this.h += System.currentTimeMillis() - this.i;
        }
        this.k = false;
        this.i = -1;
        c(i(), getCurrentPlayerSafePosition());
    }

    @TargetApi(8)
    public void resume() {
        if (VERSION.SDK_INT >= 8) {
            super.resume();
        }
        CSLog.d((Object) "StreamSenseVideoView", "resume");
        b(i(), getCurrentPlayerSafePosition());
    }

    public void seekTo(int i) {
        super.seekTo(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("seekTo:");
        stringBuilder.append(i);
        CSLog.d((Object) "StreamSenseVideoView", stringBuilder.toString());
        if (a()) {
            f();
            d();
            c(i(), -1);
            e();
        }
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        super.setOnCompletionListener(this.o);
        this.n = onCompletionListener;
    }

    public void setStreamSense(StreamSense streamSense) {
        this.f = streamSense;
        this.f.setLabel("ns_st_pv", "4.1307.02");
    }

    public void setVideoPath(String str) {
        super.setVideoPath(str);
        this.j = str;
    }

    public void setVideoURI(Uri uri) {
        super.setVideoURI(uri);
        this.j = uri.toString();
    }

    public void start() {
        super.start();
        if (this.l == null) {
            CSLog.d((Object) "StreamSenseVideoView", "start");
            e();
        }
    }

    public void stopPlayback() {
        super.stopPlayback();
        CSLog.d((Object) "StreamSenseVideoView", "stopPlayback");
        a(i(), getCurrentPlayerSafePosition());
    }
}
