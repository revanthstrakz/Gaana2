package com.helpshift.support.util;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.helpshift.e.k;
import com.helpshift.util.l;
import com.helpshift.views.c;

public class f {
    @TargetApi(9)
    public static void a(Context context) {
        Intent intent;
        try {
            intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            String packageName = context.getPackageName();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("package:");
            stringBuilder.append(packageName);
            intent.setData(Uri.parse(stringBuilder.toString()));
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            intent = new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            context.startActivity(intent);
        }
    }

    public static Snackbar a(final Fragment fragment, final String[] strArr, final int i, View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Requesting permission : ");
        stringBuilder.append(strArr[0]);
        l.a("Helpshift_Permissions", stringBuilder.toString());
        if (fragment.shouldShowRequestPermissionRationale(strArr[0])) {
            Snackbar action = c.a(view, k.hs__permission_denied_message, -2).setAction(k.hs__permission_rationale_snackbar_action_label, new OnClickListener() {
                public void onClick(View view) {
                    fragment.requestPermissions(strArr, i);
                }
            });
            action.show();
            return action;
        }
        fragment.requestPermissions(strArr, i);
        return null;
    }

    public static boolean a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                for (String equals : packageInfo.requestedPermissions) {
                    if (equals.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            l.a("Helpshift_Permissions", "Error checking permission in Manifest : ", e);
        }
        return false;
    }
}
