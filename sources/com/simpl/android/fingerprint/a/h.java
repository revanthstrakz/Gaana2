package com.simpl.android.fingerprint.a;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.facebook.places.model.PlaceFields;

final class h {
    private Context a;

    h(Context context) {
        this.a = context;
    }

    /* Access modifiers changed, original: final */
    public final Location a() {
        try {
            LocationManager locationManager = (LocationManager) this.a.getSystemService(PlaceFields.LOCATION);
            long currentTimeMillis = System.currentTimeMillis() - 10800000;
            long j = Long.MIN_VALUE;
            float f = Float.MAX_VALUE;
            Location location = null;
            for (String lastKnownLocation : locationManager.getAllProviders()) {
                Location lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                if (lastKnownLocation2 != null) {
                    float accuracy = lastKnownLocation2.getAccuracy();
                    long time = lastKnownLocation2.getTime();
                    if (time > currentTimeMillis && accuracy < f) {
                        location = lastKnownLocation2;
                        f = accuracy;
                    } else if (time < currentTimeMillis && f == Float.MAX_VALUE && time > j) {
                        location = lastKnownLocation2;
                    }
                    j = time;
                }
            }
            return location;
        } catch (Exception unused) {
            return null;
        }
    }
}
