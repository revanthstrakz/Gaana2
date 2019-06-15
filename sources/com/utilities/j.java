package com.utilities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import com.facebook.internal.NativeProtocol;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import java.io.File;

public class j {
    private static Intent a(Context context, Uri uri, String str) {
        Intent intent = new Intent("com.facebook.stories.ADD_TO_STORY");
        intent.setType("image/png");
        intent.putExtra(NativeProtocol.EXTRA_APPLICATION_ID, context.getString(R.string.app_id));
        if (uri != null) {
            intent.putExtra("interactive_asset_uri", uri);
        }
        intent.setFlags(1);
        intent.putExtra("top_background_color", str);
        intent.putExtra("bottom_background_color", context.getResources().getString(R.string.story_bottom_color));
        context.grantUriPermission("com.facebook.katana", uri, 1);
        return context.getPackageManager().resolveActivity(intent, 0) != null ? intent : null;
    }

    private static Intent b(Context context, Uri uri, String str) {
        Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
        intent.setType("image/png");
        if (uri != null) {
            intent.putExtra("interactive_asset_uri", uri);
        }
        intent.setFlags(1);
        intent.putExtra("top_background_color", str);
        intent.putExtra("bottom_background_color", context.getResources().getString(R.string.story_bottom_color));
        context.grantUriPermission("com.instagram.android", uri, 1);
        return context.getPackageManager().resolveActivity(intent, 0) != null ? intent : null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0049 A:{SYNTHETIC, Splitter:B:26:0x0049} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a A:{SYNTHETIC, Splitter:B:19:0x003a} */
    private static java.io.File a(android.graphics.Bitmap r5) {
        /*
        r0 = new java.io.File;
        r1 = a();
        r0.<init>(r1);
        r0.mkdirs();
        r1 = new java.io.File;
        r2 = "temp.jpg";
        r1.<init>(r0, r2);
        r0 = 0;
        r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0033, all -> 0x0030 }
        r2.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0033, all -> 0x0030 }
        r3 = android.graphics.Bitmap.CompressFormat.PNG;	 Catch:{ FileNotFoundException -> 0x002e }
        r4 = 100;
        r5.compress(r3, r4, r2);	 Catch:{ FileNotFoundException -> 0x002e }
        if (r2 == 0) goto L_0x002d;
    L_0x0022:
        r2.flush();	 Catch:{ IOException -> 0x0029 }
        r2.close();	 Catch:{ IOException -> 0x0029 }
        goto L_0x002d;
    L_0x0029:
        r5 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
    L_0x002d:
        return r1;
    L_0x002e:
        r5 = move-exception;
        goto L_0x0035;
    L_0x0030:
        r5 = move-exception;
        r2 = r0;
        goto L_0x0047;
    L_0x0033:
        r5 = move-exception;
        r2 = r0;
    L_0x0035:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);	 Catch:{ all -> 0x0046 }
        if (r2 == 0) goto L_0x0045;
    L_0x003a:
        r2.flush();	 Catch:{ IOException -> 0x0041 }
        r2.close();	 Catch:{ IOException -> 0x0041 }
        goto L_0x0045;
    L_0x0041:
        r5 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
    L_0x0045:
        return r0;
    L_0x0046:
        r5 = move-exception;
    L_0x0047:
        if (r2 == 0) goto L_0x0054;
    L_0x0049:
        r2.flush();	 Catch:{ IOException -> 0x0050 }
        r2.close();	 Catch:{ IOException -> 0x0050 }
        goto L_0x0054;
    L_0x0050:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
    L_0x0054:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.utilities.j.a(android.graphics.Bitmap):java.io.File");
    }

    private static Uri a(Context context, File file) {
        if (!d.f()) {
            return Uri.fromFile(file);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getApplicationContext().getPackageName());
        stringBuilder.append(".com.gaana.provider");
        return FileProvider.getUriForFile(context, stringBuilder.toString(), file);
    }

    public static Intent a(Context context, Bitmap bitmap, String str, boolean z) {
        if (bitmap != null) {
            File a = a(bitmap);
            if (a != null && a.exists()) {
                Uri a2 = a(context, a);
                if (a2 != null) {
                    if (z) {
                        return a(context, a2, str);
                    }
                    return b(context, a2, str);
                }
            }
        }
        return null;
    }

    private static String a() {
        File file = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0];
        if (file != null && file.isDirectory() && file.canRead()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append("/story");
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder2.append("/story");
        return stringBuilder2.toString();
    }

    public static boolean a(Context context) {
        Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
        intent.setType("image/png");
        return context.getPackageManager().resolveActivity(intent, 0) != null;
    }
}
