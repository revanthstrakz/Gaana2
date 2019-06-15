package com.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.managers.ap;
import com.managers.k;
import com.utilities.Util;
import com.utilities.d;
import java.util.Calendar;

public class DownloadScheduleReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser.getUserProfile() != null) {
            ap.a().a(currentUser.getUserProfile().getUserId());
        }
        if (d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true)) {
            String b = d.a().b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO_TIME", null, true);
            if (b != null) {
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(Long.valueOf(b).longValue());
                if (instance.getTime().compareTo(Calendar.getInstance().getTime()) < 0) {
                    a();
                    return;
                }
            }
            if (Util.k(GaanaApplication.getContext()) == 0) {
                Intent intent2 = new Intent(GaanaApplication.getContext(), FileDownloadService.class);
                if (d.d()) {
                    intent2.putExtra("IS_FROM_SCHEDULE_AND_OREO", true);
                    GaanaApplication.getContext().startForegroundService(intent2);
                } else {
                    GaanaApplication.getContext().startService(intent2);
                }
                return;
            }
            return;
        }
        k.a().a(GaanaApplication.getContext());
    }

    private void a() {
        k.a().b(GaanaApplication.getContext());
    }
}
