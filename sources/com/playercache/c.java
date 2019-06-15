package com.playercache;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import com.exoplayer2.upstream.FileDataSource.FileDataSourceException;
import com.gaana.BuildConfig;
import com.gaana.application.GaanaApplication;
import com.gaana.login.sso.SsoErrorCodes;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AudioCapabilities;
import com.google.android.exoplayer2.audio.AudioCapabilitiesReceiver.Listener;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.api.client.http.HttpStatusCodes;
import com.managers.u;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.o;
import com.playercache.a.a;
import com.utilities.Util;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class c implements Listener, f, a {
    private static final CookieManager m = new CookieManager();
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
    private boolean l = false;
    private a n;
    private boolean o;
    private long p;
    private Uri q;
    private int r;
    private String s;
    private int t = -1;
    private WakeLock u = null;

    public boolean a(c cVar, int i, int i2) {
        m f = o.f("LISTENER_KEY_MUSIC_CACHE_SERVICE");
        if (f != null) {
            f.onError(this, i, i2);
        }
        return false;
    }

    public void a() {
        j();
        if (this.c == 0) {
            m f = o.f("LISTENER_KEY_MUSIC_CACHE_SERVICE");
            if (f != null) {
                f.onCompletion(this);
            }
            this.c++;
        }
    }

    public void b() {
        this.h = true;
        m f = o.f("LISTENER_KEY_MUSIC_CACHE_SERVICE");
        if (f != null) {
            f.onPrepared(this);
        }
    }

    public boolean isPlaying() {
        if (this.n != null && this.n.g()) {
            int c = this.n.c();
            if (c == 2 || c == 3) {
                return true;
            }
        }
        return false;
    }

    public void a(Context context, String str, Object obj, int i, boolean z) {
        this.i = false;
        this.h = false;
        o();
        this.p = 0;
        this.b = str;
        this.q = Uri.parse(str);
        this.t = i;
        this.r = a(this.q, null);
        this.s = "-1";
        a(this.d, obj, z);
    }

    public int c() {
        return this.n != null ? (int) this.n.e() : 0;
    }

    public int d() {
        if (this.n != null) {
            return (int) this.n.d();
        }
        return (int) this.p;
    }

    public int e() {
        return this.n != null ? this.n.f() : 0;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void a(int i) {
        if (this.n != null) {
            this.n.a((long) i);
        }
    }

    public void f() {
        if (this.n != null) {
            this.n.a(false, false);
            y();
        }
    }

    public void g() {
        o();
    }

    public void h() {
        if (this.n != null) {
            this.n.a(true, this.i);
            i();
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

    public void d(boolean z) {
        this.d = z;
        if (this.n != null) {
            this.n.a(z);
        }
    }

    public void e(boolean z) {
        this.l = z;
        if (this.n != null) {
            this.n.b(z);
        }
    }

    private void i() {
        Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        intent.putExtra("android.media.extra.AUDIO_SESSION", this.n.h());
        GaanaApplication.getContext().sendBroadcast(intent);
    }

    private void j() {
        Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        intent.putExtra("android.media.extra.AUDIO_SESSION", getAudioSessionId());
        GaanaApplication.getContext().sendBroadcast(intent);
    }

    static {
        m.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
        if (this.n != null) {
            boolean g = this.n.g();
            o();
            a(g, null, false);
        }
    }

    private void a(boolean z, Object obj, boolean z2) {
        if (this.n == null) {
            this.n = new a(this.g, this.q);
            this.n.c(this.k);
            this.n.a((a) this);
            this.n.a(this.p);
            this.o = true;
        }
        if (this.o) {
            this.o = false;
        }
        this.n.b(this.l);
        this.n.a(new Uri[]{this.q}, obj, this.t, z2, this.d);
        d(this.d);
        this.n.a(z, false);
    }

    private void o() {
        if (this.n != null) {
            this.p = this.n.d();
            this.n.b();
            this.n = null;
        }
    }

    public void p() {
        g();
    }

    public void q() {
        h();
    }

    public void r() {
        f();
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
                    b();
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
        this.o = true;
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
        } else if ((cause instanceof HttpDataSourceException) || (cause instanceof UnknownHostException) || (cause instanceof SocketException) || (cause instanceof SocketTimeoutException)) {
            String str3;
            i = d();
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
            boolean g = this.n.g();
            o();
            a(g, null, false);
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
        String P = Util.P();
        if (str2 != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(P);
            stringBuilder.append(" ");
            stringBuilder.append(str2);
            P = stringBuilder.toString();
        }
        u.a().a("AdvancedStreamingFailure", stringBuilder2, P);
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
        return this.n != null ? this.n.h() : 0;
    }

    public int t() {
        return e();
    }

    public void setVolume(float f, float f2) {
        if (this.n != null) {
            this.n.a(f);
        }
    }

    public int u() {
        return c();
    }

    public int v() {
        return d();
    }

    public void b(int i) {
        a(i);
    }

    public void w() {
        y();
        o();
    }

    public void x() {
        PowerManager powerManager = (PowerManager) this.g.getSystemService("power");
        if (this.u == null) {
            this.u = powerManager.newWakeLock(1, c.class.getName());
            this.u.setReferenceCounted(false);
        }
        if (!this.u.isHeld()) {
            this.u.acquire();
        }
    }

    public void y() {
        if (this.u != null && this.u.isHeld()) {
            this.u.release();
            this.u = null;
        }
    }
}
