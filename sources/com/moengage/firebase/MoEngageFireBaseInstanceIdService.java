package com.moengage.firebase;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.push.PushManager;
import com.moengage.pushbase.push.MoEPushWorkerTask;

public class MoEngageFireBaseInstanceIdService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        try {
            Logger.v("MoEngageFireBaseInstanceIdService inside onTokenRefresh");
            MoEDispatcher.getInstance(getApplicationContext()).addTaskToQueueBeginning(new MoEPushWorkerTask(getApplicationContext(), PushManager.REQ_REFRESH, null));
        } catch (Exception e) {
            Logger.f("MoEngageFireBaseInstanceIdService: onTokenRefresh()", e);
        }
    }
}
