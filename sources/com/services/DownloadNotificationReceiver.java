package com.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.Tracks.Track;
import com.google.android.exoplayer2.C;
import com.i.i;
import com.managers.DownloadManager;
import com.managers.j;
import com.services.l.r;
import java.util.ArrayList;
import java.util.Calendar;

public class DownloadNotificationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int i = -1;
        Constants.ay = d.a().b("PREFERENCE_DOWNLOAD_NOTIFICATION_NOTIFICATION_REPEAT_DAY", -1, false);
        Constants.aw = d.a().b("PREFERENCE_DOWNLOAD_NOTIFICATION_SHOW_WEEKDAY_RANGE", "", false);
        Constants.ax = d.a().b("PREFERENCE_DOWNLOAD_NOTIFICATION_SHOW_WEEKEND_RANGE", "", false);
        if (!TextUtils.isEmpty(Constants.aw) && !TextUtils.isEmpty(Constants.ax) && Constants.ay >= 1) {
            long b = d.a().b(-1, "PREFERENCE_DOWNLOAD_NOTIFICATION_LAST_SHOWN", false);
            long j = (long) (Constants.ay * com.comscore.utils.Constants.KEEPALIVE_INTERVAL_MS);
            final ArrayList N = DownloadManager.c().N();
            if (N != null && N.size() >= 1 && b() && (b == -1 || System.currentTimeMillis() - b >= j)) {
                int parseInt;
                int parseInt2;
                int parseInt3;
                int parseInt4;
                int parseInt5;
                int parseInt6;
                int i2;
                String[] split = Constants.aw.split("-");
                if (split.length > 1) {
                    String[] split2 = split[1].split(":");
                    parseInt = Integer.parseInt(split2[0]);
                    parseInt2 = Integer.parseInt(split2[1]);
                    split = split[0].split(":");
                    parseInt3 = Integer.parseInt(split[0]);
                    parseInt4 = Integer.parseInt(split[1]);
                } else {
                    parseInt4 = -1;
                    parseInt2 = parseInt4;
                    parseInt = parseInt2;
                    parseInt3 = parseInt;
                }
                String[] split3 = Constants.ax.split("-");
                if (split3.length > 1) {
                    String[] split4 = split3[1].split(":");
                    parseInt5 = Integer.parseInt(split4[0]);
                    i = Integer.parseInt(split4[1]);
                    split3 = split3[0].split(":");
                    parseInt6 = Integer.parseInt(split3[0]);
                    int parseInt7 = Integer.parseInt(split3[1]);
                    i2 = i;
                    i = parseInt5;
                    parseInt5 = parseInt7;
                } else {
                    i2 = -1;
                    parseInt5 = i2;
                    parseInt6 = parseInt5;
                }
                if (parseInt >= 0 && parseInt2 >= 0 && i >= 0 && i2 >= 0) {
                    a();
                    Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(System.currentTimeMillis());
                    int i3 = instance.get(7);
                    if (i3 == 7 || i3 == 1) {
                        instance.set(11, i);
                        instance.set(12, i2);
                    } else {
                        instance.set(11, parseInt);
                        instance.set(12, parseInt2);
                    }
                    instance.set(13, 0);
                    if (instance.getTime().compareTo(Calendar.getInstance().getTime()) < 0) {
                        a(parseInt3, parseInt4, parseInt6, parseInt5);
                        return;
                    }
                    if (N != null && N.size() > 0) {
                        final Context context2 = context;
                        i.a().a(((Track) N.get(0)).getArtwork(), new r() {
                            public void onSuccessfulResponse(Bitmap bitmap) {
                                j.a(GaanaApplication.getContext()).a(context2, N, bitmap);
                            }

                            public void onErrorResponse(VolleyError volleyError) {
                                j.a(GaanaApplication.getContext()).a(context2, N, BitmapFactory.decodeResource(GaanaApplication.getContext().getResources(), R.drawable.placeholder_album_artwork_large));
                            }
                        });
                    }
                    a(parseInt3, parseInt4, parseInt6, parseInt5);
                }
            }
        }
    }

    private void a() {
        try {
            ((AlarmManager) GaanaApplication.getContext().getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(GaanaApplication.getContext(), 9998, new Intent(GaanaApplication.getContext(), DownloadScheduleReceiver.class), C.ENCODING_PCM_MU_LAW));
        } catch (Exception unused) {
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0) {
            d.a().a("PREFERENCE_DOWNLOAD_ALARM_ACTIVE", false, false);
            return;
        }
        Calendar instance = Calendar.getInstance();
        instance.set(13, 0);
        instance.add(5, 2);
        int i5 = instance.get(7);
        if (i5 == 7 || i5 == 1) {
            instance.set(11, i3);
            instance.set(12, i4);
        } else {
            instance.set(11, i);
            instance.set(12, i2);
        }
        ((AlarmManager) GaanaApplication.getContext().getSystemService(NotificationCompat.CATEGORY_ALARM)).setRepeating(0, instance.getTimeInMillis(), com.comscore.utils.Constants.SESSION_INACTIVE_PERIOD, PendingIntent.getBroadcast(GaanaApplication.getContext(), 9998, new Intent(GaanaApplication.getContext(), DownloadNotificationReceiver.class), C.ENCODING_PCM_MU_LAW));
    }

    private boolean b() {
        String c = d.a().c("PREFF_GAANA_USER_INFO", false);
        if (!TextUtils.isEmpty(c)) {
            UserInfo userInfo = (UserInfo) n.a(c);
            if (userInfo == null || !userInfo.getLoginStatus()) {
                return false;
            }
            if (userInfo.getUserSubscriptionData().getAccountType() == 3 || userInfo.getUserSubscriptionData().getAccountType() == 2) {
                return true;
            }
            return false;
        }
        return false;
    }
}
