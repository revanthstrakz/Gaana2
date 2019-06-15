package com.cast_music;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Callback;
import android.support.v4.media.session.PlaybackStateCompat.Builder;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.CaptioningManager.CaptionStyle;
import android.view.accessibility.CaptioningManager.CaptioningChangeListener;
import com.cast_music.a.c;
import com.cast_music.b.d;
import com.cast_music.exceptions.CastException;
import com.cast_music.exceptions.NoConnectionException;
import com.cast_music.exceptions.TransientNetworkDisconnectionException;
import com.facebook.share.internal.ShareConstants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.Cast.CastOptions;
import com.google.android.gms.cast.Cast.Listener;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult;
import com.google.android.gms.cast.RemoteMediaPlayer.OnMetadataUpdatedListener;
import com.google.android.gms.cast.RemoteMediaPlayer.OnPreloadStatusUpdatedListener;
import com.google.android.gms.cast.RemoteMediaPlayer.OnQueueStatusUpdatedListener;
import com.google.android.gms.cast.RemoteMediaPlayer.OnStatusUpdatedListener;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.images.WebImage;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class VideoCastManager extends a implements com.cast_music.exceptions.a {
    private static VideoCastManager E;
    public static final long s = TimeUnit.HOURS.toMillis(2);
    private static final String t = com.cast_music.b.b.a(VideoCastManager.class);
    private static final long u = TimeUnit.SECONDS.toMillis(1);
    private Timer A;
    private b B;
    private com.cast_music.b.a C;
    private com.cast_music.b.a D;
    private Class<?> F;
    private AudioManager G;
    private RemoteMediaPlayer H;
    private MediaSessionCompat I;
    private VolumeType J = VolumeType.DEVICE;
    private int K = 1;
    private int L;
    private String M;
    private MessageReceivedCallback N;
    private final Set<c> O = new CopyOnWriteArraySet();
    private final Set<com.cast_music.tracks.a> P = new CopyOnWriteArraySet();
    private final Set<e> Q = new CopyOnWriteArraySet();
    private long R = s;
    private MediaQueueItem S;
    private Class<? extends Service> v;
    private double w = 0.05d;
    private com.cast_music.tracks.b x;
    private d y;
    private MediaStatus z;

    public enum VolumeType {
        STREAM,
        DEVICE
    }

    private class b extends TimerTask {
        private b() {
        }

        /* synthetic */ b(VideoCastManager videoCastManager, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void run() {
            if (VideoCastManager.this.K != 4 && VideoCastManager.this.f() && VideoCastManager.this.H != null) {
                try {
                    int I = (int) VideoCastManager.this.I();
                    if (I > 0) {
                        VideoCastManager.this.a((int) VideoCastManager.this.K(), I);
                    }
                } catch (NoConnectionException | TransientNetworkDisconnectionException e) {
                    com.cast_music.b.b.a(VideoCastManager.t, "Failed to update the progress tracker due to network issues", e);
                }
            }
        }
    }

    class a extends Listener {
        a() {
        }

        public void onApplicationDisconnected(int i) {
            VideoCastManager.this.h(i);
        }

        public void onApplicationStatusChanged() {
            VideoCastManager.this.X();
        }

        public void onVolumeChanged() {
            VideoCastManager.this.Y();
        }
    }

    private VideoCastManager() {
    }

    protected VideoCastManager(Context context, b bVar) {
        String str;
        super(context, bVar);
        com.cast_music.b.b.a(t, "VideoCastManager is instantiated");
        if (bVar.c() == null) {
            str = null;
        } else {
            str = (String) bVar.c().get(0);
        }
        this.M = str;
        this.F = GaanaActivity.class;
        this.h.a("cast-activity-name", this.F.getName());
        if (!TextUtils.isEmpty(this.M)) {
            this.h.a("cast-custom-data-namespace", this.M);
        }
        this.G = (AudioManager) this.b.getSystemService("audio");
        this.v = bVar.f();
    }

    public static synchronized VideoCastManager a(Context context, b bVar) {
        VideoCastManager videoCastManager;
        synchronized (VideoCastManager.class) {
            if (E == null) {
                com.cast_music.b.b.a(t, "New instance of VideoCastManager is created");
                if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0) {
                    com.cast_music.b.b.b(t, context.getString(R.string.google_services_not_available));
                }
                E = new VideoCastManager(context, bVar);
                E.aj();
            }
            E.z();
            videoCastManager = E;
        }
        return videoCastManager;
    }

    public static VideoCastManager y() {
        if (E != null) {
            return E;
        }
        String str = "No VideoCastManager instance was found, did you forget to initialize it?";
        com.cast_music.b.b.b(t, str);
        throw new IllegalStateException(str);
    }

    /* Access modifiers changed, original: protected */
    public void z() {
        if (c(16)) {
            this.x = new com.cast_music.tracks.b(this.b.getApplicationContext());
            a(this.b.getApplicationContext());
        }
    }

    public final RemoteMediaPlayer A() {
        return this.H;
    }

    public final boolean B() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        MediaInfo F = F();
        return F != null && F.getStreamType() == 2;
    }

    private void W() throws NoConnectionException {
        if (this.H == null) {
            throw new NoConnectionException();
        }
    }

    public boolean C() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        return this.K == 4 || this.K == 2;
    }

    public boolean D() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        return this.K == 3;
    }

    public boolean E() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        return D() || C();
    }

    public MediaInfo F() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        W();
        return this.H.getMediaInfo();
    }

    public double G() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        if (this.J != VolumeType.STREAM) {
            return l();
        }
        W();
        return this.H.getMediaStatus().getStreamVolume();
    }

    public void b(double d) throws CastException, TransientNetworkDisconnectionException, NoConnectionException {
        s();
        if (d > 1.0d) {
            d = 1.0d;
        } else if (d < 0.0d) {
            d = 0.0d;
        }
        if (this.J == VolumeType.STREAM) {
            W();
            this.H.setStreamVolume(this.m, d).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    if (!mediaChannelResult.getStatus().isSuccess()) {
                        VideoCastManager.this.onFailed(R.string.ccl_failed_setting_volume, mediaChannelResult.getStatus().getStatusCode());
                    }
                }
            });
            return;
        }
        a(d);
    }

    public void c(double d) throws CastException, TransientNetworkDisconnectionException, NoConnectionException {
        s();
        double G = G() + d;
        d = 1.0d;
        if (G <= 1.0d) {
            d = G < 0.0d ? 0.0d : G;
        }
        b(d);
    }

    public boolean H() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        if (this.J != VolumeType.STREAM) {
            return m();
        }
        W();
        return this.H.getMediaStatus().isMute();
    }

    public long I() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        W();
        return this.H.getStreamDuration();
    }

    public long J() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        if (this.H == null) {
            return -1;
        }
        long j;
        if (B()) {
            j = this.R;
        } else {
            j = this.H.getStreamDuration() - this.H.getApproximateStreamPosition();
        }
        return j;
    }

    public long K() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        W();
        return this.H.getApproximateStreamPosition();
    }

    private void h(int i) {
        String str = t;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onApplicationDisconnected() reached with error code: ");
        stringBuilder.append(i);
        com.cast_music.b.b.a(str, stringBuilder.toString());
        this.r = i;
        d(false);
        if (this.I != null && c(2)) {
            this.c.setMediaSessionCompat(null);
        }
        for (c onApplicationDisconnected : this.O) {
            onApplicationDisconnected.onApplicationDisconnected(i);
        }
        if (this.c != null) {
            String str2 = t;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("onApplicationDisconnected(): Cached RouteInfo: ");
            stringBuilder2.append(k());
            com.cast_music.b.b.a(str2, stringBuilder2.toString());
            str2 = t;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("onApplicationDisconnected(): Selected RouteInfo: ");
            stringBuilder2.append(this.c.getSelectedRoute());
            com.cast_music.b.b.a(str2, stringBuilder2.toString());
            if (k() == null || this.c.getSelectedRoute().equals(k())) {
                com.cast_music.b.b.a(t, "onApplicationDisconnected(): Setting route to default");
                this.c.selectRoute(this.c.getDefaultRoute());
            }
        }
        a(null, null);
    }

    private void X() {
        if (f()) {
            try {
                String applicationStatus = Cast.CastApi.getApplicationStatus(this.m);
                String str = t;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onApplicationStatusChanged() reached: ");
                stringBuilder.append(applicationStatus);
                com.cast_music.b.b.a(str, stringBuilder.toString());
                for (c onApplicationStatusChanged : this.O) {
                    onApplicationStatusChanged.onApplicationStatusChanged(applicationStatus);
                }
            } catch (IllegalStateException e) {
                com.cast_music.b.b.a(t, "onApplicationStatusChanged()", e);
            }
        }
    }

    private void Y() {
        com.cast_music.b.b.a(t, "onVolumeChanged() reached");
        try {
            double G = G();
            boolean H = H();
            for (c onVolumeChanged : this.O) {
                onVolumeChanged.onVolumeChanged(G, H);
            }
        } catch (NoConnectionException | TransientNetworkDisconnectionException e) {
            com.cast_music.b.b.a(t, "Failed to get volume", e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        str = t;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onApplicationConnected() reached with sessionId: ");
        stringBuilder.append(str2);
        stringBuilder.append(", and mReconnectionStatus=");
        stringBuilder.append(this.j);
        com.cast_music.b.b.a(str, stringBuilder.toString());
        this.r = 0;
        if (this.j == 2) {
            List<RouteInfo> routes = this.c.getRoutes();
            if (routes != null) {
                String a = this.h.a("route-id");
                for (RouteInfo routeInfo : routes) {
                    if (a.equals(routeInfo.getId())) {
                        com.cast_music.b.b.a(t, "Found the correct route during reconnection attempt");
                        this.j = 3;
                        this.c.selectRoute(routeInfo);
                        break;
                    }
                }
            }
        }
        try {
            ac();
            Z();
            this.q = str2;
            this.h.a("session-id", this.q);
            this.H.requestStatus(this.m).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    if (!mediaChannelResult.getStatus().isSuccess()) {
                        VideoCastManager.this.onFailed(R.string.ccl_failed_status_request, mediaChannelResult.getStatus().getStatusCode());
                    }
                }
            });
            for (c onApplicationConnected : this.O) {
                onApplicationConnected.onApplicationConnected(applicationMetadata, this.q, z);
            }
        } catch (TransientNetworkDisconnectionException e) {
            com.cast_music.b.b.a(t, "Failed to attach media/data channel due to network issues", e);
            onFailed(R.string.ccl_failed_no_connection_trans, -1);
        } catch (NoConnectionException e2) {
            com.cast_music.b.b.a(t, "Failed to attach media/data channel due to network issues", e2);
            onFailed(R.string.ccl_failed_no_connection, -1);
        }
    }

    public void q() {
        aa();
        ad();
        super.q();
    }

    public void b(int i) {
        for (c onApplicationStopFailed : this.O) {
            onApplicationStopFailed.onApplicationStopFailed(i);
        }
    }

    public void a(int i) {
        String str = t;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onApplicationConnectionFailed() reached with errorCode: ");
        stringBuilder.append(i);
        com.cast_music.b.b.a(str, stringBuilder.toString());
        this.r = i;
        if (this.j != 2) {
            for (c onApplicationConnectionFailed : this.O) {
                onApplicationConnectionFailed.onApplicationConnectionFailed(i);
            }
            a(null, null);
            if (this.c != null) {
                com.cast_music.b.b.a(t, "onApplicationConnectionFailed(): Setting route to default");
                this.c.selectRoute(this.c.getDefaultRoute());
            }
        } else if (i == CastStatusCodes.APPLICATION_NOT_RUNNING) {
            this.j = 4;
            a(null, null);
        }
    }

    public void a(MediaInfo mediaInfo, boolean z, int i) throws TransientNetworkDisconnectionException, NoConnectionException {
        a(mediaInfo, z, i, null);
    }

    public void a(MediaInfo mediaInfo, boolean z, int i, JSONObject jSONObject) throws TransientNetworkDisconnectionException, NoConnectionException {
        a(mediaInfo, null, z, i, jSONObject);
    }

    public void a(MediaInfo mediaInfo, long[] jArr, boolean z, int i, JSONObject jSONObject) throws TransientNetworkDisconnectionException, NoConnectionException {
        com.cast_music.b.b.a(t, "loadMedia");
        s();
        if (mediaInfo != null) {
            if (this.H == null) {
                com.cast_music.b.b.b(t, "Trying to load a video with no active media session");
                throw new NoConnectionException();
            }
            this.H.load(this.m, mediaInfo, z, (long) i, jArr, jSONObject).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    for (c onMediaLoadResult : VideoCastManager.this.O) {
                        onMediaLoadResult.onMediaLoadResult(mediaChannelResult.getStatus().getStatusCode());
                    }
                }
            });
        }
    }

    public void a(JSONObject jSONObject) throws TransientNetworkDisconnectionException, NoConnectionException {
        com.cast_music.b.b.a(t, "play(customData)");
        s();
        if (this.H == null) {
            com.cast_music.b.b.b(t, "Trying to play a video with no active media session");
            throw new NoConnectionException();
        } else {
            this.H.play(this.m, jSONObject).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    if (!mediaChannelResult.getStatus().isSuccess()) {
                        VideoCastManager.this.onFailed(R.string.ccl_failed_to_play, mediaChannelResult.getStatus().getStatusCode());
                    }
                }
            });
        }
    }

    public void L() throws CastException, TransientNetworkDisconnectionException, NoConnectionException {
        a(null);
    }

    public void M() throws CastException, TransientNetworkDisconnectionException, NoConnectionException {
        b(null);
    }

    public void b(JSONObject jSONObject) throws TransientNetworkDisconnectionException, NoConnectionException {
        com.cast_music.b.b.a(t, "attempting to pause media");
        s();
        if (this.H == null) {
            com.cast_music.b.b.b(t, "Trying to pause a video with no active media session");
            throw new NoConnectionException();
        } else {
            this.H.pause(this.m, jSONObject).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    if (!mediaChannelResult.getStatus().isSuccess()) {
                        VideoCastManager.this.onFailed(R.string.ccl_failed_to_pause, mediaChannelResult.getStatus().getStatusCode());
                    }
                }
            });
        }
    }

    public void g(int i) throws TransientNetworkDisconnectionException, NoConnectionException {
        com.cast_music.b.b.a(t, "attempting to seek media");
        s();
        if (this.H == null) {
            com.cast_music.b.b.b(t, "Trying to seek a video with no active media session");
            throw new NoConnectionException();
        } else {
            this.H.seek(this.m, (long) i, 0).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    if (!mediaChannelResult.getStatus().isSuccess()) {
                        VideoCastManager.this.onFailed(R.string.ccl_failed_seek, mediaChannelResult.getStatus().getStatusCode());
                    }
                }
            });
        }
    }

    public void N() throws CastException, TransientNetworkDisconnectionException, NoConnectionException {
        s();
        if (C()) {
            M();
        } else if (this.K == 1 && this.L == 1) {
            a(F(), true, 0);
        } else {
            L();
        }
    }

    private void Z() throws TransientNetworkDisconnectionException, NoConnectionException {
        com.cast_music.b.b.a(t, "attachMediaChannel()");
        s();
        if (this.H == null) {
            this.H = new RemoteMediaPlayer();
            this.H.setOnStatusUpdatedListener(new OnStatusUpdatedListener() {
                public void onStatusUpdated() {
                    com.cast_music.b.b.a(VideoCastManager.t, "RemoteMediaPlayer::onStatusUpdated() is reached");
                    VideoCastManager.this.ae();
                }
            });
            this.H.setOnPreloadStatusUpdatedListener(new OnPreloadStatusUpdatedListener() {
                public void onPreloadStatusUpdated() {
                    com.cast_music.b.b.a(VideoCastManager.t, "RemoteMediaPlayer::onPreloadStatusUpdated() is reached");
                    VideoCastManager.this.af();
                }
            });
            this.H.setOnMetadataUpdatedListener(new OnMetadataUpdatedListener() {
                public void onMetadataUpdated() {
                    com.cast_music.b.b.a(VideoCastManager.t, "RemoteMediaPlayer::onMetadataUpdated() is reached");
                    VideoCastManager.this.R();
                }
            });
            this.H.setOnQueueStatusUpdatedListener(new OnQueueStatusUpdatedListener() {
                public void onQueueStatusUpdated() {
                    com.cast_music.b.b.a(VideoCastManager.t, "RemoteMediaPlayer::onQueueStatusUpdated() is reached");
                    VideoCastManager.this.z = VideoCastManager.this.H != null ? VideoCastManager.this.H.getMediaStatus() : null;
                    if (VideoCastManager.this.z == null || VideoCastManager.this.z.getQueueItems() == null) {
                        VideoCastManager.this.a(null, null, 0, false);
                    } else {
                        VideoCastManager.this.a(VideoCastManager.this.z.getQueueItems(), VideoCastManager.this.z.getQueueItemById(VideoCastManager.this.z.getCurrentItemId()), VideoCastManager.this.z.getQueueRepeatMode(), false);
                    }
                }
            });
        }
        try {
            com.cast_music.b.b.a(t, "Registering MediaChannel namespace");
            Cast.CastApi.setMessageReceivedCallbacks(this.m, this.H.getNamespace(), this.H);
        } catch (IOException | IllegalStateException e) {
            com.cast_music.b.b.a(t, "attachMediaChannel()", e);
        }
        a(null);
    }

    private void aa() {
        if (this.H != null && this.m != null) {
            try {
                com.cast_music.b.b.a(t, "Registering MediaChannel namespace");
                Cast.CastApi.setMessageReceivedCallbacks(this.m, this.H.getNamespace(), this.H);
            } catch (IOException | IllegalStateException e) {
                com.cast_music.b.b.a(t, "reattachMediaChannel()", e);
            }
        }
    }

    private void ab() {
        com.cast_music.b.b.a(t, "trying to detach media channel");
        if (this.H != null) {
            try {
                Cast.CastApi.removeMessageReceivedCallbacks(this.m, this.H.getNamespace());
            } catch (IOException | IllegalStateException e) {
                com.cast_music.b.b.a(t, "detachMediaChannel()", e);
            }
            this.H = null;
        }
    }

    public int O() {
        return this.K;
    }

    public int P() {
        return this.L;
    }

    private void ac() throws TransientNetworkDisconnectionException, NoConnectionException {
        if (!TextUtils.isEmpty(this.M) && this.N == null) {
            s();
            this.N = new MessageReceivedCallback() {
                public void onMessageReceived(CastDevice castDevice, String str, String str2) {
                    for (c onDataMessageReceived : VideoCastManager.this.O) {
                        onDataMessageReceived.onDataMessageReceived(str2);
                    }
                }
            };
            try {
                Cast.CastApi.setMessageReceivedCallbacks(this.m, this.M, this.N);
            } catch (IOException | IllegalStateException e) {
                com.cast_music.b.b.a(t, "attachDataChannel()", e);
            }
        }
    }

    private void ad() {
        if (!TextUtils.isEmpty(this.M) && this.N != null) {
            try {
                Cast.CastApi.setMessageReceivedCallbacks(this.m, this.M, this.N);
            } catch (IOException | IllegalStateException e) {
                com.cast_music.b.b.a(t, "reattachDataChannel()", e);
            }
        }
    }

    public boolean Q() {
        if (TextUtils.isEmpty(this.M)) {
            return false;
        }
        try {
            if (this.m != null) {
                Cast.CastApi.removeMessageReceivedCallbacks(this.m, this.M);
            }
            this.N = null;
            this.h.a("cast-custom-data-namespace", null);
            return true;
        } catch (IOException | IllegalStateException e) {
            String str = t;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("removeDataChannel() failed to remove namespace ");
            stringBuilder.append(this.M);
            com.cast_music.b.b.a(str, stringBuilder.toString(), e);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0112 A:{Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0121 A:{Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }} */
    private void ae() {
        /*
        r8 = this;
        r0 = t;
        r1 = "onRemoteMediaPlayerStatusUpdated() reached";
        com.cast_music.b.b.a(r0, r1);
        r0 = r8.m;
        if (r0 == 0) goto L_0x0137;
    L_0x000b:
        r0 = r8.H;
        if (r0 == 0) goto L_0x0137;
    L_0x000f:
        r0 = r8.H;
        r0 = r0.getMediaStatus();
        if (r0 != 0) goto L_0x0019;
    L_0x0017:
        goto L_0x0137;
    L_0x0019:
        r0 = r8.H;
        r0 = r0.getMediaStatus();
        r8.z = r0;
        r0 = r8.z;
        r0 = r0.getQueueItems();
        r1 = 0;
        if (r0 == 0) goto L_0x0040;
    L_0x002a:
        r2 = r8.z;
        r2 = r2.getCurrentItemId();
        r3 = r8.z;
        r2 = r3.getQueueItemById(r2);
        r3 = r8.z;
        r3 = r3.getQueueRepeatMode();
        r8.a(r0, r2, r3, r1);
        goto L_0x0044;
    L_0x0040:
        r0 = 0;
        r8.a(r0, r0, r1, r1);
    L_0x0044:
        r0 = r8.z;
        r0 = r0.getPlayerState();
        r8.K = r0;
        r0 = r8.z;
        r0 = r0.getIdleReason();
        r8.L = r0;
        r2 = r8.G();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r0 = r8.H();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = r8.K;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = 2;
        r6 = 1;
        if (r4 != r5) goto L_0x0075;
    L_0x0062:
        r4 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = "onRemoteMediaPlayerStatusUpdated(): Player status = playing";
        com.cast_music.b.b.a(r4, r5);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r8.d(r6);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = r8.J();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r8.a(r4);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x0107;
    L_0x0075:
        r4 = r8.K;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = 3;
        if (r4 != r5) goto L_0x0086;
    L_0x007a:
        r4 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = "onRemoteMediaPlayerStatusUpdated(): Player status = paused";
        com.cast_music.b.b.a(r4, r5);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r8.d(r1);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x0107;
    L_0x0086:
        r4 = r8.K;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        if (r4 != r6) goto L_0x00fb;
    L_0x008a:
        r4 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = new java.lang.StringBuilder;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5.<init>();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r7 = "onRemoteMediaPlayerStatusUpdated(): Player status = IDLE with reason: ";
        r5.append(r7);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r7 = r8.L;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5.append(r7);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = r5.toString();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        com.cast_music.b.b.a(r4, r5);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r8.d(r1);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = r8.L;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        switch(r4) {
            case 1: goto L_0x00d8;
            case 2: goto L_0x00cb;
            case 3: goto L_0x00bf;
            case 4: goto L_0x00ad;
            default: goto L_0x00aa;
        };	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
    L_0x00aa:
        r4 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x00e4;
    L_0x00ad:
        r1 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = "onRemoteMediaPlayerStatusUpdated(): IDLE reason = ERROR";
        com.cast_music.b.b.a(r1, r4);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r8.S();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r1 = 2131820852; // 0x7f110134 float:1.927443E38 double:1.053259446E-314;
        r4 = -1;
        r8.onFailed(r1, r4);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x0110;
    L_0x00bf:
        r4 = r8.z;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = r4.getLoadingItemId();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        if (r4 != 0) goto L_0x0107;
    L_0x00c7:
        r8.S();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x0110;
    L_0x00cb:
        r1 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = "onRemoteMediaPlayerStatusUpdated(): IDLE reason = CANCELLED";
        com.cast_music.b.b.a(r1, r4);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r1 = r8.B();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r1 = r1 ^ r6;
        goto L_0x0107;
    L_0x00d8:
        r4 = r8.z;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = r4.getLoadingItemId();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        if (r4 != 0) goto L_0x0107;
    L_0x00e0:
        r8.S();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x0110;
    L_0x00e4:
        r5 = new java.lang.StringBuilder;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5.<init>();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r6 = "onRemoteMediaPlayerStatusUpdated(): Unexpected Idle Reason ";
        r5.append(r6);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r6 = r8.L;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5.append(r6);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = r5.toString();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        com.cast_music.b.b.b(r4, r5);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x0107;
    L_0x00fb:
        r4 = r8.K;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = 4;
        if (r4 != r5) goto L_0x0109;
    L_0x0100:
        r4 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r5 = "onRemoteMediaPlayerStatusUpdated(): Player status = buffering";
        com.cast_music.b.b.a(r4, r5);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
    L_0x0107:
        r6 = r1;
        goto L_0x0110;
    L_0x0109:
        r1 = t;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = "onRemoteMediaPlayerStatusUpdated(): Player status = unknown";
        com.cast_music.b.b.a(r1, r4);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
    L_0x0110:
        if (r6 == 0) goto L_0x0115;
    L_0x0112:
        r8.v();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
    L_0x0115:
        r1 = r8.O;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r1 = r1.iterator();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
    L_0x011b:
        r4 = r1.hasNext();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        if (r4 == 0) goto L_0x0136;
    L_0x0121:
        r4 = r1.next();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4 = (com.cast_music.a.c) r4;	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4.onRemoteMediaPlayerStatusUpdated();	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        r4.onVolumeChanged(r2, r0);	 Catch:{ NoConnectionException | TransientNetworkDisconnectionException -> 0x012e, NoConnectionException | TransientNetworkDisconnectionException -> 0x012e }
        goto L_0x011b;
    L_0x012e:
        r0 = move-exception;
        r1 = t;
        r2 = "Failed to get volume state due to network issues";
        com.cast_music.b.b.a(r1, r2, r0);
    L_0x0136:
        return;
    L_0x0137:
        r0 = t;
        r1 = "mApiClient or mRemoteMediaPlayer is null, so will not proceed";
        com.cast_music.b.b.a(r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cast_music.VideoCastManager.ae():void");
    }

    private void af() {
        MediaQueueItem mediaQueueItem = null;
        this.z = this.H != null ? this.H.getMediaStatus() : null;
        if (this.z != null) {
            mediaQueueItem = this.z.getQueueItemById(this.z.getPreloadedItemId());
        }
        this.S = mediaQueueItem;
        String str = t;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRemoteMediaPreloadStatusUpdated() ");
        stringBuilder.append(mediaQueueItem);
        com.cast_music.b.b.a(str, stringBuilder.toString());
        for (c onRemoteMediaPreloadStatusUpdated : this.O) {
            onRemoteMediaPreloadStatusUpdated.onRemoteMediaPreloadStatusUpdated(mediaQueueItem);
        }
    }

    private void a(List<MediaQueueItem> list, MediaQueueItem mediaQueueItem, int i, boolean z) {
        int i2;
        com.cast_music.b.b.a(t, "onQueueUpdated() reached");
        String str = t;
        String str2 = "Queue Items size: %d, Item: %s, Repeat Mode: %d, Shuffle: %s";
        Object[] objArr = new Object[4];
        if (list == null) {
            i2 = 0;
        } else {
            i2 = list.size();
        }
        objArr[0] = Integer.valueOf(i2);
        objArr[1] = mediaQueueItem;
        objArr[2] = Integer.valueOf(i);
        objArr[3] = Boolean.valueOf(z);
        com.cast_music.b.b.a(str, String.format(str2, objArr));
        if (list != null) {
            this.y = new d(new CopyOnWriteArrayList(list), mediaQueueItem, z, i);
        } else {
            this.y = new d(new CopyOnWriteArrayList(), null, false, 0);
        }
        for (c onMediaQueueUpdated : this.O) {
            onMediaQueueUpdated.onMediaQueueUpdated(list, mediaQueueItem, i, z);
        }
    }

    public void R() {
        com.cast_music.b.b.a(t, "onRemoteMediaPlayerMetadataUpdated() reached");
        ah();
        for (c onRemoteMediaPlayerMetadataUpdated : this.O) {
            onRemoteMediaPlayerMetadataUpdated.onRemoteMediaPlayerMetadataUpdated();
        }
        try {
            b(F());
        } catch (NoConnectionException | TransientNetworkDisconnectionException e) {
            com.cast_music.b.b.a(t, "Failed to update lock screen metadata due to a network issue", e);
        }
    }

    @SuppressLint({"InlinedApi"})
    private void a(MediaInfo mediaInfo) {
        if (c(2)) {
            if (this.I == null) {
                this.I = new MediaSessionCompat(this.b, "TAG", new ComponentName(this.b, VideoIntentReceiver.class.getName()), null);
                this.I.setFlags(3);
                this.I.setActive(true);
                this.I.setCallback(new Callback() {
                    public boolean onMediaButtonEvent(Intent intent) {
                        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
                        if (keyEvent != null && (keyEvent.getKeyCode() == 127 || keyEvent.getKeyCode() == 126)) {
                            a();
                        }
                        return true;
                    }

                    public void onPlay() {
                        a();
                    }

                    public void onPause() {
                        a();
                    }

                    private void a() {
                        try {
                            VideoCastManager.this.N();
                        } catch (CastException | NoConnectionException | TransientNetworkDisconnectionException e) {
                            com.cast_music.b.b.a(VideoCastManager.t, "MediaSessionCompat.Callback(): Failed to toggle playback", e);
                        }
                    }
                });
            }
            this.G.requestAudioFocus(null, 3, 3);
            PendingIntent ag = ag();
            if (ag != null) {
                this.I.setSessionActivity(ag);
            }
            if (mediaInfo == null) {
                this.I.setPlaybackState(new Builder().setState(0, 0, 1.0f).build());
            } else {
                this.I.setPlaybackState(new Builder().setState(3, 0, 1.0f).setActions(512).build());
            }
            b(mediaInfo);
            ah();
            this.c.setMediaSessionCompat(this.I);
        }
    }

    private PendingIntent ag() {
        try {
            Bundle a = d.a(F());
            Intent intent = new Intent(this.b, this.F);
            intent.putExtra(ShareConstants.WEB_DIALOG_PARAM_MEDIA, a);
            return PendingIntent.getActivity(this.b, 0, intent, 134217728);
        } catch (NoConnectionException | TransientNetworkDisconnectionException unused) {
            com.cast_music.b.b.b(t, "getCastControllerPendingIntent(): Failed to get the remote media information");
            return null;
        }
    }

    private void b(MediaInfo mediaInfo) {
        if (mediaInfo != null) {
            c(mediaInfo);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0097  */
    private void c(com.google.android.gms.cast.MediaInfo r8) {
        /*
        r7 = this;
        if (r8 == 0) goto L_0x00b7;
    L_0x0002:
        r0 = r7.I;
        if (r0 != 0) goto L_0x0008;
    L_0x0006:
        goto L_0x00b7;
    L_0x0008:
        r8 = r8.getMetadata();
        r8 = r8.getImages();
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 18;
        r2 = 2131231660; // 0x7f0803ac float:1.8079407E38 double:1.0529683465E-314;
        r3 = 0;
        r4 = 0;
        r5 = 1;
        if (r0 <= r1) goto L_0x004f;
    L_0x001c:
        r0 = r8.size();
        if (r0 <= r5) goto L_0x002d;
    L_0x0022:
        r8 = r8.get(r5);
        r8 = (com.google.android.gms.common.images.WebImage) r8;
        r8 = r8.getUrl();
        goto L_0x006d;
    L_0x002d:
        r0 = r8.size();
        if (r0 != r5) goto L_0x003e;
    L_0x0033:
        r8 = r8.get(r4);
        r8 = (com.google.android.gms.common.images.WebImage) r8;
        r8 = r8.getUrl();
        goto L_0x006d;
    L_0x003e:
        r8 = r7.b;
        if (r8 == 0) goto L_0x004d;
    L_0x0042:
        r8 = r7.b;
        r8 = r8.getResources();
        r8 = android.graphics.BitmapFactory.decodeResource(r8, r2);
        goto L_0x006a;
    L_0x004d:
        r8 = r3;
        goto L_0x006d;
    L_0x004f:
        r0 = r8.isEmpty();
        if (r0 != 0) goto L_0x0060;
    L_0x0055:
        r8 = r8.get(r4);
        r8 = (com.google.android.gms.common.images.WebImage) r8;
        r8 = r8.getUrl();
        goto L_0x006d;
    L_0x0060:
        r8 = r7.b;
        r8 = r8.getResources();
        r8 = android.graphics.BitmapFactory.decodeResource(r8, r2);
    L_0x006a:
        r6 = r3;
        r3 = r8;
        r8 = r6;
    L_0x006d:
        if (r3 == 0) goto L_0x0097;
    L_0x006f:
        r8 = r7.I;
        r8 = r8.getController();
        r8 = r8.getMetadata();
        if (r8 != 0) goto L_0x0081;
    L_0x007b:
        r8 = new android.support.v4.media.MediaMetadataCompat$Builder;
        r8.<init>();
        goto L_0x0087;
    L_0x0081:
        r0 = new android.support.v4.media.MediaMetadataCompat$Builder;
        r0.<init>(r8);
        r8 = r0;
    L_0x0087:
        r0 = r7.I;
        r1 = "android.media.metadata.ART";
        r8 = r8.putBitmap(r1, r3);
        r8 = r8.build();
        r0.setMetadata(r8);
        goto L_0x00b6;
    L_0x0097:
        r0 = r7.C;
        if (r0 == 0) goto L_0x00a0;
    L_0x009b:
        r0 = r7.C;
        r0.cancel(r5);
    L_0x00a0:
        r0 = r7.b;
        r0 = com.cast_music.b.d.b(r0);
        r1 = new com.cast_music.VideoCastManager$12;
        r2 = r0.x;
        r0 = r0.y;
        r1.<init>(r2, r0, r4);
        r7.C = r1;
        r0 = r7.C;
        r0.a(r8);
    L_0x00b6:
        return;
    L_0x00b7:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cast_music.VideoCastManager.c(com.google.android.gms.cast.MediaInfo):void");
    }

    @TargetApi(14)
    private void d(boolean z) {
        int i = 2;
        if (c(2) && f()) {
            try {
                if (this.I == null && z) {
                    a(F());
                }
                if (this.I != null) {
                    int i2 = B() ? 6 : 3;
                    if (z) {
                        i = i2;
                    }
                    PendingIntent ag = ag();
                    if (ag != null) {
                        this.I.setSessionActivity(ag);
                    }
                    this.I.setPlaybackState(new Builder().setState(i, 0, 1.0f).setActions(512).build());
                }
            } catch (NoConnectionException | TransientNetworkDisconnectionException e) {
                com.cast_music.b.b.a(t, "Failed to set up MediaSessionCompat due to network issues", e);
            }
        }
    }

    private void ah() {
        if (this.I != null && c(2)) {
            try {
                MediaInfo F = F();
                if (F != null) {
                    MediaMetadata metadata = F.getMetadata();
                    MediaMetadataCompat metadata2 = this.I.getController().getMetadata();
                    MediaMetadataCompat.Builder builder = metadata2 == null ? new MediaMetadataCompat.Builder() : new MediaMetadataCompat.Builder(metadata2);
                    this.I.setMetadata(builder.putString("android.media.metadata.TITLE", metadata.getString(MediaMetadata.KEY_TITLE)).putString("android.media.metadata.ALBUM_ARTIST", this.b.getResources().getString(R.string.ccl_casting_to_device, new Object[]{i()})).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, metadata.getString(MediaMetadata.KEY_TITLE)).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, metadata.getString(MediaMetadata.KEY_SUBTITLE)).putLong("android.media.metadata.DURATION", F.getStreamDuration()).build());
                    Uri url = metadata.hasImages() ? ((WebImage) metadata.getImages().get(0)).getUrl() : null;
                    if (url == null) {
                        this.I.setMetadata(builder.putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, BitmapFactory.decodeResource(this.b.getResources(), R.drawable.placeholder_album_artwork_large)).build());
                    } else {
                        if (this.D != null) {
                            this.D.cancel(true);
                        }
                        this.D = new com.cast_music.b.a() {
                            /* Access modifiers changed, original: protected */
                            /* renamed from: a */
                            public void onPostExecute(Bitmap bitmap) {
                                if (!(bitmap == null || VideoCastManager.this.I == null)) {
                                    MediaMetadataCompat metadata = VideoCastManager.this.I.getController().getMetadata();
                                    VideoCastManager.this.I.setMetadata((metadata == null ? new MediaMetadataCompat.Builder() : new MediaMetadataCompat.Builder(metadata)).putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap).build());
                                }
                                VideoCastManager.this.D = null;
                            }
                        };
                        this.D.a(url);
                    }
                }
            } catch (NotFoundException e) {
                com.cast_music.b.b.a(t, "Failed to update Media Session due to resource not found", e);
            } catch (NoConnectionException | TransientNetworkDisconnectionException e2) {
                com.cast_music.b.b.a(t, "Failed to update Media Session due to network issues", e2);
            }
        }
    }

    public void S() {
        com.cast_music.b.b.a(t, "clearMediaSession()");
        if (c(2)) {
            if (this.C != null) {
                this.C.cancel(true);
            }
            if (this.D != null) {
                this.D.cancel(true);
            }
            this.G.abandonAudioFocus(null);
            if (this.I != null) {
                this.I.setMetadata(null);
                this.I.setPlaybackState(new Builder().setState(0, 0, 1.0f).build());
                this.I.release();
                this.I.setActive(false);
                this.I = null;
            }
        }
    }

    public synchronized void a(c cVar) {
        if (cVar != null) {
            a((com.cast_music.a.a) cVar);
            this.O.add(cVar);
            String str = t;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Successfully added the new CastConsumer listener ");
            stringBuilder.append(cVar);
            com.cast_music.b.b.a(str, stringBuilder.toString());
        }
    }

    public synchronized void b(c cVar) {
        if (cVar != null) {
            b((com.cast_music.a.a) cVar);
            this.O.remove(cVar);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        ab();
        Q();
        this.K = 1;
        this.z = null;
    }

    /* Access modifiers changed, original: protected */
    public CastOptions.Builder a(CastDevice castDevice) {
        CastOptions.Builder builder = CastOptions.builder(this.f, new a());
        if (c(1)) {
            builder.setVerboseLoggingEnabled(true);
        }
        return builder;
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        d(false);
        this.K = 1;
        this.z = null;
    }

    public void b(boolean z, boolean z2, boolean z3) {
        super.b(z, z2, z3);
        if (z2 && !this.p) {
            S();
        }
        this.K = 1;
        this.z = null;
        this.y = null;
    }

    public void onFailed(int i, int i2) {
        String str = t;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onFailed: ");
        stringBuilder.append(this.b.getString(i));
        stringBuilder.append(", code: ");
        stringBuilder.append(i2);
        com.cast_music.b.b.a(str, stringBuilder.toString());
        super.onFailed(i, i2);
    }

    public boolean a(KeyEvent keyEvent, double d) {
        if (f()) {
            boolean z = keyEvent.getAction() == 0;
            switch (keyEvent.getKeyCode()) {
                case 24:
                    if (a(d, z)) {
                        return true;
                    }
                    break;
                case 25:
                    if (a(-d, z)) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private boolean a(double d, boolean z) {
        if (VERSION.SDK_INT >= 16 && O() == 2 && c(2)) {
            return false;
        }
        if (z) {
            try {
                c(d);
            } catch (CastException | NoConnectionException | TransientNetworkDisconnectionException e) {
                com.cast_music.b.b.a(t, "Failed to change volume", e);
            }
        }
        return true;
    }

    public void a(List<MediaTrack> list) {
        long[] jArr;
        int i = 0;
        if (list.isEmpty()) {
            jArr = new long[0];
        } else {
            jArr = new long[list.size()];
            while (i < list.size()) {
                jArr[i] = ((MediaTrack) list.get(i)).getId();
                i++;
            }
        }
        a(jArr);
        if (list.size() > 0) {
            a(T().a());
        }
    }

    public void a(long[] jArr) {
        if (this.H != null && this.H.getMediaInfo() != null) {
            this.H.setActiveMediaTracks(this.m, jArr).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    String V = VideoCastManager.t;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Setting track result was successful? ");
                    stringBuilder.append(mediaChannelResult.getStatus().isSuccess());
                    com.cast_music.b.b.a(V, stringBuilder.toString());
                    if (!mediaChannelResult.getStatus().isSuccess()) {
                        V = VideoCastManager.t;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed since: ");
                        stringBuilder.append(mediaChannelResult.getStatus());
                        stringBuilder.append(" and status code:");
                        stringBuilder.append(mediaChannelResult.getStatus().getStatusCode());
                        com.cast_music.b.b.a(V, stringBuilder.toString());
                    }
                }
            });
        }
    }

    public void a(TextTrackStyle textTrackStyle) {
        this.H.setTextTrackStyle(this.m, textTrackStyle).setResultCallback(new ResultCallback<MediaChannelResult>() {
            /* renamed from: a */
            public void onResult(MediaChannelResult mediaChannelResult) {
                if (!mediaChannelResult.getStatus().isSuccess()) {
                    VideoCastManager.this.onFailed(R.string.ccl_failed_to_set_track_style, mediaChannelResult.getStatus().getStatusCode());
                }
            }
        });
        for (c cVar : this.O) {
            try {
                cVar.onTextTrackStyleChanged(textTrackStyle);
            } catch (Exception e) {
                String str = t;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onTextTrackStyleChanged(): Failed to inform ");
                stringBuilder.append(cVar);
                com.cast_music.b.b.a(str, stringBuilder.toString(), e);
            }
        }
    }

    public void b(TextTrackStyle textTrackStyle) {
        com.cast_music.b.b.a(t, "onTextTrackStyleChanged() reached");
        if (this.H != null && this.H.getMediaInfo() != null) {
            this.H.setTextTrackStyle(this.m, textTrackStyle).setResultCallback(new ResultCallback<MediaChannelResult>() {
                /* renamed from: a */
                public void onResult(MediaChannelResult mediaChannelResult) {
                    if (!mediaChannelResult.getStatus().isSuccess()) {
                        VideoCastManager.this.onFailed(R.string.ccl_failed_to_set_track_style, mediaChannelResult.getStatus().getStatusCode());
                    }
                }
            });
            for (c cVar : this.O) {
                try {
                    cVar.onTextTrackStyleChanged(textTrackStyle);
                } catch (Exception e) {
                    String str = t;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onTextTrackStyleChanged(): Failed to inform ");
                    stringBuilder.append(cVar);
                    com.cast_music.b.b.a(str, stringBuilder.toString(), e);
                }
            }
        }
    }

    public void c(boolean z) {
        com.cast_music.b.b.a(t, "onTextTrackEnabledChanged() reached");
        if (!z) {
            a(new long[0]);
        }
        for (c onTextTrackEnabledChanged : this.O) {
            onTextTrackEnabledChanged.onTextTrackEnabledChanged(z);
        }
    }

    public void a(Locale locale) {
        com.cast_music.b.b.a(t, "onTextTrackLocaleChanged() reached");
        for (c onTextTrackLocaleChanged : this.O) {
            onTextTrackLocaleChanged.onTextTrackLocaleChanged(locale);
        }
    }

    @SuppressLint({"NewApi"})
    private void a(Context context) {
        if (d.a) {
            ((CaptioningManager) context.getSystemService("captioning")).addCaptioningChangeListener(new CaptioningChangeListener() {
                public void onEnabledChanged(boolean z) {
                    VideoCastManager.this.c(z);
                }

                public void onUserStyleChanged(@NonNull CaptionStyle captionStyle) {
                    VideoCastManager.this.b(VideoCastManager.this.x.a());
                }

                public void onFontScaleChanged(float f) {
                    VideoCastManager.this.b(VideoCastManager.this.x.a());
                }

                public void onLocaleChanged(Locale locale) {
                    VideoCastManager.this.a(locale);
                }
            });
        }
    }

    public com.cast_music.tracks.b T() {
        return this.x;
    }

    public long[] U() {
        return (this.H == null || this.H.getMediaStatus() == null) ? null : this.H.getMediaStatus().getActiveTrackIds();
    }

    public void b(List<MediaTrack> list) {
        if (list == null) {
            throw new IllegalArgumentException("tracks must not be null");
        } else if (this.P.isEmpty()) {
            a((List) list);
        } else {
            for (com.cast_music.tracks.a a : this.P) {
                a.a(list);
            }
        }
    }

    private void ai() {
        com.cast_music.b.b.a(t, "Stopped TrickPlay Timer");
        if (this.B != null) {
            this.B.cancel();
            this.B = null;
        }
        if (this.A != null) {
            this.A.cancel();
            this.A = null;
        }
    }

    private void aj() {
        ai();
        this.A = new Timer();
        this.B = new b(this, null);
        this.A.scheduleAtFixedRate(this.B, 100, u);
        com.cast_music.b.b.a(t, "Restarted Progress Timer");
    }

    private void a(int i, int i2) {
        for (e a : this.Q) {
            a.a(i, i2);
        }
    }
}
