package com.player_framework;

import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import com.cast_music.VideoCastManager;
import com.cast_music.a.d;
import com.cast_music.exceptions.CastException;
import com.cast_music.exceptions.NoConnectionException;
import com.cast_music.exceptions.TransientNetworkDisconnectionException;
import com.constants.Constants;
import com.gaana.BuildConfig;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Artist;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.PlayerManager;
import com.managers.ad;
import com.player_framework.PlayerConstants.PauseReasons;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements f {
    private static boolean q;
    private final d a = new d() {
        public void onRemoteMediaPlayerMetadataUpdated() {
            b.this.j();
        }

        public void onRemoteMediaPlayerStatusUpdated() {
            b.this.o();
        }
    };
    private int b;
    private volatile int c;
    private volatile String d;
    private String e;
    private VideoCastManager f = VideoCastManager.y();
    private Track g;
    private boolean h;
    private boolean i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private WakeLock o;
    private int p;
    private boolean r;

    public void a(boolean z) {
    }

    public void e(boolean z) {
    }

    public int getAudioSessionId() {
        return 0;
    }

    public void a() {
        this.f.a(this.a);
        if (this.m) {
            this.m = false;
            this.b = 3;
            try {
                this.f.L();
            } catch (CastException e) {
                ThrowableExtension.printStackTrace(e);
            } catch (TransientNetworkDisconnectionException e2) {
                ThrowableExtension.printStackTrace(e2);
            } catch (NoConnectionException e3) {
                ThrowableExtension.printStackTrace(e3);
            }
        }
    }

    public void b() {
        this.r = false;
        this.f.b(this.a);
        this.b = 1;
    }

    public int c() {
        if (!this.f.f()) {
            return this.c;
        }
        try {
            return (int) this.f.K();
        } catch (NoConnectionException | TransientNetworkDisconnectionException unused) {
            return -1;
        }
    }

    public void a(int i) {
        this.p = i;
    }

    public void d() {
        this.c = c();
    }

    public void a(String str) {
        this.e = str;
        q = false;
        if (ad.a(GaanaApplication.getContext()).o().booleanValue()) {
            this.g = ad.a(GaanaApplication.getContext()).i();
            q = true;
        } else {
            this.g = PlayerManager.a(GaanaApplication.getContext()).i().b();
        }
        try {
            a(this.g, true, str);
            this.b = 6;
        } catch (NoConnectionException | TransientNetworkDisconnectionException | IllegalArgumentException | JSONException unused) {
        }
    }

    public void e() {
        try {
            if (this.f.E()) {
                this.f.M();
                this.c = (int) this.f.K();
                this.b = 2;
                return;
            }
            a(this.g, false, this.e);
        } catch (CastException | NoConnectionException | TransientNetworkDisconnectionException | IllegalArgumentException | JSONException unused) {
        }
    }

    public void c(int i) {
        try {
            if (this.f.E()) {
                this.f.g(i);
                this.c = i;
                return;
            }
            this.c = i;
            a(this.g, false, this.e);
        } catch (NoConnectionException | TransientNetworkDisconnectionException | IllegalArgumentException | JSONException unused) {
        }
    }

    public int u() {
        try {
            return (int) this.f.I();
        } catch (TransientNetworkDisconnectionException e) {
            ThrowableExtension.printStackTrace(e);
            return 0;
        } catch (NoConnectionException e2) {
            ThrowableExtension.printStackTrace(e2);
            return 0;
        }
    }

    public int v() {
        this.c = c();
        return this.c;
    }

    public void b(int i) {
        c(i);
    }

    public void w() {
        y();
        b();
    }

    public void p() {
        e();
        b();
    }

    public void q() {
        a();
    }

    public void r() {
        e();
    }

    public int t() {
        return this.f.A() != null ? (int) this.f.A().getStreamDuration() : 0;
    }

    public String s() {
        return this.e;
    }

    public void setVolume(float f, float f2) {
        this.a.onVolumeChanged((double) f, false);
    }

    public void c(boolean z) {
        this.l = z;
    }

    public boolean isPlaying() {
        boolean z = false;
        try {
            if (this.f.f() && this.f.C()) {
                z = true;
            }
            return z;
        } catch (NoConnectionException | TransientNetworkDisconnectionException unused) {
            return false;
        }
    }

    public void d(boolean z) {
        this.i = z;
    }

    public void a(Context context, String str, Object obj, int i, boolean z) {
        this.h = false;
        this.r = false;
        if (this.p > 0) {
            this.c = this.p;
            this.p = 0;
        } else {
            this.c = 0;
        }
        a();
        a(str);
    }

    public void b(boolean z) {
        this.m = z;
    }

    public boolean m() {
        return this.l;
    }

    public boolean n() {
        return this.k;
    }

    public boolean l() {
        return this.m;
    }

    public boolean k() {
        return this.n;
    }

    public void x() {
        PowerManager powerManager = (PowerManager) GaanaApplication.getInstance().getSystemService("power");
        if (this.o == null) {
            this.o = powerManager.newWakeLock(1, b.class.getName());
            this.o.setReferenceCounted(false);
        }
        if (!this.o.isHeld()) {
            this.o.acquire();
        }
    }

    public void y() {
        if (this.o != null && this.o.isHeld()) {
            this.o.release();
            this.o = null;
        }
    }

    private void a(Track track, boolean z, String str) throws TransientNetworkDisconnectionException, NoConnectionException, JSONException {
        if (track != null) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(bm.b, track.getBusinessObjId());
            this.f.a(a(track, jSONObject, str), z, this.c, jSONObject);
        }
    }

    private static MediaInfo a(Track track, JSONObject jSONObject, String str) {
        String str2;
        MediaMetadata mediaMetadata = new MediaMetadata(3);
        String str3 = MediaMetadata.KEY_TITLE;
        if (track.getEnglishName() == null) {
            str2 = "";
        } else {
            str2 = track.getEnglishName();
        }
        mediaMetadata.putString(str3, str2);
        str3 = MediaMetadata.KEY_SUBTITLE;
        if (track.getEnglishName() == null) {
            str2 = "";
        } else {
            str2 = track.getEnglishName();
        }
        mediaMetadata.putString(str3, str2);
        mediaMetadata.putString(MediaMetadata.KEY_ALBUM_ARTIST, a(track.getArtists()));
        mediaMetadata.putString(MediaMetadata.KEY_ALBUM_TITLE, track.getAlbumTitle() == null ? "" : track.getAlbumTitle());
        WebImage webImage = new WebImage(new Builder().encodedPath(track.getArtworkLarge()).build());
        mediaMetadata.addImage(webImage);
        mediaMetadata.addImage(webImage);
        String str4 = MimeTypes.AUDIO_MPEG;
        if (str != null && str.contains(".m3u8")) {
            str4 = "application/x-mpegurl";
        }
        return new MediaInfo.Builder(str).setContentType(str4).setStreamType(q ? 2 : 1).setMetadata(mediaMetadata).setCustomData(jSONObject).build();
    }

    private void j() {
        try {
            MediaInfo F = this.f.F();
            if (F != null) {
                JSONObject customData = F.getCustomData();
                if (customData != null && customData.has(bm.b)) {
                    String string = customData.getString(bm.b);
                    if (!TextUtils.equals(this.d, string)) {
                        this.d = string;
                        d();
                    }
                }
            }
        } catch (NoConnectionException | TransientNetworkDisconnectionException | JSONException unused) {
        }
    }

    public boolean f() {
        return this.b == 3 || this.b == 6;
    }

    private void o() {
        this.n = false;
        int O = this.f.O();
        int P = this.f.P();
        switch (O) {
            case 1:
                this.n = true;
                if (P == 1) {
                    h();
                    return;
                }
                return;
            case 2:
                this.b = 3;
                j();
                this.m = false;
                if (this.r) {
                    o.c(GaanaApplication.getContext(), PauseReasons.MEDIA_BUTTON_TAP);
                    this.r = false;
                }
                g();
                return;
            case 3:
                if (this.b == 3) {
                    this.r = true;
                    o.a(GaanaApplication.getContext(), PauseReasons.MEDIA_BUTTON_TAP);
                }
                this.b = 2;
                j();
                return;
            case 4:
                this.b = 6;
                return;
            default:
                return;
        }
    }

    public void g() {
        this.h = true;
        if (i()) {
            z();
            c(false);
            if (l()) {
                e();
            }
            for (m mVar : o.b().values()) {
                if (mVar != null) {
                    mVar.onPrepared(this);
                }
            }
            this.j = 0;
            return;
        }
        c(false);
        b(true);
    }

    public void h() {
        A();
        if (this.j == 0) {
            for (m mVar : o.b().values()) {
                if (mVar != null) {
                    mVar.onCompletion(this);
                }
            }
            this.j++;
        }
    }

    private void z() {
        Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        intent.putExtra("android.media.extra.AUDIO_SESSION", getAudioSessionId());
        GaanaApplication.getContext().sendBroadcast(intent);
    }

    private void A() {
        Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        intent.putExtra("android.media.extra.AUDIO_SESSION", getAudioSessionId());
        GaanaApplication.getContext().sendBroadcast(intent);
    }

    public boolean i() {
        return this.i;
    }

    private static String a(ArrayList<Artist> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Artist artist = (Artist) it.next();
                if (stringBuilder.length() == 0) {
                    stringBuilder.append(Constants.a(artist.name));
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(", ");
                    stringBuilder2.append(Constants.a(artist.name));
                    stringBuilder.append(stringBuilder2.toString());
                }
            }
        }
        return stringBuilder.toString();
    }
}
