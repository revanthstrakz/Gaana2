package io.branch.referral;

import android.app.Application;
import android.content.Context;

public class BranchApp extends Application {
    public void onCreate() {
        super.onCreate();
        if (h.a(this)) {
            Branch.b((Context) this);
        } else {
            Branch.a((Context) this);
        }
    }
}
