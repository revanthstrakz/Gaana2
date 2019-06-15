package com.managers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.google.android.now.NowAuthService;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.TaskManager.TaskListner;
import com.services.d;
import com.services.h;
import com.services.i;
import com.services.j;
import com.utilities.Util;
import org.json.JSONObject;

public class v {
    private static v a;
    private final String b = "776891288343-9qek5kfpa2fha96pdo4i8g744m75pmrg.apps.googleusercontent.com";
    private final String c = "PREFF_GOOGLE_NOW_AUTH_CODE";
    private final String d = "NOW_CARD";
    private final int e = 12;

    public static v a() {
        if (a == null) {
            a = new v();
        }
        return a;
    }

    public void b() {
        if (Util.j(GaanaApplication.getContext())) {
            LoginManager.getInstance().loginSilently(null, new IOnLoginCompleted() {
                public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                    if (userInfo != null && userInfo.getLoginStatus()) {
                        v.this.a(userInfo.getAuthToken());
                    }
                }
            }, false);
        }
    }

    private void a(final String str) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/user.php?type=is_googlenowtoken_valid");
                stringBuilder.append("&token=");
                stringBuilder.append(str);
                i b = new j().b(stringBuilder.toString());
                if (b != null && b.b().booleanValue()) {
                    try {
                        JSONObject jSONObject = new JSONObject(b.a());
                        if (jSONObject.has("status") && jSONObject.getString("status").compareTo("0") == 0) {
                            v.this.b(str);
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }
        }, -1);
    }

    private void b(String str) {
        try {
            String authCode = NowAuthService.getAuthCode(GaanaApplication.getContext(), "776891288343-9qek5kfpa2fha96pdo4i8g744m75pmrg.apps.googleusercontent.com");
            d.a().a("PREFF_GOOGLE_NOW_AUTH_CODE", authCode, true);
            a(str, authCode);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            a(str, "");
        }
    }

    private void a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/user.php?type=send_googlenow_token&googlenow_token=");
        stringBuilder.append(str2);
        str2 = stringBuilder.toString();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str2);
        stringBuilder2.append("&token=");
        stringBuilder2.append(str);
        i b = new j().b(stringBuilder2.toString());
        if (b != null && b.b().booleanValue()) {
            try {
                JSONObject jSONObject = new JSONObject(b.a());
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public void c() {
        if (!d.a().b("PREFERENCE_GOOGLE_NOW_ALARM_REGISTERED", false, false)) {
            a(GaanaApplication.getContext());
        }
    }

    public void a(Context context) {
        b(context);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setRepeating(0, System.currentTimeMillis(), 43200000, PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmManagerBroadcastReceiver.class), 0));
        d.a().a("PREFERENCE_GOOGLE_NOW_ALARM_REGISTERED", true, false);
    }

    public void b(Context context) {
        try {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmManagerBroadcastReceiver.class), 0));
        } catch (Exception unused) {
        }
    }
}
