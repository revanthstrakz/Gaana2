package com.facebook.places.internal;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException.Type;
import com.facebook.places.model.PlaceFields;
import java.util.ArrayList;
import java.util.List;

public class LocationScannerImpl implements LocationListener, LocationScanner {
    private static final float MIN_DISTANCE_BETWEEN_UPDATES = 0.0f;
    private static final long MIN_TIME_BETWEEN_UPDATES = 100;
    private Context context;
    private List<String> enabledProviders;
    private Location freshLocation;
    private LocationManager locationManager;
    private LocationPackageRequestParams params;
    private final Object scanLock = new Object();

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public LocationScannerImpl(Context context, LocationPackageRequestParams locationPackageRequestParams) {
        this.context = context;
        this.params = locationPackageRequestParams;
        this.locationManager = (LocationManager) context.getSystemService(PlaceFields.LOCATION);
    }

    public void initAndCheckEligibility() throws ScannerException {
        if (Validate.hasLocationPermission(this.context)) {
            this.enabledProviders = new ArrayList(this.params.getLocationProviders().length);
            for (String str : this.params.getLocationProviders()) {
                if (this.locationManager.isProviderEnabled(str)) {
                    this.enabledProviders.add(str);
                }
            }
            if (this.enabledProviders.isEmpty()) {
                throw new ScannerException(Type.DISABLED);
            }
            return;
        }
        throw new ScannerException(Type.PERMISSION_DENIED);
    }

    private Location getLastLocation(String str) {
        Location lastKnownLocation = this.locationManager.getLastKnownLocation(str);
        if (lastKnownLocation != null) {
            if (System.currentTimeMillis() - lastKnownLocation.getTime() < this.params.getLastLocationMaxAgeMs()) {
                return lastKnownLocation;
            }
        }
        return null;
    }

    public Location getLocation() throws ScannerException {
        for (String lastLocation : this.enabledProviders) {
            Location lastLocation2 = getLastLocation(lastLocation);
            if (lastLocation2 != null) {
                return lastLocation2;
            }
        }
        return getFreshLocation();
    }

    private Location getFreshLocation() throws ScannerException {
        this.freshLocation = null;
        HandlerThread handlerThread = new HandlerThread("LocationScanner");
        try {
            handlerThread.start();
            for (String requestLocationUpdates : this.enabledProviders) {
                this.locationManager.requestLocationUpdates(requestLocationUpdates, MIN_TIME_BETWEEN_UPDATES, 0.0f, this, handlerThread.getLooper());
            }
            try {
                synchronized (this.scanLock) {
                    this.scanLock.wait(this.params.getLocationRequestTimeoutMs());
                }
            } catch (Exception unused) {
            }
            this.locationManager.removeUpdates(this);
            handlerThread.quit();
            if (this.freshLocation != null) {
                return this.freshLocation;
            }
            throw new ScannerException(Type.TIMEOUT);
        } catch (Throwable th) {
            this.locationManager.removeUpdates(this);
            handlerThread.quit();
        }
    }

    public void onLocationChanged(Location location) {
        if (this.freshLocation == null && location.getAccuracy() < this.params.getLocationMaxAccuracyMeters()) {
            synchronized (this.scanLock) {
                this.freshLocation = location;
                this.scanLock.notify();
            }
        }
    }
}
