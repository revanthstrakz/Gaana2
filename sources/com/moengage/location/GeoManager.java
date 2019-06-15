package com.moengage.location;

import android.content.Context;
import android.content.Intent;
import com.moengage.core.Logger;

public final class GeoManager {
    private static GeoManager _THIS;
    private LocationHandler handler;

    public interface LocationHandler {
        void onGeoFenceHit(Context context, Intent intent);

        void setGeoFences(Context context, String str);

        void updateFenceAndLocation(Context context);
    }

    public enum TASK_TYPE {
        GET_GEOFENCE,
        GEOFENCE_HIT
    }

    private GeoManager() {
    }

    public static GeoManager getInstance() {
        if (_THIS == null) {
            _THIS = new GeoManager();
        }
        return _THIS;
    }

    public LocationHandler getHandler(Context context) {
        if (context == null) {
            return null;
        }
        if (this.handler == null) {
            try {
                this.handler = (LocationHandler) Class.forName("com.moengage.locationlibrary.LocationHandlerImpl").newInstance();
            } catch (ClassNotFoundException unused) {
                Logger.e("Location Handler class Not Found Exception");
            } catch (Exception e) {
                Logger.e("Exception", e);
            }
        }
        return this.handler;
    }
}
