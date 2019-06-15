package io.branch.referral;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

class h {
    public static boolean a(Context context) {
        String str = "io.branch.sdk.TestMode";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getBoolean(str, false);
            }
            return false;
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }
}
