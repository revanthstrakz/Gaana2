package com.bumptech.glide.e;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.bumptech.glide.load.c;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class a {
    private static final ConcurrentHashMap<String, c> a = new ConcurrentHashMap();

    public static c a(Context context) {
        String packageName = context.getPackageName();
        c cVar = (c) a.get(packageName);
        if (cVar != null) {
            return cVar;
        }
        cVar = b(context);
        c cVar2 = (c) a.putIfAbsent(packageName, cVar);
        return cVar2 == null ? cVar : cVar2;
    }

    private static c b(Context context) {
        PackageInfo packageInfo;
        Object valueOf;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            packageInfo = null;
        }
        if (packageInfo != null) {
            valueOf = String.valueOf(packageInfo.versionCode);
        } else {
            valueOf = UUID.randomUUID().toString();
        }
        return new c(valueOf);
    }
}
