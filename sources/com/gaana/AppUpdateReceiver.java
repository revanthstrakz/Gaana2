package com.gaana;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.utilities.g;

public class AppUpdateReceiver extends BroadcastReceiver {
    private String TAG = "AppUpdateReceiver";
    private Context mContext;

    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.MY_PACKAGE_REPLACED")) {
            g.a();
            if (g.b()) {
                setIcon();
            }
        }
    }

    private void setIcon() {
        PackageManager packageManager = this.mContext.getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivityMMX"), 1, 1);
        packageManager.getComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivity"));
        packageManager.setComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivity"), 2, 1);
        packageManager.getComponentEnabledSetting(new ComponentName(this.mContext, "com.gaana.SplashScreenActivity"));
    }
}
