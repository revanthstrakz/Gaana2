package com.cast_music;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.MediaRouteActionProvider;
import android.support.v7.app.MediaRouteDialogFactory;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.view.Menu;
import android.view.MenuItem;
import com.cast_music.b.b;
import com.cast_music.b.c;
import com.cast_music.b.d;
import com.cast_music.exceptions.CastException;
import com.cast_music.exceptions.NoConnectionException;
import com.cast_music.exceptions.TransientNetworkDisconnectionException;
import com.cast_music.reconnection.ReconnectionService;
import com.gaana.R;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.cast.Cast.CastOptions.Builder;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class a implements com.cast_music.exceptions.a, ConnectionCallbacks, OnConnectionFailedListener {
    private static final String s = b.a(a.class);
    private static String t;
    protected b a;
    protected Context b;
    protected MediaRouter c;
    protected MediaRouteSelector d;
    protected c e;
    protected CastDevice f;
    protected String g;
    protected c h;
    protected String i;
    protected int j = 4;
    protected int k;
    protected boolean l;
    protected GoogleApiClient m;
    protected AsyncTask<Void, Integer, Boolean> n;
    protected int o;
    protected boolean p;
    protected String q;
    protected int r = 0;
    private final Set<com.cast_music.a.a> u = new CopyOnWriteArraySet();
    private boolean v = false;
    private Handler w;
    private RouteInfo x;

    private class a implements Callback {
        private a() {
        }

        /* synthetic */ a(a aVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean handleMessage(Message message) {
            a.this.b(message.what == 0);
            return true;
        }
    }

    private static boolean a(int i, int i2) {
        return i == 0 || (i & i2) == i2;
    }

    public abstract Builder a(CastDevice castDevice);

    /* Access modifiers changed, original: protected */
    public void a() {
    }

    public abstract void a(int i);

    public abstract void a(ApplicationMetadata applicationMetadata, String str, String str2, boolean z);

    public abstract void b(int i);

    protected a() {
    }

    protected a(Context context, b bVar) {
        this.a = bVar;
        this.o = bVar.a();
        b.a(c(1));
        t = context.getString(R.string.ccl_version);
        this.i = bVar.b();
        String str = s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BaseCastManager is instantiated\nVersion: ");
        stringBuilder.append(t);
        stringBuilder.append("\nApplication ID: ");
        stringBuilder.append(this.i);
        b.a(str, stringBuilder.toString());
        this.b = context.getApplicationContext();
        this.h = new c(this.b);
        this.w = new Handler(new a(this, null));
        this.h.a("application-id", this.i);
        this.c = MediaRouter.getInstance(this.b);
        this.d = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(this.i)).build();
        this.e = new c(this);
        this.c.addCallback(this.d, this.e, 4);
    }

    private MediaRouteDialogFactory y() {
        return this.a.g();
    }

    public final void a(CastDevice castDevice, RouteInfo routeInfo) {
        for (com.cast_music.a.a onDeviceSelected : this.u) {
            onDeviceSelected.onDeviceSelected(castDevice, routeInfo);
        }
        if (castDevice == null) {
            a(this.v, true, false);
        } else {
            b(castDevice);
        }
    }

    public final void a(boolean z) {
        for (com.cast_music.a.a onCastAvailabilityChanged : this.u) {
            onCastAvailabilityChanged.onCastAvailabilityChanged(z);
        }
    }

    public final void a(boolean z, boolean z2, boolean z3) {
        String str = s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("disconnectDevice(");
        stringBuilder.append(z2);
        stringBuilder.append(",");
        stringBuilder.append(z3);
        stringBuilder.append(")");
        b.a(str, stringBuilder.toString());
        if (this.f != null) {
            StringBuilder stringBuilder2;
            int i;
            this.f = null;
            this.g = null;
            String str2 = "disconnectDevice() Disconnect Reason: ";
            if (this.p) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append("Connectivity lost");
                str2 = stringBuilder2.toString();
                i = 1;
            } else {
                i = this.r;
                if (i == 0) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str2);
                    stringBuilder2.append("Intentional disconnect");
                    str2 = stringBuilder2.toString();
                    i = 3;
                } else if (i != CastStatusCodes.APPLICATION_NOT_RUNNING) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str2);
                    stringBuilder2.append("Other");
                    str2 = stringBuilder2.toString();
                    i = 0;
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str2);
                    stringBuilder2.append("App was taken over or not available anymore");
                    str2 = stringBuilder2.toString();
                    i = 2;
                }
            }
            b.a(s, str2);
            for (com.cast_music.a.a onDisconnectionReason : this.u) {
                onDisconnectionReason.onDisconnectionReason(i);
            }
            str2 = s;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("mConnectionSuspended: ");
            stringBuilder2.append(this.p);
            b.a(str2, stringBuilder2.toString());
            if (!this.p && z2) {
                f(0);
                v();
            }
            try {
                if ((f() || g()) && z) {
                    b.a(s, "Calling stopApplication");
                    r();
                }
            } catch (NoConnectionException | TransientNetworkDisconnectionException e) {
                b.a(s, "Failed to stop the application after disconnecting route", e);
            }
            a();
            if (this.m != null) {
                if (this.m.isConnected()) {
                    b.a(s, "Trying to disconnect");
                    this.m.disconnect();
                }
                if (this.c != null && z3) {
                    b.a(s, "disconnectDevice(): Setting route to default");
                    this.c.selectRoute(this.c.getDefaultRoute());
                }
                this.m = null;
            }
            this.q = null;
            b(z, z2, z3);
        }
    }

    private void b(CastDevice castDevice) {
        this.f = castDevice;
        this.g = this.f.getFriendlyName();
        if (this.m == null) {
            String str = s;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("acquiring a connection to Google Play services for ");
            stringBuilder.append(this.f);
            b.a(str, stringBuilder.toString());
            this.m = new GoogleApiClient.Builder(this.b).addApi(Cast.API, a(this.f).build()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
            this.m.connect();
        } else if (!this.m.isConnected() && !this.m.isConnecting()) {
            this.m.connect();
        }
    }

    public final void a(RouteInfo routeInfo) {
        for (com.cast_music.a.a onCastDeviceDetected : this.u) {
            onCastDeviceDetected.onCastDeviceDetected(routeInfo);
        }
    }

    public final void b(RouteInfo routeInfo) {
        for (com.cast_music.a.a onRouteRemoved : this.u) {
            onRouteRemoved.onRouteRemoved(routeInfo);
        }
    }

    public final MenuItem a(Menu menu, int i) {
        MenuItem findItem = menu.findItem(i);
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(findItem);
        mediaRouteActionProvider.setRouteSelector(this.d);
        if (y() != null) {
            mediaRouteActionProvider.setDialogFactory(y());
        }
        return findItem;
    }

    public final synchronized void b() {
        this.k++;
        if (!this.l) {
            this.l = true;
            this.w.removeMessages(1);
            this.w.sendEmptyMessageDelayed(0, 300);
        }
        if (this.k == 0) {
            b.a(s, "UI is no longer visible");
        } else {
            b.a(s, "UI is visible");
        }
    }

    public final synchronized void c() {
        int i = this.k - 1;
        this.k = i;
        if (i == 0) {
            b.a(s, "UI is no longer visible");
            if (this.l) {
                this.l = false;
                this.w.removeMessages(0);
                this.w.sendEmptyMessageDelayed(1, 300);
            }
        } else {
            b.a(s, "UI is visible");
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(boolean z) {
        if (z) {
            if (!(this.c == null || this.e == null)) {
                b.a(s, "onUiVisibilityChanged() addCallback called");
                d();
                if (c(32)) {
                    p();
                }
            }
        } else if (this.c != null) {
            b.a(s, "onUiVisibilityChanged() removeCallback called");
            e();
        }
        for (com.cast_music.a.a onUiVisibilityChanged : this.u) {
            onUiVisibilityChanged.onUiVisibilityChanged(z);
        }
    }

    public final void d() {
        this.c.addCallback(this.d, this.e, 4);
    }

    public final void e() {
        this.c.removeCallback(this.e);
    }

    public final boolean f() {
        return this.m != null && this.m.isConnected();
    }

    public final boolean g() {
        return this.m != null && this.m.isConnecting();
    }

    public final void h() {
        if (f() || g()) {
            a(this.v, true, true);
        }
    }

    public final String i() {
        return this.g;
    }

    public final MediaRouteSelector j() {
        return this.d;
    }

    public final RouteInfo k() {
        return this.x;
    }

    public final boolean c(int i) {
        return (this.o & i) == i;
    }

    public final void a(double d) throws CastException, TransientNetworkDisconnectionException, NoConnectionException {
        s();
        try {
            Cast.CastApi.setVolume(this.m, d);
        } catch (IOException e) {
            throw new CastException("Failed to set volume", e);
        } catch (IllegalStateException e2) {
            throw new NoConnectionException("setDeviceVolume()", e2);
        }
    }

    public final double l() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        try {
            return Cast.CastApi.getVolume(this.m);
        } catch (IllegalStateException e) {
            throw new NoConnectionException("getDeviceVolume()", e);
        }
    }

    public final boolean m() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        try {
            return Cast.CastApi.isMute(this.m);
        } catch (IllegalStateException e) {
            throw new NoConnectionException("isDeviceMute()", e);
        }
    }

    public final int n() {
        return this.j;
    }

    public final void d(int i) {
        if (this.j != i) {
            this.j = i;
            g(this.j);
        }
    }

    private void g(int i) {
        for (com.cast_music.a.a onReconnectionStatusChanged : this.u) {
            onReconnectionStatusChanged.onReconnectionStatusChanged(i);
        }
    }

    public final boolean a(String str) {
        String a = this.h.a("session-id");
        String a2 = this.h.a("route-id");
        String a3 = this.h.a("ssid");
        if (a == null || a2 == null) {
            return false;
        }
        if (str != null && (a3 == null || !a3.equals(str))) {
            return false;
        }
        b.a(s, "Found session info in the preferences, so proceed with an attempt to reconnect if possible");
        return true;
    }

    private void c(RouteInfo routeInfo) {
        if (!f()) {
            String a = this.h.a("session-id");
            String a2 = this.h.a("route-id");
            String str = s;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("reconnectSessionIfPossible() Retrieved from preferences: sessionId=");
            stringBuilder.append(a);
            stringBuilder.append(", routeId=");
            stringBuilder.append(a2);
            b.a(str, stringBuilder.toString());
            if (a != null && a2 != null) {
                d(2);
                CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
                if (fromBundle != null) {
                    a2 = s;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("trying to acquire Cast Client for ");
                    stringBuilder2.append(fromBundle);
                    b.a(a2, stringBuilder2.toString());
                    a(fromBundle, routeInfo);
                }
            }
        }
    }

    public final void o() {
        b.a(s, "cancelling reconnection task");
        if (this.n != null && !this.n.isCancelled()) {
            this.n.cancel(true);
        }
    }

    public final void p() {
        e(10);
    }

    public final void e(int i) {
        a(i, null);
    }

    @TargetApi(14)
    public void a(final int i, String str) {
        b.a(s, String.format("reconnectSessionIfPossible(%d, %s)", new Object[]{Integer.valueOf(i), str}));
        if (!f()) {
            String a = this.h.a("route-id");
            if (a(str)) {
                List<RouteInfo> routes = this.c.getRoutes();
                RouteInfo routeInfo = null;
                if (routes != null) {
                    for (RouteInfo routeInfo2 : routes) {
                        if (routeInfo2.getId().equals(a)) {
                            routeInfo = routeInfo2;
                            break;
                        }
                    }
                }
                if (routeInfo != null) {
                    c(routeInfo);
                } else {
                    d(1);
                }
                if (!(this.n == null || this.n.isCancelled())) {
                    this.n.cancel(true);
                }
                this.n = new AsyncTask<Void, Integer, Boolean>() {
                    /* Access modifiers changed, original: protected|varargs */
                    /* renamed from: a */
                    public Boolean doInBackground(Void... voidArr) {
                        int i = 0;
                        while (i < i) {
                            String x = a.s;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Reconnection: Attempt ");
                            i++;
                            stringBuilder.append(i);
                            b.a(x, stringBuilder.toString());
                            if (isCancelled()) {
                                return Boolean.valueOf(true);
                            }
                            try {
                                if (a.this.f()) {
                                    cancel(true);
                                }
                                Thread.sleep(1000);
                            } catch (InterruptedException unused) {
                            }
                        }
                        return Boolean.valueOf(false);
                    }

                    /* Access modifiers changed, original: protected */
                    /* renamed from: a */
                    public void onPostExecute(Boolean bool) {
                        if (bool == null || !bool.booleanValue()) {
                            b.a(a.s, "Couldn't reconnect, dropping connection");
                            a.this.d(4);
                            a.this.a(null, null);
                        }
                    }
                };
                if (VERSION.SDK_INT >= 11) {
                    this.n.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    this.n.execute(new Void[0]);
                }
            }
        }
    }

    public void q() {
        for (com.cast_music.a.a onConnectivityRecovered : this.u) {
            onConnectivityRecovered.onConnectivityRecovered();
        }
    }

    public final void onConnected(Bundle bundle) {
        String str = s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onConnected() reached with prior suspension: ");
        stringBuilder.append(this.p);
        b.a(str, stringBuilder.toString());
        if (this.p) {
            this.p = false;
            if (bundle == null || !bundle.getBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING)) {
                q();
            } else {
                b.a(s, "onConnected(): App no longer running, so disconnecting");
                h();
            }
        } else if (f()) {
            try {
                if (c(8)) {
                    this.h.a("ssid", d.a(this.b));
                }
                Cast.CastApi.requestStatus(this.m);
                if (!this.a.e()) {
                    z();
                }
                for (com.cast_music.a.a onConnected : this.u) {
                    onConnected.onConnected();
                }
            } catch (IOException | IllegalStateException e) {
                b.a(s, "requestStatus()", e);
            }
        } else {
            if (this.j == 2) {
                d(4);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(boolean z, boolean z2, boolean z3) {
        b.a(s, "onDisconnected() reached");
        this.g = null;
        for (com.cast_music.a.a onDisconnected : this.u) {
            onDisconnected.onDisconnected();
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        String str = s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onConnectionFailed() reached, error code: ");
        stringBuilder.append(connectionResult.getErrorCode());
        stringBuilder.append(", reason: ");
        stringBuilder.append(connectionResult.toString());
        b.a(str, stringBuilder.toString());
        a(this.v, false, false);
        this.p = false;
        if (this.c != null) {
            this.c.selectRoute(this.c.getDefaultRoute());
        }
        for (com.cast_music.a.a onConnectionFailed : this.u) {
            onConnectionFailed.onConnectionFailed(connectionResult);
        }
        PendingIntent resolution = connectionResult.getResolution();
        if (resolution != null) {
            try {
                resolution.send();
            } catch (CanceledException e) {
                b.a(s, "Failed to show recovery from the recoverable error", e);
            }
        }
    }

    public void onConnectionSuspended(int i) {
        this.p = true;
        String str = s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onConnectionSuspended() was called with cause: ");
        stringBuilder.append(i);
        b.a(str, stringBuilder.toString());
        for (com.cast_music.a.a onConnectionSuspended : this.u) {
            onConnectionSuspended.onConnectionSuspended(i);
        }
    }

    public final void a(String str, LaunchOptions launchOptions) throws TransientNetworkDisconnectionException, NoConnectionException {
        b.a(s, "launchApp(applicationId, launchOptions) is called");
        if (!f()) {
            if (this.j == 2) {
                d(4);
                return;
            }
            s();
        }
        if (this.j == 2) {
            b.a(s, "Attempting to join a previously interrupted session...");
            String a = this.h.a("session-id");
            b.a(s, "joinApplication() -> start");
            Cast.CastApi.joinApplication(this.m, str, a).setResultCallback(new ResultCallback<ApplicationConnectionResult>() {
                /* renamed from: a */
                public void onResult(ApplicationConnectionResult applicationConnectionResult) {
                    if (applicationConnectionResult.getStatus().isSuccess()) {
                        b.a(a.s, "joinApplication() -> success");
                        a.this.a(applicationConnectionResult.getApplicationMetadata(), applicationConnectionResult.getApplicationStatus(), applicationConnectionResult.getSessionId(), applicationConnectionResult.getWasLaunched());
                        return;
                    }
                    b.a(a.s, "joinApplication() -> failure");
                    a.this.f(12);
                    a.this.o();
                    a.this.a(applicationConnectionResult.getStatus().getStatusCode());
                }
            });
        } else {
            b.a(s, "Launching app");
            Cast.CastApi.launchApplication(this.m, str, launchOptions).setResultCallback(new ResultCallback<ApplicationConnectionResult>() {
                /* renamed from: a */
                public void onResult(ApplicationConnectionResult applicationConnectionResult) {
                    if (applicationConnectionResult.getStatus().isSuccess()) {
                        b.a(a.s, "launchApplication() -> success result");
                        a.this.a(applicationConnectionResult.getApplicationMetadata(), applicationConnectionResult.getApplicationStatus(), applicationConnectionResult.getSessionId(), applicationConnectionResult.getWasLaunched());
                        return;
                    }
                    b.a(a.s, "launchApplication() -> failure result");
                    a.this.a(applicationConnectionResult.getStatus().getStatusCode());
                }
            });
        }
    }

    private void z() throws TransientNetworkDisconnectionException, NoConnectionException {
        b.a(s, "launchApp() is called");
        a(this.a.b(), this.a.d());
    }

    public final void r() throws TransientNetworkDisconnectionException, NoConnectionException {
        s();
        Cast.CastApi.stopApplication(this.m, this.q).setResultCallback(new ResultCallback<Status>() {
            /* renamed from: a */
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    b.a(a.s, "stopApplication -> onResult Stopped application successfully");
                    return;
                }
                b.a(a.s, "stopApplication -> onResult: stopping application failed");
                a.this.b(status.getStatusCode());
            }
        });
    }

    public final void a(com.cast_music.a.a aVar) {
        if (aVar != null && this.u.add(aVar)) {
            String str = s;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Successfully added the new BaseCastConsumer listener ");
            stringBuilder.append(aVar);
            b.a(str, stringBuilder.toString());
        }
    }

    public final void b(com.cast_music.a.a aVar) {
        if (aVar != null && this.u.remove(aVar)) {
            String str = s;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Successfully removed the existing BaseCastConsumer listener ");
            stringBuilder.append(aVar);
            b.a(str, stringBuilder.toString());
        }
    }

    public final void s() throws TransientNetworkDisconnectionException, NoConnectionException {
        if (!f()) {
            if (this.p) {
                throw new TransientNetworkDisconnectionException();
            }
            throw new NoConnectionException();
        }
    }

    public void onFailed(int i, int i2) {
        String str = s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onFailed() was called with statusCode: ");
        stringBuilder.append(i2);
        b.a(str, stringBuilder.toString());
        for (com.cast_music.a.a onFailed : this.u) {
            onFailed.onFailed(i, i2);
        }
    }

    public static String t() {
        return t;
    }

    public c u() {
        return this.h;
    }

    public final void f(int i) {
        String str = s;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("clearPersistedConnectionInfo(): Clearing persisted data for ");
        stringBuilder.append(i);
        b.a(str, stringBuilder.toString());
        if (a(i, 4)) {
            this.h.a("session-id", null);
        }
        if (a(i, 1)) {
            this.h.a("route-id", null);
        }
        if (a(i, 2)) {
            this.h.a("ssid", null);
        }
        if (a(i, 8)) {
            this.h.a("media-end", null);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) {
        if (c(8)) {
            String str = s;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startReconnectionService() for media length lef = ");
            stringBuilder.append(j);
            b.a(str, stringBuilder.toString());
            this.h.a("media-end", Long.valueOf(SystemClock.elapsedRealtime() + j));
            Context applicationContext = this.b.getApplicationContext();
            Intent intent = new Intent(applicationContext, ReconnectionService.class);
            intent.setPackage(applicationContext.getPackageName());
            applicationContext.startService(intent);
        }
    }

    /* Access modifiers changed, original: protected */
    public void v() {
        if (c(8)) {
            b.a(s, "stopReconnectionService()");
            Context applicationContext = this.b.getApplicationContext();
            Intent intent = new Intent(applicationContext, ReconnectionService.class);
            intent.setPackage(applicationContext.getPackageName());
            applicationContext.stopService(intent);
        }
    }

    public boolean w() {
        return this.f != null ? this.f.hasCapability(1) ^ 1 : false;
    }
}
