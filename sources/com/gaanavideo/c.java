package com.gaanavideo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import com.exoplayer2.ui.VideoPlayerView;
import com.exoplayer2.upstream.FileDataSource.FileDataSourceException;
import com.gaana.BuildConfig;
import com.gaana.application.GaanaApplication;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaanavideo.b.a;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AudioCapabilities;
import com.google.android.exoplayer2.audio.AudioCapabilitiesReceiver.Listener;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.api.client.http.HttpStatusCodes;
import com.managers.u;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.o;
import com.utilities.Util;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class c implements a, Listener, f {
    private static final CookieManager l = new CookieManager();
    public boolean a = false;
    private String b = null;
    private int c = 0;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private GaanaApplication g = GaanaApplication.getInstance();
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private b m;
    private boolean n;
    private long o;
    private Uri p;
    private int q;
    private String r;
    private int s = -1;
    private WakeLock t = null;

    public void e(boolean z) {
    }

    public boolean a(c cVar, int i, int i2) {
        m e = o.e("LISTENER_KEY_VIDEO_FEED_PLAYER_ACTIVITY");
        if (e != null) {
            e.onError(this, i, i2);
        }
        return false;
    }

    public void a() {
        if (this.c == 0) {
            m e = o.e("LISTENER_KEY_VIDEO_FEED_PLAYER_ACTIVITY");
            if (e != null) {
                e.onCompletion(this);
            }
            this.c++;
        }
    }

    public boolean b() {
        return this.h;
    }

    public void c() {
        this.h = true;
        if (o()) {
            c(false);
            if (l()) {
                h();
            }
            m e = o.e("LISTENER_KEY_VIDEO_FEED_PLAYER_ACTIVITY");
            if (e != null) {
                e.onPrepared(this);
            }
            this.c = 0;
            return;
        }
        c(false);
        b(true);
    }

    public boolean isPlaying() {
        if (this.m != null && this.m.i()) {
            int e = this.m.e();
            if (e == 2 || e == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean d() {
        return this.m != null ? this.m.i() : false;
    }

    public void a(Context context, String str, Object obj, int i, boolean z) {
        this.i = false;
        this.h = false;
        A();
        this.o = 0;
        this.b = str;
        this.p = Uri.parse(str);
        this.s = i;
        this.q = a(this.p, null);
        this.r = "-1";
        a(this.d, obj, z);
    }

    public int e() {
        return this.m != null ? (int) this.m.g() : 0;
    }

    public int f() {
        if (this.m != null) {
            return (int) this.m.f();
        }
        return (int) this.o;
    }

    public int g() {
        return this.m != null ? this.m.h() : 0;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void a(int i) {
        if (this.m != null) {
            this.m.a((long) i);
        }
    }

    public void h() {
        if (this.m != null) {
            this.m.a(false, false);
            y();
        }
    }

    public void i() {
        A();
    }

    public void j() {
        if (this.m != null) {
            this.m.a(true, this.i);
            z();
        }
    }

    public boolean k() {
        return (isPlaying() || m() || l()) ? false : true;
    }

    public boolean l() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public boolean m() {
        return this.f;
    }

    public boolean n() {
        return this.a;
    }

    public void c(boolean z) {
        this.f = z;
    }

    public boolean o() {
        return this.d;
    }

    public void d(boolean z) {
        this.d = z;
        if (this.m != null) {
            this.m.b(z);
        }
    }

    private void z() {
        Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        intent.putExtra("android.media.extra.AUDIO_SESSION", this.m.j());
        GaanaApplication.getContext().sendBroadcast(intent);
    }

    static {
        l.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
        if (this.m != null) {
            boolean i = this.m.i();
            A();
            a(i, null, false);
        }
    }

    private void a(boolean z, Object obj, boolean z2) {
        if (this.m == null) {
            this.m = new b(this.g, this.p);
            this.m.a((a) this);
            this.m.a(this.o);
            this.n = true;
            this.m.a(this.k);
        }
        if (this.n) {
            this.n = false;
        }
        this.m.a(new Uri[]{this.p}, obj, this.s, z2, this.d);
        d(this.d);
        this.m.a(z, false);
    }

    private void A() {
        if (this.m != null) {
            this.o = this.m.f();
            this.m.d();
            this.m = null;
        }
    }

    public void p() {
        i();
    }

    public void q() {
        j();
    }

    public void r() {
        h();
    }

    public String s() {
        return this.b;
    }

    public void a(boolean z, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("playWhenReady=");
        stringBuilder.append(z);
        stringBuilder.append(", playbackState=");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3;
        switch (i) {
            case 1:
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append("idle");
                stringBuilder3.toString();
                return;
            case 2:
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append("buffering");
                stringBuilder3.toString();
                return;
            case 3:
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append("ready");
                stringBuilder3.toString();
                if (!this.h || this.j) {
                    c();
                    return;
                }
                return;
            case 4:
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append("ended");
                stringBuilder3.toString();
                a();
                return;
            default:
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append("unknown");
                stringBuilder3.toString();
                return;
        }
    }

    public void a(Exception exception) {
        int i;
        StringBuilder stringBuilder;
        String stringBuilder2;
        this.n = true;
        this.j = false;
        Throwable cause = exception.getCause();
        CharSequence message = exception.getMessage();
        String str = "Others";
        boolean z = cause instanceof InvalidResponseCodeException;
        int i2 = -1234;
        String str2 = null;
        StringBuilder stringBuilder3;
        if (z) {
            i = ((InvalidResponseCodeException) cause).responseCode;
            if (i == 403 || i == HttpStatusCodes.STATUS_CODE_FOUND) {
                a(this, i, i);
                return;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Server-");
            stringBuilder.append(i);
            message = stringBuilder.toString();
        } else if (exception instanceof InvalidResponseCodeException) {
            i = ((InvalidResponseCodeException) exception).responseCode;
            if (i == 403 || i == HttpStatusCodes.STATUS_CODE_FOUND) {
                a(this, i, i);
                return;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Server-");
            stringBuilder.append(i);
            message = stringBuilder.toString();
        } else if (cause instanceof DecoderInitializationException) {
            a(this, 4567, 4567);
            return;
        } else if ((cause instanceof HttpDataSourceException) || (cause instanceof UnknownHostException) || (cause instanceof SocketException) || (cause instanceof SocketTimeoutException)) {
            String str3;
            i = f();
            if (i <= 0) {
                str3 = "Network Failure - Start";
            } else {
                str2 = Util.a(i / 1000);
                str3 = "Network Failure - Between";
            }
            i2 = SsoErrorCodes.NO_INTERNET_CONNECTION;
            if (!Util.j(this.g)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str3);
                stringBuilder.append(" - No Internet");
                str3 = stringBuilder.toString();
            }
            Object message2 = str3;
            str = "Network Failure";
        } else if (cause instanceof FileDataSourceException) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Media Player - ");
            stringBuilder3.append(exception.getMessage());
            message2 = stringBuilder3.toString();
            str = "Media Player";
        } else if (cause instanceof UnrecognizedInputFormatException) {
            a(this, 9876, 9876);
            return;
        } else if (cause instanceof BehindLiveWindowException) {
            boolean i3 = this.m.i();
            A();
            a(i3, null, false);
            return;
        } else if ((cause instanceof IllegalStateException) || (exception instanceof IllegalStateException)) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Media Player - ");
            stringBuilder3.append(exception.getMessage());
            message2 = stringBuilder3.toString();
            str = "Media Player";
        }
        i = i2;
        if (!(z || (cause instanceof ParserException) || (exception instanceof InvalidResponseCodeException) || (exception instanceof ParserException))) {
            Util.b(this.b, str, exception.getMessage());
        }
        StringBuilder stringBuilder4;
        if (TextUtils.isEmpty(message2)) {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Buffer not fetched - ");
            stringBuilder4.append(cause.toString());
            stringBuilder2 = stringBuilder4.toString();
        } else {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Buffer not fetched - ");
            stringBuilder4.append(message2);
            stringBuilder2 = stringBuilder4.toString();
        }
        String O = Util.O();
        if (str2 != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(O);
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            O = stringBuilder.toString();
        }
        u.a().a("VideoStreamingFailure", stringBuilder2, O);
        a(this, i2, i);
    }

    private static int a(Uri uri, String str) {
        String lastPathSegment;
        if (TextUtils.isEmpty(str)) {
            lastPathSegment = uri.getLastPathSegment();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(".");
            stringBuilder.append(str);
            lastPathSegment = stringBuilder.toString();
        }
        if (lastPathSegment == null) {
            return 3;
        }
        if (lastPathSegment.endsWith(".m3u8")) {
            return 2;
        }
        if (lastPathSegment.endsWith(".mpd")) {
            return 0;
        }
        if (lastPathSegment.endsWith(".ism")) {
            return 1;
        }
        return 3;
    }

    public int getAudioSessionId() {
        return this.m != null ? this.m.j() : 0;
    }

    public int t() {
        return g();
    }

    public void setVolume(float f, float f2) {
        if (this.m != null) {
            this.m.a(f);
        }
    }

    public int u() {
        return e();
    }

    public int v() {
        return f();
    }

    public void b(int i) {
        a(i);
    }

    public void w() {
        y();
        A();
    }

    public void x() {
        PowerManager powerManager = (PowerManager) this.g.getSystemService("power");
        if (this.t == null) {
            this.t = powerManager.newWakeLock(1, c.class.getName());
            this.t.setReferenceCounted(false);
        }
        if (!this.t.isHeld()) {
            this.t.acquire();
        }
    }

    public void y() {
        if (this.t != null && this.t.isHeld()) {
            this.t.release();
            this.t = null;
        }
    }

    public void a(VideoPlayerView videoPlayerView) {
        if (videoPlayerView != null && this.m != null) {
            videoPlayerView.setPlayer(this.m.k());
        }
    }

    public void f(boolean z) {
        this.k = z;
        if (this.m != null) {
            this.m.a(z);
        }
    }
}
