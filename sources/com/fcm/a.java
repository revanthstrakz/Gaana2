package com.fcm;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.i.i;
import com.library.managers.TaskManager.TaskListner;
import com.managers.URLManager;
import com.moengage.push.PushManager;
import com.services.d;
import com.services.h;
import com.services.l.s;
import java.util.HashMap;

public class a {
    public static void a() {
        a(d.a().b("SHARED_PREF_OLD_GCM_TOKEN", "", false));
    }

    public static void b() {
        a("");
    }

    private static void a(final String str) {
        h.a().a(new TaskListner() {
            private String b = null;

            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                d.a();
                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        AnonymousClass1.this.b = task.isSuccessful() ? ((InstanceIdResult) task.getResult()).getToken() : "";
                        if (!TextUtils.isEmpty(AnonymousClass1.this.b)) {
                            com.helpshift.a.a(GaanaApplication.getContext(), AnonymousClass1.this.b);
                            PushManager.getInstance().refreshToken(GaanaApplication.getContext(), AnonymousClass1.this.b);
                            if (Constants.b) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("GCM token sent: ");
                                stringBuilder.append(AnonymousClass1.this.b);
                                Log.d("GaanaGcm", stringBuilder.toString());
                            }
                        }
                        a.b(AnonymousClass1.this.b, str);
                    }
                });
            }
        }, -1);
    }

    private static void b(String str, String str2) {
        if (!str.equalsIgnoreCase(str2)) {
            String replace = "https://api.gaana.com/user.php?type=update_push_notification_setting&gcm_token=<gcm_token>&platform=android".replace("<gcm_token>", str);
            UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
            if (!TextUtils.isEmpty(str2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(replace);
                stringBuilder.append("&old_device_token=");
                stringBuilder.append(str2);
                replace = stringBuilder.toString();
            }
            d a = d.a();
            if (!str2.equalsIgnoreCase(str)) {
                a.a("SHARED_PREF_OLD_GCM_TOKEN", str, false);
            }
            if (currentUser != null && currentUser.getLoginStatus()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(replace);
                stringBuilder2.append("&token=");
                stringBuilder2.append(currentUser.getAuthToken());
                replace = stringBuilder2.toString();
            }
            str = replace.replace(" ", "%20");
            boolean b = a.b("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", true, false);
            boolean b2 = a.b("PREFERENCE_KEY_NOTIFICATION_FAVORITE_PLAYLIST", true, false);
            boolean b3 = a.b("PREFERENCE_KEY_NOTIFICATION_FOLLOW_UPDATES", true, false);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("{\"music_recommendations\":");
            stringBuilder3.append(b);
            stringBuilder3.append(",\"someone_favourites_my_playlist\":");
            stringBuilder3.append(b2);
            stringBuilder3.append(",\"someone_follows_me\":");
            stringBuilder3.append(b3);
            stringBuilder3.append("}");
            str2 = stringBuilder3.toString();
            URLManager uRLManager = new URLManager();
            uRLManager.c(1);
            uRLManager.a(str);
            HashMap hashMap = new HashMap();
            hashMap.put("push_notification_info", str2);
            uRLManager.a(hashMap);
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                }
            }, uRLManager);
        }
    }
}
