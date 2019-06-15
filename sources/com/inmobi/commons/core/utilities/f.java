package com.inmobi.commons.core.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.inmobi.commons.a.a;

public final class f {
    private static final String a = "f";

    public static boolean a(@NonNull String str) {
        Context b = a.b();
        if (b == null) {
            return false;
        }
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(b) == 0) {
                return true;
            }
            return false;
        } catch (NoClassDefFoundError unused) {
            return false;
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder("Error in connecting to GooglePlayServices API for component ");
            stringBuilder.append(str);
            stringBuilder.append(" : (");
            stringBuilder.append(e.getMessage());
            stringBuilder.append(")");
            return false;
        }
    }
}
