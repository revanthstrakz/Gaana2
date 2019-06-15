package net.hockeyapp.android.d;

import android.content.Context;

public class j {
    private static String a = "versionInfo";

    public static String a(Context context) {
        return context != null ? context.getSharedPreferences("HockeyApp", 0).getString(a, "[]") : "[]";
    }
}
