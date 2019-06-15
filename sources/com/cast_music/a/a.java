package com.cast_music.a;

import android.support.v7.media.MediaRouter.RouteInfo;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.ConnectionResult;

public interface a extends com.cast_music.exceptions.a {
    void onCastAvailabilityChanged(boolean z);

    void onCastDeviceDetected(RouteInfo routeInfo);

    void onConnected();

    void onConnectionFailed(ConnectionResult connectionResult);

    void onConnectionSuspended(int i);

    void onConnectivityRecovered();

    void onDeviceSelected(CastDevice castDevice, RouteInfo routeInfo);

    void onDisconnected();

    void onDisconnectionReason(int i);

    void onReconnectionStatusChanged(int i);

    void onRouteRemoved(RouteInfo routeInfo);

    void onUiVisibilityChanged(boolean z);
}
