package com.cast_music;

import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.RouteInfo;
import com.cast_music.b.b;
import com.google.android.gms.cast.CastDevice;

public class c extends Callback {
    private static final String a = b.a(c.class);
    private final a b;
    private boolean c = false;

    public c(a aVar) {
        this.b = aVar;
    }

    public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRouteSelected: info=");
        stringBuilder.append(routeInfo);
        b.a(str, stringBuilder.toString());
        if (this.b.n() == 3) {
            this.b.d(4);
            this.b.o();
            return;
        }
        this.b.u().a("route-id", routeInfo.getId());
        CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
        this.b.a(fromBundle, routeInfo);
        String str2 = a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("onRouteSelected: mSelectedDevice=");
        stringBuilder.append(fromBundle != null ? fromBundle.getFriendlyName() : "Null");
        b.a(str2, stringBuilder.toString());
    }

    public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRouteUnselected: route=");
        stringBuilder.append(routeInfo);
        b.a(str, stringBuilder.toString());
        this.b.a(null, routeInfo);
    }

    public void onRouteAdded(MediaRouter mediaRouter, RouteInfo routeInfo) {
        if (!mediaRouter.getDefaultRoute().equals(routeInfo)) {
            a(mediaRouter);
            this.b.a(routeInfo);
        }
        if (this.b.n() == 1) {
            if (routeInfo.getId().equals(this.b.u().a("route-id"))) {
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onRouteAdded: Attempting to recover a session with info=");
                stringBuilder.append(routeInfo);
                b.a(str, stringBuilder.toString());
                this.b.d(2);
                CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
                String str2 = a;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("onRouteAdded: Attempting to recover a session with device: ");
                stringBuilder2.append(fromBundle != null ? fromBundle.getFriendlyName() : "Null");
                b.a(str2, stringBuilder2.toString());
                this.b.a(fromBundle, routeInfo);
            }
        }
    }

    public void onRouteRemoved(MediaRouter mediaRouter, RouteInfo routeInfo) {
        a(mediaRouter);
        this.b.b(routeInfo);
    }

    public void onRouteChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
        a(mediaRouter);
    }

    private void a(MediaRouter mediaRouter) {
        boolean b = b(mediaRouter);
        if (b != this.c) {
            this.c = b;
            this.b.a(this.c);
        }
    }

    private boolean b(MediaRouter mediaRouter) {
        return mediaRouter.isRouteAvailable(this.b.j(), 3);
    }
}
