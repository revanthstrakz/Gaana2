package com.moengage.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.moengage.core.Logger;
import com.moengage.core.MoEUtils;
import com.moengage.push.PushManager;

public class MoEngaeFireBaseMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage == null) {
            Logger.e("MoEngageFireBaseMessagingService:onMessageReceived : RemoteMessage Null");
            return;
        }
        PushManager.getInstance().getPushHandler().handlePushPayload(getApplicationContext(), MoEUtils.convertMapToBundle(remoteMessage.getData()));
    }

    public void onDeletedMessages() {
        Logger.e("Missed some messages which has now expired");
    }
}
