package com.moengage.pushbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moengage.core.Logger;
import com.moengage.core.MoEDispatcher;
import com.moengage.push.PushManager;
import com.moengage.pushbase.PushActionMapperConstants;
import com.moengage.pushbase.PushConstants;
import com.moengage.pushbase.push.MoEPushCallBacks;
import com.moengage.pushbase.push.MoEngageNotificationUtils;
import com.moengage.pushbase.push.PushActionManager;
import com.moengage.pushbase.push.PushMessageListener;
import org.json.JSONObject;

public class PushTracker extends FragmentActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.v("PushTracker:Reached ");
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                boolean hasExtra = intent.hasExtra(MoEHelperConstants.GCM_EXTRA_WEB_URL);
                PushMessageListener pushMessageListener = (PushMessageListener) PushManager.getInstance().getPushHandler().getMessageListener();
                pushMessageListener.dismissNotificationAfterClick(getApplicationContext(), extras);
                pushMessageListener.logNotificationClicked(getApplicationContext(), getIntent());
                MoEngageNotificationUtils.deleteImagesFromInternal(this, MoEngageNotificationUtils.getCampaignIdIfAny(extras));
                MoEPushCallBacks.getInstance().onPushClicked(extras);
                if (extras.containsKey(InAppConstants.LINKED_IN_APP)) {
                    String string = extras.getString(InAppConstants.LINKED_IN_APP);
                    if (!TextUtils.isEmpty(string)) {
                        MoEDispatcher.getInstance(getApplicationContext()).checkAndShowLinkedInApp(string);
                    }
                }
                if (extras.containsKey(PushActionMapperConstants.KEY_ACTION_TAG)) {
                    Logger.v("PushTracker: Redirecting to ActionMapper");
                    String string2 = extras.getString(PushActionMapperConstants.KEY_ACTION_TAG);
                    try {
                        PushActionManager.getInstance().onActionPerformed(this, string2, new JSONObject(extras.getString(PushActionMapperConstants.KEY_ACTION_PAYLOAD)));
                    } catch (Exception e) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("PushTracker: error converting string to JSON,");
                        stringBuilder.append(e.getMessage());
                        Logger.f(stringBuilder.toString());
                    }
                } else {
                    extras.remove(MoEHelperConstants.NOTIFICATION_RECEIVED_MOE);
                    extras.remove(MoEHelperConstants.GCM_EXTRA_CAMPAIGN_ID);
                    if (extras.containsKey(PushConstants.ATTR_CAMPAIGN_ATTRIBUTES)) {
                        extras.remove(PushConstants.ATTR_CAMPAIGN_ATTRIBUTES);
                    }
                    pushMessageListener.onHandleRedirection(this, extras);
                }
                if (hasExtra) {
                    MoEHelper.getInstance(getApplicationContext()).syncInteractionDataNow();
                }
                finish();
                Logger.v("PushTracker:Completed");
            }
        }
    }
}
