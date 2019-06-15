package com.moengage.push;

import android.content.Context;
import android.support.annotation.Nullable;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import org.json.JSONObject;

public class MoEMessagingManager {
    private static MoEMessagingManager _INSTANCE;
    private MessagingHandler messagingHandler = null;

    public interface MessagingHandler {
        void forceMessageSync(Context context, boolean z);

        void parsePayloadAndShowPush(Context context, JSONObject jSONObject);

        void saveCampaignId(Context context, String str);

        void scheduleAndSyncMessages(Context context);

        void scheduleMessageSync(Context context);
    }

    private MoEMessagingManager() {
        loadHandler();
    }

    public static MoEMessagingManager getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new MoEMessagingManager();
        }
        return _INSTANCE;
    }

    private void loadHandler() {
        try {
            this.messagingHandler = (MessagingHandler) Class.forName("com.moengage.addon.messaging.MessagingHandlerImpl").newInstance();
            Logger.v("MoEMessagingManager:loadHandler Messaging module Enabled");
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEMessagingManager : loadHandler : did not find supported module: ");
            stringBuilder.append(e.getMessage());
            Logger.e(stringBuilder.toString());
        }
    }

    @Nullable
    public MessagingHandler getMessagingHandler(Context context) {
        return ConfigurationProvider.getInstance(context).isInboxEnabled() ? this.messagingHandler : null;
    }
}
