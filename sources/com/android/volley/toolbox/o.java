package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.android.volley.h;
import java.io.File;

public class o {
    public static h a(Context context, e eVar) {
        File file = new File(context.getCacheDir(), "com/android/volley");
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(packageName);
            stringBuilder.append("/");
            stringBuilder.append(packageInfo.versionCode);
            stringBuilder.toString();
        } catch (NameNotFoundException unused) {
        }
        if (eVar == null) {
            eVar = new f();
        }
        h hVar = new h(new c(file, 5242880), new a(eVar), 3, 2);
        hVar.a();
        return hVar;
    }

    public static h a(Context context) {
        return a(context, null);
    }
}
