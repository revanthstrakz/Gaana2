package com.google.android.gms.cast;

import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.RouteInfo;

final class zzu extends Callback {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzu(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    public final void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo) {
        this.zzci.zzb("onRouteUnselected");
        if (this.zzci.zzby == null) {
            this.zzci.zzb("onRouteUnselected, no device was selected");
        } else if (CastDevice.getFromBundle(routeInfo.getExtras()).getDeviceId().equals(this.zzci.zzby.getDeviceId())) {
            CastRemoteDisplayLocalService.stopService();
        } else {
            this.zzci.zzb("onRouteUnselected, device does not match");
        }
    }
}
