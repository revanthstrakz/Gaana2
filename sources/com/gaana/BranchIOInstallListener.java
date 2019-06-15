package com.gaana;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.branch.referral.InstallListener;

public class BranchIOInstallListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        new InstallListener().onReceive(context, intent);
    }
}
