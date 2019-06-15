package com.managers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.comscore.utils.Constants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.android.exoplayer2.C;
import com.services.DownloadScheduleReceiver;
import com.services.d;
import com.utilities.Util;
import java.util.Calendar;
import java.util.Date;

public class k {
    private static k a;
    private d b = d.a();
    private String c;

    public static k a() {
        if (a == null) {
            synchronized (k.class) {
                if (a == null) {
                    a = new k();
                }
            }
        }
        return a;
    }

    private k() {
    }

    public void a(Context context, Calendar calendar, long j) {
        a(context);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setRepeating(0, calendar.getTimeInMillis(), j, PendingIntent.getBroadcast(context, 9999, new Intent(context, DownloadScheduleReceiver.class), C.ENCODING_PCM_MU_LAW));
    }

    public void a(Context context) {
        try {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(context, 9999, new Intent(context, DownloadScheduleReceiver.class), C.ENCODING_PCM_MU_LAW));
        } catch (Exception unused) {
        }
    }

    public void b(Context context) {
        Date date = new Date();
        String[] stringArray = context.getResources().getStringArray(R.array.schedule_time_slots);
        String str = stringArray[this.b.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM", 0, true)];
        int parseInt = Integer.parseInt(str.substring(0, 2));
        int i = (parseInt == 12 || !str.contains("PM")) ? (parseInt == 12 && str.contains("AM")) ? 0 : parseInt : parseInt + 12;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.set(11, i);
        instance.set(12, 0);
        instance.set(13, 0);
        String str2 = stringArray[this.b.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO", 0, true)];
        int parseInt2 = Integer.parseInt(str2.substring(0, 2));
        int i2 = (parseInt2 == 12 || !str2.contains("PM")) ? (parseInt2 == 12 && str2.contains("AM")) ? 0 : parseInt2 : parseInt2 + 12;
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(System.currentTimeMillis());
        instance2.set(11, i2);
        instance2.set(12, 0);
        instance2.set(13, 0);
        if (instance.getTime().compareTo(date) < 0 && instance2.getTime().compareTo(date) <= 0) {
            instance.add(5, 1);
        }
        if (instance2.getTime().compareTo(date) < 0) {
            instance2.add(5, 1);
        }
        if (instance.getTime().compareTo(date) < 0 && instance2.getTime().compareTo(date) < 0 && i > i2) {
            instance2.add(5, 1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(instance.getTimeInMillis());
        this.b.a("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM_TIME", stringBuilder.toString(), true);
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(instance2.getTimeInMillis());
        this.b.a("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO_TIME", stringBuilder.toString(), true);
        a(GaanaApplication.getContext(), instance, Constants.SESSION_INACTIVE_PERIOD);
    }

    public boolean b() {
        if (Util.k(GaanaApplication.getContext()) == 0 && this.b.b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true) && this.b.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
            return true;
        }
        return false;
    }

    public boolean c(Context context) {
        String b = d.a().b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM_TIME", null, true);
        String b2 = d.a().b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO_TIME", null, true);
        if (!(b == null || b2 == null)) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(Long.valueOf(b).longValue());
            Calendar instance2 = Calendar.getInstance();
            instance2.setTimeInMillis(Long.valueOf(b2).longValue());
            Date date = new Date();
            if (instance2.getTime().compareTo(date) > 0 && instance.getTime().compareTo(date) < 0) {
                return true;
            }
        }
        return false;
    }

    public void a(String str) {
        StringBuilder stringBuilder;
        if (this.c != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.c);
            stringBuilder.append(str);
            stringBuilder.append(",");
            this.c = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(",");
            this.c = stringBuilder.toString();
        }
        o.a().d(this.c);
    }

    public void c() {
        if (this.c != null) {
            this.c = null;
        }
        o.a().d(this.c);
    }
}
