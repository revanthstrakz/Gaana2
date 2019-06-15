package com.inmobi.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.HandlerThread;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.comscore.android.id.IdHelperAndroid;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.e.f;
import com.inmobi.commons.core.utilities.b.g;
import com.inmobi.commons.core.utilities.e;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

public class m implements LocationListener {
    static boolean a = false;
    private static final String e = "m";
    private static m f = null;
    private static final Object g = new Object();
    private static boolean h = false;
    LocationManager b;
    HandlerThread c = new HandlerThread("LThread");
    GoogleApiClient d;

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public static m a() {
        m mVar = f;
        if (mVar == null) {
            synchronized (g) {
                mVar = f;
                if (mVar == null) {
                    mVar = new m();
                    f = mVar;
                }
            }
        }
        return mVar;
    }

    private m() {
        this.c.start();
        this.b = (LocationManager) a.b().getSystemService(PlaceFields.LOCATION);
    }

    static boolean b() {
        try {
            GoogleApiClient.class.getName();
            FusedLocationProviderClient.class.getName();
            LocationServices.class.getName();
            return false;
        } catch (NoClassDefFoundError unused) {
            return true;
        }
    }

    static boolean c() {
        try {
            if (e.a(a.b(), "signals", "android.permission.ACCESS_FINE_LOCATION") || e.a(a.b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder("location changed. ts:");
                stringBuilder.append(location.getTime());
                stringBuilder.append(" lat:");
                stringBuilder.append(location.getLatitude());
                stringBuilder.append(":");
                stringBuilder.append(location.getLongitude());
                stringBuilder.append(" accu:");
                stringBuilder.append(location.getAccuracy());
            } catch (Exception e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return;
            }
        }
        if (c()) {
            this.b.removeUpdates(this);
        }
    }

    public final synchronized HashMap<String, Object> d() {
        return a(i(), true);
    }

    public final HashMap<String, String> e() {
        HashMap hashMap = new HashMap();
        String str = "loc-consent-status";
        String str2 = c() ? g() ? "AUTHORISED" : "DENIED" : "DENIED";
        hashMap.put(str, str2.toLowerCase(Locale.ENGLISH));
        return hashMap;
    }

    public final synchronized HashMap<String, String> f() {
        HashMap hashMap;
        hashMap = new HashMap();
        Location i = i();
        HashMap a;
        if (i != null) {
            a = a(i, true);
        } else {
            a = a(g.c(), false);
        }
        for (Entry entry : a.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return hashMap;
    }

    public static void a(boolean z) {
        a = z;
    }

    /* Access modifiers changed, original: final */
    @SuppressLint({"newApi"})
    @TargetApi(19)
    public final boolean g() {
        Context b = a.b();
        if (b == null) {
            return false;
        }
        if (VERSION.SDK_INT >= 28) {
            if (this.b != null) {
                return this.b.isLocationEnabled();
            }
            return false;
        } else if (VERSION.SDK_INT < 19 || VERSION.SDK_INT >= 28) {
            if (this.b != null) {
                boolean z;
                boolean isProviderEnabled;
                if (e.a(b, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                    isProviderEnabled = this.b.isProviderEnabled("gps");
                    z = false;
                } else if (e.a(b, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = this.b.isProviderEnabled("network");
                    isProviderEnabled = false;
                } else {
                    z = false;
                    isProviderEnabled = z;
                }
                if (z || isProviderEnabled) {
                    return true;
                }
            }
            return false;
        } else {
            int i;
            try {
                i = Secure.getInt(b.getContentResolver(), "location_mode");
            } catch (SettingNotFoundException unused) {
                i = 0;
            }
            if (i != 0) {
                return true;
            }
            return false;
        }
    }

    private Location i() {
        Location k;
        Location location;
        Exception e;
        Location location2 = null;
        try {
            if (a && g() && c()) {
                k = h ? k() : null;
                try {
                    if (this.b != null) {
                        location2 = j();
                    }
                } catch (Exception e2) {
                    Exception exception = e2;
                    location = k;
                    e = exception;
                    new StringBuilder("SDK encountered unexpected error in getting a location fix; ").append(e.getMessage());
                    k = location;
                    return a(k, location2);
                }
                return a(k, location2);
            }
            k = null;
            return a(k, location2);
        } catch (Exception e3) {
            e = e3;
            location = null;
            new StringBuilder("SDK encountered unexpected error in getting a location fix; ").append(e.getMessage());
            k = location;
            return a(k, location2);
        }
    }

    private static Location a(Location location, Location location2) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        if (location == null && location2 == null) {
            try {
                b.a().a(new f("signals", "LocationFixFailed"));
            } catch (Exception e) {
                stringBuilder = new StringBuilder("Error in submitting telemetry event : (");
                stringBuilder.append(e.getMessage());
                stringBuilder.append(")");
            }
            return null;
        } else if (location == null) {
            stringBuilder2 = new StringBuilder("Location info provided by Android Api client:");
            stringBuilder2.append(location2);
            stringBuilder2.append(" ts : ");
            stringBuilder2.append(location2.getTime());
            return location2;
        } else if (location2 == null) {
            stringBuilder = new StringBuilder("Location info provided by Google Api client:");
            stringBuilder.append(location);
            stringBuilder.append(" ts : ");
            stringBuilder.append(location.getTime());
            return location;
        } else {
            long time = location.getTime() - location2.getTime();
            Object obj = null;
            Object obj2 = time > 120000 ? 1 : null;
            Object obj3 = time < -120000 ? 1 : null;
            Object obj4 = time > 0 ? 1 : null;
            if (obj2 != null) {
                stringBuilder = new StringBuilder("Location info provided by Google Api client:");
                stringBuilder.append(location);
                stringBuilder.append(" ts : ");
                stringBuilder.append(location.getTime());
                return location;
            } else if (obj3 != null) {
                stringBuilder2 = new StringBuilder("Location info provided by Android Api client:");
                stringBuilder2.append(location2);
                stringBuilder2.append(" ts : ");
                stringBuilder2.append(location2.getTime());
                return location2;
            } else {
                int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
                obj3 = accuracy > 0 ? 1 : null;
                Object obj5 = accuracy < 0 ? 1 : null;
                if (accuracy > 200) {
                    obj = 1;
                }
                if (obj5 != null || (obj4 != null && (obj3 == null || obj == null))) {
                    stringBuilder = new StringBuilder("Location info provided by Google Api client:");
                    stringBuilder.append(location);
                    stringBuilder.append(" ts : ");
                    stringBuilder.append(location.getTime());
                    return location;
                }
                stringBuilder2 = new StringBuilder("Location info provided by Android Api client:");
                stringBuilder2.append(location2);
                stringBuilder2.append(" ts : ");
                stringBuilder2.append(location2.getTime());
                return location2;
            }
        }
    }

    private Location j() {
        Criteria criteria = new Criteria();
        if (e.a(a.b(), "signals", "android.permission.ACCESS_FINE_LOCATION")) {
            criteria.setAccuracy(1);
        } else if (e.a(a.b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
            criteria.setAccuracy(2);
        }
        boolean z = false;
        criteria.setCostAllowed(false);
        String bestProvider = this.b.getBestProvider(criteria, true);
        Location location = null;
        if (bestProvider != null) {
            try {
                location = this.b.getLastKnownLocation(bestProvider);
            } catch (Exception e) {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", "SecurityException");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(e.getMessage());
                    hashMap.put("message", stringBuilder.toString());
                    b.a();
                    b.a("signals", "ExceptionCaught", hashMap);
                } catch (Exception e2) {
                    StringBuilder stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
                    stringBuilder2.append(e2.getMessage());
                    stringBuilder2.append(")");
                }
            }
            if (location == null) {
                location = l();
            }
        }
        StringBuilder stringBuilder3 = new StringBuilder("Location info provided by Location manager:");
        if (location != null) {
            z = true;
        }
        stringBuilder3.append(z);
        return location;
    }

    private static Location k() {
        try {
            return (Location) LocationServices.getFusedLocationProviderClient(a.b()).getLastLocation().getResult();
        } catch (Exception unused) {
            return null;
        }
    }

    private Location l() {
        Location location = null;
        if (this.b != null) {
            List providers = this.b.getProviders(true);
            for (int size = providers.size() - 1; size >= 0; size--) {
                String str = (String) providers.get(size);
                if (this.b.isProviderEnabled(str)) {
                    try {
                        location = this.b.getLastKnownLocation(str);
                    } catch (SecurityException e) {
                        try {
                            HashMap hashMap = new HashMap();
                            hashMap.put("type", "SecurityException");
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(e.getMessage());
                            hashMap.put("message", stringBuilder.toString());
                            b.a();
                            b.a("signals", "ExceptionCaught", hashMap);
                        } catch (Exception e2) {
                            StringBuilder stringBuilder2 = new StringBuilder("Error in submitting telemetry event : (");
                            stringBuilder2.append(e2.getMessage());
                            stringBuilder2.append(")");
                        }
                    }
                    if (location != null) {
                        break;
                    }
                }
            }
        }
        return location;
    }

    private HashMap<String, Object> a(Location location, boolean z) {
        HashMap hashMap = new HashMap();
        Context b = a.b();
        if (b == null) {
            return hashMap;
        }
        if (location != null) {
            if (location.getTime() > 0) {
                hashMap.put("u-ll-ts", Long.valueOf(location.getTime()));
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(location.getLatitude());
            stringBuilder.append(",");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append(",");
            stringBuilder.append((int) location.getAccuracy());
            hashMap.put("u-latlong-accu", stringBuilder.toString());
            hashMap.put("sdk-collected", Integer.valueOf(z));
        }
        if (a) {
            hashMap.put("loc-allowed", Integer.valueOf(g()));
        }
        if (g() && c()) {
            if (e.a(b, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                hashMap.put("loc-granularity", "coarse");
            }
            if (e.a(b, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                hashMap.put("loc-granularity", "fine");
            }
        } else {
            hashMap.put("loc-granularity", IdHelperAndroid.NO_ID_AVAILABLE);
        }
        return hashMap;
    }
}
