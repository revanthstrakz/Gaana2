package com.helpshift.campaigns.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.helpshift.campaigns.models.AnalyticsEvent.a;
import com.helpshift.util.b;

public class NotificationService extends Service {
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String stringExtra = intent.getStringExtra("campaignId");
        b.a((Context) this, stringExtra, 1);
        com.helpshift.campaigns.c.b.a().e.a(Integer.valueOf(intent.getIntExtra("type", a.a.intValue())), stringExtra, Boolean.valueOf(false));
        stopSelf();
        return 2;
    }
}
