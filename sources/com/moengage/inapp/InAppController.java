package com.moengage.inapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.WorkerThread;
import com.moe.pushlibrary.models.Event;
import com.moengage.core.Logger;
import java.util.HashMap;
import org.json.JSONObject;

public class InAppController {
    public static String SINGLE_API_FAILURE = "single_fetch_api_failure";
    public static String SMART_API_FAILURE = "smart_api_fail";
    public static String SYNC_API_FAILURE = "sync_api_fail";
    private static InAppController _INSTANCE;
    private InAppHandler inAppHandler;
    private String mActivityName = null;
    private int mActivityOrientation = -1;

    public interface InAppHandler {
        void fetchLinkedInApp(Context context, String str);

        void logInAppAPIFailure(String str);

        void parseAndSaveInApps(JSONObject jSONObject, Context context);

        void registerAutoTriggerEvent(Context context, Event event);

        void registerInAppManager(Activity activity);

        void setInappsSynced(boolean z);

        void showInAppIfPossible(Context context);

        void showInAppOnConfigurationChange(Context context);

        void showLinkedInApp(Context context, JSONObject jSONObject, HashMap<String, String> hashMap);

        void showTestInAppErrorDialog(String str);

        void syncOrShowInApps(Context context);

        @WorkerThread
        void tryShowAutoTriggerInApp(Context context, JSONObject jSONObject);

        void unregisterInAppManager(Activity activity);

        void writeInAppCheckFailureCounterToStorage(Context context);
    }

    public enum NETWORK_CALL_TYPE {
        AUTO_TRIGGER_EVENT,
        SYNC_IN_APPS,
        SINGLE_FETCH
    }

    private InAppController() {
        loadInAppHandler();
    }

    private void loadInAppHandler() {
        try {
            this.inAppHandler = (InAppHandler) Class.forName("com.moengage.inapp.InAppHandlerImpl").newInstance();
            Logger.v("InAppController:loadInAppHandler InApp Module present");
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("InAppController : loadInAppHandler : InApp Module not present ");
            stringBuilder.append(e.getMessage());
            Logger.e(stringBuilder.toString());
        }
    }

    public InAppHandler getInAppHandler() {
        return this.inAppHandler;
    }

    public static InAppController getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new InAppController();
        }
        return _INSTANCE;
    }

    public String getActivityName() {
        return this.mActivityName;
    }

    public void setActivityName(String str) {
        this.mActivityName = str;
    }

    public int getActivityOrientation() {
        return this.mActivityOrientation;
    }

    public void setActivityOrientation(int i) {
        this.mActivityOrientation = i;
    }

    public void trackAPIFailure(String str) {
        InAppHandler inAppHandler = getInAppHandler();
        if (inAppHandler != null) {
            inAppHandler.logInAppAPIFailure(str);
        }
    }
}
