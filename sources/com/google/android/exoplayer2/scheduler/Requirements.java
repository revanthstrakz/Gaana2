package com.google.android.exoplayer2.scheduler;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.PowerManager;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Requirements {
    private static final int DEVICE_CHARGING = 16;
    private static final int DEVICE_IDLE = 8;
    public static final int NETWORK_TYPE_ANY = 1;
    private static final int NETWORK_TYPE_MASK = 7;
    public static final int NETWORK_TYPE_METERED = 4;
    public static final int NETWORK_TYPE_NONE = 0;
    public static final int NETWORK_TYPE_NOT_ROAMING = 3;
    private static final String[] NETWORK_TYPE_STRINGS = null;
    public static final int NETWORK_TYPE_UNMETERED = 2;
    private static final String TAG = "Requirements";
    private final int requirements;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkType {
    }

    private static void logd(String str) {
    }

    public Requirements(int i, boolean z, boolean z2) {
        int i2 = 0;
        i |= z ? 16 : 0;
        if (z2) {
            i2 = 8;
        }
        this(i | i2);
    }

    public Requirements(int i) {
        this.requirements = i;
    }

    public int getRequiredNetworkType() {
        return this.requirements & 7;
    }

    public boolean isChargingRequired() {
        return (this.requirements & 16) != 0;
    }

    public boolean isIdleRequired() {
        return (this.requirements & 8) != 0;
    }

    public boolean checkRequirements(Context context) {
        return checkNetworkRequirements(context) && checkChargingRequirement(context) && checkIdleRequirement(context);
    }

    public int getRequirementsData() {
        return this.requirements;
    }

    private boolean checkNetworkRequirements(Context context) {
        int requiredNetworkType = getRequiredNetworkType();
        if (requiredNetworkType == 0) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            logd("No network info or no connection.");
            return false;
        } else if (!checkInternetConnectivity(connectivityManager)) {
            return false;
        } else {
            if (requiredNetworkType == 1) {
                return true;
            }
            boolean isRoaming;
            if (requiredNetworkType == 3) {
                isRoaming = activeNetworkInfo.isRoaming();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Roaming: ");
                stringBuilder.append(isRoaming);
                logd(stringBuilder.toString());
                return isRoaming ^ 1;
            }
            isRoaming = isActiveNetworkMetered(connectivityManager, activeNetworkInfo);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Metered network: ");
            stringBuilder2.append(isRoaming);
            logd(stringBuilder2.toString());
            if (requiredNetworkType == 2) {
                return isRoaming ^ 1;
            }
            if (requiredNetworkType == 4) {
                return isRoaming;
            }
            throw new IllegalStateException();
        }
    }

    private boolean checkChargingRequirement(Context context) {
        if (!isChargingRequired()) {
            return true;
        }
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver == null) {
            return false;
        }
        int intExtra = registerReceiver.getIntExtra("status", -1);
        if (intExtra == 2 || intExtra == 5) {
            z = true;
        }
        return z;
    }

    private boolean checkIdleRequirement(Context context) {
        boolean z = true;
        if (!isIdleRequired()) {
            return true;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (Util.SDK_INT >= 23) {
            z = powerManager.isDeviceIdleMode();
        } else if (Util.SDK_INT < 20 ? powerManager.isScreenOn() : powerManager.isInteractive()) {
            z = false;
        }
        return z;
    }

    private static boolean checkInternetConnectivity(ConnectivityManager connectivityManager) {
        if (Util.SDK_INT < 23) {
            return true;
        }
        Network activeNetwork = connectivityManager.getActiveNetwork();
        boolean z = false;
        if (activeNetwork == null) {
            logd("No active network.");
            return false;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        if (networkCapabilities == null || !networkCapabilities.hasCapability(16)) {
            z = true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Network capability validated: ");
        stringBuilder.append(z);
        logd(stringBuilder.toString());
        return z ^ 1;
    }

    private static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
        if (Util.SDK_INT >= 16) {
            return connectivityManager.isActiveNetworkMetered();
        }
        int type = networkInfo.getType();
        boolean z = true;
        if (type == 1 || type == 7 || type == 9) {
            z = false;
        }
        return z;
    }

    public String toString() {
        return super.toString();
    }
}
