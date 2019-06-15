package com.player_framework;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.RequiresApi;
import com.moe.pushlibrary.utils.MoEHelperConstants;

public class h extends ContextWrapper {
    private NotificationManager a;

    public h(Context context) {
        super(context);
    }

    @RequiresApi(26)
    public void a() {
        NotificationChannel notificationChannel = new NotificationChannel("com.gaana.play", "Media playback", 2);
        notificationChannel.setDescription("Media playback controls");
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        b().createNotificationChannel(notificationChannel);
        notificationChannel = new NotificationChannel("com.gaana.download", "Downloads", 2);
        notificationChannel.setDescription("Download info");
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        b().createNotificationChannel(notificationChannel);
        notificationChannel = new NotificationChannel("com.gaana.social", "Social notification", 3);
        notificationChannel.setDescription("Social notification");
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        b().createNotificationChannel(notificationChannel);
        notificationChannel = new NotificationChannel("com.gaana.offer", "Offers and Promotions", 3);
        notificationChannel.setDescription("Offers and promotions");
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        b().createNotificationChannel(notificationChannel);
        notificationChannel = new NotificationChannel("com.gaana.recommendation", "Recommendations", 3);
        notificationChannel.setDescription("Recommendations");
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        b().createNotificationChannel(notificationChannel);
        notificationChannel = new NotificationChannel("com.gaana.other", "Transactional", 3);
        notificationChannel.setDescription("Others");
        notificationChannel.setShowBadge(false);
        notificationChannel.setLockscreenVisibility(1);
        b().createNotificationChannel(notificationChannel);
    }

    private NotificationManager b() {
        if (this.a == null) {
            this.a = (NotificationManager) getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
        }
        return this.a;
    }
}
