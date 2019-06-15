package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
    private static final AtomicInteger zzdn = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private Bundle zzdo;
    private final Context zzx;

    public zza(Context context) {
        this.zzx = context.getApplicationContext();
    }

    static boolean zzf(Bundle bundle) {
        return "1".equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }

    static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    static String zzb(Bundle bundle, String str) {
        str = String.valueOf(str);
        String valueOf = String.valueOf("_loc_key");
        return zza(bundle, valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    static Object[] zzc(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String zza = zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zza);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException unused) {
            valueOf = "FirebaseMessaging";
            str = String.valueOf(str);
            String valueOf3 = String.valueOf("_loc_args");
            str = (valueOf3.length() != 0 ? str.concat(valueOf3) : new String(str)).substring(6);
            StringBuilder stringBuilder = new StringBuilder((41 + String.valueOf(str).length()) + String.valueOf(zza).length());
            stringBuilder.append("Malformed ");
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(zza);
            stringBuilder.append("  Default value will be used.");
            Log.w(valueOf, stringBuilder.toString());
            return null;
        }
    }

    @Nullable
    static Uri zzg(@NonNull Bundle bundle) {
        CharSequence zza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zza)) {
            zza = zza(bundle, "gcm.n.link");
        }
        return !TextUtils.isEmpty(zza) ? Uri.parse(zza) : null;
    }

    /* Access modifiers changed, original: final */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0222  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0220  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0230  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02dc  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x031d  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x032e  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0337  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x033c  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0341  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0346  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0372  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0063 A:{RETURN} */
    public final boolean zzh(android.os.Bundle r18) {
        /*
        r17 = this;
        r0 = r17;
        r1 = r18;
        r2 = "1";
        r3 = "gcm.n.noui";
        r3 = zza(r1, r3);
        r2 = r2.equals(r3);
        r3 = 1;
        if (r2 == 0) goto L_0x0014;
    L_0x0013:
        return r3;
    L_0x0014:
        r2 = r0.zzx;
        r4 = "keyguard";
        r2 = r2.getSystemService(r4);
        r2 = (android.app.KeyguardManager) r2;
        r2 = r2.inKeyguardRestrictedInputMode();
        r4 = 0;
        if (r2 != 0) goto L_0x0060;
    L_0x0025:
        r2 = com.google.android.gms.common.util.PlatformVersion.isAtLeastLollipop();
        if (r2 != 0) goto L_0x0030;
    L_0x002b:
        r5 = 10;
        android.os.SystemClock.sleep(r5);
    L_0x0030:
        r2 = android.os.Process.myPid();
        r5 = r0.zzx;
        r6 = "activity";
        r5 = r5.getSystemService(r6);
        r5 = (android.app.ActivityManager) r5;
        r5 = r5.getRunningAppProcesses();
        if (r5 == 0) goto L_0x0060;
    L_0x0044:
        r5 = r5.iterator();
    L_0x0048:
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x0060;
    L_0x004e:
        r6 = r5.next();
        r6 = (android.app.ActivityManager.RunningAppProcessInfo) r6;
        r7 = r6.pid;
        if (r7 != r2) goto L_0x0048;
    L_0x0058:
        r2 = r6.importance;
        r5 = 100;
        if (r2 != r5) goto L_0x0060;
    L_0x005e:
        r2 = r3;
        goto L_0x0061;
    L_0x0060:
        r2 = r4;
    L_0x0061:
        if (r2 == 0) goto L_0x0064;
    L_0x0063:
        return r4;
    L_0x0064:
        r2 = "gcm.n.title";
        r2 = r0.zzd(r1, r2);
        r5 = android.text.TextUtils.isEmpty(r2);
        if (r5 == 0) goto L_0x0080;
    L_0x0070:
        r2 = r0.zzx;
        r2 = r2.getApplicationInfo();
        r5 = r0.zzx;
        r5 = r5.getPackageManager();
        r2 = r2.loadLabel(r5);
    L_0x0080:
        r5 = "gcm.n.body";
        r5 = r0.zzd(r1, r5);
        r6 = "gcm.n.icon";
        r6 = zza(r1, r6);
        r7 = android.text.TextUtils.isEmpty(r6);
        if (r7 != 0) goto L_0x00e8;
    L_0x0092:
        r7 = r0.zzx;
        r7 = r7.getResources();
        r8 = "drawable";
        r9 = r0.zzx;
        r9 = r9.getPackageName();
        r8 = r7.getIdentifier(r6, r8, r9);
        if (r8 == 0) goto L_0x00ad;
    L_0x00a6:
        r9 = r0.zzb(r8);
        if (r9 == 0) goto L_0x00ad;
    L_0x00ac:
        goto L_0x0111;
    L_0x00ad:
        r8 = "mipmap";
        r9 = r0.zzx;
        r9 = r9.getPackageName();
        r8 = r7.getIdentifier(r6, r8, r9);
        if (r8 == 0) goto L_0x00c2;
    L_0x00bb:
        r7 = r0.zzb(r8);
        if (r7 == 0) goto L_0x00c2;
    L_0x00c1:
        goto L_0x0111;
    L_0x00c2:
        r7 = "FirebaseMessaging";
        r8 = 61;
        r9 = java.lang.String.valueOf(r6);
        r9 = r9.length();
        r8 = r8 + r9;
        r9 = new java.lang.StringBuilder;
        r9.<init>(r8);
        r8 = "Icon resource ";
        r9.append(r8);
        r9.append(r6);
        r6 = " not found. Notification will use default icon.";
        r9.append(r6);
        r6 = r9.toString();
        android.util.Log.w(r7, r6);
    L_0x00e8:
        r6 = r17.zzas();
        r7 = "com.google.firebase.messaging.default_notification_icon";
        r6 = r6.getInt(r7, r4);
        if (r6 == 0) goto L_0x00fa;
    L_0x00f4:
        r7 = r0.zzb(r6);
        if (r7 != 0) goto L_0x0102;
    L_0x00fa:
        r6 = r0.zzx;
        r6 = r6.getApplicationInfo();
        r6 = r6.icon;
    L_0x0102:
        if (r6 == 0) goto L_0x010d;
    L_0x0104:
        r7 = r0.zzb(r6);
        if (r7 != 0) goto L_0x010b;
    L_0x010a:
        goto L_0x010d;
    L_0x010b:
        r8 = r6;
        goto L_0x0111;
    L_0x010d:
        r6 = 17301651; // 0x1080093 float:2.4979667E-38 double:8.5481514E-317;
        goto L_0x010b;
    L_0x0111:
        r6 = "gcm.n.color";
        r6 = zza(r1, r6);
        r6 = r0.zzl(r6);
        r7 = zzi(r18);
        r9 = android.text.TextUtils.isEmpty(r7);
        r10 = 0;
        if (r9 == 0) goto L_0x0128;
    L_0x0126:
        r7 = r10;
        goto L_0x0181;
    L_0x0128:
        r9 = "default";
        r9 = r9.equals(r7);
        if (r9 != 0) goto L_0x017c;
    L_0x0130:
        r9 = r0.zzx;
        r9 = r9.getResources();
        r11 = "raw";
        r12 = r0.zzx;
        r12 = r12.getPackageName();
        r9 = r9.getIdentifier(r7, r11, r12);
        if (r9 == 0) goto L_0x017c;
    L_0x0144:
        r9 = r0.zzx;
        r9 = r9.getPackageName();
        r11 = 24;
        r12 = java.lang.String.valueOf(r9);
        r12 = r12.length();
        r11 = r11 + r12;
        r12 = java.lang.String.valueOf(r7);
        r12 = r12.length();
        r11 = r11 + r12;
        r12 = new java.lang.StringBuilder;
        r12.<init>(r11);
        r11 = "android.resource://";
        r12.append(r11);
        r12.append(r9);
        r9 = "/raw/";
        r12.append(r9);
        r12.append(r7);
        r7 = r12.toString();
        r7 = android.net.Uri.parse(r7);
        goto L_0x0181;
    L_0x017c:
        r7 = 2;
        r7 = android.media.RingtoneManager.getDefaultUri(r7);
    L_0x0181:
        r9 = "gcm.n.click_action";
        r9 = zza(r1, r9);
        r11 = android.text.TextUtils.isEmpty(r9);
        if (r11 != 0) goto L_0x01a1;
    L_0x018d:
        r11 = new android.content.Intent;
        r11.<init>(r9);
        r9 = r0.zzx;
        r9 = r9.getPackageName();
        r11.setPackage(r9);
        r9 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r11.setFlags(r9);
        goto L_0x01d4;
    L_0x01a1:
        r9 = zzg(r18);
        if (r9 == 0) goto L_0x01bb;
    L_0x01a7:
        r11 = new android.content.Intent;
        r12 = "android.intent.action.VIEW";
        r11.<init>(r12);
        r12 = r0.zzx;
        r12 = r12.getPackageName();
        r11.setPackage(r12);
        r11.setData(r9);
        goto L_0x01d4;
    L_0x01bb:
        r9 = r0.zzx;
        r9 = r9.getPackageManager();
        r11 = r0.zzx;
        r11 = r11.getPackageName();
        r11 = r9.getLaunchIntentForPackage(r11);
        if (r11 != 0) goto L_0x01d4;
    L_0x01cd:
        r9 = "FirebaseMessaging";
        r12 = "No activity found to launch app";
        android.util.Log.w(r9, r12);
    L_0x01d4:
        r9 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r11 != 0) goto L_0x01da;
    L_0x01d8:
        r11 = r10;
        goto L_0x021e;
    L_0x01da:
        r12 = 67108864; // 0x4000000 float:1.5046328E-36 double:3.31561842E-316;
        r11.addFlags(r12);
        r12 = new android.os.Bundle;
        r12.<init>(r1);
        com.google.firebase.messaging.FirebaseMessagingService.zzj(r12);
        r11.putExtras(r12);
        r12 = r12.keySet();
        r12 = r12.iterator();
    L_0x01f2:
        r13 = r12.hasNext();
        if (r13 == 0) goto L_0x0212;
    L_0x01f8:
        r13 = r12.next();
        r13 = (java.lang.String) r13;
        r14 = "gcm.n.";
        r14 = r13.startsWith(r14);
        if (r14 != 0) goto L_0x020e;
    L_0x0206:
        r14 = "gcm.notification.";
        r14 = r13.startsWith(r14);
        if (r14 == 0) goto L_0x01f2;
    L_0x020e:
        r11.removeExtra(r13);
        goto L_0x01f2;
    L_0x0212:
        r12 = r0.zzx;
        r13 = zzdn;
        r13 = r13.incrementAndGet();
        r11 = android.app.PendingIntent.getActivity(r12, r13, r11, r9);
    L_0x021e:
        if (r1 != 0) goto L_0x0222;
    L_0x0220:
        r12 = r4;
        goto L_0x022e;
    L_0x0222:
        r12 = "1";
        r13 = "google.c.a.e";
        r13 = r1.getString(r13);
        r12 = r12.equals(r13);
    L_0x022e:
        if (r12 == 0) goto L_0x0262;
    L_0x0230:
        r12 = new android.content.Intent;
        r13 = "com.google.firebase.messaging.NOTIFICATION_OPEN";
        r12.<init>(r13);
        zza(r12, r1);
        r13 = "pending_intent";
        r12.putExtra(r13, r11);
        r11 = r0.zzx;
        r13 = zzdn;
        r13 = r13.incrementAndGet();
        r11 = com.google.firebase.iid.zzav.zza(r11, r13, r12, r9);
        r12 = new android.content.Intent;
        r13 = "com.google.firebase.messaging.NOTIFICATION_DISMISS";
        r12.<init>(r13);
        zza(r12, r1);
        r13 = r0.zzx;
        r14 = zzdn;
        r14 = r14.incrementAndGet();
        r9 = com.google.firebase.iid.zzav.zza(r13, r14, r12, r9);
        goto L_0x0263;
    L_0x0262:
        r9 = r10;
    L_0x0263:
        r12 = "gcm.n.android_channel_id";
        r12 = zza(r1, r12);
        r13 = com.google.android.gms.common.util.PlatformVersion.isAtLeastO();
        r14 = 3;
        if (r13 == 0) goto L_0x02ff;
    L_0x0270:
        r13 = r0.zzx;
        r13 = r13.getApplicationInfo();
        r13 = r13.targetSdkVersion;
        r15 = 26;
        if (r13 >= r15) goto L_0x027e;
    L_0x027c:
        goto L_0x02ff;
    L_0x027e:
        r10 = r0.zzx;
        r13 = android.app.NotificationManager.class;
        r10 = r10.getSystemService(r13);
        r10 = (android.app.NotificationManager) r10;
        r13 = android.text.TextUtils.isEmpty(r12);
        if (r13 != 0) goto L_0x02bc;
    L_0x028e:
        r13 = r10.getNotificationChannel(r12);
        if (r13 == 0) goto L_0x0296;
    L_0x0294:
        r10 = r12;
        goto L_0x02ff;
    L_0x0296:
        r13 = "FirebaseMessaging";
        r15 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        r4 = java.lang.String.valueOf(r12);
        r4 = r4.length();
        r15 = r15 + r4;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r15);
        r15 = "Notification Channel requested (";
        r4.append(r15);
        r4.append(r12);
        r12 = ") has not been created by the app. Manifest configuration, or default, value will be used.";
        r4.append(r12);
        r4 = r4.toString();
        android.util.Log.w(r13, r4);
    L_0x02bc:
        r4 = r17.zzas();
        r12 = "com.google.firebase.messaging.default_notification_channel_id";
        r4 = r4.getString(r12);
        r12 = android.text.TextUtils.isEmpty(r4);
        if (r12 != 0) goto L_0x02dc;
    L_0x02cc:
        r12 = r10.getNotificationChannel(r4);
        if (r12 == 0) goto L_0x02d4;
    L_0x02d2:
        r10 = r4;
        goto L_0x02ff;
    L_0x02d4:
        r4 = "FirebaseMessaging";
        r12 = "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.";
        android.util.Log.w(r4, r12);
        goto L_0x02e3;
    L_0x02dc:
        r4 = "FirebaseMessaging";
        r12 = "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.";
        android.util.Log.w(r4, r12);
    L_0x02e3:
        r4 = "fcm_fallback_notification_channel";
        r4 = r10.getNotificationChannel(r4);
        if (r4 != 0) goto L_0x02fd;
    L_0x02eb:
        r4 = new android.app.NotificationChannel;
        r12 = "fcm_fallback_notification_channel";
        r13 = r0.zzx;
        r15 = com.google.firebase.messaging.R.string.fcm_fallback_notification_channel_label;
        r13 = r13.getString(r15);
        r4.<init>(r12, r13, r14);
        r10.createNotificationChannel(r4);
    L_0x02fd:
        r10 = "fcm_fallback_notification_channel";
    L_0x02ff:
        r4 = new android.support.v4.app.NotificationCompat$Builder;
        r12 = r0.zzx;
        r4.<init>(r12);
        r4 = r4.setAutoCancel(r3);
        r4 = r4.setSmallIcon(r8);
        r8 = android.text.TextUtils.isEmpty(r2);
        if (r8 != 0) goto L_0x0317;
    L_0x0314:
        r4.setContentTitle(r2);
    L_0x0317:
        r2 = android.text.TextUtils.isEmpty(r5);
        if (r2 != 0) goto L_0x032c;
    L_0x031d:
        r4.setContentText(r5);
        r2 = new android.support.v4.app.NotificationCompat$BigTextStyle;
        r2.<init>();
        r2 = r2.bigText(r5);
        r4.setStyle(r2);
    L_0x032c:
        if (r6 == 0) goto L_0x0335;
    L_0x032e:
        r2 = r6.intValue();
        r4.setColor(r2);
    L_0x0335:
        if (r7 == 0) goto L_0x033a;
    L_0x0337:
        r4.setSound(r7);
    L_0x033a:
        if (r11 == 0) goto L_0x033f;
    L_0x033c:
        r4.setContentIntent(r11);
    L_0x033f:
        if (r9 == 0) goto L_0x0344;
    L_0x0341:
        r4.setDeleteIntent(r9);
    L_0x0344:
        if (r10 == 0) goto L_0x0349;
    L_0x0346:
        r4.setChannelId(r10);
    L_0x0349:
        r2 = r4.build();
        r4 = "gcm.n.tag";
        r1 = zza(r1, r4);
        r4 = "FirebaseMessaging";
        r4 = android.util.Log.isLoggable(r4, r14);
        if (r4 == 0) goto L_0x0362;
    L_0x035b:
        r4 = "FirebaseMessaging";
        r5 = "Showing notification";
        android.util.Log.d(r4, r5);
    L_0x0362:
        r4 = r0.zzx;
        r5 = "notification";
        r4 = r4.getSystemService(r5);
        r4 = (android.app.NotificationManager) r4;
        r5 = android.text.TextUtils.isEmpty(r1);
        if (r5 == 0) goto L_0x0389;
    L_0x0372:
        r5 = android.os.SystemClock.uptimeMillis();
        r1 = 37;
        r7 = new java.lang.StringBuilder;
        r7.<init>(r1);
        r1 = "FCM-Notification:";
        r7.append(r1);
        r7.append(r5);
        r1 = r7.toString();
    L_0x0389:
        r5 = 0;
        r4.notify(r1, r5, r2);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzh(android.os.Bundle):boolean");
    }

    private final String zzd(Bundle bundle, String str) {
        String zza = zza(bundle, str);
        if (!TextUtils.isEmpty(zza)) {
            return zza;
        }
        zza = zzb(bundle, str);
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        Resources resources = this.zzx.getResources();
        int identifier = resources.getIdentifier(zza, "string", this.zzx.getPackageName());
        String str2;
        if (identifier == 0) {
            str2 = "FirebaseMessaging";
            str = String.valueOf(str);
            String valueOf = String.valueOf("_loc_key");
            str = (valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).substring(6);
            StringBuilder stringBuilder = new StringBuilder((49 + String.valueOf(str).length()) + String.valueOf(zza).length());
            stringBuilder.append(str);
            stringBuilder.append(" resource not found: ");
            stringBuilder.append(zza);
            stringBuilder.append(" Default value will be used.");
            Log.w(str2, stringBuilder.toString());
            return null;
        }
        Object[] zzc = zzc(bundle, str);
        if (zzc == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzc);
        } catch (MissingFormatArgumentException e) {
            str2 = Arrays.toString(zzc);
            StringBuilder stringBuilder2 = new StringBuilder((58 + String.valueOf(zza).length()) + String.valueOf(str2).length());
            stringBuilder2.append("Missing format argument for ");
            stringBuilder2.append(zza);
            stringBuilder2.append(": ");
            stringBuilder2.append(str2);
            stringBuilder2.append(" Default value will be used.");
            Log.w("FirebaseMessaging", stringBuilder2.toString(), e);
            return null;
        }
    }

    @TargetApi(26)
    private final boolean zzb(int i) {
        if (VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(this.zzx.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            StringBuilder stringBuilder = new StringBuilder(77);
            stringBuilder.append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
            stringBuilder.append(i);
            Log.e("FirebaseMessaging", stringBuilder.toString());
            return false;
        } catch (NotFoundException unused) {
            return false;
        }
    }

    private final Integer zzl(String str) {
        if (VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException unused) {
                StringBuilder stringBuilder = new StringBuilder(54 + String.valueOf(str).length());
                stringBuilder.append("Color ");
                stringBuilder.append(str);
                stringBuilder.append(" not valid. Notification will use default color.");
                Log.w("FirebaseMessaging", stringBuilder.toString());
            }
        }
        int i = zzas().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i != 0) {
            try {
                return Integer.valueOf(ContextCompat.getColor(this.zzx, i));
            } catch (NotFoundException unused2) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            }
        }
        return null;
    }

    static String zzi(Bundle bundle) {
        String zza = zza(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(zza) ? zza(bundle, "gcm.n.sound") : zza;
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final Bundle zzas() {
        if (this.zzdo != null) {
            return this.zzdo;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.zzx.getPackageManager().getApplicationInfo(this.zzx.getPackageName(), 128);
        } catch (NameNotFoundException unused) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.zzdo = applicationInfo.metaData;
        return this.zzdo;
    }
}
