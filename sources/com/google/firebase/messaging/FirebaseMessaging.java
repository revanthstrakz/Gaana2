package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.regex.Pattern;

public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zzdp = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private static FirebaseMessaging zzdq;
    private final FirebaseInstanceId zzdj;

    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            if (zzdq == null) {
                zzdq = new FirebaseMessaging(FirebaseInstanceId.getInstance());
            }
            firebaseMessaging = zzdq;
        }
        return firebaseMessaging;
    }

    private FirebaseMessaging(FirebaseInstanceId firebaseInstanceId) {
        this.zzdj = firebaseInstanceId;
    }

    public boolean isAutoInitEnabled() {
        return this.zzdj.zzr();
    }

    public void setAutoInitEnabled(boolean z) {
        this.zzdj.zzb(z);
    }

    public Task<Void> subscribeToTopic(String str) {
        CharSequence str2;
        if (str2 != null && str2.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            str2 = str2.substring(8);
        }
        if (str2 == null || !zzdp.matcher(str2).matches()) {
            StringBuilder stringBuilder = new StringBuilder(78 + String.valueOf(str2).length());
            stringBuilder.append("Invalid topic name: ");
            stringBuilder.append(str2);
            stringBuilder.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zzdj;
        String valueOf = String.valueOf("S!");
        str2 = String.valueOf(str2);
        return firebaseInstanceId.zza(str2.length() != 0 ? valueOf.concat(str2) : new String(valueOf));
    }

    public Task<Void> unsubscribeFromTopic(String str) {
        CharSequence str2;
        if (str2 != null && str2.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            str2 = str2.substring(8);
        }
        if (str2 == null || !zzdp.matcher(str2).matches()) {
            StringBuilder stringBuilder = new StringBuilder(78 + String.valueOf(str2).length());
            stringBuilder.append("Invalid topic name: ");
            stringBuilder.append(str2);
            stringBuilder.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zzdj;
        String valueOf = String.valueOf("U!");
        str2 = String.valueOf(str2);
        return firebaseInstanceId.zza(str2.length() != 0 ? valueOf.concat(str2) : new String(valueOf));
    }

    public void send(RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        Intent intent2 = new Intent();
        intent2.setPackage("com.google.example.invalidpackage");
        intent.putExtra("app", PendingIntent.getBroadcast(applicationContext, 0, intent2, 0));
        intent.setPackage("com.google.android.gms");
        intent.putExtras(remoteMessage.zzds);
        applicationContext.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }
}
