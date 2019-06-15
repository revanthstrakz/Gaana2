package com.moengage.locationlibrary;

import android.app.IntentService;
import android.content.Intent;
import com.moengage.core.Logger;
import com.moengage.location.GeoManager;
import com.moengage.location.GeoManager.LocationHandler;

public class GeofenceIntentService extends IntentService {
    public GeofenceIntentService() {
        super("geo-fence-transitions-service");
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        try {
            LocationHandler handler = GeoManager.getInstance().getHandler(getApplicationContext());
            if (handler != null) {
                handler.onGeoFenceHit(getApplicationContext(), intent);
            }
        } catch (Throwable unused) {
            Logger.f("Class definition not found");
        }
    }
}
