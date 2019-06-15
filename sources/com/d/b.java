package com.d;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsService;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class b {
    private static final String a = "b";
    private static String b;

    private b() {
    }

    public static String a(Context context) {
        if (b != null) {
            return b;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.gaana.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        CharSequence charSequence = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            b = null;
        } else if (arrayList.size() == 1) {
            b = (String) arrayList.get(0);
        } else if (!TextUtils.isEmpty(charSequence) && !a(context, intent) && arrayList.contains(charSequence)) {
            b = charSequence;
        } else if (arrayList.contains("com.android.chrome")) {
            b = "com.android.chrome";
        } else if (arrayList.contains("com.chrome.beta")) {
            b = "com.chrome.beta";
        } else if (arrayList.contains("com.chrome.dev")) {
            b = "com.chrome.dev";
        } else if (arrayList.contains("com.google.android.apps.chrome")) {
            b = "com.google.android.apps.chrome";
        }
        return b;
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null) {
                if (queryIntentActivities.size() != 0) {
                    for (ResolveInfo resolveInfo : queryIntentActivities) {
                        IntentFilter intentFilter = resolveInfo.filter;
                        if (intentFilter != null) {
                            if (intentFilter.countDataAuthorities() == 0) {
                                continue;
                            } else if (intentFilter.countDataPaths() != 0) {
                                if (resolveInfo.activityInfo != null) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            Log.e(a, "Runtime exception while getting specialized handlers");
        }
    }
}
