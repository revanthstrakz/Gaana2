package com.library.util;

import android.content.Context;
import android.text.TextUtils;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import net.hockeyapp.android.b;
import net.hockeyapp.android.c;

public class CrashUtil {
    public static void checkCrashesByHockey(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            b.a(context, str, new c() {
                public boolean shouldAutoUploadCrashes() {
                    return true;
                }

                public String getUserID() {
                    UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
                    return (currentUser == null || !currentUser.getLoginStatus()) ? "Not Logged In" : currentUser.getUserProfile().getUserId();
                }
            });
        }
    }
}
