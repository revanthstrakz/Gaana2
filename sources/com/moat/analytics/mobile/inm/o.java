package com.moat.analytics.mobile.inm;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import com.facebook.places.model.PlaceFields;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class o implements LocationListener {
    private static o a;
    private ScheduledExecutorService b;
    private ScheduledFuture<?> c;
    private ScheduledFuture<?> d;
    private LocationManager e;
    private boolean f;
    private Location g;
    private boolean h;

    private o() {
        try {
            this.f = ((k) MoatAnalytics.getInstance()).c;
            if (this.f) {
                p.a(3, "LocationManager", (Object) this, "Moat location services disabled");
                return;
            }
            this.b = Executors.newScheduledThreadPool(1);
            this.e = (LocationManager) a.a().getSystemService(PlaceFields.LOCATION);
            if (this.e.getAllProviders().size() == 0) {
                p.a(3, "LocationManager", (Object) this, "Device has no location providers");
            } else {
                e();
            }
        } catch (Exception e) {
            m.a(e);
        }
    }

    static o a() {
        if (a == null) {
            a = new o();
        }
        return a;
    }

    private void a(boolean z) {
        try {
            p.a(3, "LocationManager", (Object) this, "stopping location fetch");
            h();
            i();
            if (z) {
                k();
            } else {
                j();
            }
        } catch (Exception e) {
            m.a(e);
        }
    }

    private static boolean a(Location location) {
        return location == null ? false : !(location.getLatitude() == 0.0d && location.getLongitude() == 0.0d) && location.getAccuracy() >= 0.0f && b(location) < 600.0f;
    }

    static boolean a(Location location, Location location2) {
        return location == location2 ? true : (location == null || location2 == null || location.getTime() != location2.getTime()) ? false : true;
    }

    private static boolean a(String str) {
        return ContextCompat.checkSelfPermission(a.a().getApplicationContext(), str) == 0;
    }

    private static float b(Location location) {
        return (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
    }

    private static Location b(Location location, Location location2) {
        boolean a = a(location);
        boolean a2 = a(location2);
        return !a ? !a2 ? null : location2 : (a2 && location.getAccuracy() >= location.getAccuracy()) ? location2 : location;
    }

    private void e() {
        try {
            if (!this.f && this.e != null) {
                if (this.h) {
                    p.a(3, "LocationManager", (Object) this, "already updating location");
                }
                p.a(3, "LocationManager", (Object) this, "starting location fetch");
                this.g = b(this.g, f());
                if (this.g != null) {
                    StringBuilder stringBuilder = new StringBuilder("Have a valid location, won't fetch = ");
                    stringBuilder.append(this.g.toString());
                    p.a(3, "LocationManager", (Object) this, stringBuilder.toString());
                    k();
                    return;
                }
                g();
            }
        } catch (Exception e) {
            m.a(e);
        }
    }

    private Location f() {
        try {
            Location b;
            boolean l = l();
            boolean m = m();
            if (l && m) {
                b = b(this.e.getLastKnownLocation("gps"), this.e.getLastKnownLocation("network"));
            } else {
                LocationManager locationManager;
                String str;
                if (l) {
                    locationManager = this.e;
                    str = "gps";
                } else {
                    if (m) {
                        locationManager = this.e;
                        str = "network";
                    }
                    return null;
                }
                b = locationManager.getLastKnownLocation(str);
            }
            return b;
        } catch (SecurityException e) {
            m.a(e);
        }
    }

    private void g() {
        try {
            if (!this.h) {
                p.a(3, "LocationManager", (Object) this, "Attempting to start update");
                if (l()) {
                    p.a(3, "LocationManager", (Object) this, "start updating gps location");
                    this.e.requestLocationUpdates("gps", 0, 0.0f, this, Looper.getMainLooper());
                    this.h = true;
                }
                if (m()) {
                    p.a(3, "LocationManager", (Object) this, "start updating network location");
                    this.e.requestLocationUpdates("network", 0, 0.0f, this, Looper.getMainLooper());
                    this.h = true;
                }
                if (this.h) {
                    i();
                    this.d = this.b.schedule(new Runnable() {
                        public void run() {
                            try {
                                p.a(3, "LocationManager", (Object) this, "fetchTimedOut");
                                o.this.a(true);
                            } catch (Exception e) {
                                m.a(e);
                            }
                        }
                    }, 60, TimeUnit.SECONDS);
                }
            }
        } catch (SecurityException e) {
            m.a(e);
        }
    }

    private void h() {
        try {
            p.a(3, "LocationManager", (Object) this, "Stopping to update location");
            if (n() && this.e != null) {
                this.e.removeUpdates(this);
                this.h = false;
            }
        } catch (SecurityException e) {
            m.a(e);
        }
    }

    private void i() {
        if (this.d != null && !this.d.isCancelled()) {
            this.d.cancel(true);
            this.d = null;
        }
    }

    private void j() {
        if (this.c != null && !this.c.isCancelled()) {
            this.c.cancel(true);
            this.c = null;
        }
    }

    private void k() {
        p.a(3, "LocationManager", (Object) this, "Resetting fetch timer");
        j();
        float f = 600.0f;
        if (this.g != null) {
            f = Math.max(600.0f - b(this.g), 0.0f);
        }
        this.c = this.b.schedule(new Runnable() {
            public void run() {
                try {
                    p.a(3, "LocationManager", (Object) this, "fetchTimerCompleted");
                    o.this.e();
                } catch (Exception e) {
                    m.a(e);
                }
            }
        }, (long) f, TimeUnit.SECONDS);
    }

    private boolean l() {
        return a("android.permission.ACCESS_FINE_LOCATION") && this.e.getProvider("gps") != null && this.e.isProviderEnabled("gps");
    }

    private boolean m() {
        return n() && this.e.getProvider("network") != null && this.e.isProviderEnabled("network");
    }

    private static boolean n() {
        return a("android.permission.ACCESS_FINE_LOCATION") || a("android.permission.ACCESS_COARSE_LOCATION");
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public Location b() {
        return (this.f || this.e == null) ? null : this.g;
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        e();
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        a(false);
    }

    public void onLocationChanged(Location location) {
        try {
            StringBuilder stringBuilder = new StringBuilder("Received an updated location = ");
            stringBuilder.append(location.toString());
            p.a(3, "LocationManager", (Object) this, stringBuilder.toString());
            float b = b(location);
            if (location.hasAccuracy() && location.getAccuracy() <= 100.0f && b < 600.0f) {
                this.g = b(this.g, location);
                p.a(3, "LocationManager", (Object) this, "fetchCompleted");
                a(true);
            }
        } catch (Exception e) {
            m.a(e);
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
