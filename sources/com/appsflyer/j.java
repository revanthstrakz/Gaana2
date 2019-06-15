package com.appsflyer;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class j extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        Object token;
        super.onTokenRefresh();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            token = FirebaseInstanceId.getInstance().getToken();
        } catch (Throwable th) {
            AFLogger.a("Error registering for uninstall tracking", th);
            token = null;
        }
        if (token != null) {
            AFLogger.d("Firebase Refreshed Token = ".concat(String.valueOf(token)));
            r a = r.a(i.a().a("afUninstallToken"));
            r rVar = new r(currentTimeMillis, token);
            if (a.a(rVar)) {
                ag.a(getApplicationContext(), rVar);
            }
        }
    }
}
