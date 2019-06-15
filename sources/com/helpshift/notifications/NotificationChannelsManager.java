package com.helpshift.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build.VERSION;
import com.helpshift.e.k;
import com.helpshift.k.b;
import com.helpshift.util.o;
import com.helpshift.util.w;

public class NotificationChannelsManager {
    private final Context a;

    public enum NotificationChannelType {
        SUPPORT,
        CAMPAIGN
    }

    public NotificationChannelsManager(Context context) {
        this.a = context;
    }

    private String a(NotificationChannelType notificationChannelType) {
        switch (notificationChannelType) {
            case SUPPORT:
                return a();
            case CAMPAIGN:
                return b();
            default:
                throw new IllegalStateException();
        }
    }

    private String a() {
        String c = o.d().m().c("supportNotificationChannelId");
        if (w.a(c)) {
            c();
            return "helpshift_default_channel_id";
        }
        d();
        return c;
    }

    private String b() {
        String str = b.a().a.l;
        if (w.a(str)) {
            c();
            return "helpshift_default_channel_id";
        }
        d();
        return str;
    }

    @TargetApi(26)
    private void c() {
        String str = "helpshift_default_channel_id";
        NotificationManager d = com.helpshift.util.b.d(this.a);
        if (d != null && d.getNotificationChannel(str) == null) {
            String string = this.a.getResources().getString(k.hs__default_notification_channel_name);
            String string2 = this.a.getResources().getString(k.hs__default_notification_channel_desc);
            NotificationChannel notificationChannel = new NotificationChannel(str, string, 3);
            notificationChannel.setDescription(string2);
            d.createNotificationChannel(notificationChannel);
        }
    }

    @TargetApi(26)
    private void d() {
        NotificationManager d = com.helpshift.util.b.d(this.a);
        String str = "helpshift_default_channel_id";
        if (d != null && d.getNotificationChannel(str) != null) {
            d.deleteNotificationChannel(str);
        }
    }

    public Notification a(Notification notification, NotificationChannelType notificationChannelType) {
        if (VERSION.SDK_INT < 26 || com.helpshift.util.b.b(this.a) < 26) {
            return notification;
        }
        Builder recoverBuilder = Builder.recoverBuilder(this.a, notification);
        recoverBuilder.setChannelId(a(notificationChannelType));
        return recoverBuilder.build();
    }
}
