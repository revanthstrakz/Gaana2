package com.helpshift.support.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import com.google.android.exoplayer2.C;
import com.helpshift.e.j;
import com.helpshift.support.activities.ParentActivity;
import com.helpshift.util.b;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.y;

public final class i {
    public static Builder a(Context context, Long l, String str, int i, String str2, String str3) {
        Uri parse;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Creating Support notification : \n Id : ");
        stringBuilder.append(str);
        stringBuilder.append("\n Title : ");
        stringBuilder.append(str3);
        stringBuilder.append("\n Message count : ");
        stringBuilder.append(i);
        l.a("Helpshift_SupportNotif", stringBuilder.toString());
        o.d().h().a(i);
        String quantityString = context.getResources().getQuantityString(j.hs__notification_content_title, i, new Object[]{Integer.valueOf(i)});
        int a = y.a(context);
        Integer b = o.d().m().b("notificationSoundId");
        Bitmap bitmap = null;
        if (b != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("android.resource://");
            stringBuilder2.append(context.getPackageName());
            stringBuilder2.append("/");
            stringBuilder2.append(b);
            parse = Uri.parse(stringBuilder2.toString());
        } else {
            parse = null;
        }
        Integer b2 = o.d().m().b("notificationIconId");
        if (b2 != null) {
            a = b2.intValue();
        }
        b2 = o.d().m().b("notificationLargeIconId");
        if (b2 != null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), b2.intValue());
        }
        int abs = str != null ? Math.abs(str.hashCode()) : 0;
        Intent intent = new Intent(context, ParentActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("support_mode", 1);
        intent.putExtra("conversationIdInPush", l);
        intent.putExtra("chatLaunchSource", str2);
        intent.putExtra("isRoot", true);
        PendingIntent activity = PendingIntent.getActivity(context, abs, intent, 0);
        Builder builder = new Builder(context);
        builder.setSmallIcon(a);
        builder.setContentTitle(str3);
        builder.setContentText(quantityString);
        builder.setContentIntent(activity);
        builder.setAutoCancel(true);
        if (bitmap != null) {
            builder.setLargeIcon(bitmap);
        }
        if (parse != null) {
            builder.setSound(parse);
            if (b.a(context, "android.permission.VIBRATE")) {
                builder.setDefaults(6);
            } else {
                builder.setDefaults(4);
            }
        } else if (b.a(context, "android.permission.VIBRATE")) {
            builder.setDefaults(-1);
        } else {
            builder.setDefaults(5);
        }
        return builder;
    }
}
