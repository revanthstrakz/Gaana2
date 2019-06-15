package com.moengage.pushbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.moengage.core.Logger;
import com.moengage.push.PushManager;
import com.moengage.pushbase.PushActionMapperConstants;
import com.moengage.pushbase.push.MoEPushCallBacks;
import com.moengage.pushbase.push.PushActionManager;
import com.moengage.pushbase.push.PushMessageListener;
import org.json.JSONObject;

public class SnoozeTracker extends FragmentActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.v("SnoozeTracker:Reached");
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
            if (bundle != null) {
                PushMessageListener pushMessageListener = (PushMessageListener) PushManager.getInstance().getPushHandler().getMessageListener();
                pushMessageListener.dismissNotificationAfterClick(getApplicationContext(), bundle);
                pushMessageListener.logNotificationClicked(getApplicationContext(), getIntent());
                MoEPushCallBacks.getInstance().onPushClicked(bundle);
                if (bundle.containsKey(PushActionMapperConstants.KEY_ACTION_TAG)) {
                    Logger.v("SnoozeTracker: Redirecting to ActionMappper");
                    String string = bundle.getString(PushActionMapperConstants.KEY_ACTION_TAG);
                    try {
                        PushActionManager.getInstance().onActionPerformed(this, string, new JSONObject(bundle.getString(PushActionMapperConstants.KEY_ACTION_PAYLOAD)));
                    } catch (Exception e) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("SnoozeTracker: error converting string to JSON,");
                        stringBuilder.append(e.getMessage());
                        Logger.f(stringBuilder.toString());
                    }
                }
                if (!PushActionManager.isDialogShown()) {
                    finish();
                    Logger.v("SnoozeTracker:Completed");
                }
            }
        }
    }
}
